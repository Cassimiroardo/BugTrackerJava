package app.main.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Severity {
	LOW(0), MEDIUM(1), HIGH(2);
	
	private final int value;

	Severity(int value) {
		this.value = value;
	}

	public static Optional<Severity> valueOf(int value) {
        return Arrays.stream(values())
            .filter(severity -> severity.value == value)
            .findFirst();
    }
}
