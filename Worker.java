import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Abstract class for worker actors
 * 
 * @author Alex Watts 
 * @version 1.0
 */
public abstract class Worker extends Actor implements IWorker {
    protected int speed;
    protected IMovement movement;
    
    // Constructor to initialise a wotker with a specific movement strategy
    public Worker(IMovement movement) {
        if (movement == null) {
            throw new IllegalArgumentException("Worker must be assigned a valid movement strategy.");
        }
        this.movement = movement;
        
    }
    
    // Updates the speed of the worker
    public void updateSpeed(int speedChange) {
        this.speed += speedChange;
    }
    
    //get current speed
    public int getSpeed() {
        return speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = Math.max(0, speed);
    }
    
    // Applies the effect of a collectible to the worker and removes the collectible from the world.
    public void applyEffectToCollectible(Collectible collectible) {
        collectible.applyEffectToWorker(this);  // Apply the specific effect from the collectible
        (collectible).removeFromWorld(); // Remove the collectible from the world
    }
    
    // Abstract method for worker interaction (to be implemented by subclasses)
    public abstract void interactWith(Worker worker);
    
    // Abstract method for the worker to move
    public abstract void move();  // Each worker will have its own movement
}
