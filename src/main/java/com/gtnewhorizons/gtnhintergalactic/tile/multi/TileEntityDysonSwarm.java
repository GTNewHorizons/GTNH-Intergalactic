package com.gtnewhorizons.gtnhintergalactic.tile.multi;

import static gregtech.api.util.GTUtility.filterValidMTEs;
import static net.minecraft.util.EnumChatFormatting.*;
import static net.minecraft.util.StatCollector.translateToLocal;
import static net.minecraft.util.StatCollector.translateToLocalFormatted;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.util.ForgeDirection;

import com.gtnewhorizon.gtnhlib.client.tooltip.LoreHolder;
import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.ISurvivalBuildEnvironment;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureUtility;
import com.gtnewhorizons.gtnhintergalactic.block.IGBlocks;
import com.gtnewhorizons.gtnhintergalactic.client.IGTextures;
import com.gtnewhorizons.gtnhintergalactic.config.IGConfig;
import com.gtnewhorizons.gtnhintergalactic.item.IGItems;

import gregtech.api.GregTechAPI;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.MTEHatchDynamo;
import gregtech.api.metatileentity.implementations.MTEHatchInputBus;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GTStructureUtility;
import gregtech.api.util.GTUtility;
import gregtech.api.util.MultiblockTooltipBuilder;
import gregtech.common.items.IDMetaTool01;
import gregtech.common.items.MetaGeneratedTool01;
import micdoodle8.mods.galacticraft.api.world.IOrbitDimension;
import tectech.thing.metaTileEntity.hatch.MTEHatchDynamoMulti;
import tectech.thing.metaTileEntity.multi.base.TTMultiblockBase;

