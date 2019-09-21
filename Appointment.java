public class Appointment extends TimedEvent {
	Appointment(int ID, Time time, int length) {
		super(ID, time, length);
		this.setBusy(true);
	}

	@Override
	public String toString() {
		return getTime() + " - " + getEnd() + " Appointment. ID: " + getID();
	}

}
