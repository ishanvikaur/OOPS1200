// PerishableProduct.java
// Author: Ishanvi Kaur 
// Date: 30 November 2024
// Description: Represents a perishable product in a store.

import java.time.LocalDate;

public class PerishableProduct extends Product {
    private LocalDate expiryDate;

    // Default Constructor
    public PerishableProduct() {
        super();
        this.expiryDate = LocalDate.now().plusDays(30); // Default expiry 30 days from now
    }

    // Parametrized Constructor
    public PerishableProduct(long sku, String name, double unitCost, double salePrice, int quantityOnHand, int quantityNeeded, String specialInstructions, LocalDate expiryDate) {
        super(sku, name, unitCost, salePrice, quantityOnHand, quantityNeeded, specialInstructions);
        this.expiryDate = expiryDate;
    }

    // Getter and Setter
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Overridden Display Method
    @Override
    public String toString() {
        return super.toString() + "\nExpiry Date: " + expiryDate;
    }
}
