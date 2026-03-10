package com.ot.enums;

public enum RoleType {
    // System Level
    SUPER_ADMIN,
    
    // Hospital Level
    HOSPITAL_ADMIN,
    ADMIN,
    
    // OT Staff
    SURGEON, 
    ANESTHESIOLOGIST, 
    SCRUB_NURSE, 
    CIRCULATING_NURSE, 
    OT_TECHNICIAN,
    
    // Others
    RESIDENT, 
    INTERN, 
    STUDENT, 
    OBSERVER
}