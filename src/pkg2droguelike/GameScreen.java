package pkg2droguelike;

/**
 *
 * @author taken
 */

import Entities.Protag;
import Tiles.TileControl;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class GameScreen extends JPanel implements Runnable{
    
    //Screen settings
    final int originalTileSize = 25; // tile size : 25x25
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale; //48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //768 pixels
    final int screenHeight = tileSize * maxScreenRow; //576 pixels
    
    int FPS = 60;
    
    TileControl tileM = new TileControl(this);
    Controls key = new Controls();
    Thread gameTimeThread;
    Protag player = new Protag(this,key);
    
    //test positioning for player
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    /**
     *
     */
    public GameScreen(){
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }
    
    public void startGameTimeThread() {
        
        gameTimeThread = new Thread(this);
        gameTimeThread.start();
    }    
    
    @Override
    public void run() {
        
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        
        while(gameTimeThread != null) {
            
            update();
            
            repaint();
            
            
            
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                
                if(remainingTime < 0) {
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime);
                
                nextDrawTime += drawInterval;
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void update() {
        
        player.update();
        
    }
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        tileM.drawTile(g2);
        
        player.drawPlayer(g2);
        
        g2.dispose();
    }
}