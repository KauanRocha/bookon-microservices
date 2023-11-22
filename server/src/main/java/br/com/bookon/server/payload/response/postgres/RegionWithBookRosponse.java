package br.com.bookon.server.payload.response.postgres;

import java.util.List;

public class RegionWithBookRosponse {
	
	private List<BookResponse> books;
	
	private double latitude;
	
	private double longitude;
	
	private double distance;

	public List<BookResponse> getBooks() {
		return books;
	}

	public void setBooks(List<BookResponse> books) {
		this.books = books;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public RegionWithBookRosponse() {
	}
	
}
