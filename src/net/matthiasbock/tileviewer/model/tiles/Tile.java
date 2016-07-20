package net.matthiasbock.tileviewer.model.tiles;

import java.util.ArrayList;
import java.util.List;

import net.matthiasbock.tileviewer.model.cells.LogicCell;

/**
 * A superclass for the tiles,
 * our FPGA consists of,
 * with attributes and methods,
 * which apply to all tiles,
 * no matter the type
 */
public class Tile
{
    protected ETileType type = ETileType.EMPTY;
    protected List<LogicCell> cells = new ArrayList<>();
    
    protected int rowspan = 1;
    protected int colspan = 1;

    /*
     * Getters and Setters 
     */
    
    public ETileType getType()
    {
        return type;
    }
    
    public void setType(ETileType type)
    {
        this.type = type;
    }

    public int getRowspan()
    {
        return rowspan;
    }

    public void setRowspan(int rowspan)
    {
        this.rowspan = rowspan;
    }

    public int getColspan()
    {
        return colspan;
    }

    public void setColspan(int colspan)
    {
        this.colspan = colspan;
    }
}
