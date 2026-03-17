package de.eddie.cube;

import java.util.Random;

public class Cube{
    private Room[][][] roomMatrix;
    private int exitX, exitY, exitZ;
    private String exitDirection;

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

    public void setupExit(){
        Random rnd = new Random();
        // Wahl einer der 6 Seiten des Cubes (0: Norden, 1: Süden, 2: Osten, 3: Westen, 4: Oben, 5: unten)
        int side = rnd.nextInt(6);

        // Zufällige Koordinaten auf der ausgewählten Seite
        int coord1 = rnd.nextInt(5);
        int coord2 = rnd.nextInt(5);

        switch ( side ){
            case 0: exitX = coord1; exitY = 4; exitZ = coord2; exitDirection = "norden"; break; // Nordwand
            case 1: exitX = coord1; exitY = 0; exitZ = coord2; exitDirection = "süden";  break; // Südwand
            case 2: exitX = 4; exitY = coord1; exitZ = coord2; exitDirection = "osten";  break; // Ostwand
            case 3: exitX = 0; exitY = coord1; exitZ = coord2; exitDirection = "westen"; break; // Westwand
            case 4: exitX = coord1; exitY = coord2; exitZ = 4; exitDirection = "hoch";   break; // Decke
            case 5: exitX = coord1; exitY = coord2; exitZ = 0; exitDirection = "unten";  break; // Boden
        }
        System.out.println("DEBUG: Exit ist in (" + exitX + "|" + exitY + "|" + exitZ + ") Richtung " + exitDirection);
    }

    public boolean checkExit( int x, int y, int z, String direction){
        return x == exitX && y == exitY && z == exitZ && direction.equalsIgnoreCase( exitDirection );
    }
}
