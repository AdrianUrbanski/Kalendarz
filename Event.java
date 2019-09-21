// product

abstract class Event {
	private boolean busy;
	private boolean active = true;
	private int ID;

	Event(int ID) {
		this.ID = ID;
	}

	abstract boolean isWholeDay();

	void setBusy(boolean busy) {
		this.busy = busy;
	}

	boolean isBusy() {
		return busy;
	}

	boolean isNotActive() {
		return !active;
	}

	void deactivate() {
		this.active = false;
	}

	int getID() {
		return ID;
	}
}
