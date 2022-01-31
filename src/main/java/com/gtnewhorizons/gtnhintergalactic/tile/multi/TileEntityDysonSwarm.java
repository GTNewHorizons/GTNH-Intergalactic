package galaxyspace.core.tile.machine.multi;

import static net.minecraft.util.EnumChatFormatting.*;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.github.technus.tectech.thing.metaTileEntity.hatch.GT_MetaTileEntity_Hatch_DynamoMulti;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureUtility;

import galaxyspace.GalaxySpace;
import galaxyspace.core.config.GSConfigCore;
import galaxyspace.core.register.GSBlocks;
import galaxyspace.core.register.GSItems;
import gregtech.api.GregTech_API;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Textures.BlockIcons;
import gregtech.api.enums.Textures.BlockIcons.CustomIcon;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Dynamo;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_InputBus;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import gregtech.api.objects.XSTR;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_StructureUtility;
import micdoodle8.mods.galacticraft.api.world.IOrbitDimension;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldProvider;

public class TileEntityDysonSwarm extends GT_MetaTileEntity_EnhancedMultiBlockBase_EM {

    private static Consumer<TileEntityDysonSwarm> moduleDestroyer;
    private static IIconContainer OVERLAY_FRONT_GLOW;
    private static IIconContainer OVERLAY_FRONT_ACTIVE_GLOW;
    private static ITexture OVERLAY_FRONT;
    private static ITexture OVERLAY_FRONT_ACTIVE;
    private static Map<String, Double> powerFactors;

