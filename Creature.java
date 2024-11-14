package roguelike.Entities;

/*
 *
 *
 * Evan Weber
 */

import java.awt.Color;

public class Creature extends Entity{
    
    public Creature(String name, char glyph, Color color, int xPos, int yPos){
        super(name, glyph, color, xPos, yPos);
    }
    
    public void move(int dx, int dy){
        x += dx;
            y += dy;
    }
}
