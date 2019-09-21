public class Vacation extends WholeDayEvent {
	Vacation(int ID, int length) {
		super(ID, length);
		this.setBusy(true);
	}

	@Override
	public String toString() {
		return "Vacation. ID: " + getID();
	}
}
