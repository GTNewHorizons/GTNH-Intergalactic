package com.gtnewhorizons.gtnhintergalactic.tile.multi.elevatormodules;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import gregtech.api.enums.GTValues;
import net.minecraft.util.EnumChatFormatting;

import gregtech.api.objects.overclockdescriber.EUNoOverclockDescriber;
import gregtech.api.util.GTRecipe;
import gregtech.api.util.MethodsReturnNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ModuleOverclockDescriber extends EUNoOverclockDescriber {

    private final int moduleTier;

    public ModuleOverclockDescriber(byte tier, int moduleTier) {
        super(tier, 1);
        this.moduleTier = moduleTier;
    }

    @Nonnull
    @Override
    public String getTierString() {
        return GTValues.TIER_COLORS[tier] + "MK " + moduleTier + EnumChatFormatting.RESET;
    }

    @Override
    public boolean canHandle(GTRecipe recipe) {
        return super.canHandle(recipe) && this.moduleTier >= recipe.mSpecialValue;
    }
}
