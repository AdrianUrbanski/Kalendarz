public class CustomTimedEvent extends TimedEvent {

	private String eventName;

	CustomTimedEvent(int ID, String name, Time time, int length, boolean busy) {
		super(ID, time, length);
		this.eventName = name;
		this.setBusy(busy);
	}

	@Override
	public String toString() {
		return getTime() + " - " + getEnd() + " " + eventName + " ID: " + getID();
	}

}
