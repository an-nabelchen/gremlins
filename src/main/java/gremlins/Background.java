package gremlins;
import processing.core.PImage;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;

public class Background {
    private File mapfile;
    private String[][] current_map = new String[33][];
    
    private PImage stonewallSprite;
    private PImage brickwallSprite;

    public Background(String file_name, PImage s, PImage b) {
        this.stonewallSprite = s;
        this.brickwallSprite = b;
        File f = new File(file_name);
        this.mapfile = f;
    }

    public String[][] readfile() {                                      //initial input of file - the emplate map
        Scanner sc;
        try {
            sc = new Scanner(mapfile);
            int i = 0;
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                line = line.replace(' ', 'd');          // replace spaces with d, so array doesn't just have void
                String[] myList = line.split("");                //turn line into array
                current_map[i] = myList;                                //add to current map
                i++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found");
        }
        return current_map;
    }

    public void draw(App app) {
        readfile();
        for(int j=0; j<33; j++) {                                       //i=row=y, j=column=x
            for(int i=0; i<36; i++) {
                if(current_map[j][i].equals("X")) {
                    app.image(stonewallSprite, i*20, j*20);             //thjs method writes x then y
                } else if (current_map[j][i].equals("B")) {
                    app.image(brickwallSprite, i*20, j*20);
                }
            }
        }
    }

    public String[][] get_map() {
        return current_map;
    }
}
