public class Motor extends Vehicle
{
   //Declare all attributes
    private String motorName,motorID, motorBrand, motorType,statusM;
    private double motorPrice;
   
   //Default Constructor
    public Motor()
    {
        super();
        motorName="unknown";
        motorID= "unknown";
        motorBrand = "unknown";
        motorType = "unknown";
        statusM= "unknown";
        motorPrice = 0;
    }
   
    //Normal Constructor: w/o rental details
    public Motor(String motorName, String motorID, String motorType, String motorBrand, String statusM, double motorPrice)
    {
        super();
        this.motorName=motorName;
        this.motorID=motorID;
        this.motorBrand=motorBrand;
        this.motorType=motorType;
        this.statusM=statusM;
        this.motorPrice=motorPrice;
    }
    
    //Normal constructor:  with Customer details
    public Motor(String motorName, String motorID, String motorBrand, String motorType,
    String statusM, double motorPrice,String rentalID, String startD,String endD,Customer customer)
    {
        super(rentalID, startD, endD, customer);
        this.motorName=motorName;
        this.motorID=motorID;
        this.motorBrand=motorBrand;
        this.motorType=motorType;
        this.statusM=statusM;
        this.motorPrice=motorPrice;
    }
    
   
    //setter method/mutator method
    public void setRentalID(String rentalID){this.rentalID=rentalID;}
    public void setStartD(String startD){this.startD=startD;}
    public void setEndD(String endD){this.endD=endD;}
    public void setCustomer(Customer customer){this.customer=customer;}
    public void setMotorName(String motorName){this.motorName=motorName;}
    public void setMotorID(String motorID){this.motorID = motorID; }
    public void setMotorBrand(String motorBrand) { this.motorBrand = motorBrand; }
    public void setMotorType(String motorType){this.motorType = motorType;}
    public void setStatusM(String statusM){this.statusM=statusM;}
    public void setMotorPrice(double motorPrice){this.motorPrice=motorPrice;}

    
   //getter method/accessor method/retriever method  
    public String getMotorName() {return motorName;}
    public String getMotorID() {return motorID;}
    public String getMotorBrand() { return motorBrand; }
    public String getMotorType() {return motorType;}
    public double getMotorPrice() {return motorPrice;}
    public String getRentalID(){return rentalID;}
    public String getStatusM(){return statusM;}
    public String getStartD(){return startD;}
    public String getEndD(){return endD;}
    public Customer getCustomer(){return customer;}
    
   //toString method: with Customer
   public String toString(String rentalID,String startD,String endD,Customer customer)
   {
    return  "\n___________________________________________"+
            "\n         [  Customer Details  ]" + getCustomer() + 
            "\nRental ID: " + getRentalID() + 
            "\nStart Date: " + getStartD() + 
            "\nEnd Date: " + getEndD() + 
            "\nMotor Name: " + motorName + 
            "\nMotor ID: " + motorID + 
            "\nMotor Brand: " + motorBrand + 
            "\nMotor Type: " + motorType + 
            "\nMotor Status: " +statusM +
            "\nMotor Price: " + motorPrice +
            "\n___________________________________________";
   }
   
   // toString : w/o Customer
    public String toString()
    {
        return "\nMotor Name: " + motorName + 
                "\nMotor ID: " + motorID + 
               "\nMotor Brand: " + motorBrand + 
               "\nMotor Type: " + motorType +
               "\nMotor Status: " +statusM +
               "\nMotor Price: " + motorPrice ;
    }
}