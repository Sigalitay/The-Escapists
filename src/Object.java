
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Object  //table
{

    private int x;
    private int y;
    private boolean focusedObj;
    private boolean canPickUp;
    private int areaIAmIn;
    private ImageIcon tableimage;
    private Image table;
    private ImageIcon insideImage;
    private Image inside;
    private int diam;
    private Inventory invenArray[];
    private boolean invenOpen;

    private boolean destroy;
    private boolean storageScreen;

    public Object()
    {
        //filler
    }

    public Object(int xCoor, int yCoor, boolean pickup, int a)
    {
        destroy = false;
        x = xCoor;
        y = yCoor;
        focusedObj = false;

        canPickUp = pickup;
        areaIAmIn = a;
        diam = EGraphics.magicNumber;
        storageScreen = false;
        invenOpen = false;

        setImages("table.png");
        setImageV2("insideDrawer.png");

        invenArray = new Inventory[5];//+2
        invenArray[0] = new Inventory(80, 210, true);
        invenArray[1] = new Inventory(210, 210, true);
        invenArray[2] = new Inventory(340, 210, true);
        invenArray[3] = new Inventory(470, 210, true);
        invenArray[4] = new Inventory(275, 40, false);

    }

    //moving
    public boolean moving(int px, int py)
    {
        if (getPickUp())
        {
            x = px;
            y = py;
            return true;
        }
        else
            return false;
    }

    //drawing self based on area
    public void drawSelf(Graphics g, int area)
    {
        if (!destroy)
        {
            Graphics2D g2d = (Graphics2D) g;
            if (areaIAmIn == area)
            {
                g2d.drawImage(table, x, y, diam, diam, null);
            }
        }
    }

    public void drawInsideSelf(Graphics g, int area, Player p)
    {
        Graphics2D g2d = (Graphics2D) g;
        if (areaIAmIn == area)
        {
            g2d.drawImage(inside, 0, 0, EGraphics.width, EGraphics.height, null);
            for (int i = 0; i < invenArray.length; i++)
            {
                getItemFromPlayer(p);
                invenArray[i].drawSelf(g);
            }
        }
    }

    public void setImageV2(String str)
    {
        insideImage = new ImageIcon(EGraphics.class.getResource(str));
        inside = insideImage.getImage();
    }

    public void getItemFromPlayer(Player p)
    {
        if (invenOpen)
            invenArray[4].setItemType(p.getInven().getItemType());
        else
            invenArray[4].setItemType(0);
    }

    public boolean checkContra()
    {
        if (!destroy)
        {
            for (int i = 0; i < invenArray.length; i++)
            {
                if (invenArray[i].getItem().getContra())
                {
                    
                    return true;
                }
            }
        }
        return false;
    }

    public void interact(Player p)
    {
        if (!destroy)
        {
            if (!storageScreen)
            {
                storageScreen = true;
                invenOpen = true;

            }
            else
            {
                storageScreen = false;
                invenOpen = false;
            }
        }
    }
    

    public boolean stopIt(Player p, Object curr)
    {
        int nextXCenter = getCenterX();
        int nextYCenter = getCenterX();

        int objXC = curr.getCenterX();
        int objYC = curr.getCenterY();

       
        if (EGraphics.distance(nextXCenter, objXC, nextYCenter, objYC) <= getRadius() + curr.getRadius())
        {
            
            return true;
        }
        return false;
    }

    //accessors
    public boolean getIfFocused()
    {
        return focusedObj;
    }
    public int whatType()
    {
        return 1;
    }

    public void setIfFocused(boolean o)
    {
        focusedObj = o;
    }

    public void openStorage()
    {
        storageScreen = true;
    }

    public void closeStorage()
    {
        storageScreen = false;
    }

    public boolean getStorage()
    {
        return storageScreen;
    }

    public void setImages(String img)
    {
        tableimage = new ImageIcon(EGraphics.class.getResource(img));
        table = tableimage.getImage();
    }

    public int getAreaIAmIn()
    {
        if (!destroy)
            return areaIAmIn;
        return 0;
    }

    public int getDiam()
    {
        return diam;
    }

    public int getRadius()
    {
        return diam / 2;
    }

    public int getCenterX()
    {
        if (!destroy)
            return x + getRadius();
        return 0;
    }

    public int getCenterY()
    {
        if (!destroy)
            return y + getRadius();
        return 0;
    }

    public int getXCoor()
    {
        return x;
    }

    public int getYCoor()
    {
        return y;
    }

    public boolean getPickUp()
    {
        return canPickUp;
    }

    //mutators
    public void setXCoor(int newX)
    {
        x = newX;
    }

    public void setYCoor(int newY)
    {
        y = newY;
    }

    public void setArea(int a)
    {
        areaIAmIn = a;
    }

    public void setNight(boolean o)
    {

    }

    public boolean getNight()
    {
        return false;
    }

    public void setPlayerSleeping(boolean o)
    {
    }

    public boolean getPlayerSleeping()
    {
        return false;
    }

    public boolean objType()
    {
        return true;
    }

    public Inventory getInvenArray0()
    {

        return invenArray[0];
    }

    public Inventory getInvenArray1()
    {
        return invenArray[1];
    }

    public Inventory getInvenArray2()
    {
        return invenArray[2];
    }

    public Inventory getInvenArray3()
    {
        return invenArray[3];
    }

    public Inventory getInvenArray4()
    {
        return invenArray[4];

    }

    public void destroyIt()
    {
        destroy = true;
    }
}
