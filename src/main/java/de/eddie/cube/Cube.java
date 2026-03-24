package de.eddie.cube;

import java.util.Random;

public class Cube{
    private Room[][][] roomMatrix;

    Random cube = new Random();

    public boolean isPositionValid(int x, int y, int z){
        return  x >= 0 && x < 5 &&
                y >= 0 && y < 5 &&
                z >= 0 && z < 5;
    }

    public Cube(){
        this.roomMatrix = new Room[ 5 ][ 5 ][ 5 ];

        for( int x = 0; x < 5; x++ ){
            for( int y = 0; y < 5; y++ ){
                for( int z = 0; z < 5; z++ ){
                    int[] numbers = {
                            cube.nextInt(900) + 100,
                            cube.nextInt(900) + 100,
                            cube.nextInt(900) + 100
                    };

                    roomMatrix[x][y][z] = new Room(numbers, x, y, z);
                }
            }
        }

        System.out.println("Cube mit 125 Räumen wurde erstellt.");
    }

    public Room getRoom(int x, int y, int z){
        return roomMatrix[x][y][z];
    }

}
