package com.minecraft7771.gtnhintergalactic.tile.multi.elevator;

import java.util.*;
import java.util.function.Consumer;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.ImmutableList;
import com.gtnewhorizon.structurelib.StructureLibAPI;
import com.gtnewhorizon.structurelib.structure.*;
import com.minecraft7771.gtnhintergalactic.block.IGBlocks;
import com.minecraft7771.gtnhintergalactic.tile.multi.elevatormodules.TileEntityModuleBase;

import gregtech.api.interfaces.IHatchElement;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.util.IGT_HatchAdder;
import gregtech.common.misc.spaceprojects.SpaceProjectManager;
import gregtech.common.misc.spaceprojects.interfaces.ISpaceProject;

/**
 * Utility functions of the Space Elevator
 *
 * @author minecraft7771
 */
public class ElevatorUtil {

    /**
     * Check if the owner of the machine has the space project that is needed
     *
     * @param machineOwner   UUID of the machine owner
     * @param neededProject  Project that is needed, can be null if any
     * @param neededLocation Location that is needed, can be null if any
     * @return True if the needed project is available, else false
     */
    public static boolean isProjectAvailable(UUID machineOwner, String neededProject, String neededLocation) {
        if (neededProject != null && !neededProject.equals("")) {
            if (neededLocation != null && !neededLocation.equals("")) {
                ISpaceProject project = SpaceProjectManager
                        .getTeamProject(machineOwner, SpaceProjectManager.getLocation(neededLocation), neededProject);
                return project != null && project.isFinished();
            } else {
                ArrayList<ISpaceProject> projects = new ArrayList<>(
                        SpaceProjectManager.getTeamSpaceProjects(machineOwner));
                for (ISpaceProject project : projects) {
                    if (project != null && project.isFinished()
                            && neededLocation != null
                            && neededLocation.equals(project.getProjectLocation().getName())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public interface IBlockAdder<T> {

        /**
         * Callback on block added, needs to check if block is valid (and add it)
         *
         * @param block block attempted to add
         * @param meta  meta of block attempted to add
         * @param world World of the block
         * @param x     X coordinate of the block
         * @param y     Y coordinate of the block
         * @param z     Z coordinate of the block
         * @return is structure still valid
         */
        boolean apply(T t, Block block, int meta, World world, int x, int y, int z);
    }

    /**
     * Modified block adder of the structure utility. This one parses world and coordinate of the block to the block
     * adder.
     *
     * @param iBlockAdder  Used block adder that verifies the validity
     * @param defaultBlock Default block in this place
     * @param defaultMeta  Default meta of the block in this place
     * @param <T>          Structure
     * @return Structure element
     */
    public static <T> IStructureElement<T> ofBlockAdder(IBlockAdder<T> iBlockAdder, Block defaultBlock,
            int defaultMeta) {
        if (iBlockAdder == null || defaultBlock == null) {
            throw new IllegalArgumentException();
        }
        if (defaultBlock instanceof ICustomBlockSetting) {
            return new IStructureElement<T>() {

                @Override
                public boolean check(T t, World world, int x, int y, int z) {
                    Block worldBlock = world.getBlock(x, y, z);
                    return iBlockAdder.apply(t, worldBlock, worldBlock.getDamageValue(world, x, y, z), world, x, y, z);
                }

                @Override
                public boolean placeBlock(T t, World world, int x, int y, int z, ItemStack trigger) {
                    ((ICustomBlockSetting) defaultBlock).setBlock(world, x, y, z, defaultMeta);
                    return true;
                }

                @Override
                public boolean spawnHint(T t, World world, int x, int y, int z, ItemStack trigger) {
                    StructureLibAPI.hintParticle(world, x, y, z, defaultBlock, defaultMeta);
                    return true;
                }

                @Override
                public PlaceResult survivalPlaceBlock(T t, World world, int x, int y, int z, ItemStack trigger,
                        IItemSource s, EntityPlayerMP actor, Consumer<IChatComponent> chatter) {
                    if (check(t, world, x, y, z)) return PlaceResult.SKIP;
                    return StructureUtility
                            .survivalPlaceBlock(defaultBlock, defaultMeta, world, x, y, z, s, actor, chatter);
                }
            };
        } else {
            return new IStructureElement<T>() {

                @Override
                public boolean check(T t, World world, int x, int y, int z) {
                    Block worldBlock = world.getBlock(x, y, z);
                    return iBlockAdder.apply(t, worldBlock, worldBlock.getDamageValue(world, x, y, z), world, x, y, z);
                }

                @Override
                public boolean placeBlock(T t, World world, int x, int y, int z, ItemStack trigger) {
                    world.setBlock(x, y, z, defaultBlock, defaultMeta, 2);
                    return true;
                }

                @Override
                public boolean spawnHint(T t, World world, int x, int y, int z, ItemStack trigger) {
                    StructureLibAPI.hintParticle(world, x, y, z, defaultBlock, defaultMeta);
                    return true;
                }

                @Override
                public PlaceResult survivalPlaceBlock(T t, World world, int x, int y, int z, ItemStack trigger,
                        IItemSource s, EntityPlayerMP actor, Consumer<IChatComponent> chatter) {
                    if (check(t, world, x, y, z)) return PlaceResult.SKIP;
                    return StructureUtility
                            .survivalPlaceBlock(defaultBlock, defaultMeta, world, x, y, z, s, actor, chatter);
                }
            };
        }
    }

    /**
     * Get the list of tiered motor blocks
     *
     * @return List of tiered motor blocks
     */
    public static List<Pair<Block, Integer>> getMotorTiers() {
        return ImmutableList.of(
                Pair.of(IGBlocks.SpaceElevatorMotor, 0),
                Pair.of(IGBlocks.SpaceElevatorMotor, 1),
                Pair.of(IGBlocks.SpaceElevatorMotor, 2),
                Pair.of(IGBlocks.SpaceElevatorMotor, 3),
                Pair.of(IGBlocks.SpaceElevatorMotor, 4));
    }

    /**
     * Get the converter that returns the motor tier from a block and its meta
     *
     * @return Motor tier converter
     */
    public static ITierConverter<Integer> motorTierConverter() {
        return (block, meta) -> {
            if (block == null) {
                return -1;
            } else if (block == IGBlocks.SpaceElevatorMotor) {
                return meta + 1;
            }
            return -1;
        };
    }

    /**
     * Additional hatch elements used in the Space Elevator
     *
     * @author minecraft7771
     */
    public enum ProjectModuleElement implements IHatchElement<TileEntitySpaceElevator> {

        ProjectModule(TileEntitySpaceElevator::addProjectModuleToMachineList, TileEntityModuleBase.class) {

            @Override
            public long count(TileEntitySpaceElevator tileEntity) {
                return tileEntity.mProjectModuleHatches.size();
            }
        };

        private final List<Class<? extends IMetaTileEntity>> mteClasses;
        private final IGT_HatchAdder<TileEntitySpaceElevator> adder;

        @SafeVarargs
        ProjectModuleElement(IGT_HatchAdder<TileEntitySpaceElevator> adder,
                Class<? extends IMetaTileEntity>... mteClasses) {
            this.mteClasses = Collections.unmodifiableList(Arrays.asList(mteClasses));
            this.adder = adder;
        }

        @Override
        public List<? extends Class<? extends IMetaTileEntity>> mteClasses() {
            return mteClasses;
        }

        public IGT_HatchAdder<? super TileEntitySpaceElevator> adder() {
            return adder;
        }
    }
}
