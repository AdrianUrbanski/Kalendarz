import java.util.*;

// facade
public class CalendarMenu {
	private LinkedList<Calendar> profiles = new LinkedList<>();
	private Calendar current = new Calendar("default");

	private int IDCount=0;

	CalendarMenu() {
		profiles.add(current);
	}


	void menu(){
		Scanner in = new Scanner(System.in);
		int option;
		int day, month, year;
		String name;
		while(true) {
			clearScreen();
			System.out.println("Current profile: " + current.getProfileName());
			System.out.println("Input option number");
			System.out.println("1. Display month");
			System.out.println("2. Display and remove events");
			System.out.println("3. Add event");
			System.out.println("4. Schedule meeting");
			System.out.println("5. Change profile");
			System.out.println("0. Exit");
			option = in.nextInt();
			in.nextLine();
			switch (option){
				case 0:
					return;
				case 1:
					System.out.println("Input month and year (as integers)");
					month = in.nextInt();
					year = in.nextInt();
					in.nextLine();
					displayMonth(month, year);
					break;
				case 2:
					System.out.println("Input day, month and year (as integers).");
					day = in.nextInt();
					month = in.nextInt();
					year = in.nextInt();
					in.nextLine();
					displayEvents(new Date(day, month, year));
					break;
				case 3:
					System.out.println("Input day, month and year (as integers).");
					day = in.nextInt();
					month = in.nextInt();
					year = in.nextInt();
					in.nextLine();
					addEvent(new Date(day, month, year));
					break;
				case 4:
					LinkedList<Calendar> prof = new LinkedList<>();
					System.out.println("Input day, month and year of the meeting (as integers).");
					day = in.nextInt();
					month = in.nextInt();
					year = in.nextInt();
					System.out.println("Input number of profiles that will take part in a meeting.");
					int num = in.nextInt();
					in.nextLine();
					System.out.println("Now input names of these profiles (separated by enter).");
					for(int i=0; i<num; i++){
						name = in.nextLine();
						prof.add(getProfile(name));
					}
					IDCount++;
					new MeetingScheduler().scheduleMeeting(prof, new Date(day, month, year), IDCount);
					break;
				case 5:
					System.out.println("Input profile name");
					name = in.nextLine();
					changeProfile(name);
					break;
				default:
					break;
			}

		}
	}

	static void clearScreen() {
		// As suggested in:
		// https://stackoverflow.com/questions/4888362/commands-in-java-to-clear-the-screen
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private void displayMonth(int month, int year){
		clearScreen();
		Scanner in = new Scanner(System.in);
		int position = Date.getFirstDayOfWeek(month, year);
		System.out.println("  " + Date.monthName(month) + " " + year);
		System.out.println();
		System.out.println("  Mon  Tue  Wed  Thu  Fri  Sat  Sun");
		for(int i=1; i < position; i++)
			System.out.print("     ");
		for(int i=1; i <= Date.monthLength(month, year); i++){
			Date key = new Date(i, month, year);
			Day day = current.getDay(key);
			if(day!=null) {
				System.out.printf(day.color() + " %3d " + Day.ANSI_RESET, i);
			}
			else
				System.out.printf(Day.ANSI_GREEN + " %3d " + Day.ANSI_RESET, i);
			if((i+position)%7==1 || i == Date.monthLength(month, year))
				System.out.println();
		}
		System.out.println();
		System.out.println("Press ENTER to continue.");
		in.nextLine();
	}

	private void displayEvents(Date date){
		int ID=1;
		Scanner in = new Scanner(System.in);
		while(ID>0) {
			clearScreen();
			Day day = current.getDay(date);
			System.out.println(date);
			System.out.println();
			System.out.println("All day events:");
			day.printAllDayEvents();
			System.out.println();
			System.out.println("Timed events:");
			day.printTimedEvents();
			System.out.println();
			System.out.println("If you want to remove an event, input it's ID.");
			System.out.println("Otherwise, input 0.");
			ID = in.nextInt();
			in.nextLine();
			if (ID == 0)
				return;
			if(!current.removeEvent(date, ID))
				System.out.println("Event not found.");
		}
	}

	//factory
	private void addEvent(Date date){
		clearScreen();
		String name;
		int length;
		boolean busy;
		int hour, minute;
		Scanner in = new Scanner(System.in);
		IDCount++;
		System.out.println("Choose event");
		System.out.println("All day events:");
		System.out.println("1. Birthday");
		System.out.println("2. Vacation");
		System.out.println("3. Custom event");
		System.out.println("Timed events:");
		System.out.println("4. Appointment");
		System.out.println("5. Custom event");
		int option = in.nextInt();
		in.nextLine();
		CalendarMenu.clearScreen();
		switch(option){
			case 1:
				System.out.println("Input name of a person having birthday.");
				name = in.nextLine();
				current.addEvent(date, new Birthday(IDCount, name ));
				break;
			case 2:
				System.out.println("Input length of the vacation (in days).");
				length = in.nextInt();
				in.nextLine();
				current.addEvent(date, new Vacation(IDCount, length));
				break;
			case 3:
				System.out.println("Input name of the event.");
				name = in.nextLine();
				System.out.println("Input length of the event (in days).");
				length = in.nextInt();
				in.nextLine();
				System.out.println("Will you be busy during the event? (true / false)");
				busy = in.nextBoolean();
				in.nextLine();
				current.addEvent(date, new CustomWholeDayEvent(IDCount, name, length, busy));
				break;
			case 4:
				System.out.println("Input time of the appointment (as two integers).");
				hour = in.nextInt();
				minute = in.nextInt();
				in.nextLine();
				System.out.println("Input length of the appoinment (in minutes).");
				length = in.nextInt();
				in.nextLine();
				current.addEvent(date, new Appointment(IDCount, new Time(hour, minute), length));
				break;
			case 5:
				System.out.println("Input name of the event.");
				name = in.nextLine();
				System.out.println("Input time of the event (as two integers).");
				hour = in.nextInt();
				minute = in.nextInt();
				System.out.println("Input length of the event (in minutes).");
				length = in.nextInt();
				in.next();
				System.out.println("Will you be busy during the event? (true/ falase)");
				busy = in.nextBoolean();
				in.next();
				current.addEvent(date, new CustomTimedEvent(IDCount, name, new Time(hour, minute), length, busy));
				break;
			default:
				System.out.println("No such type of event.");
		}
	}

	private void changeProfile(String name){
		for(Calendar profile : profiles){
			if(profile.getProfileName().equals(name)) {
				current = profile;
				return;
			}
		}
		current = new Calendar(name);
		profiles.add(current);
	}

	private Calendar getProfile(String name){
		for(Calendar profile : profiles){
			if(profile.getProfileName().equals(name))
				return profile;
		}
		Calendar result = new Calendar(name);
		profiles.add(result);
		return result;
	}
}
