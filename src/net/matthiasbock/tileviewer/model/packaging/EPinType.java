package net.matthiasbock.tileviewer.model.packaging;

public enum EPinType
{
    NC,         // Not Connected 
    DPIO,
    PIO,        // Programmable In/Out
    SPI,        // Serial Peripheral Interface
    GBIN,       // Global Input
    VCC,        // Supply voltage for core logic
    GND,        // Ground
    VCCIO,      // Supply voltage for an I/O bank
    VCCPLL,     // Supply voltage for a Phase Locked Loop
    GNDPLL,     // Ground for a Phase Locked Loop
    VPP,        // Supply voltage for EEPROM or NVCM programming and operation
}
