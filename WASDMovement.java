import greenfoot.*;

/**
 * Handles movement using WASD controls.
 * 
 * @author Alex Watts
 * @version 1.1
 */
public class WASDMovement implements IMovement {
    private TiredOfficeWorker worker;
    private boolean isStopped = false;

    public WASDMovement() {}

    /**
     * Assigns the worker to control.
     */
    public void setWorker(TiredOfficeWorker worker) {
        this.worker = worker;
    }

    @Override
    public void move() {
        if (worker == null || worker.getEnergy() <= 0 || isStopped) return;

        int speed = worker.getSpeed();
        int newX = worker.getX();
        int newY = worker.getY();

        if (Greenfoot.isKeyDown("W")) newY -= speed;
        if (Greenfoot.isKeyDown("S")) newY += speed;
        if (Greenfoot.isKeyDown("A")) newX -= speed;
        if (Greenfoot.isKeyDown("D")) newX += speed;

        if (canMoveTo(newX, newY)) {
            worker.setLocation(newX, newY);
            worker.updateEnergy(-5);  // Deduct energy only if movement happens
        }
    }

    /**
     * Checks if the worker can move to the specified position.
     */
    private boolean canMoveTo(int x, int y) {
        World world = worker.getWorld();
        return world != null && world.getObjectsAt(x, y, wall.class).isEmpty();
    }

    /**
     * Stops movement.
     */
    public void stopMoving() {
        isStopped = true;
    }

    /**
     * Resumes movement.
     */
    public void resumeMoving() {
        isStopped = false;
    }

    /**
     * Checks if the worker is currently moving.
     */
    public boolean isMoving() {
        return Greenfoot.isKeyDown("W") || Greenfoot.isKeyDown("A") ||
               Greenfoot.isKeyDown("S") || Greenfoot.isKeyDown("D");
    }

    /**
     * Gets horizontal movement direction (-1 for left, 1 for right, 0 for none).
     */
    public int getDirectionX() {
        return Greenfoot.isKeyDown("A") ? -1 : (Greenfoot.isKeyDown("D") ? 1 : 0);
    }

    /**
     * Gets vertical movement direction (-1 for up, 1 for down, 0 for none).
     */
    public int getDirectionY() {
        return Greenfoot.isKeyDown("W") ? -1 : (Greenfoot.isKeyDown("S") ? 1 : 0);
    }
}
