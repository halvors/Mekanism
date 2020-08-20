package mekanism.common.item;

import mekanism.common.Mekanism;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRadioactive extends Item {
    private double radioactivity;

    public ItemRadioactive(double radioactivity, Properties properties) {
        super(properties);

        this.radioactivity = radioactivity;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (entity instanceof LivingEntity) {
            Mekanism.radiationManager.radiate((LivingEntity) entity, radioactivity);
        }

        super.inventoryTick(itemStack, world, entity, itemSlot, isSelected);
    }
}