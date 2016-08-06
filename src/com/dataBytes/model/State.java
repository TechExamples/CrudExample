package com.dataBytes.model;

import lombok.Getter;
import lombok.Setter;

public enum State {

	ACTIVE("Active"), INACTIVE("Inactive"), DELETED("Deleted"), LOCKED("Locked");

	private State(final String state) {
		this.state = state;
	}

	@Getter
	@Setter
	private String state;

	@Override
	public String toString() {
		return this.state;
	}

	public String getName() {
		return this.name();
	}

}
