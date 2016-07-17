package net.matthiasbock.tileviewer.model.packaging;

import java.util.List;

/**
 * This class holds all information about the IC package
 * our FPGA ships in
 */
public class Package
{
    private EPackageType type = null;

    private int pinCount = 0;
    
    // List of all pins in this package
    // Attention:
    //  pin counting begins with 1
    //  list counting begins with 0
    private List<Pin> pins = null;

    
    /*
     *  Getters and Setters
     */
    
    public EPackageType getType()
    {
        return type;
    }

    public void setType(EPackageType type)
    {
        this.type = type;
    }

    public int getPinCount()
    {
        return pinCount;
    }

    public void setPinCount(int pinCount)
    {
        this.pinCount = pinCount;
    }

    public List<Pin> getPins()
    {
        return pins;
    }

    public void setPins(List<Pin> pins)
    {
        this.pins = pins;
    }
}
