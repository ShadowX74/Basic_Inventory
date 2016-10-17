/**
 * *********************************************************************
 * Program Filename: Inventory.java
 * Author: Shen, Xiangyu
 * Date: 10/16/16
 * Description: Loops loot dropping, and asks player what to do
 * Input: Keyboard
 * Output: Console
 **********************************************************************
 */
package inventory;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

public class Inventory {

    /*ArrayLists for inventory and possible drops*/
    private static ArrayList<RngDrops> drops = new ArrayList<RngDrops>();
    private static ArrayList<RngDrops> inventory = new ArrayList<RngDrops>();
    //To iterate through the arraylist
    private static ListIterator<RngDrops> litr;

    //Scanner for user input
    private final static Scanner scan = new Scanner(System.in);

    //Boolean to continue loop while player is alive
    private static boolean playerAlive = true;
    //Weight of player's inventory
    private static int playerWeight = 0;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        setupGame();
        runGame();
    }

    /**
    * *********************************************************************
    * Method: setupGame
    * Description: fills up list of drops
    * Parameters: N/A
    * Pre-conditions: Called in main to start
    * Post-conditions: arraylist 'drops' is filled up
    * *********************************************************************
    */
    private static void setupGame() {
        /*creates item and ads it to list of possible drops*/
        RngDrops item1 = new RngDrops("Gilded Sword", 10, false, "You swing the sword in an arc, decapitating a previously hidden enemy.");
        drops.add(item1);
        RngDrops item2 = new RngDrops("Death Serum", 1, true, "You chug the death serum, and feel your insides begin to liquify.");
        drops.add(item2);
        RngDrops item3 = new RngDrops("Hamburger", 5, false, "You take a bite out of a hamburger.");
        drops.add(item3);
        RngDrops item4 = new RngDrops("Bow", 5, false, "You have no arrows, and can do nothing with the bow.");
        drops.add(item4);
        RngDrops item5 = new RngDrops("Rusty Sword", 8, true, "You swing the sword in an arc, but the blade falls off and pierces through you.");
        drops.add(item5);
        RngDrops item6 = new RngDrops("Magical Mirror", 10, false, "You look at the mirror, and see yourself.");
        drops.add(item6);
        RngDrops item7 = new RngDrops("Magical Mirror", 10, true, "You look at the mirror and see a terrible monster, Stumbling back, you impale yourself on a fallen weapon..");
        drops.add(item7);
        RngDrops item8 = new RngDrops("Anvil", 35, false, "You drop the anvil, which lands with a dull clang. You feel compelled to pick it back up.");
        drops.add(item8);
        RngDrops item9 = new RngDrops("Phone", 3, false, "You press a few buttons, but nothing happens.");
        drops.add(item9);
        RngDrops item10 = new RngDrops("Skull", 7, true, "A snake crawls out of the skull and bites your hand. You soon collapse into giggling fits and die.");
        drops.add(item10);
    }

    /**
    * *********************************************************************
    * Method: runGame
    * Description: loops game, asking player for each new item until player dies
    * Parameters: N/A
    * Pre-conditions: Called in main to start
    * Post-conditions: Game is over. Player is dead.
    * *********************************************************************
    */
    private static void runGame() {
        //While player is not dead
        while (playerAlive) {
            //Drops random item
            RngDrops dropped = dropItem();
            //Asks player what to do with the dropped item
            whatDo(dropped);
            //If player is dead, ask if they want to play again
            if (!playerAlive) {
                playAgain();
            }
        }

    }

    /**
    * *********************************************************************
    * Method: dropItem
    * Description: randomizes a drop
    * Parameters: N/A
    * Pre-conditions: Called runGame everytime it loops
    * Post-conditions: returns a random index for an item
    * *********************************************************************
    */
    private static RngDrops dropItem() {
        //Chooses random item in the list
        Random rand = new Random();
        return drops.get(rand.nextInt(drops.size()));
    }

    /**
    * *********************************************************************
    * Method: whatDo
    * Description: asks user what they want to do with item and executes
    * Parameters: the current item, currentItem
    * Pre-conditions: Called in runGame every loop, or after an action in itself
    * Post-conditions: action performed, and returns to loop, 
    * or player is dead and program ends
    * *********************************************************************
    */
    private static void whatDo(RngDrops currentItem) {
        /*Displaying information*/
        System.out.println("");
        System.out.println("The current weight of your bag is " + playerWeight);
        System.out.println("You find a " + currentItem.getName() + " with a weight of " + currentItem.getWeight());

        /*Asking user for choice*/
        System.out.println("");
        System.out.println("What do you want to do?");
        System.out.println("A. Check Bag");
        System.out.println("B. Pick Up");
        System.out.println("C. Drop Item");
        System.out.println("D. Use Item");
        System.out.println("E. Dead");

        String action = scan.nextLine().toLowerCase();

        System.out.println("");
        //Defining list iterator
        litr = inventory.listIterator();

        //Doing whichever the user chooses
        if (action.equals("a")) { //Checking inventory
            //If there are no items, print that the bag is empty
            if (inventory.isEmpty()) {
                System.out.println("The bag is empty...");
            } else {
                System.out.println("Your inventory:");
                //Iterates through each item, printing the name
                while (litr.hasNext()) {
                    System.out.println(litr.next().getName());
                }
            }
            //Asks user what they will do with the item that is dropped again
            whatDo(currentItem);
        } else if (action.equals("b")) {//Adding item to inventory
            System.out.println("You place the " + currentItem.getName() + " in your bag.");
            /*Adds item to inventory and adds weight*/
            inventory.add(currentItem);
            playerWeight += currentItem.getWeight();
            /*Player dies if there is too much weight*/
            if (playerWeight > 50) {
                System.out.println("The bag was too heavy...");
                System.out.println("You died.");
                playerAlive = false;
            }
        } else if (action.equals("c")) {
            /*If bag has something in it to drop*/
            if (!inventory.isEmpty()) {
                //ArrayList of all objects to remove
                ArrayList<RngDrops> toRemove = new ArrayList();
                //Asks user
                System.out.println("What items would you like to drop?");
                String drop = scan.nextLine();
                //Iterates through items
                while (litr.hasNext()) {
                    //Identifies next item in bag
                    RngDrops nextItem = litr.next();
                    //If the item name matches
                    if (nextItem.getName().equalsIgnoreCase(drop)) {
                        /*Removes weight and adds object to list of to be removed items*/
                        playerWeight -= nextItem.getWeight();
                        toRemove.add(nextItem);
                    }
                }
                
                /* 
                * Remove statement is outside of the list iterator because if
                * you edit the ArrayList while the ListIterator is going through
                * it, it will throw an error. One way to fix this would be to use
                * a normal Iterator
                */
                
                /*If none of those objects were found*/
                if (toRemove.isEmpty()) {
                    System.out.println("You don't have one of those.");
                    //Forces user to not be able to skip current item
                    whatDo(currentItem);
                } else { //If items were found
                    //Removes items
                    inventory.removeAll(toRemove);
                }
            } else {
                //Noting in bag to discard, makes person choose what to do again
                System.out.println("Your bag is empty.");
                whatDo(currentItem);
            }
		} else if (action.equals("d")) {
			if (!inventory.isEmpty()) {
				/* Uses last picked up */
				System.out.println("You used a " + inventory.get(inventory.size() - 1).getName() + ".");
				// Prints out action for item
				System.out.println(inventory.get(inventory.size() - 1).getAction());

				/* If the item has the kills property, the player dies */
				if (inventory.get(inventory.size() - 1).getKills() == true) {
					playerAlive = false;
				}

				if (!inventory.get(inventory.size() - 1).getName().equals("Anvil") && 
						!inventory.get(inventory.size() - 1).getName().equals("Gilded Sword") && 
						!inventory.get(inventory.size() - 1).getName().equals("Magical Mirror") && 
						!inventory.get(inventory.size() - 1).getName().equals("Bow") &&
						!inventory.get(inventory.size() - 1).getName().equals("Phone")) { //If item is reusable
					/* Removes item from inventory */
					playerWeight -= inventory.get(inventory.size() - 1).getWeight();
					inventory.remove(inventory.size() - 1);
				}
			} else {
				System.out.println("There is nothing in your bag to use.");
				whatDo(currentItem);
			}
        } else if (action.equals("e")) {
            //Suicide option
            System.out.println("You deaded yourself");
            playerAlive = false;
        } else {
            //No valid answer
            System.out.println("Not a valid answer. Please try again.");
            whatDo(currentItem);
        }
    }

    /**
    * *********************************************************************
    * Method: playAgain
    * Description: asks user if they want to play again
    * Parameters: N/A
    * Pre-conditions: Called in main to start
    * Post-conditions: program ends, or begins looping again.
    * *********************************************************************
    */
    private static void playAgain() {
        System.out.println("Play Again? (Y/N)");
        String again = scan.nextLine();
        //If player does want to play again
        if (again.equalsIgnoreCase("y")) {
            /*Reset variables*/
            playerAlive = true;
            playerWeight = 0;
            inventory.removeAll(inventory);
        }
    }

}
