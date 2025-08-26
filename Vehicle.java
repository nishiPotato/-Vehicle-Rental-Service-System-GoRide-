public abstract class Vehicle
{
    //Declare variables
    protected String rentalID,startD,endD;
    protected Customer customer;
   
    //Default Constructor
    public Vehicle()
    {
        rentalID =" ";
        startD =" ";
        endD =" ";
        customer = null;
    }

   // Normal Constructor
    public Vehicle(String rentalID, String startD, String endD, Customer customer)
    {
        this.rentalID=rentalID;
        this.startD=startD;
        this.endD=endD;
        this.customer=customer;
    }

    //Setter
    public void setCustomer(Customer customer){this.customer=customer;}
    public void setEndD(String endD){this.endD=endD;}
    public void setStartD(String startD){this.startD=startD;}
    public void setRentalID(String rentalID){this.rentalID=rentalID;}

   //Getter
    public String getRentalID(){return rentalID;}
    public String getStartD(){return startD;}
    public String getEndD(){return endD;}
    public Customer getCustomer(){return customer;}
   
   //Processor
    public double calcTotalPrice(double fixedPrice,int day)
    {
        double totalprice=0;
        double tax= fixedPrice*0.05;
    
        totalprice= (fixedPrice* day)+tax;
        return totalprice;
    }
    
    // Renting a vehicle
    public String rentVehicle(String status)
    {
        if (status.equalsIgnoreCase("Available"))
        { 
            return "\n[ Vehicle rented successfully. Rental ID: " + rentalID + " ]";
        } else {
            return "\n[ Vehicle is not available for rent. ]";
        }
    }

    // Returning a vehicle
    public String returnVehicle(String status) 
    {
        if (status.equalsIgnoreCase("Rented")) 
        {
            return "\n[ Vehicle returned successfully. ]";
        } else
        {
            return "\n[ Vehicle is not currently rented. ]";
        }
    }

    // Canceling rental
    public String cancelRental(String status)
    {
        if (status.equalsIgnoreCase("Rented")) {
            return "\n[ Rental canceled successfully. ]";
        } else {
            return "\n[ Rental is not currently active. ]";
        }
    }

    // toString method (Polymorphism)
    public abstract String toString();
}