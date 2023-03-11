package galaxyspace.core.recipe;

import static gregtech.api.enums.GT_Values.RA;
import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;

import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.core.util.RecipeUtil;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.github.bartimaeusnek.bartworks.common.loaders.ItemRegistry;
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.github.technus.tectech.recipe.TT_recipeAdder;
import com.github.technus.tectech.thing.CustomItemList;

import cpw.mods.fml.common.registry.GameRegistry;
import galaxyspace.BarnardsSystem.BRBlocks;
import galaxyspace.core.register.GSBlocks;
import galaxyspace.core.register.GSItems;
import galaxyspace.core.register.GSMaterials;
import gregtech.api.GregTech_API;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.objects.GT_ItemStack;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe.GT_Recipe_Map;
import gregtech.api.util.GT_Utility;
import gregtech.common.items.GT_MetaGenerated_Item_01;

public class CraftingRecipes {

    public static void loadRecipes() {
        Fluid moltenRhugnor = FluidRegistry.getFluid("molten.rhugnor"); // GTPP
        Fluid moltenRadoxPolymer = FluidRegistry.getFluid("molten.radoxpoly"); // dreamcraft
        Item ocItem = GameRegistry.findItem("OpenComputers", "item");
        Item case1 = GameRegistry.findItem("OpenComputers", "case1");
        Item case2 = GameRegistry.findItem("OpenComputers", "case2");
        Item case3 = GameRegistry.findItem("OpenComputers", "case3");
        Item keyboard = GameRegistry.findItem("OpenComputers", "keyboard");
        Item magicalMemory = GameRegistry.findItem("computronics", "computronics.ocSpecialParts");
        Item blueprint = GameRegistry.findItem("BuildCraft|Builders", "blueprintItem");
        Item frfCoil3 = GameRegistry.findItem("GoodGenerator", "FRF_Coil_2");
        Item advancedRadiationProtectionPlate = GameRegistry
                .findItem("GoodGenerator", "advancedRadiationProtectionPlate");
        Item tungstenString = GameRegistry.findItem("dreamcraft", "item.TungstenString");
        Item pikoCircuit = GameRegistry.findItem("dreamcraft", "item.PikoCircuit");
        Item quantumCircuit = GameRegistry.findItem("dreamcraft", "item.QuantumCircuit");
        Item display = GameRegistry.findItem("dreamcraft", "item.Display");
        Item waferT3 = GameRegistry.findItem("dreamcraft", "item.WaferTier3");
        Item micaInsulator = GameRegistry.findItem("dreamcraft", "item.MicaInsulatorFoil");
        Item irradiantReinforcedBedrockiumPlate = GameRegistry
                .findItem("dreamcraft", "item.IrradiantReinforcedBedrockiumPlate");
        Fluid solderUEV = FluidRegistry.getFluid("molten.mutatedlivingsolder") != null
                ? FluidRegistry.getFluid("molten.mutatedlivingsolder")
                : FluidRegistry.getFluid("molten.solderingalloy");
        Fluid solderLuV = FluidRegistry.getFluid("molten.indalloy140") != null
                ? FluidRegistry.getFluid("molten.indalloy140")
                : FluidRegistry.getFluid("molten.solderingalloy");

        // Assembler
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(Blocks.glass),
                        GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Desh, 8) },
                null,
                new ItemStack(GSBlocks.FutureGlass, 1, 0),
                200,
                480);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(GCItems.battery, 1, WILDCARD_VALUE),
                        GT_Utility.getIntegratedCircuit(1) },
                Materials.Lead.getMolten(6000),
                new ItemStack(GSItems.LeadBattery, 1, 100),
                100,
                120);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(GSItems.ThermalPaddingTier2), GT_Utility.getIntegratedCircuit(1) },
                null,
                new ItemStack(GSItems.ThermalClothTier2, 5),
                360,
                1024);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(GSItems.ThermalPaddingTier2, 1, 1),
                        GT_Utility.getIntegratedCircuit(1) },
                null,
                new ItemStack(GSItems.ThermalClothTier2, 8),
                600,
                1024);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(GSItems.ThermalPaddingTier2, 1, 2),
                        GT_Utility.getIntegratedCircuit(1) },
                null,
                new ItemStack(GSItems.ThermalClothTier2, 7),
                520,
                1024);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(GSItems.ThermalPaddingTier2, 1, 3),
                        GT_Utility.getIntegratedCircuit(1) },
                null,
                new ItemStack(GSItems.ThermalClothTier2, 4),
                300,
                1024);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(AsteroidsItems.basicItem, 1, 7),
                        GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Titanium, 8),
                        new ItemStack(tungstenString, 8), GT_Utility.getIntegratedCircuit(1) },
                Materials.Polybenzimidazole.getMolten(576),
                new ItemStack(GSItems.ThermalClothTier2),
                600,
                1024);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(GSItems.ThermalClothTier2, 5), new ItemStack(tungstenString, 5),
                        GT_Utility.getIntegratedCircuit(5) },
                Materials.Polybenzimidazole.getMolten(1440),
                new ItemStack(GSItems.ThermalPaddingTier2),
                1500,
                1920);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(GSItems.ThermalClothTier2, 8), new ItemStack(tungstenString, 8),
                        GT_Utility.getIntegratedCircuit(8) },
                Materials.Polybenzimidazole.getMolten(2304),
                new ItemStack(GSItems.ThermalPaddingTier2, 1, 1),
                2400,
                1920);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(GSItems.ThermalClothTier2, 7), new ItemStack(tungstenString, 7),
                        GT_Utility.getIntegratedCircuit(7) },
                Materials.Polybenzimidazole.getMolten(2016),
                new ItemStack(GSItems.ThermalPaddingTier2, 1, 2),
                2100,
                1920);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(GSItems.ThermalClothTier2, 4), new ItemStack(tungstenString, 4),
                        GT_Utility.getIntegratedCircuit(4) },
                Materials.Polybenzimidazole.getMolten(1152),
                new ItemStack(GSItems.ThermalPaddingTier2, 1, 3),
                1200,
                1920);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(GSItems.SpacesuitHelmet), new ItemStack(GCItems.sensorGlasses) },
                Materials.Duralumin.getMolten(1440),
                new ItemStack(GSItems.SpacesuitHelmetGlasses),
                600,
                1920);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(GSItems.SpacesuitPlate),
                        GT_ModHandler.getModItem("IC2", "itemArmorJetpackElectric", 1, WILDCARD_VALUE) },
                Materials.Duralumin.getMolten(1440),
                new ItemStack(GSItems.SpacesuitJetPlate),
                600,
                1920);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(GSItems.SpacesuitBoots),
                        new ItemStack(GSItems.CompressedPlates, 2, 4) },
                Materials.Duralumin.getMolten(1440),
                new ItemStack(GSItems.SpacesuitGravityBoots),
                600,
                1920);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(case1), new ItemStack(keyboard), new ItemStack(display),
                        ItemList.Emitter_HV.get(1), ItemList.Sensor_HV.get(1),
                        new ItemStack(GCItems.heavyPlatingTier1) },
                Materials.SolderingAlloy.getMolten(576),
                new ItemStack(GSItems.ControlComputer, 1, 1),
                600,
                480,
                true);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(case1), new ItemStack(keyboard), new ItemStack(display),
                        ItemList.Field_Generator_EV.get(2), new ItemStack(MarsItems.marsItemBasic, 1, 3) },
                Materials.SolderingAlloy.getMolten(864),
                new ItemStack(GSItems.ControlComputer, 1, 2),
                600,
                1920,
                true);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(case1), new ItemStack(keyboard), new ItemStack(display),
                        ItemList.Emitter_IV.get(1), ItemList.Sensor_IV.get(1),
                        new ItemStack(AsteroidsItems.basicItem) },
                Materials.SolderingAlloy.getMolten(1152),
                new ItemStack(GSItems.ControlComputer, 1, 3),
                600,
                7680,
                true);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(case2), new ItemStack(keyboard), new ItemStack(display),
                        ItemList.Emitter_IV.get(1), ItemList.Sensor_IV.get(1),
                        new ItemStack(GSItems.heavyDutyPlateT4) },
                Materials.SolderingAlloy.getMolten(1728),
                new ItemStack(GSItems.ControlComputer, 1, 4),
                600,
                7680,
                true);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(case2), new ItemStack(keyboard), new ItemStack(display),
                        ItemList.Emitter_LuV.get(1), ItemList.Sensor_LuV.get(1),
                        new ItemStack(GSItems.heavyDutyPlateT5) },
                Materials.SolderingAlloy.getMolten(2304),
                new ItemStack(GSItems.ControlComputer, 1, 5),
                600,
                30720,
                true);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(case3), new ItemStack(keyboard), new ItemStack(display),
                        ItemList.Emitter_ZPM.get(1), ItemList.Sensor_ZPM.get(1),
                        new ItemStack(GSItems.heavyDutyPlateT6) },
                Materials.SolderingAlloy.getMolten(3456),
                new ItemStack(GSItems.ControlComputer, 1, 6),
                600,
                122880,
                true);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(case3), new ItemStack(keyboard), new ItemStack(display),
                        ItemList.Emitter_UV.get(1), ItemList.Sensor_UV.get(1),
                        new ItemStack(GSItems.heavyDutyPlateT7) },
                Materials.SolderingAlloy.getMolten(4608),
                new ItemStack(GSItems.ControlComputer, 1, 7),
                600,
                491520,
                true);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(case3), new ItemStack(keyboard), new ItemStack(display),
                        ItemList.Emitter_UV.get(1), ItemList.Sensor_UV.get(1),
                        new ItemStack(GSItems.heavyDutyPlateT8) },
                Materials.SolderingAlloy.getMolten(6912),
                new ItemStack(GSItems.ControlComputer, 1, 8),
                600,
                491520,
                true);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(case1), new ItemStack(GCItems.basicItem, 1, 14),
                        GT_OreDictUnificator.get("circuitData", 1), ItemList.Emitter_HV.get(1),
                        ItemList.Sensor_HV.get(1), new ItemStack(GCItems.heavyPlatingTier1) },
                Materials.SolderingAlloy.getMolten(576),
                new ItemStack(GSItems.ControlComputer, 1, 100),
                600,
                480,
                true);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(case1), new ItemStack(GCItems.basicItem, 1, 14),
                        GT_OreDictUnificator.get("circuitElite", 1), ItemList.Emitter_EV.get(1),
                        ItemList.Sensor_EV.get(1), new ItemStack(MarsItems.marsItemBasic, 1, 3) },
                Materials.SolderingAlloy.getMolten(864),
                new ItemStack(GSItems.ControlComputer, 1, 101),
                600,
                1920,
                true);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(case1), new ItemStack(GCItems.basicItem, 1, 14),
                        GT_OreDictUnificator.get("circuitMaster", 1), ItemList.Emitter_IV.get(1),
                        ItemList.Sensor_IV.get(1), new ItemStack(AsteroidsItems.basicItem) },
                Materials.SolderingAlloy.getMolten(1152),
                new ItemStack(GSItems.ControlComputer, 1, 102),
                600,
                7680,
                true);
        RA.addAssemblerRecipe(
                new ItemStack[] { GT_ModHandler.getModItem("dreamcraft", "tile.NeutroniumPlatedReinforcedStone", 1),
                        WerkstoffLoader.HDCS.get(OrePrefixes.plate, 8) },
                FluidRegistry.getFluidStack("molten.adamantium alloy", 144),
                new ItemStack(GSBlocks.DysonSwarmBlocks, 1, 9),
                500,
                30720);
        RA.addAssemblerRecipe(
                new ItemStack[] { new ItemStack(GSItems.DysonSwarmItems, 4, 1),
                        new ItemStack(GSItems.DysonSwarmItems, 4, 2) },
                null,
                new ItemStack(GSItems.DysonSwarmItems, 1, 3),
                200,
                30720);

        // Assembly Line
        TT_recipeAdder.addResearchableAssemblylineRecipe(
                new ItemStack(ocItem, 1, 91),
                192000,
                512,
                8000000,
                16,
                new Object[] { ItemList.Cover_SolarPanel_LuV.get(1), ItemList.Cover_SolarPanel_LuV.get(1),
                        ItemList.Cover_SolarPanel_LuV.get(1), ItemList.Cover_SolarPanel_LuV.get(1),
                        new ItemStack(GSItems.DysonSwarmItems, 2, 3), new ItemStack(GSItems.DysonSwarmItems, 2, 3),
                        new ItemStack(GSItems.DysonSwarmItems, 2, 3), new ItemStack(GSItems.DysonSwarmItems, 2, 3),
                        new Object[] { OrePrefixes.circuit.get(Materials.Infinite), 1L },
                        ItemList.Circuit_Chip_QPIC.get(1), ItemList.Circuit_Chip_QPIC.get(1),
                        new Object[] { OrePrefixes.circuit.get(Materials.Infinite), 1L }, new ItemStack(ocItem, 2, 91),
                        ItemList.Emitter_UEV.get(1), ItemList.Sensor_UEV.get(1), new ItemStack(ocItem, 2, 91) },
                new FluidStack[] { new FluidStack(solderUEV, 18432) },
                new ItemStack(GSItems.DysonSwarmItems, 64),
                100,
                1966080);

        TT_recipeAdder.addResearchableAssemblylineRecipe( // Dyson Swarm Energy Receiver Base Casing
                ItemList.Circuit_Chip_QPIC.get(1),
                192000,
                512,
                2000000,
                32,
                new ItemStack[] { ItemRegistry.energyDistributor[9], ItemList.Circuit_Chip_QPIC.get(16),
                        ItemList.Circuit_Chip_QPIC.get(16), ItemRegistry.energyDistributor[9],
                        GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.SuperconductorUEV, 1),
                        ItemList.UHV_Coil.get(32), ItemList.UHV_Coil.get(32),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.SuperconductorUEV, 1),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.SuperconductorUEV, 1),
                        ItemList.UHV_Coil.get(32), ItemList.UHV_Coil.get(32),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.SuperconductorUEV, 1),
                        ItemRegistry.energyDistributor[9], ItemList.Circuit_Chip_QPIC.get(16),
                        ItemList.Circuit_Chip_QPIC.get(16), ItemRegistry.energyDistributor[9] },
                new FluidStack[] { GSMaterials.liquidHelium.getFluidOrGas(50000),
                        Materials.SuperCoolant.getFluid(16000), new FluidStack(solderUEV, 11520),
                        Materials.UUMatter.getFluid(8000) },
                new ItemStack(GSBlocks.DysonSwarmBlocks, 4, 0),
                800,
                8000000);

        TT_recipeAdder.addResearchableAssemblylineRecipe( // Dyson Swarm Energy Receiver Dish Block
                new ItemStack(GSItems.DysonSwarmItems, 1, 3),
                192000,
                512,
                2000000,
                32,
                new ItemStack[] { new ItemStack(GSItems.DysonSwarmItems, 16, 3),
                        new ItemStack(GSItems.DysonSwarmItems, 16, 3), new ItemStack(GSItems.DysonSwarmItems, 16, 3),
                        new ItemStack(GSItems.DysonSwarmItems, 16, 3),
                        new ItemStack(advancedRadiationProtectionPlate, 16),
                        new ItemStack(advancedRadiationProtectionPlate, 16),
                        new ItemStack(advancedRadiationProtectionPlate, 16),
                        new ItemStack(advancedRadiationProtectionPlate, 16), ItemList.Reactor_Coolant_Sp_6.get(1),
                        ItemList.Reactor_Coolant_Sp_6.get(1), ItemList.Reactor_Coolant_Sp_6.get(1),
                        ItemList.Reactor_Coolant_Sp_6.get(1),
                        GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Infinity, 4),
                        new ItemStack(frfCoil3), new ItemStack(frfCoil3),
                        GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Infinity, 4) },
                new FluidStack[] { new FluidStack(moltenRhugnor, 40), Materials.SuperCoolant.getFluid(16000),
                        new FluidStack(solderUEV, 11520), Materials.UUMatter.getFluid(8000) },
                new ItemStack(GSBlocks.DysonSwarmBlocks, 3, 1),
                600,
                8000000);

        TT_recipeAdder.addResearchableAssemblylineRecipe( // Dyson Swarm Module Deployment Unit Base Casing
                ItemList.Quantum_Chest_IV.get(1),
                192000,
                512,
                2000000,
                32,
                new ItemStack[] { new ItemStack(GregTech_API.sBlockMachines, 1, 11231), new ItemStack(pikoCircuit),
                        new ItemStack(pikoCircuit), new ItemStack(GregTech_API.sBlockMachines, 1, 11231),
                        ItemList.Electric_Pump_UIV.get(16), ItemList.Quantum_Tank_IV.get(1),
                        ItemList.Quantum_Tank_IV.get(1), ItemList.Electric_Pump_UIV.get(16),
                        ItemList.Conveyor_Module_UIV.get(16), ItemList.Quantum_Chest_IV.get(1),
                        ItemList.Quantum_Chest_IV.get(1), ItemList.Conveyor_Module_UIV.get(16),
                        new ItemStack(GregTech_API.sBlockMachines, 1, 11231), new ItemStack(pikoCircuit),
                        new ItemStack(pikoCircuit), new ItemStack(GregTech_API.sBlockMachines, 1, 11231) },
                new FluidStack[] { Materials.Lubricant.getFluid(50000), Materials.SuperCoolant.getFluid(16000),
                        new FluidStack(solderUEV, 11520), Materials.UUMatter.getFluid(8000) },
                new ItemStack(GSBlocks.DysonSwarmBlocks, 4, 2),
                800,
                8000000);

        TT_recipeAdder.addResearchableAssemblylineRecipe( // Dyson Swarm Module Deployment Unit Core
                new ItemStack(Blocks.dropper),
                192000,
                512,
                2000000,
                32,
                new ItemStack[] { new ItemStack(GregTech_API.sBlockMachines, 1, 11231),
                        ItemList.Conveyor_Module_UIV.get(8), new ItemStack(Blocks.dropper, 64),
                        new ItemStack(GregTech_API.sBlockMachines, 1, 11231), ItemList.Conveyor_Module_UIV.get(8),
                        ItemList.Robot_Arm_UIV.get(16), ItemList.Electric_Piston_UIV.get(16),
                        new ItemStack(Blocks.dropper, 64), new ItemStack(Blocks.dropper, 64),
                        ItemList.Electric_Piston_UIV.get(16), ItemList.Robot_Arm_UIV.get(16),
                        ItemList.Conveyor_Module_UIV.get(8), new ItemStack(GregTech_API.sBlockMachines, 1, 11231),
                        new ItemStack(Blocks.dropper, 64), ItemList.Conveyor_Module_UIV.get(8),
                        new ItemStack(GregTech_API.sBlockMachines, 1, 11231) },
                new FluidStack[] { Materials.Lubricant.getFluid(50000), Materials.SuperCoolant.getFluid(16000),
                        new FluidStack(solderUEV, 11520), Materials.UUMatter.getFluid(8000) },
                new ItemStack(GSBlocks.DysonSwarmBlocks, 1, 3),
                200,
                8000000);

        TT_recipeAdder.addResearchableAssemblylineRecipe( // Dyson Swarm Module Deployment Unit Superconducting Magnet
                new ItemStack(GregTech_API.sBlockMachines, 1, 10984),
                192000,
                512,
                2000000,
                32,
                new ItemStack[] { new ItemStack(GregTech_API.sBlockMachines, 1, 11231),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.SuperconductorUEV, 2),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.SuperconductorUEV, 2),
                        new ItemStack(GregTech_API.sBlockMachines, 1, 11231), ItemList.Circuit_Chip_QPIC.get(32),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.SuperconductorUEV, 2),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.SuperconductorUEV, 2),
                        new ItemStack(pikoCircuit, 4), new ItemStack(pikoCircuit, 4),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.SuperconductorUEV, 2),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.SuperconductorUEV, 2),
                        ItemList.Circuit_Chip_QPIC.get(32), new ItemStack(GregTech_API.sBlockMachines, 1, 11231),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.SuperconductorUEV, 2),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.SuperconductorUEV, 2),
                        new ItemStack(GregTech_API.sBlockMachines, 1, 11231) },
                new FluidStack[] { new FluidStack(moltenRhugnor, 40), Materials.SuperCoolant.getFluid(16000),
                        new FluidStack(solderUEV, 11520), Materials.UUMatter.getFluid(8000) },
                new ItemStack(GSBlocks.DysonSwarmBlocks, 4, 4),
                800,
                8000000);

        TT_recipeAdder.addResearchableAssemblylineRecipe( // Dyson Swarm Control Center Base Casing
                CustomItemList.Machine_Multi_Computer.get(1),
                192000,
                512,
                2000000,
                32,
                new ItemStack[] { new ItemStack(GregTech_API.sBlockMachines, 1, 11231), new ItemStack(ocItem, 1, 103),
                        new ItemStack(ocItem, 1, 103), new ItemStack(GregTech_API.sBlockMachines, 1, 11231),
                        new ItemStack(pikoCircuit), CustomItemList.Machine_Multi_Computer.get(1),
                        CustomItemList.Machine_Multi_Computer.get(1), new ItemStack(pikoCircuit),
                        new ItemStack(pikoCircuit), CustomItemList.Machine_Multi_Computer.get(1),
                        CustomItemList.Machine_Multi_Computer.get(1), new ItemStack(pikoCircuit),
                        new ItemStack(GregTech_API.sBlockMachines, 1, 11231), new ItemStack(ocItem, 1, 103),
                        new ItemStack(ocItem, 1, 103), new ItemStack(GregTech_API.sBlockMachines, 1, 11231) },
                new FluidStack[] { Materials.SuperCoolant.getFluid(16000), Materials.SuperCoolant.getFluid(16000),
                        new FluidStack(solderUEV, 11520), Materials.UUMatter.getFluid(8000) },
                new ItemStack(GSBlocks.DysonSwarmBlocks, 8, 5),
                1600,
                8000000);

        TT_recipeAdder.addResearchableAssemblylineRecipe( // Dyson Swarm Control Center Primary Windings
                CustomItemList.tM_TeslaPrimary_6.get(1),
                192000,
                512,
                2000000,
                32,
                new ItemStack[] { ItemList.Circuit_Chip_QPIC.get(1), new ItemStack(micaInsulator, 64),
                        new ItemStack(micaInsulator, 64), ItemList.Circuit_Chip_QPIC.get(1),
                        new ItemStack(micaInsulator, 64), CustomItemList.eM_Coil.get(1), CustomItemList.eM_Coil.get(1),
                        new ItemStack(micaInsulator, 64), new ItemStack(micaInsulator, 64),
                        CustomItemList.eM_Coil.get(1), CustomItemList.eM_Coil.get(1), new ItemStack(micaInsulator, 64),
                        ItemList.Circuit_Chip_QPIC.get(1), new ItemStack(micaInsulator, 64),
                        new ItemStack(micaInsulator, 64), ItemList.Circuit_Chip_QPIC.get(1) },
                new FluidStack[] { new FluidStack(moltenRadoxPolymer, 3456), Materials.SuperCoolant.getFluid(16000),
                        new FluidStack(solderUEV, 11520), Materials.UUMatter.getFluid(8000) },
                new ItemStack(GSBlocks.DysonSwarmBlocks, 4, 6),
                800,
                8000000);

        TT_recipeAdder.addResearchableAssemblylineRecipe( // Dyson Swarm Control Center Secondary Windings
                CustomItemList.tM_TeslaSecondary.get(1),
                192000,
                512,
                2000000,
                32,
                new ItemStack[] { ItemList.Circuit_Chip_QPIC.get(1), new ItemStack(micaInsulator, 64),
                        new ItemStack(micaInsulator, 64), ItemList.Circuit_Chip_QPIC.get(1),
                        new ItemStack(micaInsulator, 64), ItemList.Casing_Coil_AwakenedDraconium.get(1),
                        ItemList.Casing_Coil_AwakenedDraconium.get(1), new ItemStack(micaInsulator, 64),
                        new ItemStack(micaInsulator, 64), ItemList.Casing_Coil_AwakenedDraconium.get(1),
                        ItemList.Casing_Coil_AwakenedDraconium.get(1), new ItemStack(micaInsulator, 64),
                        ItemList.Circuit_Chip_QPIC.get(1), new ItemStack(micaInsulator, 64),
                        new ItemStack(micaInsulator, 64), ItemList.Circuit_Chip_QPIC.get(1) },
                new FluidStack[] { new FluidStack(moltenRadoxPolymer, 3240), Materials.SuperCoolant.getFluid(16000),
                        new FluidStack(solderUEV, 11520), Materials.UUMatter.getFluid(8000) },
                new ItemStack(GSBlocks.DysonSwarmBlocks, 4, 7),
                800,
                8000000);

        TT_recipeAdder.addResearchableAssemblylineRecipe( // Dyson Swarm Control Center Toroid Casing
                CustomItemList.tM_TeslaToroid.get(1),
                192000,
                512,
                2000000,
                32,
                new ItemStack[] { GT_OreDictUnificator.get(OrePrefixes.screw, Materials.CosmicNeutronium, 1),
                        GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Neutronium, 1),
                        GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Neutronium, 1),
                        GT_OreDictUnificator.get(OrePrefixes.screw, Materials.CosmicNeutronium, 1),
                        GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Neutronium, 1),
                        GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.SuperconductorUEVBase, 1),
                        GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.SuperconductorUEVBase, 1),
                        GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Neutronium, 1),
                        GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Neutronium, 1),
                        GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.SuperconductorUEVBase, 1),
                        GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.SuperconductorUEVBase, 1),
                        GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Neutronium, 1),
                        GT_OreDictUnificator.get(OrePrefixes.screw, Materials.CosmicNeutronium, 1),
                        GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Neutronium, 1),
                        GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Neutronium, 1),
                        GT_OreDictUnificator.get(OrePrefixes.screw, Materials.CosmicNeutronium, 1) },
                new FluidStack[] { new FluidStack(moltenRadoxPolymer, 144), Materials.SuperCoolant.getFluid(16000),
                        new FluidStack(solderUEV, 11520), Materials.UUMatter.getFluid(8000) },
                new ItemStack(GSBlocks.DysonSwarmBlocks, 1, 8),
                200,
                8000000);

        TT_recipeAdder.addResearchableAssemblylineRecipe( // Dyson Swarm Controller
                GT_ModHandler.getModItem("supersolarpanel", "PhotonicSolarPanel", 1),
                192000,
                512,
                8000000,
                16,
                new ItemStack[] { new ItemStack(GregTech_API.sBlockMachines, 1, 11231),
                        new ItemStack(irradiantReinforcedBedrockiumPlate),
                        new ItemStack(irradiantReinforcedBedrockiumPlate),
                        new ItemStack(GregTech_API.sBlockMachines, 1, 11231), new ItemStack(pikoCircuit, 2),
                        new ItemStack(magicalMemory, 2), new ItemStack(ocItem, 4, 103), new ItemStack(pikoCircuit, 2),
                        new ItemStack(pikoCircuit, 2), new ItemStack(ocItem, 4, 103), new ItemStack(magicalMemory, 2),
                        new ItemStack(pikoCircuit, 2), new ItemStack(GregTech_API.sBlockMachines, 1, 11231),
                        new ItemStack(irradiantReinforcedBedrockiumPlate),
                        new ItemStack(irradiantReinforcedBedrockiumPlate),
                        new ItemStack(GregTech_API.sBlockMachines, 1, 11231) },
                new FluidStack[] { new FluidStack(moltenRhugnor, 100), Materials.SuperCoolant.getFluid(16000),
                        new FluidStack(solderUEV, 11520), Materials.UUMatter.getFluid(8000) },
                GSItems.DysonSwarmController,
                2400,
                32000000);

        // Chemical Reactor
        RA.addChemicalRecipe(
                Materials.Carbon.getDust(1),
                GT_OreDictUnificator.get("dustHafnia", 1),
                null,
                Materials.Oxygen.getGas(2000),
                GSMaterials.hafniumCarbide.get(OrePrefixes.dust),
                400);

        // Mixer
        RA.addMixerRecipe(
                GT_OreDictUnificator.get("dustTantalumCarbide", 4),
                GSMaterials.hafniumCarbide.get(OrePrefixes.dust),
                GT_Utility.getIntegratedCircuit(1),
                null,
                null,
                null,
                null,
                null,
                GSMaterials.tantalumCarbideHafniumCarbideMixture.get(OrePrefixes.dust, 5),
                200,
                1920);

        // Plasma Arc Furnace
        RA.addPlasmaArcFurnaceRecipe(
                GSMaterials.tantalumCarbideHafniumCarbideMixture.get(OrePrefixes.dust),
                Materials.Nitrogen.getPlasma(2),
                new ItemStack[] { GSMaterials.tantalumHafniumCarbide.get(OrePrefixes.nugget, 9) },
                Materials.Nitrogen.getGas(1),
                new int[] { 10000 },
                5,
                7680);

        // Vacuum Freezer
        RA.addVacuumFreezerRecipe(
                Materials.Helium.getGas(1000),
                GSMaterials.liquidHelium.getFluidOrGas(1000),
                1800,
                480);

        // Autoclave
        RA.addAutoclaveRecipe(
                GT_OreDictUnificator.get(OrePrefixes.dust, Materials.MysteriousCrystal, 1),
                Materials.Water.getFluid(1000),
                new ItemStack(GSItems.UnknowCrystal),
                9000,
                3600,
                480);
        RA.addAutoclaveRecipe(
                GT_OreDictUnificator.get(OrePrefixes.dust, Materials.MysteriousCrystal, 1),
                FluidRegistry.getFluidStack("ic2distilledwater", 1000),
                new ItemStack(GSItems.UnknowCrystal),
                10000,
                2400,
                480);
        RA.addAutoclaveRecipe(
                new ItemStack(GSItems.DysonSwarmItems, 1, 4),
                GSMaterials.tantalumHafniumCarbide.getMolten(576),
                new ItemStack(GSItems.DysonSwarmItems, 5, 1),
                10000,
                1000,
                7680);
        RA.addAutoclaveRecipe(
                new ItemStack(GSItems.DysonSwarmItems, 1, 5),
                Materials.Neutronium.getMolten(576),
                new ItemStack(GSItems.DysonSwarmItems, 5, 2),
                10000,
                1000,
                7680);

        // Compressor
        RA.addCompressorRecipe(
                new ItemStack(GSItems.GlowstoneDusts, 4),
                new ItemStack(GSBlocks.CeresGlowStone),
                300,
                2);
        RA.addCompressorRecipe(
                new ItemStack(GSItems.GlowstoneDusts, 4, 1),
                new ItemStack(GSBlocks.IoGlowStone),
                300,
                2);
        RA.addCompressorRecipe(
                new ItemStack(GSItems.GlowstoneDusts, 4, 2),
                new ItemStack(GSBlocks.EnceladusGlowStone),
                300,
                2);
        RA.addCompressorRecipe(
                new ItemStack(GSItems.GlowstoneDusts, 4, 3),
                new ItemStack(GSBlocks.ProteusBlocks),
                300,
                2);
        RA.addCompressorRecipe(
                new ItemStack(GSItems.GlowstoneDusts, 4, 4),
                new ItemStack(GSBlocks.PlutoGlowStone),
                300,
                2);

        // Laser Engraver
        RA.addLaserEngraverRecipe(
                new ItemStack(blueprint),
                getGTNHItem("SchematicsMoonBuggy", 0),
                new ItemStack(GCItems.schematic),
                200,
                480,
                true);
        RA.addLaserEngraverRecipe(
                new ItemStack(blueprint),
                getGTNHItem("SchematicsTier2", 0),
                new ItemStack(GCItems.schematic, 1, 1),
                200,
                480,
                true);
        RA.addLaserEngraverRecipe(
                new ItemStack(blueprint),
                getGTNHItem("SchematicsTier3", 0),
                new ItemStack(MarsItems.schematic),
                200,
                480,
                true);
        RA.addLaserEngraverRecipe(
                new ItemStack(blueprint),
                getGTNHItem("SchematicsCargoRocket", 0),
                new ItemStack(MarsItems.schematic, 1, 1),
                200,
                480,
                true);
        RA.addLaserEngraverRecipe(
                new ItemStack(blueprint),
                getGTNHItem("SchematicsAstroMiner", 0),
                new ItemStack(MarsItems.schematic, 1, 2),
                200,
                480,
                true);
        RA.addLaserEngraverRecipe(
                new ItemStack(blueprint),
                getGTNHItem("SchematicsTier4", 0),
                new ItemStack(GSItems.Tier4Schematic),
                200,
                480,
                true);
        RA.addLaserEngraverRecipe(
                new ItemStack(blueprint),
                getGTNHItem("SchematicsTier5", 0),
                new ItemStack(GSItems.Tier5Schematic),
                200,
                480,
                true);
        RA.addLaserEngraverRecipe(
                new ItemStack(blueprint),
                getGTNHItem("SchematicsTier6", 0),
                new ItemStack(GSItems.Tier6Schematic),
                200,
                480,
                true);
        RA.addLaserEngraverRecipe(
                new ItemStack(blueprint),
                getGTNHItem("SchematicsTier7", 0),
                new ItemStack(GSItems.Tier7Schematic),
                200,
                480,
                true);
        RA.addLaserEngraverRecipe(
                new ItemStack(blueprint),
                getGTNHItem("SchematicsTier8", 0),
                new ItemStack(GSItems.Tier8Schematic),
                200,
                480,
                true);

        // Pulverization
        RA.addPulveriserRecipe(
                new ItemStack(GSBlocks.CeresGlowStone),
                new ItemStack[] { new ItemStack(GSItems.GlowstoneDusts, 4) },
                new int[] { 10000 },
                300,
                2);
        RA.addPulveriserRecipe(
                new ItemStack(GSBlocks.IoGlowStone),
                new ItemStack[] { new ItemStack(GSItems.GlowstoneDusts, 4, 1) },
                new int[] { 10000 },
                300,
                2);
        RA.addPulveriserRecipe(
                new ItemStack(GSBlocks.EnceladusGlowStone),
                new ItemStack[] { new ItemStack(GSItems.GlowstoneDusts, 4, 2) },
                new int[] { 10000 },
                300,
                2);
        RA.addPulveriserRecipe(
                new ItemStack(GSBlocks.ProteusGlowStone),
                new ItemStack[] { new ItemStack(GSItems.GlowstoneDusts, 4, 3) },
                new int[] { 10000 },
                300,
                2);
        RA.addPulveriserRecipe(
                new ItemStack(GSBlocks.PlutoGlowStone),
                new ItemStack[] { new ItemStack(GSItems.GlowstoneDusts, 4, 4) },
                new int[] { 10000 },
                300,
                2);
        GT_Recipe_Map.sMaceratorRecipes.mRecipeItemMap
                .remove(new GT_ItemStack(GSMaterials.tantalumHafniumCarbide.get(OrePrefixes.nugget)));
        RA.addPulveriserRecipe(
                GSMaterials.tantalumHafniumCarbide.get(OrePrefixes.nugget, 9),
                new ItemStack[] { GSMaterials.tantalumHafniumCarbide.get(OrePrefixes.dust) },
                new int[] { 10000 },
                100,
                480);
        RA.addPulveriserRecipe(
                GSMaterials.tantalumHafniumCarbide.get(OrePrefixes.dust),
                new ItemStack[] { new ItemStack(GSItems.DysonSwarmItems, 1, 4) },
                new int[] { 10000 },
                100,
                480);
        RA.addPulveriserRecipe(
                Materials.Neutronium.getDust(1),
                new ItemStack[] { new ItemStack(GSItems.DysonSwarmItems, 1, 5) },
                new int[] { 10000 },
                100,
                480);

        // Shaped Crafting
        addDyedFutureGlassRecipe(0, "Black");
        addDyedFutureGlassRecipe(1, "Red");
        addDyedFutureGlassRecipe(2, "Green");
        addDyedFutureGlassRecipe(3, "Brown");
        addDyedFutureGlassRecipe(4, "Blue");
        addDyedFutureGlassRecipe(5, "Purple");
        addDyedFutureGlassRecipe(6, "Cyan");
        addDyedFutureGlassRecipe(7, "LightGray");
        addDyedFutureGlassRecipe(8, "Gray");
        addDyedFutureGlassRecipe(9, "Pink");
        addDyedFutureGlassRecipe(10, "Lime");
        addDyedFutureGlassRecipe(11, "Yellow");
        addDyedFutureGlassRecipe(12, "LightBlue");
        addDyedFutureGlassRecipe(13, "Magenta");
        addDyedFutureGlassRecipe(14, "Orange");
        addDyedFutureGlassRecipe(15, "White");
        addDecorativeMetalBlockRecipe(0, new ItemStack(GSItems.CompressedPlates, 1, 3));
        addDecorativeMetalBlockRecipe(1, new ItemStack(GSItems.CompressedPlates));
        addDecorativeMetalBlockRecipe(2, new ItemStack(GSItems.CompressedPlates, 1, 1));
        addDecorativeMetalBlockRecipe(3, new ItemStack(GSItems.CompressedPlates, 1, 4));
        addDecorativeMetalBlockRecipe(4, new ItemStack(GSItems.CompressedPlates, 1, 5));
        addDecorativeMetalBlockRecipe(5, new ItemStack(GSItems.CompressedPlates, 1, 6));
        addDecorativeMetalBlockRecipe(6, new ItemStack(GSItems.CompressedPlates, 1, 7));
        addDecorativeMetalBlockRecipe(7, new ItemStack(GSItems.CompressedPlates, 1, 8));
        addDecorativeMetalBlockRecipe(8, new ItemStack(GSItems.CompressedPlates, 1, 9));
        addDecorativeMetalBlockRecipe(9, new ItemStack(GCItems.basicItem, 1, 6));
        RecipeUtil.addRecipe(
                new ItemStack(GSBlocks.MachineFrames),
                new Object[] { "MWM", "CTC", "MWM", 'M', new ItemStack(GSItems.CompressedPlates, 1, 5), 'W', waferT3,
                        'C', new ItemStack(GCBlocks.aluminumWire, 1, 1), 'T',
                        new ItemStack(GregTech_API.sBlockCasings4) });
        RecipeUtil.addRecipe(
                new ItemStack(GSBlocks.StorageModuleT3),
                new Object[] { "CEC", "WFW", "CEC", 'C', new ItemStack(GSItems.CompressedPlates, 1, 9), 'E',
                        new ItemStack(GCBlocks.machineTiered, 1, 8), 'W', waferT3, 'F',
                        new ItemStack(GSBlocks.MachineFrames) });
        RecipeUtil.addRecipe(
                new ItemStack(GSBlocks.OxStorageModuleT2),
                new Object[] { "SCS", "PFP", "SWS", 'S', new ItemStack(GCBlocks.machineBase2, 1, 8), 'C',
                        new ItemStack(GCItems.oxygenConcentrator), 'P', new ItemStack(GCBlocks.oxygenPipe), 'F',
                        new ItemStack(GSBlocks.MachineFrames), 'W', waferT3 });
        RecipeUtil.addRecipe(
                new ItemStack(GSBlocks.SolarWindPanel),
                new Object[] { "TFT", "APA", "MWS", 'T', new ItemStack(AsteroidsItems.basicItem, 1, 6), 'F',
                        new ItemStack(GSItems.SolarFlares, 1, 1), 'A', new ItemStack(GCBlocks.aluminumWire, 1, 1), 'P',
                        new ItemStack(GCItems.flagPole), 'M', ItemList.Electric_Motor_LV.get(1), 'W',
                        new ItemStack(GCItems.basicItem, 1, 14), 'S', ItemList.Sensor_LV.get(1) });
        RecipeUtil.addRecipe(
                new ItemStack(GSBlocks.SolarPanel),
                new Object[] { "DSD", "AFA", "MWs", 'D', new ItemStack(GSItems.CompressedPlates, 1, 2), 'S',
                        new ItemStack(GCItems.basicItem, 1, 1), 'A', new ItemStack(GCBlocks.aluminumWire, 1, 1), 'F',
                        new ItemStack(GSBlocks.MachineFrames), 'M', ItemList.Electric_Motor_MV.get(1), 'W', waferT3,
                        's', ItemList.Sensor_MV.get(1) });
        RecipeUtil.addRecipe(
                new ItemStack(BRBlocks.BarnardaCPlanks, 4),
                new Object[] { "L", 'L', new ItemStack(BRBlocks.BarnardaCLog) }); // GT replaces this recipe
                                                                                  // automatically
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.SpacesuitBoots),
                new Object[] { "ABA", "ADA", "CDC", 'A', new ItemStack(GSItems.CompressedSDHD120), 'B',
                        new ItemStack(GSItems.LeadBoots), 'D', new ItemStack(MarsItems.marsItemBasic, 1, 5), 'C',
                        new ItemStack(GSItems.CompressedPlates, 1, 2) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.QuantBow),
                new Object[] { "LMS", "m S", "LMS", 'L',
                        GT_OreDictUnificator.get(OrePrefixes.stickLong, Materials.Lead, 1), 'M',
                        getGTNHItem("MytrylCrystal"), 'S', GT_ModHandler.getModItem("TConstruct", "bowstring", 1, 2),
                        'm', getGTNHItem("MysteriousCrystal") });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.LeadHelmet),
                new Object[] { "LLL", "LHL", "D D", 'L', new ItemStack(GSItems.CompressedPlates, 1, 3), 'H',
                        "craftingToolHardHammer", 'D',
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Desh, 1) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.LeadPlate),
                new Object[] { "LHL", "LDL", "L L", 'L', new ItemStack(GSItems.CompressedPlates, 1, 3), 'H',
                        "craftingToolHardHammer", 'D',
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Desh, 1) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.LeadLeg),
                new Object[] { "LLL", "LDL", "LHL", 'L', new ItemStack(GSItems.CompressedPlates, 1, 3), 'H',
                        "craftingToolHardHammer", 'D',
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Desh, 1) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.LeadBoots),
                new Object[] { "D D", "LHL", "L L", 'L', new ItemStack(GSItems.CompressedPlates, 1, 3), 'H',
                        "craftingToolHardHammer", 'D',
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Desh, 1) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.CobaltumHelmet),
                new Object[] { "CCC", "CHC", "D D", 'C', new ItemStack(GSItems.CompressedPlates, 1, 1), 'H',
                        "craftingToolHardHammer", 'D',
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Desh, 1) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.CobaltumPlate),
                new Object[] { "CHC", "CDC", "C C", 'C', new ItemStack(GSItems.CompressedPlates, 1, 1), 'H',
                        "craftingToolHardHammer", 'D',
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Desh, 1) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.CobaltumLeg),
                new Object[] { "CCC", "CDC", "CHC", 'C', new ItemStack(GSItems.CompressedPlates, 1, 1), 'H',
                        "craftingToolHardHammer", 'D',
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Desh, 1) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.CobaltumBoots),
                new Object[] { "D D", "CHC", "C C", 'C', new ItemStack(GSItems.CompressedPlates, 1, 1), 'H',
                        "craftingToolHardHammer", 'D',
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Desh, 1) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.SpacesuitHelmet),
                new Object[] { "AHA", "AMA", "DCD", 'A', new ItemStack(GSItems.CompressedSDHD120), 'H',
                        new ItemStack(GSItems.LeadHelmet), 'M', new ItemStack(GCItems.oxMask), 'D',
                        new ItemStack(MarsItems.marsItemBasic, 1, 5), 'C', new ItemStack(GCItems.oxygenConcentrator) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.SpacesuitPlate),
                new Object[] { "APA", "ACA", "ADA", 'A', new ItemStack(GSItems.CompressedSDHD120), 'P',
                        new ItemStack(GSItems.LeadPlate), 'C', new ItemStack(GSItems.CompressedPlates, 1, 2), 'D',
                        new ItemStack(MarsItems.marsItemBasic, 1, 5) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.SpacesuitLeg),
                new Object[] { "ALA", "CAC", "CDC", 'A', new ItemStack(GSItems.CompressedSDHD120), 'L',
                        new ItemStack(GSItems.LeadLeg), 'C', new ItemStack(GSItems.CompressedPlates, 1, 2), 'D',
                        new ItemStack(MarsItems.marsItemBasic, 1, 5) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.ModuleLander),
                new Object[] { "FSS", "SBC", "PEP", 'F', new ItemStack(GCItems.basicItem, 1, 19), 'S',
                        new ItemStack(GCItems.basicItem, 1, 9), 'B', new ItemStack(GCItems.partBuggy, 1, 1), 'C',
                        new ItemStack(GCItems.basicItem, 1, 6), 'P', new ItemStack(GCItems.flagPole), 'E',
                        new ItemStack(GCItems.rocketEngine) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.ModuleLanderT2),
                new Object[] { "FPF", "CLC", "FCF", 'F', GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Gold, 1),
                        'P', new ItemStack(GCItems.parachute, 1, WILDCARD_VALUE), 'C', new ItemStack(GCItems.canvas),
                        'L', new ItemStack(GSItems.ModuleLander) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.ModuleLanderT3),
                new Object[] { "DDD", "PLP", "DDD", 'D', new ItemStack(MarsItems.marsItemBasic, 1, 5), 'P',
                        new ItemStack(GCItems.flagPole), 'L', new ItemStack(GSItems.ModuleLanderT2) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.PlasmaSword, 1, 100),
                new Object[] { "DCD", "FCH", "DBW", 'D', GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Desh, 1),
                        'C', new ItemStack(GSItems.CompressedPlates, 1, 1), 'F', "craftingToolFile", 'H',
                        "craftingToolHardHammer", 'B', new ItemStack(GCItems.battery, 1, WILDCARD_VALUE), 'W',
                        new ItemStack(GCBlocks.aluminumWire, 1, 1) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.PlasmaPickaxe, 1, 100),
                new Object[] { "CCC", "FPH", "DBW", 'C', new ItemStack(GSItems.CompressedPlates, 1, 1), 'F',
                        "craftingToolFile", 'P', new ItemStack(GCItems.flagPole), 'H', "craftingToolHardHammer", 'D',
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Desh, 1), 'B',
                        new ItemStack(GCItems.battery, 1, WILDCARD_VALUE), 'W',
                        new ItemStack(GCBlocks.aluminumWire, 1, 1) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.PlasmaAxe, 1, 100),
                new Object[] { "CCH", "CPD", "FBW", 'C', new ItemStack(GSItems.CompressedPlates, 1, 1), 'H',
                        "craftingToolHardHammer", 'P', new ItemStack(GCItems.flagPole), 'D',
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Desh, 1), 'F', "craftingToolFile", 'B',
                        new ItemStack(GCItems.battery, 1, WILDCARD_VALUE), 'W',
                        new ItemStack(GCBlocks.aluminumWire, 1, 1) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.PlasmaShovel, 1, 100),
                new Object[] { "FCH", "DPD", "WBW", 'F', "craftingToolFile", 'C',
                        new ItemStack(GSItems.CompressedPlates, 1, 1), 'H', "craftingToolHardHammer", 'D',
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Desh, 1), 'P',
                        new ItemStack(GCItems.flagPole), 'W', new ItemStack(GCBlocks.aluminumWire, 1, 1), 'B',
                        new ItemStack(GCItems.battery, 1, WILDCARD_VALUE) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.PlasmaHoe, 1, 100),
                new Object[] { "CCH", "FPD", "WBD", 'C', new ItemStack(GSItems.CompressedPlates, 1, 1), 'H',
                        "craftingToolHardHammer", 'F', "craftingToolFile", 'P', new ItemStack(GCItems.flagPole), 'D',
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Desh, 1), 'W',
                        new ItemStack(GCBlocks.aluminumWire, 1, 1), 'B',
                        new ItemStack(GCItems.battery, 1, WILDCARD_VALUE) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.SolarFlares),
                new Object[] { "IPI", "PSP", "PsP", 'I', new ItemStack(GCItems.basicItem, 1, 11), 'P',
                        new ItemStack(GCItems.flagPole), 'S', new ItemStack(GCItems.basicItem, 1, 1), 's',
                        new ItemStack(GCItems.basicItem, 1, 9) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.SolarFlares, 1, 1),
                new Object[] { "DFD", "FTF", "DFD", 'D', new ItemStack(MarsItems.marsItemBasic, 1, 5), 'F',
                        new ItemStack(GSItems.SolarFlares), 'T', new ItemStack(AsteroidsItems.basicItem, 1, 6) });
        RecipeUtil.addRecipe(
                new ItemStack(GSItems.RobotArm),
                new Object[] { "NNL", "MRA", "PCR", 'N',
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.NetherStar, 1), 'L',
                        new ItemStack(GCItems.sensorLens), 'M',
                        new ItemStack(GT_MetaGenerated_Item_01.INSTANCE, 1, 32602), 'R',
                        GT_OreDictUnificator.get(OrePrefixes.stickLong, Materials.StainlessSteel, 1), 'A',
                        ItemList.Robot_Arm_HV.get(1), 'P', ItemList.Electric_Piston_HV.get(1), 'C', "circuitData" });
        RecipeUtil.addRecipe(
                new ItemStack(GCBlocks.nasaWorkbench),
                new Object[] { "RRR", "CDC", "WAW", 'R', new ItemStack(GSItems.RobotArm), 'C', "circuitElite", 'D',
                        display, 'W', GT_ModHandler.getModItem("openmodularturrets", "hardWallTierFour", 1), 'A',
                        new ItemStack(GregTech_API.sBlockMachines, 1, 213) });
        RecipeUtil.addRecipe(
                getGTNHItem("RawSDHCAlloy"),
                new Object[] { "SDS", "BCA", "SHS", 'S',
                        GT_OreDictUnificator.get(OrePrefixes.screw, Materials.StainlessSteel, 1), 'D',
                        "craftingToolScrewDriver", 'B', new ItemStack(GSItems.CompressedDualBronze), 'C',
                        new ItemStack(GSItems.CompressedCoal), 'A', new ItemStack(GSItems.CompressedDualAluminium), 'H',
                        "craftingToolHardHammer" });
    }

    private static void addDyedFutureGlassRecipe(int meta, String color) {
        RecipeUtil.addRecipe(
                new ItemStack(GSBlocks.FutureGlasses, 8, meta),
                new Object[] { "GGG", "GDG", "GGG", 'G', new ItemStack(GSBlocks.FutureGlass), 'D', "dye" + color });
    }

    private static void addDecorativeMetalBlockRecipe(int meta, ItemStack plate) {
        RecipeUtil.addRecipe(
                new ItemStack(GSBlocks.MetalsBlock, 1, meta),
                new Object[] { "HP ", "PSP", " PW", 'H', "craftingToolHardHammer", 'P', plate, 'S', "stone", 'W',
                        "craftingToolWrench" });
    }

    private static ItemStack getGTNHItem(String name) {
        return GT_ModHandler.getModItem("dreamcraft", "item." + name, 1);
    }

    private static ItemStack getGTNHItem(String name, int amount) {
        return GT_ModHandler.getModItem("dreamcraft", "item." + name, amount);
    }
}
