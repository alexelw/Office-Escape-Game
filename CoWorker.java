import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class CoWorker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CoWorker extends Worker {
    private static final int SPEED = 2;

    private final Point pointA;
    private final Point pointB; // Use shared chat interaction logic

    public CoWorker(Point pointA, Point pointB) {
        super(new TwoPointMovement(pointA, pointB));
        ((TwoPointMovement) movement).setWorker(this);

        this.pointA = pointA;
        this.pointB = pointB;
        setSpeed(SPEED);

    }

    @Override
    public void act() {
        move();

        TiredOfficeWorker worker = (TiredOfficeWorker) getOneIntersectingObject(TiredOfficeWorker.class);
        if (worker != null) {
            interactWith(worker);
        }
    }

    @Override
    public void resumeMovement() {
        setSpeed(SPEED);
        ((TwoPointMovement) movement).resumeMoving();
    }

    @Override
    public void interactWith(Worker worker) {
        if (worker instanceof TiredOfficeWorker) {
            MyWorld world = (MyWorld) getWorld();
            if (!world.getChatHandler().isChatActive() && !world.getChatHandler().isChatOnCooldown()) {
                world.getChatHandler().startChat((TiredOfficeWorker) worker, this);
            }
        }
    }


    @Override
    public void stopMovement() {
        ((TwoPointMovement) movement).stopMoving();
    }

    @Override
    public void move() {
        movement.move(); 
    }
}

