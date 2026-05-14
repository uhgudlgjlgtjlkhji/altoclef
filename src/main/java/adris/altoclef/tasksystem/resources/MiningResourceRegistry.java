package adris.altoclef.tasksystem.resources;

import adris.altoclef.CataloguedResource;
import adris.altoclef.TaskCatalogue;
import adris.altoclef.util.Dimension;
import adris.altoclef.util.MiningRequirement;
import adris.altoclef.util.helpers.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

/**
 * Registry for all mining-related resources (ores, blocks, plants, mobs, crops).
 * Handles the initial resource discovery and mining task registration.
 */
public class MiningResourceRegistry {
    
    /**
     * Registers all mining resources including ores, blocks, plants, mobs, and crops.
     */
    public static void registerAll() {
        registerRawResources();
        registerOres();
        registerMobDrops();
        registerCropsAndPlants();
        registerSpecialBlocks();
    }
    
    private static void registerRawResources() {
        String p = "planks";
        String s = "stick";
        
        // Wood
        TaskCatalogue.mine("log", MiningRequirement.HAND, ItemHelper.LOG, ItemHelper.LOG).anyDimension();
        TaskCatalogue.woodTasks("log", wood -> wood.log, (wood, count) -> 
            new adris.altoclef.tasks.resources.MineAndCollectTask(wood.log, count, 
                new Block[]{Block.getBlockFromItem(wood.log)}, MiningRequirement.HAND), true);
        
        TaskCatalogue.mine("dirt", MiningRequirement.HAND, 
            new Block[]{Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.DIRT_PATH}, Items.DIRT);
        
        TaskCatalogue.simple("cobblestone", Items.COBBLESTONE, adris.altoclef.tasks.resources.CollectCobblestoneTask::new).dontMineIfPresent();
        TaskCatalogue.simple("cobbled_deepslate", Items.COBBLED_DEEPSLATE, adris.altoclef.tasks.resources.CollectCobbledDeepslateTask::new).dontMineIfPresent();
        
        TaskCatalogue.mine("andesite", MiningRequirement.WOOD, Blocks.ANDESITE, Items.ANDESITE);
        TaskCatalogue.mine("granite", MiningRequirement.WOOD, Blocks.GRANITE, Items.GRANITE);
        TaskCatalogue.mine("diorite", MiningRequirement.WOOD, Blocks.DIORITE, Items.DIORITE);
        TaskCatalogue.mine("calcite", MiningRequirement.WOOD, Blocks.CALCITE, Items.CALCITE);
        TaskCatalogue.mine("tuff", MiningRequirement.WOOD, Blocks.TUFF, Items.TUFF);
        
        // Nether blocks
        TaskCatalogue.mine("netherrack", MiningRequirement.WOOD, Blocks.NETHERRACK, Items.NETHERRACK).forceDimension(Dimension.NETHER);
        TaskCatalogue.mine("magma_block", MiningRequirement.WOOD, Blocks.MAGMA_BLOCK, Items.MAGMA_BLOCK).forceDimension(Dimension.NETHER);
        TaskCatalogue.mine("blackstone", MiningRequirement.WOOD, Blocks.BLACKSTONE, Items.BLACKSTONE).forceDimension(Dimension.NETHER);
        TaskCatalogue.mine("basalt", MiningRequirement.WOOD, Blocks.BASALT, Items.BASALT).forceDimension(Dimension.NETHER);
        TaskCatalogue.mine("soul_sand", Items.SOUL_SAND).forceDimension(Dimension.NETHER);
        TaskCatalogue.mine("soul_soil", Items.SOUL_SOIL).forceDimension(Dimension.NETHER);
        TaskCatalogue.mine("glowstone_dust", Blocks.GLOWSTONE, Items.GLOWSTONE_DUST).forceDimension(Dimension.NETHER);
        TaskCatalogue.mine("ancient_debris", MiningRequirement.DIAMOND, Blocks.ANCIENT_DEBRIS, Items.ANCIENT_DEBRIS).forceDimension(Dimension.NETHER);
        TaskCatalogue.mine("gilded_blackstone", MiningRequirement.STONE, Blocks.GILDED_BLACKSTONE, Items.GILDED_BLACKSTONE).forceDimension(Dimension.NETHER);
        
        TaskCatalogue.simple("sandstone", Items.SANDSTONE, adris.altoclef.tasks.resources.CollectSandstoneTask::new).dontMineIfPresent();
        TaskCatalogue.simple("red_sandstone", Items.RED_SANDSTONE, adris.altoclef.tasks.resources.CollectRedSandstoneTask::new).dontMineIfPresent();
        TaskCatalogue.simple("coarse_dirt", Items.COARSE_DIRT, adris.altoclef.tasks.resources.CollectCoarseDirtTask::new).dontMineIfPresent();
        TaskCatalogue.simple("amethyst_block", Items.AMETHYST_BLOCK, adris.altoclef.tasks.resources.CollectAmethystBlockTask::new).dontMineIfPresent();
        TaskCatalogue.simple("dripstone_block", Items.DRIPSTONE_BLOCK, adris.altoclef.tasks.resources.CollectDripstoneBlockTask::new).dontMineIfPresent();
        TaskCatalogue.simple("flint", Items.FLINT, adris.altoclef.tasks.resources.CollectFlintTask::new);
        TaskCatalogue.simple("obsidian", Items.OBSIDIAN, adris.altoclef.tasks.resources.CollectObsidianTask::new).dontMineIfPresent();
        TaskCatalogue.simple("wool", ItemHelper.WOOL, adris.altoclef.tasks.resources.CollectWoolTask::new);
        TaskCatalogue.simple("egg", Items.EGG, adris.altoclef.tasks.resources.CollectEggsTask::new);
        
        TaskCatalogue.mine("sand", Blocks.SAND, Items.SAND);
        TaskCatalogue.mine("red_sand", Blocks.RED_SAND, Items.RED_SAND);
        TaskCatalogue.mine("gravel", Blocks.GRAVEL, Items.GRAVEL);
        TaskCatalogue.mine("clay_ball", Blocks.CLAY, Items.CLAY_BALL);
    }
    
