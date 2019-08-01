package com.miningmark48.tieredmagnets.util.exceptions;

public class ExceptionCapabilityNotPresent extends IllegalStateException {

    public ExceptionCapabilityNotPresent() {
        super("Capability was not present and therefore could not be queried. No further Information available!");
    }

    public ExceptionCapabilityNotPresent(Throwable cause) {
        super("Capability was not present and therefore could not be queried. No further Information available!", cause);
    }

}
