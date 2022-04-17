package com.ryan.eggwars.oldmechanics;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class OldItems {
    // https://minecraft.gamepedia.com/Sword?oldid=760162#Damage
    
    public static ItemStack getOldSword(Material material) {
        ItemStack sword = new ItemStack(material);
        ItemMeta swordMeta = sword.getItemMeta();
        int damage = switch (material) {
            case DIAMOND_SWORD -> 8;
            case IRON_SWORD -> 7;
            case STONE_SWORD -> 6;
            case GOLDEN_SWORD -> 4;
            case WOODEN_SWORD -> 5;
            default -> 2;
        };

        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", damage, AttributeModifier.Operation.ADD_NUMBER);
        swordMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
        
        swordMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        swordMeta.setUnbreakable(true);
        sword.setItemMeta(swordMeta);
        
        return sword;
    }
    
    // armor values haven't changed, makes them unbreakable
    public static ItemStack getArmor(Material material) {
        ItemStack armor = new ItemStack(material);
        ItemMeta armorMeta = armor.getItemMeta();
        armorMeta.setUnbreakable(true);
        armor.setItemMeta(armorMeta);
        
        return armor;
    }
}
