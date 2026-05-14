package adris.altoclef.tasksystem.resources;

import adris.altoclef.TaskCatalogue;
import adris.altoclef.util.Dimension;
import adris.altoclef.util.MiningRequirement;
import adris.altoclef.util.helpers.ItemHelper;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;

/**
 * Registry for crafting materials - smelting, block crafting, and material processing.
 */
public class CraftingMaterialRegistry {
    
    public static void registerAll() {
        registerMaterials();
        registerSmelting();
        registerBlockCrafting();
        registerComplexRecipes();
    }
    
    private static void registerMaterials() {
        String p = "planks";
        String s = "stick";
        
        TaskCatalogue.simple("planks", ItemHelper.PLANKS, adris.altoclef.tasks.resources.CollectPlanksTask::new).dontMineIfPresent();
        
        for (adris.altoclef.CataloguedResource woodCatalogue : TaskCatalogue.woodTasks("planks", wood -> wood.planks, (wood, count) -> {
            adris.altoclef.tasks.resources.CollectPlanksTask result = new adris.altoclef.tasks.resources.CollectPlanksTask(wood.planks, count);
            if (wood.isNetherWood()) {
                result.logsInNether();
            }
            return result;
        }, true)) {
            woodCatalogue.dontMineIfPresent();
        }
        
        TaskCatalogue.simple("stick", Items.STICK, adris.altoclef.tasks.resources.CollectSticksTask::new);
        TaskCatalogue.simple("gold_ingot", Items.GOLD_INGOT, adris.altoclef.tasks.resources.CollectGoldIngotTask::new).anyDimension();
        TaskCatalogue.simple("gold_nugget", Items.GOLD_NUGGET, adris.altoclef.tasks.resources.CollectGoldNuggetsTask::new);
        TaskCatalogue.simple("magma_cream", Items.MAGMA_CREAM, adris.altoclef.tasks.resources.CollectMagmaCreamTask::new);
        TaskCatalogue.simple("honeycomb", Items.HONEYCOMB, adris.altoclef.tasks.resources.CollectHoneycombTask::new);
    }
    
    private static void registerSmelting() {
        TaskCatalogue.smelt("stone", Items.STONE, "cobblestone").dontMineIfPresent();
        TaskCatalogue.smelt("deepslate", Items.DEEPSLATE, "cobbled_deepslate").dontMineIfPresent();
        TaskCatalogue.smelt("smooth_stone", Items.SMOOTH_STONE, "stone");
        TaskCatalogue.smelt("smooth_quartz", Items.SMOOTH_QUARTZ, "quartz_block");
        TaskCatalogue.smelt("smooth_basalt", Items.SMOOTH_BASALT, "basalt");
        TaskCatalogue.smelt("glass", Items.GLASS, "sand").dontMineIfPresent();
        TaskCatalogue.smelt("iron_ingot", Items.IRON_INGOT, "raw_iron", Items.IRON_ORE);
        TaskCatalogue.smelt("copper_ingot", Items.COPPER_INGOT, "raw_copper", Items.COPPER_ORE);
        TaskCatalogue.smelt("charcoal", Items.CHARCOAL, "log");
        TaskCatalogue.smelt("brick", Items.BRICK, "clay_ball");
        TaskCatalogue.smelt("nether_brick", Items.NETHER_BRICK, "netherrack");
        TaskCatalogue.smelt("green_dye", Items.GREEN_DYE, "cactus");
        TaskCatalogue.smelt("netherite_scrap", Items.NETHERITE_SCRAP, "ancient_debris");
        
        TaskCatalogue.smelt("cracked_stone_bricks", Items.CRACKED_STONE_BRICKS, "stone_bricks");
        TaskCatalogue.smelt("cracked_nether_bricks", Items.CRACKED_NETHER_BRICKS, "nether_bricks");
        TaskCatalogue.smelt("cracked_polished_blackstone_bricks", Items.CRACKED_POLISHED_BLACKSTONE_BRICKS, "polished_blackstone_bricks");
        TaskCatalogue.smelt("cracked_deepslate_bricks", Items.CRACKED_DEEPSLATE_BRICKS, "deepslate_bricks");
        TaskCatalogue.smelt("cracked_deepslate_tiles", Items.CRACKED_DEEPSLATE_TILES, "deepslate_tiles");
        TaskCatalogue.smelt("smooth_sandstone", Items.SMOOTH_SANDSTONE, "sandstone");
        TaskCatalogue.smelt("smooth_red_sandstone", Items.SMOOTH_RED_SANDSTONE, "red_sandstone");
    }
    
