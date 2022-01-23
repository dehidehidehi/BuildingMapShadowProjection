package BuildingShadows.DAO.Entities;

import BuildingShadows.Objects.GPSCoordinates;

public class Building {
	
	private String name;
	private double height;
	private String country;
	private String town;
	private GPSCoordinates coordinates;

	public Building() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public GPSCoordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(GPSCoordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	@Override
	public String toString() {
		String[] repr = {this.name, String.valueOf(this.height), this.country, this.town, this.coordinates.toString()};
		return String.join("\t",  repr);
	}

}
