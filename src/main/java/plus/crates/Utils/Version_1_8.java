package plus.crates.Utils;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import plus.crates.CratesPlus;

import java.util.List;

public class Version_1_8 extends Version_Util {

    public Version_1_8(CratesPlus cratesPlus) {
        super(cratesPlus);
    }

    public ItemMeta handleItemFlags(ItemMeta itemMeta, List<ItemFlag> flags) {
        if (flags.size() > 0) {
            for (ItemFlag flag : flags) {
                try {
                   
                    if (flag != null) {
                        itemMeta.addItemFlags(flag);
                    }
                } catch (Exception ignored) {

                }
            }
        }
        return itemMeta;
    }

}
