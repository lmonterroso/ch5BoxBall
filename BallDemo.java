import java.awt.Color;
import java.util.Random;
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
        myCanvas = new Canvas("Ball Demo", 600, 500);
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
    public void boxBounce(int balls, int height, int length)
    {
        //Random color is used to create random colors for the balls
        Random color = new Random();
        
        //ball1 is used to draw the box, originally I had the walls drawn straight from this method but I implemented it into BoxBall 
        BoxBall ball1 = new BoxBall(16, Color.BLUE, myCanvas, length, height);
        
        //The array is used to organize all the balls and make it easy to perform operations on every ball
        BoxBall[] Balls = new BoxBall[balls];
        ball1.drawBox();
        
        // creates a BoxBall for every element in the array, and draws it when it's instatiated. 
        for (int i = 0; i < Balls.length; i++)
        {
            //generates a random color for the ball, limited to 215 to make the balls more visible.
            Color newColor = new Color(color.nextInt(215),color.nextInt(215),color.nextInt(215));
            
            Balls[i] = new BoxBall(16, newColor, myCanvas, length, height);
            Balls[i].draw();
        }
        boolean finished =  false; 
        while(!finished) {
            myCanvas.wait(25);           // small delay
            for (BoxBall ball: Balls) 
            {
                ball.move(); 
            }
            if(ball1.getXPosition() >= 550) //finished is always true so the simulation won't end
                finished = true;
        }
        
        
    }
}
