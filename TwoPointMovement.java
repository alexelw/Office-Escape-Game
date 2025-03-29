import greenfoot.*;
/**
 * Write a description of class TwoPointMovement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TwoPointMovement implements IMovement {
    private final Point pointA;
    private final Point pointB;
    private Worker worker;
    private boolean movingToPointB;
    private boolean isStopped = false;

    public TwoPointMovement(Point pointA, Point pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.movingToPointB = true;
    }
    

    // Set the worker that will move
    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @Override
public void move() {
    if (worker == null || isStopped) return; // Stop movement if isStopped is true

    int xTarget = movingToPointB ? pointB.x : pointA.x;
    int yTarget = movingToPointB ? pointB.y : pointA.y;

    // Print current and target positions for debugging
    System.out.println("Worker position: (" + worker.getX() + ", " + worker.getY() + ")");
    System.out.println("Target position: (" + xTarget + ", " + yTarget + ")");

    // Move towards the target point
    if (worker.getX() < xTarget) {
        worker.setLocation(worker.getX() + 1, worker.getY());  // Move right
    }
    if (worker.getX() > xTarget) {
        worker.setLocation(worker.getX() - 1, worker.getY());  // Move left
    }
    if (worker.getY() < yTarget) {
        worker.setLocation(worker.getX(), worker.getY() + 1);  // Move down
    }
    if (worker.getY() > yTarget) {
        worker.setLocation(worker.getX(), worker.getY() - 1);  // Move up
    }

    // Once reached the target point, reverse direction
    if (worker.getX() == xTarget && worker.getY() == yTarget) {
        movingToPointB = !movingToPointB;
    }
}



    public int getDx() {
        int xTarget = movingToPointB ? pointB.x : pointA.x;
        return Integer.compare(xTarget, worker.getX());
    }
    
    public int getDy() {
        int yTarget = movingToPointB ? pointB.y : pointA.y;
        return Integer.compare(yTarget, worker.getY());
    }

    // Method to stop the movement
    public void stopMoving() {
        isStopped = true;
    }

    // Method to resume movement
    public void resumeMoving() {
        isStopped = false;
    }
}
