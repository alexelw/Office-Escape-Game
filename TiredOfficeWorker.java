import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TiredOfficeWorker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class TiredOfficeWorker extends Worker {
    private Energy energy;
    private static final int NORMAL_SPEED = 2;
    private static final int SLOW_SPEED = 1;
    private static final int NO_SPEED = 0;
    //threshold for low energy to slow movement
    private static final int LOW_ENERGY_THRESHOLD = 1000;
    //rate energy regenerates
    private static final int ENERGY_REGEN_RATE = 4;

    private boolean isMoving = false;
    
    public TiredOfficeWorker() {
        super(new WASDMovement());
        this.energy = new Energy(2000);
        ((WASDMovement) movement).setWorker(this);
    }

    public void updateEnergy(int energyChange) {
        energy.changeEnergy(energyChange);
        updateSpeed();  // Combine energy change and speed update
    }

    public int getEnergy() {
        return energy.getCurrentEnergy();
    }

    private void updateSpeed() {
        int currentEnergy = energy.getCurrentEnergy();
        int speed;

        if (currentEnergy <= LOW_ENERGY_THRESHOLD && currentEnergy > 0) {
            speed = SLOW_SPEED;
        } else if (currentEnergy == 0) {
            speed = NO_SPEED;
        } else {
            speed = NORMAL_SPEED;
        }
        setSpeed(speed);
    }

    
    @Override
    public void move() {
        if (energy.getCurrentEnergy() > 0) {
            movement.move();
        }
    }

    @Override
    public void interactWith(Worker worker) {
        // Interaction logic remains the sameapplyEffect
    }

    // Add the act method to control movement and other actions
    public void act() {
        move();  // Handle movement logic
        regenerateEnergy();  // Energy regeneration
    }
    
    private void regenerateEnergy() {
        if (energy.getCurrentEnergy() < energy.getMaxEnergy()) {
            energy.restoreEnergy(ENERGY_REGEN_RATE);
        }
    }
}
