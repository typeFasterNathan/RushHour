/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rushhour;
import java.io.*;
import java.util.*;
/**
 *
 * @author Master
 */
public class RushHour {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            File in = new File("problemFile");
            Scanner inFile = new Scanner(in);
            int numberOfCars = inFile.nextInt();
            ArrayList<Vehicle> cars = new ArrayList();
            BoardLocation[][] Board = new BoardLocation[6][6];
            //read in all the cars (and trucks)
            for(int i = 0; i < numberOfCars; i++) {
                Vehicle newVehicle = new Vehicle();
                newVehicle.IsCar = inFile.next() == "car" ? true : false;
                newVehicle.Color = inFile.next();
                newVehicle.IsHorizonatal = inFile.next() == "h";
                newVehicle.X = inFile.nextInt() - 1;
                newVehicle.Y = inFile.nextInt() - 1;
                cars.add(newVehicle);
                Board[newVehicle.getY()][newVehicle.getX()].OccupyingVehicle = newVehicle;
                Board[newVehicle.getY()][newVehicle.getX()].isOccupied = true;
                for(int j = newVehicle.IsCar ? 2 : 3; i > 0 ; i--) {
                    if(newVehicle.IsHorizonatal){
                        Board[newVehicle.getY()][newVehicle.getX() - j - 1].isOccupied = true;
                    }
                    else{
                        Board[newVehicle.getY()][newVehicle.getX() - j - 1].isOccupied = true;
                    }
                }
                // check all the cars for potential moves (empty adjacent spots)
                // put those in a queue 
                // pop the moves out and search 
            }
 
        }
        catch (Exception ex) {
            
        }
    }
    
}
