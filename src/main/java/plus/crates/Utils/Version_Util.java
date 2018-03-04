package plus.crates.Utils;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.SpawnEgg;
import plus.crates.CratesPlus;

import java.util.List;

public class Version_Util {
    protected CratesPlus cratesPlus;

    public Version_Util(CratesPlus cratesPlus) {
        this.cratesPlus = cratesPlus;
    }

    public ItemStack getItemInPlayersHand(Player player) {
        return player.getItemInHand();
    }

    public ItemStack getItemInPlayersOffHand(Player player) {
        return null;
    }

    public void removeItemInOffHand(Player player) {

    }

    public ItemStack getSpawnEgg(EntityType entityType, Integer amount) {
        ItemStack egg = new ItemStack(Material.MONSTER_EGG, amount);
        if (entityType != null) {
            SpawnEgg spawnEgg = new SpawnEgg(entityType);
            egg.setData(spawnEgg);
        }
        return egg;
    }

    public EntityType getEntityTypeFromItemStack(ItemStack itemStack) {
        SpawnEgg spawnEgg = (SpawnEgg) itemStack.getData();
        return spawnEgg.getSpawnedType();
    }

    public ItemMeta handleItemFlags(ItemMeta itemMeta, List<ItemFlag> flags) {
    	
    	for (ItemFlag flag : flags) {
    		itemMeta.addItemFlags(flag);
		}
    	
        return itemMeta;
    }

}
