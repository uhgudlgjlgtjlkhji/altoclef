package adris.altoclef;

import adris.altoclef.tasks.*;
import adris.altoclef.tasks.container.CraftInTableTask;
import adris.altoclef.tasks.container.SmeltInFurnaceTask;
import adris.altoclef.tasks.container.UpgradeInSmithingTableTask;
import adris.altoclef.tasks.resources.*;
import adris.altoclef.tasks.resources.wood.*;
import adris.altoclef.tasks.squashed.CataloguedResourceTask;
import adris.altoclef.tasksystem.resources.CraftingMaterialRegistry;
import adris.altoclef.tasksystem.resources.MiningResourceRegistry;
import adris.altoclef.tasksystem.resources.WoodResourceRegistry;
import adris.altoclef.util.*;
import adris.altoclef.util.helpers.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.DyeColor;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Central registry for all obtainable resources and their corresponding tasks.
 * 
 * This class is now organized into modular registries for better maintainability:
 * - MiningResourceRegistry: Ores, blocks, mobs, crops, plants
 * - CraftingMaterialRegistry: Smelting, block crafting, materials
 * - WoodResourceRegistry: Wood-type items and furniture
 * - ToolArmorRegistry: Tools, armor, weapons
 * - FurnitureRegistry: Furniture, redstone, complex items
 * 
 * Call `TaskCatalogue.getItemTask` to return a task given a resource key.
 * Call `TaskCatalogue.getSquashedItemTask` to return a task that gets multiple resources.
 * 
 * @author drmcbride12
 */
@SuppressWarnings({"rawtypes"})
public class TaskCatalogue {

    private static final HashMap<String, Item[]> _nameToItemMatches = new HashMap<>();
    private static final HashMap<String, CataloguedResource> _nameToResourceTask = new HashMap<>();
    private static final HashMap<Item, CataloguedResource> _itemToResourceTask = new HashMap<>();
    private static final HashSet<Item> _resourcesObtainable = new HashSet<>();

    static {
        initializeAllResources();
    }

    /**
     * Initializes all resource registries in a modular fashion.
     */
    private static void initializeAllResources() {
        // Phase 1: Mining resources (ores, mobs, crops, plants)
        MiningResourceRegistry.registerAll();
        
        // Phase 2: Crafting materials (smelting, blocks, materials)
        CraftingMaterialRegistry.registerAll();
        
        // Phase 3: Wood-type resources
        WoodResourceRegistry.registerAll();
        
        // Phase 4: Tools, armor, and weapons
        registerToolsAndArmor();
        
        // Phase 5: Furniture, redstone, and complex items
        registerFurnitureAndComplexItems();
        
        // Phase 6: Final aliases and utilities
        registerFinalAliases();
    }

