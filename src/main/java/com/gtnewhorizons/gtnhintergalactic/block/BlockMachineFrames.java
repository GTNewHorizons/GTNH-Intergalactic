package galaxyspace.core.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import galaxyspace.GalaxySpace;
import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockMachineFrames extends Block implements ITerraformableBlock
{
    public static String[] metadata;
    protected IIcon[] textures;

    public BlockMachineFrames() {
        super(Material.rock);
        this.textures = new IIcon[BlockMachineFrames.metadata.length];
        this.setBlockName("MachineFrames");
        this.setHardness(2.0f);
        this.setStepSound(Block.soundTypeStone);
        this.setHarvestLevel("pickaxe", 3);
    }

    @Override
	@SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTabToDisplayOn() {
        return GalaxySpace.tabBlocks;
    }

    @Override
	public boolean isTerraformable(final World world, final int x, final int y, final int z) {
        return false;
    }

    @Override
	public int damageDropped(final int metadata) {
        return metadata;
    }

    @Override
	public void onBlockPlacedBy(final World world, final int x, final int y, final int z, final EntityLivingBase entity, final ItemStack is) {
        world.setBlockMetadataWithNotify(x, y, z, is.getItemDamage(), 3);
    }

    @Override
	public void registerBlockIcons(final IIconRegister iconRegister) {
        super.registerBlockIcons(iconRegister);
        this.textures[0] = iconRegister.registerIcon("galaxyspace:overworld/machine");
        this.textures[1] = iconRegister.registerIcon("galacticraftasteroids:machine");
    }

    @Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(final int side, final int meta) {
        if (meta < 0 || meta > this.textures.length) {
            return this.textures[0];
        }
        return this.textures[meta];
    }

    @Override
	public void getSubBlocks(final Item block, final CreativeTabs creativeTabs, final List list) {
        list.add(new ItemStack(block, 1, 0));
    }

    static {
        BlockMachineFrames.metadata = new String[] { "MachineFrame", "AdvMachineFrame" };
    }
}
