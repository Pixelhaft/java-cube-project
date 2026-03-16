package de.eddie.cube;

import java.sql.SQLOutput;
import java.util.Locale;

public class Player{
    // Aktuelle Positivion im Cube
    private int x, y, z;
    private Cube myCube;        // In welchem Cube ist der Spieler

    public Player( int posX, int posY, int posZ, Cube cube ){
        this.x = posX;
        this.y = posY;
        this.z = posZ;
        this.myCube = cube;
    }

    public void walk( String direction ){
        int newX = x, newY = y, newZ = z;

        switch( direction.toLowerCase() ){
            case "north", "norden":
                newY++;
                break;
            case "south", "süden":
                newY--;
                break;
            case "east", "osten":
                newX++;
                break;
            case "west","westen":
                newX--;
                break;
            case "up", "hoch":
                newZ++;
                break;
            case "down", "runter":
                newZ--;
                break;

            default:
                System.out.println("Unknown direction!");
                return;
        }

        // Anfrage an den Cube, ob man in diese Richtung gehen kann
        if( myCube.isPositionValid( newX, newY, newZ ) ){
            this.x = newX;
            this.y = newY;
            this.z = newZ;

            System.out.println( "You move to " + direction + "." );

            // Raumnummer anzeigen
            Room currentRoom = myCube.getRoom( this.x, this.y, this.z );
            int[] numbers = currentRoom.getRoomNumber();

            System.out.println( "---------------------------------" );
            System.out.println( "Numbers on the door: " + numbers[0] + "|" + numbers[1] + "|" + numbers[2] );
            System.out.println( "---------------------------------" );


        } else{
            System.out.println( "Weird, there's no passage here." );
        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getZ(){
        return z;
    }
}
