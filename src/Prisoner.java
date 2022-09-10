
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Prisoner extends Guard
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

    private int preX;
    private int preY;
    private int prevVx, prevVy;  //this keeps track of the speed he was going at before the collision.

    private boolean up, down, left, right;

    private int prisonerNumber;

    private ImageIcon npcimage, npcbackimage, npcrightimage, npcleftimage;
    private Image npcpic, npcback, npcright, npcleft;

    private boolean talking;

    private boolean missionGUIScreen;
    private boolean shopGUIScreen;
    private boolean focusedPris;

    private String sayThis;
    private long startTalking;

    private long startTime;
    private long currTime;

    private int sector;
    private boolean nightTime;

    public Prisoner(int xCoor, int yCoor, String n, int a, int t)
    {
        super(xCoor, yCoor, n, a, t);
        x = xCoor;
        y = yCoor;
        vx = 0;
        vy = 0;

        preX = xCoor;
        preY = yCoor;
        prevVx = 0;//0 will mean that he never collided 
        prevVy = 0;

        name = n;

        sector = 1;
        diam = 30;
        steps = 0;
        currArea = a;
        stage = 0;
        magicNumber = 30;
        prisonerNumber = t;
        nightTime = false;

        up = false;
        down = true;
        left = false;
        right = false;

        sayThis = "";
        startTalking = -1;

        startTime = System.currentTimeMillis();
        currTime = 0;

        //pics
        if (prisonerNumber == 2)
        {
            npcimage = new ImageIcon(EGraphics.class.getResource("shopkeep.png"));
            npcpic = npcimage.getImage();
            npcbackimage = new ImageIcon(EGraphics.class.getResource("shopkeepback.png"));
            npcback = npcbackimage.getImage();
            npcrightimage = new ImageIcon(EGraphics.class.getResource("shopkeepright.png"));
            npcright = npcrightimage.getImage();
            npcleftimage = new ImageIcon(EGraphics.class.getResource("shopkeepleft.png"));
            npcleft = npcleftimage.getImage();
        }
        else if (prisonerNumber == 4)
        {
            npcimage = new ImageIcon(EGraphics.class.getResource("missiongiver.png"));
            npcpic = npcimage.getImage();
            npcbackimage = new ImageIcon(EGraphics.class.getResource("missiongiverback.png"));
            npcback = npcbackimage.getImage();
            npcrightimage = new ImageIcon(EGraphics.class.getResource("missiongiverright.png"));
            npcright = npcrightimage.getImage();
            npcleftimage = new ImageIcon(EGraphics.class.getResource("missiongiverleft.png"));
            npcleft = npcleftimage.getImage();
        }
        else
        {
            npcimage = new ImageIcon(EGraphics.class.getResource("prisoner1.png"));
            npcpic = npcimage.getImage();
            npcbackimage = new ImageIcon(EGraphics.class.getResource("prisoner1back.png"));
            npcback = npcbackimage.getImage();
            npcrightimage = new ImageIcon(EGraphics.class.getResource("prisoner1right.png"));
            npcright = npcrightimage.getImage();
            npcleftimage = new ImageIcon(EGraphics.class.getResource("prisoner1left.png"));
            npcleft = npcleftimage.getImage();
        }

        talking = false;

        missionGUIScreen = false;
        shopGUIScreen = false;
        focusedPris = false;

    }

    

    

    //interact
    public void interact(EGraphics e, Player p)
    {

        if (prisonerNumber == 2)//shop keep
        {

            //set the GUI for shop to open
            if (!shopGUIScreen)
            {
                shopGUIScreen = true;
            }
            else
                shopGUIScreen = true;

        }
        else if (prisonerNumber == 4)//mission
        {

            if (e.getCurrMission() == 0)
            {
                if (p.getInven().getItemType() == 1)
                {
                    e.completeMission(0);
                    p.getInven().setItemType(0);
                    e.setCurrMission(-1);
                    e.setMissionAccepted(0);
                }
                else
                {
                    if (!missionGUIScreen)
                    {
                        missionGUIScreen = true;
                    }
                    else
                        missionGUIScreen = true;
                }
            }
            else if (e.getCurrMission() == 3)
            {
                if (p.getInven().getItemType() == 3)
                {
                    e.completeMission(3);
                    p.getInven().setItemType(0);
                    e.setCurrMission(-1);
                    e.setMissionAccepted(0);
                }
                else
                {
                    if (!missionGUIScreen)
                    {
                        missionGUIScreen = true;
                    }
                    else
                        missionGUIScreen = true;
                }
            }
            else
            {
                //set the GUI for mission to open
                if (!missionGUIScreen)
                {
                    missionGUIScreen = true;
                }
                else
                    missionGUIScreen = true;
            }
        }
        else if (prisonerNumber == 1)
        {
            if (e.getCurrMission() == 1)
            {
                if (p.getInven().getItemType() == 2)
                {
                    e.completeMission(1);
                    p.getInven().setItemType(0);
                    e.setCurrMission(-1);
                    e.setMissionAccepted(0);
                }
                else
                {
                    //say something random
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
        else
        {
            if (sayThis.equals(""))
            {
                startTalking = System.currentTimeMillis();
                sayThis = talking();
            }
        }
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
        currTime = System.currentTimeMillis() - startTime;
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
            if (prisonerNumber == 1)
            {
                if (stage == 0 && sector == 1)
                {
                    if (currTime >= 2000)
                    {
                        stage++;
                    }
                }
                if (stage == 1 && sector == 1 && sector == 1) //i am walking right and reached the end of my path steps > 30 &&
                {
                    goingLeft(p);
                }
                else if (steps > 30 && stage == 2 && sector == 1) //i am walking down and reached the end 
                {
                    goingDown(p);
                }
                else if (steps > 50 && stage == 3 && sector == 1)//left and end
                {
                    goingRight(p);

                }
                else if (steps > 100 && stage == 4 && sector == 1)
                {
                    goingUp(p);

                }
                else if (steps > 30 && stage == 5 && sector == 1)
                {
                    vx = 0;
                    vy = 0;
                    steps = 0;
                    stage++;
                    up = false;
                    down = false;
                    left = false;
                    right = true;

                }
                if (sector == 2 && stage == 0)
                {
                    goingRight(p);

                }
                else if (sector == 2 && stage == 1 && steps > 70)
                {
                    goingUp(p);
                }
                else if (sector == 2 && stage == 2 && steps > 250)
                {
                    changeArea(2, p);
                    stage++;
                    steps = 0;
                }
                else if (sector == 2 && stage == 3)
                {
                    goingUp(p);
                }
                else if (sector == 2 && stage == 4 && steps > 180)
                {
                    goingLeft(p);
                }
                else if (sector == 2 && stage == 5 && steps > 110)
                {
                    goingUp(p);
                }
                else if (sector == 2 && stage == 6 && steps > 220)
                {
                    goingRight(p);
                }
                else if (sector == 2 && stage == 7 && steps > 70)
                {
                    goingDown(p);
                }
                else if (sector == 2 && stage == 8 && steps > 30)
                {
                    vx = 0;
                    vy = 0;
                }
                if (sector == 3 && stage == 0)
                {
                    goingLeft(p);
                }
                else if (sector == 3 && stage == 1 && steps > 70)
                {
                    goingDown(p);
                }
                else if (sector == 3 && stage == 2 && steps > 220)
                {
                    goingRight(p);
                }
                else if (sector == 3 && stage == 3 && steps > 270)
                {
                    goingUp(p);
                }
                else if (sector == 3 && stage == 4 && steps > 240)
                {
                    goingLeft(p);
                }
                else if (sector == 3 && stage == 5 && steps > 80)
                {
                    vx = 0;
                    vy = 0;
                }
                if (sector == 4 && stage == 0)
                {
                    goingRight(p);
                }
                else if (sector == 4 && stage == 1 && steps > 70)
                {
                    goingDown(p);
                }
                else if (sector == 4 && stage == 2 && steps > 220)
                {
                    goingLeft(p);
                }
                else if (sector == 4 && stage == 3 && steps > 260)
                {
                    goingUp(p);
                }
                else if (sector == 4 && stage == 4 && steps > 240)
                {
                    goingRight(p);
                }
                else if (sector == 4 && stage == 5 && steps > 70)
                {
                    goingDown(p);
                }
                else if (sector == 4 && stage == 6 && steps > 30)
                {
                    vx = 0;
                    vy = 0;

                }
                if (sector == 5 && stage == 0)
                {
                    goingLeft(p);
                }
                else if (sector == 5 && stage == 1 && steps > 70)
                {
                    goingDown(p);
                }
                else if (sector == 5 && stage == 2 && steps > 220)
                {
                    goingRight(p);
                }
                else if (sector == 5 && stage == 3 && steps > 270)
                {
                    goingUp(p);
                }
                else if (sector == 5 && stage == 4 && steps > 240)
                {
                    goingLeft(p);
                }
                else if (sector == 5 && stage == 5 && steps > 80)
                {
                    vx = 0;
                    vy = 0;
                }
                if (sector == 6 && stage == 0)
                {
                    goingRight(p);
                }
                else if (sector == 6 && stage == 1 && steps > 70)
                {
                    goingDown(p);
                }
                else if (sector == 6 && stage == 2 && steps > 220)
                {
                    goingLeft(p);
                }
                else if (sector == 6 && stage == 3 && steps > 120)
                {
                    goingDown(p);
                }
                else if (sector == 6 && stage == 4 && steps > 180)
                {
                    changeArea(1, p);
                }
                else if (sector == 6 && stage == 5 && steps > 120)
                {
                    vx = 0;
                    vy = 0;
                    down = false;
                    up = true;
                }
                if (sector == 7 && stage == 0)
                {
                    goingUp(p);
                }
                else if (sector == 7 && stage == 1 && steps > 120)
                {
                    changeArea(2, p);
                }
                else if (sector == 7 && stage == 2)
                {
                    goingUp(p);
                }
                else if (sector == 7 && stage == 3 && steps > 180)
                {
                    goingLeft(p);
                }
                else if (sector == 7 && stage == 4 && steps > 110)
                {
                    goingUp(p);
                }
                else if (sector == 7 && stage == 5 && steps > 220)
                {
                    goingRight(p);
                }
                else if (sector == 7 && stage == 6 && steps > 70)
                {
                    goingDown(p);
                }
                else if (sector == 7 && stage == 7 && steps > 30)
                {
                    vx = 0;
                    vy = 0;
                }
                if (sector == 8 && stage == 0)
                {
                    goingLeft(p);
                }
                else if (sector == 8 && stage == 1 && steps > 70)
                {
                    goingDown(p);
                }
                else if (sector == 8 && stage == 2 && steps > 220)
                {
                    goingRight(p);
                }
                else if (sector == 8 && stage == 3 && steps > 120)
                {
                    goingDown(p);
                }
                else if (sector == 8 && stage == 4 && steps > 120)
                {
                    changeArea(1, p);
                }

            }
            else if (prisonerNumber == 2)
            {
                if (stage == 0 && sector == 1)
                {
                    if (currTime >= 3500)
                    {
                        stage++;
                    }
                }
                if (stage == 1 && sector == 1) //i am walking right and reached the end of my path steps > 30 &&
                {
                    goingLeft(p);
                }
                else if (steps > 30 && stage == 2 && sector == 1) //i am walking down and reached the end 
                {
                    goingDown(p);
                }
                else if (steps > 50 && stage == 3 && sector == 1)//left and end
                {
                    goingRight(p);

                }
                else if (steps > 100 && stage == 4 && sector == 1)
                {
                    goingUp(p);

                }
                else if (steps > 30 && stage == 5 && sector == 1)
                {
                    vx = 0;
                    vy = 0;
                    steps = 0;
                    stage++;
                    up = false;
                    down = false;
                    left = false;
                    right = true;
                }
                if (sector == 2 && stage == 0)
                {
                    goingRight(p);
                }
                else if (sector == 2 && stage == 1 && steps > 70)
                {
                    goingUp(p);
                }
                else if (sector == 2 && stage == 2 && steps > 360)
                {
                    changeArea(2, p);
                    stage++;
                    steps = 0;
                }
                else if (sector == 2 && stage == 3)
                {
                    goingUp(p);
                }
                else if (sector == 2 && stage == 4 && steps > 180)
                {
                    goingLeft(p);
                }
                else if (sector == 2 && stage == 5 && steps > 140)
                {
                    goingUp(p);
                }
                else if (sector == 2 && stage == 6 && steps > 220)
                {
                    goingRight(p);
                }
                else if (sector == 2 && stage == 7 && steps > 110)
                {
                    goingDown(p);
                }
                else if (sector == 2 && stage == 8 && steps > 30)
                {
                    vx = 0;
                    vy = 0;
                }
                if (sector == 3 && stage == 0)
                {
                    goingLeft(p);
                }
                else if (sector == 3 && stage == 1 && steps > 110)
                {
                    goingDown(p);
                }
                else if (sector == 3 && stage == 2 && steps > 220)
                {
                    goingRight(p);
                }
                else if (sector == 3 && stage == 3 && steps > 270)
                {
                    goingUp(p);
                }
                else if (sector == 3 && stage == 4 && steps > 160)
                {
                    goingRight(p);
                }
                else if (sector == 3 && stage == 5 && steps > 50)
                {
                    vx = 0;
                    vy = 0;
                }
                if (sector == 4 && stage == 0)
                {
                    goingLeft(p);
                }
                else if (sector == 4 && stage == 1 && steps > 70)
                {
                    goingDown(p);
                }
                else if (sector == 4 && stage == 2 && steps > 160)
                {
                    goingLeft(p);
                }
                else if (sector == 4 && stage == 3 && steps > 250)
                {
                    goingUp(p);
                }
                else if (sector == 4 && stage == 4 && steps > 240)
                {
                    goingRight(p);
                }
                else if (sector == 4 && stage == 5 && steps > 110)
                {
                    goingDown(p);
                }
                else if (sector == 4 && stage == 6 && steps > 15)
                {
                    vx = 0;
                    vy = 0;

                }
                if (sector == 5 && stage == 0)
                {
                    goingLeft(p);
                }
                else if (sector == 5 && stage == 1 && steps > 110)
                {
                    goingDown(p);
                }
                else if (sector == 5 && stage == 2 && steps > 240)
                {
                    goingRight(p);
                }
                else if (sector == 5 && stage == 3 && steps > 250)
                {
                    goingUp(p);
                }
                else if (sector == 5 && stage == 4 && steps > 160)
                {
                    goingRight(p);
                }
                else if (sector == 5 && stage == 5 && steps > 50)
                {
                    vx = 0;
                    vy = 0;
                }
                if (sector == 6 && stage == 0)
                {
                    goingLeft(p);
                }
                else if (sector == 6 && stage == 1 && steps > 50)
                {
                    goingDown(p);
                }
                else if (sector == 6 && stage == 2 && steps > 160)
                {
                    goingLeft(p);
                }
                else if (sector == 6 && stage == 3 && steps > 120)
                {
                    goingDown(p);
                }
                else if (sector == 6 && stage == 4 && steps > 140)
                {
                    changeArea(1, p);
                    x = EGraphics.width / 2 + 20;
                }
                else if (sector == 6 && stage == 5 && steps > 120)
                {
                    vx = 0;
                    vy = 0;
                    down = false;
                    up = true;
                }
                if (sector == 7 && stage == 0)
                {
                    goingUp(p);
                }
                else if (sector == 7 && stage == 1 && steps > 120)
                {
                    changeArea(2, p);
                }
                else if (sector == 7 && stage == 2)
                {
                    goingUp(p);
                }
                else if (sector == 7 && stage == 3 && steps > 180)
                {
                    goingLeft(p);
                }
                else if (sector == 7 && stage == 4 && steps > 110)
                {
                    goingUp(p);
                }
                else if (sector == 7 && stage == 5 && steps > 220)
                {
                    goingRight(p);
                }
                else if (sector == 7 && stage == 6 && steps > 70)
                {
                    goingDown(p);
                }
                else if (sector == 7 && stage == 7 && steps > 30)
                {
                    vx = 0;
                    vy = 0;
                }
                if (sector == 8 && stage == 0)
                {
                    goingLeft(p);
                }
                else if (sector == 8 && stage == 1 && steps > 70)
                {
                    goingDown(p);
                }
                else if (sector == 8 && stage == 2 && steps > 220)
                {
                    goingRight(p);
                }
                else if (sector == 8 && stage == 3 && steps > 120)
                {
                    goingDown(p);
                }
                else if (sector == 8 && stage == 4 && steps > 120)
                {
                    changeArea(1, p);
                }

            }
            else if (prisonerNumber == 3)
            {
                if (stage == 0 && sector == 1)
                {
                    if (currTime >= 1500)
                    {
                        stage++;
                    }
                }
                if (stage == 1 && sector == 1) //i am walking right and reached the end of my path steps > 30 &&
                {
                    goingRight(p);
                }
                else if (steps > 30 && stage == 2 && sector == 1) //i am walking down and reached the end 
                {
                    goingDown(p);
                }
                else if (steps > 50 && stage == 3 && sector == 1)//left and end
                {
                    goingLeft(p);

                }
                else if (steps > 100 && stage == 4 && sector == 1)
                {
                    goingUp(p);

                }
                else if (steps > 30 && stage == 5 && sector == 1)
                {
                    vx = 0;
                    vy = 0;
                    steps = 0;
                    stage++;
                    up = false;
                    down = false;
                    left = true;
                    right = false;

                }

                if (sector == 2 && stage == 0)
                {
                    goingLeft(p);
                }
                else if (sector == 2 && stage == 1 && steps > 70)
                {
                    goingUp(p);
                }
                else if (sector == 2 && stage == 2 && steps > 140)
                {
                    changeArea(2, p);
                    stage++;
                    steps = 0;
                }
                else if (sector == 2 && stage == 3)
                {
                    goingUp(p);
                }
                else if (sector == 2 && stage == 4 && steps > 180)
                {
                    goingLeft(p);
                }
                else if (sector == 2 && stage == 5 && steps > 140)
                {
                    goingUp(p);
                }
                else if (sector == 2 && stage == 6 && steps > 80)
                {
                    goingRight(p);
                }
                else if (sector == 2 && stage == 7 && steps > 70)
                {
                    goingUp(p);
                }
                else if (sector == 2 && stage == 8 && steps > 30)
                {
                    vx = 0;
                    vy = 0;
                }
                if (sector == 3 && stage == 0)
                {
                    goingLeft(p);
                }
                else if (sector == 3 && stage == 1 && steps > 70)
                {
                    goingDown(p);
                }
                else if (sector == 3 && stage == 2 && steps > 160)
                {
                    goingRight(p);
                }
                else if (sector == 3 && stage == 3 && steps > 270)
                {
                    goingUp(p);
                }
                else if (sector == 3 && stage == 4 && steps > 220)
                {
                    goingLeft(p);
                }
                else if (sector == 3 && stage == 5 && steps > 100)
                {
                    vx = 0;
                    vy = 0;
                    up = true;
                    down = false;
                    left = false;
                    right = false;

                }
                if (sector == 4 && stage == 0)
                {
                    goingRight(p);
                }
                else if (sector == 4 && stage == 1 && steps > 120)
                {
                    goingDown(p);
                }
                else if (sector == 4 && stage == 2 && steps > 220)
                {
                    goingLeft(p);
                }
                else if (sector == 4 && stage == 3 && steps > 270)
                {
                    goingUp(p);
                }
                else if (sector == 4 && stage == 4 && steps > 150)
                {
                    goingRight(p);
                }
                else if (sector == 4 && stage == 5 && steps > 45)
                {
                    goingUp(p);
                }
                else if (sector == 4 && stage == 6 && steps > 10)
                {
                    vx = 0;
                    vy = 0;

                }
                if (sector == 5 && stage == 0)
                {
                    goingLeft(p);
                }
                else if (sector == 5 && stage == 1 && steps > 70)
                {
                    goingDown(p);
                }
                else if (sector == 5 && stage == 2 && steps > 160)
                {
                    goingRight(p);
                }
                else if (sector == 5 && stage == 3 && steps > 270)
                {
                    goingUp(p);
                }
                else if (sector == 5 && stage == 4 && steps > 220)
                {
                    goingLeft(p);
                }
                else if (sector == 5 && stage == 5 && steps > 100)
                {
                    vx = 0;
                    vy = 0;
                    up = true;
                    left = false;

                }
                if (sector == 6 && stage == 0)
                {
                    goingRight(p);
                }
                else if (sector == 6 && stage == 1 && steps > 100)
                {
                    goingDown(p);
                }
                else if (sector == 6 && stage == 2 && steps > 220)
                {
                    goingLeft(p);
                }
                else if (sector == 6 && stage == 3 && steps > 120)
                {
                    goingDown(p);
                }
                else if (sector == 6 && stage == 4 && steps > 150)
                {
                    changeArea(1, p);
                    x = EGraphics.width / 2 + 20;
                }
                else if (sector == 6 && stage == 5)
                {
                    goingDown(p);
                }
                else if (sector == 6 && stage == 6 && steps > 90)
                {
                    goingRight(p);
                }

                else if (sector == 6 && stage == 7 && steps > 50)
                {
                    goingDown(p);
                }
                else if (sector == 6 && stage == 8 && steps > 60)
                {
                    goingLeft(p);
                }
                else if (sector == 6 && stage == 9 && steps > 50)
                {
                    vx = 0;
                    vy = 0;
                    down = false;
                    up = true;
                }
                if (sector == 7 && stage == 0)
                {
                    goingUp(p);
                }
                else if (sector == 7 && stage == 1 && steps > 170)
                {
                    changeArea(2, p);
                }
                else if (sector == 7 && stage == 2)
                {
                    goingUp(p);
                }
                else if (sector == 7 && stage == 3 && steps > 180)
                {
                    goingLeft(p);
                }
                else if (sector == 7 && stage == 4 && steps > 110)
                {
                    goingUp(p);
                }
                else if (sector == 7 && stage == 5 && steps > 90)
                {
                    goingRight(p);
                }
                else if (sector == 7 && stage == 6 && steps > 40)
                {
                    goingUp(p);
                }
                else if (sector == 7 && stage == 7 && steps > 15)
                {
                    vx = 0;
                    vy = 0;
                }
                if (sector == 8 && stage == 0)
                {
                    goingLeft(p);
                }
                else if (sector == 8 && stage == 1 && steps > 70)
                {
                    goingDown(p);
                }
                else if (sector == 8 && stage == 2 && steps > 220)
                {
                    goingRight(p);
                }
                else if (sector == 8 && stage == 3 && steps > 120)
                {
                    goingDown(p);
                }
                else if (sector == 8 && stage == 4 && steps > 120)
                {
                    changeArea(1, p);
                }
            }
            else if (prisonerNumber == 4)
            {
                if (stage == 0 && sector == 1)
                {
                    if (currTime >= 3000)
                    {
                        stage++;
                    }
                }
                if (stage == 1 && sector == 1) //i am walking right and reached the end of my path steps > 30 &&
                {
                    goingRight(p);
                }
                else if (steps > 30 && stage == 2 && sector == 1) //i am walking down and reached the end 
                {
                    goingDown(p);
                }
                else if (steps > 50 && stage == 3 && sector == 1)//left and end
                {
                    goingLeft(p);

                }
                else if (steps > 100 && stage == 4 && sector == 1)
                {
                    goingUp(p);

                }
                else if (steps > 30 && stage == 5 && sector == 1)
                {
                    vx = 0;
                    vy = 0;
                    steps = 0;
                    stage++;
                    up = false;
                    down = false;
                    left = true;
                    right = false;
                }
                if (sector == 2 && stage == 0)
                {
                    goingLeft(p);
                }
                else if (sector == 2 && stage == 1 && steps > 70)
                {
                    goingUp(p);
                }
                else if (sector == 2 && stage == 2 && steps > 250)
                {
                    changeArea(2, p);
                    stage++;
                    steps = 0;
                }
                else if (sector == 2 && stage == 3)
                {
                    goingUp(p);
                }
                else if (sector == 2 && stage == 4 && steps > 180)
                {
                    goingLeft(p);
                }
                else if (sector == 2 && stage == 5 && steps > 140)
                {
                    goingUp(p);
                }
                else if (sector == 2 && stage == 6 && steps > 80)
                {
                    goingRight(p);
                }
                else if (sector == 2 && stage == 7 && steps > 110)
                {
                    goingUp(p);
                }
                else if (sector == 2 && stage == 8 && steps > 30)
                {
                    vx = 0;
                    vy = 0;
                }
                if (sector == 3 && stage == 0)
                {
                    goingLeft(p);
                }
                else if (sector == 3 && stage == 1 && steps > 80)
                {
                    goingDown(p);
                }
                else if (sector == 3 && stage == 2 && steps > 140)
                {
                    goingRight(p);
                }
                else if (sector == 3 && stage == 3 && steps > 250)
                {
                    goingUp(p);
                }
                else if (sector == 3 && stage == 4 && steps > 155)
                {
                    goingRight(p);
                }
                else if (sector == 3 && stage == 5 && steps > 80)
                {
                    vx = 0;
                    vy = 0;
                    right = false;
                    left = true;
                }
                if (sector == 4 && stage == 0)
                {
                    goingLeft(p);
                }
                else if (sector == 4 && stage == 1 && steps > 70)
                {
                    goingDown(p);
                }
                else if (sector == 4 && stage == 2 && steps > 150)
                {
                    goingLeft(p);
                }
                else if (sector == 4 && stage == 3 && steps > 260)
                {
                    goingUp(p);
                }
                else if (sector == 4 && stage == 4 && steps > 120)
                {
                    goingRight(p);
                }
                else if (sector == 4 && stage == 5 && steps > 80)
                {
                    goingUp(p);
                }
                else if (sector == 4 && stage == 6 && steps > 20)
                {
                    vx = 0;
                    vy = 0;

                }
                if (sector == 5 && stage == 0)
                {
                    goingLeft(p);
                }
                else if (sector == 5 && stage == 1 && steps > 110)
                {
                    goingDown(p);
                }
                else if (sector == 5 && stage == 2 && steps > 120)
                {
                    goingRight(p);
                }
                else if (sector == 5 && stage == 3 && steps > 250)
                {
                    goingUp(p);
                }
                else if (sector == 5 && stage == 4 && steps > 110)
                {
                    goingRight(p);
                }
                else if (sector == 5 && stage == 5 && steps > 100)
                {
                    vx = 0;
                    vy = 0;
                    left = true;
                    right = false;
                }
                if (sector == 6 && stage == 0)
                {
                    goingLeft(p);
                }
                else if (sector == 6 && stage == 1 && steps > 70)
                {
                    goingDown(p);
                }
                else if (sector == 6 && stage == 2 && steps > 160)
                {
                    goingLeft(p);
                }
                else if (sector == 6 && stage == 3 && steps > 120)
                {
                    goingDown(p);
                }
                else if (sector == 6 && stage == 4 && steps > 140)
                {
                    changeArea(1, p);
                }
                else if (sector == 6 && stage == 5)
                {
                    goingDown(p);
                }
                else if (sector == 6 && stage == 6 && steps > 90)
                {
                    goingLeft(p);
                }
                else if (sector == 6 && stage == 7 && steps > 50)
                {
                    goingDown(p);
                }
                else if (sector == 6 && stage == 8 && steps > 60)
                {
                    goingRight(p);
                }
                else if (sector == 6 && stage == 9 && steps > 50)
                {
                    vx = 0;
                    vy = 0;
                    down = false;
                    up = true;
                }

                if (sector == 7 && stage == 0)
                {
                    goingUp(p);
                }
                else if (sector == 7 && stage == 1 && steps > 120)
                {
                    changeArea(2, p);
                    y = EGraphics.height - 70;
                }
                else if (sector == 7 && stage == 2)
                {
                    goingUp(p);
                }
                else if (sector == 7 && stage == 3 && steps > 180)
                {
                    goingLeft(p);
                }
                else if (sector == 7 && stage == 4 && steps > 110)
                {
                    goingUp(p);
                }
                else if (sector == 7 && stage == 5 && steps > 60)
                {
                    goingRight(p);
                }
                else if (sector == 7 && stage == 6 && steps > 80)
                {
                    goingUp(p);
                }
                else if (sector == 7 && stage == 7 && steps > 10)
                {
                    vx = 0;
                    vy = 0;
                }

                if (sector == 8 && stage == 0)
                {
                    goingLeft(p);
                }
                else if (sector == 8 && stage == 1 && steps > 70)
                {
                    goingDown(p);
                }
                else if (sector == 8 && stage == 2 && steps > 220)
                {
                    goingRight(p);
                }
                else if (sector == 8 && stage == 3 && steps > 120)
                {
                    goingDown(p);
                }
                else if (sector == 8 && stage == 4 && steps > 120)
                {
                    changeArea(1, p);
                }
            }

            if (prisonerNumber == 1)
            {
                if (sector == 8 && stage == 5)
                {
                    goingDown(p);
                    x = (EGraphics.width / 2 - 30);
                }
                else if (sector == 8 && stage == 6 && steps > 250)
                {
                    goingLeft(p);

                }
                else if (sector == 8 && stage == 7 && steps > 170)
                {
                    goingUp(p);

                }
                else if (sector == 8 && stage == 8 && steps > 30)
                {
                    vx = 0;
                    vy = 0;
                    up = false;
                    right = true;

                }
            }
            if (prisonerNumber == 2)
            {
                if (sector == 8 && stage == 5)
                {
                    goingDown(p);
                    y = 60;
                    x = (EGraphics.width / 2 - 30);

                }
                else if (sector == 8 && stage == 6 && steps > 350)
                {
                    goingLeft(p);

                }
                else if (sector == 8 && stage == 7 && steps > 170)
                {
                    goingUp(p);

                }
                else if (sector == 8 && stage == 8 && steps > 30)
                {
                    vx = 0;
                    vy = 0;
                    up = false;
                    right = true;

                }
            }
            if (prisonerNumber == 3)
            {
                if (sector == 8 && stage == 5)
                {
                    goingDown(p);
                    x = (EGraphics.width / 2 + 30);

                }
                else if (sector == 8 && stage == 6 && steps > 100)
                {
                    goingRight(p);

                }
                else if (sector == 8 && stage == 7 && steps > 130)
                {
                    goingUp(p);

                }
                else if (sector == 8 && stage == 8 && steps > 40)
                {
                    vx = 0;
                    vy = 0;
                    up = false;
                    left = true;

                }
            }
            if (prisonerNumber == 4)
            {
                if (sector == 8 && stage == 5)
                {
                    goingDown(p);
                    y = 30;
                    x = (EGraphics.width / 2 + 30);

                }
                else if (sector == 8 && stage == 6 && steps > 250)
                {
                    goingRight(p);

                }
                else if (sector == 8 && stage == 7 && steps > 130)
                {
                    goingUp(p);

                }
                else if (sector == 8 && stage == 8 && steps > 40)
                {
                    vx = 0;
                    vy = 0;
                    up = false;
                    left = true;

                }
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

    
    public void changeArea(int a, Player p)
    {
        if (a == 1)
        {
            if (prisonerNumber != 1 || prisonerNumber != 4)
                x = EGraphics.width / 2 - 30;
            else
                x = EGraphics.width / 2;
            if (sector == 8 && prisonerNumber == 3)
                y = 60;
            else
                y = 30;
            goingDown(p);
        }
        else if (a == 2)
        {
            if (prisonerNumber != 1)
                x = EGraphics.width / 2;
            else
                x = EGraphics.width / 2 - 30;
            y = EGraphics.height - 40;
            goingUp(p);
        }
        currArea = a;
    }
    //draw self

    public void drawSelf(Graphics g, int area)
    {
        Graphics2D g2d = (Graphics2D) g;

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
            str = "Ugh I hate it here";
        }
        else if (rng < .40)
        {
            str = "I'm so tired";
        }
        else
            str = "What's up";
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
    //objects
    //accessors
    public void moveOutOfWay(Player p)
    {

    }

    public void moveBack(Player p)
    {

    }
    public void increaseSector()
    {
        sector++;
        stage = 0;
    }
    public void setSector(int i)
    {
        sector = i;
    }

    public void resetSector()
    {
        sector = 1;
    }
    public void setNight(boolean o)
    {
         if(o)
         {
             x = 700;
             y = 700;
         }
         else if(!o)
         {
             x = preX;
             y = preY;
         }
    }
    public boolean getTalking()
    {
        return talking;
    }

    public void stopTalking()
    {
        talking = false;
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

    public boolean getIfFocused()
    {
        return focusedPris;
    }

    public void setIfFocused(boolean o)
    {
        focusedPris = o;
    }

    public boolean getShopOpen()
    {
        return shopGUIScreen;
    }

    public boolean getOpenMission()
    {
        return missionGUIScreen;
    }

    public void setOpenShop(boolean o)
    {
        shopGUIScreen = o;
    }

    public void setOpenMission(boolean o)
    {
        missionGUIScreen = o;
    }

    public boolean getIFGuard()
    {
        return false;
    }

}
//make a mission prisoner
//make a shop prisoner

