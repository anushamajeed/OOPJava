/*
  Anusha Majeed
  1001582645
 */
package code3_1001582645;
import java.util.Scanner;

public class Code3_1001582645
{
    static int CokeMenu()
    {
        Scanner in = new Scanner(System.in);
        int myChoice = 0;
        do
        {
            System.out.println("\nCSE 1325 Coke Machine\n");
            System.out.println("Please choose from the following options\n");
            System.out.println("0. Walk away");
            System.out.println("1. Buy a Coke");
            System.out.println("2. Restock Machine");
            System.out.println("3. Add change level");
            System.out.println("4. Display Machine Info\n");
            System.out.print("Choice : ");
            
            try
            {
                myChoice = in.nextInt();
            }
            catch (Exception e)
            {
                myChoice = -1;
                in.nextLine();
            }
           
            if (myChoice < 0 || myChoice > 4) //verifies choice
            {
                System.out.println("Invalid choice. Re-enter ");
            }
        }
        while (myChoice < 0 || myChoice > 4);
        return myChoice;
    }
    static String displayMoney(int printChg, int payment, CokeMachine MyCokeMachine)
    {
        int changeDispensed = MyCokeMachine.getChangeDispensed();
        String disDollar = String.valueOf(changeDispensed/100);
        String disCents = String.valueOf(changeDispensed%100);
        
        int change = MyCokeMachine.getChangeLevel();
        String dollar = String.valueOf(change/100);
        String cents = String.valueOf(change%100);
        
        int changeToAdd = MyCokeMachine.getChangeLevel();
        String addDollar = String.valueOf(changeToAdd/100);
        String addCents = String.valueOf(changeToAdd%100);
        
        int maxCapacity = MyCokeMachine.getMaxChangeCapacity();
        String maxDollar = String.valueOf(maxCapacity/100);
        String maxCents = String.valueOf(maxCapacity%100);
        
        if(printChg == 0)
            return "$" + disDollar + "." + (disCents.length() == 1 ? "0" : "") + disCents;
        else if(printChg == 1)
            return "$" + dollar + "." + (cents.length() == 1 ? "0" : "") + cents;
        else if(printChg == 2)
            return "$" + addDollar + "." + (addCents.length() == 1 ? "0" : "") + addCents;
        else
            return "$" + maxDollar + "." + (maxCents.length() == 1 ? "0" : "") + maxCents;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        CokeMachine MyCokeMachine = new CokeMachine ("CSE 1325 Coke Machine", 50, 500, 100);
        int myC = 0;
        CokeMachine.ACTION action;
        
        do
        {
            myC = CokeMenu();
            switch(myC)
            {
                //walk away
                case 0:
                    if(MyCokeMachine.getNumberOfCokesSold() >= 1)
                    {
                        System.out.println("Bye - enjoy your coke.\n");
                    }
                    else
                        System.out.println("Are you sure you aren't REALLY thirsty and need a coke?\n");
                        
                    break;
                //buy a coke
                case 1:
                    int payment = 0;
                    System.out.println("\nA coke costs $0.50");
                    System.out.print("Insert payment : ");
                    
                    //if a letter is given the program will exit
                    try
                    {
                        payment = in.nextInt();
                    }
                    catch (Exception e)
                    {
                        System.exit(0);
                    }

                    action = MyCokeMachine.buyACoke(payment);
                    switch(action)
                    {
                        case NOINVENTORY:
                            System.out.println("No inventory.\n");
                            break;
                        case EXACTPAYMENT:
                            System.out.println("Thank you for exact change. Here is your coke.\n");
                            break;
                        case INSUFFICIENTFUNDS:
                            System.out.println("You did not enter sufficient payment. No coke for you.\n");
                            break;
                        case INSUFFICIENTCHANGE:
                            System.out.println("Unable to sell a coke - call 1800WEDONTCARE to register "
                                                + "a complaint...returning your payment.\n");
                            break;
                        case DISPENSECHANGE:
                            String changeDispensed;
                            int printChg = 0;
                            changeDispensed = displayMoney(printChg, payment, MyCokeMachine);
                            System.out.printf("Here's your coke and your change is %s\n", changeDispensed);
                            break;
                        default: 
                            System.out.println("Something unknown happened.\n");
                            break;
                    }
                    break;
                //restock machine
                case 2:
                    int amountToAdd = 0;
                    boolean printwhat;
                    System.out.println("How much product are you adding to the machine?\n");
                    amountToAdd = in.nextInt();
                    printwhat = MyCokeMachine.incrementInventoryLevel(amountToAdd);
                    
                    if(printwhat == false)
                    {
                        System.out.println("You have exceeded your machine's max capacity - no inventory was added.\n");
                        System.out.printf("Your inventory level is %d\n", MyCokeMachine.getInventoryLevel());  
                    }
                    else
                    {
                        System.out.println("Your machine has been restocked.\n");
                        System.out.printf("Your inventory level is %d\n", MyCokeMachine.getInventoryLevel());
                    }
                    break;
                //add change
                case 3:
                    payment  = 0;
                    int printChg;
                    amountToAdd = 0;
                    System.out.println("How much change are you adding to the machine? ");
                    amountToAdd = in.nextInt();
                    printwhat = MyCokeMachine.incrementChangeLevel(amountToAdd);
                    String change;
                    
                    if(printwhat == false)
                    {
                        printChg = 0;
                        change = displayMoney(printChg, payment, MyCokeMachine);
                        System.out.println("You have exceeded your machine's max capacity - no change was added.\n");
                        System.out.printf("Your change level is %s\n", change);  
                    }
                    else
                    {
                        String changeAdd;
                        String maxCapacity;
                        printChg = 2;
                        changeAdd = displayMoney(printChg, payment, MyCokeMachine);
                        printChg = 3;
                        maxCapacity = displayMoney(printChg, payment, MyCokeMachine);
                        System.out.println("Your change level is updated.\n");
                        System.out.printf("Your change level is %s with a max capacity of %s\n", changeAdd, maxCapacity);
                    }
                    break;
                //print machine info
                case 4:
                    System.out.println(MyCokeMachine.toString());
                    break;     
            }
        }
        while(myC != 0);
    }
}
