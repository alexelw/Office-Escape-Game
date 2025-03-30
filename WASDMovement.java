import greenfoot.*;
/**
 * Movement class for using WASD
 * 
 * Alex watts 
 * version 1.1
 */

import greenfoot.*;

public class WASDMovement implements IMovement {
    private TiredOfficeWorker worker;
    private boolean isStopped = false;

    public WASDMovement() {
    }

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

    private boolean canMoveTo(int x, int y) {
        World world = worker.getWorld();
        if (world == null) return false;

        // Less strict wall detection: Only checks the worker's approximate center
        return world.getObjectsAt(x, y, wall.class).isEmpty();
    }

    public void stopMoving() {
        isStopped = true;
    }

    public void resumeMoving() {
        isStopped = false;
    }
}