    private static void registerBlockCrafting() {
        // Metal blocks
        TaskCatalogue.shapedRecipe3x3Block("iron_block", Items.IRON_BLOCK, "iron_ingot");
        TaskCatalogue.shapedRecipe3x3Block("gold_block", Items.GOLD_BLOCK, "gold_ingot");
        TaskCatalogue.shapedRecipe3x3Block("copper_block", Items.COPPER_BLOCK, "copper_ingot");
        TaskCatalogue.shapedRecipe3x3Block("raw_iron_block", Items.RAW_IRON_BLOCK, "raw_iron");
        TaskCatalogue.shapedRecipe3x3Block("raw_gold_block", Items.RAW_GOLD_BLOCK, "raw_gold");
        TaskCatalogue.shapedRecipe3x3Block("raw_copper_block", Items.RAW_COPPER_BLOCK, "raw_copper");
        TaskCatalogue.shapedRecipe3x3Block("diamond_block", Items.DIAMOND_BLOCK, "diamond");
        TaskCatalogue.shapedRecipe3x3Block("redstone_block", Items.REDSTONE_BLOCK, "redstone");
        TaskCatalogue.shapedRecipe3x3Block("coal_block", Items.COAL_BLOCK, "coal");
        TaskCatalogue.shapedRecipe3x3Block("emerald_block", Items.EMERALD_BLOCK, "emerald");
        TaskCatalogue.shapedRecipe3x3Block("lapis_block", Items.LAPIS_BLOCK, "lapis_lazuli");
        TaskCatalogue.shapedRecipe3x3Block("slime_block", Items.SLIME_BLOCK, "slime_ball");
        TaskCatalogue.shapedRecipe3x3Block("melon", Items.MELON, "melon_slice").dontMineIfPresent();
        
        TaskCatalogue.shapedRecipe2x2Block("glowstone", Items.GLOWSTONE, "glowstone_dust").dontMineIfPresent();
        TaskCatalogue.shapedRecipe2x2Block("clay", Items.CLAY, "clay_ball").dontMineIfPresent();
        
        // Polished blocks
        TaskCatalogue.shapedRecipe2x2Block("polished_andesite", Items.POLISHED_ANDESITE, 4, "andesite");
        TaskCatalogue.shapedRecipe2x2Block("polished_diorite", Items.POLISHED_DIORITE, 4, "diorite");
        TaskCatalogue.shapedRecipe2x2Block("polished_granite", Items.POLISHED_GRANITE, 4, "granite");
        TaskCatalogue.shapedRecipe2x2Block("quartz_block", Items.QUARTZ_BLOCK, "quartz");
        TaskCatalogue.shapedRecipe2x2Block("polished_blackstone", Items.POLISHED_BLACKSTONE, 4, "blackstone");
        TaskCatalogue.shapedRecipe2x2Block("polished_blackstone_bricks", Items.POLISHED_BLACKSTONE_BRICKS, 4, "polished_blackstone");
        TaskCatalogue.shapedRecipe2x2Block("polished_basalt", Items.POLISHED_BASALT, 4, "basalt");
        TaskCatalogue.shapedRecipe2x2Block("polished_deepslate", Items.POLISHED_DEEPSLATE, 4, "cobbled_deepslate");
        TaskCatalogue.shapedRecipe2x2Block("deepslate_bricks", Items.DEEPSLATE_BRICKS, 4, "polished_deepslate");
        TaskCatalogue.shapedRecipe2x2Block("deepslate_tiles", Items.DEEPSLATE_TILES, 4, "deepslate_bricks");
        TaskCatalogue.shapedRecipe2x2Block("cut_copper", Items.CUT_COPPER, 4, "copper_block");
        TaskCatalogue.shapedRecipe2x2Block("cut_sandstone", Items.CUT_SANDSTONE, 4, "sandstone");
        TaskCatalogue.shapedRecipe2x2Block("cut_red_sandstone", Items.CUT_RED_SANDSTONE, 4, "red_sandstone");
        TaskCatalogue.shapedRecipe2x2Block("quartz_bricks", Items.QUARTZ_BRICKS, 4, "quartz_block");
        TaskCatalogue.shapedRecipe2x2Block("stone_bricks", Items.STONE_BRICKS, 4, "stone");
        TaskCatalogue.shapedRecipe2x2Block("nether_bricks", Items.NETHER_BRICKS, 4, "nether_wart");
        
        TaskCatalogue.shapedRecipe2x2("quartz_pillar", Items.QUARTZ_PILLAR, 4, "quartz_block", "o", "quartz_block", "o");
    }
    
