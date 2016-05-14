package net.Andrewcpu.tests.agar;

import javafx.scene.input.KeyCode;
import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.listeners.KeyListener;
import net.Andrewcpu.engine.listeners.MouseListener;
import net.Andrewcpu.engine.world.*;
import net.Andrewcpu.tests.agar.world.entities.Bullet;
import net.Andrewcpu.tests.agar.world.entities.Food;
import net.Andrewcpu.tests.agar.world.entities.Player;
import net.Andrewcpu.tests.agar.world.frame.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.util.*;

/**
 * Created by stein on 5/10/2016.
 */
public class Main extends JFrame{
    public static void main(String[] args){
        new Main();
    }
    private GameFrame frame = null;
    public Main(){
        setLayout(null);
        setBounds(0,0,1000,700);
        Engine.setWIDTH(getWidth());
        Engine.setHEIGHT(getHeight());
        setTitle("Game");
        setupWorld();
        frame = new GameFrame();
        frame.setBounds(getBounds());
        add(frame);
        setVisible(true);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                frame.setBounds(0,0,getWidth(),getHeight());
                repaint();
                Engine.setWIDTH(getWidth());
                Engine.setHEIGHT(getHeight());
            }
        });
        Engine.startTickLoop(30);
    }
    public static Player player = new Player(250,250,50,50);
    private java.util.List<Player> enemies = new ArrayList<>();
    private Random random = new Random();
    public void createEnemies(){
            int x = random.nextInt(Engine.getWIDTH());
            int y = random.nextInt(Engine.getHEIGHT());
            Player enemy = new Player(x,y,50,50, true);
            enemy.setSize(random.nextInt(11 - 1)+ 1);
            enemy.setHealth(enemy.getMaxHealth());
            enemy.setRespawn(false);
            enemies.add(enemy);
            enemy.setColor(Color.RED);
            enemy.setSpeed(3);
        enemy.setTarget(player);
            World.getInstance().addEntity(enemy);
    }

    public void setupWorld(){
        createEnemies();
        createEnemies();
        player.setSize(5);
        World.getInstance().setColor(Color.BLACK);
        player.setHealth(player.getMaxHealth());
        World world = World.getInstance();
        world.addEntity(player);

        Engine.getEventManager().registerTickListener(()->tick());
        Engine.getEventManager().registerCollisionListener((entity1,entity2)->{
            if(entity1 instanceof Player && entity2 instanceof Bullet && ((Bullet)entity2).getShooter()!=entity1){
                ((Player)entity1).damage(10);
                ((Player)((Bullet)entity2).getShooter()).heal(5);
                World.getInstance().removeEntity(entity2);
            }
//            if(entity1 instanceof Player && entity2 instanceof Player){
//                Player player = (Player)entity1;
//                Player player2 = (Player)entity2;
//                if(player2.isAI() && player.getSize() > player2.getSize()){
//                    int health = player2.getHealth();
//                    player.setSize(player2.getSize()/2 + player.getSize());
//                    if(player.getHealth() + health > player.getMaxHealth()){
//                        player.setHealth(player.getMaxHealth());
//                    }
//                    else{
//                        player.setHealth(player.getHealth() + health);
//                    }
//                    World.getInstance().removeEntity(player2);
//                }
//            }
//            if(entity1 instanceof Player && entity2 instanceof Food){
//                ((Player)entity1).eat(((Food)entity2));
//            }
        });
        Engine.getEventManager().registerMouseListener(new MouseListener() {
            @Override
            public void mouseMoved(Point point) {
            }
            @Override
            public void mouseDragged(Point point) {
            }
            @Override
            public void mouseClicked(Point point){
             //   player.setAI(true);
               // player.setTarget(point);
            }
        });
        Engine.getEventManager().registerKeyListener(new KeyListener() {
            @Override
            public void keyPressed(int keyCode) {
                if(keyCode== KeyEvent.VK_C){
                    for(Entity e : World.getInstance().getEntitiesByClass(Bullet.class)){
                        World.getInstance().removeEntity(e);
                    }
                }
                while(player.isPressing(keyCode))
                    player.releaseKey(keyCode);
                player.pressKey(keyCode);
            }

            @Override
            public void keyReleased(int keyCode) {
                while(player.isPressing(keyCode))
                    player.releaseKey(keyCode);
            }
        });
    }

    private int nextEnemy = 0;

    public void generateFood(){
        int x = random.nextInt(Engine.getWIDTH());
        int y = random.nextInt(Engine.getHEIGHT());
        Food food = new Food(x,y,10,10,10);
        World.getInstance().addEntity(food);
    }

    public void tick(){
        frame.requestFocus();
        for(Player enemy : enemies){
            enemy.setTarget(player);
        }
        boolean charge = true;
        for(int i = 0; i<player.getKeyCodes().size(); i++){
            int keyCode = player.getKeyCodes().get(i);
            if(keyCode==KeyEvent.VK_ESCAPE){
                Engine.setPauseTick(true);
                dispose();
            }
            if(keyCode==KeyEvent.VK_W){
                player.moveForward(10);
                player.setAI(false);
            }
            if(keyCode==KeyEvent.VK_S){
                player.moveBackward(10);
                player.setAI(false);
            }
            if(keyCode==KeyEvent.VK_F){
                if(player.getGunPower() - 1 < 0)
                    continue;
                player.setGunPower(player.getGunPower()-1);
                player.fireBullet(player.moveForwardSteps(500));
                charge = false;
            }

            if(keyCode==KeyEvent.VK_G){
                enemies.clear();
                createEnemies();
                player.setHealth(player.getMaxHealth());
            }
            if(keyCode==KeyEvent.VK_A){
                player.setRotation(player.getRotation()-5);
                if(player.getRotation()<=0){
                    player.setRotation(359);
                }
                player.setAI(false);
            }
            if(keyCode==KeyEvent.VK_D){
                player.setRotation(player.getRotation()+5);
                if(player.getRotation()>=360){
                    player.setRotation(0);
                }
                player.setAI(false);
            }
            if(keyCode== KeyEvent.VK_E){
                player.explode();
            }
            if(keyCode==KeyEvent.VK_UP){
                player.setSize(player.getSize()+1);
            }
        }
//        player.getKeyCodes().().forEach((keyCode)->{
//
//        });
        if(charge){
            if(player.getGunPower() + 1 <=player.getGunPowerMax())
                player.setGunPower(player.getGunPower()+1);
        }
        if(random.nextInt(100)<=5){
            //generateFood();
        }
        nextEnemy++;
        if(nextEnemy==100){
          //  createEnemies();
            nextEnemy = 0;
        }
        repaint();
    }
}
