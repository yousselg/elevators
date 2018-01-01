package com.sqli.test.elevators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Building {

	private int numberOfFloors;
	private Map<String, Elevator> buildingElevators = new HashMap<>();

	/**
	 * @param numberOfFloors:
	 *            the number of floors in the building
	 * @param elevators:
	 *            an array of descriptions of the elevators of the building. A
	 *            description has the following format
	 *            "[elevator_id]:[elevator_current_floor]".
	 */
	public Building(int numberOfFloors, String... elevators) {
		this.setNumberOfFloors(numberOfFloors);
		String[] elevatorContent;
		for (String elevator : elevators) {
			elevatorContent = elevator.split(":");
			if (Double.parseDouble(elevatorContent[1]) <= numberOfFloors)
				buildingElevators.put(elevatorContent[0],
						new Elevator(elevatorContent[0], Integer.parseInt(elevatorContent[1]), "REST"));
		}
	}

	/**
	 * Request an elevator at floor zero.
	 * 
	 * @return the id of the elevator that should serve the request.
	 */
	public String requestElevator() {

		Elevator maxElev = null;
		String direction;
		int currentPosition, maxPosition = 0;

		for (Elevator elevator : buildingElevators.values()) {

			currentPosition = elevator.getCurrentFloor();
			direction = elevator.getDirection();

			if ((direction == "REST" || direction == "UP") && elevator.getStopAt().size() == 0)
				if (currentPosition > maxPosition) {
					maxElev = elevator;
					maxPosition = currentPosition;
					System.out.println(maxPosition + " " + maxElev + " " + direction);
				}
		}
		return maxElev.getId();
	}

	/**
	 * Request an elevator at floor indicate by the {@code floor} param.
	 * 
	 * @param floor
	 *            : the floor where the request is made.
	 * @return the id of the elevator that should serve the request.
	 */
	public String requestElevator(int floor) {
		List<Elevator> elevators = new ArrayList<Elevator>();
		elevators.addAll(buildingElevators.values());
		Collections.sort(elevators);
		String nearestElevator = null;

		for (Elevator e : elevators) {
			if (e.getCurrentFloor() >= floor)
				nearestElevator = e.getId();
		}
		return nearestElevator;
	}

	/**
	 * Request the elevator with the id {@code elevatorId} to stop at the floor
	 * indicated by the {@code floor} param.
	 * 
	 * @param elevatorId
	 *            : the id of the elevator to whom give the order.
	 * @param floor
	 *            : the floor at which the elevator should stop
	 */
	public void stopAt(String elevatorId, int floor) {
		Elevator elevator = buildingElevators.get(elevatorId);
		elevator.getStopAt().add(floor);
	}

	/**
	 * Move the elevator with the id {@code elevatorId} in the direction
	 * indicated by the {@code direction} param.
	 * 
	 * @param elevatorId
	 *            : the id of the elevator to move.
	 * @param direction
	 *            : the direction to go. Can be "UP" or "DOWN".
	 */
	public void move(String elevatorId, String direction) {
		if (direction != "UP" && direction != "DOWN")
			throw new UnsupportedOperationException("Wrong direction");
		Elevator elevator = buildingElevators.get(elevatorId);
		elevator.setDirection(direction);
		buildingElevators.replace(elevatorId, elevator);
	}

	public int getNumberOfFloors() {
		return numberOfFloors;
	}

	public void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}

}
