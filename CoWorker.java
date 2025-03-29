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
    private static final int CHAT_DURATION = 100;

    private int chatTimer = 0;
    private boolean isInChat = false;

    private final Point pointA;
    private final Point pointB;

    public CoWorker(Point pointA, Point pointB) {
        super(new TwoPointMovement(pointA, pointB));
        this.pointA = pointA;
        this.pointB = pointB;
        setSpeed(SPEED);
    }

    @Override
    public void move() {
        if (isInChat) {
            if (chatTimer > 0) {
                chatTimer--;
            } else {
                isInChat = false;
            }
        } else {
            movement.move();
        }
    }

    @Override
    public void interactWith(Worker worker) {
        if (worker instanceof TiredOfficeWorker && !isInChat) {
            isInChat = true;
            chatTimer = CHAT_DURATION;
            stopMovement();
        }
    }
    
    private void stopMovement() {
        // Stop the coworker from moving
        ((TwoPointMovement) movement).stopMoving();
    }

    public void endChat() {
        isInChat = false;
        chatTimer = 0;
    }
}

