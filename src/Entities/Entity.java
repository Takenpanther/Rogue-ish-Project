
package Entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author taken
 */

public class Entity {
    
    public int worldX, worldY;
    public int speed;
    
    public BufferedImage up1, up2, up3, down1, down2, down3, 
            right1, right2, right3, left1, left2, left3;
    
    public String direction;
    
    public int spriteCount = 0;
    public int spriteNum = 1;
    
    public Rectangle hitbox;
    public boolean collision = false;
}
