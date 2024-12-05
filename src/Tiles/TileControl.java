package Tiles;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import pkg2droguelike.GameScreen;

/**
 *
 * @author taken
 */

public class TileControl {
    
    GameScreen gs;
    TileData[] tile;
    
    public TileControl(GameScreen gs) {
        
        this.gs = gs;
        
        tile = new TileData[10];
        
        getTileSprite();
    }
    
    public void getTileSprite() {
        
        try {
            
            File grassTile = new File("./TileSprites/GrassTile.png");
            tile[0] = new TileData();
            tile[0].image = ImageIO.read(grassTile);
            
            File treeTile = new File("./TileSprites/TreeTile.png");
            tile[1] = new TileData();
            tile[1].image = ImageIO.read(treeTile);
            
            File waterTile = new File("./TileSprites/WaterTile.png");
            tile[2] = new TileData();
            tile[2].image = ImageIO.read(waterTile);
            
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void drawTile(Graphics2D g2) {
        
        g2.drawImage(tile[0].image, 0, 0, gs.tileSize, gs.tileSize, null);
    }
}