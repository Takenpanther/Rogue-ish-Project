package pkg2droguelike;

/**
 *
 * @author taken
 */

import javax.swing.JFrame;
public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Curse of Apple Woods");
        
        GameScreen gamePanel = new GameScreen();
        window.add(gamePanel);
        
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamePanel.startGameTimeThread();
    }
    
}