    private static void registerOres() {
        TaskCatalogue.mine("coal", MiningRequirement.WOOD, new Block[] {Blocks.COAL_ORE, Blocks.DEEPSLATE_COAL_ORE}, Items.COAL);
        TaskCatalogue.mine("raw_iron", MiningRequirement.STONE, new Block[] {Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE}, Items.RAW_IRON);
        TaskCatalogue.mine("raw_gold", MiningRequirement.IRON, new Block[]{Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE}, Items.RAW_GOLD);
        TaskCatalogue.mine("raw_copper", MiningRequirement.STONE, new Block[]{Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE}, Items.RAW_COPPER);
        TaskCatalogue.mine("diamond", MiningRequirement.IRON, new Block[]{Blocks.DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE}, Items.DIAMOND);
        TaskCatalogue.mine("emerald", MiningRequirement.IRON, new Block[]{Blocks.EMERALD_ORE, Blocks.DEEPSLATE_EMERALD_ORE}, Items.EMERALD);
        TaskCatalogue.mine("redstone", MiningRequirement.IRON, new Block[]{Blocks.REDSTONE_ORE, Blocks.DEEPSLATE_REDSTONE_ORE}, Items.REDSTONE);
        TaskCatalogue.mine("lapis_lazuli", MiningRequirement.IRON, new Block[]{Blocks.LAPIS_ORE, Blocks.DEEPSLATE_LAPIS_ORE}, Items.LAPIS_LAZULI);
        TaskCatalogue.alias("lapis", "lapis_lazuli");
        TaskCatalogue.mine("amethyst_shard", MiningRequirement.WOOD, Blocks.AMETHYST_CLUSTER, Items.AMETHYST_SHARD);
        TaskCatalogue.mine("pointed_dripstone", MiningRequirement.WOOD, Blocks.POINTED_DRIPSTONE, Items.POINTED_DRIPSTONE);
    }
    
