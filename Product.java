// Product.java
// Author: Ishanvi Kaur
// Date: 30 November 2024
// Description: Represents a generic product in a store.

public class Product {
    private long sku;
    private String name;
    private double unitCost;
    private double salePrice;
    private int quantityOnHand;
    private int quantityNeeded;
    private String specialInstructions;

    // Default Constructor
    public Product() {
        this.sku = 10000000L;
        this.name = "Default Product";
        this.unitCost = 0.0;
        this.salePrice = 0.0;
        this.quantityOnHand = 0;
        this.quantityNeeded = 0;
        this.specialInstructions = "None";
    }

    // Parametrized Constructor
    public Product(long sku, String name, double unitCost, double salePrice, int quantityOnHand, int quantityNeeded, String specialInstructions) {
        setSku(sku);
        setName(name);
        setUnitCost(unitCost);
        setSalePrice(salePrice);
        setQuantityOnHand(quantityOnHand);
        setQuantityNeeded(quantityNeeded);
        this.specialInstructions = specialInstructions;
    }

    // Getters and Setters with Validation
    public long getSku() {
        return sku;
    }

    public void setSku(long sku) {
        if (String.valueOf(sku).length() >= 8) {
            this.sku = sku;
        } else {
            throw new IllegalArgumentException("SKU must be at least 8 digits long.");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        if (unitCost >= 0) {
            this.unitCost = unitCost;
        } else {
            throw new IllegalArgumentException("Unit cost must be >= 0.");
        }
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        if (salePrice >= 0) {
            this.salePrice = salePrice;
        } else {
            throw new IllegalArgumentException("Sale price must be >= 0.");
        }
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        if (quantityOnHand >= 0) {
            this.quantityOnHand = quantityOnHand;
        } else {
            throw new IllegalArgumentException("Quantity on hand must be >= 0.");
        }
    }

    public int getQuantityNeeded() {
        return quantityNeeded;
    }

    public void setQuantityNeeded(int quantityNeeded) {
        if (quantityNeeded >= 0) {
            this.quantityNeeded = quantityNeeded;
        } else {
            throw new IllegalArgumentException("Quantity needed must be >= 0.");
        }
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    // Display method
    @Override
    public String toString() {
        return "SKU: " + sku +
                "\nProduct Name: " + name +
                "\nUnit Cost: $" + unitCost +
                "\nSale Price: $" + salePrice +
                "\nQuantity on Hand: " + quantityOnHand +
                "\nQuantity Needed: " + quantityNeeded +
                "\nSpecial Instructions: " + specialInstructions;
    }
}
