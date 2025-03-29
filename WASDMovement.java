import greenfoot.*;
/**
 * Movement class for using WASD
 * 
 * Alex watts 
 * version 1.1
 */

public class WASDMovement implements IMovement {
    private TiredOfficeWorker worker;
    private boolean isStopped = false;
    
    public WASDMovement() {
    }

    // Set the actual worker after the worker object is created
    public void setWorker(TiredOfficeWorker worker) {
        this.worker = worker;
    }

    @Override
    public void move() {
        if (worker == null || worker.getEnergy() <= 0 || isStopped) return;

        int movementSpeed = worker.getSpeed();
        
        // Use a helper method for directional movement to reduce redundancy
        moveInDirection("W", 0, -movementSpeed);
        moveInDirection("S", 0, movementSpeed);
        moveInDirection("A", -movementSpeed, 0);
        moveInDirection("D", movementSpeed, 0);
    }

    // Helper method to check for walls and move accordingly
    private void moveInDirection(String key, int dx, int dy) {
        if (Greenfoot.isKeyDown(key)) {
            int newX = worker.getX() + dx;
            int newY = worker.getY() + dy;
            // Check if there's a wall at the new location
            if (!isWallAt(newX, newY)) {
                worker.setLocation(newX, newY);
                worker.updateEnergy(-5);  // Deduct energy for movement
            }
        }
    }
    
    // Returns true if there's a wall at (x, y)
    private boolean isWallAt(int x, int y) {
        World world = worker.getWorld();
        if (world == null) return false;
        return !world.getObjectsAt(x, y, wall.class).isEmpty();
    }

    public void stopMoving() {
        isStopped = true;
    }

    // Resume movement
    public void resumeMoving() {
        isStopped = false;
    }
}



