package mekanism.api.recipes;

import java.util.function.Predicate;
import javax.annotation.ParametersAreNonnullByDefault;
import mcp.MethodsReturnNonnullByDefault;
import mekanism.api.annotations.FieldsAreNonnullByDefault;
import mekanism.api.annotations.NonNull;
import mekanism.api.gas.GasStack;
import mekanism.api.recipes.inputs.GasStackIngredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Contract;

/**
 * Created by Thiakil on 21/07/2019.
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@FieldsAreNonnullByDefault
public abstract class GasToGasRecipe extends MekanismRecipe implements Predicate<@NonNull GasStack> {

    private final GasStackIngredient input;
    private final GasStack outputRepresentation;

    public GasToGasRecipe(ResourceLocation id, GasStackIngredient input, GasStack outputRepresentation) {
        super(id);
        this.input = input;
        this.outputRepresentation = outputRepresentation;
    }

    @Override
    public boolean test(GasStack gasStack) {
        return input.test(gasStack);
    }

    public GasStackIngredient getInput() {
        return input;
    }

    public GasStack getOutputRepresentation() {
        return outputRepresentation;
    }

    @Contract(value = "_ -> new", pure = true)
    public GasStack getOutput(GasStack input) {
        return outputRepresentation.copy();
    }

    @Override
    public void write(PacketBuffer buffer) {
        input.write(buffer);
        outputRepresentation.writeToPacket(buffer);
    }
}