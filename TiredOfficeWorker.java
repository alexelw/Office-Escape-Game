import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * tired office worker class
 * 
 * Alex Watts
 * version 1.1
 */

public class TiredOfficeWorker extends Worker {
    private Energy energy;
    private static final int NORMAL_SPEED = 2;
    private static final int SLOW_SPEED = 1;
    private static final int NO_SPEED = 0;
    private static final int LOW_ENERGY_THRESHOLD = 1000;
    private static final int ENERGY_REGEN_RATE = 4;
    private boolean chatting = false;
    
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
            32, 32
        );

     }

         
    public void setChatting(boolean chatting) {
        this.chatting = chatting;
    }
    
    public void animateTalking() {
        chatting = true;
        animation.animateTalking();
    }


    public void updateEnergy(int energyChange) {
        energy.changeEnergy(energyChange);
        updateSpeed();
    }

    public int getEnergy() {
        return energy.getCurrentEnergy();
    }

    public Energy getEnergyObject() {
        return energy;
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
        // Only move if not in chat and if energy allows movement
        if (energy.getCurrentEnergy() > 0 ) {
            movement.move();
        }
    }

    @Override
    public void interactWith(Worker worker) {
        if (worker instanceof CoWorker) {
            MyWorld world = (MyWorld) getWorld();
            if (!world.getChatHandler().isChatActive() && !world.getChatHandler().isChatOnCooldown()){
                world.getChatHandler().startChat(this, (CoWorker) worker);
            }
        } else if (worker instanceof Boss) {
            animation.animateTalking();
        }
}


    public void act() {
        move();
        regenerateEnergy();
        handleAnimation(); // Update chat interaction state
    }

    private void handleAnimation() {
        if (chatting) {
            animation.animateTalking();
        } else if (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("a") ||
            Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("d")) {
            animation.animateWalking();
        } else {
            animation.resetToIdle();
        }
    }


    private void regenerateEnergy() {
        if (energy.getCurrentEnergy() < energy.getMaxEnergy()) {
            energy.restoreEnergy(ENERGY_REGEN_RATE);
        }
    }

    @Override
    public void stopMovement() {
        setSpeed(0); // Prevent movement
        ((WASDMovement) movement).stopMoving(); // Stop WASD movement
    }

    @Override
    public void resumeMovement() {
        chatting = false;
        updateSpeed();
        ((WASDMovement) movement).resumeMoving();
    }
    

}
