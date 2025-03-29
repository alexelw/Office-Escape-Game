import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Boss extends Worker {
    private AnimationHandler animation;
    private Worker targetWorker; // Who the boss is chasing
    private BossMovement bossMovement;

    public Boss(Worker targetWorker) {
        super(null); // We'll set movement after initialization
        this.targetWorker = targetWorker;
        this.animation = new AnimationHandler(this,
            "boss_idle.png",
            new String[]{"boss_walk1.png", "boss_walk2.png"},
            new String[]{"boss_effect1.png", "boss_effect2.png"},
            new String[]{"boss_talk1.png", "boss_talk2.png"},
            32, 32
        );
        bossMovement = new BossMovement(this, targetWorker);
        this.movement = bossMovement;
        this.speed = 1; // Start with base speed
    }

    @Override
    public void move() {
        movement.move();
        animation.animateWalking();
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
