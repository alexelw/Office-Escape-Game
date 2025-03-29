import greenfoot.*;
/**
 * Movement class for using WASD
 * 
 * Alex watts 
 * version 1.1
 */

public class WASDMovement implements IMovement {
    private TiredOfficeWorker worker;

    public WASDMovement() {
    }

    // Set the actual worker after the worker object is created
    public void setWorker(TiredOfficeWorker worker) {
        this.worker = worker;
    }

    @Override
    public void move() {
        if (worker == null || worker.getEnergy() <= 0) return;

        int movementSpeed = worker.getSpeed();
        
        // Use a helper method for directional movement to reduce redundancy
        moveInDirection("W", 0, -movementSpeed);
        moveInDirection("S", 0, movementSpeed);
        moveInDirection("A", -movementSpeed, 0);
        moveInDirection("D", movementSpeed, 0);
    }

    // Helper method to move in one direction
    private void moveInDirection(String key, int dx, int dy) {
        if (Greenfoot.isKeyDown(key)) {
            worker.setLocation(worker.getX() + dx, worker.getY() + dy);
            worker.updateEnergy(-5);  // Deduct energy for movement
        }
    }
}


