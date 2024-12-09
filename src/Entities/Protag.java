package Entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import pkg2droguelike.Controls;
import pkg2droguelike.GameScreen;

/**
 *
 * @author taken
 */

public class Protag extends Entity {
    
     GameScreen gs;
     Controls key;
     
     public final int screenX;
     public final int screenY;
     
     public Protag(GameScreen gs, Controls key) {
         
         this.gs = gs;
         this.key = key;
         
         screenX = gs.screenWidth/2 - gs.tileSize/2;
         screenY = gs.screenHeight/2 - gs.tileSize/2;
         
         hitbox = new Rectangle(8, 16, 32, 32);
         
         setStart(6,9);
         getProtagImage();
         
     }
     public void setStart(int x, int y){
         
         worldX = gs.tileSize * x;
         worldY = gs.tileSize * y;
         speed = 4;
         direction = "down";
     }
     public void getProtagImage() {
         
        try{
            File pf1 = new File("./Protag/PlayerBackIdle.png");
            File pf2 = new File("./Protag/PlayerBackLStep.png");
            File pf3 = new File("./Protag/PlayerBackRStep.png");
            up1 = ImageIO.read(pf1);
            up2 = ImageIO.read(pf2);
            up3 = ImageIO.read(pf3);
            
            File pf4 = new File("./Protag/PlayerFrontIdle.png");
            File pf5 = new File("./Protag/PlayerFrontLeftStep.png");
            File pf6 = new File("./Protag/PlayerFrontRightStep.png");
            down1 = ImageIO.read(pf4);
            down2 = ImageIO.read(pf5);
            down3 = ImageIO.read(pf6);
            
            File pf7 = new File("./Protag/PlayerRightIdle.png");
            File pf8 = new File("./Protag/PlayerRightLStep.png");
            File pf9 = new File("./Protag/PlayerRightRStep.png");
            right1 = ImageIO.read(pf7);
            right2 = ImageIO.read(pf8);
            right3 = ImageIO.read(pf9);
            
            File pf10 = new File("./Protag/PlayerLeftIdle.png");
            File pf11 = new File("./Protag/PlayerLeftLStep.png");
            File pf12 = new File("./Protag/PlayerLeftRStep.png");
            left1 = ImageIO.read(pf10);
            left2 = ImageIO.read(pf11);
            left3 = ImageIO.read(pf12);
            
        }
        catch(IOException e) {
            e.printStackTrace();
        }
     }
     public void update() {
        
        if(key.upPressed == true || key.downPressed == true || 
                key.leftPressed == true || key.rightPressed == true) {
            
            if(key.upPressed == true){
                direction = "up";
            }
            else if (key.downPressed == true) {
                direction = "down";
            }
            else if (key.leftPressed == true) {
                direction = "left";
            }
            else if (key.rightPressed == true) {
                direction = "right";
            }
            
            collision = false;
            gs.check.tileCheck(this);
            
            if(collision == false){
                
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            spriteCount ++;
            if(spriteCount > 10) {
               if(spriteNum == 1){
                  spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCount = 0;
            }
        }
        
     
    }
        
     public void drawPlayer(Graphics2D g2){
         
        BufferedImage image = null;
        
        switch(direction) {
            case "up":
                if(spriteNum == 0){
                    image = up1;
                }
                if(spriteNum == 1){
                    image = up2;
                }
                if(spriteNum == 2){
                    image = up3;
                }
                break;
            case "down":
                if(spriteNum == 0){
                    image = down1;
                }
                if(spriteNum == 1){
                    image = down2;
                }
                if(spriteNum == 2){
                    image = down3;
                }
                break;
            case "left":
                if(spriteNum == 0){
                    image = left1;
                }
                if(spriteNum == 1){
                    image = left2;
                }
                if(spriteNum == 2){
                    image = left3;
                }
                break;
            case "right":
                if(spriteNum == 0){
                    image = right1;
                }
                if(spriteNum == 1){
                    image = right2;
                }
                if(spriteNum == 2){
                    image = right3;
                }
                break;
        }
        
        g2.drawImage(image, screenX, screenY, gs.tileSize, gs.tileSize, null);
     }
}
