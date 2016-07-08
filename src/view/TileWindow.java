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
    private static final int TILE_WIDTH = 5; 
    private static final int TILE_HEIGHT = 5; 
    
    /**
     * Create window for FPGA visualization
     */
    public TileWindow()
    {
        super();
        
        this.setTitle("FPGA tiles");
        
        this.setLocationRelativeTo(null);
        this.setBounds(100, 100, 600, 250);
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
        // window as grid with appropriate size
        this.getContentPane().setLayout(new GridLayout(fpga.getSizeY(), fpga.getSizeX()));

        // initialize matrix
        tileBoxes = new ArrayList<>();
        
        // fill with panels
        for (int y=0; y<fpga.getSizeY(); y++)
        {
            List<JPanel> row = new ArrayList<>();
            tileBoxes.add(row);
            
            for (int x=0; x<fpga.getSizeX(); x++)
            {
                JPanel panel = new JPanel();
                row.add(panel);
//                panel.setBackground(Color.BLUE);
                panel.setBounds(x, y, 1, 1);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                
                this.add(panel);
            }
        }
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
