public class Time implements Comparable<Time> {
	private int hour;
	private int minute;

	Time(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}

	Time increase(int minutes){
		int newMinute = this.getMinute() + minutes;
		int newHour = this.getHour() + (newMinute/60);
		newMinute %= 60;
		return new Time(newHour, newMinute);
	}

	private int getHour() {
		return hour;
	}

	private int getMinute() {
		return minute;
	}

	@Override
	public String toString() {
		return String.format("%02d", hour) + ":" + String.format("%02d", minute);
	}

	@Override
	public int compareTo(Time t) {
		if(this.getHour() > t.getHour())
			return 1;
		else if(this.getHour() < t.getHour())
			return -1;
		else
			if (this.getMinute() > t.getMinute())
				return 1;
			else if (this.getMinute() < t.getMinute())
				return -1;
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		Time t = (Time) obj;
		return (this.getHour() == t.getHour() && this.getMinute() == t.getMinute());
	}
}
