package com.example.COGIP.VariableType;


public enum UserType { // role are in number in db
    admin, // = 0
    accountant, // = 1
    intern; // = 2

    // Custom method to convert a string to UserType
    public static UserType fromString(String input) {
        for (UserType userType : UserType.values()) {
            if (userType.name().equalsIgnoreCase(input)) {
                return userType;
            }
        }
        throw new IllegalArgumentException("No UserType with name " + input);
    }
}
