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
    private static final int CHAT_DURATION = 100; // Duration in frames (about 3 seconds)

    private int chatTimer = 0;
    private boolean isInChat = false;

    private final Point pointA;
    private final Point pointB;
    private boolean movingToPointB = true;

    public CoWorker(Point pointA, Point pointB) {
        super(new TwoPointMovement(pointA, pointB));
        this.pointA = pointA;
        this.pointB = pointB;
        setSpeed(SPEED);
    }

    @Override
    public void move() {
        if (isInChat) {
            // If in a chat, stop movement for the duration of the chat
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
            chatTimer = CHAT_DURATION; // Start the chat timer
            // Optionally, you could add visual feedback or sound for the interaction here
        }
    }
}
