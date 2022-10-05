package gremlins;

import jogamp.nativewindow.windows.PIXELFORMATDESCRIPTOR;
import processing.core.PImage;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;


public abstract class MovingCharacter {
    protected int direction; 
    protected PImage sprite;

    protected float x;
    protected float y;

    protected int speed;
    protected int x_vel=0;
    protected int y_vel=0;

    protected String[][] current_map; // moving characters to interact with the map

    public MovingCharacter(int x, int y, int dir, int speed) {
        this.x = x;
        this.y = y;
        this.direction = dir;
        this.speed = speed;
    }

    public float get_x() {
        return this.x;
    } public float get_y() {
        return this.y;
    }

    public void left() {
        this.x_vel = -speed;
    } public void down() { //right
        this.y_vel = speed;
    } public void up() {
        this.y_vel = -speed;
    } public void right() {   //down
        this.x_vel = speed;
    } public void stop() {
        this.x_vel = 0;       //no movement
        this.y_vel = 0;
    }

    public void set_sprite(PImage sprite) {
        this.sprite = sprite;
    }

    public void update_map(String[][] map) {
        this.current_map = map;
    }

    public void draw(App app) {
        if (x+x_vel>720 || x+x_vel<0 || y+y_vel>720 || y+y_vel<0) {
            this.stop();
        }
        
        x += x_vel;
        y += y_vel;
        app.image(sprite, x, y);
    }
}

