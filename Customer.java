public class Customer 
{
    //Declare variable
    private String name,phoneNum,icNum;
    
    //Default Constructor
    public Customer()
    {
        name = "unknown";
        phoneNum = "unknown";
        icNum = "unknown";
    }
    
    //Normal Constructor
    public Customer(String name, String phoneNum, String icNum)
    {
        this.name=name;
        this.phoneNum=phoneNum;
        this.icNum=icNum;
    }
    
    //Setter method
    public void setCustomer(String name, String phoneNum, String icNum)
    {
        this.name=name;
        this.phoneNum=phoneNum;
        this.icNum=icNum;
    }
    
    //Getter method
    public String getName(){return name;}
    public String getPhoneNum(){return phoneNum;}
    public String getICNum(){return icNum;}
    
    //toString method
    public String toString()
    {
        String detail = " ";
        detail="\nName : "+name+"\nPhone Number : "+phoneNum+"\nIC Number : "+icNum;
        return detail;
    }
}
