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

        // Improved wall check: Check more points around the boss for collision
        if (!isWallAt(newX, bossY) && !isWallAt(bossX, newY)) {
            // Move boss if there are no walls in both horizontal and vertical directions
            boss.setLocation(newX, newY);
        } else {
            // Try horizontal movement if no wall is blocking on X-axis
            if (!isWallAt(newX, bossY)) {
                boss.setLocation(newX, bossY);
            } 
            // Try vertical movement if no wall is blocking on Y-axis
            else if (!isWallAt(bossX, newY)) {
                boss.setLocation(bossX, newY);
            }
        }
    }

    // Improved wall detection to avoid clipping
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
