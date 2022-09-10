
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Bed extends Object
{

    private int x;
    private int y;
    private ImageIcon bedPillowImage;
    private Image bedPillow;

    private ImageIcon bedBlanketImage;
    private Image bedBlanket;
    private int areaIAmIn;
    private boolean nightTime;
    private String whoseBed;
    private boolean playerSleeping;
    private long startTime;

    public Bed(int xCoor, int yCoor, int a, String str)
    {
        super(xCoor, yCoor, false, a);

        areaIAmIn = a;
        x = xCoor;
        y = yCoor;
        whoseBed = str;
        nightTime = false;
        playerSleeping = false;
        startTime = System.currentTimeMillis();
    }

    //you can OVERRIDE the methods that exist in the parent (ObjecT) and make them your own.  in this case we decided to not allow the toilet ot move
    public boolean moving(int px, int py)//
    {
        return false;
    }

    public void drawSelf(Graphics g, int area)
    {
        if (nightTime)
        {
            if (whoseBed.equals("James"))
                bedPillowImage = new ImageIcon(EGraphics.class.getResource("jamiepillow.png"));

            else if (whoseBed.equals("Monty"))
                bedPillowImage = new ImageIcon(EGraphics.class.getResource("montyPillow.png"));
            else if (whoseBed.equals("Jessie"))
                bedPillowImage = new ImageIcon(EGraphics.class.getResource("jamiepillow.png"));
            else if (whoseBed.equals("Banner"))
                bedPillowImage = new ImageIcon(EGraphics.class.getResource("bannerpillow.png"));
            if (playerSleeping)
            {
                if (whoseBed.equals("Jill"))
                    bedPillowImage = new ImageIcon(EGraphics.class.getResource("mypillow.png"));
            }

        }
        else
            bedPillowImage = new ImageIcon(EGraphics.class.getResource("pillow.png"));
        bedPillow = bedPillowImage.getImage();
        bedBlanketImage = new ImageIcon(EGraphics.class.getResource("blanket.png"));
        bedBlanket = bedBlanketImage.getImage();

        Graphics2D g2d = (Graphics2D) g;
        if (areaIAmIn == area)
        {
            g2d.drawImage(bedPillow, x, y, 30, 15, null);
            g2d.drawImage(bedBlanket, x, y + 15, 30, 30, null);
        }
    }

    public void interact(Player p)
    {
        if (whoseBed.equals("Jill") && nightTime == true)
        {
            playerSleeping = true;
            p.setX(5000);
        }
        else
            System.out.println("you can't sleep there");

    }

    public void setPlayerSleeping(boolean o)
    {
        playerSleeping = o;
    }

    public boolean getPlayerSleeping()
    {
        return playerSleeping;
    }

    public void setNight(boolean o)
    {
        if (o == false)
        {
            long currTime = System.currentTimeMillis() - startTime;

            if (currTime >= 2000)
            {
                nightTime = o;
            }
        }
        else
            nightTime = o;
    }

    public boolean getNight()
    {

        return nightTime;
    }
    public void drawNextDay(Graphics g, int area)
    {
        
    }
    public int whatType()
    {
        return 3;
    }
}
