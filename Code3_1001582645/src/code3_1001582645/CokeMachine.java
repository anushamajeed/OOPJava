/*
 * Anusha Majeed
   1001582645
 */
package code3_1001582645;


/**
 *
 * @author anush
 */
public class CokeMachine
{
    private String machineName;
    private int changeLevel;
    private int maxChangeCapacity = 500;
    private int inventoryLevel;
    private int maxInventoryCapacity = 100;
    private int CokePrice;
    private int changeDispensed;
    private static int numberOfCokesSold = 0;
    
    
    public enum ACTION
    {
        DISPENSECHANGE, INSUFFICIENTCHANGE, INSUFFICIENTFUNDS, EXACTPAYMENT, NOINVENTORY
    }
    
    CokeMachine(String name, int cost, int change, int inventory)
    {
       machineName = name;
       CokePrice = cost;
       changeLevel = change;
       inventoryLevel = inventory;
    }
    public String toString()
    { 
        return (String.format("Machine Name \t\t" + machineName + 
               "\nCurrent Inventory Level \t" + inventoryLevel + 
               "\nCurrent Change Level \t" + changeLevel +
               "\nLast Change Dispensed \t" + changeDispensed +
               "\nInventory Capacity \t" + maxInventoryCapacity +
               "\nChange Capacity \t\t" + maxChangeCapacity +
               "\nCoke Price \t\t" + CokePrice +
               "\nCokes Sold \t\t" + numberOfCokesSold));
                       
    }
    public String getMachineName()
    {
        return machineName;
    }
    public int getChangeLevel()
    {
        return changeLevel;
    }
    public int getMaxChangeCapacity()
    {
        return maxChangeCapacity;
    }
    public int getInventoryLevel()
    {
        return inventoryLevel;
    }
    public int getMaxInventoryLevel()
    {
        return maxInventoryCapacity;
    }
    public int getCokePrice()
    {
        return CokePrice;
    }
    public int getChangeDispensed()
    {
        return changeDispensed;
    }
    public int getNumberOfCokesSold()
    {
        return numberOfCokesSold;
    }
    public boolean incrementInventoryLevel(int amountToAdd)
    {
        if((amountToAdd + inventoryLevel) > maxInventoryCapacity)
        {
            return false;
        }
        else
        {
            inventoryLevel = inventoryLevel + amountToAdd;
            return true;
        }
    }
    public boolean incrementChangeLevel(int amountToAdd)
    {
        if((amountToAdd + changeLevel) > maxChangeCapacity)
        {
            return false;
        }
        else
        {
            changeLevel = changeLevel + amountToAdd;
            return true;
        }
    }
    public ACTION buyACoke(int payment)
    {
        ACTION action;
        
        if(inventoryLevel == 0)
            return action = ACTION.NOINVENTORY;  
        else if (payment == CokePrice)
        {
            inventoryLevel--;
            numberOfCokesSold++;
            changeLevel = changeLevel + payment;
            return action = ACTION.EXACTPAYMENT;
        }
        else if (payment < CokePrice)
        {
            return action = ACTION.INSUFFICIENTFUNDS;
        }
        else if (changeLevel == 0 || changeLevel < (payment - CokePrice))
        {
            return action = ACTION.INSUFFICIENTCHANGE;
        }
        else if (payment > CokePrice)
        {
            inventoryLevel--;
            numberOfCokesSold++;
            changeDispensed = payment - CokePrice;
            return action = ACTION.DISPENSECHANGE;
        }
        else
            return null; 
    }
        
}
