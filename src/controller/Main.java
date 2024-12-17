package controller;
import java.util.Scanner;

import barrier.BarrierControl;
import store.*;
import personnel.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize BarrierControl and CentralComputer with Singleton pattern
        BarrierControl barrierControl = new BarrierControl();
        CentralComputer centralComputer = CentralComputer.getInstance(barrierControl);
        
        // Initialize PeopleCounter and register CentralComputer as an observer
        PeopleCounter peopleCounter = new PeopleCounter();
        peopleCounter.addObserver(centralComputer);
        
        // Initialize CardReader
        CardReader cardReader = new CardReader();
        
        System.out.println("------------------------------------------------------------------");
        System.out.println("-------------Visual people counting and barrier system------------");
        System.out.println("------------------------------------------------------------------");
        
        System.out.println("\n----------------------------Current Status------------------------");
        System.out.println("");
        // Sets the initial maximum store capacity in CentralComputer and StoreManager
        int initialCapacity = 5;
        centralComputer.setMaxCapacity(initialCapacity); // Set in CentralComputer for barrier control
        StoreManager manager = new StoreManager();
        manager.setMaxCapacity(initialCapacity);

        int employeeCount = 0;

        while (true) {
            // Display menu options
        	System.out.println("\n------------------------------------------------------------------");
            System.out.println("\nChoose an action:");
            System.out.println("1. Enter Manager mode");
            System.out.println("2. Enter Customer");
            System.out.println("3. Enter Employee");
            System.out.println("4. Exit Customer");
            System.out.println("5. Exit Employee");
            System.out.println("6. Exit Program");
            System.out.println("\n------------------------------------------------------------------");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
            case 1:
            	
            	try {
            		System.out.println("\nVerifying Access..........");
					Thread.sleep(1000);// Delay for 1 second
					System.out.println("Entering manager mode........");
					Thread.sleep(1000);// Delay for 1 second
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				} 
            	
            	
            	boolean inManagerMode = true;
                
            	while (inManagerMode) {
                	System.out.print("\nChoose an action: ");
    	            System.out.println("\n1. View Current Store Limit");
    	            System.out.println("2. Change Store Limit");
    	            System.out.println("3. View Current Customer Count");
    	            System.out.println("4. View Current Employee Count");
    	            System.out.println("5. Exit manager mode");
    	            System.out.println("\n------------------------------------------------------------------");

    	            int managerChoice = scanner.nextInt();
	                	switch(managerChoice) {
	                    case 1: // View Current Store Limit
	                        System.out.println("Current store limit: " + manager.getMaxCapacity());
	                        break;
	
	                    case 2: // Change Store Limit
	                        System.out.print("Enter new maximum capacity: ");
	                        int newCapacity = scanner.nextInt();
	                        manager.setMaxCapacity(newCapacity);
	                        centralComputer.setMaxCapacity(newCapacity); // Reflect capacity change in CentralComputer
	                        break;
	                        
	                    case 3: // View Current Customer Count
	                        System.out.println("Current customer count: " + peopleCounter.getCurrentCustomerCount());
	                        break;

	                    case 4: // View Current Employee Count
	                        System.out.println("Current employee count: " + employeeCount);
	                        break;
	                        
		                 case 5: // Return to Main Menu
		                	 
		                     inManagerMode = false; // Exit the manager mode loop
		                     break;
	
		                 default:
		                      System.out.println("Invalid option in Manager Mode.");
		                 }
	                	
	            }
            	
            	break;
                

                case 2: // Enter Customer
                    if (barrierControl.isBarrierOpen() || peopleCounter.getCurrentCustomerCount()< centralComputer.getMaxCapacity()) {
                    	System.out.println("Customer enters...");
                        peopleCounter.detectEntry(); // Track entry through PeopleCounter
                        System.out.println("Current customer count: " + peopleCounter.getCurrentCustomerCount());
                    } else {
                        System.out.println("Cannot enter: Entry Barrier is closed due to capacity limit.");
                    }
                    break;

                case 3: // Enter Employee
                    
                    String employeeId = "CARD";
                    Employee employee = new Employee(employeeId, "CARD");
                    
				 
                    if (cardReader.verifyPersonnel(employee)) {
                    	try {
        					Thread.sleep(1000);
        				
                        barrierControl.temporaryOpenBarrier();
                        System.out.println("Employee entering.");
                        employeeCount++;
                        System.out.println("Current employee count: " + employeeCount);
                    }catch (InterruptedException e) {
    					
    					e.printStackTrace();
    				}}
                         else {
                        System.out.println("Employee verification failed.");
                    }
                    break;

                case 4: // Exit Customer
                    
                    if (peopleCounter.getCurrentCustomerCount() < 1) {
                    	System.out.println("No customers in the store.");
                        
                    } else {
                    	
                    	peopleCounter.detectExit();
                    	System.out.println("Customer exiting...");
                    	System.out.println("Current customer count: " + peopleCounter.getCurrentCustomerCount());
                    }
                    break;

                case 5: // Exit Employee
                	if (employeeCount < 1) {
                    	System.out.println("No Employee inside the store.");
                    	break;
                    }
                    
                    String exitingEmployeeId = "CARD";
                    Employee exitingEmployee = new Employee(exitingEmployeeId, "CARD");
                    
                    if (cardReader.verifyPersonnel(exitingEmployee)) {
                    	try {
        					Thread.sleep(1000);
                        barrierControl.temporaryOpenBarrier();
                        System.out.println("Employee exiting.");
                        if (employeeCount > 0) {
                            employeeCount--;
                        }
                        System.out.println("Current employee count: " + employeeCount);
                    	}catch (InterruptedException e) {
        					
        					e.printStackTrace();
        				}} else {
                        System.out.println("Employee verification failed.");
                    }
                    break;

                case 6: // Exit Program
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
