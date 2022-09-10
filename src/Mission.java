import java.awt.Font;
public class Mission
{
    private int x;
    private int y;
    private String missionStatus;
    private int pay;
    
    
    public Mission(int xCoor, int yCoor, int price)
    {
        x = xCoor;
        y = yCoor;
        missionStatus = "ACCEPT";
        pay = price;
    }
    
    public void acceptMission()
    {
        missionStatus = "ACCEPTED";
        x-=15;
    }
    
    public void completeMission(Player p)
    {
        missionStatus = "COMPLETED";
        x-=5;
        p.earnMoney(pay);
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    public String getMS()
    {
        return missionStatus;
    }
}