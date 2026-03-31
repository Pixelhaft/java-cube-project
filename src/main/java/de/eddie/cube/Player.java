package de.eddie.cube;

import java.sql.SQLOutput;
import java.util.Locale;

public class Player{
    // Aktuelle Positivion im Cube
    private int x, y, z;
    private Cube myCube; // In welchem Cube ist der Spieler
    private int shoes = 2;
    private int roomsSurvived = 0;
    private static int highscore = 0;

    public Player( int posX, int posY, int posZ, Cube cube ){
        this.x = posX;
        this.y = posY;
        this.z = posZ;
        this.myCube = cube;
    }

    public boolean walk(String direction) {
        int[] target = calculateTarget( direction );
        int newX = target[0];
        int newY = target[1];
        int newZ = target[2];

        if ( newX == x && newY == y && newZ == z ){
                System.out.println("Unknown direction!");
                return true;
        }

        if (myCube.isPositionValid(newX, newY, newZ)) {
            this.x = newX;
            this.y = newY;
            this.z = newZ;

            System.out.println("You move to " + direction + ".");
            Room currentRoom = myCube.getRoom(this.x, this.y, this.z);

            if (currentRoom.isTrap()) {
                return false; // Spieler ist in eine Falle getappt
            } else {
                roomsSurvived++;
                if( roomsSurvived > highscore )
                    highscore = roomsSurvived;
                System.out.println("The room is silent. You are safe.");
                return true; // Überlebt
            }
        } else {
            Room currentRoom = myCube.getRoom(this.x, this.y, this.z);

            if (currentRoom.isExitRiddleSolved()) {
                System.out.println("\n*********************************************");
                System.out.println("The numbers " + currentRoom.getRoomNumber()[0] + "|" +
                        currentRoom.getRoomNumber()[1] + "|" +
                        currentRoom.getRoomNumber()[2] + " glow in a soft blue.");
                System.out.println("The sum " + (currentRoom.getRoomNumber()[0] +
                        currentRoom.getRoomNumber()[1] +
                        currentRoom.getRoomNumber()[2]) + " is a prime!");
                System.out.println("YOU HAVE ESCAPED THE CUBE!");
                System.out.println("*********************************************");
                System.exit(0); // Sieg!
                return true;
            } else {
                // Dieser Ausgang ist eine Falle
                System.out.println("\n#################################################");
                System.out.println("You try to open the outer hatch, but it's a TRAP!");
                System.out.println("      Flamethrowers incinerate the room.");
                System.out.println("#################################################");
                return false; // Triggert den Respawn in der Main
            }
        }
    }

    public void setMyCube( Cube newCube ){
        this.myCube = newCube;
    }

    public void inspect( String direction ){
        int[] target = calculateTarget( direction );
        int checkX = target[0];
        int checkY = target[1];
        int checkZ = target[2];

        // Prüfen, ob in der ausgewählten Richtung ein Raum ist
        if( myCube.isPositionValid( checkX, checkY, checkZ )){
            Room nextRoom = myCube.getRoom( checkX, checkY, checkZ );
            int[] nums = nextRoom.getRoomNumber();

            if( nextRoom.isTrap() ){
                System.out.println("[DEBUG: This room is a Trap!]");
            }

            System.out.println("You peek through the hatch to the " + direction + ".");
            System.out.println("The numbers engraved on the frame are: " + nums[0] + "|" + nums[1] + "|" + nums[2]);
        } else {
            System.out.println("You look to the " + direction + ". There is no next room.");
            System.out.println("You see a heavy industrial airlock leading to the OUTSIDE.");

            Room current = myCube.getRoom(this.x, this.y, this.z);
            int[] nums = current.getRoomNumber();
            System.out.println("The numbers on your current hatch are: " + nums[0] + "|" + nums[1] + "|" + nums[2]);

            if( current.isExitRiddleSolved() ){
                System.out.println("[DEBUG: THIS IS A POSSIBLE EXIT!]");
            }
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

    public int getRoomsSurvived(){
        return roomsSurvived;
    }

    public void respawn(){
        this.x = 2;
        this.y = 2;
        this.z = 2;
        this.shoes = 2;
        this.roomsSurvived = 0;

        System.out.println("\n*****************************************************");
        System.out.println("You open your eyes... everything is familiar.");
        System.out.println("Your vision returns, and you're back in the first room.");
        System.out.println("*******************************************************");
    }

    public void throwShoe(String direction){
        if( shoes <= 0 ){
            System.out.println( "You don't have any shoes left! You are barefoot and scared." );
            return;
        }

        int[] target = calculateTarget( direction );
        int targetX = target[0];
        int targetY = target[1];
        int targetZ = target[2];

        if( myCube.isPositionValid( targetX, targetY, targetZ ) ){
            shoes--;
            Room targetRoom = myCube.getRoom( targetX, targetY, targetZ );

            if( targetRoom.isTrap() ){
                System.out.println("KLONK! The shoe triggered a trap and is GONE!");
            } else {
                System.out.println("Thud. The room seems safe.");
            }
        } else {
            System.out.println("You hit the wall. The shoe bounces back to you.");
        }
    }

    private int[] calculateTarget(String direction) {
        int targetX = this.x;
        int targetY = this.y;
        int targetZ = this.z;

        switch (direction.toLowerCase().trim()) {
            case "north", "norden": targetY++; break;
            case "south", "süden":  targetY--; break;
            case "east", "osten":   targetX++; break;
            case "west", "westen":  targetX--; break;
            case "up", "hoch":      targetZ++; break;
            case "down", "runter":  targetZ--; break;
        }

        return new int[]{targetX, targetY, targetZ};
    }
}
