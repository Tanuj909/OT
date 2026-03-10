package com.ot.enums;

public enum TheaterStatus {
	  ACTIVE,         // OT operational hai aur rooms scheduling ke liye available hain
	  INACTIVE,       // OT temporarily disabled hai (admin ne deactivate kiya)
	  CLOSED ,         // OT permanently band kar diya gaya hai
      AVAILABLE, 
      OCCUPIED, 
      MAINTENANCE, 
      CLEANING, 
      RESERVED, 
      OUT_OF_SERVICE
}