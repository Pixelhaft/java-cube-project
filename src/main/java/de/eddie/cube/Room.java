package de.eddie.cube;

public class Room{
    private int[] roomNumber;
    private int x, y, z;
    private boolean aExit;

    public Room (int[] roomNumber, int x, int y, int z){
        this.roomNumber = roomNumber;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int[] getRoomNumber(){
        return roomNumber;
    }
}
