
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Items
{

    private Image item;
    private ImageIcon img;
    private int type;
    private boolean contraband;

    public Items(int t)
    {
        type = t;
        checkContra();
        setImages();

    }

    public void checkContra()
    {
        if (type == 1)
        {
            contraband = false;//pencil
        }
        else if (type == 2)
        {
            contraband = true;//note
        }
        else if (type == 3)
        {
            contraband = true;//wire cutters
        }
        else if (type == 4)
        {
            contraband = false;//soap
        }
        else if (type == 5)
        {
            contraband = false;//pillow
        }
    }

    public void setImages()
    {
        if (type == 1)
        {
            img = new ImageIcon(EGraphics.class.getResource("pencil.png"));
            item = img.getImage();
        }
        else if (type == 2)
        {
            img = new ImageIcon(EGraphics.class.getResource("note.png"));
            item = img.getImage();
        }
        else if (type == 3)
        {
            img = new ImageIcon(EGraphics.class.getResource("wirecutters.png"));
            item = img.getImage();
        }
        else if (type == 4)
        {
            img = new ImageIcon(EGraphics.class.getResource("soap.png"));
            item = img.getImage();
        }
        else if (type == 5)
        {
            img = new ImageIcon(EGraphics.class.getResource("pillowItem.png"));
            item = img.getImage();
        }
        else
        {
            img = new ImageIcon(EGraphics.class.getResource("filler.png"));
            item = img.getImage();
        }
    }
    public Image getImg()
    {
        return item;
    }
    public void setType(int t)
    {
        type = t;
        setImages();
    }

    public boolean getContra()
    {
        System.out.println(type);
        return contraband;
    }

    public int getType()
    {
        return type;
    }
}
