package net.matthiasbock.tileviewer.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import net.matthiasbock.libpcf.model.PcfFile;
import net.matthiasbock.tileviewer.model.FPGA;
import net.matthiasbock.tileviewer.view.PackageWindowTQFP;
import net.matthiasbock.tileviewer.view.TileWindow;

import com.google.gson.Gson;

/**
 * Main class of the TileViewer project
 * 
 * Execute this class's main method to run TileViewer 
 */
public class Main
{
    /**
     * Import FPGA structure from JSON file
     * and visualize/display parsed information
     */
    public static void main(String[] args)
    {
        // import FPGA structure
        FPGA fpga = importFPGAfromFile("fpgas/Lattice/iCE40-HX1K-TQFP144.json");
        System.out.printf("FPGA has %dx%d tiles.\n", fpga.getSizeX(), fpga.getSizeY());

        // import tile usage from PCF
        PcfFile pcf = importPcf("test.pcf");
        System.out.printf("Physical Constraints File defines nets on %d I/Os and %d locations.\n", pcf.getIos().size(), pcf.getLocations().size());
        
        // display FPGA tile architecture
        TileWindow tileWindow = new TileWindow(fpga);
        //tileWindow.highlightUsedTiles(pcf);
        
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
            System.out.printf("File not found: %s\n", filename);
            System.exit(1);
        }

        // initialize Google's JSON parser
        Gson parser = new Gson();
        
        // create new object with attributes parsed from JSON file
        FPGA fpga = parser.fromJson(reader, FPGA.class);
        //System.out.println(parser.toJson(fpga));
        
        return fpga;
    }
    
    /**
     * Import Lattice Semiconductor Physical Constraints File
     * 
     * @param filename: File to import from
     * @return: Populated instance of class PcfFile
     */
    public static PcfFile importPcf(String filename)
    {
        // open file for reading
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e)
        {
            System.out.printf("File not found: %s\n", filename);
            System.exit(1);
        }
        
        // read and parse line by line
        PcfFile pcf = new PcfFile();
        try
        {
            String line;
            int i = 1;
            while ((line = reader.readLine()) != null)
            {
                pcf.addLine(line, i);
                i++;
            }
        }
        catch (IOException e)
        {
            System.out.printf("Error: Could not read from %s\n", filename);
        }
        
        return pcf;
    }
}
