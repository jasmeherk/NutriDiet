package model;
// Class for fluid type

public class Fluids {
    public String fluidName;
    public int quantityinML;

    public Fluids(String fluidName, int quantityinML) {
        this.fluidName = fluidName;
        this.quantityinML = quantityinML;
    }

    public int getQuantityinML() {
        return quantityinML;
    }

    public String getFluidName() {
        return fluidName;
    }
}
