package com.ot.enums;

public enum RoomType {
    OPERATION_THEATER, 
    PRE_OP, 
    POST_OP, 
    RECOVERY, 
    STERILIZATION, 
    STORAGE,
    GENERAL,        // Normal surgeries (appendix, minor procedures)
    MAJOR,          // Complex surgeries requiring full OT setup
    MINOR,          // Small procedures (suturing, small excisions)
    CARDIAC,        // Heart surgeries
    NEURO,          // Brain / spine surgeries
    ORTHOPEDIC,     // Bone / joint surgeries
    GYNECOLOGY,     // Women related surgeries
    EMERGENCY,      // Emergency OT available 24/7
    ROBOTIC         // Robotic assisted surgery OT
}