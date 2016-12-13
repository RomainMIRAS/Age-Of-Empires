package com.andrei1058.ageofempire.locations;

public class Schematic
{

    private short[] blocks;
    private byte[] data;
    private short width;
    private short lenght;
    private short height;

    public Schematic(short[] blocks, byte[] data, short width, short lenght, short height)
    {
        this.blocks = blocks;
        this.data = data;
        this.width = width;
        this.lenght = lenght;
        this.height = height;
    }

    public short[] getBlocks()
    {
        return blocks;
    }

    public byte[] getData()
    {
        return data;
    }

    public short getWidth()
    {
        return width;
    }

    public short getLenght()
    {
        return lenght;
    }

    public short getHeight()
    {
        return height;
    }
}