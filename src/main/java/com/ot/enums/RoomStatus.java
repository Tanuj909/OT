package com.ot.enums;

public enum RoomStatus {
    ACTIVE,
    INACTIVE, 
    MAINTENANCE,
    CLEANING,
    AVAILABLE,      // Room ready hai surgery ke liye
    OCCUPIED,       // Surgery chal rahi hai
    RESERVED,       // Upcoming surgery ke liye block hai
    OUT_OF_SERVICE  // Room temporarily use nahi ho sakta
}