package com.gtnewhorizons.gtnhintergalactic.config;

import net.minecraft.client.gui.GuiScreen;

import com.gtnewhorizon.gtnhlib.config.SimpleGuiFactory;

@SuppressWarnings("unused") // referenced via string in @Mod annotation
public class IGGUIFactory implements SimpleGuiFactory {

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return IGGUIClientConfig.class;
    }
}
