package controller;

import model.FPGA;
import view.TileWindow;

public class Main
{
    public static void main(String[] args)
    {
        // import FPGA from file
        FPGA fpga = importFPGAfromFile("FPGAs/Lattice/iCE40-HX1K-TQFP144.json");
        
        // display FPGA tile architecture
        TileWindow window = new TileWindow(fpga);
    }

    /**
     * Import FPGA tile matrix from JSON file
     * 
     * @param filename: Full path to file to import from
     * @return: New instance of class FPGA
     */
    public static FPGA importFPGAfromFile(String filename)
    {
        // TODO: BufferedReader FileStream ...
        return new FPGA(2, 5);
    }
}
