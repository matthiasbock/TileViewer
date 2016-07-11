package model.packaging;

/**
 * This class holds all information about a single physical pin or pad
 */
public class Pin
{
    // the pin number; counting begins with 1 
    private int pinNumber = -1;
    
    // the I/O bank, this pin belongs to
    private String bank = null;
    
    // the type of this pin
    private EPinType type = null;
    
    // the name of the specific net, this pin represents
    private String net = null;

    
    /*
     * Getters and Setters
     */
    
    public int getPinNumber()
    {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber)
    {
        this.pinNumber = pinNumber;
    }

    public String getBank()
    {
        return bank;
    }

    public void setBank(String bank)
    {
        this.bank = bank;
    }

    public EPinType getType()
    {
        return type;
    }

    public void setType(EPinType type)
    {
        this.type = type;
    }

    public String getNet()
    {
        return net;
    }

    public void setNet(String net)
    {
        this.net = net;
    }
}
