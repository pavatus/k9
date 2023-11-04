package mdt.falloutmod.core.items;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PipboyItem extends Item {

    // Add support for https://www.curseforge.com/minecraft/mc-mods/trinkets
    public PipColor pipColor = PipColor.GREEN;
    //private MinecraftClient client = MinecraftClient.getInstance();

    public PipboyItem(Settings settings) {
        super(settings);
        setPipColor(this.pipColor);
    }

    public void setPipColor(PipColor pipColor) {
        this.pipColor = pipColor;
    }

    public PipColor getPipColor() {
        return this.pipColor;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.of("Pipboy"));
            tooltip.add(Text.of("Color: " + this.pipColor));
        } else {
            tooltip.add(Text.of("Hold shift for more info"));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}

