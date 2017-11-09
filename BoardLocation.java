
package rushhour;

public class BoardLocation {
    Vehicle OccupyingVehicle;
    Boolean isOccupied;
    Boolean isVisited;
    
    public BoardLocation() {
        isVisited = false;
        isOccupied = false;
    }
}
