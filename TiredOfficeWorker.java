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
    
    private int speedBoostTimer = 0; // Timer for the speed boost

    public TiredOfficeWorker() {
        super(new WASDMovement());
        this.energy = new Energy(2000);
        ((WASDMovement) movement).setWorker(this);

        animation = new AnimationHandler(this,
            "worker_idle.png",
            new String[]{"worker_walkL1.png", "worker_walkL2.png"},
            new String[]{"worker_walkR1.png", "worker_walkR2.png"},
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
        
        if (speedBoostTimer > 0) {
            speed = NORMAL_SPEED * 2; // Double speed during the boost
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
        handleAnimation();
        handleSpeedBoost(); // Handle the speed boost timer
    }

    private void handleAnimation() {
        if (chatting) {
            animation.animateTalking();
        } else {
            // Send movement direction to animation handler
            int moveDirectionX = 0;
            int moveDirectionY = 0;
            if (Greenfoot.isKeyDown("w")) moveDirectionY = -1;
            if (Greenfoot.isKeyDown("s")) moveDirectionY = 1;
            if (Greenfoot.isKeyDown("a")) moveDirectionX = -1;
            if (Greenfoot.isKeyDown("d")) moveDirectionX = 1;

            animation.animateWalking(moveDirectionX, moveDirectionY);
        }
    }

    private void regenerateEnergy() {
        if (energy.getCurrentEnergy() < energy.getMaxEnergy()) {
            energy.restoreEnergy(ENERGY_REGEN_RATE);
        }
    }

    @Override
    public void stopMovement() {
        setSpeed(0); 
        ((WASDMovement) movement).stopMoving(); 
    }

    @Override
    public void resumeMovement() {
        chatting = false;
        updateSpeed();
        ((WASDMovement) movement).resumeMoving();
    }

    // This method will handle the speed boost timer
    private void handleSpeedBoost() {
        if (speedBoostTimer > 0) {
            speedBoostTimer--;
            if (speedBoostTimer == 0) {
                updateSpeed(); // Reset the speed after the boost duration
            }
        }
    }

    // Start speed boost timer for 3 seconds
    public void startSpeedBoost(int duration) {
        speedBoostTimer = duration;
        updateSpeed();
    }
}


