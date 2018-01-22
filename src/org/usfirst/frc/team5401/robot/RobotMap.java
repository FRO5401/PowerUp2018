package org.usfirst.frc.team5401.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Global Constants that are not locations
	//This is for DriveBase
	public static final double LOW_GEAR_LEFT_DPP = 0;
	public static final double LOW_GEAR_RIGHT_DPP = 0;
	public static final double HIGH_GEAR_LEFT_DPP = 0;
	public static final double HIGH_GEAR_RIGHT_DPP = 0;
	
	//This is for XboxMove
	public static final double MINIMUM_VELOCITY_FOR_HIGH_GEAR = 0; //Experimentally Determined, REMEMBER inches per second
	public static final double MAXIMUM_VELOCITY_FOR_LOW_GEAR = 0;
	public static final double DRIVE_SENSITIVITY_PRECISE = 0;
	public static final double DRIVE_SENSITIVITY_DEFAULT = 0;
	public static final double DRIVE_THRESHHOLD = 0;
	public static final double DRIVE_SPIN_SENSITIVITY = 0;
	
	//This is for AutoTurnAngle
	public static final double ANGLE_THRESHOLD = 1;//in degrees
	public static final double AUTO_TURN_SPEED = 0.95;
	public static final double AUTO_TURN_PRECISION = 0.5;
	
	//This is for OI
	 public static final int XBOX_CONTROLLER_DRIVER = 0;
	 public static final int XBOX_CONTROLLER_OPERATOR = 1;
	 public static final int XBOX_AXIS_LEFT_X = 0;
	 public static final int XBOX_AXIS_LEFT_TRIGGER = 0;
	 public static final int XBOX_AXIS_RIGHT_TRIGGER = 0;
	 public static final int XBOX_AXIS_RIGHT_Y = 0;
	 public static final int XBOX_AXIS_LEFT_Y = 0;
	 
	 
	//Motors
		//Drive Motors
	public static final int DRIVE_RIGHT_MOTOR_1 = 0;
	public static final int DRIVE_RIGHT_MOTOR_2 = 1;
	public static final int DRIVE_RIGHT_MOTOR_3 = 2;
	public static final int DRIVE_LEFT_MOTOR_1 	= 3;
	public static final int DRIVE_LEFT_MOTOR_2 	= 4;
	public static final int DRIVE_LEFT_MOTOR_3  = 5;
	
	
	//PCM ID
	public static final int PCM_ID 				= 0;
	//Solenoids
	public static final int DRIVE_SHIFT_IN 		= 0;
	public static final int DRIVE_SHIFT_OUT 	= 0;
	//DIO Sensors
	public static final int DRIVE_ENC_LEFT_A 	= 0;
	public static final int DRIVE_ENC_LEFT_B 	= 1;
	public static final int DRIVE_ENC_RIGHT_A 	= 2;
	public static final int DRIVE_ENC_RIGHT_B 	= 3;
}
