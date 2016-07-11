package model.packaging;

public enum EPinType
{
    NC,         // Not Connected 
    DPIO,
    PIO,        // Programmable In/Out
    CONFIG,
    SPI,        // Serial Peripheral Interface
    GBIN,       // Global Input
    VCC,        // Supply voltage
    GND,        // Ground
    VCCIO,      // Supply voltage for I/O bank
    VCCPLL,     // Supply voltage for Phase Locked Loop
    GNDPLL,     // Ground for Phase Locked Loop
    VPP,        // Supply voltage for EEPROM or NVCM programming and operation
}
