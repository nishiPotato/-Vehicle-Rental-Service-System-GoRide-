import java.util.*;
import java.io.*;

public class MainApplication
{
    public static void main(String args[]) throws IOException
    {
        Scanner inputText = new Scanner(System.in);
        Scanner inputNum = new Scanner(System.in);
        Car[] carArray=new Car[100];  
        Motor[] motorArray = new Motor[100];
        Vehicle veh[] = new Vehicle[999];// Array to store rental services
        
        int rentalIndex=0;
        int index=0, menu,choicePayment;
        String startD=" ",endD=" ",name=" ",phoneNum=" ",icNum=" ",rentalID=" ";
                
        do 
        {
            System.out.println("*****************************************************");
            System.out.println("\n       Welcome to GoRide's Rental Service!         ");
            System.out.println("\nHere are some of the services we provide:");
            System.out.println("\n1. Rent a vehicle");
            System.out.println("\n2. Return a vehicle");
            System.out.println("\n3. Cancel a vehicle");
            System.out.println("\nPlease choose a service: ");
            int choice = inputNum.nextInt();
            System.out.println("*****************************************************");
                
            if (choice == 1) //Rent Vehicle
            {
                System.out.println("\nPlease choose a vehicle:");
                System.out.println("\n1. Car");
                System.out.println("\n2. Motorcycle");
                int choiceVehicle = inputNum.nextInt();
                    
                if (choiceVehicle == 1) //Choice: Car
                {
                    System.out.println("\nChoose the transmission:");
                    System.out.println("\n1. Manual");
                    System.out.println("\n2. Automatic");
                    int choiceTrans = inputNum.nextInt();
                    
                    if (choiceTrans == 1) //Manual 
                    {
                        System.out.println("\nChoose the pax:");
                        System.out.println("\n1. 5 pax");
                        System.out.println("\n2. 8 pax");
                        int choicePax = inputNum.nextInt();
                    
                        if (choicePax == 1) //5 pax
                        {
                            try 
                            {
                                //CarRental[] carArray = new CarRental[10];
                    
                                FileReader fr = new FileReader("carManual5.txt");
                                BufferedReader br = new BufferedReader(fr);
                    
                                int count = 0;
                                StringTokenizer st = null;
                                String dataRow = br.readLine();
                                while (dataRow != null) 
                                {
                                    st = new StringTokenizer(dataRow, ";");
                    
                                            String carName = st.nextToken();
                                            String carID = st.nextToken();
                                            String transmission = st.nextToken();
                                            int pax = Integer.parseInt(st.nextToken());
                                            String carBrand = st.nextToken();
                                            double carPrice = Double.parseDouble(st.nextToken());
                                            String statusC = st.nextToken();
                    
                                            carArray[count] = new Car(carName, carID, carBrand, transmission, pax, statusC, carPrice);
                                            count++;
                                            dataRow = br.readLine();
                                }
                    
                                System.out.println("---------------------------------------------------");
                                System.out.println("Category : Car \nTransmission : Manual \nPax : 5");
                                System.out.println("---------------------------------------------------");
                                 
                                                 
                                for (int i = 0; i <count; i++) 
                                {
                                        boolean carRented=false;
                                        for(int j=0;j<rentalIndex;j++)//use j to differentiate between array for cararray and array for rent 
                                        {
                                            if (veh[j] instanceof Car)
                                            {
                                                Car car=(Car) veh[j];
                                                if (car.getCarID().equals(carArray[i].getCarID()))
                                                {
                                                    carRented=car.getStatusC().equalsIgnoreCase("Rented");
                                                    break;
                                                }
                                            }
                                        }
                                        
                                        if(carRented) //false 
                                        {
                                            carArray[i].setStatusC("RENTED");
                                        }
                                        else //true 
                                        {
                                           carArray[i].setStatusC("AVAILABLE"); 
                                        }
                                        System.out.println((i + 1) + ". " + carArray[i].toString() + "\n");
                                }
                                                      
                                boolean validInput = false;
                                do
                                {         
                                    System.out.println("Enter the number of the car information: ");
                                    int userInputIndex = inputNum.nextInt();
                        
                                    index = userInputIndex - 1;
                        
                                    if (index >= 0 && index < count)
                                    {
                                        if (carArray[index].getStatusC().equalsIgnoreCase("Rented"))
                                                {
                                                    System.out.println("\nSorry this car is already rented.");
                                                                        
                                                }
                                                else
                                                {
                                                    System.out.println("\nInformation about car " + userInputIndex + ":");
                                                    System.out.println(carArray[index].toString());
                                                                        
                                                                        
                                                    System.out.println("\nStart Date :");
                                                    startD = inputText.nextLine();
                                                    System.out.println("\nEnd Date :");
                                                    endD = inputText.nextLine();
                                                    System.out.println("\nCustomer name :");
                                                    name = inputText.nextLine();
                                                    System.out.println("\nCustomer phone Number :");
                                                    phoneNum = inputText.nextLine();
                                                    System.out.println("\nIC Number :");
                                                    icNum = inputText.nextLine();
                                
                                                    rentalID = icNum.substring(8, 12);
                                                                        
                                                    carArray[index].setRentalID(rentalID);
                         
                                                    String rentalMessage = carArray[index].rentVehicle(carArray[index].getStatusC());
                                                    System.out.println(rentalMessage);
                                                                        
                                                    carArray[index].setStatusC("RENTED");
                                                    
                                                    
                                                    //Polymorphism Superclass (vehicle) hold object of a subclass (car) 
                                                    veh[rentalIndex]= new Car(carArray[index].getCarName(),carArray[index].getCarID(),
                                                    carArray[index].getCarBrand(),carArray[index].getTransmission(),carArray[index].getPax(),
                                                    carArray[index].getStatusC(),carArray[index].getCarPrice(),rentalID,startD,endD,
                                                    new Customer(name, phoneNum, icNum));
                                                    rentalIndex++;
                                      
                                                    carArray[index]=new Car(carArray[index].getCarName(),carArray[index].getCarID(),
                                                    carArray[index].getCarBrand(),carArray[index].getTransmission(),carArray[index].getPax(),carArray[index].getStatusC(),
                                                    carArray[index].getCarPrice(),rentalID,startD,endD,new Customer(name, phoneNum, icNum));
                                                                        
                                                    System.out.println(carArray[index].toString(rentalID, startD, endD, new Customer(name, phoneNum, icNum)));
                                                    validInput=true;
                                                                                
                                        }
                                    }
                                    else 
                                    {
                                                System.out.println("\nInvalid car number. Please enter a number between 1 and " + count + ".");
                                    }               
                                }while (!validInput);
                            }catch(EOFException eof){System.out.println("Problem: " + eof.getMessage());
                            } catch (FileNotFoundException e) {System.out.println("Problem: " + e.getMessage());
                            } catch (IOException ioe) {System.out.println("Problem: " + ioe.getMessage());}
                        }
                        else if(choicePax==2) //8pax
                        {                            
                            try 
                            {
                                FileReader fr = new FileReader("carManual8.txt");
                                BufferedReader br = new BufferedReader(fr);
                    
                                int count = 0;
                                StringTokenizer st = null;
                                String dataRow = br.readLine();
                                while (dataRow != null)
                                {
                                    st = new StringTokenizer(dataRow, ";");
                    
                                    String carName = st.nextToken();
                                    String carID = st.nextToken();
                                    String transmission = st.nextToken();
                                    int pax = Integer.parseInt(st.nextToken());
                                    String carBrand = st.nextToken();
                                    double carPrice = Double.parseDouble(st.nextToken());
                                    String statusC = st.nextToken();
                    
                                    carArray[count] = new Car(carName, carID, carBrand, transmission, pax, statusC, carPrice);
                                    count++;
                                    dataRow = br.readLine();
                                }
                    
                                System.out.println("---------------------------------------------------");
                                System.out.println("Category : Car \nTransmission : Manual \nPax : 8");
                                System.out.println("---------------------------------------------------");
                                                    
                                for (int i = 0; i <count; i++) 
                                {
                                    boolean carRented=false;
                                    for(int j=0;j<rentalIndex;j++)
                                    {
                                        if (veh[j] instanceof Car)
                                        {
                                            Car car=(Car) veh[j];
                                            if (car.getCarID().equals(carArray[i].getCarID()))
                                            {
                                                carRented=car.getStatusC().equalsIgnoreCase("Rented");
                                                break;
                                            }
                                        }
                                    }
                                                
                                    if(carRented)
                                    {
                                        carArray[i].setStatusC("RENTED");
                                    }
                                    else
                                    {
                                        carArray[i].setStatusC("AVAILABLE"); 
                                    }
                                    System.out.println((i + 1) + ". " + carArray[i].toString() + "\n");
                                }
                                                      
                                boolean validInput = false;
                                do
                                {         
                                    System.out.println("Enter the number of the car information: ");
                                    int userInputIndex = inputNum.nextInt();
                        
                                    index = userInputIndex - 1;
                        
                                    if (index >= 0 && index < count)
                                    {
                                        if (carArray[index].getStatusC().equals("Rented"))
                                        {
                                            System.out.println("\nSorry this car is already rented.");
                                                                        
                                        }
                                        else
                                        {
                                            System.out.println("\nInformation about car " + userInputIndex + ":");
                                            System.out.println(carArray[index].toString());
                                                                        
                                            System.out.println("\nStart Date :");
                                            startD = inputText.nextLine();
                                            System.out.println("\nEnd Date :");
                                            endD = inputText.nextLine();
                                            System.out.println("\nCustomer name :");
                                            name = inputText.nextLine();
                                            System.out.println("\nCustomer phone Number :");
                                            phoneNum = inputText.nextLine();
                                            System.out.println("\nIC Number :");
                                            icNum = inputText.nextLine();
                            
                                            rentalID = icNum.substring(8, 12);                          
                                            carArray[index].setRentalID(rentalID);
                
                                            String rentalMessage = carArray[index].rentVehicle(carArray[index].getStatusC());
                                            System.out.println(rentalMessage);
                                                                        
                                            carArray[index].setStatusC("RENTED");
                                
                                            veh[rentalIndex]= new Car(carArray[index].getCarName(),carArray[index].getCarID(),carArray[index].getCarBrand(),
                                            carArray[index].getTransmission(),carArray[index].getPax(),carArray[index].getStatusC(),carArray[index].getCarPrice(),
                                            rentalID,startD,endD,new Customer(name, phoneNum, icNum));
                                            rentalIndex++;
                                              
                                            carArray[index]=new Car(carArray[index].getCarName(),carArray[index].getCarID(),carArray[index].getCarBrand(),
                                            carArray[index].getTransmission(),carArray[index].getPax(),carArray[index].getStatusC(),
                                            carArray[index].getCarPrice(),rentalID,startD,endD,new Customer(name, phoneNum, icNum));
                                                                        
                                            System.out.println(carArray[index].toString(rentalID, startD, endD, new Customer(name, phoneNum, icNum)));
                                            validInput=true;
                                                                                
                                        }
                                    } 
                                    else 
                                    {
                                        System.out.println("\nInvalid car number. Please enter a number between 1 and " + count + ".");
                                    }
                                }while (!validInput);
                            }catch(EOFException eof){System.out.println("Problem: " + eof.getMessage());
                            } catch (FileNotFoundException e) {System.out.println("Problem: " + e.getMessage());
                            } catch (IOException ioe) {System.out.println("Problem: " + ioe.getMessage());}
                        }
                    }
                    else if (choiceTrans == 2) //Auto Transmission
                    {
                            System.out.println("\nChoose the pax:");
                            System.out.println("\n1. 5 pax");
                            System.out.println("\n2. 8 pax");
                            int choicePax = inputNum.nextInt();
                    
                            if (choicePax == 1) //Car Auto 5pax
                            {
                                try 
                                {
                    
                                    FileReader fr = new FileReader("carAuto5.txt");
                                    BufferedReader br = new BufferedReader(fr);
                    
                                    int count = 0;
                                    StringTokenizer st = null;
                                    String dataRow = br.readLine();
                                    while (dataRow != null)
                                    {
                                        st = new StringTokenizer(dataRow, ";");
                    
                                        String carName = st.nextToken();
                                        String carID = st.nextToken();
                                        String transmission = st.nextToken();
                                        int pax = Integer.parseInt(st.nextToken());
                                        String carBrand = st.nextToken();
                                        double carPrice = Double.parseDouble(st.nextToken());
                                        String statusC = st.nextToken();
                    
                                        carArray[count] = new Car(carName, carID, carBrand, transmission, pax, statusC, carPrice);
                                        count++;
                                        dataRow = br.readLine();
                                    }
                    
                                    System.out.println("---------------------------------------------------");
                                    System.out.println("Category : Car \nTransmission : Auto \nPax : 5");
                                    System.out.println("---------------------------------------------------");
                                                    
                                    for (int i = 0; i <count; i++) 
                                    {
                                        boolean carRented=false;
                                        for(int j=0;j<rentalIndex;j++)
                                        {
                                            if (veh[j] instanceof Car)
                                            {
                                                Car car=(Car) veh[j];
                                                if (car.getCarID().equals(carArray[i].getCarID()))
                                                {
                                                    carRented=car.getStatusC().equalsIgnoreCase("Rented");
                                                    break;
                                                }
                                            }
                                        }
                                                        
                                        if(carRented)
                                        {
                                            carArray[i].setStatusC("RENTED");
                                        }
                                        else
                                        {
                                            carArray[i].setStatusC("AVAILABLE"); 
                                        }
                                        System.out.println((i + 1) + ". " + carArray[i].toString() + "\n");
                                    }
                                                      
                                    boolean validInput = false;
                                    do
                                    {         
                                        System.out.println("Enter the number of the car information: ");
                                        int userInputIndex = inputNum.nextInt();
                        
                                        index = userInputIndex - 1;
                        
                                        if (index >= 0 && index < count)
                                        {
                                            if (carArray[index].getStatusC().equals("Rented"))
                                            {
                                                System.out.println("\nSorry this car is already rented.");
                                                                        
                                            }
                                            else
                                            {
                                                System.out.println("\nInformation about car " + userInputIndex + ":");
                                                System.out.println(carArray[index].toString());
                                                                        
                                                System.out.println("\nStart Date :");
                                                startD = inputText.nextLine();
                                                System.out.println("\nEnd Date :");
                                                endD = inputText.nextLine();
                                                System.out.println("\nCustomer name :");
                                                name = inputText.nextLine();
                                                System.out.println("\nCustomer phone Number :");
                                                phoneNum = inputText.nextLine();
                                                System.out.println("\nIC Number :");
                                                icNum = inputText.nextLine();
                                
                                                rentalID = icNum.substring(8,12);    
                                                carArray[index].setRentalID(rentalID);
                                                String rentalMessage = carArray[index].rentVehicle(carArray[index].getStatusC());
                                                System.out.println(rentalMessage);
                                                                        
                                                carArray[index].setStatusC("RENTED");                  
                                                veh[rentalIndex]= new Car(carArray[index].getCarName(),carArray[index].getCarID(),carArray[index].getCarBrand(),
                                                carArray[index].getTransmission(),carArray[index].getPax(),carArray[index].getStatusC(),carArray[index].getCarPrice(),
                                                rentalID,startD,endD,new Customer(name, phoneNum, icNum));
                                                rentalIndex++;                                                  
                                            
                                                carArray[index]=new Car(carArray[index].getCarName(),carArray[index].getCarID(),carArray[index].getCarBrand(),
                                                carArray[index].getTransmission(),carArray[index].getPax(),carArray[index].getStatusC(),carArray[index].getCarPrice(),
                                                rentalID,startD,endD,new Customer(name, phoneNum, icNum));
                                                                        
                                                System.out.println(carArray[index].toString(rentalID, startD, endD, new Customer(name, phoneNum, icNum)));
                                                validInput=true;
                                                                                
                                            }
                                        }
                                        else
                                        {
                                            System.out.println("\nInvalid car number. Please enter a number between 1 and " + count + ".");
                                        }
                                    }while (!validInput);
                                }catch(EOFException eof){System.out.println("Problem: " + eof.getMessage());
                                } catch (FileNotFoundException e) {System.out.println("Problem: " + e.getMessage());
                                } catch (IOException ioe) {System.out.println("Problem: " + ioe.getMessage());}
                            }
                        
                        
                                else if (choicePax == 2)// Car Auto 8 Pax
                                {
                                    try 
                                    {
                                        FileReader fr = new FileReader("carAuto8.txt");
                                        BufferedReader br = new BufferedReader(fr);
                                        int count = 0;
                                        StringTokenizer st = null;
                                        String dataRow = br.readLine();
                                        while (dataRow != null)
                                        {
                                            st = new StringTokenizer(dataRow, ";");
                            
                                            String carName = st.nextToken();
                                            String carID = st.nextToken();
                                            String transmission = st.nextToken();
                                            int pax = Integer.parseInt(st.nextToken());
                                            String carBrand = st.nextToken();
                                            double carPrice = Double.parseDouble(st.nextToken());
                                            String statusC = st.nextToken();
                            
                                            carArray[count] = new Car(carName, carID, carBrand, transmission, pax, statusC, carPrice);
                                            count++;
                                            dataRow = br.readLine();
                                        }
                            
                                        System.out.println("---------------------------------------------------");
                                        System.out.println("Category : Car \nTransmission : Auto \nPax : 8");
                                        System.out.println("---------------------------------------------------");
                                                            
                                        for (int i = 0; i <count; i++) 
                                        {
                                            boolean carRented=false;
                                            for(int j=0;j<rentalIndex;j++)
                                            {
                                                if (veh[j] instanceof Car)
                                                {
                                                    Car car=(Car) veh[j];
                                                    if (car.getCarID().equals(carArray[i].getCarID()))
                                                    {
                                                        carRented=car.getStatusC().equalsIgnoreCase("Rented");
                                                        break;
                                                    }
                                                }
                                            }
                                                            
                                            if(carRented)
                                            {
                                                carArray[i].setStatusC("RENTED");
                                            }
                                            else
                                            {
                                                carArray[i].setStatusC("AVAILABLE"); 
                                            }
                                            System.out.println((i + 1) + ". " + carArray[i].toString() + "\n");
                                        }                  
                                        
                                        boolean validInput = false;
                                        do
                                        {         
                                            System.out.println("Enter the number of the car information: ");
                                            int userInputIndex = inputNum.nextInt();
                                
                                            index = userInputIndex - 1;
                                
                                            if (index >= 0 && index < count)
                                            {
                                                if (carArray[index].getStatusC().equalsIgnoreCase("Rented"))
                                                {
                                                    System.out.println("\nSorry this car is already rented.");
                                                                                
                                                }
                                                else
                                                {
                                                    System.out.println("\nInformation about car " + userInputIndex + ":");
                                                    System.out.println(carArray[index].toString());
                                                                                
                                                    System.out.println("\nStart Date :");
                                                    startD = inputText.nextLine();
                                                    System.out.println("\nEnd Date :");
                                                    endD = inputText.nextLine();
                                                    System.out.println("\nCustomer name :");
                                                    name = inputText.nextLine();
                                                    System.out.println("\nCustomer phone Number :");
                                                    phoneNum = inputText.nextLine();
                                                    System.out.println("\nIC Number :");
                                                    icNum = inputText.nextLine();
                                                    
                                                    rentalID = icNum.substring(8,12);                          
                                                    carArray[index].setRentalID(rentalID);
                                                    String rentalMessage = carArray[index].rentVehicle(carArray[index].getStatusC());
                                                    System.out.println(rentalMessage);
                                                                                
                                                    carArray[index].setStatusC("RENTED");
                                                    veh[rentalIndex]= new Car(carArray[index].getCarName(),carArray[index].getCarID(),
                                                    carArray[index].getCarBrand(),carArray[index].getTransmission(),carArray[index].getPax(),
                                                    carArray[index].getStatusC(),carArray[index].getCarPrice(),rentalID,startD,endD,
                                                    new Customer(name, phoneNum, icNum));
                                                    
                                                    rentalIndex++;                           
                                                    carArray[index]=new Car(carArray[index].getCarName(),carArray[index].getCarID(),
                                                    carArray[index].getCarBrand(),carArray[index].getTransmission(),carArray[index].getPax(),
                                                    carArray[index].getStatusC(),carArray[index].getCarPrice(),
                                                    rentalID,startD,endD,new Customer(name, phoneNum, icNum));
                                                                                
                                                    System.out.println(carArray[index].toString(rentalID, startD, endD, new Customer(name, phoneNum, icNum)));
                                                    validInput=true;                               
                                                }
                                            }
                                            else
                                            {
                                                System.out.println("\nInvalid car number. Please enter a number between 1 and " + count + ".");
                                            }
                                        }while (!validInput);
                                    }catch(EOFException eof){System.out.println("Problem: " + eof.getMessage());
                                    } catch (FileNotFoundException e) {System.out.println("Problem: " + e.getMessage());
                                    } catch (IOException ioe) {System.out.println("Problem: " + ioe.getMessage());}
                                }
                        }
                        System.out.println("\nHow many days for the rental?");
                        int day = inputNum.nextInt();
                        
                        double totalPrice = 0;
                        for (int i = 0; i < veh.length; i++) 
                        {
                            if (veh[i] instanceof Car) 
                            {
                                Car car=(Car) veh[i];
                                totalPrice = veh[rentalIndex-1].calcTotalPrice(car.getCarPrice(), day);
                                                    
                            }
                        }
                        System.out.println("\nThe total price after tax is : RM" + totalPrice);   
                }
                else if ( choiceVehicle == 2) //Choice: Motor 
                {
                    System.out.println("\nChoose the type:");
                    System.out.println("\n1. Standard");
                    System.out.println("\n2. Superbike");
                    int choiceType = inputNum.nextInt();
                    if (choiceType ==1) //Choose Standard Motor
                    {
                        try
                        {
                            FileReader fr = new FileReader("motorStandard.txt");
                            BufferedReader br = new BufferedReader(fr);
                            
                            int count = 0;
                            StringTokenizer st = null;
                            String dataRow = br.readLine();
                             while (dataRow != null) 
                            {
                                st = new StringTokenizer(dataRow, ";");
                                String motorName = st.nextToken();
                                String motorID = st.nextToken();
                                String motorType = st.nextToken();
                                String motorBrand = st.nextToken();
                                double motorPrice = Double.parseDouble(st.nextToken());
                                String statusM = st.nextToken();
                                
                                motorArray[count] = new Motor(motorName, motorID, motorType, motorBrand, statusM, motorPrice);
                                count++;
                                dataRow = br.readLine();
                            }
                            
                            System.out.println("---------------------------------------------------");
                            System.out.println("Category : Motor \nMotor Type : Standard");
                            System.out.println("---------------------------------------------------");
                             
                             for (int i = 0; i <count; i++) 
                            {
                                boolean motorRented=false;
                                for(int j=0;j<rentalIndex;j++)
                                {
                                    if (veh[j] instanceof Motor)
                                    {
                                        Motor motor=(Motor) veh[j];
                                        if (motor.getMotorID().equals(motorArray[i].getMotorID()))
                                        {
                                            motorRented=motor.getStatusM().equalsIgnoreCase("Rented");
                                            break;
                                        }
                                    }
                                }
                                if(motorRented)
                                {
                                    motorArray[i].setStatusM("RENTED");
                                }
                                else
                                {
                                    motorArray[i].setStatusM("AVAILABLE"); 
                                }
                                System.out.println((i + 1) + ". " + motorArray[i].toString() + "\n");
                            }                  
                                        
                                                              
                            boolean validInput = false;
                            do
                            {         
                                System.out.println("Enter the number of the motor information: ");
                                int userInputIndex = inputNum.nextInt();
                                
                                index = userInputIndex - 1;
                                
                                if (index >= 0 && index < count)
                                {
                                    if (motorArray[index].getStatusM().equalsIgnoreCase("RENTED"))
                                    {
                                        System.out.println("\nSorry this motor is already rented.");
                                                                                        
                                    }
                                    else
                                    {
                                        System.out.println("\nInformation about motor " + userInputIndex + ":");
                                        System.out.println(motorArray[index].toString());
                                                                                        
                                                                                        
                                        System.out.println("\nStart Date :");
                                        startD = inputText.nextLine();
                                        System.out.println("\nEnd Date :");
                                        endD = inputText.nextLine();
                                        System.out.println("\nCustomer name :");
                                        name = inputText.nextLine();
                                        System.out.println("\nCustomer phone Number :");
                                        phoneNum = inputText.nextLine();
                                        System.out.println("\nIC Number :");
                                        icNum = inputText.nextLine();
                                                
                                        rentalID = icNum.substring(8, 12);                                              
                                        motorArray[index].setRentalID(rentalID);
                                        String rentalMessage = motorArray[index].rentVehicle(motorArray[index].getStatusM());
                                        System.out.println(rentalMessage);
                                                                                        
                                        motorArray[index].setStatusM("RENTED");                             
                                        veh[rentalIndex]= new Motor(motorArray[index].getMotorName(),motorArray[index].getMotorID(),
                                        motorArray[index].getMotorBrand(),motorArray[index].getMotorType(),motorArray[index].getStatusM(),
                                        motorArray[index].getMotorPrice(),rentalID,startD,endD,new Customer(name, phoneNum, icNum));
                                        rentalIndex++;
                                        
                                        motorArray[index]=new Motor(motorArray[index].getMotorName(),motorArray[index].getMotorID(),
                                        motorArray[index].getMotorBrand(),motorArray[index].getMotorType(),motorArray[index].getStatusM(),
                                        motorArray[index].getMotorPrice(),rentalID,startD,endD,new Customer(name, phoneNum, icNum));
                                                                                        
                                        System.out.println(motorArray[index].toString(rentalID, startD, endD, new Customer(name, phoneNum, icNum)));
                                        validInput=true;                                                     
                                    }
                                }
                                else 
                                {  System.out.println("\nInvalid motor number. Please enter a number between 1 and " + count + ".");                      }
                                }while (!validInput);
                             }catch(EOFException eof){System.out.println("Problem: " + eof.getMessage());
                             } catch (FileNotFoundException e) {System.out.println("Problem: " + e.getMessage());
                             } catch (IOException ioe) {System.out.println("Problem: " + ioe.getMessage());}
                    }
                    else if(choiceType ==2) //Choose Superbike Motor
                    {
                        try
                        {
                            FileReader fr = new FileReader("motorSuperbike.txt");
                            BufferedReader br = new BufferedReader(fr);
                            
                            int count = 0;
                            StringTokenizer st = null;
                            String dataRow = br.readLine();
                             while (dataRow != null) 
                            {
                                st = new StringTokenizer(dataRow, ";");
                                String motorName = st.nextToken();
                                String motorID = st.nextToken();
                                String motorType = st.nextToken();
                                String motorBrand = st.nextToken();
                                double motorPrice = Double.parseDouble(st.nextToken());
                                String statusM = st.nextToken();
                                
                                motorArray[count] = new Motor(motorName, motorID, motorType, motorBrand, statusM, motorPrice);
                                count++;
                                dataRow = br.readLine();
                            }
                            
                            System.out.println("---------------------------------------------------");
                            System.out.println("Category : Motor \nMotor Type : Superbike");
                            System.out.println("---------------------------------------------------");
                        
                             for (int i = 0; i <count; i++) 
                            {
                                boolean motorRented=false;
                                for(int j=0;j<rentalIndex;j++)
                                {
                                    if (veh[j] instanceof Motor)
                                    {
                                        Motor motor=(Motor) veh[j];
                                        if (motor.getMotorID().equals(motorArray[i].getMotorID()))
                                        {
                                            motorRented=motor.getStatusM().equalsIgnoreCase("Rented");
                                            break;
                                        }
                                    }
                                }
                                                            
                                if(motorRented)
                                {
                                    motorArray[i].setStatusM("RENTED");
                                }
                                else
                                {
                                    motorArray[i].setStatusM("AVAILABLE"); 
                                }
                                System.out.println((i + 1) + ". " + motorArray[i].toString() + "\n");
                            }                  
                                        
                                                              
                            boolean validInput = false;
                            do
                            {         
                                System.out.println("Enter the number of the motor information: ");
                                int userInputIndex = inputNum.nextInt();
                                index = userInputIndex - 1;
                                if (index >= 0 && index < count)
                                {
                                    if (motorArray[index].getStatusM().equalsIgnoreCase("RENTED"))
                                    {
                                        System.out.println("\nSorry this motor is already rented.");                              
                                    }
                                    else
                                    {
                                        System.out.println("\nInformation about motor " + userInputIndex + ":");
                                        System.out.println(motorArray[index].toString());                       
                                        System.out.println("\nStart Date :");
                                        startD = inputText.nextLine();
                                        System.out.println("\nEnd Date :");
                                        endD = inputText.nextLine();
                                        System.out.println("\nCustomer name :");
                                        name = inputText.nextLine();
                                        System.out.println("\nCustomer phone Number :");
                                        phoneNum = inputText.nextLine();
                                        System.out.println("\nIC Number :");
                                        icNum = inputText.nextLine();
                                                
                                        rentalID = icNum.substring(8, 12);                         
                                        motorArray[index].setRentalID(rentalID);
                                        String rentalMessage = motorArray[index].rentVehicle(motorArray[index].getStatusM());
                                        System.out.println(rentalMessage);
                                                                                        
                                        motorArray[index].setStatusM("RENTED");                     
                                        veh[rentalIndex]= new Motor(motorArray[index].getMotorName(),motorArray[index].getMotorID(),
                                        motorArray[index].getMotorBrand(),motorArray[index].getMotorType(),motorArray[index].getStatusM(),
                                        motorArray[index].getMotorPrice(),rentalID,startD,endD,new Customer(name, phoneNum, icNum));
                                        rentalIndex++;
                                        
                                        motorArray[index]=new Motor(motorArray[index].getMotorName(),motorArray[index].getMotorID(),
                                        motorArray[index].getMotorBrand(),motorArray[index].getMotorType(),motorArray[index].getStatusM(),
                                        motorArray[index].getMotorPrice(),rentalID,startD,endD,new Customer(name, phoneNum, icNum));
                                                                                        
                                        System.out.println(motorArray[index].toString(rentalID, startD, endD, new Customer(name, phoneNum, icNum)));
                                        validInput=true;
                                                                                                
                                    }
                                }
                                else 
                                {  System.out.println("\nInvalid motor number. Please enter a number between 1 and " + count + ".");                      }
                            }while (!validInput);
                             }catch(EOFException eof){System.out.println("Problem: " + eof.getMessage());
                             }catch (FileNotFoundException e) {System.out.println("Problem: " + e.getMessage());
                             }catch (IOException ioe) {System.out.println("Problem: " + ioe.getMessage());}
                    }
                    
                    //Payment Process
                    System.out.println("\nHow many days for the rental?");
                    int day = inputNum.nextInt();
                    
                    System.out.println("\nDo you want to add helmet as accesorries?");
                    System.out.println("\n(Adding a helmet will cost RM 15 per day)");
                    System.out.println("[ 1 ] - yes");
                    System.out.println("[ 2 ] - no");
                    System.out.println("\nWould you like to proceed?");
                    int choiceHelmet=inputNum.nextInt();
                    double totalPrice = 0;
                    double helmetPrice=0;
                    if (choiceHelmet==1) // Additional Cost of Helmet
                    {
                        helmetPrice=15*day;
                        for (int i = 0; i < veh.length; i++) 
                        {
                            if (veh[i] instanceof Motor) 
                            {
                                Motor motor=(Motor) veh[i];
                                totalPrice = veh[i].calcTotalPrice(motor.getMotorPrice(), day);
                                totalPrice=totalPrice + helmetPrice;                            
                            }
                        }
                    }
                    else //no Additional Cost
                    {
                        for (int i = 0; i < veh.length; i++) 
                        {
                            if (veh[i] instanceof Motor) 
                            {
                                Motor motor=(Motor) veh[i];
                                totalPrice = veh[i].calcTotalPrice(motor.getMotorPrice(), day);
                                                            
                            }
                        }
                    }
                    System.out.println("\nThe total price after tax is : RM" + totalPrice); 
                }          
            }
            else if (choice == 2) //Return vehicle
            {
                    // Return vehicle
                    System.out.println("\nReturning a vehicle...");
                    System.out.println("\nChoose return :-");
                    System.out.println("\n1. Car ");
                    System.out.println("\n2. Motor");
                    int choiceReturn=inputNum.nextInt();
                    
                if (choiceReturn==1)  //Return Car
                {       
                    boolean validRentalID=false;
                    do
                    {
                        System.out.println("\nPlease enter your rentalID :");
                        rentalID=inputText.nextLine();
                        
                        for(int i=0;i<veh.length;i++)
                        {
                                        
                            if(veh[i]!=null && veh[i].getRentalID() !=null && veh[i].getRentalID().equals(rentalID))
                            {
                                    validRentalID=true;
                                                
                                    if(veh[i] instanceof Car)
                                    {
                                        Car car=(Car) veh[i];
                                        System.out.println(car.toString(rentalID, startD, endD, new Customer(name, phoneNum, icNum)));
                                        System.out.println("\nDoes the customer return the car on the same end date?");
                                        System.out.println("[ 1 ] - yes");
                                        System.out.println("[ 2 ] - no");
                                        int choiceDate=inputNum.nextInt();
                                        if (choiceDate==1) 
                                        {
                                            String returnMessage=car.returnVehicle(car.getStatusC());
                                            System.out.println(returnMessage);
                                            car.setStatusC("AVAILABLE");
                                            System.out.println(car.toString(rentalID, startD, endD, new Customer(name, phoneNum, icNum)));
                                            break;
                                        }
                                        else if (choiceDate==2) 
                                        {
                                            boolean paymentReceived=false;
                                            do
                                            {   System.out.println("\nEnter total exceeded day : ");
                                                int exDay=inputNum.nextInt();
                                                int exCharge=exDay*160;
                                                System.out.println("\nThe total fine is : RM "+exCharge); 
                                                System.out.println("\nPlease enter your bank account number : ");
                                                String bankAccNum=inputText.nextLine();
                                                System.out.println("\nPayment received?");
                                                System.out.println("[ 1 ] - yes");
                                                System.out.println("[ 2 ] - no");
                                                choicePayment=inputNum.nextInt();
                                                if (choicePayment==1) 
                                                {
                                                    String returnMessage=car.returnVehicle(car.getStatusC());
                                                    System.out.println(returnMessage);
                                                    car.setStatusC("AVAILABLE");
                                                    System.out.println(car.toString(rentalID, startD, endD, new Customer(name, phoneNum, icNum)));
                                                    break;
                                                }
                                                else if(choicePayment==2)
                                                {
                                                    System.out.println("\nPlease try again ");
                                                }
                                            }while(!paymentReceived); 
                                            break;
                                        }                       
                                    }
                                }
                            }
                        if(!validRentalID)
                        {
                            System.out.println("\nRental ID not found. Please try again.");
                        }
                    }while(!validRentalID);
                }
                else if ( choiceReturn == 2) //Return Motor
                {
                        //motor return
                        boolean validRentalID=false;
                        do
                        {
                            System.out.println("\nPlease enter your rentalID :");
                            rentalID=inputText.nextLine();
                            for(int i=0;i<veh.length;i++)
                            {
                                        
                                if(veh[i]!=null && veh[i].getRentalID() !=null && veh[i].getRentalID().equals(rentalID))
                                {
                                    validRentalID=true;
                                                
                                    if(veh[i] instanceof Motor)
                                    {
                                        Motor motor=(Motor) veh[i];
                                        System.out.println(motor.toString(rentalID, startD, endD, new Customer(name, phoneNum, icNum)));
                                        System.out.println("\nDoes the customer return the car on the same end date?");
                                        System.out.println("[ 1 ] - yes");
                                        System.out.println("[ 2 ] - no");
                                        int choiceDate=inputNum.nextInt();
                                        if (choiceDate==1)
                                        {
                                            String returnMessage=motor.returnVehicle(motor.getStatusM());
                                            System.out.println(returnMessage);
                                            motor.setStatusM("AVAILABLE");
                                            System.out.println(motor.toString(rentalID, startD, endD, new Customer(name, phoneNum, icNum)));
                                            break;
                                        }
                                        else if (choiceDate==2)
                                        {
                                            boolean paymentReceived=false;
                                            do
                                            {   System.out.println("\nEnter total exceeded day : ");
                                                int exDay=inputNum.nextInt();
                                                int exCharge=exDay*160;
                                                System.out.println("\nThe total fine is : RM "+exCharge); 
                                                System.out.println("\nPlease enter your bank account number : ");
                                                String bankAccNum=inputText.nextLine();
                                                System.out.println("\nPayment received?");
                                                System.out.println("[ 1 ] - yes");
                                                System.out.println("[ 2 ] - no");
                                                choicePayment=inputNum.nextInt();
                                                if (choicePayment==1)
                                                {
                                                    String returnMessage=motor.returnVehicle(motor.getStatusM());
                                                    System.out.println(returnMessage);
                                                    motor.setStatusM("AVAILABLE");
                                                    System.out.println(motor.toString(rentalID, startD, endD, new Customer(name, phoneNum, icNum)));
                                                    break;
                                                }
                                                else if(choicePayment==2)
                                                {
                                                    System.out.println("\nPlease try again ");
                                                }
                                            }while(!paymentReceived); 
                                            break;
                                        }                       
                                    }
                                }
                            }
                            if(!validRentalID)
                            {
                                System.out.println("\nRental ID not found. Please try again.");
                            }
                        }while(!validRentalID);
                }
            }
            else if(choice == 3) //Cancel Vehicle
            {
                    System.out.println("\nCanceling a vehicle...");
                    System.out.println("\nChoose return :-");
                    System.out.println("\n1. Car ");
                    System.out.println("\n2. Motor");
                    int choiceCancel=inputNum.nextInt();
                    
                if (choiceCancel==1) //Cancel car
                {       
                    boolean validRentalID=false;
                    do
                    {
                        System.out.println("\nPlease enter your rentalID :");
                        rentalID=inputText.nextLine();
                        
                        for(int i=0;i<veh.length;i++)
                        {
                                        
                            if(veh[i]!=null && veh[i].getRentalID() !=null && veh[i].getRentalID().equals(rentalID))
                            {
                                validRentalID=true;
                                                
                                if(veh[i] instanceof Car)
                                {
                                    Car car=(Car) veh[i];
                                    System.out.println(car.toString(rentalID, startD, endD, new Customer(name, phoneNum, icNum)));
                                    System.out.println("\nIs this information correct?");
                                    System.out.println("\n1. [ YES ]");
                                    System.out.println("\n2. [ NO ]");
                                    int choiceConfirmation=inputNum.nextInt();
                                    if (choiceConfirmation == 1)
                                    {
                                        String cancelMessage=car.cancelRental(car.getStatusC());
                                        System.out.println(cancelMessage);
                                        car.setStatusC("AVAILABLE");
                                        break;
                                        
                                    }

                                }
                                                      
                            }
                        }   
                        if(!validRentalID)
                        {
                            System.out.println("\nRental ID not found. Please try again.");
                        }
                    }while(!validRentalID);
                }
                else if( choiceCancel == 2)//cancel Motor
                {
        
                        boolean validRentalID=false;
                        do
                        {
                            System.out.println("\nPlease enter your rentalID :");
                            rentalID=inputText.nextLine();
                            for(int i=0;i<veh.length;i++)
                            {
                                        
                                if(veh[i]!=null && veh[i].getRentalID() !=null && veh[i].getRentalID().equals(rentalID))
                                {
                                    validRentalID=true;
                                                
                                    if(veh[i] instanceof Motor)
                                    {
                                        Motor motor=(Motor) veh[i];
                                        System.out.println(motor.toString(rentalID, startD, endD, new Customer(name, phoneNum, icNum)));
                                        System.out.println("\nIs this information correct?");
                                        System.out.println("\n1. [ YES ]");
                                        System.out.println("\n2. [ NO ]");
                                        int choiceConfirmation=inputNum.nextInt();
                                        if (choiceConfirmation == 1)
                                        {
                                            String cancelMessage=motor.cancelRental(motor.getStatusM());
                                            System.out.println(cancelMessage);
                                            motor.setStatusM("AVAILABLE");
                                            break;
                                        }

                                    }
                                }
                            }
                            if(!validRentalID)
                            {
                                System.out.println("\nRental ID not found. Please try again.");
                            }
                        }while(!validRentalID);
                }  
            }
            System.out.println("\n--------------------------------------------");
            System.out.println("\nGo back to main menu? :");
            System.out.println("\n1-[YES]");
            System.out.println("\n2-[NO]");
            System.out.println("\nBack to main menu : ");
            menu = inputNum.nextInt();
            System.out.println("\n--------------------------------------------");
 
        }while (menu == 1);
        System.out.println("\nThank you for using our system");
        
    }
}
        
