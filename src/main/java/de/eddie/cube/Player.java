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

    public boolean walk( String direction ){
        int newX = x, newY = y, newZ = z;

        switch( direction.toLowerCase() ){
            case "north", "norden": newY++; break;
            case "south", "süden":  newY--; break;
            case "east", "osten":   newX++; break;
            case "west","westen":   newX--; break;
            case "up", "hoch":      newZ++; break;
            case "down", "runter":  newZ--; break;

            default:
                System.out.println("Unknown direction!");
        }

        // Anfrage an den Cube, ob man in diese Richtung gehen kann
        if( myCube.isPositionValid( newX, newY, newZ ) ){
            this.x = newX;
            this.y = newY;
            this.z = newZ;

            System.out.println("You move to " + direction + ".");

            Room currentRoom = myCube.getRoom( this.x, this.y, this.z );

            if( currentRoom.isTrap() ){
                return false; // Spieler ist gestorben
            } else {
                System.out.println("The room is silent. You are safe...for now.");
                return true; // Spieler lebt
            }

        } else{
            System.out.println("Weird, there's no passage here.");
            return true;
        }
    }

    public void setMyCube( Cube newCube ){
        this.myCube = newCube;
    }

    public void inspect( String direction ){
        int checkX = x, checkY = y, checkZ = z;

        switch( direction.toLowerCase() ){
            case "north", "norden": checkY++; break;
            case "south", "süden":  checkY--; break;
            case "east", "osten":   checkX++; break;
            case "west","westen":   checkX--; break;
            case "up", "hoch":      checkZ++; break;
            case "down", "runter":  checkZ--; break;

            default:
                System.out.println("Unknown direction to look at!");
                return;
        }

        // Prüfen, ob in der Richtung ein Raum ist
        if( myCube.isPositionValid( checkX, checkY, checkZ )){
            Room nextRoom = myCube.getRoom( checkX, checkY, checkZ );
            int[] nums = nextRoom.getRoomNumber();

            System.out.println("You peek through the hatch to the " + direction + ".");
            System.out.println("The numbers engraved on the frame are: " + nums[0] + "|" + nums[1] + "|" + nums[2]);
        } else {
            System.out.println("You look to the " + direction + ", but there is only a solid wall");
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

    public void respawn(){
        this.x = 2;
        this.y = 2;
        this.z = 2;

        System.out.println("\n*******************************************");
        System.out.println("You open your eyes... everything is familiar.");
        System.out.println("You are back in the center of the Cube.");
        System.out.println("*******************************************");
    }
}
