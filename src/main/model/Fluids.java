package model;
// Class for fluid type

import exceptions.InvalidInputException;

public class Fluids {
    String fluidName;
    int quantityinML;

    public Fluids(String fluidName, String quantityinML) {
        setFluidName(fluidName);
        setQuantityinML(quantityinML);
    }

    public int getQuantityinML() {
        return quantityinML;
    }

    public String getFluidName() {
        return fluidName;
    }

    public void setFluidName(String fluidName) {
        this.fluidName = fluidName;
    }

    public void setQuantityinML(String quantityinML) {
        try {
            int quantity = Integer.parseInt(quantityinML);
            this.quantityinML = quantity;
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }
}