public class TileEntityDysonSwarm extends GT_MetaTileEntity_EnhancedMultiBlockBase_EM
        implements ISurvivalConstructable {

    @LoreHolder("gt.blockmachines.multimachine.ig.dyson.lore")
    private static String loreTooltip;

    private static final Map<Locale, DecimalFormat> DECIMAL_FORMATTERS = new HashMap<>();
    private static final String STRUCTURE_PIECE_MAIN = "main";

    // spotless:off

    private static final IStructureDefinition<TileEntityDysonSwarm> STRUCTURE_DEFINITION = StructureDefinition
        .<TileEntityDysonSwarm>builder()
        .addShape(
            STRUCTURE_PIECE_MAIN,
            StructureUtility.transpose(
                new String[][] {
                    { "         ---    ", "        -----   ", "       -------  ", "      --------- ",
                        "     -----------", "     -----------", "     -----------", "      --------- ",
                        "       -------  ", "        -----   ", "         ---    ", "  ttt           ",
                        "  ttt        k  ", "  ttt       k k ", "             k  ", "                " },
                    { "         ---    ", "        -----   ", "       -------  ", "      --------- ",
                        "     -----------", "     -----------", "     -----------", "      --------- ",
                        "       -------  ", "        -----   ", "  ttt    ---    ", " ttttt          ",
                        " ttttt       k  ", " ttttt      k k ", "  ttt        k  ", "                " },
                    { "         ---    ", "        -----   ", "       -------  ", "      --------- ",
                        "     -----------", "     -----------", "     -----------", "      --------- ",
                        "       -------  ", "        -----   ", "  ttt    ---    ", " ttttt          ",
                        " ttttt       k  ", " ttttt      k k ", "  ttt        k  ", "                " },
                    { "         ---    ", "        -----   ", "       -------  ", "      --------- ",
                        "     -----------", "     -----------", "     -----------", "      --------- ",
                        "       -------  ", "        -----   ", "  ttt    ---    ", " ttttt          ",
                        " ttttt       k  ", " ttttt      k k ", "  ttt        k  ", "                " },
                    { "         ---    ", "        -----   ", "       -------  ", "      --------- ",
                        "     -----------", "     -----------", "     -----------", "      --------- ",
                        "       -------  ", "        -----   ", "         ---    ", "  ttt           ",
                        "  tst        k  ", "  ttt       k k ", "             k  ", "                " },
                    { "         ---    ", "        -----   ", "       -------  ", "      --------- ",
                        "     -----------", "     -----------", "     -----------", "      --------- ",
                        "       -------  ", "        -----   ", "         ---    ", "                ",
                        "   s         k  ", "            k k ", "             k  ", "                " },
                    { "         ---    ", "        -----   ", "       -------  ", "      --------- ",
                        "     -----------", "     -----h-----", "     -----------", "      --------- ",
                        "       -------  ", "        -----   ", "  ttt    ---    ", " t g t          ",
                        " tgsgt       k  ", " t g t      k k ", "  ttt        k  ", "                " },
                    { "         ---    ", "        -----   ", "       -------  ", "      --------- ",
                        "     -----------", "     -----f-----", "     -----------", "      --------- ",
                        "       -------  ", "        -----   ", "         ---    ", "                ",
                        "   s         k  ", "            k k ", "             k  ", "                " },
                    { "         ddd    ", "        d---d   ", "       d-----d  ", "      d-------d ",
                        "     d---------d", "     d----f----d", "     d---------d", "      d-------d ",
                        "       d-----d  ", "        d---d   ", "  ttt    ddd    ", " t g t          ",
                        " tgsgt      kmk ", " t g t      m m ", "  ttt       kmk ", "                " },
                    { "                ", "         ddd    ", "        ddddd   ", "       dd---dd  ",
                        "      dd-----dd ", "      dd--f--dd ", "      dd-----dd ", "       dd---dd  ",
                        "        ddddd   ", "         ddd    ", "                ", "                ",
                        "   s        kmk ", "            m m ", "            kmk ", "                " },
                    { "                ", "                ", "                ", "         ddd    ",
                        "        ddddd   ", "        ddddd   ", "        ddddd   ", "         ddd    ",
                        "                ", "                ", "  ttt           ", " t g t          ",
                        " tgsgt      kmk ", " t g t      m m ", "  ttt       kmk ", "                " },
                    { "                ", "                ", "                ", "                ",
                        "         f f    ", "                ", "         f f    ", "                ",
                        "                ", "                ", "                ", "                ",
                        "   s        kmk ", "            m m ", "            kmk ", "                " },
                    { "                ", "                ", "                ", "                ",
                        "         f f    ", "                ", "         f f    ", "                ",
                        "                ", "                ", "  ttt           ", " t g t          ",
                        " tgsgt      kmk ", " t g t      m m ", "  ttt       kmk ", "                " },
                    { "                ", "                ", "                ", "                ",
                        "         f f    ", "                ", "         f f    ", "                ",
                        "                ", "                ", "                ", "                ",
                        "   s        kmk ", "            m m ", "            kmk ", "                " },
                    { "                ", "                ", "                ", "                ",
                        "         f f    ", "                ", "         f f    ", "                ",
                        "                ", "                ", "                ", "                ",
                        "   s        kmk ", "            m m ", "            kmk ", "                " },
                    { "                ", "                ", "                ", "                ",
                        "         f f    ", "                ", "         f f    ", "                ",
                        "                ", "                ", "  ppp           ", " p   p          ",
                        " p s p      kmk ", " p   p      m m ", "  ppp       kmk ", "                " },
                    { "                ", "                ", "                ", "        xxxxx   ",
                        "        xxxxx   ", "        xxxxx   ", "        xxxxx   ", "        xxxxx   ",
                        "                ", "yyyyyyy         ", "yyyyyyy         ", "yypppyy    zzzzz",
                        "yypypyy    zzzzz", "yypppyy    zzjzz", "yyyyyyy    zzzzz", "yyyyyyy    zzzzz" },
                    { "                ", "                ", "                ", "        xeeex   ",
                        "        eccce   ", "        eccce   ", "        eccce   ", "        xeeex   ",
                        "                ", "ooooooo         ", "oyyyyyo         ", "oyyyyyo    ziiiz",
                        "oyyyyyo    izzzi", "oyyyyyo    izzzi", "oyyyyyo    izzzi", "ooooooo    ziiiz" },
                    { "                ", "                ", "                ", "        xx~xx   ",
                        "        xxxxx   ", "        xxxxx   ", "        xxxxx   ", "        xxxxx   ",
                        "                ", "yyyyyyy         ", "yyyyyyy         ", "yyyyyyy    zzzzz",
                        "yyyyyyy    zzzzz", "yyyyyyy    zzzzz", "yyyyyyy    zzzzz", "yyyyyyy    zzzzz" },
                    { "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb",
                        "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb",
                        "bbbbbbbnbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb",
                        "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb" } }))
        .addElement('b', StructureUtility.ofBlock(IGBlocks.DysonSwarmCasing, 9)) // Dyson Swarm Ground Unit Ultra High Strength Concrete Floor
        .addElement('c', StructureUtility.ofBlock(GregTechAPI.sBlockCasings5, 8)) // Awakened Draconium Coil Block
        .addElement('d', StructureUtility.ofBlock(IGBlocks.DysonSwarmCasing, 1)) // Dyson Swarm Energy Receiver Dish Block
        .addElement(
            'e',
            StructureUtility.ofChain(
                GTStructureUtility.ofHatchAdder(TileEntityDysonSwarm::addDynamoToMachineList, IGTextures.CASING_INDEX_RECEIVER, 1),
                StructureUtility.ofBlock(IGBlocks.DysonSwarmCasing, 0))) // Dyson Swarm Energy Receiver Base Casing
        .addElement('f', GTStructureUtility.ofFrame(Materials.HSSS))
        .addElement('g', GTStructureUtility.ofFrame(Materials.Titanium))
        .addElement('h', StructureUtility.ofBlock(GregTechAPI.sBlockCasings6, 10)) // Hermetic Casing X
        .addElement(
            'i',
            StructureUtility.ofChain(
                GTStructureUtility.ofHatchAdder(TileEntityDysonSwarm::addInputToMachineList, IGTextures.CASING_INDEX_LAUNCH, 2),
                StructureUtility.ofBlock(IGBlocks.DysonSwarmCasing, 2))) // Dyson Swarm Module Deployment Unit Base Casing
        .addElement('j', StructureUtility.ofBlock(IGBlocks.DysonSwarmCasing, 3)) // Dyson Swarm Module Deployment Unit Core
        .addElement('k', GTStructureUtility.ofFrame(Materials.Longasssuperconductornameforuhvwire))
        .addElement('m', StructureUtility.ofBlock(IGBlocks.DysonSwarmCasing, 4)) // Dyson Swarm Module Deployment Unit Superconducting Magnet
        .addElement(
            'n',
            StructureUtility.ofChain(
                GTStructureUtility.ofHatchAdder(TileEntityDysonSwarm::addClassicMaintenanceToMachineList, IGTextures.CASING_INDEX_FLOOR, 3),
                StructureUtility.ofBlock(IGBlocks.DysonSwarmCasing, 9))) // Dyson Swarm Ground Unit Ultra High Strength Concrete Floor
        .addElement(
            'o',
            StructureUtility.ofChain(
                GTStructureUtility.ofHatchAdder(TileEntityDysonSwarm::addDataInputToMachineList, IGTextures.CASING_INDEX_COMMAND, 4),
                StructureUtility.ofBlock(IGBlocks.DysonSwarmCasing, 5))) // Dyson Swarm Command Center Base Casing
        .addElement('p', StructureUtility.ofBlock(IGBlocks.DysonSwarmCasing, 6)) // Dyson Swarm Command Center Primary Windings
        .addElement('s', StructureUtility.ofBlock(IGBlocks.DysonSwarmCasing, 7)) // Dyson Swarm Command Center Secondary Windings
        .addElement('t', StructureUtility.ofBlock(IGBlocks.DysonSwarmCasing, 8)) // Dyson Swarm Command Center Toroid Casing
        .addElement('x', StructureUtility.ofBlock(IGBlocks.DysonSwarmCasing, 0)) // Dyson Swarm Energy Receiver Base Casing
        .addElement('y', StructureUtility.ofBlock(IGBlocks.DysonSwarmCasing, 5)) // Dyson Swarm Command Center Base Casing
        .addElement('z', StructureUtility.ofBlock(IGBlocks.DysonSwarmCasing, 2)) // Dyson Swarm Module Deployment Unit Base Casing
        .build();

    // spotless:on

    private long euPerTick = 0;
    private double powerFactor = 0.0;
    private int moduleCount = 0;

    public TileEntityDysonSwarm(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    protected TileEntityDysonSwarm(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new TileEntityDysonSwarm(this.mName);
    }

    @Override
    public void onFirstTick_EM(IGregTechTileEntity aBaseMetaTileEntity) {
        powerFactor = getPowerFactor();
    }

    /*************
     * STRUCTURE *
     *************/
    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {
        structureBuild_EM(STRUCTURE_PIECE_MAIN, 10, 18, 3, stackSize, hintsOnly);
    }

    @Override
    public IStructureDefinition<? extends TTMultiblockBase> getStructure_EM() {
        return STRUCTURE_DEFINITION;
    }

    @Override
    public int survivalConstruct(ItemStack stackSize, int elementBudget, ISurvivalBuildEnvironment env) {
        if (this.mMachine) return -1;
        return this.survivialBuildPiece(STRUCTURE_PIECE_MAIN, stackSize, 10, 18, 3, elementBudget, env, true, true);
    }

    @Override
    public boolean checkMachine_EM(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        return structureCheck_EM(STRUCTURE_PIECE_MAIN, 10, 18, 3) && mMaintenanceHatches.size() <= 1
                && mInputBusses.size() > 0
                && mInputHatches.size() > 0
                && eInputData.size() > 0
                && (mDynamoHatches.size() > 0 || eDynamoMulti.size() > 0);
    }

    @Override
    public boolean explodesOnComponentBreak(ItemStack aStack) {
        return true;
    }

    /**********
     * RECIPE *
     **********/
    @Override
    public boolean checkRecipe_EM(ItemStack aStack) {
        for (MTEHatchInputBus bus : filterValidMTEs(mInputBusses)) {
            for (int i = 0; i < bus.getBaseMetaTileEntity().getSizeInventory(); i++) {
                ItemStack stack = bus.getBaseMetaTileEntity().getStackInSlot(i);
                if (stack != null && stack.getItem() == IGItems.DysonSwarmItems
                        && stack.getItemDamage() == 0
                        && moduleCount < IGConfig.dysonSwarm.maxModules + 1) {
                    int usedStackSize = Math.min(stack.stackSize, IGConfig.dysonSwarm.maxModules - moduleCount);
                    moduleCount += usedStackSize;
                    stack.stackSize -= usedStackSize;
                }
            }
        }

        euPerTick = (long) ((long) moduleCount * IGConfig.dysonSwarm.euPerModule * powerFactor);

        if (moduleCount > 0 && depleteInput(IGConfig.dysonSwarm.getCoolantStack())) {
            // With a certain chance (configurable), the size of the ItemStack(s) is reduced.
            // This has the effect that the player must constantly replace "broken" Modules.
            destroyModules();
            mEfficiencyIncrease = 10000;
            mMaxProgresstime = 72000;
            return true;
        }
        mEfficiency = 0;
        return false;
    }

    private void destroyModules() {
        if (IGConfig.dysonSwarm.destroyModuleA <= 0.0f) {
            return;
        }

        moduleCount -= moduleCount * (2 * IGConfig.dysonSwarm.destroyModuleChance)
                / (Math.exp(-IGConfig.dysonSwarm.destroyModuleA * (moduleCount - 1)) + Math.exp(
                        IGConfig.dysonSwarm.destroyModuleB
                                * Math.min(eAvailableData, (long) IGConfig.dysonSwarm.destroyModuleMaxCPS)));

        if (moduleCount < 0) {
            moduleCount = 0;
        }
    }

    @Override
    public boolean energyFlowOnRunningTick(ItemStack aStack, boolean allowProduction) {
        if (allowProduction && euPerTick > 0) {
            addEnergyOutput_EM(euPerTick, 1);
        }
        return true;
    }

    @Override
    public boolean addEnergyOutput_EM(long EU, long Amperes) {
        return addEnergyOutput_EM(EU, Amperes, true);
    }

    public boolean addEnergyOutput_EM(long EU, long Amperes, boolean allowMixedVoltages) {
        if (EU < 0) {
            EU = -EU;
        }
        if (Amperes < 0) {
            Amperes = -Amperes;
        }
        long euVar = EU * Amperes;
        long diff;
        for (MTEHatchDynamo tHatch : filterValidMTEs(mDynamoHatches)) {
            if (tHatch.maxEUOutput() < euVar && !allowMixedVoltages) {
                explodeMultiblock();
            }
            diff = tHatch.maxEUStore() - tHatch.getBaseMetaTileEntity().getStoredEU();
            if (diff > 0) {
                if (euVar <= diff) {
                    tHatch.setEUVar(tHatch.getBaseMetaTileEntity().getStoredEU() + euVar);
                    return true;
                }
                tHatch.setEUVar(tHatch.maxEUStore());
                euVar -= diff;
            }
        }
        for (MTEHatchDynamoMulti tHatch : filterValidMTEs(eDynamoMulti)) {
            if (tHatch.maxEUOutput() < euVar && !allowMixedVoltages) {
                explodeMultiblock();
            }
            diff = tHatch.maxEUStore() - tHatch.getBaseMetaTileEntity().getStoredEU();
            if (diff > 0) {
                if (euVar <= diff) {
                    tHatch.setEUVar(tHatch.getBaseMetaTileEntity().getStoredEU() + euVar);
                    return true;
                }
                tHatch.setEUVar(tHatch.maxEUStore());
                euVar -= diff;
            }
        }
        explodeMultiblock(); // If no energy is left, we would've already exploded. Thus no check is needed here
        return false;
    }

    @Override
    public boolean onRightclick(IGregTechTileEntity aBaseMetaTileEntity, EntityPlayer aPlayer, ForgeDirection side,
            float aX, float aY, float aZ) {
        ItemStack heldItem = aPlayer.getHeldItem();

        // Check if the player is holding a plunger
        if (heldItem == null || heldItem.getItem() != MetaGeneratedTool01.INSTANCE
                || heldItem.getItemDamage() != IDMetaTool01.PLUNGER.ID) {
            return super.onRightclick(aBaseMetaTileEntity, aPlayer);
        }

        // Setup
        int prevCount = this.moduleCount;
        int maxReduction = (int) Math.min(
                this.moduleCount,
                MetaGeneratedTool01.getToolMaxDamage(heldItem) - MetaGeneratedTool01.getToolDamage(heldItem));
        ItemStack modules = new ItemStack(IGItems.DysonSwarmItems, maxReduction);

        // Fill player inventory
        aPlayer.inventory.addItemStackToInventory(modules);

        // If the player's inventory is full and some modules are still left and the player is sneaking, spawn them in
        // front of the controller
        if (modules.stackSize > 0 && aPlayer.isSneaking()) {
            aPlayer.worldObj.spawnEntityInWorld(
                    new EntityItem(aPlayer.worldObj, aPlayer.posX, aPlayer.posY + 0.5, aPlayer.posZ, modules));

            // Set moduleCount based on the number of ejected modules and damage the plunger
            this.moduleCount = 0;
            MetaGeneratedTool01.INSTANCE.doDamage(heldItem, maxReduction);
        } else {
            this.moduleCount = prevCount - maxReduction + modules.stackSize;
            MetaGeneratedTool01.INSTANCE.doDamage(heldItem, maxReduction - modules.stackSize);
        }

        return true;
    }

    @Override
    public boolean getDefaultHasMaintenanceChecks() {
        return false;
    }

    /****************
     * CLIENT STUFF *
     ****************/
    @Override
    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, ForgeDirection side, ForgeDirection facing,
            int colorIndex, boolean aActive, boolean aRedstone) {
        if (side == facing) {
            if (aActive)
                return new ITexture[] { Textures.BlockIcons.getCasingTextureForId(IGTextures.CASING_INDEX_RECEIVER),
                        TextureFactory.of(IGTextures.DYSON_OVERLAY_FRONT_ACTIVE),
                        TextureFactory.builder().addIcon(IGTextures.DYSON_OVERLAY_FRONT_ACTIVE_GLOW).glow().build() };
            return new ITexture[] { Textures.BlockIcons.getCasingTextureForId(IGTextures.CASING_INDEX_RECEIVER),
                    TextureFactory.of(IGTextures.DYSON_OVERLAY_FRONT),
                    TextureFactory.builder().addIcon(IGTextures.DYSON_OVERLAY_FRONT_GLOW).glow().build() };
        }
        return new ITexture[] { Textures.BlockIcons.getCasingTextureForId(IGTextures.CASING_INDEX_RECEIVER) };
    }

    @Override
    protected MultiblockTooltipBuilder createTooltip() {
        final MultiblockTooltipBuilder tt = new MultiblockTooltipBuilder();
        tt.addMachineType(translateToLocal("gt.blockmachines.multimachine.ig.dyson.type"));
        if (loreTooltip != null) tt.addInfo(ITALIC + loreTooltip);

        tt.addInfo(translateToLocal("gt.blockmachines.multimachine.ig.dyson.desc1"))
                .addInfo(
                        translateToLocalFormatted(
                                "gt.blockmachines.multimachine.ig.dyson.desc2",
                                getDecimalFormat().format(IGConfig.dysonSwarm.euPerModule)))
                .addInfo(translateToLocal("gt.blockmachines.multimachine.ig.dyson.desc3"))
                .addInfo(
                        translateToLocalFormatted(
                                "gt.blockmachines.multimachine.ig.dyson.desc4",
                                getDecimalFormat().format(IGConfig.dysonSwarm.destroyModuleChance),
                                getDecimalFormat().format(IGConfig.dysonSwarm.destroyModuleA),
                                IGConfig.dysonSwarm.destroyModuleB))
                .addInfo(
                        translateToLocalFormatted(
                                "gt.blockmachines.multimachine.ig.dyson.desc5",
                                getDecimalFormat().format(IGConfig.dysonSwarm.coolantConsumption),
                                IGConfig.dysonSwarm.getCoolantStack().getLocalizedName()))
                .addInfo(translateToLocal("gt.blockmachines.multimachine.ig.dyson.desc6"))
                .addInfo(translateToLocal("gt.blockmachines.multimachine.ig.dyson.desc7")).addTecTechHatchInfo()
                .beginStructureBlock(16, 20, 16, false).addDynamoHatch(translateToLocal("ig.dyson.structure.dynamo"), 1)
                .addInputBus("1 - 11", 2).addInputHatch("1 - 11", 2)
                .addOtherStructurePart(translateToLocal("ig.dyson.structure.optical"), "1 - 24", 4).addStructureInfo("")
                .addStructureInfo(ITALIC + translateToLocal("ig.dyson.structure.additionally"))
                .addCasingInfoRange(translateToLocal("ig.dyson.structure.receiver.base"), 53, 64, false)
                .addCasingInfoExactly(translateToLocal("ig.dyson.structure.receiver.dish"), 81, false)
                .addCasingInfoRange(translateToLocal("ig.dyson.structure.deployment.base"), 62, 72, false)
                .addCasingInfoExactly(translateToLocal("ig.dyson.structure.deployment.core"), 1, false)
                .addCasingInfoExactly(translateToLocal("ig.dyson.structure.deployment.magnet"), 32, false)
                .addCasingInfoRange(translateToLocal("ig.dyson.structure.control.base"), 115, 138, false)
                .addCasingInfoExactly(translateToLocal("ig.dyson.structure.control.primary"), 20, false)
                .addCasingInfoExactly(translateToLocal("ig.dyson.structure.control.secondary"), 12, false)
                .addCasingInfoExactly(translateToLocal("ig.dyson.structure.control.toroid"), 128, false)
                .addCasingInfoExactly(translateToLocal("ig.dyson.structure.base.floor"), 256, false)
                .addCasingInfoExactly(translateToLocal("ig.dyson.structure.base.coil"), 9, false)
                .addCasingInfoExactly(translateToLocal("ig.dyson.structure.base.hermetic"), 1, false)
                .addCasingInfoExactly(translateToLocal("ig.dyson.structure.base.frameTitanium"), 16, false)
                .addCasingInfoExactly(translateToLocal("ig.dyson.structure.base.frameHSSS"), 23, false)
                .addCasingInfoExactly(translateToLocal("ig.dyson.structure.base.frameUHVBase"), 64, false)
                .toolTipFinisher();
        return tt;
    }

    @Override
    public String[] getInfoData() {
        return new String[] { LIGHT_PURPLE + "Operational Data:" + RESET,
                "Modules: " + YELLOW + GTUtility.formatNumbers(moduleCount) + RESET,
                "Power Factor: " + (powerFactor < 1.0f ? RED : GREEN)
                        + GTUtility.formatNumbers(powerFactor * 100.0)
                        + "%"
                        + RESET,
                "Theoretical Output: " + YELLOW
                        + GTUtility.formatNumbers((long) moduleCount * IGConfig.dysonSwarm.euPerModule * powerFactor)
                        + RESET
                        + " EU/t",
                "Current Output: " + YELLOW + GTUtility.formatNumbers(euPerTick) + RESET + " EU/t",
                "Computation required: " + YELLOW + GTUtility.formatNumbers(eRequiredData) + RESET + "/t",
                "Maintenance Status: " + (getRepairStatus() == getIdealStatus() ? GREEN + "Working perfectly" + RESET
                        : RED + "Has problems" + RESET),
                "---------------------------------------------" };
    }

    /******************
     * HELPER METHODS *
     ******************/
    public double getPowerFactor() {
        WorldProvider provider = this.getBaseMetaTileEntity().getWorld().provider;

        if (provider instanceof IOrbitDimension orbitDimension) {
            return IGConfig.dysonSwarm.getPowerFactor(orbitDimension.getPlanetToOrbit());
        }

        String className = provider.getClass().getName();
        return switch (className) {
            case "zarkov.utilityworlds.UW_WorldProviderGarden" -> IGConfig.dysonSwarm.getPowerFactor("UW_Garden");
            case "zarkov.utilityworlds.UW_WorldProviderMining" -> IGConfig.dysonSwarm.getPowerFactor("UW_Mining");
            case "zarkov.utilityworlds.UW_WorldProviderVoid" -> IGConfig.dysonSwarm.getPowerFactor("UW_Void");
            default -> IGConfig.dysonSwarm.getPowerFactor(String.valueOf(provider.dimensionId));
        };
    }

    private static DecimalFormat getDecimalFormat() {
        return DECIMAL_FORMATTERS.computeIfAbsent(Locale.getDefault(Locale.Category.FORMAT), locale -> {
            DecimalFormat format = new DecimalFormat();
            format.setGroupingUsed(true);
            format.setMaximumFractionDigits(5);
            format.setRoundingMode(RoundingMode.HALF_UP);
            DecimalFormatSymbols dfs = format.getDecimalFormatSymbols();
            dfs.setGroupingSeparator(',');
            format.setDecimalFormatSymbols(dfs);
            return format;
        });
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
        moduleCount = aNBT.getInteger("moduleCount");
        euPerTick = aNBT.getLong("euPerTick");
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
        aNBT.setInteger("moduleCount", moduleCount);
        aNBT.setLong("euPerTick", euPerTick);
    }
}
