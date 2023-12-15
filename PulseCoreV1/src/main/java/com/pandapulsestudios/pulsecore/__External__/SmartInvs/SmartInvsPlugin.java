package com.pandapulsestudios.pulsecore.__External__.SmartInvs;

import com.pandapulsestudios.pulsecore.PulseCore;

public class SmartInvsPlugin {

    private InventoryManager invManager;
    public InventoryManager manager() { return invManager; }

    public SmartInvsPlugin(PulseCore pandaAPI){
        invManager = new InventoryManager(pandaAPI);
        invManager.init();
    }

}