    private static void registerMobDrops() {
        TaskCatalogue.mob("bone", Items.BONE, net.minecraft.entity.mob.SkeletonEntity.class);
        TaskCatalogue.mob("gunpowder", Items.GUNPOWDER, net.minecraft.entity.mob.CreeperEntity.class);
        TaskCatalogue.mob("ender_pearl", Items.ENDER_PEARL, net.minecraft.entity.mob.EndermanEntity.class).anyDimension();
        TaskCatalogue.mob("spider_eye", Items.SPIDER_EYE, net.minecraft.entity.mob.SpiderEntity.class);
        TaskCatalogue.mob("leather", Items.LEATHER, net.minecraft.entity.passive.CowEntity.class);
        TaskCatalogue.mob("feather", Items.FEATHER, net.minecraft.entity.passive.ChickenEntity.class);
        TaskCatalogue.mob("rotten_flesh", Items.ROTTEN_FLESH, net.minecraft.entity.mob.ZombieEntity.class);
        TaskCatalogue.mob("rabbit_foot", Items.RABBIT_FOOT, net.minecraft.entity.passive.RabbitEntity.class);
        TaskCatalogue.mob("rabbit_hide", Items.RABBIT_HIDE, net.minecraft.entity.passive.RabbitEntity.class);
        TaskCatalogue.mob("slime_ball", Items.SLIME_BALL, net.minecraft.entity.mob.SlimeEntity.class);
        TaskCatalogue.mob("wither_skeleton_skull", Items.WITHER_SKELETON_SKULL, net.minecraft.entity.mob.WitherSkeletonEntity.class).forceDimension(Dimension.NETHER);
        TaskCatalogue.mob("ink_sac", Items.INK_SAC, net.minecraft.entity.passive.SquidEntity.class);
        TaskCatalogue.mob("glow_ink_sac", Items.GLOW_INK_SAC, net.minecraft.entity.passive.GlowSquidEntity.class);
        TaskCatalogue.mob("string", Items.STRING, net.minecraft.entity.mob.SpiderEntity.class);
    }
    
