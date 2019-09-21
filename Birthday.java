public class Birthday extends WholeDayEvent {

	private String name;

	Birthday(int ID, String name) {
		super(ID, 1);
		this.name = name;
		this.setBusy(false);
	}

	@Override
	public String toString() {
		return name+"'s Birthday. ID: " + getID();
	}
}
