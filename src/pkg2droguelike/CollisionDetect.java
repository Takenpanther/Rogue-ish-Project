package pkg2droguelike;

import Entities.Entity;

/**
 *
 * @author taken
 */

public class CollisionDetect {
    
    GameScreen gs;
    
    public CollisionDetect(GameScreen gs){
        
        this.gs = gs;
    }
    
    public void tileCheck(Entity entity){
        
        int entityLeftWorldX = entity.worldX + entity.hitbox.x;
        int entityRightWorldX = entity.worldX + entity.hitbox.x + entity.hitbox.width;
        int entityTopWorldY = entity.worldY + entity.hitbox.y;
        int entityBottomWorldY = entity.worldY + entity.hitbox.y + entity.hitbox.height;
        
        int entityLeftCol = entityLeftWorldX/gs.tileSize;
        int entityRightCol = entityRightWorldX/gs.tileSize;
        int entityTopRow = entityTopWorldY/gs.tileSize;
        int entityBottomRow = entityBottomWorldY/gs.tileSize;
        
        int tileNum1, tileNum2;
        
        switch(entity.direction) {
            
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gs.tileSize;
                tileNum1 = gs.tileM.mapTileGenerator[entityLeftCol][entityTopRow];
                tileNum2 = gs.tileM.mapTileGenerator[entityRightCol][entityTopRow];
                
                if(gs.tileM.tile[tileNum1].collision == true || gs.tileM.tile[tileNum2].collision == true) {
                    
                    entity.collision = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gs.tileSize;
                tileNum1 = gs.tileM.mapTileGenerator[entityLeftCol][entityBottomRow];
                tileNum2 = gs.tileM.mapTileGenerator[entityRightCol][entityBottomRow];
                
                if(gs.tileM.tile[tileNum1].collision == true || gs.tileM.tile[tileNum2].collision == true) {
                    
                    entity.collision = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gs.tileSize;
                tileNum1 = gs.tileM.mapTileGenerator[entityLeftCol][entityTopRow];
                tileNum2 = gs.tileM.mapTileGenerator[entityLeftCol][entityBottomRow];
                
                if(gs.tileM.tile[tileNum1].collision == true || gs.tileM.tile[tileNum2].collision == true) {
                    
                    entity.collision = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gs.tileSize;
                tileNum1 = gs.tileM.mapTileGenerator[entityRightCol][entityTopRow];
                tileNum2 = gs.tileM.mapTileGenerator[entityRightCol][entityBottomRow];
                
                if(gs.tileM.tile[tileNum1].collision == true || gs.tileM.tile[tileNum2].collision == true) {
                    
                    entity.collision = true;
                }
                break;
        }
    }
}
