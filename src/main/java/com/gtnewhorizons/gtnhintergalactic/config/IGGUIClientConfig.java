package com.gtnewhorizons.gtnhintergalactic.config;

import net.minecraft.client.gui.GuiScreen;

import com.gtnewhorizon.gtnhlib.config.ConfigException;
import com.gtnewhorizon.gtnhlib.config.SimpleGuiConfig;

import gregtech.api.enums.Mods;

public class IGGUIClientConfig extends SimpleGuiConfig {

    public IGGUIClientConfig(GuiScreen parentScreen) throws ConfigException {
        super(parentScreen, Mods.GTNHIntergalactic.ID, "GTNH Intergalactic", false, IGConfig.class);
    }
}
