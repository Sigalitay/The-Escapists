
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Guard
{

    private int x;
    private int y;
    private String name;
    private int diam;
    private int vx;
    private int vy;
    private int steps;
 
    private int currArea;
    private int stage;
    private int magicNumber;
    private boolean isGuard;
    private boolean angry;
    private int sector;
    //added in to fix problem with guard
    private int prevVx, prevVy;  //this keeps track of the speed he was going at before the collision.

    private boolean up, down, left, right;

    private int guardNumber;

    private ImageIcon npcimage, npcbackimage, npcrightimage, npcleftimage;
    private Image npcpic, npcback, npcright, npcleft;

    private String sayThis;
    private long startTalking;
    private int speakCheck;
    private int rng;

    private long startTime, currTime;

    public Guard()
    {

    }

    public void increaseSector()
    {
        sector++;
    }

    public Guard(int xCoor, int yCoor, String n, int a, int g)
    {
        startTime = System.currentTimeMillis();
        currTime = 0;
        x = xCoor;
        y = yCoor;
        name = n;
        angry = false;
        vx = 0;
        vy = 0;
        diam = 30;
        steps = 0;
        currArea = a;
        stage = 0;
        magicNumber = 30;
        guardNumber = g;

        rng = (int) (Math.random() * 5 + 1);

        prevVx = 0;//0 will mean that he never collided 
        prevVy = 0;

        up = false;
        down = true;
        left = false;
        right = false;
        isGuard = true;

        sayThis = "";
        startTalking = -1;
        speakCheck = 0;

        //pics
        if (guardNumber > 0)
        {
            npcimage = new ImageIcon(EGraphics.class.getResource("cop1.png"));
            npcpic = npcimage.getImage();
            npcbackimage = new ImageIcon(EGraphics.class.getResource("cop1Back.png"));
            npcback = npcbackimage.getImage();
            npcrightimage = new ImageIcon(EGraphics.class.getResource("cop1right.png"));
            npcright = npcrightimage.getImage();
            npcleftimage = new ImageIcon(EGraphics.class.getResource("cop1left.png"));
            npcleft = npcleftimage.getImage();
        }
        else if (guardNumber == 0)
        {
            npcimage = new ImageIcon(EGraphics.class.getResource("dirtycop.png"));
            npcpic = npcimage.getImage();
            npcbackimage = new ImageIcon(EGraphics.class.getResource("cop1Back.png"));
            npcback = npcbackimage.getImage();
            npcrightimage = new ImageIcon(EGraphics.class.getResource("dirtyCopRight.png"));
            npcright = npcrightimage.getImage();
            npcleftimage = new ImageIcon(EGraphics.class.getResource("dirtyCopLeft.png"));
            npcleft = npcleftimage.getImage();
        }
        else
        {
            npcimage = new ImageIcon(EGraphics.class.getResource("filler.png"));
            npcpic = npcimage.getImage();
            npcbackimage = new ImageIcon(EGraphics.class.getResource("filler.png"));
            npcback = npcbackimage.getImage();
            npcrightimage = new ImageIcon(EGraphics.class.getResource("filler.png"));
            npcright = npcrightimage.getImage();
            npcleftimage = new ImageIcon(EGraphics.class.getResource("filler.png"));
            npcleft = npcleftimage.getImage();
        }

    }

    public void resetRNG()
    {
        rng = (int) (Math.random() * 5 + 1);
    }

    public boolean getIFGuard()
    {
        return isGuard;
    }

    public void goingUp(Player p)
    {
        if (detectCollisionPlayer(p))
        {
            vy = 0;
            vx = 0;
        }
        else
        {
            vx = 0;
            vy = -2;
            steps = 0;
            stage++;
            up = true;
            down = false;
            left = false;
            right = false;
        }

    }

    public void goingDown(Player p)
    {
        if (detectCollisionPlayer(p))
        {
            vy = 0;
            vx = 0;
        }
        else
        {
            vx = 0;
            vy = 2;
            steps = 0;
            stage++;
            up = false;
            down = true;
            left = false;
            right = false;
        }
    }

    public void goingRight(Player p)
    {
        if (detectCollisionPlayer(p))
        {
            vy = 0;
            vx = 0;
        }
        else
        {
            stage++;
            vx = 2;
            vy = 0;
            steps = 0;
            up = false;
            down = false;
            left = false;
            right = true;
        }
    }

    public void goingLeft(Player p)
    {
        if (detectCollisionPlayer(p))
        {

            vy = 0;
            vx = 0;
        }
        else
        {
            stage++;
            vx = -2;
            vy = 0;
            steps = 0;
            up = false;
            down = false;
            left = true;
            right = false;
        }
    }

    //act
    public void act(int w, int h, Player p)
    {

        //handling the talking
        if (!sayThis.equals("") && System.currentTimeMillis() > startTalking + 1000)//i have talked for a second
        {
            sayThis = "";
            startTalking = -1;
        }

        if (vx == 0 && vy == 0 && (prevVx != 0 || prevVy != 0))
        {
            //this means that he bumped into someone and his vx and vy should become the previous values
            vx = prevVx;
            vy = prevVy;

            //set the previous values back to 0 to allow him to go on his normal path
            prevVx = 0;
            prevVy = 0;

           
        }
        else
        {
            steps += Math.abs(vx);
            steps += Math.abs(vy);

        }

        //get the next x and y coordinates
        int nextX, nextY;
        nextX = x + vx;
        nextY = y + vy;
        if (!detectCollisionPlayer(p))
        {
            if (guardNumber == 1)
            {
                if (stage == 0) //i am walking right and reached the end of my path steps > 30 &&
                {
                    goingLeft(p);

                }
                else if (steps > 60 && stage == 1) //i am walking down and reached the end 
                {
                    goingDown(p);

                }
                else if (steps > 400 && stage == 2)//left and end
                {
                    goingRight(p);
                }
                else if (steps > 60 && stage == 3)
                {
                    goingUp(p);

                }
                else if (steps > 340 && stage == 4)
                {
                    if (detectCollisionPlayer(p))
                    {
                        vy = 0;
                        vx = 0;
                    }
                    else
                    {
                        vx = 0;
                        vy = 0;
                        steps = 0;
                        stage++;
                        up = false;
                        down = true;
                        left = false;
                        right = false;
                    }
                }
            }
            else if (guardNumber == 2)
            {
                if (stage == 0) //i am walking right and reached the end of my path steps > 30 &&
                {
                    goingRight(p);

                }
                else if (steps > 60 && stage == 1) //i am walking down and reached the end 
                {
                    goingDown(p);

                }
                else if (steps > 400 && stage == 2)//left and end
                {
                    goingLeft(p);
                }
                else if (steps > 60 && stage == 3)
                {
                    goingUp(p);

                }
                else if (steps > 340 && stage == 4)
                {
                    if (detectCollisionPlayer(p))
                    {
                        vy = 0;
                        vx = 0;
                    }
                    else
                    {
                        vx = 0;
                        vy = 0;
                        steps = 0;
                        stage++;
                        up = false;
                        down = true;
                        left = false;
                        right = false;
                    }
                }

            }
            if (stage == 5)
            {
                if (guardNumber == 1)
                    goingLeft(p);
                else if (guardNumber == 2)
                    goingRight(p);
            }
            else if (stage == 6 && steps > 80)
            {
                vx = 0;
                vy = 0;
                steps = 0;
                stage++;
                up = false;
                down = true;
                left = false;
                right = false;

            }
            
          
            //edges
            if (nextY + 30 > h)
            {
                vy *= -1;
            }
            else if (nextY < 0)
            {
                vy *= -1;
            }
            else if (nextX + 30 > w)
            {
                vx *= -1;
            }
            else if (nextX < 0)
            {
                vx *= -1;
            }
        }
        else
        {
            //grabbing the previous speeds
            prevVx = vx;
            prevVy = vy;

          
            vx = 0;
            vy = 0;

        }
//if-statements to handle the Bubble bouncing off of the 4 walls
//updating x and y
        x = x + vx;
        y = y + vy;
    }

    
    public void moveBack(Player p)
    {
        if (stage == 0)
            goingRight(p);
        else if (stage == 1 && steps > 100)
        {
            vx = 0;
            vy = 0;
            stage = 0;
        }

    }

    public void drawTextBubble(Graphics g, String str, int xCoor, int yCoor)
    {
        ImageIcon img;
        Image bubble;
        Graphics2D g2d = (Graphics2D) g;
        img = new ImageIcon(EGraphics.class.getResource("TextBubble.png"));
        bubble = img.getImage();
        g2d.drawImage(bubble, xCoor + 25 - setSize(str), yCoor - 15, setSize(str), 15, null);

        Font f = new Font("Stone Sans Sem ITC TT", Font.PLAIN, 8);
        g.setFont(f);
        g.setColor(Color.black);
        g.drawString(str, xCoor + 27 - setSize(str), yCoor - 5);

    }

    public int setSize(String str)
    {
        int output = 0;
        int len = str.length();
        for (int i = 0; i < len; i++)
        {
            if (i % 2 == 0)
            {
                output += 10;
            }
        }
        return output;
//        if (len <= 6)
//        {
//            if (len <= 4)
//            {
//                return 20;
//            }
//            return 40;
//        }
//        else
//            return 190;

    }

    //draw self
    public void drawSelf(Graphics g, int area)
    {
        Graphics2D g2d = (Graphics2D) g;
        ImageIcon angryImage = new ImageIcon(EGraphics.class.getResource("angry.png"));
        Image anger = angryImage.getImage();

        //Drawing the user-controlled player
        if (currArea == area)
        {
            if (up)
                g2d.drawImage(npcback, x, y, magicNumber, magicNumber, null);
            else if (down)
                g2d.drawImage(npcpic, x, y, magicNumber, magicNumber, null);
            else if (right)
                g2d.drawImage(npcright, x, y, magicNumber, magicNumber, null);
            else if (left)
                g2d.drawImage(npcleft, x, y, magicNumber, magicNumber, null);

            if (angry)
            {
                long currTime2 = System.currentTimeMillis() - startTime;
                g2d.drawImage(anger, x - 5, y - 5, 10, 10, null);
                if (currTime2 >= 9000)
                {
                    angry = false;
                }
            }
            if (!sayThis.equals(""))
                drawTextBubble(g, sayThis, x, y);

        }

    }

    public String talking()
    {
        double rng = (Math.random());
        String str;
        if (rng < .10)
        {
            str = "Go Away!";
        }
        else if (rng < .20)
        {
            str = "Haha";
        }
        else if (rng < .30)
        {
            str = "Dunking Donuts sponsor us";
        }
        else if (rng < .40)
        {
            str = "Get back in line!";
        }
        else
            str = "Get back in line!";
        return str;
    }

    //handle collision
    public boolean detectCollisionPlayer(Player p)
    {
        int pNextCenterX = p.getCenterX() + p.getVx();
        int pNextCenterY = p.getCenterY() + p.getVy();

        int nextCenterX = getCenterX() + vx;
        int nextCenterY = getCenterY() + vy;

        if ((distance(nextCenterX, nextCenterY, pNextCenterX, pNextCenterY) < p.getRadius() + getRadius()))
        {
            return true;

        }
        return false;

    }

    //guard and children
    public void handleCollisionG(Guard g)
    {
        if (g.getCurrArea() == currArea)
        {
            int nextGCenterX = g.getCenterX() + g.getVX();
            int nextGCenterY = g.getCenterY() + g.getVY();

            int nextCenterX = getCenterX() + vx;
            int nextCenterY = getCenterY() + vy;
            if ((distance(nextCenterX, nextCenterY, nextGCenterX, nextGCenterY) < 30))//< g.getRadius() + getRadius())
            {
                vx = 0;
                vy = 0;

            }
        }

    }

    public void setNight(boolean o)
    {

    }

    public void setSector(int i)
    {
        sector = 1;
    }

    public void resetSector()
    {
        sector = 1;
    }

    //objects
    public void handleCollisionO(Object[] obj)
    {
        Object o;

        for (int i = 0; i < obj.length; i++)
        {
            o = obj[i];
            if (o.whatType() == 1)
            {
                if (o.getAreaIAmIn() == currArea)
                {
                    int nextGCenterX = o.getCenterX();
                    int nextGCenterY = o.getCenterY();

                    int nextCenterX = getCenterX() + vx;
                    int nextCenterY = getCenterY() + vy;
                    if ((distance(nextCenterX, nextCenterY, nextGCenterX, nextGCenterY) < 30) && o.getAreaIAmIn() == currArea)//< g.getRadius() + getRadius())
                    {

                        angry = true;

                        o.destroyIt();

                    }
                }
            }
        }

    }

    public double distance(int x1, int y1, int x2, int y2)
    {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
    //mutators

    public void setXCoor(int xCoor)
    {
        x = xCoor;
    }

    public void setYCoor(int yCoor)
    {
        y = yCoor;
    }

    //interact
    public void interact(EGraphics e, Player p)/////////////////////************************
    {
        //just say something random like get back to it
        //unless it's the dirty cop then say the intro for the dirty cop
        //then only say not talking to you until you have the money
        //then when you have money play the dirtyCop method and end the game by winning
        //maybe cutscene
        if (guardNumber > 0)
        {
            if (p.getInven().getItemType() > 0)
            {
                int type = p.getInven().getItemType();
                if (type == 1)
                {
                    if (sayThis.equals(""))
                    {
                        startTalking = System.currentTimeMillis();
                        sayThis = talking();
                    }
                }
                else if (type == 2)
                {
                    if (sayThis.equals(""))
                    {
                        startTalking = System.currentTimeMillis();
                        sayThis = "I FOUND CONTRABAND";
                        e.endGame(4);
                    }

                }
                else if (type == 3)
                {
                    if (sayThis.equals(""))
                    {
                        startTalking = System.currentTimeMillis();
                        sayThis = "I FOUND CONTRABAND";
                        e.endGame(4);
                    }
                    
                }
                else if (type == 4)
                {
                    if (sayThis.equals(""))
                    {
                        startTalking = System.currentTimeMillis();
                        sayThis = talking();
                    }
                }
                else if (type == 5)
                {
                    if (sayThis.equals(""))
                    {
                        startTalking = System.currentTimeMillis();
                        sayThis = talking();
                    }
                }
            }
            else
            {
                if (sayThis.equals(""))
                {
                    startTalking = System.currentTimeMillis();
                    sayThis = talking();
                }
            }
        }
        else if (guardNumber == 0)
        {
           
            if (p.getMoney() >= 500)
            {
                e.endGame(1);
            }
        }
        else
        {
            if (sayThis.equals(""))
            {
                startTalking = System.currentTimeMillis();
                sayThis = talking();
            }
        }

    }

    public void rollCall(Object[] objArray, EGraphics e)
    {
        if (sayThis.equals("") && speakCheck == 0)
        {
            startTalking = System.currentTimeMillis();
            sayThis = "Jill";
            speakCheck++;
        }
        if (sayThis.equals("") && speakCheck == 1)
        {
            startTalking = System.currentTimeMillis();
            sayThis = "James";
            speakCheck++;
        }
        if (sayThis.equals("") && speakCheck == 2)
        {
            startTalking = System.currentTimeMillis();
            sayThis = "Monty";
            speakCheck++;
        }
        if (sayThis.equals("") && speakCheck == 3)
        {
            startTalking = System.currentTimeMillis();
            sayThis = "Banner";
            speakCheck++;
        }
        if (sayThis.equals("") && speakCheck == 4)
        {
            startTalking = System.currentTimeMillis();
            sayThis = "Jessie";
            speakCheck++;
        }

        if (sayThis.equals("") && speakCheck == 5)
        {
            startTalking = System.currentTimeMillis();
            String person;
            if (rng == 1)
            {
                person = "Jill";
            }
            else if (rng == 2)
            {
                person = "James";
            }
            else if (rng == 3)
            {
                person = "Monty";
            }
            else if (rng == 4)
            {
                person = "Banner";
            }
            else if (rng == 5)
            {
                person = "Jessie";
            }
            else
                person = "no one";
            sayThis = "The one chosen for a search today is " + person;

        }
        currTime = System.currentTimeMillis() - startTime;
        
        if (currTime >= 14000 || currTime>90000)
        {
            if (rng == 1)
            {
                if (objArray[0].checkContra())
                    e.endGame(4);
            }
            else if (rng == 1)
            {
                objArray[1].checkContra();
            }
            else if (rng == 2)
            {
                objArray[2].checkContra();
            }
            else if (rng == 3)
            {
                objArray[3].checkContra();
            }
            else if (rng == 4)
            {
                objArray[4].checkContra();
            }
        }
    }

    //accessors
    public int getXCoor()
    {
        return x;
    }

    public int getYCoor()
    {
        return y;
    }

    public String getName()
    {
        return name;
    }

    public int getVX()
    {
        return vx;
    }

    public int getVY()
    {
        return vy;
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

    public int getCurrArea()
    {
        return currArea;
    }

    //useless methods to stop the game from imploding
    public boolean getIfFocused()
    {
        return false;
    }

    public void setIfFocused(boolean o)
    {

    }

    public boolean getShopOpen()
    {
        return false;
    }

    public boolean getOpenMission()
    {
        return false;
    }

    public void setOpenShop(boolean o)
    {
    }

    public void setOpenMission(boolean o)
    {
    }

}
