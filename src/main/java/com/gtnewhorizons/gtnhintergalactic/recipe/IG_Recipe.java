package com.gtnewhorizons.gtnhintergalactic.recipe;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.util.GTRecipe;
import gregtech.api.util.GTUtility;

/**
 * GT recipes of GTNH-Intergalactic. These include a needed space project optionally.
 *
 * @author minecraft7771
 */
public class IG_Recipe extends GTRecipe {

    /** Space project that is needed to be constructed to do this recipe */
    protected final String neededSpaceProject;

    /** Space project location that is needed to be constructed to do this recipe */
    protected final String neededSpaceProjectLocation;

    /**
     * Create a new recipe for the Space Research Module
     *
     * @param aOptimize     Flag if the recipe should be optimized
     * @param aInputs       Item inputs of the recipe
     * @param aOutputs      Item outputs of the recipe
     * @param aSpecialItems Special item input that is needed (potentially catalyst)
     * @param aChances      Chances for each item output
     * @param aFluidInputs  Fluid inputs of the recipe
     * @param aFluidOutputs Fluid outputs of the recipe
     * @param aDuration     Duration of the recipe in ticks
     * @param aEUt          EU consumption of the recipe per tick
     * @param aSpecialValue Special value of the recipe
     */
    public IG_Recipe(boolean aOptimize, ItemStack[] aInputs, ItemStack[] aOutputs, Object aSpecialItems, int[] aChances,
            FluidStack[] aFluidInputs, FluidStack[] aFluidOutputs, int aDuration, int aEUt, int aSpecialValue) {
        this(
                aOptimize,
                aInputs,
                aOutputs,
                aSpecialItems,
                aChances,
                aFluidInputs,
                aFluidOutputs,
                aDuration,
                aEUt,
                aSpecialValue,
                null,
                null);
    }

    /**
     * Create a new recipe for the Space Research Module
     *
     * @param aOptimize          Flag if the recipe should be optimized
     * @param aInputs            Item inputs of the recipe
     * @param aOutputs           Item outputs of the recipe
     * @param aSpecialItems      Special item input that is needed (potentially catalyst)
     * @param aChances           Chances for each item output
     * @param aFluidInputs       Fluid inputs of the recipe
     * @param aFluidOutputs      Fluid outputs of the recipe
     * @param aDuration          Duration of the recipe in ticks
     * @param aEUt               EU consumption of the recipe per tick
     * @param aSpecialValue      Special value of the recipe
     * @param neededSpaceProject Space project that is needed to do this recipe
     */
    public IG_Recipe(boolean aOptimize, ItemStack[] aInputs, ItemStack[] aOutputs, Object aSpecialItems, int[] aChances,
            FluidStack[] aFluidInputs, FluidStack[] aFluidOutputs, int aDuration, int aEUt, int aSpecialValue,
            String neededSpaceProject, String neededSpaceProjectLocation) {
        super(
                aOptimize,
                aInputs,
                aOutputs,
                aSpecialItems,
                aChances,
                aFluidInputs,
                aFluidOutputs,
                aDuration,
                aEUt,
                aSpecialValue);
        this.neededSpaceProject = neededSpaceProject;
        this.neededSpaceProjectLocation = neededSpaceProjectLocation;
    }

    /**
     * @return Space project that is needed to do this recipe
     */
    public String getNeededSpaceProject() {
        return neededSpaceProject;
    }

    /**
     * @return Space project location that is needed to do this recipe
     */
    public String getNeededSpaceProjectLocation() {
        return neededSpaceProjectLocation;
    }

    /**
     * Recipe for Space Mining in the Space Elevator
     *
     * @author minecraft7771
     */
    public static class IG_SpaceMiningRecipe extends IG_Recipe {

        public String asteroidName;
        public int minDistance;
        public int maxDistance;
        public int minSize;
        public int maxSize;
        public int computation;
        public int recipeWeight;

        public IG_SpaceMiningRecipe(boolean aOptimize, String asteroidName, ItemStack[] aItemInputs,
                ItemStack[] aItemOutputs, FluidStack[] aFluidInputs, int[] aChances, int aDuration, int aEUt,
                int computation, int minModuleTier, int minDistance, int maxDistance, int minSize, int maxSize,
                int recipeWeight) {
            super(
                    aOptimize,
                    aItemInputs,
                    aItemOutputs,
                    null,
                    aChances,
                    aFluidInputs,
                    null,
                    aDuration,
                    aEUt,
                    minModuleTier);
            this.asteroidName = asteroidName;
            this.minDistance = minDistance;
            this.maxDistance = maxDistance;
            this.minSize = minSize;
            this.maxSize = maxSize;
            this.computation = computation;
            this.recipeWeight = recipeWeight;
        }

        /**
         * Get the non localized name of the asteroid, can be used in nei and gui
         * 
         * @return Asteroid Name
         */
        public String getAsteroidName() {
            return asteroidName;
        }

