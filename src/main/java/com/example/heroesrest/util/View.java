package com.example.heroesrest.util;

public class View {

    // For retrieveing list of creatures by certain castle - no need to send castle field
    public interface Creature {}

    // For other cases
    public interface CreatureWithCastle {}
}
