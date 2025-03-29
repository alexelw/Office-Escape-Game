import greenfoot.*;
/**
 * Write a description of class BossMovement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BossMovement implements IMovement {
    private Boss boss;
    private TiredOfficeWorker target;
    private boolean stopped = false;

    public BossMovement(Boss boss, TiredOfficeWorker target) {
        this.boss = boss;
        this.target = target;
    }

    @Override
    public void move() {
        if (stopped || boss == null || target == null) return;

        int bossX = boss.getX();
        int bossY = boss.getY();
        int targetX = target.getX();
        int targetY = target.getY();

        int dx = targetX - bossX;
        int dy = targetY - bossY;
        double distance = Math.hypot(dx, dy);

        // Normalize movement direction
        double moveX = (dx / distance) * boss.getSpeed();
        double moveY = (dy / distance) * boss.getSpeed();

        // Apply movement but ensure the boss doesn’t overshoot
        int newX = (int) (bossX + moveX);
        int newY = (int) (bossY + moveY);

        // Check for walls and adjust position if needed
        if (!isWallAround(newX, newY)) {
            boss.setLocation(newX, newY);
        } else {
            // If there’s a wall, try moving horizontally first
            if (!isWallAt(newX, bossY)) {
                boss.setLocation(newX, bossY);
            }
            // If there’s a wall horizontally, try moving vertically
            else if (!isWallAt(bossX, newY)) {
                boss.setLocation(bossX, newY);
            }
        }
    }

    // Enhanced wall detection: Check a larger area around the Boss to simulate its full size
    private boolean isWallAround(int newX, int newY) {
        int bossSize = 32; // Assuming the Boss has a width and height of 32 pixels
        for (int i = -bossSize; i <= bossSize; i++) {
            for (int j = -bossSize; j <= bossSize; j++) {
                if (isWallAt(newX + i, newY + j)) {
                    return true; // Wall detected around the new position
                }
            }
        }
        return false;
    }

    // Standard wall detection method
    private boolean isWallAt(int x, int y) {
        World world = boss.getWorld();
        if (world == null) return false;

        // Check if there are any walls at these positions
        return !world.getObjectsAt(x, y, wall.class).isEmpty();
    }

    public void stopMoving() {
        stopped = true;
    }

    public void resumeMoving() {
        stopped = false;
    }
}


