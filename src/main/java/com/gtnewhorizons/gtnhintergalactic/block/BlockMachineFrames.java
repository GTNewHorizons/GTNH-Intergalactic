package galaxyspace.core.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

import galaxyspace.GalaxySpace;
import gregtech.api.GregTech_API;
import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;

public class BlockMachineFrames extends Block implements ITerraformableBlock {

    public BlockMachineFrames() {
        super(Material.rock);
        this.setBlockName("MachineFrame");
        this.setHardness(2.0f);
        this.setStepSound(soundTypeStone);
        this.setHarvestLevel("pickaxe", 3);
        this.setCreativeTab(GalaxySpace.tabBlocks);
        this.setBlockTextureName(GalaxySpace.ASSET_PREFIX + ":overworld/machine");
        GregTech_API.registerMachineBlock(this, -1);
    }

    @Override
    public boolean isTerraformable(final World world, final int x, final int y, final int z) {
        return false;
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        if (GregTech_API.isMachineBlock(this, world.getBlockMetadata(x, y, z))) {
            GregTech_API.causeMachineUpdate(world, x, y, z);
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        if (GregTech_API.isMachineBlock(block, meta)) {
            GregTech_API.causeMachineUpdate(world, x, y, z);
        }
    }
}
