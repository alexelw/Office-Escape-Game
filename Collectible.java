import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Abstract class for collectible actors.
 * This class defines the basic behavior for all collectible objects in the world.
 * Each collectible has an effect on the worker when collected, and it can be removed from the world.
 * 
 * @author Alex Watts
 * @version 1.0
 */
public abstract class Collectible extends Actor implements ICollectible {
    
    /**
     * Abstract method to apply the effect of the collectible to the worker.
     * Each subclass of Collectible should define its specific effect on the worker.
     * 
     * @param worker the worker that interacts with the collectible
     */
    public abstract void applyEffectToWorker(Worker worker);
    
    /**
     * Removes this collectible from the world.
     * Calls the method in the world to remove the collectible from the active objects.
     */
    public void removeFromWorld() {
        MyWorld world = (MyWorld) getWorld();
        if (world != null) {
            world.removeCollectibleFromWorld(this);  // Remove the collectible from the world
        }
    }
}
