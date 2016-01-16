

package solarsystem;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;//Needed for graphics
import static javax.swing.JFrame.EXIT_ON_CLOSE;//Needed for graphics

public class SolarSystem extends JFrame{//Needed for graphics

    static int width = 1000;
    static int height = 700;
    static int millisecondsBetweenFrames = 10;
    static int numstars = 1000;//Stars to be painted on screen
    static int[] xstar = new int[numstars];//Array for xvalues of the stars
    static int[] ystar = new int[numstars];//Array for yvalues of the stars
    
    //Fields needed to complete planet created by the user
    static String newName;
    static double farthestDist;
    static double closestDist;
    static int planetRad;//Radius of planet
    static String color;
      
    //Creating all the planets using the Planet Class
    static Planet mercury = new Planet("Mercury",87.5,57.33,width/2-87.5,height/2,4,Color.lightGray);
    static Planet venus = new Planet("Venus",137,135,width/2-137,height/2,6,Color.orange);
    static Planet earth = new Planet("Earth",190,185,width/2-190,height/2,10,Color.blue);
    static Planet mars = new Planet("Mars",260,258.75,width/2-260,height/2,8,Color.red);
    static Planet planetx;
    
    //Creates an array of planets
    static Planet[] planets = new Planet[5];   
   
    //Fills the arrays xstar and ystar with random values within the range of the given width and height
    public void makestarVals() {      
        for (int i = 0; i < numstars; i++) {
            Random rand = new Random();
            xstar[i] = rand.nextInt(width);
            ystar[i] = rand.nextInt(height);           
        }       
    }
    
    //Draws everything on the screen with the use of Graphics
    public void paint(Graphics g) {     
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);      
        g.setColor(Color.yellow);
        g.fillOval(width/2-20, height/2-20, 40, 40); //Draws sun in the centre of the screen
        
        //Creates the stars on the screen
        for (int i = 0; i < numstars; i++) {   
            g.setColor(Color.white);
            g.drawLine(xstar[i], ystar[i], xstar[i], ystar[i]);                     
        }
        
        //Draws the ellipses tracing the orbit path of the planets
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < planets.length; i++) {
            g.drawOval(500-(int)planets[i].maxDist,350-(int)planets[i].minDist,(int) planets[i].maxDist*2, (int)planets[i].minDist*2);           
        }
        
        //Draws the planets on the screen with there names near them
        for (int i = 0; i < 5; i++) {
            g.setColor(planets[i].color);
            g.fillOval((int)planets[i].xpos-planets[i].radius,(int)planets[i].ypos-planets[i].radius,planets[i].diameter,planets[i].diameter); 
            g.setColor(Color.white);
            g.drawString(planets[i].name,(int) planets[i].newx+ planets[i].radius/2+2, (int) planets[i].ypos-planets[i].radius/2);     
        } 
        
        //Draws the Velocity chart on the side of the screen displaying the velocities of each planet in KM/second
        int[] yvals = {200,300,400,500,600,700};//Creats yvalues for the list of Planets and their velocities
        g.setFont(new Font("Serif", Font.PLAIN, 20));//Creates font for title "Velocity of Planets"
        g.setColor(Color.green);
        g.drawString("VELOCITY OF PLANETS", 750, 130);//Draws the title on screen
        g.setColor(Color.cyan);
        for (int i = 0; i < planets.length; i++) {
            g.setFont(new Font("Serif", Font.PLAIN, 13));//Creates font for the list of Planets and their velocities
            //Draws on screen the list of Planets with their velocites. The velocities are reduced to KM per second and only two decimal places are drawn
            g.drawString(planets[i].name+"'s Velocity is " + new DecimalFormat("##.##").format(planets[i].vel/10000)+ "KM/sec", 800, yvals[i]);
        }
        
    }
     
    //Sets up the window for the items to be drawn in
    public void initializeWindow() {     
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setBackground(Color.black);        
        setVisible(true);
    }
    
    //Method for pausing between frames when new ones are being drawn
    public static void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
        }
    }
    
    //Method for getting the color from the users input color 
    public Color userCol(String col) {
        if (col.equalsIgnoreCase("magenta")) {
            return Color.magenta;
        }
        else if (col.equalsIgnoreCase("cyan")) {
            return Color.cyan;
        }
        else if (col.equalsIgnoreCase("pink")) {
            return Color.pink;
        }
        else if (col.equalsIgnoreCase("yellow")) {
            return Color.yellow;
        }
        else if (col.equalsIgnoreCase("gray")) {
            return Color.gray;
        }
        else if (col.equalsIgnoreCase("red")) {
            return Color.red;
        }
        else if (col.equalsIgnoreCase("green")) {
            return Color.green;
        }
        else if (col.equalsIgnoreCase("blue")) {
            return Color.blue;
        }
        else if (col.equalsIgnoreCase("orange")) {
            return Color.orange;
        }
        else {
            return Color.white;
        }
    }



    public static void main(String[] args) {
        SolarSystem s = new SolarSystem();       
        s.makestarVals();
        
        //Intro to the simulation
        System.out.println("Hello and welcome to ORBIT SIMULATOR!" + "\n" + "Create your Planet below and watch it come to life in orbit. "  );
        System.out.println("The Planets Mercury, Venus, Earth and Mars have already been created for you." + "\n" + "Enjoy!" + "\n");
        
        //List of questions asked to create users Planet
        Scanner r = new Scanner(System.in);
        System.out.println("What do you want to call your Planet?");
        newName = r.next();//Sets name for new Planet to user input
        System.out.println("What is the farthest distance your Planet will be from the sun? (e.g: Earth is 190 units)");
        farthestDist = r.nextDouble();//Sets furthestDist for new Planet to user input
        System.out.println("What is the closest distance your Planet will be from the sun? (e.g: Earth is 185 units)");
        closestDist = r.nextDouble();//Sets closestDist for new Planet to user input
        System.out.println("What is the radius of your Planet? (e.g: Earth is 10 units)");
        planetRad = r.nextInt();//Sets planetRad for new Planet to user input
        System.out.println("What colour would you like your planet to be? (Yellow, Gray, Orange, Blue and Red are in use, but feel free to use them as well)");
        color = r.next();//Sets color for new Planet to user input     
        
        //Creates the new Planet based on what the user has input
        planetx = new Planet(newName,farthestDist, closestDist, width/2-farthestDist, height/2, planetRad, s.userCol(color));
        
        //Fills array of Planets with these given Planets that have already been made
        planets[0] = mercury;
        planets[1] = venus;
        planets[2] = earth;
        planets[3] = mars;
        planets[4] = planetx;
        
        //Sets up window so planets can be drawn
        s.initializeWindow();
        
        while (true) {//Goes on until user closes screen
            for (int i = 0; i < planets.length; i++) {//For every planet
                planets[i].updatePosition();
                planets[i].calculateEccent();
                planets[i].calculateVel();
                s.repaint();//Paints everything on screen for every new frame
                s.sleep(millisecondsBetweenFrames);          
            }      
        }
        
    }
    
}