    private static final String LORE_TOOLTIP;
    private static final String STRUCTURE_PIECE_MAIN = "main";
    private static final int CASING_INDEX_RECEIVER = 150;
    private static final int CASING_INDEX_COMMAND = 151;
    private static final int CASING_INDEX_LAUNCH = 152;
    private static final int CASING_INDEX_FLOOR = 153;
    private static final IStructureDefinition<TileEntityDysonSwarm> STRUCTURE_DEFINITION = StructureDefinition.<TileEntityDysonSwarm>builder()
            .addShape(STRUCTURE_PIECE_MAIN, StructureUtility.transpose(new String[][] {
                {"         ---    ", "        -----   ", "       -------  ", "      --------- ", "     -----------", "     -----------", "     -----------", "      --------- ", "       -------  ", "        -----   ", "         ---    ", "  ttt           ", "  ttt        k  ", "  ttt       k k ", "             k  ", "                "},
                {"         ---    ", "        -----   ", "       -------  ", "      --------- ", "     -----------", "     -----------", "     -----------", "      --------- ", "       -------  ", "        -----   ", "  ttt    ---    ", " ttttt          ", " ttttt       k  ", " ttttt      k k ", "  ttt        k  ", "                "},
                {"         ---    ", "        -----   ", "       -------  ", "      --------- ", "     -----------", "     -----------", "     -----------", "      --------- ", "       -------  ", "        -----   ", "  ttt    ---    ", " ttttt          ", " ttttt       k  ", " ttttt      k k ", "  ttt        k  ", "                "},
                {"         ---    ", "        -----   ", "       -------  ", "      --------- ", "     -----------", "     -----------", "     -----------", "      --------- ", "       -------  ", "        -----   ", "  ttt    ---    ", " ttttt          ", " ttttt       k  ", " ttttt      k k ", "  ttt        k  ", "                "},
                {"         ---    ", "        -----   ", "       -------  ", "      --------- ", "     -----------", "     -----------", "     -----------", "      --------- ", "       -------  ", "        -----   ", "         ---    ", "  ttt           ", "  tst        k  ", "  ttt       k k ", "             k  ", "                "},
                {"         ---    ", "        -----   ", "       -------  ", "      --------- ", "     -----------", "     -----------", "     -----------", "      --------- ", "       -------  ", "        -----   ", "         ---    ", "                ", "   s         k  ", "            k k ", "             k  ", "                "},
                {"         ---    ", "        -----   ", "       -------  ", "      --------- ", "     -----------", "     -----h-----", "     -----------", "      --------- ", "       -------  ", "        -----   ", "  ttt    ---    ", " t g t          ", " tgsgt       k  ", " t g t      k k ", "  ttt        k  ", "                "},
                {"         ---    ", "        -----   ", "       -------  ", "      --------- ", "     -----------", "     -----f-----", "     -----------", "      --------- ", "       -------  ", "        -----   ", "         ---    ", "                ", "   s         k  ", "            k k ", "             k  ", "                "},
                {"         ddd    ", "        d---d   ", "       d-----d  ", "      d-------d ", "     d---------d", "     d----f----d", "     d---------d", "      d-------d ", "       d-----d  ", "        d---d   ", "  ttt    ddd    ", " t g t          ", " tgsgt      kmk ", " t g t      m m ", "  ttt       kmk ", "                "},
                {"                ", "         ddd    ", "        ddddd   ", "       dd---dd  ", "      dd-----dd ", "      dd--f--dd ", "      dd-----dd ", "       dd---dd  ", "        ddddd   ", "         ddd    ", "                ", "                ", "   s        kmk ", "            m m ", "            kmk ", "                "},
                {"                ", "                ", "                ", "         ddd    ", "        ddddd   ", "        ddddd   ", "        ddddd   ", "         ddd    ", "                ", "                ", "  ttt           ", " t g t          ", " tgsgt      kmk ", " t g t      m m ", "  ttt       kmk ", "                "},
                {"                ", "                ", "                ", "                ", "         f f    ", "                ", "         f f    ", "                ", "                ", "                ", "                ", "                ", "   s        kmk ", "            m m ", "            kmk ", "                "},
                {"                ", "                ", "                ", "                ", "         f f    ", "                ", "         f f    ", "                ", "                ", "                ", "  ttt           ", " t g t          ", " tgsgt      kmk ", " t g t      m m ", "  ttt       kmk ", "                "},
                {"                ", "                ", "                ", "                ", "         f f    ", "                ", "         f f    ", "                ", "                ", "                ", "                ", "                ", "   s        kmk ", "            m m ", "            kmk ", "                "},
                {"                ", "                ", "                ", "                ", "         f f    ", "                ", "         f f    ", "                ", "                ", "                ", "                ", "                ", "   s        kmk ", "            m m ", "            kmk ", "                "},
                {"                ", "                ", "                ", "                ", "         f f    ", "                ", "         f f    ", "                ", "                ", "                ", "  ppp           ", " p   p          ", " p s p      kmk ", " p   p      m m ", "  ppp       kmk ", "                "},
                {"                ", "                ", "                ", "        xxxxx   ", "        xxxxx   ", "        xxxxx   ", "        xxxxx   ", "        xxxxx   ", "                ", "yyyyyyy         ", "yyyyyyy         ", "yypppyy    zzzzz", "yypypyy    zzzzz", "yypppyy    zzjzz", "yyyyyyy    zzzzz", "yyyyyyy    zzzzz"},
                {"                ", "                ", "                ", "        xeeex   ", "        eccce   ", "        eccce   ", "        eccce   ", "        xeeex   ", "                ", "ooooooo         ", "oyyyyyo         ", "oyyyyyo    ziiiz", "oyyyyyo    izzzi", "oyyyyyo    izzzi", "oyyyyyo    izzzi", "ooooooo    ziiiz"},
                {"                ", "                ", "                ", "        xx~xx   ", "        xxxxx   ", "        xxxxx   ", "        xxxxx   ", "        xxxxx   ", "                ", "yyyyyyy         ", "yyyyyyy         ", "yyyyyyy    zzzzz", "yyyyyyy    zzzzz", "yyyyyyy    zzzzz", "yyyyyyy    zzzzz", "yyyyyyy    zzzzz"},
                {"bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbnbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb"}}))
            .addElement('b', StructureUtility.ofBlock(GSBlocks.DysonSpherePart, 9)) //Dyson Swarm Ground Unit Ultra High Strenght Concrete Floor
            .addElement('c', StructureUtility.ofBlock(GregTech_API.sBlockCasings5, 8)) //Awakened Draconium Coil Block
            .addElement('d', StructureUtility.ofBlock(GSBlocks.DysonSpherePart, 1)) //Dyson Swarm Energy Receiver Dish Block
            .addElement('e', StructureUtility.ofChain(
                    GT_StructureUtility.ofHatchAdder(TileEntityDysonSwarm::addDynamoToMachineList, CASING_INDEX_RECEIVER, 1),
                    StructureUtility.ofBlock(GSBlocks.DysonSpherePart, 0))) //Dyson Swarm Energy Receiver Base Casing
            .addElement('f', GT_StructureUtility.ofFrame(Materials.HSSS))
            .addElement('g', GT_StructureUtility.ofFrame(Materials.Titanium))
            .addElement('h', StructureUtility.ofBlock(GregTech_API.sBlockCasings6, 10)) //Hermetic Casing X
            .addElement('i', StructureUtility.ofChain(
                    GT_StructureUtility.ofHatchAdder(TileEntityDysonSwarm::addInputToMachineList, CASING_INDEX_LAUNCH, 2),
                    StructureUtility.ofBlock(GSBlocks.DysonSpherePart, 2))) //Dyson Swarm Module Deployment Unit Base Casing
            .addElement('j', StructureUtility.ofBlock(GSBlocks.DysonSpherePart, 3)) //Dyson Swarm Module Deployment Unit Core
            .addElement('k', GT_StructureUtility.ofFrame(Materials.Longasssuperconductornameforuhvwire))
            .addElement('m', StructureUtility.ofBlock(GSBlocks.DysonSpherePart, 4)) //Dyson Swarm Module Deployment Unit Superconducting Magnet
            .addElement('n', GT_StructureUtility.ofHatchAdder(TileEntityDysonSwarm::addMaintenanceToMachineList, CASING_INDEX_FLOOR, 3))
            .addElement('o', StructureUtility.ofChain(
            		GT_StructureUtility.ofHatchAdder(TileEntityDysonSwarm::addDataConnectorToMachineList, CASING_INDEX_COMMAND, 4),
            		StructureUtility.ofBlock(GSBlocks.DysonSpherePart, 5))) //Dyson Swarm Command Center Base Casing
            .addElement('p', StructureUtility.ofBlock(GSBlocks.DysonSpherePart, 6)) //Dyson Swarm Command Center Primary Windings
            .addElement('s', StructureUtility.ofBlock(GSBlocks.DysonSpherePart, 7)) //Dyson Swarm Command Center Secondary Windings
            .addElement('t', StructureUtility.ofBlock(GSBlocks.DysonSpherePart, 8)) //Dyson Swarm Command Center Toroid Casing
            .addElement('x', StructureUtility.ofBlock(GSBlocks.DysonSpherePart, 0)) //Dyson Swarm Energy Receiver Base Casing
            .addElement('y', StructureUtility.ofBlock(GSBlocks.DysonSpherePart, 5)) //Dyson Swarm Command Center Base Casing
            .addElement('z', StructureUtility.ofBlock(GSBlocks.DysonSpherePart, 2)) //Dyson Swarm Module Deployment Unit Base Casing
            .build();

