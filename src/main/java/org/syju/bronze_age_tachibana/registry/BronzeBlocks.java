package org.syju.bronze_age_tachibana.registry;

import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockModel;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraftforge.common.Tags;
import org.syju.bronze_age_tachibana.BronzeSpriteShifts;
import org.syju.bronze_age_tachibana.content.block.BronzeCogWheelBlock;
import org.syju.bronze_age_tachibana.content.block.BronzeCogwheelBlockItem;

import static com.simibubi.create.foundation.data.BlockStateGen.simpleCubeAll;
import static com.simibubi.create.foundation.data.TagGen.*;
import static org.syju.bronze_age_tachibana.registry.Registrate.REGISTRATE;

public class BronzeBlocks {
    // Base block
    public static final BlockEntry<Block> TIN_BLOCK = REGISTRATE.block("tin_block", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_YELLOW)
                    .requiresCorrectToolForDrops())
            .transform(pickaxeOnly())
            .blockstate(simpleCubeAll("tin_block"))
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .tag(Tags.Blocks.STORAGE_BLOCKS)
            .tag(BlockTags.BEACON_BASE_BLOCKS)
            .transform(tagBlockAndItem("storage_blocks/tin"))
            .tag(Tags.Items.STORAGE_BLOCKS)
            .build()
            .lang("Block of Tin")
            .register();
    public static final BlockEntry<Block> BRONZE_BLOCK = REGISTRATE.block("bronze_block", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_YELLOW)
                    .requiresCorrectToolForDrops())
            .transform(pickaxeOnly())
            .blockstate(simpleCubeAll("bronze_block"))
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .tag(Tags.Blocks.STORAGE_BLOCKS)
            .tag(BlockTags.BEACON_BASE_BLOCKS)
            .transform(tagBlockAndItem("storage_blocks/bronze"))
            .tag(Tags.Items.STORAGE_BLOCKS)
            .build()
            .lang("Block of Bronze")
            .register();

    // Ores
    public static final BlockEntry<Block> TIN_ORE = REGISTRATE.block("tin_ore", Block::new)
            .initialProperties(() -> Blocks.IRON_ORE)
            .properties(p -> p.mapColor(MapColor.METAL)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE))
            .transform(pickaxeOnly())
            .loot((lt, b) -> lt.add(b,
                    RegistrateBlockLootTables.createSilkTouchDispatchTable(b,
                            lt.applyExplosionDecay(b, LootItem.lootTableItem(BronzeItems.RAW_TIN.get())
                                    .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))))))
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .tag(Tags.Blocks.ORES)
            .transform(tagBlockAndItem("ores/tin", "ores_in_ground/stone"))
            .tag(Tags.Items.ORES)
            .build()
            .register();

    public static final BlockEntry<Block> DEEPSLATE_TIN_ORE = REGISTRATE.block("deepslate_tin_ore", Block::new)
            .initialProperties(() -> Blocks.DEEPSLATE_IRON_ORE)
            .properties(p -> p.mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE))
            .transform(pickaxeOnly())
            .loot((lt, b) -> lt.add(b,
                    RegistrateBlockLootTables.createSilkTouchDispatchTable(b,
                            lt.applyExplosionDecay(b, LootItem.lootTableItem(BronzeItems.RAW_TIN.get())
                                    .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))))))
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .tag(Tags.Blocks.ORES)
            .transform(tagBlockAndItem("ores/tin", "ores_in_ground/deepslate"))
            .tag(Tags.Items.ORES)
            .build()
            .register();

    // Raw blocks
    public static final BlockEntry<Block> RAW_TIN_BLOCK = REGISTRATE.block("raw_tin_block", Block::new)
            .initialProperties(() -> Blocks.RAW_IRON_BLOCK)
            .properties(p -> p.mapColor(MapColor.GLOW_LICHEN)
                    .requiresCorrectToolForDrops())
            .transform(pickaxeOnly())
            .tag(Tags.Blocks.STORAGE_BLOCKS)
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .lang("Block of Raw Tin")
            .transform(tagBlockAndItem("storage_blocks/raw_tin"))
            .tag(Tags.Items.STORAGE_BLOCKS)
            .build()
            .register();

    // Casing
    public static final BlockEntry<CasingBlock> BRONZE_CASING = REGISTRATE.block("bronze_casing", CasingBlock::new)
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_ORANGE))
            .transform(BuilderTransformers.casing(() -> BronzeSpriteShifts.BRONZE_CASING))
            .register();

    // Cogwheel
    public static final BlockEntry<BronzeCogWheelBlock> BRONZE_COGWHEEL = REGISTRATE
            .block("bronze_cogwheel", BronzeCogWheelBlock::small)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.sound(SoundType.METAL)
                    .mapColor(MapColor.METAL))
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .item(BronzeCogwheelBlockItem::new)
            .build()
            .register();

    public static final BlockEntry<BronzeCogWheelBlock> LARGE_BRONZE_COGWHEEL =
            REGISTRATE.block("large_bronze_cogwheel", BronzeCogWheelBlock::large)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.sound(SoundType.METAL)
                            .mapColor(MapColor.METAL))
                    .transform(pickaxeOnly())
                    .blockstate(BlockStateGen.axisBlockProvider(false))
                    .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
                    .item(BronzeCogwheelBlockItem::new)
                    .build()
                    .register();

    // Encased cogwheel
//    public static final BlockEntry<EncasedCogwheelBlock> BRONZE_COGWHEEL = REGISTRATE
//            .block("bronze_cogwheel", p -> new EncasedCogwheelBlock(p, false, AllBlocks.BRONZE_BLOCK))
//            .properties(p -> p.sound(SoundType.METAL).mapColor(MapColor.METAL))
//            .item()
//            .build()
//            .register();
//
//
//    public static final BlockEntry<EncasedCogwheelBlock> LARGE_BRONZE_COGWHEEL = REGISTRATE
//            .block("large_bronze_cogwheel", p -> new EncasedCogwheelBlock(p, true, AllBlocks.BRONZE_BLOCK))
//            .properties(p -> p.sound(SoundType.METAL).mapColor(MapColor.METAL))
//            .item()
//            .build()
//            .register();


    public static void initialize() { }
}
