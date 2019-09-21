abstract class TimedEvent extends Event implements Comparable<TimedEvent>{
	private Time time;
	private int length;

	TimedEvent(int ID, Time time, int length) {
		super(ID);
		this.time = time;
		this.length = length;
	}

	int getLength() {
		return length;
	}

	Time getTime() {
		return time;
	}

	Time getEnd(){
		return time.increase(length);
	}

	@Override
	boolean isWholeDay() {
		return false;
	}

	@Override
	public int compareTo(TimedEvent timedEvent) {
		return this.getTime().compareTo(timedEvent.getTime());
	}
}