    private static void registerCropsAndPlants() {
        TaskCatalogue.mine("sugar_cane", Items.SUGAR_CANE);
        TaskCatalogue.mine("brown_mushroom", MiningRequirement.HAND, new Block[]{Blocks.BROWN_MUSHROOM, Blocks.BROWN_MUSHROOM_BLOCK}, Items.BROWN_MUSHROOM);
        TaskCatalogue.mine("red_mushroom", MiningRequirement.HAND, new Block[]{Blocks.RED_MUSHROOM, Blocks.RED_MUSHROOM_BLOCK}, Items.RED_MUSHROOM);
        TaskCatalogue.mine("mushroom", MiningRequirement.HAND, new Block[]{Blocks.BROWN_MUSHROOM, Blocks.BROWN_MUSHROOM_BLOCK, Blocks.RED_MUSHROOM, Blocks.RED_MUSHROOM_BLOCK}, Items.BROWN_MUSHROOM, Items.RED_MUSHROOM);
        TaskCatalogue.mine("melon_slice", MiningRequirement.HAND, Blocks.MELON, Items.MELON_SLICE);
        TaskCatalogue.mine("pumpkin", MiningRequirement.HAND, Blocks.PUMPKIN, Items.PUMPKIN);
        TaskCatalogue.mine("bell", MiningRequirement.WOOD, Blocks.BELL, Items.BELL);
        
        // Nether fungi
        TaskCatalogue.mine("nether_wart", MiningRequirement.HAND, Blocks.NETHER_WART, Items.NETHER_WART).forceDimension(Dimension.NETHER);
        TaskCatalogue.mine("crimson_fungus", MiningRequirement.HAND, Blocks.CRIMSON_FUNGUS, Items.CRIMSON_FUNGUS).forceDimension(Dimension.NETHER);
        TaskCatalogue.mine("warped_fungus", MiningRequirement.HAND, Blocks.WARPED_FUNGUS, Items.WARPED_FUNGUS).forceDimension(Dimension.NETHER);
        TaskCatalogue.mine("crimson_roots", MiningRequirement.HAND, Blocks.CRIMSON_ROOTS, Items.CRIMSON_ROOTS).forceDimension(Dimension.NETHER);
        TaskCatalogue.mine("warped_roots", MiningRequirement.HAND, Blocks.WARPED_ROOTS, Items.WARPED_ROOTS).forceDimension(Dimension.NETHER);
        TaskCatalogue.mine("weeping_vines", MiningRequirement.HAND, Blocks.WEEPING_VINES, Items.WEEPING_VINES).forceDimension(Dimension.NETHER);
        TaskCatalogue.mine("twisting_vines", MiningRequirement.HAND, Blocks.TWISTING_VINES, Items.TWISTING_VINES).forceDimension(Dimension.NETHER);
        TaskCatalogue.mine("nether_wart_block", MiningRequirement.HAND, Blocks.NETHER_WART_BLOCK, Items.NETHER_WART_BLOCK).forceDimension(Dimension.NETHER);
        TaskCatalogue.mine("warped_wart_block", MiningRequirement.HAND, Blocks.WARPED_WART_BLOCK, Items.WARPED_WART_BLOCK).forceDimension(Dimension.NETHER);
        TaskCatalogue.mine("shroomlight", MiningRequirement.HAND, Blocks.SHROOMLIGHT, Items.SHROOMLIGHT).forceDimension(Dimension.NETHER);
        
        TaskCatalogue.simple("blaze_rod", Items.BLAZE_ROD, adris.altoclef.tasks.resources.CollectBlazeRodsTask::new).forceDimension(Dimension.NETHER);
        TaskCatalogue.mine("quartz", MiningRequirement.WOOD, Blocks.NETHER_QUARTZ_ORE, Items.QUARTZ).forceDimension(Dimension.NETHER);
        TaskCatalogue.simple("cocoa_beans", Items.COCOA_BEANS, adris.altoclef.tasks.resources.CollectCocoaBeansTask::new);
        
        // Shearable plants
        TaskCatalogue.shear("cobweb", Blocks.COBWEB, Items.COBWEB).dontMineIfPresent();
        TaskCatalogue.colorfulTasks("wool", color -> color.wool, (color, count) -> new adris.altoclef.tasks.resources.CollectWoolTask(color.color, count));
        TaskCatalogue.shear("leaves", ItemHelper.itemsToBlocks(ItemHelper.LEAVES), ItemHelper.LEAVES).dontMineIfPresent();
        
        for (CataloguedResource resource : TaskCatalogue.woodTasks(
                "leaves",
                woodItems -> woodItems.leaves,
                (woodItems, count) -> {
                    if (woodItems.isNetherWood()) {
                        return new adris.altoclef.tasks.resources.MineAndCollectTask(woodItems.leaves, count, 
                            new Block[]{Block.getBlockFromItem(woodItems.leaves)}, MiningRequirement.HAND).forceDimension(Dimension.NETHER);
                    } else {
                        return new adris.altoclef.tasks.resources.ShearAndCollectBlockTask(woodItems.leaves, count, Block.getBlockFromItem(woodItems.leaves));
                    }
                })
        ) {
            resource.dontMineIfPresent();
        }
        
        TaskCatalogue.mine("bamboo", Blocks.BAMBOO, Items.BAMBOO);
        TaskCatalogue.shear("vine", Blocks.VINE, Items.VINE).dontMineIfPresent();
        TaskCatalogue.shear("grass", Blocks.GRASS, Items.GRASS).dontMineIfPresent();
        TaskCatalogue.shear("lily_pad", Blocks.LILY_PAD, Items.LILY_PAD).dontMineIfPresent();
        TaskCatalogue.shear("tall_grass", Blocks.TALL_GRASS, Items.TALL_GRASS).dontMineIfPresent();
        TaskCatalogue.shear("fern", Blocks.FERN, Items.FERN).dontMineIfPresent();
        TaskCatalogue.shear("large_fern", Blocks.LARGE_FERN, Items.LARGE_FERN).dontMineIfPresent();
        TaskCatalogue.shear("dead_bush", Blocks.DEAD_BUSH, Items.DEAD_BUSH).dontMineIfPresent();
        TaskCatalogue.shear("glow_lichen", Blocks.GLOW_LICHEN, Items.GLOW_LICHEN).dontMineIfPresent();
        
        // Flowers
        TaskCatalogue.simple("flower", ItemHelper.FLOWER, adris.altoclef.tasks.resources.CollectFlowerTask::new);
        TaskCatalogue.mine("allium", Items.ALLIUM);
        TaskCatalogue.mine("azure_bluet", Items.AZURE_BLUET);
        TaskCatalogue.mine("blue_orchid", Items.BLUE_ORCHID);
        TaskCatalogue.mine("cactus", Items.CACTUS);
        TaskCatalogue.mine("cornflower", Items.CORNFLOWER);
        TaskCatalogue.mine("dandelion", Items.DANDELION);
        TaskCatalogue.mine("lilac", Items.LILAC);
        TaskCatalogue.mine("lily_of_the_valley", Items.LILY_OF_THE_VALLEY);
        TaskCatalogue.mine("orange_tulip", Items.ORANGE_TULIP);
        TaskCatalogue.mine("oxeye_daisy", Items.OXEYE_DAISY);
        TaskCatalogue.mine("pink_tulip", Items.PINK_TULIP);
        TaskCatalogue.mine("poppy", Items.POPPY);
        TaskCatalogue.mine("peony", Items.PEONY);
        TaskCatalogue.mine("red_tulip", Items.RED_TULIP);
        TaskCatalogue.mine("rose_bush", Items.ROSE_BUSH);
        TaskCatalogue.mine("sunflower", Items.SUNFLOWER);
        TaskCatalogue.mine("white_tulip", Items.WHITE_TULIP);
        
        // Crops
        TaskCatalogue.simple("wheat", Items.WHEAT, adris.altoclef.tasks.resources.CollectWheatTask::new);
        TaskCatalogue.crop("carrot", Items.CARROT, Blocks.CARROTS, Items.CARROT);
        TaskCatalogue.crop("potato", Items.POTATO, Blocks.POTATOES, Items.POTATO);
        TaskCatalogue.crop("poisonous_potato", Items.POISONOUS_POTATO, Blocks.POTATOES, Items.POTATO);
        TaskCatalogue.crop("beetroot", Items.BEETROOT, Blocks.BEETROOTS, Items.BEETROOT_SEEDS);
        TaskCatalogue.simple("wheat_seeds", Items.WHEAT_SEEDS, adris.altoclef.tasks.resources.CollectWheatSeedsTask::new);
        TaskCatalogue.crop("beetroot_seeds", Items.BEETROOT_SEEDS, Blocks.BEETROOTS, Items.BEETROOT_SEEDS);
    }
    
    private static void registerSpecialBlocks() {
        TaskCatalogue.simple("hay_block", Items.HAY_BLOCK, adris.altoclef.tasks.resources.CollectHayBlockTask::new).dontMineIfPresent();
        TaskCatalogue.simple("nether_bricks", Items.NETHER_BRICKS, adris.altoclef.tasks.resources.CollectNetherBricksTask::new).dontMineIfPresent();
    }
}
