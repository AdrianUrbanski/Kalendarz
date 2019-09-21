import java.util.HashMap;

class Calendar{

	private String profileName;
	private HashMap<Date, Day> calendar;

	Calendar(String profileName) {
		this.profileName = profileName;
		calendar = new HashMap<>();
	}

	String getProfileName() {
		return profileName;
	}

	Day getDay(Date key){
		return calendar.get(key);
	}

	void addEvent(Date d, Event e){
		int length = 1;
		Day temp;
		if(e.isWholeDay()){
			WholeDayEvent t = (WholeDayEvent) e;
			length = t.getLength();
		}
		for(int i=0; i<length; i++) {
			if(calendar.containsKey(d))
				temp = calendar.get(d);
			else
				temp = new Day();
			temp.addEvent(e);
			calendar.put(d, temp);
			d = Date.nextDay(d);
		}
	}

	boolean removeEvent(Date d, int ID){
		return calendar.get(d).removeEvent(ID);
	}


}
