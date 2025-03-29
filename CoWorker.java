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

    private AnimationHandler animation;
    private boolean chatting = false;

    public CoWorker(Point pointA, Point pointB) {
        super(new TwoPointMovement(pointA, pointB));
        ((TwoPointMovement) movement).setWorker(this);

        this.pointA = pointA;
        this.pointB = pointB;
        setSpeed(SPEED);
        
        animation = new AnimationHandler(this,
            "coworker_idle.png",
            new String[]{"coworker_walk1.png", "coworker_walk2.png"},
            new String[]{"coworker_effect1.png", "coworker_effect2.png"},
            new String[]{"coworker_talk1.png", "coworker_talk2.png"},
            32, 32
        );


    }

    @Override
    public void act() {
        move();
        handleAnimation(); 
        TiredOfficeWorker worker = (TiredOfficeWorker) getOneIntersectingObject(TiredOfficeWorker.class);
        if (worker != null) {
            interactWith(worker);
        }
    }

    
        public void setChatting(boolean chatting) {
        this.chatting = chatting;
    }
    
    public void animateTalking() {
        chatting = true;
        animation.animateTalking();
    }
    
    @Override
    public void resumeMovement() {
        chatting = false;
        ((TwoPointMovement) movement).resumeMoving();
    }
    
    private void handleAnimation() {
        if (chatting) {
            animation.animateTalking();
        } else {
            animation.animateWalking(); // Coworker is always moving
        }
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
        chatting = true;
        handleAnimation();
    }

    @Override
    public void move() {
        movement.move(); 
    }
}

