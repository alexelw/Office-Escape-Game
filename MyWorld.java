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
    private static final int WALL_WIDTH = 28;
    private static final int WALL_HEIGHT = 26;

    public MyWorld() {
        // Initialize the world with a specific width and height
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 

        TiredOfficeWorker worker = new TiredOfficeWorker();
        addObject(worker, 300, 200);
        
        layout();

    }

    public void removeCollectibleFromWorld(Collectible collectible) {
        removeObject(collectible);
    }
    
    private void layout() {
        // Create office-like maze walls (desks, partitions, etc.)
        
        // Top horizontal wall
        for (int i = 0; i < 25; i++) {
            addObject(new wall(), 10 + i * WALL_WIDTH, 10);
        }

        // 1st left vertical wall
        for (int i = 0; i < 3; i++) {
            addObject(new wall(), 10, 30 + i * WALL_HEIGHT);
        }
        
        // 2nd left vertical wall
        for (int i = 0; i < 10; i++) {
            addObject(new wall(), 10, 160 + i * WALL_HEIGHT);
        }

        // Right vertical wall
        for (int i = 0; i < 15; i++) {
            addObject(new wall(), 590, 30 + i * WALL_HEIGHT);
        }

        // Bottom horizontal wall
        for (int i = 0; i < 25; i++) {
            addObject(new wall(), 10 + i * WALL_WIDTH, 390);
        }

        // Create small desk areas to mimick office space
        addObject(new wall(), 150, 100);
        addObject(new wall(), 178, 100);
        addObject(new wall(), 206, 100);
        addObject(new wall(), 206, 126);
        addObject(new wall(), 206, 152);
    }
}


