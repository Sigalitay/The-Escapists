
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;

public class Sensor
{

    private int x;
    private int y;
    private int diam;
    private int vx;
    private int vy;
    private int area;
    private Player player;
    //private int type;

    public Sensor(int xCoor, int yCoor, int d, int velx, int vely, int a, Player p)
    {
        x = xCoor + diam / 2;
        y = yCoor - 30;
        diam = d;
        vx = velx;
        vy = vely;
        area = a;
    }
    public void drawSelf(Graphics g)
    {
        g.setColor(Color.red);
        g.fillOval(x, y, diam, diam);
    }

    public boolean senseGuard(Guard g)
    {
        
        if (g.getCurrArea() == area)
        {
            int xCenter = x + getRadius();
            int yCenter = y + getRadius();

            int objXC = g.getCenterX();
            int objYC = g.getCenterY();

            if ((EGraphics.distance(xCenter, objXC, yCenter, objYC) <= 20))
            {
                
                return true;
            }

        }
        return false;
    }

    public boolean senseObject(Object o)
    {
        
        if (o.getAreaIAmIn() == area)
        {
            int xCenter = x + getRadius();
            int yCenter = y + getRadius();

            int objXC = o.getCenterX();
            int objYC = o.getCenterY();

            if ((EGraphics.distance(xCenter, objXC, yCenter, objYC) <= 20))
            {
               
                return true;
            }

        }
        return false;
    }

    

    //accessors
    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getDiam()
    {
        return diam;
    }

    public int getRadius()
    {
        return diam / 2;
    }

    //mutators
    public void setX(int xCoor)
    {
        x = xCoor;
    }

    public void setY(int yCoor)
    {
        y = yCoor;
    }
    
    public void setArea(int a)
    {
        area = a;
    }

    
}
