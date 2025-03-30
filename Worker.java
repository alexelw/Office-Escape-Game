import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Abstract class for worker actors in the game.
 * 
 * Provides core functionality for managing speed, movement, interactions,
 * and applying effects from collectibles.
 * 
 * @author Alex Watts 
 * @version 1.0
 */
public abstract class Worker extends Actor implements IWorker {
    
    // Worker attributes
    protected int speed;           // The worker's speed
    protected IMovement movement;  // Movement strategy for the worker
    
    /**
     * Constructor to initialize a worker with a specific movement strategy.
     * 
     * @param movement the movement strategy to be used by the worker
     * @throws IllegalArgumentException if the movement is null
     */
    public Worker(IMovement movement) {
        if (movement == null) {
            throw new IllegalArgumentException("Worker must be assigned a valid movement strategy.");
        }
        this.movement = movement;
    }
    
    /**
     * Updates the worker's speed by a specified change.
     * Ensures that speed cannot go below 1.
     * 
     * @param speedChange the change in speed (positive or negative)
     */
    public void updateSpeed(int speedChange) {
        this.speed += speedChange;
        if (this.speed < 1) {
            this.speed = 1;  // Ensure speed doesn't go below 1 (optional)
        }
    }
    
    /**
     * Returns the current speed of the worker.
     * 
     * @return the worker's current speed
     */
    public int getSpeed() {
        return speed;
    }
    
    /**
     * Sets the worker's speed to a specified value.
     * Ensures speed cannot be negative.
     * 
     * @param speed the new speed to set
     */
    public void setSpeed(int speed) {
        this.speed = Math.max(0, speed);
    }
    
    /**
     * Applies the effect of a collectible to the worker and removes the collectible from the world.
     * 
     * @param collectible the collectible whose effect will be applied
     */
    public void applyEffectToCollectible(Collectible collectible) {
        collectible.applyEffectToWorker(this);  // Apply the specific effect from the collectible
        collectible.removeFromWorld();  // Remove the collectible from the world
    }
    
    /**
     * Abstract method to handle interactions between workers.
     * This must be implemented by subclasses.
     * 
     * @param worker the other worker to interact with
     */
    public abstract void interactWith(Worker worker);
    
    /**
     * Abstract method for moving the worker.
     * Each worker will implement its own movement logic.
     */
    public abstract void move();
    
    /**
     * Stops the worker's movement.
     * This can be overridden by subclasses for specific stop behavior.
     */
    public void stopMovement() {
        // Default empty implementation
    }
    
    /**
     * Resumes the worker's movement.
     * This can be overridden by subclasses for specific resume behavior.
     */
    public void resumeMovement() {
        // Default empty implementation
    }
    
    /**
     * Animates the worker's talking state.
     * Default implementation does nothing but can be overridden by subclasses.
     */
    public void animateTalking() {
        // Default implementation does nothing
    }
}
