package com.gtnewhorizons.gtnhintergalactic.recipe;

import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SpaceMiningData {

    public final int minDistance;
    public final int maxDistance;
    public final int minSize;
    public final int maxSize;
    public final int computation;
    public final int recipeWeight;

    public SpaceMiningData(int minDistance, int maxDistance, int minSize, int maxSize, int computation,
            int recipeWeight) {
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.computation = computation;
        this.recipeWeight = recipeWeight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minDistance, maxDistance, minSize, maxSize, computation, recipeWeight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpaceMiningData that = (SpaceMiningData) o;

        if (minDistance != that.minDistance) return false;
        if (maxDistance != that.maxDistance) return false;
        if (minSize != that.minSize) return false;
        if (maxSize != that.maxSize) return false;
        if (computation != that.computation) return false;
        return recipeWeight == that.recipeWeight;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("minDistance", minDistance).append("maxDistance", maxDistance)
                .append("minSize", minSize).append("maxSize", maxSize).append("computation", computation)
                .append("recipeWeight", recipeWeight).toString();
    }
}
