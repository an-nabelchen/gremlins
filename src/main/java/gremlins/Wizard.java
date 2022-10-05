package gremlins;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;
import processing.core.PImage;

public class Wizard extends MovingCharacter{
    private PImage wiz_up;
    private PImage wiz_down;
    private PImage wiz_right;
    private PImage wiz_left;

    private int lives;

    public Wizard(PImage ws1, PImage ws2, PImage ws3, PImage ws0) {
        super(40,20, 1, 2);
        this.wiz_right = ws1;
        this.wiz_up = ws2;
        this.wiz_down = ws3;
        this.wiz_left = ws0;
        set_sprite(ws1);   // starting facing right
    }

    
    public void left() {
        // changeDirection('l');
        set_sprite(wiz_left);
        this.direction = -1;
        super.left();
    }
    public void right() {
        // changeDirection('r');
        set_sprite(wiz_right);
        this.direction = 1;
        super.right();
    }
    public void up() {
        // changeDirection('u');
        set_sprite(wiz_up);
        this.direction = 2;
        super.up();
    }
    public void down() {
        // changeDirection('d');
        set_sprite(wiz_down);
        this.direction = -2;
        super.down();
    }

    public void draw(App app) {
        // stop moving if the next block is a brick or stone
        int future_y = (int) (this.y+(10*y_vel))/20;            // this.y + 20, but the 20 is relative to the direction we're heading in, otherwise it just stops all movement in all directions
        int future_x = (int) (this.x+(10*x_vel))/20;            // ie. if going right, then x_vel=2. Therefore, check for index x+20
        if (current_map[future_y][future_x].equals("B") || current_map[future_y][future_x].equals("X")) {
            this.stop();
        }
        super.draw(app);
    }
}
