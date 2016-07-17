package net.matthiasbock.tileviewer.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import net.matthiasbock.tileviewer.model.FPGA;
import net.matthiasbock.tileviewer.view.PackageWindowTQFP;
import net.matthiasbock.tileviewer.view.TileWindow;

import com.google.gson.Gson;

public class Main
{
    /**
     * Import FPGA structure from JSON file
     * and visualize/display parsed information
     */
    public static void main(String[] args)
    {
        // import FPGA from file
        FPGA fpga = importFPGAfromFile("fpgas/Lattice/iCE40-HX1K-TQFP144.json");
        
        System.out.printf("FPGA has %dx%d tiles.\n", fpga.getSizeX(), fpga.getSizeY());
        
        // display FPGA tile architecture
        TileWindow tileWindow = new TileWindow(fpga);
        
        // display FPGA package
        PackageWindowTQFP packageWindow = new PackageWindowTQFP(fpga);
    }

    /**
     * Import FPGA tile matrix from JSON file
     * 
     * @param filename: Full path to file to import from
     * @return: New instance of class FPGA
     */
    public static FPGA importFPGAfromFile(String filename)
    {
        // open file for reading
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.out.printf("Sorry, opening file \"%s\" failed.\n", filename);
            System.exit(1);
        }

        // initialize Google's JSON parser
        Gson parser = new Gson();
        
        // create new object with attributes parsed from JSON file
        FPGA fpga = parser.fromJson(reader, FPGA.class);
        
        //System.out.println(parser.toJson(fpga));
        
        return fpga;
    }
}
