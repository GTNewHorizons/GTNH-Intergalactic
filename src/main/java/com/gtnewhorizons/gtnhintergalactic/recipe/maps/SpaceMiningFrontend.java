package com.gtnewhorizons.gtnhintergalactic.recipe.maps;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import com.gtnewhorizons.gtnhintergalactic.recipe.IGRecipeMaps;
import com.gtnewhorizons.gtnhintergalactic.recipe.SpaceMiningData;
import com.gtnewhorizons.modularui.api.math.Pos2d;

import gregtech.api.recipe.BasicUIPropertiesBuilder;
import gregtech.api.recipe.NEIRecipePropertiesBuilder;
import gregtech.api.recipe.RecipeMapFrontend;
import gregtech.api.util.GTUtility;
import gregtech.api.util.MethodsReturnNonnullByDefault;
import gregtech.common.gui.modularui.UIHelper;
import gregtech.nei.RecipeDisplayInfo;
import gregtech.nei.formatter.INEISpecialInfoFormatter;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class SpaceMiningFrontend extends RecipeMapFrontend {

    public SpaceMiningFrontend(BasicUIPropertiesBuilder uiPropertiesBuilder,
            NEIRecipePropertiesBuilder neiPropertiesBuilder) {
        super(
                uiPropertiesBuilder,
                neiPropertiesBuilder.neiSpecialInfoFormatter(new SpaceMiningSpecialValueFormatter()));
    }

    @Override
    public List<Pos2d> getItemInputPositions(int itemInputCount) {
        List<Pos2d> results = new ArrayList<>();
        // assume drones are present on first input
        results.add(new Pos2d(143, 15));
        results.addAll(UIHelper.getGridPositions(itemInputCount - 1, 10, 6, 2));
        return results;
    }

    @Override
    public List<Pos2d> getItemOutputPositions(int itemOutputCount) {
        return UIHelper.getGridPositions(itemOutputCount, 69, 6, 4);
    }

    @Override
    public List<Pos2d> getFluidInputPositions(int fluidInputCount) {
        return UIHelper.getGridPositions(fluidInputCount, 10, 51, fluidInputCount);
    }

    private static class SpaceMiningSpecialValueFormatter implements INEISpecialInfoFormatter {

        @Override
        public List<String> format(RecipeDisplayInfo recipeInfo) {
            List<String> result = new ArrayList<>();
            result.add(GCCoreUtil.translateWithFormat("ig.nei.module", recipeInfo.recipe.mSpecialValue));

            SpaceMiningData data = recipeInfo.recipe.getMetadata(IGRecipeMaps.SPACE_MINING_DATA);
            if (data != null) {
                result.add(
                        GCCoreUtil.translate("ig.nei.spacemining.distance") + " "
                                + data.minDistance
                                + "-"
                                + data.maxDistance);
                result.add(GCCoreUtil.translate("ig.nei.spacemining.size") + " " + data.minSize + "-" + data.maxSize);
                result.add(
                        GCCoreUtil.translateWithFormat(
                                "tt.nei.research.min_computation",
                                GTUtility.formatNumbers(data.computation)));
                result.add(GCCoreUtil.translate("ig.nei.spacemining.weight") + " " + data.recipeWeight);
            }
            return result;
        }
    }

}
