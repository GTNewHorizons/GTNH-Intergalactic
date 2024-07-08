package com.gtnewhorizons.gtnhintergalactic.block;

import net.minecraft.block.Block;

import com.gtnewhorizons.gtnhintergalactic.item.ItemBlockDysonSwarmPart;
import com.gtnewhorizons.gtnhintergalactic.item.ItemBlockSpaceElevatorCable;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * List of all blocks of this mod
 */
public class IGBlocks {

    public static Block SpaceElevatorCable;
    public static Block DysonSwarmBlocks;
    public static BlockMachineFrames MachineFrames;
    public static BlockCasingSpaceElevator SpaceElevatorCasing;
    public static BlockCasingSpaceElevatorMotor SpaceElevatorMotor;

    /**
     * Initialize the blocks of this mod
     */
    public static void init() {
        SpaceElevatorCable = new BlockSpaceElevatorCable();
        GameRegistry.registerBlock(SpaceElevatorCable, ItemBlockSpaceElevatorCable.class, "spaceelevatorcable");
        GameRegistry.registerBlock(DysonSwarmBlocks, ItemBlockDysonSwarmPart.class, "dysonswarmparts");
        SpaceElevatorCasing = new BlockCasingSpaceElevator();
        SpaceElevatorMotor = new BlockCasingSpaceElevatorMotor();
        MachineFrames = new BlockMachineFrames();
    }
}
