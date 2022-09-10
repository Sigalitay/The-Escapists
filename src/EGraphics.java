
//all accessors and mutators have been put at the bottom of each file

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class EGraphics extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{

    //instance variables
    static final int width = 600;
    static final int height = 450;
    static final int magicNumber = 30;
    private ImageIcon endGameScreenImage;
    private Image endGameScreen;
    private Circle circle;
    private Player player;
    private Object objArray[];
    private Guard npcArray[];

    private boolean introScreen;
    private boolean shopGUIScreen;
    private boolean missionGUIScreen;
    private boolean storageScreen;
    private boolean instructions;
    private int area;
    private boolean vents;

    private boolean nextDay;
    private long startTime;
    private boolean afternoon;
    private String event;

    private Mission misArray[];
    private Shop shopArray[];
    private int missionAccepted;
    private int currMission;

    private boolean endgame;
    private int endScreen;

    //Default Constructor
    public EGraphics()
    {
        //initializing instance variables

        player = new Player("Jill");
        circle = new Circle();
        objArray = new Object[15];
        npcArray = new Guard[9];

        shopGUIScreen = false;
        missionGUIScreen = false;
        vents = false;
        storageScreen = false;
        area = 1;//initial area
        nextDay = false;
        introScreen = true;
        instructions = false;

        endgame = false;

        npcArray[0] = new Guard(width / 2 - 30, 10, "Jonah", 1, 1);
        npcArray[1] = new Guard(width / 2, 10, "John", 1, 2);
        npcArray[6] = new Guard(95, 30, "Johnny", 2, 4);
        npcArray[7] = new Guard(550, 30, "Jonathon", 2, 3);
        npcArray[8] = new Guard(150, height - 40, "Jxon", 2, 0);
        npcArray[2] = new Prisoner(110, 240, "James", 1, 1);
        npcArray[3] = new Prisoner(110, 370, "Monty", 1, 2);
        npcArray[4] = new Prisoner(455, 120, "Jessie", 1, 3);
        npcArray[5] = new Prisoner(455, 240, "Banner", 1, 4);

        //tables aka objects
        objArray[0] = new Object(40, 120, true, 1);
        objArray[1] = new Object(40, 240, true, 1);
        objArray[2] = new Object(40, 360, true, 1);
        objArray[3] = new Object(width - 60, 240, true, 1);
        objArray[4] = new Object(width - 60, 120, true, 1);

        //toilets
        objArray[5] = new Toilet(10, 120, 1);
        objArray[6] = new Toilet(10, 240, 1);
        objArray[7] = new Toilet(10, 360, 1);
        objArray[8] = new Toilet(width - 30, 240, 1);
        objArray[9] = new Toilet(width - 30, 120, 1);

        //beds
        objArray[10] = new Bed(140, 120, 1, "Jill");
        objArray[11] = new Bed(140, 240, 1, "James");
        objArray[12] = new Bed(140, 365, 1, "Monty");
        objArray[13] = new Bed(width - 175, 120, 1, "Jessie");
        objArray[14] = new Bed(width - 175, 240, 1, "Banner");

        startTime = System.currentTimeMillis();
        afternoon = false;
        event = "Wake Up";

        missionAccepted = 0;
        misArray = new Mission[4];
        misArray[0] = new Mission(160, 200, 150);
        misArray[1] = new Mission(360, 200, 170);
        misArray[2] = new Mission(160, 350, 200);
        misArray[3] = new Mission(360, 350, 400);
        currMission = -1;

        shopArray = new Shop[4];
        shopArray[0] = new Shop(150, 75, 150, 1);
        shopArray[1] = new Shop(335, 75, 150, 2);
        shopArray[3] = new Shop(150, 220, 350, 3);
        shopArray[2] = new Shop(335, 220, 200, 4);

        //Setting up the GUI
        JFrame gui = new JFrame(); //This makes the gui box
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes sure program can close
        gui.setTitle("The Escapsists"); //This is the title of the game, you can change it
        gui.setPreferredSize(new Dimension(width, height + 22)); //Setting the size for gui
        gui.setResizable(false); //Makes it so the gui cant be resized
        gui.getContentPane().add(this); //Adding this class to the gui

        gui.pack(); //Packs everything together
        gui.setLocationRelativeTo(null); //Makes so the gui opens in the center of screen
        gui.setVisible(true); //Makes the gui visible
        gui.addKeyListener(this);//stating that this object will listen to the keyboard
        gui.addMouseListener(this); //stating that this object will listen to the Mouse
        gui.addMouseMotionListener(this); //stating that this object will acknowledge when the Mouse moves

    }

    //This method will acknowledge user input
    public void keyPressed(KeyEvent e)
    {
        //getting the key pressed
        int key = e.getKeyCode();
        //System.out.println(key);
        int velocity = 5;

//if you standing in front of it AND it can be picked up AND you press e(change to b later)
        if (introScreen == false)
        {
            if (instructions == false)
            {
                if (vents == false)
                {
                    if (storageScreen == false)
                    {
                        if (missionGUIScreen == false)
                        {
                            if (shopGUIScreen == false)
                            {
                                //moving the player
                                if (key == 87 || key == 38)//up
                                {
                                    //player.setVx(0);

                                    player.setVy(-velocity);
                                    player.setDirection("U");
                                }
                                if (key == 68 || key == 39)//right
                                {
                                    player.setVx(velocity);
                                    player.setDirection("R");
                                }
                                if (key == 83 || key == 40)//down
                                {

                                    player.setDirection("D");
                                    // player.setVx(0);
                                    player.setVy(velocity);

                                }
                                if (key == 65 || key == 37)//left
                                {

                                    player.setDirection("L");
                                    player.setVx(-velocity);
                                }

                                if (key == 66)//b
                                {

                                    if (!player.holdingObject())
                                        player.isObjectInfrontOfMe(objArray, area);
                                    else
                                        player.dropObject();
                                }

                            }
                            else
                            {
                                //buttons for shop GUI
                            }
                        }
                        else
                        {

                        }
                    }
                    if (key == 32)
                    {
                        //not holding anything
                        //in front of desk
                        //is a desk
                        if (!player.holdingObject())
                        {
                            if (player.senseV2(npcArray, objArray, this).indexOf("Object") > -1)
                            {
                                String str = player.senseV2(npcArray, objArray, this);
                                int len = str.length();
                                String p1 = str.substring(len - 1);
                                int p2 = Integer.parseInt(p1);
                                player.getOnTop(objArray[p2], str, width, height, objArray);

                            }
                        }
                    }
                    if (key == 88)
                    {
                        if (area == 1)
                        {
                            player.setX(100);
                            player.setY(150);
                        }
                        else
                        {
                            player.setX(300);
                            player.setY(300);
                        }
                    }
                    if (key == 70)
                    {
                        if (player.getIfOnTop())
                        {

                            for (int i = 0; i < objArray.length; i++)
                            {
                                if (objArray[i].getXCoor() <= 50 && objArray[i].getXCoor() >= 10 && objArray[i].getYCoor() <= 205 && objArray[i].getYCoor() >= 150)
                                {
                                    vents = true;
                                    player.getOffTop();
                                }
                                else if (objArray[i].getXCoor() <= 50 && objArray[i].getXCoor() >= 10 && objArray[i].getYCoor() <= 330 && objArray[i].getYCoor() >= 255)
                                {
                                    vents = true;
                                    player.getOffTop();
                                }
                                else if (objArray[i].getXCoor() <= 50 && objArray[i].getXCoor() >= 10 && objArray[i].getYCoor() <= 430 && objArray[i].getYCoor() >= 360)
                                {
                                    vents = true;
                                    player.getOffTop();
                                }
                                else if (objArray[i].getXCoor() <= 600 && objArray[i].getXCoor() >= 560 && objArray[i].getYCoor() <= 205 && objArray[i].getYCoor() >= 150)
                                {
                                    vents = true;
                                    player.getOffTop();
                                }
                                else if (objArray[i].getXCoor() <= 600 && objArray[i].getXCoor() >= 560 && objArray[i].getYCoor() <= 220 && objArray[i].getYCoor() >= 255)
                                {
                                    vents = true;
                                    player.getOffTop();
                                }

                            }
                        }
                    }
                    //interact key
                    if (key == 69)//e
                    {

                        if (storageScreen == true)
                        {
                            setStorageOpen(false);
                            for (int f = 0; f < objArray.length; f++)
                            {
                                if (objArray[f].getIfFocused())
                                {
                                    objArray[f].setIfFocused(false);
                                    objArray[f].closeStorage();
                                }
                            }
                        }
                        else if (shopGUIScreen == true)
                        {
                            setOpenShop(false);
                            for (int f = 0; f < npcArray.length; f++)
                            {
                                if (npcArray[f].getIfFocused())
                                {
                                    npcArray[f].setIfFocused(false);
                                    npcArray[f].setOpenShop(false);
                                }
                            }
                        }
                        else if (missionGUIScreen == true)
                        {
                            setOpenMission(false);
                            for (int f = 0; f < npcArray.length; f++)
                            {
                                if (npcArray[f].getIfFocused())
                                {
                                    npcArray[f].setIfFocused(false);
                                    npcArray[f].setOpenMission(false);
                                }
                            }
                        }
                        else
                        {
                            player.sense(npcArray, objArray, this);

                            if (objArray[10].getPlayerSleeping())
                            {
                                startTime = System.currentTimeMillis();
                                afternoon = false;

                                for (int i = 10; i < objArray.length; i++)
                                {

                                    objArray[i].setNight(false);
                                    player.setX(100);
                                    player.setY(150);
                                    nextDay = true;
                                }

                            }

                        }

                    }
                }
                else
                {
                    //vents controls
                    if (key == 87 || key == 38)//up
                    {
                        //circle.setVx(0);
                        circle.setYCoor(-27);

                    }
                    if (key == 68 || key == 39)//right
                    {
                        circle.setXCoor(36);

                    }
                    if (key == 83 || key == 40)//down
                    {

                        // circle.setVx(0);
                        circle.setYCoor(27);

                    }
                    if (key == 65 || key == 37)//left
                    {
                        circle.setXCoor(-36);
                    }

                    if (key == 70)
                    {
                        int cx = circle.getX();
                        int cy = circle.getY();
                        if (cx == 102 && cy == 137 && player.getInven().getItemType() == 3)
                        {
                            endGame(2);
                        }

                        else if (cx == 354 && cy == 218)
                        {
                            endGame(3);
                        }
                        else if (cx == 498 && cy == 137)
                        {
                            endGame(3);
                        }
                        else if (cx == 282 && cy == 137)
                        {
                            System.out.println("6");
                            endGame(3);
                        }
                    }

                }
            }
        }
        else
        {
            if (key > 0)
            {
                introScreen = false;
                instructions = true;
            }
        }

    }

    public void paintComponent(Graphics g)
    {
        long currTime = System.currentTimeMillis() - startTime;

        long interval = currTime / 2000;//dividing by 1000 makes it so the time changes once every SECOND   because 1000 ms in a second

        Graphics2D g2d = (Graphics2D) g;
        ImageIcon backgroundimage = new ImageIcon(EGraphics.class.getResource("background2.png"));
        Image background = backgroundimage.getImage();
        ImageIcon backgroundimage2 = new ImageIcon(EGraphics.class.getResource("Cafe.png"));
        Image background2 = backgroundimage2.getImage();
        ImageIcon missionImage = new ImageIcon(EGraphics.class.getResource("missionScreen.png"));
        Image missionScreen = missionImage.getImage();
        ImageIcon shopScreenImage = new ImageIcon(EGraphics.class.getResource("shopScreen.png"));
        Image shopScreen = shopScreenImage.getImage();
        ImageIcon lunchTableImg = new ImageIcon(EGraphics.class.getResource("lunchtable.png"));
        Image lunchtable = lunchTableImg.getImage();
        ImageIcon ventsImage = new ImageIcon(EGraphics.class.getResource("Vents.png"));
        Image vent = ventsImage.getImage();
        ImageIcon nextDayImage = new ImageIcon(EGraphics.class.getResource("nextDay.png"));
        Image nextDaything = nextDayImage.getImage();
        ImageIcon introScreenImage = new ImageIcon(EGraphics.class.getResource("introScreen.png"));
        Image introScreenThing = introScreenImage.getImage();
        ImageIcon instructionsImage = new ImageIcon(EGraphics.class.getResource("instructions.png"));
        Image instruct = instructionsImage.getImage();
        ImageIcon questionImage = new ImageIcon(EGraphics.class.getResource("question.png"));
        Image question = questionImage.getImage();

        //prisoner
        if (introScreen == false)
        {
            if (instructions == false)
            {
                if (endgame == false)
                {
                    if (vents == false)
                    {
                        if (storageScreen == false)
                        {
                            if (missionGUIScreen == false)
                            {
                                if (shopGUIScreen == false)
                                {

                                    if (area == 1)//first level
                                    {
                                        //Background
                                        g2d.drawImage(background, 0, 0, width, height, null);
                                        long currTime2 = System.currentTimeMillis() - startTime;
                                        if (nextDay)
                                        {

                                            g2d.drawImage(nextDaything, width / 3, height / 3, 200, 200, null);
                                            if (currTime2 > 1000)
                                                nextDay = false;
                                        }

                                        //drawing the  exit door, not really important jsut so i can trace what is happening
                                        g.setColor(Color.black);
                                        g.fillRect(width / 2 - 50, 0, 100, 50);

                                        player.drawSelf(g);

                                    }
                                    else if (area == 2)
                                    {
                                        //Background
                                        g2d.drawImage(background2, 0, 0, width, height, null);

                                        //lunchtable
                                        g2d.drawImage(lunchtable, 190, 45, 150, 120, null);
                                        g2d.drawImage(lunchtable, -20, 45, 150, 120, null);

                                        //drawing the  exit door, not really important jsut so i can trace what is happening
                                        g.setColor(Color.black);
                                        g.fillRect(width / 2 - 50, height - 40, 100, 50);

                                    }
                                    for (int i = 0; i < objArray.length; i++)
                                    {
                                        objArray[i].drawSelf(g, area);
                                    }
                                    for (int i = 0; i < npcArray.length; i++)
                                    {
                                        npcArray[i].drawSelf(g, area);

                                    }

                                    //EVERYTHING TIME
                                    //hotbar
                                    g.setColor(Color.LIGHT_GRAY);
                                    g.fillRect(width - 70, height - 90, 50, 50);
                                    g2d.drawImage(question, width - 40, height - 35, 20,20, null);
                                    //information deck
                                    g.setColor(Color.LIGHT_GRAY);
                                    g.fillRect(width - 170, height - 110, 90, 90);
                                    player.drawSelf(g);
                                    //drawing/calculacting the time
                                    g.setColor(Color.BLACK);
                                    Font f = new Font("Stone Sans Sem ITC TT", Font.PLAIN, 20);
                                    g.setFont(f);
                                    long min = (interval * 15) % 60;

                                    String time;

                                    long hour = (interval / 4) % 12;

                                    //do an if-statement so when hour is 0 it shows 12.
                                    if (hour >= 0 && hour < 12)
                                    {
                                        hour = hour + 7;//if you want to change the time you start change the 8

                                    }
                                    if (hour > 12)
                                    {
                                        hour = hour - 12;

                                    }
                                    if (min == 0)
                                        time = hour + ":00";
                                    else
                                        time = hour + ":" + min;
                                    String timeFrame;

                                    if (afternoon)
                                    {
                                        timeFrame = "pm";
                                    }
                                    else
                                    {
                                        timeFrame = "am";
                                    }

                                    if (objArray[10].getNight() && objArray[10].getPlayerSleeping() && (hour >= 10))// && afternoon == true
                                    {
                                        hour = 0;
                                        afternoon = false;
                                        for (int i = 10; i < objArray.length; i++)
                                        {
                                            objArray[i].setNight(false);

                                        }
                                        objArray[10].setPlayerSleeping(false);
                                        player.setX(100);
                                        player.setY(150);

                                    }

                                    g.drawString(time, width - 170, height - 130 + 40);
                                    if (hour < 10)
                                        g.drawString(timeFrame, width - 120, height - 130 + 40);
                                    else
                                        g.drawString(timeFrame, width - 112, height - 130 + 40);
                                    g.drawString("$" + player.getMoney(), width - 170, height - 100 + 40);

                                    if (hour >= 6 && hour < 8 && event.equals("Roll Call") && afternoon == false)
                                    {

                                        for (int i = 0; i < npcArray.length; i++)
                                        {
                                            if (!npcArray[i].getIFGuard())
                                                npcArray[i].drawSelf(g, 1);

                                        }

                                        npcArray[1].rollCall(objArray, this);
                                    }
                                    if (hour < 8 && hour >= 7 && afternoon == false)
                                    {
                                        player.setLockdown(false);

                                    }

                                    if (hour < 9 && hour >= 8 && afternoon == false)
                                    {
                                        event = "Roll Call";
                                        npcArray[1].rollCall(objArray, this);

                                    }
                                    else if ((hour < 11 && hour >= 9) && afternoon == false && event.equals("Roll Call"))
                                    {

                                        event = "Breakfast";
                                        for (int i = 0; i < npcArray.length; i++)
                                        {
                                            npcArray[i].increaseSector();
                                        }
                                        npcArray[1].resetRNG();
                                    }
                                    else if ((hour >= 11) && (hour < 12) && afternoon == false && event.equals("Breakfast"))
                                    {
                                        event = "Yardtime";
                                        for (int i = 0; i < npcArray.length; i++)
                                        {

                                            npcArray[i].increaseSector();
                                        }

                                    }
                                    else if ((hour == 12) && afternoon == false && event.equals("Yardtime"))
                                    {
                                        afternoon = true;
                                    }
                                    else if ((hour >= 2) && (hour < 4) && afternoon == true && event.equals("Yardtime"))
                                    {
                                        event = "Lunch";
                                        for (int i = 0; i < npcArray.length; i++)
                                        {
                                            npcArray[i].increaseSector();
                                        }

                                    }
                                    else if ((hour >= 4) && (hour < 6) && afternoon == true && event.equals("Lunch"))
                                    {
                                        event = "Yardtime";
                                        for (int i = 0; i < npcArray.length; i++)
                                        {
                                            npcArray[i].increaseSector();
                                        }
                                    }
                                    else if ((hour >= 6) && (hour < 7) && afternoon == true && event.equals("Yardtime"))
                                    {
                                        event = "Roll Call";
                                        for (int i = 0; i < npcArray.length; i++)
                                        {
                                            npcArray[i].increaseSector();
                                        }
                                        npcArray[1].rollCall(objArray, this);

                                    }
                                    else if ((hour >= 7) && (hour < 8) && afternoon == true && event.equals("Roll Call"))
                                    {

                                        npcArray[1].rollCall(objArray, this);

                                    }

                                    else if ((hour >= 8) && (hour < 10) && afternoon == true && event.equals("Roll Call"))
                                    {
                                        event = "Dinner";
                                        for (int i = 0; i < npcArray.length; i++)
                                        {

                                            npcArray[i].increaseSector();

                                        }
                                        npcArray[1].resetRNG();

                                    }
                                    else if ((hour >= 10) && (hour < 11) && afternoon == true && event.equals("Dinner"))
                                    {
                                        event = "Bedtime";
                                        for (int i = 0; i < npcArray.length; i++)
                                        {
                                            npcArray[i].increaseSector();
                                        }
                                        player.setLockdown(true);
                                        npcArray[1].resetRNG();
                                    }
                                    else if (hour >= 11 && hour < 11 && afternoon == true && event.equals("Bedtime"))
                                    {
                                        player.setLockdown(true);
                                    }

                                    else if (event.equals("Bedtime") && hour == 11 && min == 15)
                                    {
                                        for (int i = 0; i < objArray.length; i++)
                                        {
                                            objArray[i].setNight(true);

                                        }
                                        for (int i = 0; i < npcArray.length; i++)
                                        {
                                            npcArray[i].setNight(true);

                                        }
                                    }
                                    else if (hour == 12 && afternoon == true && event.equals("Bedtime"))
                                    {
                                        afternoon = false;
                                    }

                                    g.drawString(event, width - 170, height - 70 + 40);

                                    //making info deck pretty
                                    g.fillRect(width - 180, height - 130 + 45, 100, 5);
                                    g.fillRect(width - 180, height - 130 + 75, 100, 5);
                                    if (area == 1)
                                    {
                                        g.fillRect(175, 160, 5, 50);
                                        g.fillRect(175, 275, 5, 50);
                                        g.fillRect(175, 400, 5, 50);
                                        g.fillRect(width - 185, 160, 5, 50);
                                        g.fillRect(width - 185, 280, 5, 50);
                                    }

                                }
                                else
                                {
                                    //Background
                                    g2d.drawImage(shopScreen, 0, 0, width, height, null);
                                    for (int i = 0; i < shopArray.length; i++)
                                    {
                                        shopArray[i].drawSelf(g);
                                    }
                                    g.setColor(Color.BLACK);
                                    Font f = new Font("Didot", Font.BOLD, 20);
                                    g.setFont(f);
                                    g.drawString(shopArray[0].getIS(), shopArray[0].getWordX(), shopArray[0].getY() + 125);
                                    g.drawString(shopArray[1].getIS(), shopArray[1].getWordX(), shopArray[1].getY() + 125);
                                    g.drawString(shopArray[2].getIS(), shopArray[2].getWordX(), shopArray[2].getY() + 125);
                                    g.drawString(shopArray[3].getIS(), shopArray[3].getWordX(), shopArray[3].getY() + 125);

                                }
                            }
                            else
                            {
                                //mission screen
                                g2d.drawImage(missionScreen, 0, 0, width, height, null);
                                g.setColor(Color.BLACK);
                                Font f = new Font("Didot", Font.BOLD, 20);
                                g.setFont(f);

                                //set into an if statement
                                g.drawString("Missions Accepted: " + missionAccepted + "/1", width - 220, 20);

                                //mission 1
                                g.drawString("Find a pencil", 140, 110);
                                g.drawString("and give it ", 140, 135);
                                g.drawString("to me. - $150", 140, 160);
                                g.drawString(misArray[0].getMS(), misArray[0].getX(), misArray[0].getY());

                                //mission 2
                                g.drawString("  Give James", 340, 110);
                                g.drawString("      a note", 340, 135);
                                g.drawString("      $170", 340, 160);
                                g.drawString(misArray[1].getMS(), misArray[1].getX(), misArray[1].getY());

                                //mission 3
                                g.drawString("Stand in the", 140, 260);
                                g.drawString("puddle of...", 140, 285);
                                g.drawString("you know - $200", 140, 310);
                                g.drawString(misArray[2].getMS(), misArray[2].getX(), misArray[2].getY());

                                //mission 4
                                g.drawString("Get me some", 338, 260);
                                g.drawString("wirecutters", 338, 285);
                                g.drawString("       $400", 338, 310);
                                g.drawString(misArray[3].getMS(), misArray[3].getX(), misArray[3].getY());

                            }
                        }
                        else
                        {
                            for (int f = 0; f < objArray.length; f++)
                            {
                                if (objArray[f].getIfFocused())
                                {
                                    objArray[f].drawInsideSelf(g, area, player);
                                }
                            }
                        }

                    }
                    else
                    {
                        g2d.drawImage(vent, 0, 0, width, height, null);
                        circle.drawSelf(g);

                    }
                }
                else
                {

                    if (endScreen == 1)
                        endGameScreenImage = new ImageIcon(EGraphics.class.getResource("dirtyCopEnding.png"));
                    else if (endScreen == 2)
                    {
                        endGameScreenImage = new ImageIcon(EGraphics.class.getResource("ventsEscape.png"));
                    }
                    else if (endScreen == 3)
                    {
                        endGameScreenImage = new ImageIcon(EGraphics.class.getResource("failedVentsEscape.png"));
                    }
                    else
                        endGameScreenImage = new ImageIcon(EGraphics.class.getResource("contrabandFound.png"));

                    Image endGameScreen = endGameScreenImage.getImage();
                    g2d.drawImage(endGameScreen, 0, 0, width, height, null);

                }
            }
            else
            {
                g2d.drawImage(instruct, 0, 0, width, height, null);
            }
        }

        else
        {
            g2d.drawImage(introScreenThing, 0, 0, width, height, null);

        }
    }

    public void leaveArea()
    {
        if (area == 1)
        {
            if (player.getYCoor() <= 0 && player.getXCoor() >= width / 2 - 50 && player.getXCoor() <= width / 2 + 50)
            {

                area = 2;

                player.moveToArea(area);
            }
        }
        else if (area == 2)
        {
            if (player.getYCoor() >= height - 40 && player.getXCoor() >= width / 2 - 50 && player.getXCoor() <= width / 2 + 50)
            {
                area = 1;

                player.moveToArea(area);
            }
        }
    }

    public void loop()
    {
        leaveArea();
        if (introScreen == false)
        {
            if (vents == false)
            {
                if (storageScreen == false)
                {
                    if (missionGUIScreen == false)
                    {
                        if (shopGUIScreen == false)
                        {
                            boolean collidedWObj = false;
                            if (player.getVy() != 0 || player.getVx() != 0)
                            {
                                for (int i = 0; i < npcArray.length; i++)
                                {
                                    player.handleCollisionGuard(npcArray[i]);
                                }

                                collidedWObj = player.detectCollision(objArray, area);
                            }

                            if (!collidedWObj)
                                player.movement(width, height, this);

                            for (int i = 0; i < npcArray.length; i++)
                            {
                                npcArray[i].handleCollisionO(objArray);
                            }

                            for (int i = 0; i < npcArray.length; i++)
                            {
                                npcArray[i].act(width, height, player);
                            }

                        }
                        else
                        {

                        }
                    }
                    else
                    {

                    }
                }
                else
                {

                }
                for (int i = 0; i < objArray.length; i++)
                {
                    if (objArray[i].getStorage())
                    {
                        objArray[i].setIfFocused(true);
                        setStorageOpen(true);
                    }

                }
                for (int i = 0; i < npcArray.length; i++)
                {
                    if (npcArray[i].getOpenMission())
                    {
                        npcArray[i].setIfFocused(true);
                        setOpenMission(true);
                    }

                }
                for (int i = 0; i < npcArray.length; i++)
                {
                    if (npcArray[i].getShopOpen())
                    {
                        npcArray[i].setIfFocused(true);
                        setOpenShop(true);
                    }

                }
            }
        }

        //Do not write below this
        repaint();
    }
//These methods are required by the compiler.  
//You might write code in these methods depending on your goal.

    public void keyTyped(KeyEvent e)
    {
    }

    public void keyReleased(KeyEvent e)
    {
        //getting the key pressed
        int key = e.getKeyCode();

//if you standing in front of it AND it can be picked up AND you press e(change to b later)
        if (storageScreen == false)
        {
            if (missionGUIScreen == false)
            {
                if (shopGUIScreen == false)
                {

                    if (key == 87 || key == 38)//up
                    {
                        //player.setVx(0);
                        player.setVy(0);

                    }
                    if (key == 68 || key == 39)//right
                    {
                        player.setVx(0);

                    }
                    if (key == 83 || key == 40)//down
                    {

                        // player.setVx(0);
                        player.setVy(0);

                    }
                    if (key == 65 || key == 37)//left
                    {

                        player.setVx(0);

                    }

                }
            }
        }
    }

    public void mousePressed(MouseEvent e)
    {
        //storage methods
    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseClicked(MouseEvent e)
    {
        int clickedX = e.getX();
        int clickedY = e.getY();
//        System.out.println("--===---");
//        System.out.println(clickedX);
//        System.out.println(clickedY);
//        System.out.println("--===---");
        if (missionGUIScreen)//MISSION GUI CONTROLS
        {
            if (missionAccepted == 0)
            {
                if (clickedX <= 279 && clickedX >= 136 && clickedY <= 226 && clickedY >= 206)//box 1  top left
                {
                    misArray[0].acceptMission();
                    currMission = 0;
                }
                else if (clickedX <= 477 && clickedX >= 334 && clickedY <= 226 && clickedY >= 206)// box 2 top right
                {
                    misArray[1].acceptMission();
                    currMission = 1;
                }
                else if (clickedX <= 477 && clickedX >= 334 && clickedY <= 373 && clickedY >= 354)//box 3 bottom right
                {
                    misArray[3].acceptMission();
                    currMission = 3;
                }
                else if (clickedX <= 279 && clickedX >= 136 && clickedY <= 373 && clickedY >= 354)//box 4 bottom left
                {
                    misArray[2].acceptMission();
                    currMission = 2;
                }
                missionAccepted++;
            }

        }
        else if (shopGUIScreen)//SHOP GUI CONTROLS
        {
            if (player.getInven().getItemType() == 0)
            {

                if (clickedX <= 284 && clickedX >= 148 && clickedY <= 226 && clickedY >= 202)//box 1  top left
                {

                    if (!shopArray[0].getSoldOut())
                    {
                        shopArray[0].buy(player);
                    }
                }
                else if (clickedX <= 477 && clickedX >= 334 && clickedY <= 226 && clickedY >= 201)// box 2 top right
                {
                    if (!shopArray[1].getSoldOut())
                    {
                        shopArray[1].buy(player);
                    }
                }
                else if (clickedX <= 284 && clickedX >= 148 && clickedY <= 371 && clickedY >= 347)//box 3 bottom right
                {
                    if (!shopArray[3].getSoldOut())
                    {
                        shopArray[3].buy(player);
                    }
                }
                else if (clickedX <= 477 && clickedX >= 334 && clickedY <= 372 && clickedY >= 347)//box 4 bottom left
                {
                    if (!shopArray[2].getSoldOut())
                    {
                        shopArray[2].buy(player);
                    }
                }
            }
        }
        else if (storageScreen)//STORAGE CONTROLS
        {
            Object focused = new Object();
            for (int i = 0; i < objArray.length; i++)
            {

                if (objArray[i].getIfFocused())
                {
                    focused = objArray[i];
                }
            }

            if (focused.objType())
            {
                if (player.getInven().getItemType() == 0)
                {
                    if (clickedX <= 160 && clickedX >= 65 && clickedY <= 300 && clickedY >= 230)
                    {
                        player.getInven().setItemType(focused.getInvenArray0().getItemType());
                        focused.getInvenArray0().setItemType(0);
                    }
                    else if (clickedX <= 290 && clickedX >= 200 && clickedY <= 300 && clickedY >= 230)
                    {
                        player.getInven().setItemType(focused.getInvenArray1().getItemType());
                        focused.getInvenArray1().setItemType(0);
                    }
                    else if (clickedX <= 410 && clickedX >= 310 && clickedY <= 300 && clickedY >= 230)
                    {
                        player.getInven().setItemType(focused.getInvenArray2().getItemType());
                        focused.getInvenArray2().setItemType(0);
                    }
                    else if (clickedX <= 540 && clickedX >= 450 && clickedY <= 300 && clickedY >= 230)
                    {
                        player.getInven().setItemType(focused.getInvenArray3().getItemType());
                        focused.getInvenArray3().setItemType(0);
                    }
                }
                else if (clickedX <= 350 && clickedX >= 260 && clickedY <= 120 && clickedY >= 50)
                {
                    if (focused.getInvenArray0().getItemType() == 0)
                    {
                        focused.getInvenArray0().setItemType(player.getInven().getItemType());
                        player.getInven().setItemType(0);
                    }

                    else if (focused.getInvenArray1().getItemType() == 0)
                    {
                        focused.getInvenArray1().setItemType(player.getInven().getItemType());
                        player.getInven().setItemType(0);
                    }
                    else if (focused.getInvenArray2().getItemType() == 0)
                    {
                        focused.getInvenArray2().setItemType(player.getInven().getItemType());
                        player.getInven().setItemType(0);
                    }
                    else if (focused.getInvenArray3().getItemType() == 0)
                    {
                        focused.getInvenArray3().setItemType(player.getInven().getItemType());
                        player.getInven().setItemType(0);
                    }
                }

            }
            else if (!focused.objType())
            {
                if (clickedX <= 200 && clickedX >= 15 && clickedY <= 90 && clickedY >= 35)
                {
                    focused.getInvenArray0().setItemType(0);
                }
                if (player.getInven().getItemType() == 0)
                {
                    if (clickedX <= 385 && clickedX >= 250 && clickedY <= 210 && clickedY >= 120)
                    {
                        player.getInven().setItemType(focused.getInvenArray0().getItemType());
                        focused.getInvenArray0().setItemType(0);
                    }

                }
                else if (clickedX <= 560 && clickedX >= 450 && clickedY <= 450 && clickedY >= 380)
                {
                    if (focused.getInvenArray0().getItemType() == 0)
                    {
                        focused.getInvenArray0().setItemType(player.getInven().getItemType());
                        player.getInven().setItemType(0);
                    }

                }

            }

        }
        else if(instructions)
        {
             if (clickedX <= 570 && clickedX >= 530 && clickedY <= 65 && clickedY >= 30)
             {
                 instructions = false;
             }
        }
        else if(clickedX <= 580 && clickedX >= 560 && clickedY <= 455 && clickedY >= 440)
        {
            instructions = true;
        }
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mouseMoved(MouseEvent e)
    {
    }

    public void mouseDragged(MouseEvent e)
    {
    }

    public void start(final int ticks)
    {
        Thread gameThread = new Thread()
        {
            public void run()
            {
                while (true)
                {
                    loop();
                    try
                    {
                        Thread.sleep(1000 / ticks);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        gameThread.start();
    }

    public long getTime()
    {
        return startTime;
    }

    public static double distance(int x1, int x2, int y1, int y2)
    {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
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

    public void setStorageOpen(boolean o)
    {
        storageScreen = o;
    }

    public int getCurrMission()
    {
        return currMission;
    }

    public void setCurrMission(int i)
    {
        currMission = i;
    }

    public void setMissionAccepted(int o)
    {
        missionAccepted = o;
    }

    public void completeMission(int i)
    {
        misArray[i].completeMission(player);
    }

    public void endGame(int i)
    {
        endgame = true;
        endScreen = i;

        if (i == 1)
            endGameScreenImage = new ImageIcon(EGraphics.class.getResource("dirtyCopEnding.png"));
        else if (i == 2)
        {
            endGameScreenImage = new ImageIcon(EGraphics.class.getResource("ventsEscape.png"));
        }
        else if (i == 3)
        {
            System.out.println("22");
            endGameScreenImage = new ImageIcon(EGraphics.class.getResource("failedVentsEscape.png"));
        }
        else
            endGameScreenImage = new ImageIcon(EGraphics.class.getResource("contrabandFound.png"));

        Image endGameScreen = endGameScreenImage.getImage();
    }

    public static void main(String[] args)
    {
        EGraphics g = new EGraphics();
        g.start(60);
    }
}
