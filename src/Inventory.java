
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Inventory
{

    //methods
    private int x;
    private int y;
    private int type;
    private Items item;
   

    public Inventory(int xCoor, int yCoor, boolean isObj)
    {
        x = xCoor;
        y = yCoor;
        if (isObj)
        {
            double chance =(Math.random());
            if(chance<=.001)
                type = 3;
            else if(chance<=.05)
                type = 2;
            else if(chance<=.4)
                type = 1;
            else if(chance<=.6)
                type = 4;
            else
                type = 0;
        }
        else
        {
            type = 0;
        }
        item = new Items(type);

        //make this into an array of theThings and depending on the drawSelf method< setImages the theThing is chosen.
    }

    public void drawSelf(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        item.setImages();
        g2d.drawImage(item.getImg(), x, y, 50, 50, null);//125

    }
    
    

    public void takeSomething(int thing)
    {
        item.setType(thing);
        type = 0;
    }

    //accesssors
    public int getXCoor()
    {
        return x;
    }

    public int getYCoor()
    {
        return y;
    }

    public void setXCoor(int xCoor)
    {
        x = xCoor;
    }

    public void setXYoor(int yCoor)
    {
        y = yCoor;
    }

    public int getItemType()
    {
        return item.getType();
    }
    public Items getItem()
    {
        return item;
    }

    public void setItemType(int o)
    {
        item.setType(o);
    }
}
