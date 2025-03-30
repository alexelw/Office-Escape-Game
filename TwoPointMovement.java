import greenfoot.*;

/**
 * Handles movement between two points, alternating between them.
 * 
 * @author Alex Watts
 * @version 1.1
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

    // Returns the difference in X direction from the current position to the target
    public int getDx() {
        int xTarget = movingToPointB ? pointB.x : pointA.x;
        return Integer.compare(xTarget, worker.getX());
    }

    // Returns the difference in Y direction from the current position to the target
    public int getDy() {
        int yTarget = movingToPointB ? pointB.y : pointA.y;
        return Integer.compare(yTarget, worker.getY());
    }

    // Stop movement
    public void stopMoving() {
        isStopped = true;
    }

    // Resume movement
    public void resumeMoving() {
        isStopped = false;
    }
}
