package net.matthiasbock.tileviewer.model;

import java.util.ArrayList;
import java.util.List;

import net.matthiasbock.tileviewer.model.tiles.Tile;
import net.matthiasbock.tileviewer.model.packaging.Package;

/**
 * Hold all information about an FPGA,
 * which are relevant for displaying it's architecture 
 */
public class FPGA
{
    // variables required for JSON import
    // set by Gson using reflection
    private int sizeX = 0;
    private int sizeY = 0;

    // matrix of tiles
    private List<List<Tile>> tiles = null;

    // physical IC package
    private Package chipPackage = null;
    
    /**
     * Initialize FPGA as matrix of tiles
     * 
     * @param sizeX: Horizontal number of tiles 
     * @param sizeY: Vertical number of tiles
     */
    public FPGA(int sizeX, int sizeY)
    {
        // initialize tile matrix
        tiles = new ArrayList<>();
        
        // create all rows
        for (int y=0; y<sizeY; y++)
        {
            List<Tile> row = new ArrayList<>();
            tiles.add(row);

            // fill row with empty columns
            for (int x=0; x<sizeX; x++)
            {
                row.add(null);
            }
        }
    }
    
    
    /*
     * Getters and Setters 
     */
    
    /**
     * Retrieve a tile from this FPGA
     * Counting begins with zero
     * 
     * @param x: Vertical coordinate
     * @param y: Horizontal coordinate
     * @return: Tile at desired coordinate
     */
    public Tile getTile(int x, int y)
    {
        return tiles.get(y).get(x);
    }
    
    /**
     * Set the tile a the specified coordinates.
     * You must provide an object link to an existing instance of a tile type.
     * 
     * @param x: Vertical coordinate
     * @param y: Horizontal coordinate
     * @param t: Tile to set at these coordinates
     */
    public void setTile(int x, int y, Tile t)
    {
        tiles.get(y).set(x, t);
    }
    
    public int getSizeX()
    {
        if (tiles == null || tiles.size() < 1)
            return 0;
        return tiles.get(0).size();
    }

    public int getSizeY()
    {
        if (tiles == null)
            return 0;
        return tiles.size();
    }

    public Package getPackage()
    {
        return chipPackage;
    }

    public void setPackage(Package chipPackage)
    {
        this.chipPackage = chipPackage;
    }
}
