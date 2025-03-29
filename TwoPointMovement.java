/**
 * Write a description of class TwoPointMovement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TwoPointMovement implements IMovement {
    private final Point pointA;
    private final Point pointB;
    private Worker worker;  // Changed from CoWorker to Worker
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
        if (worker.getX() < xTarget) worker.setLocation(worker.getX() + 1, worker.getY());
        if (worker.getX() > xTarget) worker.setLocation(worker.getX() - 1, worker.getY());
        if (worker.getY() < yTarget) worker.setLocation(worker.getX(), worker.getY() + 1);
        if (worker.getY() > yTarget) worker.setLocation(worker.getX(), worker.getY() - 1);

        // Once reached the target point, reverse direction
        if (worker.getX() == xTarget && worker.getY() == yTarget) {
            movingToPointB = !movingToPointB;
        }
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
