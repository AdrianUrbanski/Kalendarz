import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

class MeetingScheduler {
	private LinkedList<Pair> timeline = new LinkedList<>();

	void scheduleMeeting(LinkedList<Calendar> profiles, Date date, int ID){
		Scanner in = new Scanner(System.in);
		if(!collectEvents(profiles, date))
			return;
		CalendarMenu.clearScreen();
		LinkedList<Interval> freeTime = findFreeTime();
		System.out.println(date);
		System.out.println("Possible time for a meeting:");
		for(Interval i : freeTime)
			System.out.println(i);
		System.out.println("\nDo you want to schedule a meeting that day? (true/false)");
	boolean b = in.nextBoolean();
		in.nextLine();
		if(!b)
			return;
		System.out.println("Input time of the meeting (as integers)");
		int hour = in.nextInt();
		int minute = in.nextInt();
		System.out.println("Input length of the meeting (in minutes)");
		int length = in.nextInt();
		Meeting m = new Meeting(ID, new Time(hour, minute), length);
		for(Calendar profile : profiles){
			profile.addEvent(date, m);
		}
	}

	private boolean collectEvents(LinkedList<Calendar> profiles, Date date){
		Scanner in = new Scanner(System.in);
		Calendar current;
		Day day;
		LinkedList <TimedEvent> events;
		Iterator<Calendar> it = profiles.listIterator();
		boolean busyDay = false;
		while(it.hasNext()){
			current = it.next();
			day = current.getDay(date);
			if(day == null)
				continue;
			if(day.isBusy()) {
				System.out.println(current.getProfileName() + " has this day marked as busy");
				busyDay = true;
			}
		}
		if(busyDay) {
			System.out.println();
			System.out.println("Press ENTER to continue");
			in.nextLine();
			return false;
		}
		it = profiles.listIterator();
		while(it.hasNext()){
			current = it.next();
			day = current.getDay(date);
			if(day == null)
				continue;
			events = day.getTimedEvents();
			for(TimedEvent e : events){
				timeline.add(new Pair(e.getTime(), false));
				timeline.add(new Pair(e.getEnd(), true));
			}
		}
		timeline.sort(Comparator.comparing(Pair::getTime));
		return true;
	}

	private LinkedList<Interval> findFreeTime(){
		LinkedList<Interval> freeTime = new LinkedList<>();
		Time prev, next= new Time(0, 0);
		int cnt = 0;
		for(Pair current : timeline) {
			prev = next;
			next = current.getTime();
			if(cnt == 0 && !prev.equals(next)){
				freeTime.add(new Interval(prev, next));
			}
			if(current.end)
				cnt--;
			else
				cnt++;
		}
		if(!next.equals(new Time(24, 0)))
			freeTime.add(new Interval(next, new Time(24, 0)));
		return freeTime;
	}

	private class Pair{
		Time time;
		boolean end;

		Pair(Time time, boolean end) {
			this.time = time;
			this.end = end;
		}

		Time getTime() {
			return time;
		}
	}

	private class Interval{
		Time start, end;

		Interval(Time start, Time end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return start + " - " + end;
		}
	}
}
