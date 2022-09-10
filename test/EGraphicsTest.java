/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sigi
 */
public class EGraphicsTest
{
    
    public EGraphicsTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of keyPressed method, of class EGraphics.
     */
    @Test
    public void testKeyPressed()
    {
        System.out.println("keyPressed");
        KeyEvent e = null;
        EGraphics instance = new EGraphics();
        instance.keyPressed(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paintComponent method, of class EGraphics.
     */
    @Test
    public void testPaintComponent()
    {
        System.out.println("paintComponent");
        Graphics g = null;
        EGraphics instance = new EGraphics();
        instance.paintComponent(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leaveArea method, of class EGraphics.
     */
    @Test
    public void testLeaveArea()
    {
        System.out.println("leaveArea");
        EGraphics instance = new EGraphics();
        instance.leaveArea();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loop method, of class EGraphics.
     */
    @Test
    public void testLoop()
    {
        System.out.println("loop");
        EGraphics instance = new EGraphics();
        instance.loop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of keyTyped method, of class EGraphics.
     */
    @Test
    public void testKeyTyped()
    {
        System.out.println("keyTyped");
        KeyEvent e = null;
        EGraphics instance = new EGraphics();
        instance.keyTyped(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of keyReleased method, of class EGraphics.
     */
    @Test
    public void testKeyReleased()
    {
        System.out.println("keyReleased");
        KeyEvent e = null;
        EGraphics instance = new EGraphics();
        instance.keyReleased(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mousePressed method, of class EGraphics.
     */
    @Test
    public void testMousePressed()
    {
        System.out.println("mousePressed");
        MouseEvent e = null;
        EGraphics instance = new EGraphics();
        instance.mousePressed(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseReleased method, of class EGraphics.
     */
    @Test
    public void testMouseReleased()
    {
        System.out.println("mouseReleased");
        MouseEvent e = null;
        EGraphics instance = new EGraphics();
        instance.mouseReleased(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseClicked method, of class EGraphics.
     */
    @Test
    public void testMouseClicked()
    {
        System.out.println("mouseClicked");
        MouseEvent e = null;
        EGraphics instance = new EGraphics();
        instance.mouseClicked(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseEntered method, of class EGraphics.
     */
    @Test
    public void testMouseEntered()
    {
        System.out.println("mouseEntered");
        MouseEvent e = null;
        EGraphics instance = new EGraphics();
        instance.mouseEntered(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseExited method, of class EGraphics.
     */
    @Test
    public void testMouseExited()
    {
        System.out.println("mouseExited");
        MouseEvent e = null;
        EGraphics instance = new EGraphics();
        instance.mouseExited(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseMoved method, of class EGraphics.
     */
    @Test
    public void testMouseMoved()
    {
        System.out.println("mouseMoved");
        MouseEvent e = null;
        EGraphics instance = new EGraphics();
        instance.mouseMoved(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseDragged method, of class EGraphics.
     */
    @Test
    public void testMouseDragged()
    {
        System.out.println("mouseDragged");
        MouseEvent e = null;
        EGraphics instance = new EGraphics();
        instance.mouseDragged(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class EGraphics.
     */
    @Test
    public void testStart()
    {
        System.out.println("start");
        int ticks = 0;
        EGraphics instance = new EGraphics();
        instance.start(ticks);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTime method, of class EGraphics.
     */
    @Test
    public void testGetTime()
    {
        System.out.println("getTime");
        EGraphics instance = new EGraphics();
        long expResult = 0L;
        long result = instance.getTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of distance method, of class EGraphics.
     */
    @Test
    public void testDistance()
    {
        System.out.println("distance");
        int x1 = 0;
        int x2 = 0;
        int y1 = 0;
        int y2 = 0;
        double expResult = 0.0;
        double result = EGraphics.distance(x1, x2, y1, y2);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShopOpen method, of class EGraphics.
     */
    @Test
    public void testGetShopOpen()
    {
        System.out.println("getShopOpen");
        EGraphics instance = new EGraphics();
        boolean expResult = false;
        boolean result = instance.getShopOpen();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOpenMission method, of class EGraphics.
     */
    @Test
    public void testGetOpenMission()
    {
        System.out.println("getOpenMission");
        EGraphics instance = new EGraphics();
        boolean expResult = false;
        boolean result = instance.getOpenMission();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOpenShop method, of class EGraphics.
     */
    @Test
    public void testSetOpenShop()
    {
        System.out.println("setOpenShop");
        boolean o = false;
        EGraphics instance = new EGraphics();
        instance.setOpenShop(o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOpenMission method, of class EGraphics.
     */
    @Test
    public void testSetOpenMission()
    {
        System.out.println("setOpenMission");
        boolean o = false;
        EGraphics instance = new EGraphics();
        instance.setOpenMission(o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStorageOpen method, of class EGraphics.
     */
    @Test
    public void testSetStorageOpen()
    {
        System.out.println("setStorageOpen");
        boolean o = false;
        EGraphics instance = new EGraphics();
        instance.setStorageOpen(o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrMission method, of class EGraphics.
     */
    @Test
    public void testGetCurrMission()
    {
        System.out.println("getCurrMission");
        EGraphics instance = new EGraphics();
        int expResult = 0;
        int result = instance.getCurrMission();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrMission method, of class EGraphics.
     */
    @Test
    public void testSetCurrMission()
    {
        System.out.println("setCurrMission");
        int i = 0;
        EGraphics instance = new EGraphics();
        instance.setCurrMission(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMissionAccepted method, of class EGraphics.
     */
    @Test
    public void testSetMissionAccepted()
    {
        System.out.println("setMissionAccepted");
        int o = 0;
        EGraphics instance = new EGraphics();
        instance.setMissionAccepted(o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of completeMission method, of class EGraphics.
     */
    @Test
    public void testCompleteMission()
    {
        System.out.println("completeMission");
        int i = 0;
        EGraphics instance = new EGraphics();
        instance.completeMission(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endGame method, of class EGraphics.
     */
    @Test
    public void testEndGame()
    {
        System.out.println("endGame");
        int i = 0;
        EGraphics instance = new EGraphics();
        instance.endGame(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class EGraphics.
     */
    @Test
    public void testMain()
    {
        System.out.println("main");
        String[] args = null;
        EGraphics.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
