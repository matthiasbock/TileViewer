package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.FPGA;
import model.tiles.TileColor;

/**
 * This class represents a SWING window,
 * in which the tiles of an FPGA are visualized
 */
public class TileWindow extends JFrame
{
    private static final long serialVersionUID = 1L;

    // the FPGA to visualize
    private FPGA fpga = null;
    
    // the matrix of tiles
    private List<List<JPanel>> tileBoxes = null;
    
    // preferred attributes 
    private int tileOffsetX = 10;
    private int tileOffsetY = 10;
    private int tileWidth = 12;
    private int tileHeight = 20;
    private int tileSpacingX = 2;
    private int tileSpacingY = 2;
    
    /**
     * Create window for FPGA visualization
     */
    public TileWindow()
    {
        // JFrame constructor
        super();
        
        this.setTitle("Tile viewer");
        
        this.setLocationRelativeTo(null);
        this.setBounds(300, 10, 300, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setVisible(true);
    }

    /**
     * Create window for FPGA visualization
     * 
     * @param fpga: FPGA to visualize
     */
    public TileWindow(FPGA fpga)
    {
        // create window
        this();
        
        setFPGA(fpga);
        createTileBoxes();
    }

    /**
     * Create a 2x2 matrix of JPanels on this window,
     * one panel per tile
     */
    public void createTileBoxes()
    {
        // layout null ist required for arbitrary panel positioning
        this.getContentPane().setLayout(null);

        // adjust window size
        int minWidth = 2*tileOffsetX + (this.getFPGA().getSizeX()*(tileWidth+tileSpacingX));
        int width = (int) this.getSize().getWidth(); 
        if (width < minWidth)
        {
            width = minWidth;
        }

        int minHeight = 2*tileOffsetY + (this.getFPGA().getSizeY()*(tileHeight+tileSpacingY));
        int height = (int) this.getSize().getHeight(); 
        if (height < minHeight)
        {
            height = minHeight;
        }
        
        this.setSize(width, height);
//        this.setResizable(false);

        // initialize matrix
        tileBoxes = new ArrayList<>();
        
        // fill with panels
        for (int y=0; y<fpga.getSizeY(); y++)
        {
            // new row as array of panels
            List<JPanel> row = new ArrayList<>();
            tileBoxes.add(row);
            
            for (int x=0; x<fpga.getSizeX(); x++)
            {
                // new tile
                JPanel panel = new JPanel();

                // set position
                panel.setSize(tileWidth, tileHeight);
                panel.setLocation(tileOffsetX + x*(tileWidth+tileSpacingX), minHeight - (tileOffsetY + (y+1)*tileHeight + y*tileSpacingY));

                // style according to tile type
                if (fpga.getTile(x, y) != null)
                {
                    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                    panel.setBackground(TileColor.byType(fpga.getTile(x, y).getType()));
                }
                
                // remember object link
                row.add(panel);
                
                // show
                this.add(panel);
            }
        }
        
        // necessary
        this.repaint();
    }
    
    
    /*
     * Getters and Setters
     */
    
    public FPGA getFPGA()
    {
        return this.fpga;
    }
    
    public void setFPGA(FPGA fpga)
    {
        this.fpga = fpga;
    }
    
    public JPanel getPanel(int x, int y)
    {
        return tileBoxes.get(y).get(x);
    }
}
