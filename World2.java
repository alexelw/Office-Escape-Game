import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class World2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class World2 extends MyWorld {

    private static final int WALL_WIDTH = 28;
    private static final int WALL_HEIGHT = 26;
    /**
     * Constructor for objects of class World2.
    /**
     * Constructor for objects of class World2.
     * 
     */
    public World2() {  
        
        TiredOfficeWorker worker = new TiredOfficeWorker();
        addObject(worker, 20, 250);
        
        layout2();
        placeCollectibles(); // Call method to place collectibles

    
    }
    
        private void layout2() {
    // Alternative office-like maze layout

    // Top horizontal wall
    for (int i = 0; i < 24; i++) {
        addObject(new wall(), 10 + i * WALL_WIDTH, 10);
    }

    // Bottom horizontal wall
    for (int i = 0; i < 20; i++) {
        addObject(new wall(), 30 + i * WALL_WIDTH, 390);
    }

    // Left vertical wall
    for (int i = 0; i < 14; i++) {
        addObject(new wall(), 10, 30 + i * WALL_HEIGHT);
    }

    // Right vertical wall
    for (int i = 0; i < 14; i++) {
        addObject(new wall(), 590, 30 + i * WALL_HEIGHT);
    }

    // Center vertical wall
    for (int i = 0; i < 6; i++) {
        addObject(new wall(), 300, 70 + i * WALL_HEIGHT);
    }

    // Left middle horizontal wall
    for (int i = 0; i < 5; i++) {
        addObject(new wall(), 100 + i * WALL_WIDTH, 200);
    }

    // Right middle horizontal wall
    for (int i = 0; i < 5; i++) {
        addObject(new wall(), 370 + i * WALL_WIDTH, 200);
    }

    // Left-side corner desk area
    addObject(new wall(), 150, 100);
    addObject(new wall(), 150, 126);
    addObject(new wall(), 180, 100);
    addObject(new wall(), 180, 126);
    addObject(new deskchair(), 165, 150);

    // Right-side corner desk area
    addObject(new wall(), 450, 100);
    addObject(new wall(), 480, 100);
    addObject(new wall(), 450, 126);
    addObject(new wall(), 480, 126);
    addObject(new deskchair(), 465, 150);

    // Bottom left desk area
    addObject(new wall(), 100, 300);
    addObject(new wall(), 130, 300);
    addObject(new wall(), 100, 326);
    addObject(new wall(), 130, 326);
    addObject(new deskchair(), 115, 350);

    // Bottom right desk area
    addObject(new wall(), 470, 300);
    addObject(new wall(), 500, 300);
    addObject(new wall(), 470, 326);
    addObject(new wall(), 500, 326);
    addObject(new deskchair(), 485, 350);
}
     //Collectible
    private void placeCollectibles() {
        addObject(new CoffeeBoost(), 250, 150);
        addObject(new CoffeeBoost(), 450, 300);
        addObject(new CoffeeBoost(), 50, 350);
        addObject(new CoffeeBoost(), 50, 200);
        addObject(new CoffeeBoost(), 560, 40);
        
        }
    }

