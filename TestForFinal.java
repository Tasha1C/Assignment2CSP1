


import java.util.Scanner;

public class TestForFinal {
	Scanner input = new Scanner(System.in);
	// your arrays and tracker (kept names exactly)
	String Danceclass[] = { "Irish style  ", "Hip-Hop style", "Kizomba style", "Tango style  ", "Ballet style " };
	double Fees[] = { 10.0, 20.0, 50.0, 30.0, 100.0 };
	int registrations[][] = new int[15][5];
	int registrationTracker = 0;

	// main menu 

	public void MainMenu() {



		int choice = 0;

		while (choice !=5) {
			System.out.println("This is the registration program for Mirabell Dance Studio. what would you like to do?\n (enter integer numbr between 1-5)\n\n ");
			System.out.println("1 Enter a new registration");
			System.out.println("2 Modify ONE registration");
			System.out.println("3 Delete ONE registration");
			System.out.println("4 Display ALL registrations");
			System.out.println("5 End program");
			System.out.print("Choose one option: ");

			String option = input.nextLine();

			if (option.equals("1")) {
				addRegistration();
			} else if (option.equals("2")) {
				DanceBooking();
			} else if (option.equals("3")) {
				deleteRegistration();
			} else if (option.equals("4")) {
				displayAllRegistrations();
			} else if (option.equals("5")) {
				System.out.println("Endprogram. ");
				break;
			} else {
				System.out.println("\n\nyou have entered an invalid statment. Please choose one of the options (1-5.)\n");
			}
		}
	}

	//PART A addRegistration Tasha Toroitich 
	public void addRegistration() {


		//this checks if the maximum registrations of 15 has been reached. if so,the program stops and returns a message notifying the user 
		if (registrationTracker >= 15) {
			System.out.println("You have reached the maximum registrations for the day");
			return ;
		} else {
			//if not a new registration can be made and is assigned a number
			int registrationNo = registrationTracker + 1;
			System.out.println("\n||this is registration number:||\n " + registrationNo);
		}

		//all the available dance classes are displayed along with their fees.
		System.out.println("these are the dance classes available and their prices per session");
		for (int y = 0; y < Danceclass.length; y++) {
			System.out.printf(y+1 + " " + Danceclass[y]+" =    RM%.2f\n", Fees[y]);
		}

		//an array is created in order to store the participants of each dance class
		int[] Customers = new int[5];
		//the loop goes through each dance class recoring the number of participants
		for (int y = 0; y < Danceclass.length; y++) {
			int participants=-1;

			//this is to ensure that a valid number is entered and asks the user until the input matches the requirements
			do {
				System.out.print("Enter participants for " + Danceclass[y] + ": ");
				if (input.hasNextInt()) {
					participants = input.nextInt();
					//rejects any number below 0
					if (participants < 0) {
						System.out.println("that is not a valid input. you must enter an integer that is zero or higher. try again");
						participants = -1; 
					}
				} else {
					// rejects anything that is not a number
					System.out.println("Invalid. you are only allowed to enter integers. try again");
					input.nextLine(); 
				}

			} while (participants < 0);
			
			// storage for the valid numbers entered 
			Customers[y] = participants;

		}


		//the total amount to be payed is then calculated by multiplying the total participants in a clas by the price 
		double totalPrice = 0;
		for (int y = 0; y < 5; y++) {
			totalPrice += Customers[y] * Fees[y];
		}
		System.out.printf("Total price: RM%.2f\n", totalPrice);

		//the user is asked to enter the amount payed and the program checks that it covers the total price
		double payment = -1;


		do {
			System.out.print("Enter the amount payable.  RM");

			if (input.hasNextDouble()) {
				payment = input.nextDouble();
				input.nextLine();

				 // the program ensure that the payment is at least equal to the total owed
				if (payment < totalPrice) {
					System.out.printf("You entered " +payment+". Please enter at least RM%.2f.%n",  totalPrice);
					payment = -1;

				}
			} else {
				// the program also checks that an integer has been entered 
				System.out.printf("Invalid. Please enter a number. the amount owed is RM%.2f.%n",totalPrice);
				input.nextLine(); 
			}

		} while (payment < 0);

// the change(if any) is calculated and displayed 
		double change = payment - totalPrice;
		System.out.printf("thankyou, the change will be RM%.2f\n", change);

		//the tracker is used to save the registrations made and its added by 1 each time a registration is made
		registrations[registrationTracker] = Customers;
		registrationTracker++;
// after a successful registration is made the system ask the user if they'd like to make another registration and the whole process repeats
		System.out.println("Successfully registered the customer.Press enter to register the next customer");

		input.nextLine();
	}


