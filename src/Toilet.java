
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Toilet extends Object
{

    //the toilet can have it's own instance variables
    private Inventory invenArray[];
    private int areaIAmIn;
    private ImageIcon insideImage;
    private Image inside;

    public Toilet(int xCoor, int yCoor, int a)
    {
        super(xCoor, yCoor, false, a);
        areaIAmIn = a;
        setImages("toilet.png");
        setImageV2("insideToilet.png");
        invenArray = new Inventory[2];//+2
        invenArray[0] = new Inventory(290, 122, true);
        invenArray[1] = new Inventory(475, 365, false);
    }
    public int whatType()
    {
        return 2;
    }

    //you can OVERRIDE the methods that exist in the parent (ObjecT) and make them your own.  in this case we decided to not allow the toilet ot move
    public boolean moving(int px, int py)//
    {
        return false;
    }

    public boolean objType()
    {
        return false;
    }

    public void setImageV2(String str)
    {
        insideImage = new ImageIcon(EGraphics.class.getResource(str));
        inside = insideImage.getImage();
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
    public Inventory getInvenArray0()
    {
        return invenArray[0];
    }
    public void getItemFromPlayer(Player p)
    {
        invenArray[1].setItemType(p.getInven().getItemType());
    }

}
