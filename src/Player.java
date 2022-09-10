
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Player
{

    private String name;
    private int money;
    private int x;
    private int y;
    private int vx, vy;
    private int magicNumber;
    private boolean up, down, left, right;

    private ImageIcon playerimage, playerbackimage, playerrightimage, playerleftimage;
    private Image player, playerback, playerright, playerleft;

    private int diam;

    private int currArea;
    private Object holding;

    private Sensor sensor;

    private Inventory inven;

    private boolean ontop;
    private boolean lockdown;

    public Player(String str)
    {
        name = str;
        money = 0;
        x = 100;//og 50
        y = 150;
        vx = 0;
        vy = 0;
        magicNumber = 30;

        up = false;
        down = true;
        left = false;
        right = false;
        ontop = false;
        lockdown = false;

        inven = new Inventory(530, 360, false);

        //player
        playerimage = new ImageIcon(EGraphics.class.getResource("Prisoner2.png"));
        player = playerimage.getImage();
        playerbackimage = new ImageIcon(EGraphics.class.getResource("Prisoner2 Back.png"));
        playerback = playerbackimage.getImage();
        playerrightimage = new ImageIcon(EGraphics.class.getResource("Prisoner2Right.png"));
        playerright = playerrightimage.getImage();
        playerleftimage = new ImageIcon(EGraphics.class.getResource("prisoner2left.png"));
        playerleft = playerleftimage.getImage();

        currArea = 1;
        diam = EGraphics.magicNumber;
        sensor = new Sensor(x, y, diam, vx, vy, currArea, this);
        holding = null; //null means nothing is there. before you do anything with this variable you must check that it is NOT null
        //if you don't you will get a runtime error :NullPointerException

    }

    public void setDirection(String dir)
    {
        if (dir.equals("U"))
        {
            up = true;
            down = false;
            left = false;
            right = false;
        }
        else if (dir.equals("D"))
        {
            up = false;
            down = true;
            left = false;
            right = false;
        }
        else if (dir.equals("L"))
        {
            up = false;
            down = false;
            left = true;
            right = false;
        }
        else if (dir.equals("R"))
        {
            up = false;
            down = false;
            left = false;
            right = true;
        }
    }

    public void moveToArea(int a)
    {
        currArea = a;
        sensor.setArea(a);

        if (holding != null)
            holding.setArea(currArea);

        if (currArea == 1)
        {
            x = EGraphics.width / 2;
            y = 30;
        }
        else if (currArea == 2)
        {
            y = EGraphics.height - 40;
            x = EGraphics.width / 2;
        }
    }

    //methods
    //pick up
    public boolean pickUp(Object obj)
    {
        if (obj.getPickUp())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //handle collision
    //objects
    public boolean detectCollision(Object[] objs, int area)
    {
        for (int i = 0; i < objs.length; i++)
        {
            Object curr = objs[i];
            if (curr.getAreaIAmIn() == area)
            {
                int nextXCenter;
                int nextYCenter;
                int factorX = 0;
                int factorY = 0;
                if (up)
                    factorY = -30;
                else if (down)
                    factorY = 30;
                else if (left)
                    factorX = -30;
                else if (right)
                    factorX = 30;
                else
                {
                    factorX = 0;
                    factorY = 0;
                }
                if (holdingObject())
                {
                    nextXCenter = x + getRadius() + vx + factorX;
                    nextYCenter = y + getRadius() + vy + factorY;
                }
                else
                {
                    nextXCenter = x + getRadius() + vx;
                    nextYCenter = y + getRadius() + vy;
                }

                int objXC = curr.getCenterX();
                int objYC = curr.getCenterY();

                if (EGraphics.distance(nextXCenter, objXC, nextYCenter, objYC) <= getRadius() + curr.getRadius() && holding != curr)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void drawSelf(Graphics g)
    {

        Graphics2D g2d = (Graphics2D) g;

        //Drawing the user-controlled player
        if (up)
            g2d.drawImage(playerback, x, y, magicNumber, magicNumber, null);
        else if (down)
            g2d.drawImage(player, x, y, magicNumber, magicNumber, null);
        else if (right)
            g2d.drawImage(playerright, x, y, magicNumber, magicNumber, null);
        else if (left)
            g2d.drawImage(playerleft, x, y, magicNumber, magicNumber, null);
        inven.drawSelf(g);

    }

    //checking if object in front of me
    public boolean isObjectInfrontOfMe(Object[] objs, int area)//change this to return an object or null
    {
        for (int i = 0; i < objs.length; i++)
        {
            Object curr = objs[i];
            if (curr.getAreaIAmIn() == area)
            {
                int nextXCenter = x + getRadius();
                int nextYCenter = y + getRadius();

                int objXC = curr.getCenterX();
                int objYC = curr.getCenterY();

                if (EGraphics.distance(nextXCenter, objXC, nextYCenter, objYC) - 10 <= getRadius() + curr.getRadius())
                {

                    if (curr.getPickUp())
                    {
                        if (nextXCenter < objXC && (nextYCenter >= objYC - curr.getRadius() / 2 && nextYCenter <= objYC + curr.getRadius() / 2) && right == true)//checking left
                        {
                            holding = curr;
                            return true;
                        }
                        if (nextXCenter > objXC && (nextYCenter >= objYC - curr.getRadius() / 2 && nextYCenter <= objYC + curr.getRadius() / 2) && left == true)//checking right
                        {
                            holding = curr;
                            return true;
                        }
                        if (nextYCenter > objYC && (nextXCenter >= objXC - curr.getRadius() / 2 && nextXCenter <= objXC + curr.getRadius() / 2) && up == true)//checking from below
                        {
                            holding = curr;
                            return true;
                        }
                        if (nextYCenter < objYC && (nextXCenter >= objXC - curr.getRadius() / 2 && nextXCenter <= objXC + curr.getRadius() / 2) && down == true)//checking from above
                        {
                            holding = curr;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void movement(int w, int h, EGraphics e)
    {
        int nextX;
        int nextY;
        int factorX = 0;
        int factorY = 0;
        if (up)
            factorY = -30;
        else if (down)
            factorY = 30;
        else if (left)
            factorX = -30;
        else if (right)
            factorX = 30;
        else
        {
            factorX = 0;
            factorY = 0;
        }
        if (holdingObject())
        {
            nextX = nextX = x + vx + factorX;
            nextY = nextY = y + vy + factorY;
        }
        else
        {
            nextX = x + vx;
            nextY = y + vy;
        }
        //moving the player

        //edges
        if (nextY + 30 > h)
        {
            vy = 0;
        }
        else if (nextY < 0)
        {
            vy = 0;
        }
        else if (nextX + 30 > w)
        {
            vx = 0;
        }
        else if (nextX < 0)
        {
            vx = 0;
        }

        //info deck border
        if (nextY + 30 > EGraphics.height - 120 && nextX + 30 > EGraphics.width - 180)
        {
            vy = 0;
            vx = 0;
        }

        //border
        if (currArea == 1)
        {
            if (holdingObject())
            {
                if (nextX <= 170 && nextX >= 0 && nextY <= 220 && nextY >= 200)
                {
                    vx = 0;
                    vy = 0;
                }
                else if (nextX <= 170 && nextX >= 0 && nextY <= 340 && nextY >= 320)
                {
                    vx = 0;
                    vy = 0;
                }
                else if (nextX <= w && nextX >= w - 200 && nextY <= 100 && nextY >= 80)
                {
                    vx = 0;
                    vy = 0;
                }
                else if (nextX <= w && nextX >= w - 200 && nextY <= 220 && nextY >= 200)
                {
                    vx = 0;
                    vy = 0;
                }

            }
            else
            {
                if (nextX <= 170 && nextX >= 0 && nextY <= 220 && nextY >= 180)
                {
                    vx = 0;
                    vy = 0;
                }
                else if (nextX <= 170 && nextX >= 0 && nextY <= 340 && nextY >= 300)
                {
                    vx = 0;
                    vy = 0;
                }
                else if (nextX <= w && nextX >= w - 200 && nextY <= 100 && nextY >= 60)
                {
                    vx = 0;
                    vy = 0;
                }
                else if (nextX <= w && nextX >= w - 200 && nextY <= 220 && nextY >= 180)
                {
                    vx = 0;
                    vy = 0;
                }
            }
            if (nextX <= 170 && nextX >= 0 && nextY <= 100 && nextY >= 60)
            {
                vx = 0;
                vy = 0;
            }
            else if (nextX <= 170 && nextX >= 160 && nextY <= 155 && nextY >= 110)
            {

                vx = 0;
                vy = 0;
            }

            else if (nextX <= 170 && nextX >= 160 && nextY <= 275 && nextY >= 210)
            {
                vx = 0;
                vy = 0;
            }

            else if (nextX <= 170 && nextX >= 160 && nextY <= 395 && nextY >= 355)
            {
                vx = 0;
                vy = 0;
            }

            else if (nextX <= w - 185 && nextX >= w - 200 && nextY <= 155 && nextY >= 115)
            {
                vx = 0;
                vy = 0;
            }

            else if (nextX <= w - 185 && nextX >= w - 200 && nextY <= 275 && nextY >= 235)
            {
                vx = 0;
                vy = 0;
            }

            //doors
            if (lockdown)
            {
                if (nextX <= 180 && nextX >= 160 && nextY <= h && nextY >= 100)
                {
                    vx = 0;
                    vy = 0;
                }
                else if (nextX <= w - 165 && nextX >= w - 200 && nextY <= h && nextY >= 100)
                {
                    vx = 0;
                    vy = 0;
                }
            }

        }
        else if (currArea == 2)
        {
            if (nextX <= 310 && nextX >= 283 && nextY <= 210 && nextY >= 0)
            {
                vx = 0;
                vy = 0;
            }
            else if (nextX <= 410 && nextX >= 310 && nextY <= 215 && nextY >= 190)
            {
                vx = 0;
                vy = 0;
            }
            else if (nextX <= w && nextX >= 460 && nextY <= 215 && nextY >= 190)
            {
                vx = 0;
                vy = 0;
            }
            else if (nextX <= 300 && nextX >= 190 && nextY <= 170 && nextY >= 145)
            {
                vx = 0;
                vy = 0;
            }
            else if (nextX <= 145 && nextX >= 0 && nextY <= 170 && nextY >= 145)
            {
                vx = 0;
                vy = 0;
            }
            else if (nextX <= 120 && nextX >= 95 && nextY <= h && nextY >= 160)
            {
                vx = 0;
                vy = 0;
            }
            else if (nextX <= 300 && nextX >= 190 && nextY <= 120 && nextY >= 35)
            {
                vx = 0;
                vy = 0;

            }
            else if (nextX <= 90 && nextX >= 0 && nextY <= 120 && nextY >= 35)
            {
                vx = 0;
                vy = 0;
            }
        }
        else if (currArea == 3)
        {

        }
        if (nextX <= 570 && nextX >= 530 && nextY <= 50 && nextX >= 30 && e.getCurrMission() == 2)//
        {

            e.completeMission(2);
            e.setCurrMission(-1);
            e.setMissionAccepted(0);
        }

        x = x + vx;
        y = y + vy;

        if (vy < 0)//up
        {

            if (holding != null)
            {
                holding.moving(x + (diam / 2 - holding.getDiam() / 2), y - holding.getDiam());
                nextY -= holding.getDiam();
            }

            up = true;
            down = false;
            left = false;
            right = false;

        }
        else if (vx > 0)//right
        {
            if (holding != null)
            {
                holding.moving(x + diam, y + (diam / 2 - holding.getDiam() / 2));
            }
            nextX += 30;
            up = false;
            down = false;
            left = false;
            right = true;

        }
        else if (vy > 0)//down
        {
            if (holding != null)
            {
                holding.moving(x + (diam / 2 - holding.getDiam() / 2), y + diam);

            }

            up = false;
            down = true;
            left = false;
            right = false;

        }
        else if (vx < 0)//left
        {

            if (holding != null)
            {
                holding.moving(x - holding.getDiam(), y + (diam / 2 - holding.getDiam() / 2));
            }

            up = false;
            down = false;
            left = true;
            right = false;

        }

        if (vy < 0)
        {
            sensor.setX(x + (diam / 2 - sensor.getDiam() / 2));
            sensor.setY(y - sensor.getDiam());
        }
        else if (vx > 0)//
        {
            sensor.setX(x + diam);
            sensor.setY(y + (diam / 2 - sensor.getDiam() / 2));
        }
        else if (vy > 0)//down
        {
            sensor.setX(x + (diam / 2 - sensor.getDiam() / 2));
            sensor.setY(y + diam);
        }
        else if (vx < 0)//left
        {
            sensor.setX(x - sensor.getDiam());
            sensor.setY(y + (diam / 2 - sensor.getDiam() / 2));
        }

    }

   

    //handle collision with guard or prisoner
    public void handleCollisionGuard(Guard g)
    {
        int nextXCenter = x + getRadius() + vx;
        int nextYCenter = y + getRadius() + vy;

        int guardXCenter = g.getCenterX();
        int guardYCenter = g.getCenterY();
        if (currArea == g.getCurrArea() && EGraphics.distance(nextXCenter, guardXCenter, nextYCenter, guardYCenter) < getRadius() + g.getRadius())
        {
            vy = 0;
            vx = 0;
        }
    }

    //sensing whats in front of me
    public void sense(Guard[] g, Object[] o, EGraphics e)
    {
        for (int i = 0; i < g.length; i++)
        {

            Guard currG = g[i];

            if (sensor.senseGuard(currG))
            {

                currG.interact(e, this);
                return;
            }
        }
        for (int i = 0; i < o.length; i++)
        {
            Object currO = o[i];

            if (sensor.senseObject(currO) && !currO.getStorage())
            {

                currO.interact(this);
                return;
            }
        }

    }

    public String senseV2(Guard[] g, Object[] o, EGraphics e)
    {
        for (int i = 0; i < g.length; i++)
        {

            Guard currG = g[i];

            if (sensor.senseGuard(currG))
            {
                return "Guard " + i;
            }
        }
        for (int i = 0; i < o.length; i++)
        {
            Object currO = o[i];

            if (sensor.senseObject(currO) && !currO.getStorage())
            {
                return "Object " + i;
            }
        }
        return "no";

    }

    public void getOnTop(Object o, String str, int w, int h, Object[] objArray)
    {
        if (!ontop)
        {
            int newX = o.getXCoor();
            int newY = o.getYCoor();
            setX(newX);
            setY(newY - 20);
            ontop = true;
        }
        else
        {
            ontop = false;
            //border
            int nextX;
            int nextY;
            if (up)
            {
                nextX = x;
                nextY = y - 30;
            }
            else if (down)
            {
                nextX = x;
                nextY = y + 45;
            }
            else if (right)
            {
                nextX = x + 30;
                nextY = y;
            }
            else if (left)
            {
                nextX = x - 30;
                nextY = y;
            }
            else
            {
                nextX = 0;
                nextY = 0;
            }
            if (currArea == 1)
            {
                if (nextX <= 170 && nextX >= 0 && nextY <= 100 && nextY >= 60)
                {
                    nextX = x;
                    nextY = y;
                }
                else if (nextX <= 170 && nextX >= 0 && nextY <= 220 && nextY >= 180)
                {
                    nextX = x;
                    nextY = y;
                }
                else if (nextX <= 170 && nextX >= 0 && nextY <= 340 && nextY >= 300)
                {
                    nextX = x;
                    nextY = y;
                }
                else if (nextX <= w && nextX >= w - 200 && nextY <= 100 && nextY >= 60)
                {
                    nextX = x;
                    nextY = y;
                }
                else if (nextX <= w && nextX >= w - 200 && nextY <= 220 && nextY >= 180)
                {
                    nextX = x;
                    nextY = y;
                }
            }
            else if (currArea == 2)
            {
                if (nextX <= 310 && nextX >= 283 && nextY <= 210 && nextY >= 0)
                {
                    nextX = x;
                    nextY = y;
                }
                else if (nextX <= 410 && nextX >= 310 && nextY <= 215 && nextY >= 190)
                {
                    nextX = x;
                    nextY = y;
                }
                else if (nextX <= w && nextX >= 460 && nextY <= 215 && nextY >= 190)
                {
                    nextX = x;
                    nextY = y;
                }
                else if (nextX <= 300 && nextX >= 190 && nextY <= 170 && nextY >= 145)
                {
                    nextX = x;
                    nextY = y;
                }
                else if (nextX <= 145 && nextX >= 0 && nextY <= 170 && nextY >= 145)
                {
                    nextX = x;
                    nextY = y;
                }
                else if (nextX <= 120 && nextX >= 95 && nextY <= h && nextY >= 160)
                {
                    nextX = x;
                    nextY = y;
                }
                else if (detectCollision(objArray, currArea))
                {
                    nextX = x;
                    nextY = y;
                }
            }
            x = nextX;
            y = nextY;
        }
    }

    //accessors
    //earn money
    public void earnMoney(int amount)
    {
        money += amount;
    }

    //lose money
    public void loseMoney(int amount, int type)
    {
        if (money < amount)
        {
            money = 0;

        }
        else
            money -= amount;
        inven.takeSomething(type);
    }

    public void dropObject()
    {
        holding = null;
    }

   
     public boolean getIfOnTop()
    {
        return ontop;
    }

    public void getOffTop()
    {
        ontop = false;
    }

    public void setCurrArea(int i)
    {
        currArea = i;
        sensor.setArea(i);
    }
    public int getVy()
    {
        return vy;
    }

    public int getVx()
    {
        return vx;
    }

    public String toString()
    {
        return "My name is " + name + ".";
    }

    public int getCurrArea()
    {
        return currArea;
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
        return x + getRadius();
    }

    public int getCenterY()
    {
        return y + getRadius();
    }

    //accessors
    public String getName()
    {
        return name;
    }

    public int getMoney()
    {
        return money;
    }

    public int getXCoor()
    {
        return x;
    }

    public int getYCoor()
    {
        return y;
    }

    public void setVx(int v)
    {
        vx = v;
    }

    public void setVy(int v)
    {
        vy = v;
    }

    public boolean holdingObject()
    {
        if (holding == null)
            return false;
        else
            return true;
    }

    public void setX(int xCoor)
    {
        x = xCoor;
    }

    public void setY(int yCoor)
    {
        y = yCoor;
    }

    public Inventory getInven()
    {
        return inven;
    }

    public void setLockdown(boolean o)
    {
        lockdown = o;
    }

    public Object getHolding()
    {
        return holding;
    }
}
