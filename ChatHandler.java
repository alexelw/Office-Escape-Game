import greenfoot.*; 
import java.util.*;

/**
 * Manages chat interactions between workers and bosses.
 * 
 * @author Alex Watts
 * @version 1.2
 */
public class ChatHandler {
    private static final int CHAT_DURATION = 100;  // Chat duration in ticks
    private static final int DISABLE_DURATION = 200;  // Cooldown duration in ticks

    private List<ChatInteraction> activeChats = new ArrayList<>();
    private List<ChatInteraction> cooldownChats = new ArrayList<>();

    /**
     * Starts a chat between a coworker and a tired office worker.
     */
    public void startChat(CoWorker worker1, TiredOfficeWorker worker2) {
        if (!isChatActive(worker1, worker2) && !isChatOnCooldown(worker1, worker2)) {
            ChatInteraction chat = new ChatInteraction(worker1, worker2, CHAT_DURATION, DISABLE_DURATION);
            activeChats.add(chat);
            worker1.stopMovement();
            worker2.stopMovement();
        }
    }
    
    /**
     * Starts a chat between a coworker and the boss.
     */
    public void startChat(CoWorker worker, Boss boss) {
        if (!isChatActive(worker, boss) && !isChatOnCooldown(worker, boss)) {
            ChatInteraction chat = new ChatInteraction(worker, boss, CHAT_DURATION, DISABLE_DURATION);
            activeChats.add(chat);
            worker.stopMovement();
            boss.stopMovement();
        }
    }

    /**
     * Updates chat interactions, moving finished chats to cooldown.
     */
    public void update() {
        List<ChatInteraction> finishedChats = new ArrayList<>();

        for (ChatInteraction chat : activeChats) {
            chat.update();
            if (chat.isFinished()) {
                chat.endChat();
                finishedChats.add(chat);
                cooldownChats.add(chat);
            }
        }

        activeChats.removeAll(finishedChats);
        cooldownChats.removeIf(ChatInteraction::isCooldownOver);
    }

    /**
     * Checks if a chat is currently active between two workers.
     */
    public boolean isChatActive(Worker w1, Worker w2) {
        return activeChats.stream().anyMatch(chat -> chat.involves(w1, w2));
    }

    /**
     * Checks if a chat is on cooldown between two workers.
     */
    public boolean isChatOnCooldown(Worker w1, Worker w2) {
        return cooldownChats.stream().anyMatch(chat -> chat.involves(w1, w2));
    }
}
