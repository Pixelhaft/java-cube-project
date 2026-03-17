package de.eddie.cube;

import java.util.Locale;
import java.util.Scanner;

public class Main{
    public static void main( String[] args ){
        Cube theCube = new Cube();

        // Der Spieler startet in der Mitte vom Cube
        Player one = new Player( 2, 2, 2, theCube);
        Scanner tastatur = new Scanner( System.in );
        String command = "";

        System.out.println("You wake up in a cube... in every direction there is a door!");
        System.out.println("Commands (e.g. 'north', 'look north', or 'exit')");

        while( true ){
            // Status anzeigen
            System.out.println("\n Your position: (" + one.getX() + "|" + one.getY() + "|" + one.getZ() + ")");
            System.out.println("\n Commands (e.g. 'north' or 'look north')");

            // Eingabe säubern
            command = tastatur.nextLine().toLowerCase().trim();

            if( command.equals( "exit" )) break;
            if( command.isEmpty()) continue;

            // Befehl aufteilen in Aktion und Richtung
            String[] parts = command.split(" ", 2);

            if( parts[0].equals( "look" ) && parts.length == 2 ){
                // Spieler will nur schauen, nicht laufen
                one.inspect( parts[1] );
            } else if( parts.length == 1 && !parts[0].isEmpty()){
                // Überprüfen das Ergebnis von walk: true = überleben / false = tot
                boolean amIAlive = one.walk( parts[0] );

                if( !amIAlive ){
                    System.out.println("\n###########################################");
                    System.out.println("The Cube shifts and changes... A new nightmare begins.");
                    System.out.println("###########################################");

                    // Neuer Cube wird erschaffen (neue Zufallszahlen)
                    theCube = new Cube();

                    // Dem Spieler den neuen Cube zuweisen
                    one.setMyCube( theCube );

                    // Spieler wird zurück an den Start gesetzt
                    one.respawn();
                }
            }
        }

        System.out.println( "Your vision fades to black... 'Program terminated'" );
        tastatur.close();
    }
}
