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
    protected ETileType type = ETileType.EMPTY;
    protected List<LogicCell> cells = new ArrayList<>();

    public ETileType getType()
    {
        return type;
    }
    
    public void setType(ETileType type)
    {
        this.type = type;
    }
}
