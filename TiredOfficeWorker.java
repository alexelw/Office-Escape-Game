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
    private AnimationHandler animation;

    
    public TiredOfficeWorker() {
        super(new WASDMovement());
        this.energy = new Energy(2000);
        ((WASDMovement) movement).setWorker(this);
        
        animation = new AnimationHandler(this,
            "worker_idle.png",
            new String[]{"worker_walk1.png", "worker_walk2.png"},
            new String[]{"worker_effect1.png", "worker_effect2.png"},
            new String[]{"worker_talk1.png", "worker_talk2.png"},
            32,32
        );
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

    public Energy getEnergyObject() {
        return energy;
    }
    
    @Override
    public void move() {
        if (energy.getCurrentEnergy() > 0) {
            movement.move();
        }
    }

    @Override
    public void interactWith(Worker worker) {
    if (worker instanceof TiredOfficeWorker) {
        animation.animateTalking(); // Coworker talks to coworker
    } else if (worker instanceof Boss) {
        animation.animateTalking(); // Coworker talks to boss
    }
}


    // Add the act method to control movement and other actions
    public void act() {
        move();
        regenerateEnergy();
        handleAnimation();
    }
    
        private void handleAnimation() {
        if (isMoving()) {
            animation.animateWalking();
        } else {
            animation.resetToIdle();
        }
    }
    
        private boolean isMoving() {
        return Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("a")
            || Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("d");
    }
        private void regenerateEnergy() {
            if (energy.getCurrentEnergy() < energy.getMaxEnergy()) {
                energy.restoreEnergy(ENERGY_REGEN_RATE);
            }
        }
        
        public void applyEffectToWorker() {
            animation.animateEffect(); // Animate special effect
        }
        
}
