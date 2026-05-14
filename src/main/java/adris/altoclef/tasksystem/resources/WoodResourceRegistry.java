package adris.altoclef.tasksystem.resources;

import adris.altoclef.TaskCatalogue;
import adris.altoclef.util.helpers.ItemHelper;
import net.minecraft.item.Items;

/**
 * Registry for wood-type specific resources (planks, doors, fences, stairs, etc.).
 * Handles the wood family task registration.
 */
public class WoodResourceRegistry {
    
    public static void registerAll() {
        registerWoodItems();
        registerWoodFurniture();
        registerWoodAliases();
    }
    
    private static void registerWoodItems() {
        // Wooden stairs, slabs, doors, trapdoors, fences, fence gates
        TaskCatalogue.simple("wooden_stairs", ItemHelper.WOOD_STAIRS, adris.altoclef.tasks.resources.CollectWoodenStairsTask::new);
        TaskCatalogue.woodTasks("stairs", woodItems -> woodItems.stairs, (woodItems, count) -> 
            new adris.altoclef.tasks.resources.CollectWoodenStairsTask(woodItems.stairs, woodItems.prefix + "_planks", count));
        
        TaskCatalogue.simple("wooden_slab", ItemHelper.WOOD_SLAB, adris.altoclef.tasks.resources.CollectWoodenSlabTask::new);
        TaskCatalogue.woodTasks("slab", woodItems -> woodItems.slab, (woodItems, count) -> 
            new adris.altoclef.tasks.resources.CollectWoodenSlabTask(woodItems.slab, woodItems.prefix + "_planks", count));
        
        TaskCatalogue.simple("wooden_door", ItemHelper.WOOD_DOOR, adris.altoclef.tasks.resources.CollectWoodenDoorTask::new);
        TaskCatalogue.woodTasks("door", woodItems -> woodItems.door, (woodItems, count) -> 
            new adris.altoclef.tasks.resources.CollectWoodenDoorTask(woodItems.door, woodItems.prefix + "_planks", count));
        
        TaskCatalogue.simple("wooden_trapdoor", ItemHelper.WOOD_TRAPDOOR, adris.altoclef.tasks.resources.CollectWoodenTrapDoorTask::new);
        TaskCatalogue.woodTasks("trapdoor", woodItems -> woodItems.trapdoor, (woodItems, count) -> 
            new adris.altoclef.tasks.resources.CollectWoodenTrapDoorTask(woodItems.trapdoor, woodItems.prefix + "_planks", count));
        
        TaskCatalogue.simple("wooden_fence", ItemHelper.WOOD_FENCE, adris.altoclef.tasks.resources.CollectFenceTask::new);
        TaskCatalogue.woodTasks("fence", woodItems -> woodItems.fence, (woodItems, count) -> 
            new adris.altoclef.tasks.resources.CollectFenceTask(woodItems.fence, woodItems.prefix + "_planks", count));
        
        TaskCatalogue.simple("wooden_fence_gate", ItemHelper.WOOD_FENCE_GATE, adris.altoclef.tasks.resources.CollectFenceGateTask::new);
        TaskCatalogue.woodTasks("fence_gate", woodItems -> woodItems.fenceGate, (woodItems, count) -> 
            new adris.altoclef.tasks.resources.CollectFenceGateTask(woodItems.fenceGate, woodItems.prefix + "_planks", count));
        
        // Boats
        TaskCatalogue.simple("boat", ItemHelper.WOOD_BOAT, adris.altoclef.tasks.resources.CollectBoatTask::new);
        TaskCatalogue.woodTasks("boat", woodItems -> woodItems.boat, (woodItems, count) -> 
            new adris.altoclef.tasks.resources.CollectBoatTask(woodItems.boat, woodItems.prefix + "_planks", count));
        
        // Wooden furniture
        String r = "wooden_slab";
        TaskCatalogue.shapedRecipe3x3("barrel", Items.BARREL, 1, "planks", r, "planks", "planks", "o", "planks", "planks", r, "planks");
        TaskCatalogue.shapedRecipe3x3("cartography_table", Items.CARTOGRAPHY_TABLE, 1, "paper", "paper", "o", "planks", "planks", "o", "planks", "planks", "o");
        TaskCatalogue.shapedRecipe3x3("composter", Items.COMPOSTER, 1, r, "o", r, r, "o", r, r, r, r);
        TaskCatalogue.shapedRecipe3x3("fletching_table", Items.FLETCHING_TABLE, 1, "flint", "flint", "o", "planks", "planks", "o", "planks", "planks", "o");
        TaskCatalogue.shapedRecipe3x3("lectern", Items.LECTERN, 1, r, r, r, "o", "bookshelf", "o", "o", r, "o");
    }
    
    private static void registerWoodFurniture() {
        TaskCatalogue.simple("wooden_pressure_plate", ItemHelper.WOOD_PRESSURE_PLATE, adris.altoclef.tasks.resources.CollectWoodenPressurePlateTask::new);
        TaskCatalogue.woodTasks("pressure_plate", woodItems -> woodItems.pressurePlate, (woodItems, count) -> 
            new adris.altoclef.tasks.resources.CollectWoodenPressurePlateTask(woodItems.pressurePlate, woodItems.prefix + "_planks", count));
        
        TaskCatalogue.simple("wooden_button", ItemHelper.WOOD_BUTTON, adris.altoclef.tasks.resources.CollectWoodenButtonTask::new);
        TaskCatalogue.woodTasks("button", woodItems -> woodItems.button, (woodItems, count) -> 
            new adris.altoclef.tasks.container.CraftInInventoryTask(
                new adris.altoclef.util.RecipeTarget(woodItems.button, 1, 
                    new adris.altoclef.util.CraftingRecipe(adris.altoclef.util.CraftingRecipe.newShapedRecipe(
                        woodItems.prefix + "_button", 
                        new adris.altoclef.util.ItemTarget[]{new adris.altoclef.util.ItemTarget(woodItems.planks, 1), null, null, null}, 1)))));
        
        TaskCatalogue.simple("sign", ItemHelper.WOOD_SIGN, adris.altoclef.tasks.resources.CollectSignTask::new).dontMineIfPresent();
        TaskCatalogue.woodTasks("sign", woodItems -> woodItems.sign, (woodItems, count) -> 
            new adris.altoclef.tasks.resources.CollectSignTask(woodItems.sign, woodItems.prefix + "_planks", count));
    }
    
    private static void registerWoodAliases() {
        // Most people will always think "wooden door" when they say "door".
        TaskCatalogue.alias("door", "wooden_door");
        TaskCatalogue.alias("trapdoor", "wooden_trapdoor");
        TaskCatalogue.alias("fence", "wooden_fence");
        TaskCatalogue.alias("fence_gate", "wooden_fence_gate");
    }
}
