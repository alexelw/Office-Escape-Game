import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CoffeeBoost here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CoffeeBoost extends Collectible {

    // Apply effect to worker when collected
    public void applyEffectToWorker(Worker worker) {
        worker.updateSpeed(2); // Increase worker speed by 2
        removeFromWorld(); // Remove this collectible from the world after effect is applied
        
        // Increase score
        MyWorld world = (MyWorld) getWorld();
        if (world != null) {
            world.addPoints(10); // Increase score by 10
        }
    }

    // Act method is called continuously
    public void act() {
        // Detect if the worker touches this collectible
        Worker worker = (Worker) getOneIntersectingObject(Worker.class);
        if (worker != null) {
            applyEffectToWorker(worker); // Apply the speed boost
        }
    }
}
