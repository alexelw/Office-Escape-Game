import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Represents a CoffeeBoost collectible in the game.
 * When collected, it restores the worker's energy and applies a speed boost.
 * 
 * @Olivia-Melody Adenyin
 * @version 1.2
 */
public class CoffeeBoost extends Collectible {
    
    // Constant for the duration of the speed boost effect
    private static final int BOOST_DURATION = 180; 
    
    /**
     * Applies the effect of the CoffeeBoost to the worker.
     * Restores the worker's energy and applies a speed boost.
     * 
     * @param worker the worker who collects the CoffeeBoost
     */
    public void applyEffectToWorker(Worker worker) {
        // Check if the worker is a TiredOfficeWorker
        if (worker instanceof TiredOfficeWorker) {
            TiredOfficeWorker tiredWorker = (TiredOfficeWorker) worker;
            
            // Restore energy to full
            tiredWorker.updateEnergy(tiredWorker.getEnergyObject().getMaxEnergy() - tiredWorker.getEnergy());
            
            // Apply a speed boost for a duration of BOOST_DURATION
            tiredWorker.startSpeedBoost(BOOST_DURATION);

            // Update the score when the TiredOfficeWorker collects coffee
            MyWorld world = (MyWorld) getWorld();
            world.addPoints(10);  // Add 10 points to the score (adjust as needed)
        } 
        // Check if the worker is a Boss
        else if (worker instanceof Boss) {
            Boss boss = (Boss) worker;
            
            // Apply a speed boost for the Boss as well
            boss.startSpeedBoost(BOOST_DURATION);
        }
        
        // Remove the CoffeeBoost from the world after the effect is applied
        removeFromWorld();
    }

    /**
     * The act method is called continuously in the game loop.
     * Detects if a worker intersects with the CoffeeBoost and applies the effect.
     */
    public void act() {
        // Detect if a worker is touching this CoffeeBoost
        Worker worker = (Worker) getOneIntersectingObject(Worker.class);
        if (worker != null) {
            // Apply the effect to the worker if they intersect with the CoffeeBoost
            applyEffectToWorker(worker);
        }
    }
}
