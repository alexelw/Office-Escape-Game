import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Boss Class - Handles boss movement, interactions, and applying effects from collectibles.
 */
public class Boss extends Worker {
    private AnimationHandler animation;
    private Worker targetWorker;
    private BossMovement bossMovement;

    private boolean chatting = false;
    private int chatTimer = 0;

    private int speedBoostTimer = 0; // Timer for the speed boost
    private static final int NORMAL_SPEED = 1;
    private static final int BOOSTED_SPEED = 2;
    private static final int BOOST_DURATION = 180; // 3 seconds at 60 FPS

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
        this.speed = NORMAL_SPEED; // Default speed
    }

    @Override
    public void move() {
        if (chatting) {
            animation.animateTalking();
        } else {
            movement.move();
            int dx = targetWorker.getX() - this.getX();
            int dy = targetWorker.getY() - this.getY();
            int moveDirectionX = Integer.compare(dx, 0); // -1, 0, or 1
            int moveDirectionY = Integer.compare(dy, 0); // -1, 0, or 1
            animation.animateWalking(moveDirectionX, moveDirectionY);
        }
    }

    @Override
    public void interactWith(Worker worker) {
        if (worker instanceof CoWorker && intersects(worker)) {
            if (!chatting) {
                startChatWithCoWorker();
            }
        }
        if (worker instanceof TiredOfficeWorker && intersects(worker)) {
            System.out.println("GAME OVER! The boss caught you.");
            Greenfoot.stop();
        }
    }

    private void startChatWithCoWorker() {
        chatting = true;
        chatTimer = 100;
        stopMovement();

        MyWorld world = (MyWorld) getWorld();
        world.getChatHandler().startChat(this, (CoWorker) getOneIntersectingObject(CoWorker.class));
    }

    @Override
    public void act() {
        MyWorld world = (MyWorld) getWorld();
        ChatHandler chatHandler = world.getChatHandler();

        // Check for both CoWorker and TiredOfficeWorker interactions
        CoWorker coworker = (CoWorker) getOneIntersectingObject(CoWorker.class);
        TiredOfficeWorker officeWorker = (TiredOfficeWorker) getOneIntersectingObject(TiredOfficeWorker.class);

        if (coworker != null) interactWith(coworker);
        if (officeWorker != null) interactWith(officeWorker); // Fixes game-over bug

        if (chatting) {
            if (chatTimer > 0) {
                chatTimer--;
                animateTalking();
            } else {
                chatting = false;
                resumeMovement();
            }
        }

        move();
        handleSpeedBoost();
        checkForCoffee();
    }

    private void checkForCoffee() {
        CoffeeBoost coffee = (CoffeeBoost) getOneIntersectingObject(CoffeeBoost.class);
        if (coffee != null) {
            applyEffectToCollectible(coffee);
        }
    }

    private void applyEffectToCollectible(CoffeeBoost coffee) {
        // Apply effect to the Boss when it collects coffee
        coffee.applyEffectToWorker(this);
    }

    private void handleSpeedBoost() {
        if (speedBoostTimer > 0) {
            speedBoostTimer--;
            if (speedBoostTimer == 0) {
                resetSpeed(); // Reset the speed after the boost duration
            }
        }
    }

    // Apply speed boost for the boss
    private void resetSpeed() {
        speed = NORMAL_SPEED; // Reset speed to normal
    }

    // Start speed boost timer for 3 seconds
    public void startSpeedBoost(int duration) {
        speedBoostTimer = duration;
        speed = BOOSTED_SPEED; // Increase the speed temporarily
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

    public void animateTalking() {
        animation.animateTalking();
    }
}



