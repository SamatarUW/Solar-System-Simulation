

package solarsystem;
import java.awt.*;

public class Planet {
    //Fields for each Planet to have
    String name;
    int radius,diameter;
    double maxDist;
    double minDist;
    double xpos,ypos;
    Color color;
    double eccentrity;  
    double vel;
    double newx;
    double newy;

    public Planet( String name, double maxD, double minD, double x, double y, int r, Color c) {//Constructs the Planet object
        this.name = name;
        this.radius = r;
        this.minDist = minD;
        this.maxDist = maxD;
        this.diameter = 2*r;
        this.color = c; 
        this.newx = 500-this.maxDist;
    }
    
    //Sets position of the Planet
    public void setPosition(double x, double y) { 
        this.xpos = x;
        this.ypos = y;
    }
    
    //Sets the velocity for the Planet
    public void setVelocity(double vel) {
        this.vel = vel;
        }
    
    //Sets the eccentricity for the Planet
    public void setEccent(double eccent) {
        this.eccentrity = eccent;
        }
    
    //Changes position of the Planet for the next frame
    public void updatePosition() {        
        int sign;//Positve or Negative based on if the planets are orbiting below the sun or above it
        
        if ( this.ypos >= 350) {//If the Planet's yposition is below the yposition of the sun
            this.newx+= this.vel/80000;//Increase xposition based on calculated velocity (divides by 80000 in order to scale down to usable numbers in the simulation
            sign = 1;//Set sign to positive
        } else {
            this.newx-=this.vel/80000;//Decrease xposition based on calculated velocity 
            sign = -1;//Set sign to negative
        }
        
        if (this.newx-this.vel/80000 < 500-this.maxDist) {//If the xposition of the Planet is ever less than the farthest distance the Planet should be from the sun on the left side
            this.newx = 500-this.maxDist;//Resets newx to the farthest distance it should be from the sun on left side
            this.newx+= this.vel/80000000;//Increases the velocity based on calculated velocity (divides by large number to slow it down because Planets tend to travel fast on the sides of ellipses)
            sign = 1;
        }
        
        if (this.newx+this.vel/80000 > 500+this.maxDist) {//If the xposition of the Planet is ever greater than the farthest distance the Planet should be from the sun on the right side
            this.newx = 500+this.maxDist;//Resets newx to the farthest distance it should be from the sun on right side
            this.newx-= this.vel/80000000;//Decreases the velocity based on calculated velocity 
            sign = -1;//Sets sign to negative
        }
        
        //Sets newy to the calculated yposition using the formula of find yval on an ellipse
        newy =  this.minDist * sign * Math.sqrt(1 - ((500-newx)*(500-newx))/((this.maxDist*this.maxDist)));
        this.setPosition(this.newx, newy+350);//Sets position of planet to newx and newy (add 350 so it is relative to the sun) 
    }
    
    //Calculates the velocity of a Planet
    public void calculateVel() {
        double vel = Math.sqrt(((6.67*Math.pow(10, -11)*(0.1989*Math.pow(10,30)))/((this.getDist(500,350, newx,newy+350)*800000)*(Math.PI/3)))*(1+this.eccentrity));
        this.setVelocity(vel);//Sets the calculated velocity to the velocity of the Plaent
    }
    
    //Calculates the eccentricity of the Planet
    public void calculateEccent() {
        double eccent = (this.maxDist-this.minDist)/(this.maxDist+this.minDist);
        this.setEccent(eccent);//Sets the caclulated eccentricity to the eccentricity of the Planet
    }
    
    //Calculate the distance betwwen two points. This method gets used in the calculateVel method
    public double getDist(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    }
 }
    