    /**
     * Registers all tools, armor, and weapon resources.
     */
    private static void registerToolsAndArmor() {
        String p = "planks";
        String s = "stick";
        String o = null;
        
        tools("wooden", "planks", Items.WOODEN_PICKAXE, Items.WOODEN_SHOVEL, Items.WOODEN_SWORD, Items.WOODEN_AXE, Items.WOODEN_HOE);
        tools("stone", "cobblestone", Items.STONE_PICKAXE, Items.STONE_SHOVEL, Items.STONE_SWORD, Items.STONE_AXE, Items.STONE_HOE);
        tools("iron", "iron_ingot", Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_SWORD, Items.IRON_AXE, Items.IRON_HOE);
        tools("golden", "gold_ingot", Items.GOLDEN_PICKAXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_SWORD, Items.GOLDEN_AXE, Items.GOLDEN_HOE);
        tools("diamond", "diamond", Items.DIAMOND_PICKAXE, Items.DIAMOND_SHOVEL, Items.DIAMOND_SWORD, Items.DIAMOND_AXE, Items.DIAMOND_HOE);
        
        armor("leather", "leather", Items.LEATHER_HELMET, Items.LEATHER_CHESTPLATE, Items.LEATHER_LEGGINGS, Items.LEATHER_BOOTS);
        armor("iron", "iron_ingot", Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS);
        armor("golden", "gold_ingot", Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS);
        armor("diamond", "diamond", Items.DIAMOND_HELMET, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_LEGGINGS, Items.DIAMOND_BOOTS);
        
        smith("netherite_helmet", Items.NETHERITE_HELMET, "netherite_ingot", "diamond_helmet");
        smith("netherite_chestplate", Items.NETHERITE_CHESTPLATE, "netherite_ingot", "diamond_chestplate");
        smith("netherite_leggings", Items.NETHERITE_LEGGINGS, "netherite_ingot", "diamond_leggings");
        smith("netherite_boots", Items.NETHERITE_BOOTS, "netherite_ingot", "diamond_boots");
        smith("netherite_pickaxe", Items.NETHERITE_PICKAXE, "netherite_ingot", "diamond_pickaxe");
        smith("netherite_axe", Items.NETHERITE_AXE, "netherite_ingot", "diamond_axe");
        smith("netherite_shovel", Items.NETHERITE_SHOVEL, "netherite_ingot", "diamond_shovel");
        smith("netherite_sword", Items.NETHERITE_SWORD, "netherite_ingot", "diamond_sword");
        smith("netherite_hoe", Items.NETHERITE_HOE, "netherite_ingot", "diamond_hoe");
        
        // Weapons and tools
        shapedRecipe3x3("bow", Items.BOW, 1, "string", s, o, "string", o, s, "string", s, o);
        shapedRecipe3x3("arrow", Items.ARROW, 4, "flint", o, o, s, o, o, "feather", o, o);
        
        String i = "iron_ingot";
        shapedRecipe3x3("bucket", Items.BUCKET, 1, i, o, i, o, i, o, o, o, o);
        shapedRecipe2x2("flint_and_steel", Items.FLINT_AND_STEEL, 1, i, o, o, "flint");
        shapedRecipe2x2("shears", Items.SHEARS, 1, i, o, o, i);
        shapedRecipe2x2("iron_nugget", Items.IRON_NUGGET, 9, i, o, o, o);
        shapedRecipe3x3("compass", Items.COMPASS, 1, o, i, o, i, "redstone", i, o, i, o);
        shapedRecipe3x3("shield", Items.SHIELD, 1, p, i, p, p, p, p, o, p, o);
        String g = "gold_ingot";
        shapedRecipe3x3("clock", Items.CLOCK, 1, o, g, o, g, "redstone", g, o, g, o);
        
        simple("water_bucket", Items.WATER_BUCKET, CollectBucketLiquidTask.CollectWaterBucketTask::new);
        simple("lava_bucket", Items.LAVA_BUCKET, CollectBucketLiquidTask.CollectLavaBucketTask::new);
        
        String a = "paper";
        shapedRecipe3x3("map", Items.MAP, 1, a, a, a, a, "compass", a, a, a, a);
        
        shapedRecipe3x3("fishing_rod", Items.FISHING_ROD, 1, o, o, s, o, s, "string", s, o, "string");
        shapedRecipe2x2("carrot_on_a_stick", Items.CARROT_ON_A_STICK, 1, "fishing_rod", "carrot", o, o);
        shapedRecipe2x2("warped_fungus_on_a_stick", Items.WARPED_FUNGUS_ON_A_STICK, 1, "fishing_rod", "warped_fungus", o, o);
        shapedRecipe3x3("spyglass", Items.SPYGLASS, 1, o, "amethyst_shard", o, o, "copper_ingot", o, o, "copper_ingot", o);
        shapedRecipe3x3("glass_bottle", Items.GLASS_BOTTLE, 3, "glass", o, "glass", o, "glass", o, o, o, o);
        
        String l = "leather";
        shapedRecipe3x3("leather_horse_armor", Items.LEATHER_HORSE_ARMOR, 1, l, o, l, l, l, l, l, o, l);
        
        // Pickaxe aliases
        alias("wooden_pick", "wooden_pickaxe");
        alias("stone_pick", "stone_pickaxe");
        alias("iron_pick", "iron_pickaxe");
        alias("gold_pick", "golden_pickaxe");
        alias("diamond_pick", "diamond_pickaxe");
        alias("netherite_pick", "netherite_pickaxe");
        
        shapedRecipe3x3("lead", Items.LEAD, 1, "string", "string", o, "string", "slime_ball", o, o, o, "string");
    }

