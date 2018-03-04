package plus.crates.Crates;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import plus.crates.CratesPlus;

import java.util.ArrayList;
import java.util.List;

public class Key {
    private CratesPlus cratesPlus;
    private String crateName = "";
    private Material material = Material.CHEST;
    private String name = "";
    private List<String> lore = null;
    private boolean enchanted = false;

    public Key(String crateName, Material material, String name, boolean enchanted, CratesPlus cratesPlus) {
        this.cratesPlus = cratesPlus;
        this.crateName = crateName;
        if (material == null)
            material = Material.TRIPWIRE_HOOK;
        this.material = material;
        this.name = name;
        this.enchanted = enchanted;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLore() {
        if (this.lore == null || this.lore.size() == 0) {
            this.lore = new ArrayList<>();
            this.lore.add(ChatColor.GRAY + "Right-Click on a \"" + getCrate().getName(true) + ChatColor.GRAY + "\" crate");
            this.lore.add(ChatColor.GRAY + "to win an item!");
            this.lore.add("");
        }
        return this.lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public boolean isEnchanted() {
        return enchanted;
    }

    public void setEnchanted(boolean enchanted) {
        this.enchanted = enchanted;
    }

    public ItemStack getKeyItem(Integer amount) {
        ItemStack keyItem = new ItemStack(getMaterial());
        if (isEnchanted())
            keyItem.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta keyItemMeta = keyItem.getItemMeta();
        String title = getName().replaceAll("%type%", getCrate().getName(true));
        keyItemMeta.setDisplayName(title);
        keyItemMeta.setLore(getLore());
        ArrayList<ItemFlag> flags = new ArrayList<>();
        flags.add(ItemFlag.HIDE_ENCHANTS);
        keyItemMeta = cratesPlus.getVersion_util().handleItemFlags(keyItemMeta, flags);
        keyItem.setItemMeta(keyItemMeta);
        if (amount > 1)
            keyItem.setAmount(amount);
        return keyItem;
    }

    public String getCrateName() {
        return crateName;
    }

    public Crate getCrate() {
        return cratesPlus.getConfigHandler().getCrate(getCrateName().toLowerCase());
    }

}