	//   PART B 
	public void DanceBooking() {



		//  6.1
		if (registrationTracker == 0) {
			System.out.println(" there are currently No registrations");
			return;
		}

		//  6.2
		System.out.println("bellow are the current registrations");
		for (int slots = 0; slots < registrationTracker; slots++) {
			System.out.println("||"+ (slots + 1) + "|| Registration slot");
		}

		//  6.3
		int RegistrationNumber = -1;
		boolean Selection = false;

		while (!Selection) { 
			System.out.print("Please enter the registration number you wish to modify ");
			String registration = input.nextLine();


			//  6.5
			boolean validation = true;
			for (int c = 0; c < registration.length(); c++) {
				if (!Character.isDigit(registration.charAt(c))) {
					validation = false;
					break;
				}
			}

			if (validation && registration.length() > 0) {
				RegistrationNumber = Integer.parseInt(registration);


				//  Check if registration exists
				if (RegistrationNumber >= 1 && RegistrationNumber <= registrationTracker) {
					Selection = true;
				} else {
					System.out.println("Sorry, that registration number does not exist.");
				}

			} else {
				System.out.println("Please enter a whole number.");
			}
		}

		int chosenvalue = RegistrationNumber - 1;

		// 6.4
		System.out.println("\nEditing Registration number" + RegistrationNumber);

		double PreviousTotal = 0;
		for (int styleIndex = 0; styleIndex < 5; styleIndex++) {
			int prevQty = registrations[chosenvalue][styleIndex];
			System.out.println(Danceclass[styleIndex] + " : " + prevQty);
			PreviousTotal += prevQty * Fees[styleIndex];
		}
		System.out.printf("Old Total: RM%.2f%n", PreviousTotal);

		// Enter new quantities 
		int[] updatedvalue = new int[5];

		for (int styleIndex = 0; styleIndex < 5; styleIndex++) {

			while (true) {
				System.out.print("New entry for " + Danceclass[styleIndex] + "(empty to leave unchanged) ");
				String qtyInput = input.nextLine();

				// keep old if empty
				if (qtyInput.equals("")) {
					updatedvalue[styleIndex] = registrations[chosenvalue][styleIndex];
					break;
				}

				//  only positive numbers
				boolean qtyIsNum = true;

				for (int x = 0; x < qtyInput.length(); x++) {
					if (!Character.isDigit(qtyInput.charAt(x))) {
						qtyIsNum = false;
						break;
					}
				}

				if (qtyIsNum) {
					updatedvalue[styleIndex] = Integer.parseInt(qtyInput);
					break;
				} else {
					System.out.println("Please enter a valid non-negative number.");
				}
			}
		}

		// 6.5
		double newTotal = 0;
		for (int styleIndex = 0; styleIndex < 5; styleIndex++) {
			newTotal += updatedvalue[styleIndex] * Fees[styleIndex];
		}
		System.out.printf("New Total: RM%.2f%n", newTotal);

		// 6.6
		if (newTotal > PreviousTotal) {

			double extraPayment = newTotal - PreviousTotal;
			System.out.printf("Extra payment amount: RM%.2f%n", extraPayment);

			while (true) {
				System.out.print("Enter payment amount: RM");
				String payment = input.nextLine();

				boolean validPayment = true;
				int dotCounter = 0;

				for (int i = 0; i < payment.length(); i++) {
					char ch = payment.charAt(i);

					if (ch == '.') {
						dotCounter++;
						if (dotCounter > 1) {
							validPayment = false;
							break;
						}
					} else if (!Character.isDigit(ch)) {
						validPayment = false;
						break;
					}
				}

				if (validPayment && payment.length() > 0) {

					double payAmount = Double.parseDouble(payment);

					if (payAmount < extraPayment) {
						System.out.println("Insufficient payment.");
					} else {
						double change = payAmount - extraPayment;
						System.out.printf("Change: RM%.2f%n", change);
						break;
					}

				} else {
					System.out.println("enter valid payment amount.");
				}
			}

			// (ii)
		} else if (newTotal < PreviousTotal) {
			System.out.println("No refund provided.");
			// (iii)
		} else {
			System.out.println("Total unchanged.");
		}

		// 6.7
		for (int styleIndex = 0; styleIndex < 5; styleIndex++) {
			registrations[chosenvalue][styleIndex] = updatedvalue[styleIndex];
		}

		System.out.println("Registration updated successfully.");
	}


	
	// Deleting orders Ronah Katebe 25050105
    
