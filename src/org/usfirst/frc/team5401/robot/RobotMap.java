package org.usfirst.frc.team5401.robot;

/**
* The RobotMap is a mapping from the ports sensors and actuators are wired into
* to a variable name. This provides flexibility changing wiring, makes checking
* the wiring easier and significantly reduces the number of magic numbers
* floating around.
*/
public class RobotMap {	
	
	//TODO: Update ALL ROBOTMAP PHYSICAL AND CODEBASE

	/*** Constants ***/
		//ShortArm
	public static final int ARM_TALON_CHANNEL = 0;
	public static final double ANGLE_PER_PULSE = .007143424081276;
	public static final double ARM_SPEED = .60;
	public static final double ARM_MAX_ANGLE = 20;
	public static final double ARM_MIN_ANGLE = -102;

		//DriveBase
	public static final double LOW_GEAR_LEFT_DPP = -.149926;//-.149926
	public static final double LOW_GEAR_RIGHT_DPP = -.149926;//-.15345
	public static final double HIGH_GEAR_LEFT_DPP = 0;
	public static final double HIGH_GEAR_RIGHT_DPP = 0;
	public static final double DRIVE_SENSITIVITY_PRECISE = 0.5;
	public static final double DRIVE_SENSITIVITY_DEFAULT = 1;
	public static final double DRIVE_THRESHHOLD = 0.2;
	public static final double DRIVE_SPIN_SENSITIVITY = 1;
	
		//AutoTurnAngle
	public static final double ANGLE_THRESHOLD = 0.25; //in degrees
	public static final double AUTO_TURN_SPEED = 0.95;
	public static final double AUTO_TURN_PRECISION = 0.75;

	// TODO: Figure out if these PID vals are necessary
	public static final double DRIVE_PID_ABSOLUTE_TOLERANCE = 1.0; //threshold
	public static final double DRIVE_OUTPUT_RANGE = 0.6;
	public static final double DRIVE_PID_CORRECTION_THRESHOLD = 1;
	public static final double DRIVE_P = 0.11;
	public static final double DRIVE_I = 0;
	public static final double DRIVE_D = 0;
	public static final double TURN_P = 0.01;
	public static final double TURN_I = 0.001;
	public static final double TURN_D = 0;
	public static final double TURN_F = 0;
	public static final double TURN_OUTPUT_RANGE = 0.6;

	
	/*** OI ***/
		//Constants
	public static final double AXIS_THRESHOLD = 0.5;

		//Controllers
	public static final int XBOX_CONTROLLER_DRIVER   = 0;
	public static final int XBOX_CONTROLLER_OPERATOR = 1;
	
	public static final int XBOX_AXIS_LEFT_X = 0;
	public static final int XBOX_AXIS_LEFT_Y = 1;
	public static final int XBOX_AXIS_LEFT_TRIGGER  = 2;
	public static final int XBOX_AXIS_RIGHT_TRIGGER = 3;
	public static final int XBOX_AXIS_RIGHT_X = 4;
	public static final int XBOX_AXIS_RIGHT_Y = 5;
	
		//Driver Buttons
	public static final int XBOX_BUTTON_A_DRIVER	   		 = 1;
	public static final int XBOX_BUTTON_B_DRIVER		     = 2;
	public static final int XBOX_BUTTON_X_DRIVER		     = 3;
	public static final int XBOX_BUTTON_Y_DRIVER		   	 = 4;
	public static final int XBOX_BUTTON_LEFT_BUMPER_DRIVER   = 5;
	public static final int XBOX_BUTTON_RIGHT_BUMPER_DRIVER  = 6;
	public static final int XBOX_BUTTON_BACK_DRIVER		     = 7;
	public static final int XBOX_BUTTON_START_DRIVER	  	 = 8;
	public static final int XBOX_BUTTON_L3_DRIVER		  	 = 9;
	public static final int XBOX_BUTTON_R3_DRIVER		  	 = 10;
	
		//Operator Buttons
	public static final int XBOX_BUTTON_A_OPERATOR	   		  = 1;
	public static final int XBOX_BUTTON_B_OPERATOR		      = 2;
	public static final int XBOX_BUTTON_X_OPERATOR		      = 3;
	public static final int XBOX_BUTTON_Y_OPERATOR		   	  = 4;
	public static final int XBOX_BUTTON_LEFT_BUMPER_OPERATOR  = 5;
	public static final int XBOX_BUTTON_RIGHT_BUMPER_OPERATOR = 6;
	public static final int XBOX_BUTTON_BACK_OPERATOR		  = 7;
	public static final int XBOX_BUTTON_START_OPERATOR	  	  = 8;
	public static final int XBOX_BUTTON_L3_OPERATOR		  	  = 9;
	public static final int XBOX_BUTTON_R3_OPERATOR		  	  = 10;
	
	/*** Motors ***/
		//Drive Motors
	public static final int DRIVE_RIGHT_MOTOR_1 = 6;
	public static final int DRIVE_RIGHT_MOTOR_2 = 7;
	public static final int DRIVE_LEFT_MOTOR_1 	= 9;
	public static final int DRIVE_LEFT_MOTOR_2 	= 8;	

		//Infeed Motors
	public static final int INFEED_ROLLER_LEFT  = 4;
	public static final int INFEED_ROLLER_RIGHT	= 5;
	
	//PCM ID
	public static final int PCM_ID 	= 0;
	
	/*** Solenoids ***/
		//Double
	public static final int SHIFTER_IN   = 1;
	public static final int SHIFTER_OUT  = 0;
	public static final int INFEED_OPEN	 = 2;
	public static final int INFEED_CLOSE = 3;
	
	//TODO: No up down solenoids exist, remove 
	public static final int INFEED_UP 	 = 5;
	public static final int INFEED_DOWN	 = 6;
		
		//Single
	public static final int ARM_BRAKE 	 = 6;
	
	/*** DIO Sensors ***/
	public static final int DRIVE_ENC_LEFT_A 	= 2;
	public static final int DRIVE_ENC_LEFT_B 	= 3;
	public static final int DRIVE_ENC_RIGHT_A 	= 0;
	public static final int DRIVE_ENC_RIGHT_B 	= 1;
}
