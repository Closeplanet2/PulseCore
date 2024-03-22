package com.pandapulsestudios.pulsecore.Block.Interface;

import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.MessageType;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public interface PersistentDataCallbacks {
    List<String> BlockHasTags();
    Material BlockIsMaterial();
}
