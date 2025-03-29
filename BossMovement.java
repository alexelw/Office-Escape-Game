import greenfoot.*;
/**
 * Write a description of class BossMovement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class BossMovement implements IMovement {
    private Boss boss;
    private Worker target;
    private static final int AVOID_DISTANCE = 5;

    public BossMovement(Boss boss, Worker target) {
        this.boss = boss;
        this.target = target;
    }

    @Override
    public void move() {
        if (boss == null || target == null) return;

        int bossX = boss.getX();
        int bossY = boss.getY();
        int targetX = target.getX();
        int targetY = target.getY();

        int dx = targetX - bossX;
        int dy = targetY - bossY;
        double distance = Math.hypot(dx, dy);

        if (distance != 0) {
            int moveX = (int) (boss.getSpeed() * dx / distance);
            int moveY = (int) (boss.getSpeed() * dy / distance);

            // Check wall collision
            if (!isWallAt(bossX + moveX, bossY)) {
                boss.setLocation(bossX + moveX, bossY);
            } else if (!isWallAt(bossX, bossY + moveY)) {
                boss.setLocation(bossX, bossY + moveY);
            }
        }
    }

    private boolean isWallAt(int x, int y) {
        World world = boss.getWorld();
        if (world == null) return false;
        return !world.getObjectsAt(x, y, wall.class).isEmpty();
    }
}
