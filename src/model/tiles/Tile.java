package model.tiles;

import java.lang.Character.UnicodeScript;
import java.util.ArrayList;
import java.util.List;

import model.cells.LogicCell;

/**
 * A superclass for the tiles,
 * our FPGA consists of,
 * with attributes and methods,
 * which apply to all tiles,
 * no matter the type
 */
public class Tile
{
    protected ETileType tileType = ETileType.EMPTY;
    protected List<LogicCell> cells = new ArrayList<>();
    
    /**
     * Set the type of this tile
     * 
     * @param type: Type to set
     */
    public void setTileType(ETileType type)
    {
        tileType = type;
    }
}
