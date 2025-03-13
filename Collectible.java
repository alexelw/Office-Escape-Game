import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Abstract class for collectible actors
 * 
 * @author Alex Watts
 * @version 1.0
 */
public abstract class Collectible extends Actor implements ICollectible {
    public abstract void applyEffectToWorker(Worker worker);
    
    // Removes collectible for the world
    public void removeFromWorld() {
        MyWorld world = (MyWorld) getWorld();
        if (world != null) {
            world.removeCollectibleFromWorld(this);
        }
    }
}

