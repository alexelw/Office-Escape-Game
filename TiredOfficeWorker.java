import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot, and MouseInfo)

/**
 * Represents a tired office worker in the simulation.
 * Handles movement, energy management, and interactions.
 * 
 * @author Alex Watts
 * @version 1.3
 */
public class TiredOfficeWorker extends Worker {
    // Constants for movement speed and energy thresholds
    private static final int NORMAL_SPEED = 2;
    private static final int SLOW_SPEED = 1;
    private static final int NO_SPEED = 0;
    private static final int LOW_ENERGY_THRESHOLD = 600;
    private static final int ENERGY_REGEN_RATE = 4;
    private static final int ENERGY_DRAIN_RATE = 5;
    
    private Energy energy;
    private boolean chatting = false;
    private boolean isMoving = false;
    private int speedBoostTimer = 0;
    private AnimationHandler animation;

    /**
     * Constructor - Initializes movement, energy, and animation handler.
     */
    public TiredOfficeWorker() {
        super(new WASDMovement());
        this.energy = new Energy(1000);
        ((WASDMovement) movement).setWorker(this);

        animation = new AnimationHandler(this,
            "worker_idle.png",
            new String[]{"worker_walkL1.png", "worker_walkL2.png"},
            new String[]{"worker_walkR1.png", "worker_walkR2.png"},
            new String[]{"worker_effect1.png", "worker_effect2.png"},
            new String[]{"worker_talk1.png", "worker_talk2.png"},
            32, 32
        );
    }

    /**
     * Updates the worker's energy and adjusts speed accordingly.
     */
    public void updateEnergy(int energyChange) {
        energy.changeEnergy(energyChange);
        updateSpeed();
    }

    /**
     * Returns the current energy level.
     */
    public int getEnergy() {
        return energy.getCurrentEnergy();
    }

    /**
     * Returns the Energy object for external access.
     */
    public Energy getEnergyObject() {
        return energy;
    }

    /**
     * Updates the worker's speed based on energy levels and active boosts.
     */
    private void updateSpeed() {
        int currentEnergy = energy.getCurrentEnergy();
        int speed = (currentEnergy <= LOW_ENERGY_THRESHOLD && currentEnergy > 0) ? SLOW_SPEED : NORMAL_SPEED;
        
        if (currentEnergy == 0) speed = NO_SPEED;
        if (speedBoostTimer > 0) speed = NORMAL_SPEED * 2; // Apply speed boost
        
        setSpeed(speed);
    }

    /**
     * Handles movement logic, considering energy constraints.
     */
    @Override
    public void move() {
        // Check if the worker is moving based on the WASD movement
        isMoving = ((WASDMovement) movement).isMoving(); // Update isMoving state

        if (energy.getCurrentEnergy() > 0 && isMoving) {
            movement.move();
        } else {
            isMoving = false;
        }
    }

    /**
     * Handles interactions with other workers (CoWorker or Boss).
     */
    @Override
    public void interactWith(Worker worker) {
        MyWorld world = (MyWorld) getWorld();
       if (worker instanceof TiredOfficeWorker) {
            if (!world.getChatHandler().isChatActive((CoWorker) worker, this) &&
                !world.getChatHandler().isChatOnCooldown((CoWorker) worker, this)) {
                world.getChatHandler().startChat((CoWorker) worker, this);
            }
        }
        else if (worker instanceof Boss) {
            animation.animateTalking();
        }
    }

    /**
     * Manages energy depletion when moving and regeneration when idle.
     */
    private void manageEnergy() {
        if (isMoving) {
            energy.changeEnergy(-ENERGY_DRAIN_RATE);
    
        } else if (energy.getCurrentEnergy() < energy.getMaxEnergy()) {
            energy.restoreEnergy(ENERGY_REGEN_RATE);
        }
    }

    /**
     * Handles animations based on movement and chat states.
     */
    private void handleAnimation() {
        if (chatting) {
            animation.animateTalking();
        } else {
            int moveDirectionX = 0;
            int moveDirectionY = 0;
            if (Greenfoot.isKeyDown("w")) moveDirectionY = -1;
            if (Greenfoot.isKeyDown("s")) moveDirectionY = 1;
            if (Greenfoot.isKeyDown("a")) moveDirectionX = -1;
            if (Greenfoot.isKeyDown("d")) moveDirectionX = 1;
            
            animation.animateWalking(moveDirectionX, moveDirectionY);
        }
    }
    
    /**
     * Sets the chatting state of the worker.
     */
    public void setChatting(boolean chatting) {
        this.chatting = chatting;
    }

    /**
     * Triggers the talking animation.
     */
    public void animateTalking() {
        chatting = true;
        animation.animateTalking();
    }

    /**
     * Stops movement completely.
     */
    @Override
    public void stopMovement() {
        setSpeed(NO_SPEED);
        ((WASDMovement) movement).stopMoving();
        isMoving = false;
    }

    /**
     * Resumes movement after stopping or chatting.
     */
    @Override
    public void resumeMovement() {
        chatting = false;
        updateSpeed();
        ((WASDMovement) movement).resumeMoving();
    }

    /**
     * Handles the speed boost countdown and resets speed when boost ends.
     */
    private void handleSpeedBoost() {
        if (speedBoostTimer > 0) {
            speedBoostTimer--;
            if (speedBoostTimer == 0) updateSpeed();
        }
    }

    /**
     * Activates a temporary speed boost for a set duration.
     */
    public void startSpeedBoost(int duration) {
        speedBoostTimer = duration;
        updateSpeed();
    }
    
    /**
     * The main act loop, executing movement, energy management, and animations.
     */
    public void act() {
        move();
        manageEnergy();
        handleAnimation();
        handleSpeedBoost();
    }
}
