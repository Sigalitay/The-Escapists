
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Shop
{

    private int x;
    private int wordX;
    private int y;
    private String itemStatus;
    private int pay;
    private int item;
    private ImageIcon itemImage;
    private String thingo;
    private Image itemIcon;
    private boolean soldOut;

    public Shop(int xCoor, int yCoor, int price, int type)
    {
        x = xCoor;
        wordX = xCoor;
        item = type;
        if (item == 2 || item == 4)
        {
            wordX -= 10;
        }
        y = yCoor;
        pay = price;
        if (item == 1)
        {
            thingo = "Pencil";
        }
        else if (item == 2)
        {
            thingo = "Note";
        }
        else if (item == 3)
        {
            thingo = "Cutters";
        }
        else if (item == 4)
        {
            thingo = "Soap";
        }
        itemStatus = "$" + price + " - " + thingo;

        soldOut = false;
    }

    public int getX()
    {
        return x;
    }

    public int getWordX()
    {
        return wordX;
    }

    public int getY()
    {
        return y;
    }

    public String getIS()
    {
        return itemStatus;
    }

    public void drawSelf(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        if (item == 1)
        {
            itemImage = new ImageIcon(EGraphics.class.getResource("pencil.png"));

        }
        else if (item == 2)
        {
            itemImage = new ImageIcon(EGraphics.class.getResource("note.png"));//change later

        }
        else if (item == 3)
        {
            itemImage = new ImageIcon(EGraphics.class.getResource("wirecutters.png"));

        }
        else if (item == 4)
        {
            itemImage = new ImageIcon(EGraphics.class.getResource("soap.png"));
        }
        itemIcon = itemImage.getImage();
        g2d.drawImage(itemIcon, x, y, 100, 100, null);//125
    }

    public void buy(Player p)
    {
        if (!itemStatus.equals("SOLD OUT"))
        {
            if (p.getMoney() >= pay)
            {
                p.loseMoney(pay, item);
                p.getInven().setItemType(item);
                soldOut = true;
                itemStatus = "SOLD OUT";

            }
            else
            {
                System.out.println("you cant afford that");
            }
        }
    }

    public boolean getSoldOut()
    {
        return soldOut;
    }

}
