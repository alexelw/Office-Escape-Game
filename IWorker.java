import greenfoot.*;
/**
 * Interface for worker actors
 * 
 * @author Alex Watts 
 * @version 1.0
 */
public interface IWorker
{
    //Method signature to handle speed changes
    void updateSpeed(int speedChange);
    
    // Method signature for applying effect from a collectible to a worker
    void applyEffectToCollectible(Collectible collectible);
    
    // Method signature to handle interactions between workers
    void interactWith(Worker worker);
    
    //Method signature to move the worker
    void move();
}