    private static void registerComplexRecipes() {
        String p = "planks";
        String s = "stick";
        String o = null;
        
        // Netherite
        TaskCatalogue.shapedRecipe3x3("netherite_ingot", Items.NETHERITE_INGOT, 1, 
            "netherite_scrap", "netherite_scrap", "netherite_scrap", "netherite_scrap", 
            "gold_ingot", "gold_ingot", "gold_ingot", "gold_ingot", o);
        
        // Glistering melon
        TaskCatalogue.shapedRecipe3x3("glistering_melon_slice", Items.GLISTERING_MELON_SLICE, 1, 
            "gold_nugget", "gold_nugget", "gold_nugget", "gold_nugget", 
            "melon_slice", "gold_nugget", "gold_nugget", "gold_nugget", "gold_nugget");
        
        // Basic items
        TaskCatalogue.shapedRecipe2x2("sugar", Items.SUGAR, 1, "sugar_cane", o, o, o);
        TaskCatalogue.shapedRecipe2x2("bone_meal", Items.BONE_MEAL, 3, "bone", o, o, o);
        TaskCatalogue.shapedRecipe2x2("melon_seeds", Items.MELON_SEEDS, 1, "melon_slice", o, o, o);
        TaskCatalogue.shapedRecipe2x2("paper", Items.PAPER, 3, "sugar_cane", "sugar_cane", "sugar_cane", o, o, o, o, o, o);
        TaskCatalogue.shapedRecipe2x2("book", Items.BOOK, 1, "paper", "paper", "paper", "leather");
        TaskCatalogue.shapedRecipe2x2("writable_book", Items.WRITABLE_BOOK, 1, "book", "ink_sac", o, "feather");
        TaskCatalogue.alias("book_and_quill", "writable_book");
        
        // Tools and equipment
        TaskCatalogue.shapedRecipe3x3("bowl", Items.BOWL, 4, p, o, p, o, p, o, o, o, o);
        TaskCatalogue.shapedRecipe2x2("blaze_powder", Items.BLAZE_POWDER, 2, "blaze_rod", o, o, o);
        TaskCatalogue.shapedRecipe2x2("ender_eye", Items.ENDER_EYE, 1, "blaze_powder", "ender_pearl", o, o);
        TaskCatalogue.alias("eye_of_ender", "ender_eye");
        TaskCatalogue.shapedRecipe2x2("fermented_spider_eye", Items.FERMENTED_SPIDER_EYE, 1, "brown_mushroom", "sugar", o, "spider_eye");
        TaskCatalogue.shapedRecipe3x3("fire_charge", Items.FIRE_CHARGE, 3, o, "blaze_powder", o, o, "coal", o, o, "gunpowder", o);
        TaskCatalogue.shapedRecipe2x2("flower_banner_pattern", Items.FLOWER_BANNER_PATTERN, 1, "paper", "oxeye_daisy", o, o);
        
        // Honey items
        String h = "honeycomb";
        TaskCatalogue.shapedRecipe2x2Block("honeycomb_block", Items.HONEYCOMB_BLOCK, h);
        TaskCatalogue.shapedRecipe2x2("candle", Items.CANDLE, 1, "string", o, h, o);
        TaskCatalogue.shapedRecipe3x3("beehive", Items.BEEHIVE, 1, p, p, p, h, h, h, p, p, p);
    }
}
