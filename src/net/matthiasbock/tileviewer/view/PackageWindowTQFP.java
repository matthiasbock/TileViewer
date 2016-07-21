package net.matthiasbock.tileviewer.view;

import java.awt.Color;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.matthiasbock.tileviewer.model.FPGA;
import net.matthiasbock.tileviewer.model.packaging.Pin;
import net.matthiasbock.tileviewer.model.packaging.Package;

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
    
    private int pinWidth = 12;
    private int pinHeight = 12;

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
        this.setBounds(605, 10, 600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // layout null ist required for arbitrary panel positioning
        this.getContentPane().setLayout(null);
        
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
        int count = 0;
        if (pinNumber <= (count += pinCount[0]))
            return 0;
        if (pinNumber <= (count += pinCount[1]))
            return 1;
        if (pinNumber <= (count += pinCount[2]))
            return 2;
        if (pinNumber <= (count += pinCount[3]))
            return 3;
        
        // invalid pin number
        return -1;
    }
    
    public Point calculatePinLocation(Pin pin)
    {
        int edge = getEdgeByPinNumber(pin.getPinNumber());
        
        int bottomRightX = (pinCount[1]+1)*pinWidth;
        int bottomRightY = (pinCount[0]+1)*pinHeight;
        
        switch (edge)
        {
            case 0: // left
                return new Point(0, pin.getPinNumber()*pinHeight);

            case 1: // bottom
                return new Point((pin.getPinNumber()-pinCount[0])*pinWidth, (pinCount[0]+1)*pinHeight);
                
            case 2: // right
                return new Point(bottomRightX, bottomRightY - (pin.getPinNumber()-pinCount[0]-pinCount[1])*pinHeight);

            case 3: // top
                return new Point(bottomRightX - (pin.getPinNumber()-pinCount[0]-pinCount[1]-pinCount[2])*pinWidth, 0);
                
            default: // illegal edge
                System.out.printf("Invalid edge: %d (pin %d)\n", edge, pin.getPinNumber());
                break;
        }
        
        // we should never arrive here
        return new Point(0,0);
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
            System.out.printf("\tPin %d: Bank %s, Type %s, Net %s\n", pin.getPinNumber(), pin.getBank(), pin.getType(), pin.getNet());
            
            //if (pin.getType() == EPinType.)
            JPanel panel = new JPanel();
            
            panel.setSize(pinWidth, pinHeight);
            panel.setLocation( calculatePinLocation(pin) );
            panel.setBorder( BorderFactory.createLineBorder(Color.BLACK, 1) );
            panel.setBackground(Color.BLUE);
            
            this.add(panel);
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
