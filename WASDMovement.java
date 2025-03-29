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

    private void moveInDirection(String key, int dx, int dy) {
    if (Greenfoot.isKeyDown(key)) {
        // Calculate the new position
        int newX = worker.getX() + dx;
        int newY = worker.getY() + dy;

        // Check if the worker can move to the new position by checking a few key points around the worker
        if (!isWallAt(newX, newY)) {
            worker.setLocation(newX, newY);
            worker.updateEnergy(-5);  // Deduct energy for movement
        }
    }
}

// Check if the worker's bounding box collides with a wall at the target position
private boolean isWallAt(int x, int y) {
    World world = worker.getWorld();
    if (world == null) return false;

    // Check four key points around the worker's bounding box (edges and corners)
    if (!world.getObjectsAt(x, y, wall.class).isEmpty()) {
        return true;  // Check the exact center position
    }
    if (!world.getObjectsAt(x + worker.getImage().getWidth() - 1, y, wall.class).isEmpty()) {
        return true;  // Check the right edge
    }
    if (!world.getObjectsAt(x, y + worker.getImage().getHeight() - 1, wall.class).isEmpty()) {
        return true;  // Check the bottom edge
    }
    if (!world.getObjectsAt(x + worker.getImage().getWidth() - 1, y + worker.getImage().getHeight() - 1, wall.class).isEmpty()) {
        return true;  // Check the bottom-right corner
    }

    return false;
}

    public void stopMoving() {
        isStopped = true;
    }

    // Resume movement
    public void resumeMoving() {
        isStopped = false;
    }
}



