import greenfoot.*;
/**
 * Write a description of class BossMovement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;

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

        if (distance == 0) return; // Already at target position

        // Normalize movement direction
        int moveX = (int) Math.signum(dx) * boss.getSpeed();
        int moveY = (int) Math.signum(dy) * boss.getSpeed();

        boolean canMoveX = !isWallAt(bossX + moveX, bossY);
        boolean canMoveY = !isWallAt(bossX, bossY + moveY);
        boolean canMoveDiagonal = !isWallAt(bossX + moveX, bossY + moveY);

        // Step 1: Move diagonally if possible
        if (canMoveDiagonal) {
            boss.setLocation(bossX + moveX, bossY + moveY);
        }
        // Step 2: If stuck against a vertical wall, move down until free
        else if (!canMoveX && canMoveY) {
            boss.setLocation(bossX, bossY + moveY);
        }
        // Step 3: If completely blocked by a vertical wall, force downward movement until free
        else if (!canMoveX) {
            if (!isWallAt(bossX, bossY + boss.getSpeed())) {
                boss.setLocation(bossX, bossY + boss.getSpeed()); // Move down
            } else if (!isWallAt(bossX, bossY - boss.getSpeed())) {
                boss.setLocation(bossX, bossY - boss.getSpeed()); // Move up as a backup
            }
        }
        // Step 4: If horizontal movement is possible, move that way
        else if (canMoveX) {
            boss.setLocation(bossX + moveX, bossY);
        }
        // Step 5: Last resort - random movement to break out
        else {
            int randomMove = (Greenfoot.getRandomNumber(2) == 0 ? -boss.getSpeed() : boss.getSpeed());
            if (!isWallAt(bossX, bossY + randomMove)) {
                boss.setLocation(bossX, bossY + randomMove);
            } else if (!isWallAt(bossX + randomMove, bossY)) {
                boss.setLocation(bossX + randomMove, bossY);
            }
        }
    }

    private boolean isWallAt(int x, int y) {
        World world = boss.getWorld();
        return world != null && !world.getObjectsAt(x, y, wall.class).isEmpty();
    }

    public void stopMoving() {
        stopped = true;
    }

    public void resumeMoving() {
        stopped = false;
    }
}


