import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Boss Class - Handles boss movement, interactions, and applying effects from collectibles.
 * 
 * @author Alex Watts
 * @version 1.3
 */

public class Boss extends Worker {
    // Animation handler for the boss
    private AnimationHandler animation;
    
    // Reference to the TiredOfficeWorker target for movement
    private Worker targetWorker;
    
    // Movement strategy for the boss
    private BossMovement bossMovement;
    
    // Flags and timers for various behaviors
    private boolean chatting = false;
    private int chatTimer = 0;
    private int speedBoostTimer = 0;

    // Speed constants
    private static final int NORMAL_SPEED = 1;
    private static final int BOOSTED_SPEED = 2;

    /**
     * Constructor for the Boss class.
     * 
     * @param targetWorker The TiredOfficeWorker that the boss will follow.
     */
    public Boss(TiredOfficeWorker targetWorker) {
        super(new DummyMovement()); // Temporary movement logic

        this.targetWorker = targetWorker;
        
        // Initialize the animation handler with various states
        this.animation = new AnimationHandler(
            this,
            "boss_idle.png",
            new String[]{"boss_walkL1.png", "boss_walkL2.png"},
            new String[]{"boss_walkR1.png", "boss_walkR2.png"},
            new String[]{"boss_effect1.png", "boss_effect2.png"},
            new String[]{"boss_talk1.png", "boss_talk2.png"},
            32, 32
        );

        // Set up the boss movement strategy
        bossMovement = new BossMovement(this, targetWorker);
        this.movement = bossMovement;
        
        // Set initial speed
        this.speed = NORMAL_SPEED;
    }

    /**
     * Handles the movement of the boss.
     */
    @Override
    public void move() {
        movement.move();
    }
     /**
     * Handles the animation state based on the movement and chatting status.
     */
    private void handleAnimation() {
        if (chatting) {
            animation.animateTalking();  // Show talking animation
        } else {
            // Calculate direction to move towards the target worker
            int dx = targetWorker.getX() - this.getX();
            int dy = targetWorker.getY() - this.getY();
            
            int moveDirectionX = Integer.compare(dx, 0); // -1, 0, or 1
            int moveDirectionY = Integer.compare(dy, 0); // -1, 0, or 1
            
            // Animate the walking based on direction
            animation.animateWalking(moveDirectionX, moveDirectionY);
        }
    }

    /**
     * Main action method called every cycle.
     */
    @Override
    public void act() {
        move();  // Handle movement
        handleAnimation();  // Update the animation state
        handleSpeedBoost();  // Manage speed boost timers
        
        // Interact with TiredOfficeWorker if in proximity
        TiredOfficeWorker worker = (TiredOfficeWorker) getOneIntersectingObject(TiredOfficeWorker.class);
        if (worker != null) {
            interactWith(worker);
        }
    }

    /**
     * Handles interactions between the boss and other workers.
     * 
     * @param worker The worker the boss interacts with.
     */
    @Override
    public void interactWith(Worker worker) {
        MyWorld world = (MyWorld) getWorld();
        
        // Interaction with TiredOfficeWorker: Game Over if touched
        if (worker instanceof TiredOfficeWorker) {
            world.showText("GAME OVER\nYou have overtime", world.getWidth() / 2, world.getHeight() / 2);
            Greenfoot.stop();  // Stop the simulation
        }
    
        
        // Interaction with CoWorker: Start a chat if not already active
        if (worker instanceof CoWorker) {
            if (!world.getChatHandler().isChatActive((CoWorker) worker, (Boss) this) &&
                !world.getChatHandler().isChatOnCooldown((CoWorker) worker, (Boss) this)) {
                world.getChatHandler().startChat((CoWorker) worker, (Boss) this);
            }
        }
    }

    /**
     * Starts a speed boost for the boss for a specific duration.
     * 
     * @param duration Duration of the speed boost in game cycles.
     */
    public void startSpeedBoost(int duration) {
        speedBoostTimer = duration;
        setSpeed(BOOSTED_SPEED);  // Apply the speed boost
    }

    /**
     * Handles the countdown and reset of the speed boost timer.
     */
    private void handleSpeedBoost() {
        if (speedBoostTimer > 0) {
            speedBoostTimer--;  // Decrease the timer
            if (speedBoostTimer == 0) {
                resetSpeed();  // Reset speed after the boost expires
            }
        }
    }

    /**
     * Resets the speed of the boss to normal.
     */
    private void resetSpeed() {
        setSpeed(NORMAL_SPEED);  // Reset speed to normal after boost
    }

    /**
     * Stops the boss's movement.
     */
    @Override
    public void stopMovement() {
        bossMovement.stopMoving();  // Stop the boss movement
        chatting = true;  // Set chatting flag
    }

    /**
     * Resumes the boss's movement.
     */
    @Override
    public void resumeMovement() {
        chatting = false;
        bossMovement.resumeMoving();  // Resume the boss movement
    }
}
