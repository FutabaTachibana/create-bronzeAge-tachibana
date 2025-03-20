package org.syju.bronze_age_tachibana;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

import org.jetbrains.annotations.NotNull;
import org.syju.bronze_age_tachibana.registry.AllItems;

import java.util.function.Supplier;

public class Tiers {
    // Constructor:
    // int level, int uses (durability), float speed, float attackDamageBonus, TagKey<Block> tags, Supplier<Ingredient> repairIngredient
    public static final Tier BRONZE = new ForgeTier(
            2,
            420,
            6.5f,
            2.0f,
            15,
            BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(AllItems.BRONZE_INGOT.get())
    );
    public static final Tier TIN = new ForgeTier(
            2,
            420,
            6.5f,
            2.0f,
            15,
            BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(AllItems.TIN_INGOT.get())
    );

    // Define custom armor materials by `ArmorMaterial`
    // https://minecraft.wiki/w/Armor_materials
    // Constructor:
    // ID, Durability multiplier multiplies, Armor Value, Enchantment Value
    // Sounds of equip, Toughness, Knockback resistance, Needed material to repair
    public enum BronzeArmor implements ArmorMaterial{
        BRONZE(
                "bronze",
                15,
                new int[]{2, 6, 5, 2},
                15,
                SoundEvents.ARMOR_EQUIP_IRON,
                0.0f,
                0.0f,
                () -> Ingredient.of(AllItems.BRONZE_INGOT.get())
        ),
        TIN(
                "tin",
                15,
                new int[]{2, 6, 5, 2},
                15,
                SoundEvents.ARMOR_EQUIP_IRON,
                0.0f,
                0.0f,
                () -> Ingredient.of(AllItems.BRONZE_INGOT.get())
        );
        private final String name;
        private final int durabilityMultiplier;
        private final int[] protectionAmounts;
        private final int enchantmentValue;
        private final SoundEvent equipSound;
        private final float toughness;
        private final float knockbackResistance;
        private final Ingredient repairIngredient;

        BronzeArmor(
                String name,
                int durabilityMultiplier,
                int[] protectionAmounts,
                int enchantmentValue,
                SoundEvent equipSound,
                float toughness,
                float knockbackResistance,
                Supplier<Ingredient> repairIngredient
        ) {
            this.name = BronzeAgeTachibana.MODID + ":" + name;
            this.durabilityMultiplier = durabilityMultiplier;
            this.protectionAmounts = protectionAmounts;
            this.enchantmentValue = enchantmentValue;
            this.equipSound = equipSound;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
            this.repairIngredient = repairIngredient.get();
        }

        // Implements interface `ArmorMaterial`
        @Override
        public int getDurabilityForType(ArmorItem.Type slot) {
            // Base durability
            int[] BASE_DURABILITY = {11, 16, 15, 13};
            return BASE_DURABILITY[slot.getSlot().getIndex()] * this.durabilityMultiplier;
        }

        @Override
        public int getDefenseForType(ArmorItem.Type slot) {
            return this.protectionAmounts[slot.getSlot().getIndex()];
        }

        @Override
        public int getEnchantmentValue() {
            return this.enchantmentValue;
        }

        @Override
        public @NotNull SoundEvent getEquipSound() {
            return this.equipSound;
        }
        @Override
        public @NotNull Ingredient getRepairIngredient() {
            return this.repairIngredient;
        }

        @Override
        public @NotNull String getName() {
            return this.name;
        }

        @Override
        public float getToughness() {
            return this.toughness;
        }

        @Override
        public float getKnockbackResistance() {
            return this.knockbackResistance;
        }
    }
}