    public void deleteRegistration() {
      
	//checking for existing orders
  
    if(registrationTracker == 0) {
    	System.out.println("Registration number could not be found. There is nothing to delete");
    	System.out.println("You will now return to main menu.");
    	return;    
    }
    
	//Ask user to enter a registration number
    System.out.println("Enter your registration number:"); 
    int registrationNum = input.nextInt();
    
    
    //search for registration number in the array
    boolean findRegistration = false;
    int registrationDelete =-1;
    for (int i = 0; i < registrationTracker; i++) {
    	if(registrationNum == i + 1) {
    		findRegistration =true;
    		registrationDelete = i ;
    		break;
    	}		
    }		
    
    if (!findRegistration) {
    	System.out.println("Registration number has not been found.");
        System.out.println("You will now return to main menu.");
    return;
    }
    
    		//ask user if they want to confirm deleting registration number
    		int confirmation =0;
    		while (confirmation != 1 && confirmation !=2) {
    		System.out.println("Please confirm to delete registration number? " + registrationNum);
    		System.out.println("1. Enter (1) to delete registration number");
    		System.out.println("2. Enter (2) to keep registration number");
    		int userVerify = input.nextInt();
    		
    		//confirm user's choice
    		if(userVerify ==1 || userVerify ==2) {
    			confirmation = userVerify;
    		}
    		else {
    			System.out.println("You have entered an invalid input. Please enter 1 or 2.");
    		}
    	}	
    		    		
    		if (confirmation ==2) {
    			System.out.println("You have confirmed cancellation of deleting registration number.");
    			System.out.println("You will now return to Mirabel's dance studio's main menu.");
    			return;
    		}
    		
    		//performing and displaying deletion		
    			for (int i = indexDelete; i < registrationTracker -1; i++) {
	    			registrations[i] = registrations[i + 1];
    		     }
    			
    			for(int j=0; j < registrations[0].length; j++) {
    				registrations[registrationTracker -1][j] =0;
    			}
    			
	    			registrationTracker--;
	    			System.out.println("Your registeration number has successfully been deleted");
		    		System.out.println("You will now return to the Mirabel's dance studio's main menu.");
		    
    }	    			

	    			

// Display all registrations
public void displayAllRegistrations() {
    if (registrationTracker == 0) {
        System.out.println("No registrations to display. Returning to main menu.");
        return;
    }
  //display listing
    System.out.println("Welcome to Mirabell Dance Studio! <3");
    System.out.println(" <3   ||below is the registration list for Mirabell Dance Studio! <3  || ");
    System.out.println("Registration number");
   
    for (int m = 0; m <5; m++) {
        System.out.print(Danceclass[m] + "\t");
    }

    System.out.println("total price");
    
    for (int m = 0; m < Fees.length; m++) {
        System.out.printf("\tRM%.2f      \t   ", Fees[m]);
    }
    System.out.println();
    double cumulativeTotal = 0;
    for (int q = 0; q < registrationTracker; q++) {
        System.out.print((q + 1) + " ");
        double rowTotal = 0;
        for (int c = 0; c < Danceclass.length; c++) {
            System.out.print(registrations[q][c]+"                  \t");
            rowTotal += registrations[q][c] * Fees[c];
        }
        System.out.printf("RM%.2f%n", rowTotal);
        cumulativeTotal += rowTotal;
    }

    System.out.printf(" the cumilative total is RM%.2f%n", cumulativeTotal);

    
   
  
}


	public static void main(String[] args) {
		TestForFinal system = new TestForFinal();   
		system.MainMenu(); 

	}
}






