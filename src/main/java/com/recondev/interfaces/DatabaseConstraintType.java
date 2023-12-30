package com.recondev.interfaces;

public interface DatabaseConstraintType {
    String getConstraintType();

    boolean requiresAdditionalInput();

    String getConstraintSQL(String columnName, String additionalInput);
    // Other common methods...
}

