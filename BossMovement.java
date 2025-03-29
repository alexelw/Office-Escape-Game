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
        double moveX = boss.getSpeed() * dx / distance;
        double moveY = boss.getSpeed() * dy / distance;

        // Determine potential new position
        int newX = (int) (bossX + moveX);
        int newY = (int) (bossY + moveY);

        // Check for walls in a more detailed area around the Boss
        if (!isWallAround(newX, newY)) {
            boss.setLocation(newX, newY);
        } else {
            // Try moving horizontally if there's no wall on the X axis
            if (!isWallAt(newX, bossY)) {
                boss.setLocation(newX, bossY);
            }
            // Try moving vertically if there's no wall on the Y axis
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
