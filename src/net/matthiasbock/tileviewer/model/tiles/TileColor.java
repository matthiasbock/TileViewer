package net.matthiasbock.tileviewer.model.tiles;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * Define, which tile type has which color
 */
public class TileColor
{
    public static Map<ETileType, Color> colorByType = new HashMap<>();
    static
    {
        colorByType.put(ETileType.LOGIC, Color.DARK_GRAY);
        colorByType.put(ETileType.IO, Color.BLUE);
        colorByType.put(ETileType.RAM, Color.RED);
        colorByType.put(ETileType.WARMBOOT, Color.ORANGE);
    }
    
    public static Color byType(ETileType type)
    {
        return colorByType.get(type);
    }
}
