package com.gtnewhorizons.gtnhintergalactic.recipe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.GregTech_API;
import gregtech.api.interfaces.tileentity.IHasWorldObjectAndCoords;
import gregtech.api.objects.GT_ItemStack;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe;

/**
 * GT recipes maps of GTNH-Intergalactic
 *
 * @author minecraft7771
 */
public class IG_Recipe_Map extends GT_Recipe.GT_Recipe_Map {

    public IG_Recipe_Map(Collection<GT_Recipe> aRecipeList, String aUnlocalizedName, String aLocalName, String aNEIName,
            String aNEIGUIPath, int aUsualInputCount, int aUsualOutputCount, int aMinimalInputItems,
            int aMinimalInputFluids, int aAmperage, String aNEISpecialValuePre, int aNEISpecialValueMultiplier,
            String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed) {
        super(
                aRecipeList,
                aUnlocalizedName,
                aLocalName,
                aNEIName,
                aNEIGUIPath,
                aUsualInputCount,
                aUsualOutputCount,
                aMinimalInputItems,
                aMinimalInputFluids,
                aAmperage,
                aNEISpecialValuePre,
                aNEISpecialValueMultiplier,
                aNEISpecialValuePost,
                aShowVoltageAmperageInNEI,
                aNEIAllowed);
    }

    public List<IG_Recipe.IG_SpaceMiningRecipe> findRecipes(IHasWorldObjectAndCoords aTileEntity,
            IG_Recipe.IG_SpaceMiningRecipe aRecipe, boolean aNotUnificated, boolean aDontCheckStackSizes, long aVoltage,
            FluidStack[] aFluids, ItemStack aSpecialSlot, int distance, int moduleTier, ItemStack... aInputs) {
        // Search algorithm copied from findRecipe, just fitted to add all found recipes

        if (mRecipeList.isEmpty()) return null;

        if (GregTech_API.sPostloadFinished) {
            if (mMinimalInputFluids > 0) {
                if (aFluids == null) return null;
                int tAmount = 0;
                for (FluidStack aFluid : aFluids) if (aFluid != null) tAmount++;
                if (tAmount < mMinimalInputFluids) return null;
            }
            if (mMinimalInputItems > 0) {
                if (aInputs == null) return null;
                int tAmount = 0;
                for (ItemStack aInput : aInputs) if (aInput != null) ++tAmount;
                if (tAmount < mMinimalInputItems) return null;
            }
        }

        List<IG_Recipe.IG_SpaceMiningRecipe> recipes = new ArrayList<>();
        if (aNotUnificated) aInputs = GT_OreDictUnificator.getStackArray(true, (Object[]) aInputs);

        if (aRecipe != null && !aRecipe.mFakeRecipe
                && aRecipe.mCanBeBuffered
                && aRecipe.isRecipeInputEqual(false, aDontCheckStackSizes, aFluids, aInputs)) {
            if (aRecipe.mEnabled && aVoltage * mAmperage >= aRecipe.mEUt
                    && aRecipe.minDistance <= distance
                    && aRecipe.maxDistance >= distance
                    && aRecipe.mSpecialValue <= moduleTier) {
                recipes.add(aRecipe);
            }
        } else {
            if (mUsualInputCount > 0 && aInputs != null) for (ItemStack tStack : aInputs) if (tStack != null) {
                Collection<GT_Recipe> tRecipes = mRecipeItemMap.get(new GT_ItemStack(tStack));
                if (tRecipes != null) for (GT_Recipe recipe : tRecipes) {
                    IG_Recipe.IG_SpaceMiningRecipe tRecipe = (IG_Recipe.IG_SpaceMiningRecipe) recipe;
                    if (!tRecipe.mFakeRecipe && tRecipe
                        .isRecipeInputEqual(false, aDontCheckStackSizes, aFluids, aInputs)) {
                        if (tRecipe.mEnabled && aVoltage * mAmperage >= tRecipe.mEUt
                            && tRecipe.minDistance <= distance
                            && tRecipe.maxDistance >= distance
                            && tRecipe.mSpecialValue <= moduleTier) {
                            recipes.add(tRecipe);
                        }
                    }
                }

                tRecipes = mRecipeItemMap.get(new GT_ItemStack(tStack, true));
                if (tRecipes != null) for (GT_Recipe recipe : tRecipes) {
                    IG_Recipe.IG_SpaceMiningRecipe tRecipe = (IG_Recipe.IG_SpaceMiningRecipe) recipe;
                    if (!tRecipe.mFakeRecipe && tRecipe
                        .isRecipeInputEqual(false, aDontCheckStackSizes, aFluids, aInputs)) {
                        if (tRecipe.mEnabled && aVoltage * mAmperage >= tRecipe.mEUt
                            && tRecipe.minDistance <= distance
                            && tRecipe.maxDistance >= distance
                            && tRecipe.mSpecialValue <= moduleTier) {
                            recipes.add(tRecipe);
                        }
                    }
                }
            }

            if (mMinimalInputItems == 0 && aFluids != null) for (FluidStack aFluid : aFluids) if (aFluid != null) {
                Collection<GT_Recipe> tRecipes = mRecipeFluidMap.get(aFluid.getFluid().getName());
                if (tRecipes != null) for (GT_Recipe recipe : tRecipes) {
                    IG_Recipe.IG_SpaceMiningRecipe tRecipe = (IG_Recipe.IG_SpaceMiningRecipe) recipe;
                    if (!tRecipe.mFakeRecipe && tRecipe
                        .isRecipeInputEqual(false, aDontCheckStackSizes, aFluids, aInputs)) {
                        if (tRecipe.mEnabled && aVoltage * mAmperage >= tRecipe.mEUt
                            && tRecipe.minDistance <= distance
                            && tRecipe.maxDistance >= distance
                            && tRecipe.mSpecialValue <= moduleTier) {
                            recipes.add(tRecipe);
                        }
                    }
                }
            }
        }
        return recipes;
    }
}
