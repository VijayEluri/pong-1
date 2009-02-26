package org.wintrisstech.projects.pong;

import java.awt.Toolkit;
import javax.swing.JFrame;

public class PongGame
{
    public int width = Toolkit.getDefaultToolkit().getScreenSize().width; // Start off with fixed screendimensions and switch to these later.
    public int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    public Painter painter;
    private JFrame playingField;
    private Mouser mouser;

    public static void main(String[] args)
    {
        PongGame game;
        game = new PongGame();
        game.startTheGame();
    }

    public void startTheGame()
    {
        painter = new Painter(); // Make class Painter into a "real" painter object.

        playingField = new JFrame("PngReference1"); //Make class JFrame a "real" JFrame.
        playingField.setVisible(true);
        playingField.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        playingField.add(painter); // Let the JFrame know is has an artist who will paint on the JFrame.

        playingField.setSize(2000, 1000); // Set size after adding painter so that paint() gets called initially.

        mouser = new Mouser();
        mouser.game = this;

        playingField.addMouseMotionListener(mouser); //A little abtruse for the students!
    }
}
