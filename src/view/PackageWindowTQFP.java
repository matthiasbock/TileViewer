package view;

import javax.swing.JFrame;

import model.FPGA;
import model.packaging.Package;
import model.packaging.Pin;

/**
 * This class visualizes a TQFP package
 */
public class PackageWindowTQFP extends JFrame
{
    private static final long serialVersionUID = 1L;

    // the FPGA to visualize
    private FPGA fpga = null;

    // number of pins on the four edges of the package
    // in this order: left, bottom, right, top edge
    private int[] pinCount = {0, 0, 0, 0};

    /**
     * Initialize package viewer window
     * 
     * @param pinsLeft: Pins on the left edge of the package
     * @param pinsBottom: Pins on the bottom edge of the package
     * @param pinsRight: Pins on the right edge of the package
     * @param pinsTop: Pins on the top edge of the package
     */
    public PackageWindowTQFP(int pinsLeft, int pinsBottom, int pinsRight, int pinsTop)
    {
        // JFrame constructor
        super();

        setPinCount(pinsLeft, pinsBottom, pinsRight, pinsTop);

        // show window
        this.init();
    }
    
    /**
     * Initialize package viewer window
     * 
     * @param pinCount: Total number of package pins, equivalent pin count on each edge
     */
    public PackageWindowTQFP(int totalPinCount)
    {
        // JFrame constructor
        super();

        setPinCount(totalPinCount);

        // show window
        this.init();
    }
    
    /**
     * Initialize package viewer window
     * 
     * @param fpga: FPGA to visualize
     */
    public PackageWindowTQFP(FPGA fpga)
    {
        // JFrame constructor
        super();
        
        setFPGA(fpga);
                
        Package p = fpga.getPackage();
        if (p == null)
        {
            System.out.println("FPGA package is not defined. Not opening Package viewer.");
            return;
        }
        setPinCount( p.getPinCount() );
        
        // show window
        this.init();
    }

    /**
     * Draw this JFrame and all the elements it's containing
     */
    public void init()
    {
        this.setTitle("Package viewer");
        
        this.setLocationRelativeTo(null);
        this.setBounds(605, 10, 300, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        drawPins();
        
        this.setVisible(true);
    }
    
    /**
     * Returns the index of the edge, on which the pin with specified number is located
     * 
     * @param pinNumber: Number of the pin to locate; counting starts with 1
     * @return: Edge by index: 0=left, 1=bottom, 2=right, 3=top
     */
    public int getEdgeByPinNumber(int pinNumber)
    {
        if (pinNumber <= pinCount[0])
            return 0;
        if (pinNumber <= pinCount[0]+pinCount[1])
            return 1;
        if (pinNumber <= pinCount[0]+pinCount[1]+pinCount[2])
            return 2;
        if (pinNumber <= pinCount[0]+pinCount[1]+pinCount[2]+pinCount[3])
            return 3;
        
        // invalid pin number
        return -1;
    }
    
    /**
     * Draw all pins of this package to the window
     */
    public void drawPins()
    {
        System.out.printf("Total number of pins in package: %d\n", fpga.getPackage().getPinCount());
        System.out.printf("Number of pins defined in the configuration file: %d\n", fpga.getPackage().getPins().size());
        
        for (Pin pin : getFPGA().getPackage().getPins())
        {
            int edge = getEdgeByPinNumber(pin.getPinNumber());
            System.out.printf("Pin %d: Edge %d\n", pin.getPinNumber(), edge);
        }
    }
    
    
    /*
     * getters and setters
     */

    public int[] getPinCount()
    {
        return pinCount;
    }

    public void setPinCount(int[] pinCountArray)
    {
        this.pinCount = pinCountArray;
    }
    
    public void setPinCount(int pinsLeft, int pinsBottom, int pinsRight, int pinsTop)
    {
        // save pin counts
        pinCount[0] = pinsLeft;
        pinCount[1] = pinsBottom;
        pinCount[2] = pinsRight;
        pinCount[3] = pinsTop;
    }
    
    public void setPinCount(int edgeIndex, int edgePinCount)
    {
        this.pinCount[edgeIndex] = edgePinCount;
    }
    
    public void setPinCount(int totalPinCount)
    {
        setPinCount(totalPinCount/4, totalPinCount/4, totalPinCount/4, totalPinCount/4);
    }

    public FPGA getFPGA()
    {
        return fpga;
    }

    public void setFPGA(FPGA fpga)
    {
        this.fpga = fpga;
    }
}
