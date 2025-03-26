package org.syju.bronze_age_tachibana;

import com.mojang.logging.LogUtils;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import org.syju.bronze_age_tachibana.data.BronzeWorldGen;
import org.syju.bronze_age_tachibana.recipes.BronzeProcessingRecipeGen;
import org.syju.bronze_age_tachibana.registry.*;

import java.util.Set;

import static org.syju.bronze_age_tachibana.registry.Registrate.REGISTRATE;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BronzeAgeTachibana.MODID)
public class BronzeAgeTachibana {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "bronze_age_tachibana";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "bronze_age_tachibana" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "bronze_age_tachibana" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

//    // Creates a new Block with the id "bronze_age_tachibana:example_block", combining the namespace and path
//    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
//    // Creates a new BlockItem with the id "bronze_age_tachibana:example_block", combining the namespace and path
//    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));
//    // Creates a new food item with the id "bronze_age_tachibana:example_id", nutrition 1 and saturation 2
//    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
//            .alwaysEat().nutrition(1).saturationMod(2f).build())));

    public BronzeAgeTachibana(final FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Register items and block
        REGISTRATE.registerEventListeners(modEventBus);
        BronzeItems.initialize();
        BronzeBlocks.initialize();
        BronzeBlockEntityTypes.initialize();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(EventPriority.LOWEST, BronzeAgeTachibana::gatherData);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
//        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

    }

    public static ResourceLocation asResource(String path){
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    private static void gatherData(GatherDataEvent event){
        if (event.includeServer()) {
            BronzeProcessingRecipeGen.registerAll(event.getGenerator(), event.getGenerator().getPackOutput());
        }
        event.getGenerator().addProvider(
                // Tell generator to run only when server data are generating
                event.includeServer(),
                (DataProvider.Factory<DatapackBuiltinEntriesProvider>) output -> new DatapackBuiltinEntriesProvider(
                        output,
                        event.getLookupProvider(),
                        // The builder containing the datapack registry objects to generate
                        new RegistrySetBuilder().add(Registries.CONFIGURED_FEATURE, BronzeWorldGen::bootstrap),
                        // Set of mod ids to generate the datapack registry objects of
                        Set.of(MODID)
                )
        );
    }



    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
//    private void addCreative(BuildCreativeModeTabContentsEvent event)
//    {
//        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
//            event.accept(EXAMPLE_BLOCK_ITEM);
//    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
//            LOGGER.info("HELLO FROM CLIENT SETUP");
//            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());


            BronzePatialModels.init();
        }
    }
}
