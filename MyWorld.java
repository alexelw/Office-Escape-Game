import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World {
    
    private static final int WORLD_WIDTH = 600;
    private static final int WORLD_HEIGHT = 400;

    public MyWorld() {
        // Initialize the world with a specific width and height
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 

        TiredOfficeWorker worker = new TiredOfficeWorker();
        addObject(worker, 300, 200);

    }

    public void removeCollectibleFromWorld(Collectible collectible) {
        removeObject(collectible);
    }
}
