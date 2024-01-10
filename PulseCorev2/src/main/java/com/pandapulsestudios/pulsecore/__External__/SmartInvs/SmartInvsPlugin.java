package com.pandapulsestudios.pulsecore.__External__.SmartInvs;

import com.pandapulsestudios.pulsecore.PulseCoreMain;

public class SmartInvsPlugin {

    private InventoryManager invManager;
    public InventoryManager manager() { return invManager; }

    public SmartInvsPlugin(PulseCoreMain pandaAPI){
        invManager = new InventoryManager(pandaAPI);
        invManager.init();
    }

}
