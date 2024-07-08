package com.gtnewhorizons.gtnhintergalactic.loader;

import com.github.bartimaeusnek.bartworks.system.material.Werkstoff;
import com.github.bartimaeusnek.bartworks.system.material.Werkstoff.GenerationFeatures;
import com.github.bartimaeusnek.bartworks.system.material.Werkstoff.Stats;
import com.github.bartimaeusnek.bartworks.system.material.Werkstoff.Types;
import com.github.bartimaeusnek.bartworks.util.BW_Util;

import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TextureSet;

public class GSMaterials implements Runnable {

    private static final int OFFSET = 11500;

    public static final Werkstoff liquidHelium = new Werkstoff(
            new short[] { 210, 230, 250 },
            "Liquid Helium",
            "He",
            new Stats().setBoilingPoint(4).setGas(false).setMeltingPoint(1),
            Types.MATERIAL,
            new GenerationFeatures().disable().addCells(),
            OFFSET,
            TextureSet.SET_FLUID);

    public static final Werkstoff hafniumCarbide = new Werkstoff(
            new short[] { 125, 135, 125 },
            "Hafnium Carbide",
            "HfC",
            new Stats().setMass(192),
            Types.COMPOUND,
            new GenerationFeatures().onlyDust(),
            OFFSET + 1,
            TextureSet.SET_METALLIC);

    public static final Werkstoff tantalumCarbideHafniumCarbideMixture = new Werkstoff(
            new short[] { 75, 85, 75 },
            "Tantalum Carbide / Hafnium Carbide Mixture",
            BW_Util.subscriptNumbers("(TaC)4HfC"),
            new Stats(),
            Types.COMPOUND,
            new GenerationFeatures().onlyDust(),
            OFFSET + 2,
            TextureSet.SET_METALLIC);

    public static final Werkstoff tantalumHafniumCarbide = new Werkstoff(
            new short[] { 80, 90, 80 },
            "Tantalum Hafnium Carbide",
            BW_Util.subscriptNumbers("Ta4HfC5"),
            new Stats().setMass(192).setMass(962).setMeltingPoint(4263),
            Types.COMPOUND,
            new GenerationFeatures().onlyDust().addMetalItems().addMolten().setBlacklist(OrePrefixes.plate),
            OFFSET + 3,
            TextureSet.SET_METALLIC);

    @Override
    public void run() {}
}
