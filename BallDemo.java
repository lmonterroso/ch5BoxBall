import java.awt.Color;
import java.util.Random;
import java.awt.Dimension;
/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Bill Crosbie
 * @version 2015-March-BB
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 * 
 * @author Luis Monterroso
 * @version 2018-October-14
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 800, 600);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    
    /**
     * creates a box and fills it with balls heading in random directions;
     * @param balls the number of balls you want to spawn in the box
     * @param height sets the height of the box in pixels
     * @param length sets the length of the box in pixels 
     */
    public void boxBounce(int balls)
    {
        //Random color is used to create random colors for the balls
        Random color = new Random();
        Random size = new Random();
        Dimension dim = myCanvas.getSize();
        
        //ball1 is used to draw the box, originally I had the walls drawn straight from this method but I implemented it into BoxBall 
        BoxBall ball1 = new BoxBall(16, Color.BLUE, myCanvas, (int)dim.getWidth() - 50, (int)dim.getHeight() - 50);
        
        //The array is used to organize all the balls and make it easy to perform operations on every ball
        BoxBall[] Balls = new BoxBall[balls];
        ball1.drawBox();
        
        // creates a BoxBall for every element in the array, and draws it when it's instatiated. 
        for (int i = 0; i < Balls.length; i++)
        {
            //generates a random color for the ball, limited to 215 to make the balls more visible.
            Color newColor = new Color(color.nextInt(215),color.nextInt(215),color.nextInt(215));
            
            Balls[i] = new BoxBall(size.nextInt(16) + 10, newColor, myCanvas,(int)dim.getWidth() - 50, (int)dim.getHeight() - 50);
            Balls[i].draw();
        }
        boolean finished =  false; 
        while(!finished) { // finished is never true making the simulation run as long as you want
            myCanvas.wait(25);           // small delay
            for (BoxBall ball: Balls) 
            {
                ball.move(); 
            }
        }
        
        
    }
}