    private long euPerTick = 0;
    private double powerFactor = 0.0;
    private int moduleCount = 0;

    public TileEntityDysonSwarm(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public TileEntityDysonSwarm(String aName) {
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

    @Override
    public void onServerStart() {
    	powerFactor = getPowerFactor();
    }

    /*************
     * STRUCTURE *
     *************/

    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {
        structureBuild_EM(STRUCTURE_PIECE_MAIN, 10, 18, 3, hintsOnly, stackSize);
    }

    @Override
    public IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getStructure_EM() {
        return STRUCTURE_DEFINITION;
    }

    @Override
    public boolean checkMachine_EM(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        return structureCheck_EM(STRUCTURE_PIECE_MAIN, 10, 18, 3)
        		&& mMaintenanceHatches.size() == 1
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
        for(GT_MetaTileEntity_Hatch_InputBus bus : mInputBusses) {
            if(isValidMetaTileEntity(bus)) {
                for(int i = 0; i < bus.getBaseMetaTileEntity().getSizeInventory(); i++) {
                    ItemStack stack = bus.getBaseMetaTileEntity().getStackInSlot(i);
                    if(stack != null && stack.getItem() == GSItems.DysonSwarmParts && stack.getItemDamage() == 0) {
                    	moduleCount += stack.stackSize;
                        stack = null;
                    }
                }
            }
        }

    	eRequiredData = (long) Math.ceil(Math.max(GSConfigCore.computationFactor * Math.pow(moduleCount, GSConfigCore.computationExponent) - GSConfigCore.baseComputation, 0));
        euPerTick     = (long) (((long) moduleCount) * GSConfigCore.euPerModule * powerFactor);

        if(moduleCount > 0 && depleteInput(GSConfigCore.coolantFluid)) {
            // With a certain chance (configurable), the size of the ItemStack(s) is reduced.
            // This has the effect that the player must constantly replace "broken" Modules.
        	moduleDestroyer.accept(this);
            mEfficiencyIncrease = 10000;
            mMaxProgresstime = 20;
            return true;
        }
        mEfficiency = 0;
        return false;
    }

    @Override
    public boolean energyFlowOnRunningTick(ItemStack aStack, boolean allowProduction) {
    	if(allowProduction && euPerTick > 0) {
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
        for (GT_MetaTileEntity_Hatch_Dynamo tHatch : mDynamoHatches) {
            if (GT_MetaTileEntity_MultiBlockBase.isValidMetaTileEntity(tHatch)) {
                if (tHatch.maxEUOutput() < euVar && !allowMixedVoltages) {
                    explodeMultiblock();
                }
                diff = tHatch.maxEUStore() - tHatch.getBaseMetaTileEntity().getStoredEU();
                if (diff > 0) {
                    if (euVar > diff) {
                        tHatch.setEUVar(tHatch.maxEUStore());
                        euVar -= diff;
                    } else if (euVar <= diff) {
                        tHatch.setEUVar(tHatch.getBaseMetaTileEntity().getStoredEU() + euVar);
                        return true;
                    }
                }
            }
        }
        for (GT_MetaTileEntity_Hatch_DynamoMulti tHatch : eDynamoMulti) {
            if (GT_MetaTileEntity_MultiBlockBase.isValidMetaTileEntity(tHatch)) {
                if (tHatch.maxEUOutput() < euVar && !allowMixedVoltages) {
                    explodeMultiblock();
                }
                diff = tHatch.maxEUStore() - tHatch.getBaseMetaTileEntity().getStoredEU();
                if (diff > 0) {
                    if (euVar > diff) {
                        tHatch.setEUVar(tHatch.maxEUStore());
                        euVar -= diff;
                    } else if (euVar <= diff) {
                        tHatch.setEUVar(tHatch.getBaseMetaTileEntity().getStoredEU() + euVar);
                        return true;
                    }
                }
            }
        }
        explodeMultiblock(); // If no energy is left, we would've already exploded. Thus no check is needed here
        return false;
    }

    /****************
     * CLIENT STUFF *
     ****************/

    @Override
    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex, boolean aActive, boolean aRedstone) {
        if(aSide == aFacing) {
            if(aActive) return new ITexture[] {
                    BlockIcons.getCasingTextureForId(CASING_INDEX_RECEIVER),
                    TextureFactory.of(OVERLAY_FRONT_ACTIVE),
                    TextureFactory.builder().addIcon(OVERLAY_FRONT_ACTIVE_GLOW).glow().build()
            };
            return new ITexture[] {
                    BlockIcons.getCasingTextureForId(CASING_INDEX_RECEIVER),
                    TextureFactory.of(OVERLAY_FRONT),
                    TextureFactory.builder().addIcon(OVERLAY_FRONT_GLOW).glow().build()
            };
        }
        return new ITexture[] {BlockIcons.getCasingTextureForId(CASING_INDEX_RECEIVER)};
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
    	NumberFormat nf = NumberFormat.getNumberInstance();
    	nf.setMaximumFractionDigits(5);
    	String eu_module = nf.format(GSConfigCore.euPerModule);
    	String destroy_chance = nf.format(GSConfigCore.destroyModuleFactor);
    	String destroy_exponent = nf.format(GSConfigCore.destroyModuleExponent);
    	String fluid_amount = nf.format(GSConfigCore.coolantConsumption);
    	String fluid_name = GSConfigCore.coolantFluid.getLocalizedName();
    	String a = nf.format(GSConfigCore.computationFactor);
    	String b = nf.format(GSConfigCore.computationExponent);
    	String c = nf.format(GSConfigCore.baseComputation);

        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Dyson Swarm")
        .addInfo(ITALIC + LORE_TOOLTIP)
        .addInfo("Controller Block for the Dyson Swarm Command Center")
        .addInfo("Put Dyson Swarm Modules in the Input Bus(ses) to send them to the next star.")
        .addInfo("Outputs " + eu_module + "*f EU/t, where f is a dimension-dependent factor.")
        .addInfo("Each second, n of m Dyson Swarm Modules are destroyed according to this formula:")
        .addInfo(" n=X*" + destroy_chance + "*m^" + destroy_exponent + ", where X is a normally distributed random number between 0 and 2 with mean 1.")
        .addInfo("Requires " + fluid_amount + "L/s of " + fluid_name + ".")
        .addInfo("Requires n computation per tick according to this formula:")
        .addInfo(" n=" + a + "*m^" + b + "-" + c + ", where m is the amount of modules.")
        .addSeparator()
        .beginStructureBlock(16, 20, 16, false)
        .addStructureInfo(ITALIC + "This structure is too complex to describe, use the Multiblock Structure Hologram Projector!")
        .addDynamoHatch("Can use Single- and/or Multi-Amp Dynamo Hatches and/or Laser Source Hatches (1 - 12)", 1)
        .addMaintenanceHatch("1", 3)
        .addInputBus("1 - 11", 2)
        .addInputHatch("1 - 11", 2)
        .addOtherStructurePart("Optical Slave Connector", "1 - 24", 4)
        .addStructureInfo("")
        .addStructureInfo(ITALIC + "Additionally needed:")
        .addStructureInfo("Energy Receiver Base Casing: 53 - 64 (depending on the amount of Dynamo Hatches)")
        .addStructureInfo("Energy Receiver Dish Block: 81")
        .addStructureInfo("Module Deployment Unit Base Casing: 62 - 72 (depending on the amount of Input Busses & Hatches)")
        .addStructureInfo("Module Deployment Unit Core: 1")
        .addStructureInfo("Module Deployment Unit Superconducting Magnet: 32")
        .addStructureInfo("Control Center Base Casing: 115 - 138 (depending on the amount of Optical Slave Connectors)")
        .addStructureInfo("Control Center Primary Windings: 20")
        .addStructureInfo("Control Center Secondary Windings: 12")
        .addStructureInfo("Control Center Toroid Casing: 128")
        .addStructureInfo("Ultra Hign Strenght Concrete Floor: 255")
        .addStructureInfo("Awakened Draconium Coil Block: 9")
        .addStructureInfo("Hermetic Casing X: 1")
        .addStructureInfo("Titanium Frame Box: 16")
        .addStructureInfo("HSS-S Frame Box: 23")
        .addStructureInfo("Superconductor Base UHV Frame Box: 64")
        .toolTipFinisher(DARK_PURPLE + GalaxySpace.NAME);
        return tt;
    }

    @Override
    public String[] getInfoData() {
        return new String[] {
                LIGHT_PURPLE + "Operational Data:" + RESET,
                "Modules: " + YELLOW + NumberFormat.getNumberInstance().format(moduleCount) + RESET,
                "Power Factor: " + (powerFactor < 1.0f ? RED : GREEN) + NumberFormat.getPercentInstance().format(powerFactor) + RESET,
                "Theoretical Output: " + YELLOW + NumberFormat.getNumberInstance().format(((long) moduleCount * GSConfigCore.euPerModule * powerFactor)) + RESET + " EU/t",
                "Current Output: " + YELLOW + NumberFormat.getNumberInstance().format(euPerTick) + RESET + " EU/t",
                "Computation required: " + YELLOW + NumberFormat.getNumberInstance().format(eRequiredData) + RESET + "/t",
                "Maintenance Status: " + (getRepairStatus() == getIdealStatus()
                        ? GREEN + "Working perfectly" + RESET
                        : RED + "Has problems" + RESET),
                "---------------------------------------------"
        };
    }

    /******************
     * HELPER METHODS *
     ******************/

    public double getPowerFactor() {
    	WorldProvider provider = this.getBaseMetaTileEntity().getWorld().provider;
        if(provider instanceof IOrbitDimension) {
            return powerFactors.getOrDefault("SS_" + ((IOrbitDimension) provider).getPlanetToOrbit(), GSConfigCore.powerFactorDefault);
        }
        else if(provider.getClass().getName().equals("zarkov.utilityworlds.UW_WorldProviderGarden")) {
            return powerFactors.getOrDefault("UW_Garden", GSConfigCore.powerFactorDefault);
        }
        else if(provider.getClass().getName().equals("zarkov.utilityworlds.UW_WorldProviderMining")) {
            return powerFactors.getOrDefault("UW_Mining", GSConfigCore.powerFactorDefault);
        }
        else if(provider.getClass().getName().equals("zarkov.utilityworlds.UW_WorldProviderVoid")) {
            return powerFactors.getOrDefault("UW_Void", GSConfigCore.powerFactorDefault);
        }
        else {
            return powerFactors.getOrDefault(String.valueOf(provider.dimensionId), GSConfigCore.powerFactorDefault);
        }
    }

    public double calculateOutput() {
    	return ((long) moduleCount) * GSConfigCore.euPerModule * powerFactor;
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
    	super.loadNBTData(aNBT);
    	moduleCount = aNBT.getInteger("moduleCount");
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
    	super.saveNBTData(aNBT);
    	aNBT.setInteger("moduleCount", moduleCount);
    }

    public static void initCommon() {
        GSItems.DysonSwarmController = new TileEntityDysonSwarm(1050, "DysonSwarm", "Dyson Swarm Ground Unit").getStackForm(1);
        powerFactors = new HashMap<>();

        // parse the powerFactors config entry from String[] to Map<String, Double>
        for (String s : GSConfigCore.powerFactors) {
            String[] parts = s.split(":");
            try {
                powerFactors.put(parts[0], Double.parseDouble(parts[1]));
            } catch (Exception e) {
                GalaxySpace.error("Error while trying to parse \"" + s + "\"!");
                e.printStackTrace();
            }
        }

        // If the Module Destruction chance is greater than zero is, initialize the method to randomly destroy modules.
        // If the Module Destruction chance is zero or less, always return true.
        if(GSConfigCore.destroyModuleFactor > 0.0f) {
            moduleDestroyer = tile -> {
            	tile.moduleCount -= (int) (getRandom() * GSConfigCore.destroyModuleFactor * Math.pow(tile.moduleCount, GSConfigCore.destroyModuleExponent));

            	if(tile.moduleCount < 0) {
            		tile.moduleCount = 0;
            	}
            };

        } else {
            moduleDestroyer = tile -> {/*NO-OP*/};
        }
    }

    /**
     * @return a Gaussian random value between 0.0 and 2.0 with mean 1.0
     */
    private static double getRandom() {
    	double d = XSTR.XSTR_INSTANCE.nextGaussian() + 1.0;
    	return d < 0.0 || d > 2.0 ? getRandom() : d;
    }

    public static void initClient() {
        initCommon();
        BlockIcons.setCasingTextureForId(CASING_INDEX_RECEIVER, TextureFactory.of(GSBlocks.DysonSpherePart, 0));
        BlockIcons.setCasingTextureForId(CASING_INDEX_LAUNCH, TextureFactory.of(GSBlocks.DysonSpherePart, 2));
        BlockIcons.setCasingTextureForId(CASING_INDEX_COMMAND, TextureFactory.of(GSBlocks.DysonSpherePart, 5));
        BlockIcons.setCasingTextureForId(CASING_INDEX_FLOOR, TextureFactory.of(GSBlocks.DysonSpherePart, 9));
        OVERLAY_FRONT = TextureFactory.of(new CustomIcon("iconsets/OVERLAY_FRONT_DYSONSPHERE"));
        OVERLAY_FRONT_ACTIVE = TextureFactory.of(new CustomIcon("iconsets/OVERLAY_FRONT_DYSONSPHERE_ACTIVE"));
        OVERLAY_FRONT_GLOW = new CustomIcon("iconsets/OVERLAY_FRONT_DYSONSPHERE_GLOW");
        OVERLAY_FRONT_ACTIVE_GLOW = new CustomIcon("iconsets/OVERLAY_FRONT_DYSONSPHERE_ACTIVE_GLOW");
    }

    static {
    	String[] possibleLore = new String[] {
    			"Wait, this isn't an army of vacuum cleaners?",
    			"Number 9? Not quite.",
    			"Not the game.",
    			"Basically solar panels.",
    			"Invented by a free man."};
    	LORE_TOOLTIP = possibleLore[XSTR.XSTR_INSTANCE.nextInt(possibleLore.length)];
    }

}
