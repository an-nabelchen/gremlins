package gremlins;

import processing.core.PImage;

public class Projectiles {
    private PImage sprite;
    private int direction;

    private float x;
    private float y;
    private String[][] map;

    private int speed;
    private int x_vel;
    private int y_vel;

    public Projectiles(PImage sprite, int direction, int speed, float x, float y, String[][] map) {
        this.sprite = sprite;
        this.direction = direction;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.map = map;
    }

    public void update_map(String[][] map) {
        this.map = map;
    }

    public void set_vel() {
        if (direction == 1) {
            x_vel = speed;
        } else if (direction == -1) {
            x_vel = -speed;
        } else if (direction == 2) {
            y_vel = -speed;
        } else if (direction == -2) {
            y_vel = speed;
        }
    }

    public boolean collision(float grem_x, float grem_y) {
        if (x%20==0 && y%20==0) {
            int x_idx = (int) x/20;
            int y_idx = (int) y/20;
            if (map[y_idx][x_idx].equals("B") || map[y_idx][x_idx].equals("B")) {
                return true;
            } 
        } 
        else if (grem_x==this.x && grem_y==this.y) {
            return true;
        }

        return false;
    }

    public void draw(App app) {
        set_vel();
        x += x_vel;
        y += y_vel;
        app.image(sprite, x, y);
    }
}
