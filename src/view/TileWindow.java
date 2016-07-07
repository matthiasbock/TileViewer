package view;

import javax.swing.JFrame;

import model.FPGA;

/**
 * This class represents a SWING window,
 * in which the tiles of an FPGA are visualized
 */
public class TileWindow extends JFrame
{
    private static final long serialVersionUID = 1L;

    private FPGA fpga = null;
    
    /**
     * Create window for given FPGA
     * 
     * @param fpga: FPGA to visualize
     */
    public TileWindow(FPGA fpga)
    {
        this.fpga = fpga;
        
        // TODO: visualize all tiles
    }
}
