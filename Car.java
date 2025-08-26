public class Car extends Vehicle
{
    // Declare all attributes
    private String carName, carID, carBrand, transmission, statusC;
    private int pax;
    private double carPrice;

    // Default constructor
    public Car() {
        super(); // Initialize RentalService part
        carName = "unknown";
        carID = "unknown";
        carBrand = "unknown";
        transmission = "unknown";
        statusC = "unknown";
        pax = 0;
        carPrice = 0.0;
    }

    // Normal Constructor: w/o rental details
    public Car(String carName, String carID, String carBrand, String transmission, int pax, String statusC, double carPrice)
    {
        super();
        this.carName = carName;
        this.carID = carID;
        this.carBrand = carBrand;
        this.transmission = transmission;
        this.statusC = statusC;
        this.pax = pax;
        this.carPrice = carPrice;
    }

    // Normal Constructor: With Customer details
    public Car(String carName, String carID, String carBrand, String transmission, int pax, 
    String statusC, double carPrice, String rentalID, String startD, String endD, Customer customer) {
        super(rentalID, startD, endD, customer); // Initialize RentalService part
        this.carName = carName;
        this.carID = carID;
        this.carBrand = carBrand;
        this.transmission = transmission;
        this.statusC = statusC;
        this.pax = pax;
        this.carPrice = carPrice;
    }

    // Setter methods
    public void setRentalID(String rentalID){this.rentalID=rentalID;}
    public void setStartD(String startD){this.startD=startD;}
    public void setEndD(String endD){this.endD=endD;}
    public void setCustomer(Customer customer){this.customer=customer;}
    public void setCarName(String carName) { this.carName = carName; }
    public void setCarID(String carID) { this.carID = carID; }
    public void setCarBrand(String carBrand) { this.carBrand = carBrand; }
    public void setTransmission(String transmission) { this.transmission = transmission; }
    public void setPax(int pax) { this.pax = pax; }
    public void setStatusC(String statusC) { this.statusC = statusC; }
    public void setCarPrice(double carPrice) { this.carPrice = carPrice; }

    // Getter methods
    public String getCarName() { return carName; }
    public String getCarID() { return carID; }
    public String getCarBrand() { return carBrand; }
    public String getTransmission() { return transmission; }
    public int getPax() { return pax; }
    public String getStatusC() { return statusC; }
    public double getCarPrice() { return carPrice; }
    public String getRentalID(){return rentalID;}
    public String getStartD(){return startD;}
    public String getEndD(){return endD;}
    public Customer getCustomer(){return customer;}
   

    // toString to display data: Fully with Customer Details
    public String toString(String rentalID,String startD,String endD,Customer customer) 
    {
        return "\n___________________________________________"+
               "\n       [  Customer Details  ]" + getCustomer() + 
               "\nRental ID: " + getRentalID() + 
               "\nStart Date: " + getStartD() + 
               "\nEnd Date: " + getEndD() + 
                "\nCar Name: " + carName + 
               "\nCar ID: " + carID + 
               "\nCar Brand: " + carBrand + 
               "\nTransmission: " + transmission + 
               "\nPax: " + pax + 
               "\nCar Status: " + statusC + 
               "\nCar Price: " + carPrice +
               "\n___________________________________________";
    }
    
    // toString to display data: W/o Customer details
    public String toString() 
    {
        return "\nCar Name: " + carName + 
               "\nCar ID: " + carID + 
               "\nCar Brand: " + carBrand + 
               "\nTransmission: " + transmission + 
               "\nPax: " + pax + 
               "\nCar Status: " + statusC + 
               "\nCar Price: " + carPrice;
    }
}