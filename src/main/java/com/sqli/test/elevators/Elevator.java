package com.sqli.test.elevators;

import java.util.ArrayList;
import java.util.List;

public class Elevator implements Comparable<Elevator> {

	private String id;
	private Integer currentFloor;
	private String direction;
	private List<Integer> stopAt = new ArrayList<Integer>();

	public Elevator(String id, Integer currentFloor, String direction) {
		super();
		this.id = id;
		this.currentFloor = currentFloor;
		this.direction = direction;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(Integer currentFloor) {
		this.currentFloor = currentFloor;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public List<Integer> getStopAt() {
		return stopAt;
	}

	public void setStopAt(List<Integer> stopAt) {
		this.stopAt = stopAt;
	}

	@Override
	public int compareTo(Elevator elevator) {
		return elevator.getCurrentFloor() - this.currentFloor;
	}
}
