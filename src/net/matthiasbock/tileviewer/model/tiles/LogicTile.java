package net.matthiasbock.tileviewer.model.tiles;

import net.matthiasbock.tileviewer.model.cells.LogicCell;

/**
 * This class represents the logic cells within an FPGA 
 */
public class LogicTile extends Tile
{
    private boolean hasAdditionalCarryInCell = true;

    /**
     * Initialize new logic tile with parameters and optionally with logic cells
     * 
     * @param numCells: Number of logic cells to create in this tile
     * @param hasAdditionalCarryInCell: Whether or not this tile has a carry-in cell
     */
    public LogicTile(int numCells, boolean hasAdditionalCarryInCell)
    {
        // is there a carry-in cell in this tile? 
        this.hasAdditionalCarryInCell = hasAdditionalCarryInCell;
        
        // populate this tile's cell list with new instances of logic cells
        for (int i=0; i<numCells; i++)
        {
            cells.add(new LogicCell());
        }
    }
}
