package galaxyspace.core.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import galaxyspace.GalaxySpace;

public class ItemDSParts extends Item {

    private static final String[] names = new String[] { "dysonSwarmModule", "TaHfCNanofibers", "NtNanofibers",
            "UHTResistantMesh", "TaHfNanoparticles", "NtNanoparticles" };
    private static final IIcon[] icons = new IIcon[names.length];

    public ItemDSParts() {
        setCreativeTab(GalaxySpace.tabItems);
        setHasSubtypes(true);
        setUnlocalizedName("DysonSwarmParts");
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        return icons[meta % icons.length];
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List variants) {
        for (int i = 0; i < names.length; i++) {
            variants.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item." + names[stack.getItemDamage() % names.length];
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        for (int i = 0; i < names.length; i++) {
            icons[i] = iconRegister.registerIcon(GalaxySpace.ASSET_PREFIX + ":" + names[i]);
        }
    }
}
