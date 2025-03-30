import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * CoWorker Class - Handles the movement, interaction, and animation of the coworker.
 * 
 * @Alex Watts
 * @version 1.2
 */
public class CoWorker extends Worker {
    // Constants
    private static final int SPEED = 2;
    
    // Points that the coworker moves between
    private final Point pointA;
    private final Point pointB;
    
    // Animation handler for the coworker
    private AnimationHandler animation;
    
    // Flag indicating if the coworker is chatting
    private boolean chatting = false;

    /**
     * Constructor for CoWorker.
     * 
     * @param pointA The first point in the movement path.
     * @param pointB The second point in the movement path.
     */
    public CoWorker(Point pointA, Point pointB) {
        super(new TwoPointMovement(pointA, pointB));  // Set up movement strategy

        // Set the worker for the movement strategy
        ((TwoPointMovement) movement).setWorker(this);

        // Initialize movement points and speed
        this.pointA = pointA;
        this.pointB = pointB;
        setSpeed(SPEED);

        // Initialize animation handler with idle and walking images
        animation = new AnimationHandler(this,
            "coworker_idle.png",
            new String[]{"coworker_walkL1.png", "coworker_walkL2.png"},   // For left-up movement
            new String[]{"coworker_walkR1.png", "coworker_walkR2.png"},   // For right-down movement
            new String[]{"coworker_effect1.png", "coworker_effect2.png"},
            new String[]{"coworker_talk1.png", "coworker_talk2.png"},
            32, 32
        );
    }

    /**
     * Main action method called every cycle.
     */
    @Override
    public void act() {
        move();  // Move the coworker
        handleAnimation();  // Update the animation state

        // Interact with TiredOfficeWorker if in proximity
        TiredOfficeWorker worker = (TiredOfficeWorker) getOneIntersectingObject(TiredOfficeWorker.class);
        if (worker != null) {
            interactWith(worker);
        }
        // Interact with TiredOfficeWorker if in proximity
        Boss boss = (Boss) getOneIntersectingObject(Boss.class);
        if (boss != null) {
            interactWith(boss);
        }
    }

    /**
     * Sets the chatting state for the coworker.
     * 
     * @param chatting true if the coworker is chatting, false otherwise.
     */
    public void setChatting(boolean chatting) {
        this.chatting = chatting;
    }

    /**
     * Starts the talking animation and sets the chatting state to true.
     */
    public void animateTalking() {
        chatting = true;
        animation.animateTalking();
    }

    /**
     * Resumes the movement of the coworker after chatting.
     */
    @Override
    public void resumeMovement() {
        chatting = false;
        ((TwoPointMovement) movement).resumeMoving();  // Resume movement after chat
    }

    /**
     * Handles the animation state based on the movement and chatting status.
     */
    private void handleAnimation() {
        if (chatting) {
            animation.animateTalking();  // Show talking animation
        } else {
            // Get movement direction from the TwoPointMovement strategy
            TwoPointMovement tm = (TwoPointMovement) movement;
            int moveDirectionX = tm.getDx();
            int moveDirectionY = tm.getDy();
            animation.animateWalking(moveDirectionX, moveDirectionY);  // Animate walking
        }
    }

    /**
     * Interacts with other workers such as TiredOfficeWorker and Boss.
     * 
     * @param worker The worker to interact with.
     */
    @Override
    public void interactWith(Worker worker) {
        MyWorld world = (MyWorld) getWorld();

        // Interaction with TiredOfficeWorker: Start chat if not already active or on cooldown
        if (worker instanceof TiredOfficeWorker) {
            if (!world.getChatHandler().isChatActive((CoWorker) this, (TiredOfficeWorker) worker) &&
                !world.getChatHandler().isChatOnCooldown((CoWorker) this, (TiredOfficeWorker) worker)) {
                world.getChatHandler().startChat((CoWorker) this, (TiredOfficeWorker) worker);
            }
        }
        // Interaction with Boss: Start chat if not already active or on cooldown
        if (worker instanceof Boss) {
            if (!world.getChatHandler().isChatActive((CoWorker) this, (Boss) worker) &&
                !world.getChatHandler().isChatOnCooldown((CoWorker) this, (Boss) worker)) {
                world.getChatHandler().startChat((CoWorker) this, (Boss) worker);
            }
        }

    }

    /**
     * Stops the coworker's movement and triggers the chatting state.
     */
    @Override
    public void stopMovement() {
        ((TwoPointMovement) movement).stopMoving();  // Stop movement
        chatting = true;  // Set chatting flag
        handleAnimation();  // Update animation
    }

    /**
     * Moves the coworker using the TwoPointMovement strategy.
     */
    @Override
    public void move() {
        movement.move();  // Move using the assigned movement strategy
    }
}
