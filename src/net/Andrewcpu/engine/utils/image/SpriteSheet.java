package net.Andrewcpu.engine.utils.image;

import net.Andrewcpu.engine.Engine;
import net.Andrewcpu.engine.utils.Log;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * Created by stein on 5/14/2016.
 */
public class SpriteSheet {
    public static BufferedImage[] getSprites(BufferedImage image, int rows, int columns){
        Log.d((image==null) ? "Sprite sheet is null" : "Sprite sheet found");
        int width = image.getWidth() / rows;
        int height = image.getHeight() / columns;
        BufferedImage[] sprites = new BufferedImage[rows * columns];
        int sprt = 0;
        for(int i = 0; i< columns; i++){
            for (int j = 0; j < rows; j++)
            {
                sprites[sprt] = image.getSubimage(j * width, i * height, width, height);
                sprt++;
            }
        }
        return sprites;
    }
    public static BufferedImage loadImage(URL path){
        try{
            return ImageIO.read((path));
        }catch(Exception ex){
            Log.d(ex.getLocalizedMessage());
        }
        return null;
    }
}
