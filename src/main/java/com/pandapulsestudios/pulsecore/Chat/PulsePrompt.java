package com.pandapulsestudios.pulsecore.Chat;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;
import oshi.util.tuples.Triplet;

import java.util.HashMap;
import java.util.function.Consumer;

public class PulsePrompt extends StringPrompt {

    private final String promptText;
    private final Consumer<Triplet<Player, String, ConversationContext>> onResponseCallback;
    private final Consumer<Triplet<Player, String, ConversationContext>> onConversationRestartCallback;
    private final Consumer<Triplet<Player, String, ConversationContext>> onEndConversationCallback;
    private final boolean clearPlayerChatOnRestart;
    private final boolean clearPlayerChatOnEnd;
    private final boolean translateColorCodes;
    private final boolean translateHexCodes;

    public PulsePrompt(String promptText, boolean clearPlayerChatOnRestart, boolean clearPlayerChatOnEnd,
                       boolean translateColorCodes, boolean translateHexCodes,
                       Consumer<Triplet<Player, String, ConversationContext>> onResponseCallback,
                       Consumer<Triplet<Player, String, ConversationContext>> onConversationRestartCallback,
                       Consumer<Triplet<Player, String, ConversationContext>> onEndConversationCallback) {
        this.promptText = promptText;
        this.onResponseCallback = onResponseCallback;
        this.onConversationRestartCallback = onConversationRestartCallback;
        this.onEndConversationCallback = onEndConversationCallback;
        this.clearPlayerChatOnRestart = clearPlayerChatOnRestart;
        this.clearPlayerChatOnEnd = clearPlayerChatOnEnd;
        this.translateColorCodes = translateColorCodes;
        this.translateHexCodes = translateHexCodes;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        return ChatAPI.FormatMessage(promptText, translateColorCodes, translateHexCodes);
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        if (!input.isBlank() && context.getForWhom() instanceof Player player) {
            onResponseCallback.accept(new Triplet<>(player, input, context));
            if((boolean) context.getAllSessionData().getOrDefault("END", false)){
                if(clearPlayerChatOnEnd) for(var i = 0; i < 100; i++) context.getForWhom().sendRawMessage("");
                onEndConversationCallback.accept(new Triplet<>(player, input, context));
                PulseCore.allConversations.remove(player.getUniqueId());
                return END_OF_CONVERSATION;
            }
            if(clearPlayerChatOnRestart) for(var i = 0; i < 100; i++) context.getForWhom().sendRawMessage("");
            onConversationRestartCallback.accept(new Triplet<>(player, input, context));
        }
        return this;
    }

    public static Builder PulsePromptBuilder(){return new Builder();}
    public static class Builder {
        private HashMap<Object, Object> defaultData = new HashMap<>();
        private Consumer<Triplet<Player, String, ConversationContext>> onResponseCallback = (triplet) -> {};
        private Consumer<Triplet<Player, String, ConversationContext>> onConversationRestartCallback = (triplet) -> {};
        private Consumer<Triplet<Player, String, ConversationContext>> onEndConversationCallback = (triplet) -> {};
        private String promptText = "|Enter text: |";
        private boolean clearPlayerChatOnStart = true;
        private boolean clearPlayerChatOnRestart = true;
        private boolean clearPlayerChatOnEnd = true;
        private boolean translateColorCodes = true;
        private boolean translateHexCodes = true;

        public Builder addDefaultData(String defaultDataKey, String defaultDataValue){
            defaultData.put(defaultDataKey, defaultDataValue);
            return this;
        }

        public Builder promptText(String promptText){
            this.promptText = promptText;
            return this;
        }

        public Builder onResponseCallback(Consumer<Triplet<Player, String, ConversationContext>> onResponseCallback) {
            this.onResponseCallback = onResponseCallback;
            return this;
        }

        public Builder onConversationRestartCallback(Consumer<Triplet<Player, String, ConversationContext>> onConversationRestartCallback) {
            this.onConversationRestartCallback = onConversationRestartCallback;
            return this;
        }

        public Builder onEndConversationCallback(Consumer<Triplet<Player, String, ConversationContext>> onEndConversationCallback) {
            this.onEndConversationCallback = onEndConversationCallback;
            return this;
        }

        public Builder clearPlayerChatOnStart(boolean clearPlayerChatOnStart){
            this.clearPlayerChatOnStart = clearPlayerChatOnStart;
            return this;
        }

        public Builder clearPlayerChatOnRestart(boolean clearPlayerChatOnRestart){
            this.clearPlayerChatOnRestart = clearPlayerChatOnRestart;
            return this;
        }

        public Builder clearPlayerChatOnEnd(boolean clearPlayerChatOnEnd){
            this.clearPlayerChatOnEnd = clearPlayerChatOnEnd;
            return this;
        }

        public Builder translateColorCodes(boolean translateColorCodes){
            this.translateColorCodes = translateColorCodes;
            return this;
        }

        public Builder translateHexCodes(boolean translateHexCodes){
            this.clearPlayerChatOnEnd = clearPlayerChatOnEnd;
            return this;
        }

        public void StartConversation(Player targetPlayer, boolean overrideExisting){
            var storedConversation = PulseCore.allConversations.getOrDefault(targetPlayer.getUniqueId(), null);
            if(storedConversation == null || overrideExisting){
                if(storedConversation != null) storedConversation.abandon();
                var conversationFactory = new ConversationFactory(PulseCore.Instance);
                storedConversation = conversationFactory.withInitialSessionData(defaultData)
                        .withFirstPrompt(new PulsePrompt(promptText, clearPlayerChatOnRestart, clearPlayerChatOnEnd, translateColorCodes, translateHexCodes,
                                onResponseCallback, onConversationRestartCallback, onEndConversationCallback))
                        .buildConversation(targetPlayer);
                PulseCore.allConversations.put(targetPlayer.getUniqueId(), storedConversation);
                if(clearPlayerChatOnStart) for(var i = 0; i < 100; i++) targetPlayer.sendMessage("");
                storedConversation.begin();
            }
        }
    }
}
