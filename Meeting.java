public class Meeting extends TimedEvent{
	Meeting(int ID, Time time, int length) {
		super(ID, time, length);
	}

	@Override
	public String toString() {
		return getTime() + " - " + getEnd() + " Meeting. ID: " + getID();
	}

}
