import java.util.*;


class Day {
	// As suggested in:
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	static final String ANSI_RESET = "\u001B[0m";
	static final String ANSI_RED = "\u001B[31m";
	static final String ANSI_GREEN = "\u001B[32m";
	static final String ANSI_YELLOW = "\u001B[33m";

	private LinkedList<WholeDayEvent> wholeDayEvents = new LinkedList<>();
	private LinkedList<TimedEvent> timedEvents = new LinkedList<>();

	LinkedList<TimedEvent> getTimedEvents() {
		cleanup();
		return timedEvents;
	}

	private int color[] = new int[2];

	boolean addEvent(Event e){
		TimedEvent prev, next = null;
		if(e.isWholeDay()) {
			if (e.isBusy())
				color[1]++;
			return wholeDayEvents.add((WholeDayEvent) e);
		}
		else{
			if(e.isBusy())
				color[0]++;
			if(timedEvents.isEmpty())
				return timedEvents.add((TimedEvent) e);
			ListIterator<TimedEvent> it = timedEvents.listIterator();
			Time start = ((TimedEvent) e).getTime();
			Time end = start.increase(((TimedEvent) e).getLength());
			while(it.hasNext()){
				prev = next;
				next=it.next();
				if(end.compareTo(next.getTime()) < 0){
					if(it.hasPrevious())
						it.previous();
					if(prev==null || start.compareTo(prev.getEnd()) > 0) {
						it.add((TimedEvent) e);
						return true;
					}
					else
						return false;
				}
			}
			prev = it.previous();
			if (start.compareTo(prev.getEnd()) > 0) {
				return timedEvents.add((TimedEvent) e);
			}
			return false;
		}
	}

	boolean removeEvent(int ID){
		for(WholeDayEvent e : wholeDayEvents){
			if(e.getID() == ID){
				e.deactivate();
				return true;
			}
		}

		for(TimedEvent e : timedEvents){
			if(e.getID() == ID) {
				e.deactivate();
				return true;
			}
		}
		return false;
	}

	private void cleanup(){
		Iterator<WholeDayEvent> itA = wholeDayEvents.iterator();
		while(itA.hasNext()){
			Event e = itA.next();
			if(e.isNotActive()){
				if(e.isBusy()){
					if(e.isWholeDay())
						color[1]--;
					else
						color[0]--;
				}
				itA.remove();
			}
		}
		Iterator<TimedEvent> itT = timedEvents.iterator();
		while(itT.hasNext()){
			Event e = itT.next();
			if(e.isNotActive()){
				if(e.isBusy()){
					if(e.isWholeDay())
						color[1]--;
					else
						color[0]--;
				}
				itT.remove();
			}
		}
	}

	boolean isBusy(){
		return (color[1]>0);
	}

	String color(){
		cleanup();
		if(color[1]>0)
			return ANSI_RED;
		if(color[0]>0)
			return ANSI_YELLOW;
		return ANSI_GREEN;
	}

	void printAllDayEvents(){
		cleanup();
		for(WholeDayEvent e : wholeDayEvents)
			System.out.println(e);
	}

	void printTimedEvents() {
		cleanup();
		for(TimedEvent e : timedEvents)
			System.out.println(e);
	}
}
