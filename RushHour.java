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
    
    static ArrayList<Vehicle> cars;
    
    public ArrayList<Move> examineAllCars(Move currentMove) {
        ArrayList<Move> newMoves = new ArrayList<Move>();
        for(Vehicle car: cars) {
            String board = new String(currentMove.board);
            int position = board.indexOf(car.Letter);
            int x = position % 6;
            int y = position / 6;
            int count = car.IsCar ? 2 : 3;
            if(car.IsHorizonatal) {
                while(x + count < 6 && currentMove.board[y*6 + x + count] == 'x') {
                    Move newMove = new Move();
                    newMove.previous = currentMove;
                    newMove.move = car.Color + " " + Integer.toString(count - (car.IsCar ? 1 : 2)) + " R";
                    System.arraycopy(currentMove.board, 0, newMove.board, 0, currentMove.board.length);
                    newMove.board[y * 6 + x] = 'x';
                    newMove.board[y * 6 + x] = 'x';
                    newMove.board[y * 6 + x + 1] = 'x';
                    newMove.board[y * 6 + x + (count - 1)] = car.Letter;
                    newMove.board[y * 6 + x + count] = car.Letter;
                    if(!car.IsCar){
                        newMove.board[y * 6 + x + 2] = 'x';
                        newMove.board[y * 6 + x + (count - 2)] = car.Letter;
                    }
                    newMoves.add(newMove);
                    count++;
                }
                count = 1; 
                while(x - count >= 0 && currentMove.board[y*6 + x - count] == 'x') {
                    Move newMove = new Move();
                    newMove.previous = currentMove;
                    newMove.move = car.Color + " " + Integer.toString(count) + " L";
                    System.arraycopy(currentMove.board, 0, newMove.board, 0, currentMove.board.length);
                    newMove.board[y * 6 + x] = 'x';
                    newMove.board[y * 6 + x] = 'x';
                    newMove.board[y * 6 + x + 1] = 'x';
                    newMove.board[y * 6 + x - count + 1] = car.Letter;
                    newMove.board[y * 6 + x - count] = car.Letter;
                    if(!car.IsCar){
                        newMove.board[y * 6 + x + 2] = 'x';
                        newMove.board[y * 6 + x - count + 2] = car.Letter;
                    }
                    newMoves.add(newMove);
                    count++;
                }
            }
            else {
                while(y + count < 6 && currentMove.board[(y + count)*6 + x] == 'x') {
                    Move newMove = new Move();
                    newMove.previous = currentMove;
                    newMove.move = car.Color + " " + Integer.toString(count - (car.IsCar ? 1 : 2)) + " D";
                    System.arraycopy(currentMove.board, 0, newMove.board, 0, currentMove.board.length);
                    newMove.board[y * 6 + x] = 'x';
                    newMove.board[(y + 1) * 6 + x] = 'x';
                    newMove.board[(y + count - 1) * 6 + x] = car.Letter;
                    newMove.board[(y + count) * 6 + x] = car.Letter;
                    if(!car.IsCar){
                        newMove.board[(y + 2) * 6 + x] = 'x';
                        newMove.board[(y + count - 2) * 6 + x] = car.Letter;
                    }
                    newMoves.add(newMove);
                    count++;
                }
                count = 1; 
                while(y - count >= 0 && currentMove.board[(y - count)*6 + x] == 'x') {
                    Move newMove = new Move();
                    newMove.previous = currentMove;
                    newMove.move = car.Color + " " + Integer.toString(count) + " U";
                    System.arraycopy(currentMove.board, 0, newMove.board, 0, currentMove.board.length);
                    newMove.board[y * 6 + x] = 'x';
                    newMove.board[(y + 1) * 6 + x] = 'x';
                    newMove.board[(y - count + 1) * 6 + x] = car.Letter;
                    newMove.board[(y - count) * 6 + x] = car.Letter;
                    if(!car.IsCar){
                        newMove.board[(y + 2) * 6 + x] = 'x';
                        newMove.board[(y - count + 2) * 6 + x] = car.Letter;
                    }
                    newMoves.add(newMove);
                    count++;
                }
            }
        }
        return newMoves;
    }
    public void printSolution(Move finalMove, int numOfMoves){
        if(finalMove.previous != null){
           printSolution(finalMove.previous, numOfMoves + 1);
           System.out.println(finalMove.move);
        }
        else{
            System.out.println(Integer.toString(numOfMoves) +" moves:");
        }
    }
    public static void main(String[] args) {
        try{
            File in = new File("C:\\Users\\Master\\Documents\\NetBeansProjects\\RushHour\\src\\problemFile.txt");
            Scanner inFile = new Scanner(in);
            int numberOfCars = inFile.nextInt();
            ArrayList<Vehicle> cars = new ArrayList();
            char[] board = new char[36];
            Arrays.fill(board, 'x');
            ArrayDeque<Move> moveQueue = new ArrayDeque<Move>();
            RushHour rh = new RushHour();
            //read in all the cars (and trucks)
            for(int i = 0; i < numberOfCars; i++) {
                Vehicle newVehicle = new Vehicle();
                newVehicle.IsCar = inFile.next().equals("car") ? true : false;
                newVehicle.Color = inFile.next();
                newVehicle.IsHorizonatal = inFile.next().equals("h");
                newVehicle.Letter = (char)((int)'a' + i); // 'a' will be the red car
                cars.add(newVehicle);
                
                int y = inFile.nextInt() - 1;
                int x = inFile.nextInt() - 1;
                for(int j = newVehicle.IsCar ? 1 : 2; j >= 0 ; j--) {
                    if(newVehicle.IsHorizonatal){
                        board[x + y*6 + j]  = newVehicle.Letter;
                    }
                    else{
                        board[x + y*6 + j*6]  = newVehicle.Letter;
                    }
                }

                
            }
            Move firstMove = new Move();
            firstMove.board = board;
            firstMove.move = "";
            firstMove.previous = null;
            moveQueue.addLast(firstMove);
            while(!moveQueue.isEmpty() || moveQueue.peek().board[17] == 'a'){
                Move checkMove = moveQueue.removeFirst();
                ArrayList<Move> newMoves = rh.examineAllCars(checkMove);
                for(Move m :newMoves){
                    moveQueue.addLast(m);
                }
            }
            rh.printSolution(moveQueue.peek(), 0);
 
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
}
