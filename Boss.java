import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends Worker {
    private AnimationHandler animation;
    private Worker targetWorker; 

    private BossMovement bossMovement;

    public Boss(Worker targetWorker) {
        super(null); 
        this.targetWorker = targetWorker;
        this.animation = new AnimationHandler(this,
            "boss_idle.png",
            new String[]{"boss_walkL1.png", "boss_walkL2.png"},
            new String[]{"boss_walkR1.png", "boss_walkR2.png"},
            new String[]{"boss_effect1.png", "boss_effect2.png"},
            new String[]{"boss_talk1.png", "boss_talk2.png"},
            32, 32
        );
        bossMovement = new BossMovement(this, targetWorker);
        this.movement = bossMovement;
        this.speed = 1;
    }

    @Override
    public void move() {
        movement.move();
        // Example direction, you can modify it as needed
        int moveDirectionX = 0;
        int moveDirectionY = 0;
        if (Greenfoot.isKeyDown("w")) moveDirectionY = -1;
        if (Greenfoot.isKeyDown("s")) moveDirectionY = 1;
        if (Greenfoot.isKeyDown("a")) moveDirectionX = -1;
        if (Greenfoot.isKeyDown("d")) moveDirectionX = 1;

        animation.animateWalking(moveDirectionX, moveDirectionY);
    }

    @Override
    public void interactWith(Worker worker) {
        if (worker instanceof TiredOfficeWorker && intersects(worker)) {
            Greenfoot.stop();
            System.out.println("GAME OVER! The boss caught you.");
        }
    }

    @Override
    public void act() {
        move();
        checkForCoffee();
        interactWith(targetWorker);
    }

    private void checkForCoffee() {
        CoffeeBoost coffee = (CoffeeBoost) getOneIntersectingObject(CoffeeBoost.class);
        if (coffee != null) {
            applyEffectToCollectible(coffee);
        }
    }
}

