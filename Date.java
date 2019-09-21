import java.time.LocalDate;

public class Date {
	private int day;
	private int month;
	private int year;

	Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	private int getDay() {
		return day;
	}

	private int getMonth() {
		return month;
	}

	private int getYear() {
		return year;
	}

	private void setDay(int day) {
		this.day = day;
	}

	private void setMonth(int month) {
		this.month = month;
	}

	private void setYear(int year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		return (year-2000)*365 + month*31 + day;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		Date d = (Date) obj;
		return (d.getDay() == this.getDay() &&
				d.getMonth() == this.getMonth() &&
				d.getYear() == this.getYear());
	}

	static Date nextDay(Date d){
		Date temp = new Date(d.getDay()+1, d.getMonth(), d.getYear());
		if(temp.getDay() > temp.getMonthLength()) {
			temp.setDay(1);
			temp.setMonth(temp.getMonth()+1);
			if(temp.getMonth()==13) {
				temp.setMonth(1);
				temp.setYear(temp.getYear()+1);
			}
		}
		return temp;
	}

	private int getMonthLength(){
		return monthLength(month, year);
	}

	// potrzebne do formatowania
	static int getFirstDayOfWeek(int month, int year){
		return LocalDate.of(year, month, 1).getDayOfWeek().getValue();
	}

	@Override
	public String toString() {
		return day + " " + monthName(month) + " " + year;
	}

	static int monthLength(int month, int year){
		switch(month){
			case 1:
				return 31;
			case 2:
				if((year%4==0 && year%100!=0) || year%400==0)
					return 29;
				return 28;
			case 3:
				return 31;
			case 4:
				return 30;
			case 5:
				return 31;
			case 6:
				return 30;
			case 7:
				return 31;
			case 8:
				return 31;
			case 9:
				return 30;
			case 10:
				return 31;
			case 11:
				return 30;
			case 12:
				return 31;
			default:
				return -1;
		}
	}

	static String monthName(int month){
		switch(month){
			case 1:
				return "January";
			case 2:
				return "February";
			case 3:
				return "March";
			case 4:
				return "April";
			case 5:
				return "May";
			case 6:
				return "June";
			case 7:
				return "July";
			case 8:
				return "August";
			case 9:
				return "September";
			case 10:
				return "October";
			case 11:
				return "November";
			case 12:
				return "December";
			default:
				return null;
		}
	}

}
