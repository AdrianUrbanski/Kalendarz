public class CustomWholeDayEvent extends WholeDayEvent {
	private String eventName;

	CustomWholeDayEvent(int ID, String name, int length, boolean busy) {
		super(ID, length);
		this.eventName = name;
		this.setBusy(busy);
	}

	@Override
	public String toString() {
		return eventName + ". ID: " + getID();
	}
}
