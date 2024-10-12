/*
 Name: Ishanvi Kaur
 Date: October 12, 2024
 Description: This program take the input for daily low and high temperatures 
                for a chosen period of days.It calculates and displays the average temperature for the days, 
                the overall average, it also identifies the days with the highest and lowest temperatures.
 */

 import java.util.Scanner;

 public class TemperatureAnalysis {
 
     // Constants 
     public static final double MIN_TEMP = -100.0;
     public static final double MAX_TEMP = 100.0;
 
     public static void main(String[] args) {
         // Creating a Scanner object
         Scanner Scanner = new Scanner(System.in);  
         
         // Variable for number of days entered
         int numOfDays;
 
         // Get the number of days from the user (between 2 and 366)
         numOfDays = getValidNumberOfDays(Scanner);
 
         // Arrays to store low and high temperatures for each day
         double[] lowTemp = new double[numOfDays];
         double[] highTemp = new double[numOfDays];
 
         // Collect temperatures for each day from the user, with validation
         collectTempData(Scanner, numOfDays, lowTemp, highTemp);
 
         // Display average temp, high temp and low temp
         displayTempAnalysis(numOfDays, lowTemp, highTemp);
 
         // Close the scanner
         Scanner.close();  
     }
 
     // The input is continuously requested until valid number (between 2 and 366)
     public static int getValidNumberOfDays(Scanner Scanner) {
         int days;
         do {
             System.out.print("Please enter the number of days you want to analyze (between 2 and 366): ");
             days = Scanner.nextInt();  // Get user input for number of days
             if (days < 2 || days > 366) {
                 // Validating the user if the input is out of range
                 System.out.println("Error: The number of days must be between 2 and 366. Please try again.");
             }
         } while (days < 2 || days > 366);  // Continue prompting until the valid input
         return days;
     }
 
     // Validation of high and low temperature
     public static void collectTempData(Scanner inputScanner, int numberOfDays, double[] lowTemp, double[] highTemp) {
         for (int i = 0; i < numberOfDays; i++) {
             // display entering data for a new day
             System.out.println("\nEntering data for Day " + (i + 1) + ":");
 
             // Collect and validate the low temperature for the day
             lowTemp[i] = getValidTemp(inputScanner, "low");
 
             // Collect and validate the high temperature for the day and also validating that it is not less than the low temperature
             do {
                 highTemp[i] = getValidTemp(inputScanner, "high");
                 if (highTemp[i] < lowTemp[i]) {
                     
                     System.out.println("Error: The high temperature cannot be lower than the low temperature. Please enter a valid value.");
                 }
             } while (highTemp[i] < lowTemp[i]);  // Continue prompting until the high temperature is valid
         }
     }
 
     // This method will continue prompting the user until a valid temperature is entered
     public static double getValidTemp(Scanner inputScanner, String tempType) {
         double temp;
         do {
             System.out.printf("Please enter the %s temperature (between %.1f°C and %.1f°C): ", tempType, MIN_TEMP, MAX_TEMP);
             temp = inputScanner.nextDouble();  // Getting user input for the temperature
             if (temp < MIN_TEMP || temp > MAX_TEMP) {
                // Error handling
                 System.out.println("Error: The temperature must be between -100°C and 100°C. Please try again.");
             }
         } while (temp < MIN_TEMP || temp > MAX_TEMP);  // Continue prompting until the input is valid
         return temp;
     }
 
     // This includes the average temperature for each day, the overall average, and the days with the highest and lowest temperatures
     public static void displayTempAnalysis(int numOfDays, double[] lowTemp, double[] highTemp) {
         double totalAverageTemp = 0.0;
         double highestTemp = MIN_TEMP, lowestTemp = MAX_TEMP;
         int dayWithHighestTemp = 0, dayWithLowestTemp = 0;
 
         // calculating the daily average temperature
         for (int i = 0; i < numOfDays; i++) {
             // Calculating the average temperature for the current day
             double dailyAverage = (lowTemp[i] + highTemp[i]) / 2;
             
             // using daily average to calculate the overall average
             totalAverageTemp += dailyAverage;
 
             // Display the low, high, and average temperatures for the current day
             System.out.printf("%nDay %d - Low: %.2f°C, High: %.2f°C, Average: %.2f°C", (i + 1), lowTemp[i], highTemp[i], dailyAverage);
 
             // Check if the current day's high temperature is the highest
             if (highTemp[i] > highestTemp) {
                 highestTemp = highTemp[i];
                 dayWithHighestTemp = i + 1;
             }
 
             // Check if the current day's low temperature is the lowest
             if (lowTemp[i] < lowestTemp) {
                 lowestTemp = lowTemp[i];
                 dayWithLowestTemp = i + 1;
             }
         }
 
         // Calculate the overall average temperature
         totalAverageTemp /= numOfDays;
         System.out.printf("%nThe overall average temperature for the %d days is: %.2f°C%n", numOfDays, totalAverageTemp);
 
         // Display the day with the highest temperature
         System.out.printf("The highest temperature was %.2f°C on Day %d.%n", highestTemp, dayWithHighestTemp);
 
         // Display the day with the lowest temperature
         System.out.printf("The lowest temperature was %.2f°C on Day %d.%n", lowestTemp, dayWithLowestTemp);
     }
 }
 