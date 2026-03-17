package de.eddie.cube;

public class Room{
    private int[] roomNumber;
    private int x, y, z;
    private boolean aExit;

    public Room( int[] roomNumber, int x, int y, int z ){
        this.roomNumber = roomNumber;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int[] getRoomNumber(){
        return roomNumber;
    }

    public boolean isPrime( int n ){
        if( n < 2 )
            return false;

        for( int i = 2; i*i <= n; i++){
            if( n % i == 0 ) return false;
        }
        return true;
    }

    public boolean isTrap(){
        for( int num : roomNumber ){
            if( isPrime( num )) return true;
        }
        return false;
    }
}