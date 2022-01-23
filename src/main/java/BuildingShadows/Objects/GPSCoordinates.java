package BuildingShadows.Objects;

public class GPSCoordinates {

	private int latDegrees;
	private int latMinutes;
	private double latSeconds;
	private char latDirection;
	private int longDegrees;
	private int longMinutes;
	private double longSeconds;
	private char longDirection;

	/**
	 * Sample input: 35°49?54.0?N 78°08?49?W
	 */
	public GPSCoordinates(String rawCoordinates) {
		GPSCoordinates.assertLengthIsCorrect(rawCoordinates);
		String[] split = rawCoordinates.trim().split("[^0-9.NEWS]+");

		this.latDegrees = Integer.valueOf(split[0]);
		this.latMinutes = Integer.valueOf(split[1]);
		this.latSeconds = Double.valueOf(split[2]);
		this.latDirection = Character.valueOf(split[3].charAt(0));

		this.longDegrees = Integer.valueOf(split[4]);
		this.longMinutes = Integer.valueOf(split[5]);
		this.longSeconds = Double.valueOf(split[6]);
		this.longDirection = Character.valueOf(split[7].charAt(0));
	}

	static private void assertLengthIsCorrect(String rawCoordinates) {
		int min = 18;
		int max = 30;
		if (rawCoordinates.length() < min || rawCoordinates.length() > max) {
			throw new IllegalArgumentException("Length of rawCoordinates (" + rawCoordinates.length()
					+ ") not as expected ( " + min + " to " + max + ").");
		}
	}

	public int getLatDegrees() {
		return latDegrees;
	}

	public int getLatMinutes() {
		return latMinutes;
	}

	public double getLatSeconds() {
		return latSeconds;
	}

	public int getLongDegrees() {
		return longDegrees;
	}

	public int getLongMinutes() {
		return longMinutes;
	}

	public double getLongSeconds() {
		return longSeconds;
	}

	public char getLatDirection() {
		return latDirection;
	}

	public char getLongDirection() {
		return longDirection;
	}

	@Override
	public String toString() {
		String lat = this.latDegrees + "° " + this.latMinutes + "' " + this.latSeconds + "\" "
				+ this.latDirection;
		String long_ = this.longDegrees + "° " + this.longMinutes + "' " + this.longSeconds + "\" "
				+ this.longDirection;
		return lat + " " + long_;
	}

}
