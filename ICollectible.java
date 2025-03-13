import greenfoot.*;
/**
 * Interface for Collectible actors
 * 
 * @author Alex Watts
 * @version 1.0
 */
public interface ICollectible  
{
    // Method signature for applying effect of the collectible to a worker
    void applyEffectToWorker(Worker worker);
}
