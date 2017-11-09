/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rushhour;

/**
 *
 * @author Master
 */
public class Vehicle {
    Boolean IsCar;
    String Color;
    Boolean IsHorizonatal;
    int X;
    int Y;
    
    int getX(){
        return X;
    }
    
    int getXForOutput() {
        return X + 1;
    }
    
    int getY(){
        return Y;
    }
    
    int getYForOutput() {
        return Y + 1;
    }
}
