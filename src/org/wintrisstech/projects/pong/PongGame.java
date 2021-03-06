package org.wintrisstech.projects.pong;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class PongGame implements ActionListener, MouseMotionListener
{
    public Painter painter;
    private JFrame playingField;
    public boolean gameOver = false;
    public Paddle paddle;
    public Ball ball;
    public int score = 0;
    public URL soundAddress = getClass().getResource("fire.wav"); // Find the address of the sound file.
    public AudioClip boing = Applet.newAudioClip(soundAddress); // Convert the sound file to an AudioClip that Java can use.

    public void startTheGame()
    {
        ball = new Ball();
        ball.xSpeed = Integer.parseInt(JOptionPane.showInputDialog("Ball Speed?"));
        ball.ySpeed = ball.xSpeed;

        paddle = new Paddle();

        painter = new Painter(); // Make class Painter into a "real" painter object.
        painter.game = this;

        playingField = new JFrame("PngReference1"); //Make class JFrame a "real" JFrame.
        playingField.setVisible(true);
        playingField.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        playingField.add(painter); // Let the JFrame know is has an artist who will paint on the JFrame.

        playingField.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize()); 
        playingField.pack(); // Pack after adding painter so that painter gets its size.
        
        painter.addMouseMotionListener(this); //A little abstruse for the students!

        Timer updateTimer = new Timer(20, this);
        updateTimer.start();
    }

    public void actionPerformed(ActionEvent e)
    {
        ball.x = ball.x + ball.xSpeed; // The way to explain what is happening.
        ball.y += ball.ySpeed; // The way the "Big Boys" do it.

        if ( ball.x + ball.diameter > painter.getWidth() ) // These are good brain burners for the kids.  They can usually figure it out.  Explain why it can't be 1500 if the screen width is 1500 because of ball bounding box.
        {
            ball.xSpeed = -ball.xSpeed;
        }

        if ( ball.y + ball.diameter > painter.getHeight() )
        {
            ball.ySpeed = -ball.ySpeed;
        }

        if (ball.y < 0)
        {
            ball.ySpeed = -ball.ySpeed;
        }

        if (ball.x + ball.diameter < 0)
        {
            gameOver = true;
        }

        if (ball.x + ball.diameter > 0 && ball.x < paddle.x + paddle.width && ball.y < paddle.y + paddle.height && ball.y + ball.diameter > paddle.y)
        {
            ball.xSpeed = -ball.xSpeed;
            score += 1;
            ball.x += 5; // To keep the ball from sticking to the paddle
            boing.play();
        }

        painter.repaint();
    }

    public void mouseDragged(MouseEvent arg0)
    {
        //Do nothing.
    }

    public void mouseMoved(MouseEvent mousePosition)
    {
        paddle.y = mousePosition.getY() - paddle.height/2;
        painter.repaint();
    }
}
