package org.syju.bronze_age_tachibana.registry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.*;
import org.syju.bronze_age_tachibana.Tiers;

import static org.syju.bronze_age_tachibana.Tags.*;
import static org.syju.bronze_age_tachibana.registry.Registrate.REGISTRATE;

public class AllItems {
    // Basic items
    // Raw items
    public static final ItemEntry<Item>
            RAW_TIN = tagIngredient("raw_tin", forgeItemTag("raw_materials/tin"), forgeItemTag("raw_materials"));

    // Ingots and nuggets
    public static final ItemEntry<Item>
            TIN_INGOT = tagIngredient("tin_ingot", forgeItemTag("ingots/tin"), forgeItemTag("ingots")),
            TIN_NUGGET = tagIngredient("tin_nugget", forgeItemTag("nuggets/tin"), forgeItemTag("nuggets")),
            BRONZE_INGOT = tagIngredient("bronze_ingot", forgeItemTag("ingots/bronze"), forgeItemTag("ingots")),
            BRONZE_NUGGET = tagIngredient("bronze_nugget", forgeItemTag("nuggets/bronze"), forgeItemTag("nuggets"));

    // Sheets
    public static final ItemEntry<Item>
            TIN_SHEET = tagIngredient("tin_plate", forgeItemTag("plates"), forgeItemTag("plates/tin")),
            BRONZE_SHEET = tagIngredient("bronze_plate", forgeItemTag("plates"), forgeItemTag("plates/bronze"));

    // Tools and weapons
    // Constructor: Tier tier, int baseDamage, float attackSpeed, Item.Properties properties
    public static final ItemEntry<SwordItem> BRONZE_SWORD =
            REGISTRATE.item("bronze_sword", p -> new SwordItem(Tiers.BRONZE,3,-2.4f, p))
                    .tag(forgeItemTag("swords"))
                    .lang("Bronze Sword")
                    .register();
    public static final ItemEntry<PickaxeItem> BRONZE_PICKAXE =
            REGISTRATE.item("bronze_pickaxe", p -> new PickaxeItem(Tiers.BRONZE,1,-2.8f, p))
                    .tag(forgeItemTag("pickaxes"))
                    .register();
    public static final ItemEntry<ShovelItem> BRONZE_SHOVEL =
            REGISTRATE.item("bronze_shovel", p -> new ShovelItem(Tiers.BRONZE,1.5f,-3.0f, p))
                    .tag(forgeItemTag("shovels"))
                    .register();
    public static final ItemEntry<AxeItem> BRONZE_AXE =
            REGISTRATE.item("bronze_axe", p -> new AxeItem(Tiers.BRONZE, 6.0f, -3.1f, p))
                    .tag(forgeItemTag("axes"))
                    .register();
    public static final ItemEntry<HoeItem> BRONZE_HOE =
            REGISTRATE.item("bronze_hoe", p -> new HoeItem(Tiers.BRONZE, -2,-1.0f, p))
                    .tag(forgeItemTag("hoes"))
                    .register();

    // Armor
    public static final ItemEntry<ArmorItem> BRONZE_HELMET =
            REGISTRATE.item("bronze_helmet", p -> new ArmorItem(Tiers.BronzeArmor.BRONZE, ArmorItem.Type.HELMET, p))
                    .tag(forgeItemTag("armors")).tag(forgeItemTag("armors/helmets"))
                    .lang("Bronze Helmet")
                    .register();
    public static final ItemEntry<ArmorItem> BRONZE_CHESTPLATE =
            REGISTRATE.item("bronze_chestplate", p -> new ArmorItem(Tiers.BronzeArmor.BRONZE, ArmorItem.Type.CHESTPLATE, p))
                    .tag(forgeItemTag("armors")).tag(forgeItemTag("armors/chestplates"))
                    .lang("Bronze Chestplate")
                    .register();
    public static final ItemEntry<ArmorItem> BRONZE_LEGGINGS =
            REGISTRATE.item("bronze_leggings", p -> new ArmorItem(Tiers.BronzeArmor.BRONZE, ArmorItem.Type.LEGGINGS, p))
                    .tag(forgeItemTag("armors")).tag(forgeItemTag("armors/leggings"))
                    .lang("Bronze Leggings")
                    .register();
    public static final ItemEntry<ArmorItem> BRONZE_BOOTS =
            REGISTRATE.item("bronze_boots", p -> new ArmorItem(Tiers.BronzeArmor.BRONZE, ArmorItem.Type.BOOTS, p))
                    .tag(forgeItemTag("armors")).tag(forgeItemTag("armors/boots"))
                    .lang("Bronze Boots")
                    .register();


    // Load it
    public static void initialize() { }
}
