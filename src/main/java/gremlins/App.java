package gremlins;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;
import processing.event.KeyEvent;

import java.util.Random;

import java.io.*;
import java.util.ArrayList;

public class App extends PApplet {

    public static final int WIDTH = 720;
    public static final int HEIGHT = 720;
    public static final int SPRITESIZE = 20;
    public static final int BOTTOMBAR = 60;

    public static final int FPS = 60;

    public static final Random randomGenerator = new Random();

    public String configPath;
    
    public PImage brickwall;
    public PImage stonewall;
    public PImage fireImg;

    public Background bg;
    public Wizard wiz;
    public ArrayList<Gremlin> all_gremlins = new ArrayList<Gremlin>();
    public Projectiles fireball;
    public boolean fireball_exists = false;

    public App() {
        this.configPath = "config.json";
    }

    //Initialise the setting of the window size.
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    //Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
    public void setup() {
        frameRate(FPS);

        // Load images during setup
        this.stonewall = loadImage(this.getClass().getResource("stonewall.png").getPath().replace("%20", " "));
        this.brickwall = loadImage(this.getClass().getResource("brickwall.png").getPath().replace("%20", " "));
        PImage gremlinImg = loadImage(this.getClass().getResource("gremlin.png").getPath().replace("%20", " "));
        this.fireImg = loadImage(this.getClass().getResource("fireball.png").getPath().replace("%20", " "));
        //this.slime = loadImage(this.getClass().getResource("slime.png").getPath().replace("%20", " "));
        PImage wizS_right = loadImage(this.getClass().getResource("wizard1.png").getPath().replace("%20", " "));
        PImage wizS_up = loadImage(this.getClass().getResource("wizard2.png").getPath().replace("%20", " "));
        PImage wizS_down = loadImage(this.getClass().getResource("wizard3.png").getPath().replace("%20", " "));
        PImage wizS_left = loadImage(this.getClass().getResource("wizard0.png").getPath().replace("%20", " "));


        this.bg = new Background("level1.txt", stonewall, brickwall);
        this.wiz = new Wizard(wizS_right, wizS_up, wizS_down, wizS_left);


        for(int j=0; j<33; j++) {                                       
            for(int i=0; i<36; i++) {
                if(bg.readfile()[j][i].equals("G")) {     // not drawing anything, but reading and saving to draw
                    Gremlin g = new Gremlin(i*20, j*20, gremlinImg);
                    this.all_gremlins.add(g);
                } 
            }
        }

        JSONObject conf = loadJSONObject(new File(this.configPath));
    }

    
    //Receive key pressed signal from the keyboard.
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key == 37) {         // left arrow
            wiz.left();
        } else if (key == 38) {  //  up arrow
            wiz.up();
        } else if (key == 39) {  //right
            wiz.right();
        } else if (key == 40) {  //down
            wiz.down();
        }
        
        if (key == 32) {    //space bar
            this.fireball = new Projectiles(this.fireImg, wiz.direction, 4, wiz.x, wiz.y, this.bg.get_map());
            this.fireball_exists = true;
        }
    }

    //Receive key released signal from the keyboard.
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        //if moving right or down, round up
        if(key==39 || key==40){
            wiz.x = (int) Math.ceil(wiz.x/20)*20;
            wiz.y = (int) Math.ceil(wiz.y/20)*20;
        }

        //if moving left or up, round up
        if(key==37 || key==38){
            wiz.x = (int) Math.floor(wiz.x/20)*20;
            wiz.y = (int) Math.floor(wiz.y/20)*20;
        }
        
        wiz.stop();
    }


    //Draw all elements in the game by current frame. 
    public void draw() {
        background(190,153,118);

        bg.draw(this);
        wiz.update_map(bg.get_map());
        wiz.draw(this);
        
        //Gremlin g = all_gremlins.get(0);
        for (Gremlin g : all_gremlins) {
            g.update_map(bg.get_map());
            g.draw(this);
        }


        if (fireball_exists) {
            fireball.draw(this);
            fireball.update_map(bg.get_map());
            for (Gremlin g : all_gremlins) {
                // if the fireball collides, it stops existing
                this.fireball_exists = !fireball.collision(g.get_x(), g.get_y());
            }
        }
    }


    public static void main(String[] args) {
        PApplet.main("gremlins.App");
    }
}
