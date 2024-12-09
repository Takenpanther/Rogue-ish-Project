package pkg2droguelike;

/**
 *
 * @author taken
 */

import Entities.Protag;
import Tiles.TileControl;
import javax.swing.JPanel;
import java.awt.*;

public class GameScreen extends JPanel implements Runnable{
    
    //Screen settings
    final int originalTileSize = 25; // tile size : 25x25
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels
    
    int FPS = 60;
    
    TileControl tileM = new TileControl(this);
    Controls key = new Controls();
    public CollisionDetect check = new CollisionDetect(this);
    Thread gameTimeThread;
    public Protag player = new Protag(this,key);
    
    //world settings
    public final int maxWorldCol = 31;
    public final int maxWorldRow = 31;
    public final int worldWid = tileSize * maxWorldCol;
    public final int worldHei = tileSize * maxWorldRow;
    
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