import java.util.Scanner;

public class PizzaSliceCalculator {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            double diameter = 0;
            
            // Using the while loop to keep asking the input if its not in range of 6 and 24
            while (true) {
                System.out.print("Please enter the diameter of your pizza (between 6 and 24 inches): ");
                diameter = scanner.nextDouble();
                
                // Check if the diameter is within the valid range
                if (diameter >= 6 && diameter <= 24) {
                    break;  // Exit the loop if valid input
                } else {
                    System.out.println("Error: Pizza diameter must be between 6 and 24 inches. Please try again.");
                }
            }
            
            // Calculate the number of slices
            int slices;
            if (diameter < 8) {
                slices = 4;
                } 
                else if (diameter < 12) {
                slices = 6;
                } 
                else if (diameter < 14) {
                slices = 8;
                } 
                else if (diameter < 16) {
                slices = 10;
                } 
                else if (diameter < 20) {
                slices = 12;
                } else {
                slices = 16;
                }
            
            // Calculating the area of each slice
            double radius = diameter / 2;
            double totalArea = Math.PI * radius * radius; //pi*r^2
            double sliceArea = totalArea / slices;
            
            // Output
            System.out.printf("A %.2f\" pizza will yield %d slices.\n", diameter, slices);
            System.out.printf("A single slice will have an area of %.2f square inches.\n", sliceArea);
        }
    }
}
