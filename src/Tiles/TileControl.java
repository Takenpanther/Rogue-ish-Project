 package Tiles;

import Entities.Protag;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import javax.imageio.ImageIO;
import pkg2droguelike.GameScreen;
import pkg2droguelike.Controls;
        
/**
 *
 * @author taken
 */

public class TileControl{
    Controls key;
    GameScreen gs;
    public TileData[] tile;
    public int mapTileGenerator[][];
    
    public TileControl(GameScreen gs) {
        
        this.gs = gs;
        this.key = key;
        tile = new TileData[10];
        
        mapTileGenerator = new int [gs.maxWorldCol][gs.maxWorldRow];
        
        File map = getRandomDungeonMap();
        
        File dunEnd = new File("./DungeonMaps/DungeonEnd.txt");
        getTileSprite();
        drawTileMap(map);
    }
    
    public File getRandomDungeonMap(){
        
        Random rand = new Random();
        
        String[] mapDesign = {"./DungeonMaps/DungeonMap1.txt", 
            "./DungeonMaps/DungeonMap2.txt", "./DungeonMaps/DungeonMap3.txt"};
        int rMapSelector = rand.nextInt(3);
        
        Protag player = new Protag(gs, key);
        
        switch(rMapSelector){
            case 0: player.setStart(27, 23);
                    break;
            case 1: player.setStart(7, 4);
                    break;
            case 2: player.setStart(18, 4);
            
        }
        File dungeonMap = new File(mapDesign[rMapSelector]);
        return dungeonMap;
    }
    
    public void getTileSprite() {
        
        try {
            
            File grassTile = new File("./TileSprites/GrassTile.png");
            tile[0] = new TileData();
            tile[0].image = ImageIO.read(grassTile);
            
            File treeTile = new File("./TileSprites/TreeTile.png");
            tile[1] = new TileData();
            tile[1].image = ImageIO.read(treeTile);
            tile[1].collision = true;
            
            File waterTile = new File("./TileSprites/WaterTile.png");
            tile[2] = new TileData();
            tile[2].image = ImageIO.read(waterTile);
            tile[2].collision = true;
            
            File stairTile = new File("./TileSprites/StairTile.png");
            tile[3] = new TileData();
            tile[3].image = ImageIO.read(stairTile);
            
            File spawnTile = new File("./TileSprites/GrassTile.png");
            tile[4] = new TileData();
            tile[4].image = ImageIO.read(spawnTile);
            
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void drawTileMap(File MapData) {
        
        try{
            InputStream is = new FileInputStream(MapData);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;
            
            while(col < gs.maxWorldCol && row < gs.maxWorldRow) {
                
                String line = br.readLine();
                
                while(col < gs.maxWorldCol){
                    String num[] = line.split(" ");
                    
                    int numbers = Integer.parseInt(num[col]);
                    
                    mapTileGenerator[col][row] = numbers;
                    col ++;
                }
                if (col == gs.maxWorldCol){
                    col = 0;
                    row ++;
                }
            }    
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void drawTile(Graphics2D g2) {
        
        int worldCol = 0;
        int worldRow = 0;
        
        while(worldCol < gs.maxWorldCol && worldRow < gs.maxWorldRow){
            
            int tileNum = mapTileGenerator[worldCol][worldRow];
            
            int worldX = worldCol * gs.tileSize;
            int worldY = worldRow * gs.tileSize;
            int screenX = worldX - gs.player.worldX + gs.player.screenX;
            int screenY = worldY - gs.player.worldY + gs.player.screenY;
            
            if(worldX + gs.tileSize > gs.player.worldX - gs.player.screenX &&
               worldX - gs.tileSize < gs.player.worldX + gs.player.screenX &&
               worldY + gs.tileSize > gs.player.worldY - gs.player.screenY &&
               worldY - gs.tileSize < gs.player.worldY + gs.player.screenY){
                
                g2.drawImage(tile[tileNum].image, screenX, screenY, gs.tileSize, gs.tileSize, null);
                    
            }
            
            worldCol++;
            
            if(worldCol == gs.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}