import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.text.DecimalFormat;

class Item {
    String itemName;
    double itemPrice;

    public Item (String itemName, double itemPrice){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public void display(){
        System.out.println(itemName + " - $" + itemPrice);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Item[] items = new Item[5];
        char repeat;
        char repeat2;

        items[0] = new Item("1. God Of War: Ragnarok", 59.99);
        items[1] = new Item("2. Baldur's Gate", 69.99);
        items[2] = new Item("3. Zelda Tears Of The Kingdom", 49.59);
        items[3] = new Item("4. Pokemon Sword", 35.69);
        items[4] = new Item("5. Monster Hunter World", 20.99);

        System.out.println("List Of Items: ");
        for (Item item : items) {
            item.display();
        }

        double totalPrice = 0.0;
        double over100discount = 0.10;
        double over200discount = 0.15;
        double salesTax = 0.05;

        ArrayList<String> userItemsChoice = new ArrayList<>();

        do {
            System.out.println("What do you want to buy? Enter the number: ");
            int userChoice = scanner.nextInt();

            // Check if user input is valid (1 to 5)
            if (userChoice >= 1 && userChoice <= 5) {
                // Convert user choice to correct index (subtract 1)
                int itemIndex = userChoice - 1;

                totalPrice += items[itemIndex].itemPrice;
                userItemsChoice.add(items[itemIndex].itemName);

                // Display the chosen item
                System.out.println("You chose: ");
                items[itemIndex].display();
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
            System.out.println("Do you want to add another item? (Y/N): ");
            repeat = scanner.next().charAt(0);
        } while (repeat == 'Y' || repeat == 'y');

        double totalCalculatedPrice;
        double totalDiscounted10 = 0;
        double totalDiscounted15 = 0;
        if (totalPrice >= 100 && totalPrice <= 200) {
            totalDiscounted10 = totalPrice * over100discount;
            totalCalculatedPrice = totalPrice - totalDiscounted10;
        } else if (totalPrice > 200) {
            totalDiscounted15 = totalPrice * over200discount;
            totalCalculatedPrice = totalPrice - totalDiscounted15;
        } else {
            totalCalculatedPrice = totalPrice;
        }

        DecimalFormat df = new DecimalFormat("#.00");

        double priceTax;
        priceTax = totalCalculatedPrice * salesTax;

        double allTotalPrice;
        allTotalPrice = totalCalculatedPrice + priceTax;

        if(totalPrice >= 100 && totalPrice <= 200){
            System.out.println("Your total is $" + df.format(totalPrice) + " - " + "Discounted 10% Price: -$"+ df.format(totalDiscounted10)+ " = $"
                    + df.format(totalCalculatedPrice) + " + Tax 5%: $" + df.format(priceTax) + " = $" + df.format(allTotalPrice));
        } else if (totalPrice > 200) {
            System.out.println("Your total is $" + df.format(totalPrice) + " - " + "Discounted 15% Price: -$"+ df.format(totalDiscounted15)+ " =  $"
                    + df.format(totalCalculatedPrice) + " + Tax 5%: $" + df.format(priceTax) + " = $" + df.format(allTotalPrice));
        } else {
            System.out.println("Your total is $" + df.format(totalCalculatedPrice) + " + Tax 5%: $" + df.format(priceTax) +
                    " = $" + df.format(allTotalPrice));
        }


        System.out.println("How much do you want to pay?: ");
        double userPayAmount = scanner.nextDouble();

        do {
            if (userPayAmount < totalPrice) {
                System.out.println("You do not have enough money!");
                break;
            } else {
                if (userPayAmount == totalPrice) {
                    System.out.println("Thank your for buying from us!");
                    System.out.println("===========================================");
                    System.out.println("And Here is your Receipt: ");
                    System.out.println("===========================================");
                    for (String selectedItem : userItemsChoice) {
                        System.out.println(selectedItem);
                    }
                    System.out.println("===========================================");
                    System.out.println("Total Price = $" + df.format(totalPrice));
                    if(totalPrice >= 100 && totalPrice <= 200){
                        System.out.println("Discount 10% Price: -$" + df.format(totalDiscounted10));
                    } else if (totalPrice > 200) {
                        System.out.println("Discount 15% Price: -$" + df.format(totalDiscounted15));
                    } else {
                        System.out.println("No Discount Applied");
                    }
                    System.out.println("Tax 5%: $" + df.format(priceTax));
                    System.out.println("Total: $" + df.format(allTotalPrice));
                    System.out.println("===========================================");
                    System.out.println("Cash Given: $" +df.format(userPayAmount));
                    System.out.println("Change: $0");
                    System.out.println("Thank You For Buying From Us!");
                    break;
                } else if (userPayAmount > totalPrice) {
                    double totalReturn = userPayAmount - allTotalPrice;
                    System.out.println("Here is your change: " + "$" + df.format(totalReturn));
                    System.out.println("===========================================");
                    System.out.println("And Here is your Receipt: ");
                    System.out.println("===========================================");
                    for (String selectedItem : userItemsChoice) {
                        System.out.println(selectedItem);
                    }
                    System.out.println("===========================================");
                    System.out.println("Total Price = $" + df.format(totalPrice));
                    if(totalPrice >= 100 && totalPrice <= 200){
                        System.out.println("Discount 10% Price: -$" + df.format(totalDiscounted10));
                    } else if (totalPrice > 200) {
                        System.out.println("Discount 15% Price: -$" + df.format(totalDiscounted15));
                    } else {
                        System.out.println("No Discount Applied");
                    }
                    System.out.println("Tax 5%: $" + df.format(priceTax));
                    System.out.println("Total: $" + df.format(allTotalPrice));
                    System.out.println("===========================================");
                    System.out.println("Cash Given: $" +df.format(userPayAmount));
                    System.out.println("Change: $" +df.format(totalReturn));
                    System.out.println("Thank You For Buying From Us!");
                    break;
                }
            }
            System.out.println("Do you want to try again? (Y/N): ");
            repeat2 = scanner.next().charAt(0);
        } while (repeat2 == 'Y' || repeat2 == 'y');
        System.out.println("Thank you for using!");

    }
}