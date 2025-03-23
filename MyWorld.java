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
        
        // 2nd top horizontal wall
        for (int i = 0; i < 15; i++) {
            addObject(new wall(), 100 + i * WALL_WIDTH, 80);
        }
        
        // 3rd top horizontal wall
        for (int i = 0; i < 15; i++) {
            addObject(new wall(), 100 + i * WALL_WIDTH, 270);
        }
        
        // 4th horizontal wall
        for (int i = 0; i < 3; i++) {
            addObject(new wall(), 280 + i * WALL_WIDTH, 150);
        }

        // 1st left vertical wall
        for (int i = 0; i < 3; i++) {
            addObject(new wall(), 10, 30 + i * WALL_HEIGHT);
        }
        
        // 2nd left vertical wall
        for (int i = 0; i < 10; i++) {
            addObject(new wall(), 10, 160 + i * WALL_HEIGHT);
        }
        
        // 3rd vertical wall
        for (int i = 0; i < 5; i++) {
            addObject(new wall(), 100, 160 + i * WALL_HEIGHT);
        }
        
        // 4th vertical wall
        for (int i = 0; i < 4; i++) {
            addObject(new wall(), 300, 170 + i * WALL_HEIGHT);
        }
        
        // Right vertical wall
        for (int i = 0; i < 15; i++) {
            addObject(new wall(), 590, 30 + i * WALL_HEIGHT);
        }

        // Bottom horizontal wall
        for (int i = 0; i < 12; i++) {
            addObject(new wall(), 10 + i * WALL_WIDTH, 390);
        }
        
        // Bottom horizontal wall 2
        for (int i = 0; i < 10; i++) {
            addObject(new wall(), 400 + i * WALL_WIDTH, 390);
        }

        //small desk areas for office space
        addObject(new wall(), 178, 100);
        addObject(new wall(), 178, 126);
        addObject(new wall(), 178, 152);
        addObject(new wall(), 207, 100);
        addObject(new wall(), 207, 126);
        addObject(new wall(), 207, 152);
        
        //small desk areas for office space
        addObject(new wall(), 178, 300);
        addObject(new wall(), 178, 326);
        addObject(new wall(), 207, 300);
        addObject(new wall(), 207, 326);
        addObject(new wall(), 40, 160);
        addObject(new wall(), 70, 160);
        
        
        //small desk areas for office space
        addObject(new wall(), 428, 100);
        addObject(new wall(), 428, 126);
        addObject(new wall(), 428, 152);
        addObject(new wall(), 457, 100);
        addObject(new wall(), 457, 126);
        addObject(new wall(), 457, 152);
        addObject(new wall(), 428, 178);
        addObject(new wall(), 457, 178);
        addObject(new wall(), 560, 200);
        addObject(new wall(), 530, 200);
    }
}


