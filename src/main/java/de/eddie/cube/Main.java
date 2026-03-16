package de.eddie.cube;

import java.util.Scanner;

public class Main{
    public static void main( String[] args ){
        Cube theCube = new Cube();

        // Der Spieler startet in der Mitte vom Cube
        Player one = new Player( 2, 2, 2, theCube);
        Scanner tastatur = new Scanner( System.in );
        String command = "";

        System.out.println( "You wake up in a cube... in every direction there is a door! (Enter a direction: 'north', 'east', 'south', 'west', 'up', 'down' or 'exit')" );

        while( !command.equals( "exit" ) ){
            System.out.println( "\n Your position: (" + one.getX() + "|" + one.getY() + "|" + one.getZ() + ")" );
            System.out.println( "\n Command: " );
            command = tastatur.nextLine();

            if( !command.equals( "exit" ) ){
                one.walk( command );
            }
        }

        System.out.println( "Your vision fades to black... 'Program terminated'" );
        tastatur.close();
    }
}