        /**
         * Get the weight of the recipe
         *
         * @return Recipe weight
         */
        public int getRecipeWeight() {
            return recipeWeight;
        }

        /**
         * Compute most of the hash code. The default `IG_SpaceMiningRecipe.hashCode` makes some unchecked assumptions
         * (asteroidName is unique for output set), so `fullHashCode` exists to bypass these assumptions in case we want
         * to verify they hold
         */
        private int baseHashCode() {
            int res = 0;
            res = 31 * res + minDistance;
            res = 31 * res + maxDistance;
            res = 31 * res + minSize;
            res = 31 * res + maxSize;
            res = 31 * res + computation;
            res = 31 * res + recipeWeight;
            res = 31 * res + mSpecialValue;
            res = 31 * res + mDuration;
            res = 31 * res + mEUt;
            return res;
        }

        /**
         * Compute the hash code, assuming that asteroidName is the same if and only if output item sets are the same.
         * Even if this is false, it is still correct for two objects that are not `.equals` to have the same hash code.
         */
        public int hashCode() {
            return 31 * 31 * baseHashCode() + 31 * GTUtility.ItemId.createWithoutNBT(mInputs[0]).hashCode()
                    + asteroidName.hashCode();
        }

        /**
         * Compute the hash code, including an order-invariant hash of the output item set. ONLY USE THIS IF YOU ARE
         * TESTING RECIPE GENERATION OR SOMETHING. It is unnecessarily expensive most of the time, just use `.hashCode`
         */
        public int fullHashCode() {
            int res = baseHashCode();
            res = 31 * res + GTUtility.ItemId.createWithoutNBT(mInputs[0]).hashCode();
            // We don't care about the order of the output items, so we compute the first five sums
            // of powers of the hashes of the items. This is obviously order invariant, but highly sensitive
            // to changes of item hashes, which is what we want. Five is more than we need but whatever
            int[] moments = Arrays.stream(mOutputs).reduce(new int[5], (a, item) -> {
                int ph = item == null ? 7 : GTUtility.ItemId.createNoCopy(item).hashCode();
                int x = ph;
                for (int i = 0; i < a.length; ++i) {
                    a[i] += x;
                    x *= ph;
                }
                return a;
            }, (a, b) -> {
                for (int i = 0; i < a.length; ++i) {
                    a[i] += b[i];
                }
                return a;
            });
            for (int ph : moments) {
                res = 31 * res + ph;
            }
            return res;
        }

        /**
         * Determine if two recipes are possibly equal. The default `IG_SpaceMiningRecipe.equals` makes some unchecked
         * assumptions (asteroidName is unique for output set), so `fullEquals` exists to bypass these assumptions in
         * case we want to verify they hold
         */
        private boolean baseEquals(Object _other) {
            if (!(_other instanceof IG_SpaceMiningRecipe)) {
                return false;
            }
            IG_SpaceMiningRecipe other = (IG_SpaceMiningRecipe) _other;
            if (minDistance != other.minDistance || maxDistance != other.maxDistance
                    || minSize != other.minSize
                    || maxSize != other.maxSize
                    || computation != other.computation
                    || recipeWeight != other.recipeWeight
                    || mSpecialValue != other.mSpecialValue
                    || mEUt != other.mEUt) {
                return false;
            }
            return true;
        }

        /**
         * Check if two space mining recipes are equal, assuming that asteroidName is the same if and only if output
         * item sets are the same. This should always be the case.
         */
        public boolean equals(Object _other) {
            if (!baseEquals(_other)) {
                return false;
            }
            IG_SpaceMiningRecipe other = (IG_SpaceMiningRecipe) _other;
            return asteroidName.equals(other.asteroidName)
                    && GTUtility.ItemId.createWithoutNBT(mInputs[0]).equals(other.mInputs[0]);
        }

        /**
         * Check if two space mining recipes are equal, including an order-invariant comparison of the output item set.
         * ONLY USE THIS IF YOU ARE TESTING RECIPE GENERATION OR SOMETHING. It is unnecessarily expensive most of the
         * time, just use `.equals`
         */
        public boolean fullEquals(Object _other) {
            if (!baseEquals(_other)) {
                return false;
            }
            IG_SpaceMiningRecipe other = (IG_SpaceMiningRecipe) _other;
            Collector<ItemStack, ?, Map<GTUtility.ItemId, Long>> collector = Collectors
                    .toMap(GTUtility.ItemId::createNoCopy, input -> (long) input.stackSize, (a, b) -> a + b);
            if (!Arrays.stream(mInputs).filter(Objects::nonNull).collect(collector)
                    .equals(Arrays.stream(other.mInputs).filter(Objects::nonNull).collect(collector))) {
                return false;
            }
            return Arrays.stream(mOutputs).filter(Objects::nonNull).collect(collector)
                    .equals(Arrays.stream(other.mOutputs).filter(Objects::nonNull).collect(collector));
        }
    }
}

