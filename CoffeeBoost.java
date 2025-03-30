import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CoffeeBoost here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CoffeeBoost extends Collectible {
    
    private static final int BOOST_DURATION = 180; // 3 seconds at 60 FPS

    // Apply effect to worker when collected
    public void applyEffectToWorker(Worker worker) {
        if (worker instanceof TiredOfficeWorker) {
            TiredOfficeWorker tiredWorker = (TiredOfficeWorker) worker;
            // Restore energy to full
            tiredWorker.updateEnergy(tiredWorker.getEnergyObject().getMaxEnergy() - tiredWorker.getEnergy());
            // Apply speed boost for 3 seconds
            tiredWorker.startSpeedBoost(BOOST_DURATION);

            // Update the score when the TiredOfficeWorker collects coffee
            MyWorld world = (MyWorld) getWorld();
            world.increaseScore(10);  // Add 10 points to the score (adjust as needed)
        } else if (worker instanceof Boss) {
            Boss boss = (Boss) worker;
            // Apply speed boost for 3 seconds
            boss.startSpeedBoost(BOOST_DURATION);
        }
        
        removeFromWorld(); // Remove this collectible from the world after effect is applied
    }

    // Act method is called continuously
    public void act() {
        // Detect if the worker touches this collectible
        Worker worker = (Worker) getOneIntersectingObject(Worker.class);
        if (worker != null) {
            applyEffectToWorker(worker); // Apply the boost effect
        }
    }
}

