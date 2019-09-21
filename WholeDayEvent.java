abstract class WholeDayEvent extends Event {

	private int length;

	WholeDayEvent(int ID, int length) {
		super(ID);
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	@Override
	boolean isWholeDay() {
		return true;
	}
}
