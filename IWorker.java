import greenfoot.*;

/**
 * Interface for worker actors in the game.
 * 
 * Defines the required methods for worker-related behaviors like speed, interaction,
 * applying collectible effects, movement, and managing movement state.
 * 
 * @author Alex Watts 
 * @version 1.1
 */
public interface IWorker {
    
    /**
     * Method signature for updating the speed of a worker.
     * 
     * @param speedChange the change in speed (positive or negative)
     */
    void updateSpeed(int speedChange);
    
    /**
     * Method signature for applying the effect from a collectible to the worker.
     * 
     * @param collectible the collectible that will affect the worker
     */
    void applyEffectToCollectible(Collectible collectible);
    
    /**
     * Method signature for handling interactions between workers.
     * 
     * @param worker the other worker with which this worker will interact
     */
    void interactWith(Worker worker);
    
    /**
     * Method signature to move the worker.
     */
    void move();
    
    /**
     * Method signature to stop the worker's movement.
     * 
     * Can be overridden by subclasses for specific stop behavior.
     */
    void stopMovement();
    
    /**
     * Method signature to resume the worker's movement.
     * 
     * Can be overridden by subclasses for specific resume behavior.
     */
    void resumeMovement();
    
    /**
     * Method signature to animate the worker's talking state.
     * 
     * Default implementation does nothing but can be overridden by subclasses.
     */
    void animateTalking();
    
}
