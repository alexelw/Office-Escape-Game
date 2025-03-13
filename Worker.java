import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Abstract class for worker actors
 * 
 * @author Alex Watts 
 * @version 1.0
 */
public abstract class Worker extends Actor implements IWorker {
    protected int speed;
    private IMovement movement;
    
    // Constructor to initialise a wotker with a specific movement strategy
    public Worker(IMovement movement) {
        if (movement == null) {
            throw new IllegalArgumentException("Movement cannot be null");
        }
        this.movement = movement;
    }
    
    // Updates the speed of the worker
    public void updateSpeed(int speedChange) {
        this.speed += speedChange;
    }
    
    // Applies the effect of a collectible to the worker and removes the collectible from the world.
    public void applyEffectFromCollectible(Collectible collectible) {
        collectible.applyEffectToWorker(this);  // Apply the specific effect from the collectible
        (collectible).removeFromWorld(); // Remove the collectible from the world
    }

    // Abstract method for the worker to move
    public abstract void move();  // Each worker will have its own movement
}