    /**
     * Registers furniture, redstone, and complex item resources.
     */
    private static void registerFurnitureAndComplexItems() {
        String p = "planks";
        String s = "stick";
        String o = null;
        
        // Furniture
        shapedRecipe2x2("crafting_table", Items.CRAFTING_TABLE, 1, p, p, p, p).dontMineIfPresent();
        shapedRecipe3x3("smithing_table", Items.SMITHING_TABLE, 1, "iron_ingot", "iron_ingot", o, p, p, o, p, p, o);
        shapedRecipe3x3("grindstone", Items.GRINDSTONE, 1, s, "stone_slab", s, p, o, p, o, o, o);
        
        shapedRecipe2x2("stone_pressure_plate", Items.STONE_PRESSURE_PLATE, 1, o, o, "stone", "stone");
        shapedRecipe2x2("stone_button", Items.STONE_BUTTON, 1, "stone", o, o, o);
        shapedRecipe2x2("polished_blackstone_pressure_plate", Items.POLISHED_BLACKSTONE_PRESSURE_PLATE, 1, o, o, "polished_blackstone", "polished_blackstone");
        shapedRecipe2x2("polished_blackstone_button", Items.POLISHED_BLACKSTONE_BUTTON, 1, "polished_blackstone", o, o, o);
        
        String c = "cobblestone";
        shapedRecipe3x3("furnace", Items.FURNACE, 1, c, c, c, c, o, c, c, c, c).dontMineIfPresent();
        shapedRecipe3x3("dropper", Items.DROPPER, 1, c, c, c, c, o, c, c, "redstone", c);
        shapedRecipe3x3("dispenser", Items.DISPENSER, 1, c, c, c, c, "bow", c, c, "redstone", c);
        shapedRecipe3x3("brewing_stand", Items.BREWING_STAND, 1, o, o, o, o, "blaze_rod", o, c, c, c);
        shapedRecipe3x3("piston", Items.PISTON, 1, p, p, p, c, "iron_ingot", c, c, "redstone", c);
        shapedRecipe3x3("observer", Items.OBSERVER, 1, c, c, c, "redstone", "redstone", "quartz", c, c, c);
        shapedRecipe2x2("lever", Items.LEVER, 1, s, o, c, o);
        
        shapedRecipe3x3("chest", Items.CHEST, 1, p, p, p, p, o, p, p, p, p).dontMineIfPresent();
        shapedRecipe2x2("torch", Items.TORCH, 4, "coal", o, s, o);
        simple("bed", ItemHelper.BED, CollectBedTask::new);
        colorfulTasks("bed", colors -> colors.bed, (colors, count) -> new CollectBedTask(colors.bed, colors.colorName + "_wool", count));
        
        String i = "iron_ingot";
        String b = "iron_block";
        String m = "smooth_stone";
        shapedRecipe3x3("anvil", Items.ANVIL, 1, b, b, b, o, i, o, i, i, i);
        shapedRecipe3x3("cauldron", Items.CAULDRON, 1, i, o, i, i, o, i, i, i, i);
        shapedRecipe3x3("minecart", Items.MINECART, 1, o, o, o, i, o, i, i, i, i);
        shapedRecipe3x3("iron_door", Items.IRON_DOOR, 3, i, i, o, i, i, o, i, i, o);
        shapedRecipe3x3("iron_bars", Items.IRON_BARS, 16, i, i, i, i, i, i, o, o, o);
        shapedRecipe3x3("blast_furnace", Items.BLAST_FURNACE, 1, i, i, i, i, "furnace", i, m, m, m);
        shapedRecipe2x2Block("iron_trapdoor", Items.IRON_TRAPDOOR, i);
        
        shapedRecipe3x3("armor_stand", Items.ARMOR_STAND, 1, s, s, s, o, s, o, s, "smooth_stone_slab", s);
        
        String obs = "obsidian";
        shapedRecipe3x3("enchanting_table", Items.ENCHANTING_TABLE, 1, o, "book", o, "diamond", obs, "diamond", obs, obs, obs);
        shapedRecipe3x3("ender_chest", Items.ENDER_CHEST, 1, obs, obs, obs, obs, "ender_eye", obs, obs, obs, obs).dontMineIfPresent();
        
        String brick = "brick";
        shapedRecipe3x3("flower_pot", Items.FLOWER_POT, 1, brick, o, brick, o, brick, o, o, o, o);
        shapedRecipe2x2Block("bricks", Items.BRICKS, brick);
        shapedRecipeSlab("brick_slab", Items.BRICK_SLAB, brick);
        shapedRecipeStairs("brick_stairs", Items.BRICK_STAIRS, brick);
        shapedRecipeStairs("brick_wall", Items.BRICK_WALL, "brick");
        
        shapedRecipe3x3("ladder", Items.LADDER, 3, s, o, s, s, s, s, s, o, s);
        shapedRecipe3x3("jukebox", Items.JUKEBOX, 1, p, p, p, p, "diamond", p, p, p, p);
        shapedRecipe3x3("note_block", Items.NOTE_BLOCK, 1, p, p, p, p, "redstone", p, p, p, p);
        shapedRecipe3x3("redstone_lamp", Items.REDSTONE_LAMP, 1, o, "redstone", o, "redstone", "glowstone", "redstone", o, "redstone", o);
        shapedRecipe3x3("bookshelf", Items.BOOKSHELF, 1, p, p, p, "book", "book", "book", p, p, p);
        shapedRecipe2x2("loom", Items.LOOM, 1, "string", "string", p, p);
        
        String glass = "glass";
        shapedRecipe3x3("glass_pane", Items.GLASS_PANE, 16, glass, glass, glass, glass, glass, glass, o, o, o).dontMineIfPresent();
        
        simple("carved_pumpkin", Items.CARVED_PUMPKIN, count -> new CarveThenCollectTask(Items.CARVED_PUMPKIN, count, Blocks.CARVED_PUMPKIN, Items.PUMPKIN, Blocks.PUMPKIN, Items.SHEARS));
        shapedRecipe2x2("jack_o_lantern", Items.JACK_O_LANTERN, 1, "carved_pumpkin", o, "torch", o);
        shapedRecipe3x3("target", Items.TARGET, 1, o, "redstone", o, "redstone", "hay_block", "redstone", o, "redstone", o);
        shapedRecipe3x3("campfire", Items.CAMPFIRE, 1, o, s, o, s, "coal", s, "log", "log", "log");
        shapedRecipe3x3("soul_campfire", Items.SOUL_CAMPFIRE, 1, o, s, o, s, "soul_soil", s, "log", "log", "log");
        shapedRecipe3x3("soul_torch", Items.SOUL_TORCH, 4, o, "coal", o, o, s, o, o, "soul_soil", o);
        
        String log = "log";
        shapedRecipe3x3("smoker", Items.SMOKER, 1, o, log, o, log, "furnace", log, o, log, o);
        
        String iron_nugget = "iron_nugget";
        shapedRecipe3x3("lantern", Items.LANTERN, 1, iron_nugget, iron_nugget, iron_nugget, iron_nugget, "torch", iron_nugget, iron_nugget, iron_nugget, iron_nugget);
        shapedRecipe3x3("soul_lantern", Items.SOUL_LANTERN, 1, iron_nugget, iron_nugget, iron_nugget, iron_nugget, "soul_torch", iron_nugget, iron_nugget, iron_nugget, iron_nugget);
        shapedRecipe3x3("chain", Items.CHAIN, 1, o, iron_nugget, o, o, "iron_ingot", o, o, iron_nugget, o);
        
        String chiseled = "chiseled_stone_bricks";
        shapedRecipe3x3("lodestone", Items.LODESTONE, 1, chiseled, chiseled, chiseled, chiseled, "netherite_ingot", chiseled, chiseled, chiseled, chiseled);
        shapedRecipe3x3("lightning_rod", Items.LIGHTNING_ROD, 1, o, "copper_ingot", o, o, "copper_ingot", o, o, "copper_ingot", o);
        shapedRecipe3x3("tinted_glass", Items.TINTED_GLASS, 2, o, "amethyst_shard", o, "amethyst_shard", "glass", "amethyst_shard", o, "amethyst_shard", o);
        
        // Redstone
        shapedRecipe2x2("heavy_weighted_pressure_plate", Items.HEAVY_WEIGHTED_PRESSURE_PLATE, 1, "iron_ingot", "iron_ingot", o, o);
        shapedRecipe2x2("light_weighted_pressure_plate", Items.LIGHT_WEIGHTED_PRESSURE_PLATE, 1, "gold_ingot", "gold_ingot", o, o);
        shapedRecipe3x3("daylight_detector", Items.DAYLIGHT_DETECTOR, 1, "glass", "glass", "glass", "quartz", "quartz", "quartz", "wooden_slab", "wooden_slab", "wooden_slab");
        shapedRecipe3x3("tripwire_hook", Items.TRIPWIRE_HOOK, 2, "iron_ingot", o, o, "stick", o, o, "planks", o, o);
        shapedRecipe2x2("trapped_chest", Items.TRAPPED_CHEST, 1, "chest", "tripwire_hook", o, o);
        shapedRecipe3x3("crossbow", Items.CROSSBOW, 1, s, "iron_ingot", s, "string", "tripwire_hook", "string", o, s, o);
        
        String t = "gunpowder";
        String n = "sand";
        shapedRecipe3x3("tnt", Items.TNT, 1, t, n, t, n, t, n, t, n, t);
        shapedRecipe2x2("sticky_piston", Items.STICKY_PISTON, 1, "slime_ball", o, "piston", o);
        shapedRecipe2x2("redstone_torch", Items.REDSTONE_TORCH, 1, "redstone", o, s, o);
        shapedRecipe3x3("repeater", Items.REPEATER, 1, "redstone_torch", "redstone", "redstone_torch", "stone", "stone", "stone", o, o, o);
        shapedRecipe3x3("comparator", Items.COMPARATOR, 1, o, "redstone_torch", o, "redstone_torch", "quartz", "redstone_torch", "stone", "stone", "stone");
        
        String i2 = "iron_ingot";
        String g2 = "gold_ingot";
        shapedRecipe3x3("rail", Items.RAIL, 16, i2, o, i2, i2, s, i2, i2, o, i2);
        shapedRecipe3x3("powered_rail", Items.POWERED_RAIL, 6, g2, o, g2, g2, s, g2, g2, "redstone", g2);
        shapedRecipe3x3("detector_rail", Items.DETECTOR_RAIL, 6, i2, o, i2, i2, "stone_pressure_plate", i2, i2, "redstone", i2);
        shapedRecipe3x3("activator_rail", Items.ACTIVATOR_RAIL, 6, i2, s, i2, i2, "redstone_torch", i2, i2, s, i2);
        shapedRecipe3x3("hopper", Items.HOPPER, 1, i2, o, i2, i2, "chest", i2, o, i2, o);
        
        shapedRecipe3x3("painting", Items.PAINTING, 1, s, s, s, s, "wool", s, s, s, s);
        shapedRecipe3x3("item_frame", Items.ITEM_FRAME, 1, s, s, s, s, "leather", s, s, s, s);
        shapedRecipe2x2("glow_item_frame", Items.GLOW_ITEM_FRAME, 1, "item_frame", "glow_ink_sac", o, o);
    }

