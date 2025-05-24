package com.cg.todoapp.util;

import com.cg.todoapp.model.Priority;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class InputValidator {
    public boolean isValidDate(String input) {
        try {
            LocalDate.parse(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean isValidPriority(String input) {
        try {
            Priority.valueOf(input.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isValidText(String input) {
        return input != null && !input.trim().isEmpty();
    }
}
