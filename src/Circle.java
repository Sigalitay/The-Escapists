
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Circle
{

    private int x;
    private int y;
    private int magicNumber;
    private int vx;
    private int vy;
    private ImageIcon circleImage;
    private Image circle;
    private int diam;

    public Circle()
    {
        x = 354;
        y = 218;
        diam = 30;
        vx = 0;
        vy = 0;
        magicNumber = 30;
        circleImage = new ImageIcon(EGraphics.class.getResource("circle.png"));
        circle = circleImage.getImage();

    }

    public int borders()
    {
        //1 = you cant move right
        //2 = you cant move left
        //3 = you cant move up
        //4 = you cant move down
        int nextRight = x + 36;
        int nextDown = y + 27;
        int nextLeft = x - 36;
        int nextUp = y - 27;
        System.out.println(nextLeft);
        System.out.println(nextUp);

        if (nextRight >= 390 && y >= 187)
        {
            return 1;
        }
        if (nextLeft <= 350)//&& y >= 190
        {
            return 2;
        }
        //else if()
        return 0;

    }

    public void drawSelf(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(circle, x, y, magicNumber, magicNumber, null);
    }

    public void setXCoor(int i)
    {
        int nextX = x + i;
        if (nextX >= 370 && y >= 163)
        {
            i = 0;
        }
        if (nextX >= 520)
        {
            i = 0;
        }
        if (nextX <= 90)
        {
            i = 0;
        }
        if (nextX <= 200 && y >= 163)
        {
            i = 0;
        }
        if (nextX <= 200 && y >= 160)
        {
            
            i = 0;
        }
        if (nextX <= 350 &&  nextX >= 315 && y <= 210)
        {
            i = 0;
        }
        if (nextX <= 280 &&  nextX >= 240 && y <= 210 && y>=110)
        {
            i = 0;
        }
        if (nextX <= 350 &&  nextX >= 240 && y >= 240)
        {
            i = 0;
        }
        if (nextX <= 200 && y <= 130)
        {
            i = 0;
        }
        x = x + i;
    }

    public void setYCoor(int i)
    {//if (nextX <= 170 && nextX >= 0 && nextY <= 100 && nextY >= 60)
        //if (!borders())
        int nextY = y + i;
        if (nextY <= 120 && x >= 315)
        {
            i = 0;
        }
        if (nextY <= 80 && x >= 200)
        {
            i = 0;
        }
        if (nextY <= 120 && x >= 97 && x <= 206)
        {
            i = 0;
        }
        if (nextY <= 240 && nextY >= 160 && x >= 245 && x <= 350)
        {
            i = 0;
        }
        if (nextY >= 320)
        {
            i = 0;
        }
        if (nextY <= 190 && x >= 390)
        {
            i = 0;
        }
        if (nextY >= 240 && x >= 240 && x <= 350)
        {
            i = 0;
        }
        if (nextY >= 105 && x >= 240 && x <= 280)
        {
            i = 0;
        }
        if (nextY >= 150 && x <= 200)
        {
            i = 0;
        }
        y = y + i;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

}