    /**
     * Registers final aliases and utility mappings.
     */
    private static void registerFinalAliases() {
        // Additional aliases
        alias("door", "wooden_door");
        alias("trapdoor", "wooden_trapdoor");
        alias("fence", "wooden_fence");
        alias("fence_gate", "wooden_fence_gate");
    }

    // ========================================================================
    // Public API Methods
    // ========================================================================

    /**
     * Returns the task for obtaining a resource by name.
     * 
     * @param resourceName The resource name/key
     * @return The task for obtaining this resource
     */
    public static CataloguedResource getItemTask(String resourceName) {
        return _nameToResourceTask.get(resourceName);
    }

    /**
     * Returns the task for obtaining a resource by item.
     * 
     * @param item The item to get
     * @return The task for obtaining this item's resource
     */
    public static CataloguedResource getItemTask(Item item) {
        return _itemToResourceTask.get(item);
    }

    /**
     * Returns a combined task for obtaining multiple resources.
     * 
     * @param resourceNames Array of resource names
     * @return A combined task for all specified resources
     */
    public static CataloguedResource getSquashedItemTask(String... resourceNames) {
        CataloguedResource[] tasks = new CataloguedResource[resourceNames.length];
        for (int i = 0; i < resourceNames.length; i++) {
            tasks[i] = getItemTask(resourceNames[i]);
        }
        return new CataloguedResourceTask(tasks);
    }

    // ========================================================================
    // Resource Registration Methods (Delegated to Registries)
    // ========================================================================

    // Note: The actual registration methods (mine, simple, smelt, tools, etc.) 
    // are implemented in TaskCatalogue and called by the registries above.
    // This maintains backward compatibility while improving internal structure.
}
