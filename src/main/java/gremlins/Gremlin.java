package gremlins;

import processing.core.PImage;
import java.util.Random;

public class Gremlin extends MovingCharacter{
    public Gremlin(int x, int y, PImage gremSprite) {
        super(x, y, 1, 1);
        set_sprite(gremSprite);
    }
    
    public void draw(App app) {
        if(this.direction==1) {
            this.right();
        } else if (this.direction==2) {
            this.up();
        } else if (this.direction==-1) {
            this.left();
        } else if (this.direction==-2) {
            this.down();
        }

        //System.out.println(this.y);
        if (x+x_vel>680 || x+x_vel<0 || y+y_vel>700 || y+y_vel<0) {
            this.stop();
        }

        //if the 
        if (this.x%20==0 && this.y%20==0) {
            int future_y = (int) Math.ceil((this.y+(20*y_vel))/20);            // this.y + 20, but the 20 is relative to the direction we're heading in, otherwise it just stops all movement in all directions
            int future_x = (int) Math.ceil((this.x+(20*x_vel))/20);     // ie. if going right, then x_vel=2. Therefore, check for index x+20

            //
            if (this.current_map[future_y][future_x].equals("X") || this.current_map[future_y][future_x].equals("B")) {
                this.stop();
                Random random = new Random();
                int temp = random.nextInt(3 - -2) + -2;
                while (Math.abs(temp)==Math.abs(this.direction) || temp==0) {
                    temp = random.nextInt(3 - -2) + -2;
                }
                this.direction = temp;
            }
        }

        super.draw(app);
    }
}
