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

    private boolean chatting = false;
    private int chatTimer = 0;

    public Boss(TiredOfficeWorker targetWorker) {
        super(new DummyMovement()); // Temporary, replaced below
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
        this.speed = 2; // Adjust speed if you want
    }

    @Override
    public void move() {
        if (chatting) {
            animation.animateTalking();
        } else {
            movement.move();
            int dx = targetWorker.getX() - this.getX();
            int dy = targetWorker.getY() - this.getY();
            int moveDirectionX = (dx < 0) ? -1 : (dx > 0) ? 1 : 0;
            int moveDirectionY = (dy < 0) ? -1 : (dy > 0) ? 1 : 0;
            animation.animateWalking(moveDirectionX, moveDirectionY);
        }
    }

    @Override
    public void interactWith(Worker worker) {
        if (worker instanceof TiredOfficeWorker && intersects(worker)) {
            Greenfoot.stop();
            System.out.println("GAME OVER! The boss caught you.");
        }
        if (worker instanceof CoWorker && intersects(worker)) {
            if (!chatting) {
                startChatWithCoWorker();
            }
        }
    }

    private void startChatWithCoWorker() {
        chatting = true;
        chatTimer = 100;
        stopMovement();
    }

    @Override
    public void act() {
        CoWorker coworker = (CoWorker) getOneIntersectingObject(CoWorker.class);
        if (coworker != null) {
            interactWith(coworker);
        }
        interactWith(targetWorker);

        if (chatting) {
            if (chatTimer > 0) {
                chatTimer--;
            } else {
                chatting = false;
                resumeMovement();
            }
        }

        move();
        checkForCoffee();
    }

    private void checkForCoffee() {
        CoffeeBoost coffee = (CoffeeBoost) getOneIntersectingObject(CoffeeBoost.class);
        if (coffee != null) {
            applyEffectToCollectible(coffee);
        }
    }

    @Override
    public void stopMovement() {
        if (bossMovement != null) {
            bossMovement.stopMoving();
        }
    }

    @Override
    public void resumeMovement() {
        if (bossMovement != null) {
            bossMovement.resumeMoving();
        }
    }
}
