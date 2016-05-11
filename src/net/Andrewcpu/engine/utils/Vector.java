package net.Andrewcpu.engine.utils;

/**
 * Created by stein on 5/10/2016.
 */
public class Vector {
    private double x, y;

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Vector(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public void equalize(){
        double run = getX();
        double rise = getY();
        boolean neg = (run<0);
        double nRise = rise/run;

        double nRun = run/run;
        nRun = (neg) ? -nRun : nRun;

        setX(nRun);
        setY(nRise);

        System.out.println(this.toString());
    }
    @Override
    public String toString(){
        return getX() + ", " + getY();
    }
}
