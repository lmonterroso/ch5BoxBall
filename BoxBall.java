import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
import java.awt.Dimension;

/**
 * Write a description of class BoxBall here.
 *
 * @author Luis Monterroso
 * @version 10/15/18 Extra Credit
 */
public class BoxBall
{
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;     
    private Canvas canvas;   
    private Random rand = new Random();
    private Dimension dim = new Dimension();
    
    /*
     * The yvelocity is calculated with a random number between 1 and 6,
     * while the xvelocity is calculated by subtracting yvelocity from 7,
     * this ensures all the balls move at the same speed but in random directions.
     */
    private int yvelocity = rand.nextInt(7) + 1;
    private int xvelocity = 7 - yvelocity;
    private int ground;
    private int ceiling;
    private int leftWall;
    private int rightWall;

    /**
     * Constructor for objects of class BoxBall
     * @param ballDiameter  the diameter of the ball in pixels;
     * @param ballColor  the color of the ball
     * @param drawingCanvas  the canvas to draw this ball on
     * @param length the length of your box in pixels
     * @param height the height of your box in pixels
     */
    public BoxBall(int ballDiameter, Color ballColor,
                    Canvas drawingCanvas, int length, int height)
    {
        //the four walls are calculated by taking the center of the canvas and adding or subtracting half the length or height
        canvas = drawingCanvas;
        dim = drawingCanvas.getSize();
        ground = (int)dim.getHeight() - 50;
        ceiling = 50;
        leftWall = 50;
        rightWall =(int)dim.getWidth() - 50;
        
        diameter = ballDiameter;
        
        // x and y positions are calculated randomly by taking the values between the walls minus
        // the diameter to prevent them from spawning on the walls of the box.
        xPosition = rand.nextInt((rightWall - leftWall) - diameter) + leftWall;
        yPosition = rand.nextInt(ground - ceiling - diameter) + ceiling;
        color = ballColor;
    }
    
    /**
     * Constructor for objects of class BoxBall
     * @param ballDiameter  the diameter of the ball in pixels;
     * @param ballColor  the color of the ball
     * @param drawingCanvas  the canvas to draw this ball on
     * @param length the length of your box in pixels
     * @param height the height of your box in pixels
     */
    public BoxBall(int xPos, int yPos, int ballDiameter, Color ballColor,
                    Canvas drawingCanvas, int length, int height)
    {
        //the four walls are calculated by taking the center of the canvas and adding or subtracting half the length or height
        canvas = drawingCanvas;
        dim = drawingCanvas.getSize();
        ground = (int)dim.getHeight() - 50;
        ceiling = 50;
        leftWall = 50;
        rightWall =(int)dim.getWidth() - 50;
        
        diameter = ballDiameter;
        
        // x and y positions are calculated randomly by taking the values between the walls minus
        // the diameter to prevent them from spawning on the walls of the box.
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
    }
    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }
    
    /**
     * Draws the walls of the box.
     */
    public void drawBox ()
    {
        canvas.drawLine(leftWall,ground,rightWall,ground);
        canvas.drawLine(leftWall, ceiling, rightWall, ceiling);
        canvas.drawLine(leftWall,ground,leftWall,ceiling);
        canvas.drawLine(rightWall,ground,rightWall,ceiling);
    }
    
    /**
     * draws the circle at its x and y position;
     */
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }
    
      /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }   
    
    /**
     * moves the circle and redraws the circle at its new position, if the circle hits
     * a wall it will rebound in the opposite direction.
     */
    public void move()
    {
        //velocity controls how fast the circle moves
        erase();
        xPosition += xvelocity;
        yPosition += yvelocity;
        
        //if the ball hits a wall flip either the x or y velocity
        if(yPosition >= (ground - diameter))  {
            yPosition = (int)(ground - diameter);
            yvelocity = yvelocity * -1; 
                
        } 
        if(yPosition <= (ceiling + 1))  {
            yPosition = (int)(ceiling + 1);
            yvelocity = yvelocity * -1; 
                
        }   
        if (xPosition >= (rightWall - diameter)) {
            xPosition = (int)(rightWall - diameter);
            xvelocity *= -1;
        }
        if (xPosition <= (leftWall + 1)) {
            xPosition = (int)(leftWall + 1);
            xvelocity *= -1;
        }
        draw();
    }
    public void collision(BoxBall collision)
    {
        if (this != collision)
        {  
            int x = (this.xPosition + this.diameter/2 - collision.xPosition + collision.diameter/2);
            int y = (this.yPosition + this.diameter/2 - collision.yPosition + collision.diameter/2);
            int hypotenuse = (int)Math.sqrt(x*x + y*y);
        
        
            if ((this.diameter/2 + collision.diameter/2) >= hypotenuse){
                this.xvelocity *= -1;
                this.yvelocity *= -1;
                this.move();
                
            }
        }
    }
}
