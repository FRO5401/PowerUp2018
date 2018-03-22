package org.usfirst.frc.team5401.robot;

/**
* The RobotMap is a mapping from the ports sensors and actuators are wired into
* to a variable name. This provides flexibility changing wiring, makes checking
* the wiring easier and significantly reduces the number of magic numbers
* floating around.
*/
public class RobotMap {	

	//Constants for ArmWrist
	public static final double ARM_kP = 0.5;
	public static final double ARM_kI = 0.002;
	public static final double ARM_kD = 0;
	public static final double ARM_kF = 0;
	public static final double ARM_PEAK_OUTPUT_FORWARD = 0.80;
	public static final double ARM_PEAK_OUTPUT_REVERSE = -0.40;
	public static final double ARM_NOM_OUTPUT = 0;
	public static final double ARM_RANGE = 0;
	public static final int TIMEOUT_LIMIT_IN_Ms = 10;//Might change. 10 is default in sample code
	public static final double ARM_THRESHOLD_FOR_PID_IN_DEGREES = 1.0;
	public static final double ARM_OVERRIDE_JOYSTICK_THRESHOLD = 0.2; 
	public static final double ARM_OVERRIDE_PRECISION = 0.65;
	public static final int ARM_TALON_CHANNEL = 0;
	public static final double ANGLE_PER_PULSE = .0071180006;

	//Setpoints
	// This is in Degrees
	//Floor setpoints are angles relative to the zero position, which is the position that the arm starts in. Also the lowest arm position possible 
	public static final double FLOOR_SETPOINT = 5.0;  
	public static final double SET_SWITCH_PORTAL_SETPOINT = 31.0; 
	public static final double SCALE_HIGH = 97.0;
	public static final double SCALE_MID = 76.0;
	public static final double CLIMB_POINT = 150.0;

	//Constants for Climber
	public static final double CLIMB_PRECISION = 0.1;

	//Global Constants that are not locations
	//This is for DriveBase
	public static final double LOW_GEAR_LEFT_DPP = -.149926;//-.149926
	public static final double LOW_GEAR_RIGHT_DPP = -.149926;//-.15345

	public static final double HIGH_GEAR_LEFT_DPP = 0;
	public static final double HIGH_GEAR_RIGHT_DPP = 0;
	public static final double DRIVE_PID_ABSOLUTE_TOLERANCE = 1.0; //threshold
	public static final double DRIVE_OUTPUT_RANGE = 0.6;
	
	public static final double DRIVE_P = 0.11;
	public static final double DRIVE_I = 0;
	public static final double DRIVE_D = 0;
	
	public static final double TURN_P = 0.01;
	public static final double TURN_I = 0.001;
	public static final double TURN_D = 0;
	public static final double TURN_F = 0;
	public static final double TURN_OUTPUT_RANGE = 0.6;


	
	//Constants for Infeed
	public static final double	INFEED_SPEED = 0.75;
	
	//Constants for XboxMove
	public static final double DRIVE_SENSITIVITY_PRECISE = 0.5;
	public static final double DRIVE_SENSITIVITY_DEFAULT = 1;
	public static final double DRIVE_THRESHHOLD = 0.2;
	public static final double DRIVE_SPIN_SENSITIVITY = 1;
	
	//Constants for AutoTurnAngle
	public static final double ANGLE_THRESHOLD = 0.5; //in degrees
	public static final double AUTO_TURN_SPEED = 0.95;
	public static final double AUTO_TURN_PRECISION = 0.75;

	//Constants for OI
	public static final double AXIS_THRESHOLD = 0.7;

	//Positions for OI
	public static final int XBOX_CONTROLLER_DRIVER = 0;
	public static final int XBOX_CONTROLLER_OPERATOR = 1;
	public static final int XBOX_CONTROLLER_TESTER = 2;
	
	public static final int XBOX_AXIS_LEFT_X = 0;
	public static final int XBOX_AXIS_LEFT_Y = 1;
	public static final int XBOX_AXIS_LEFT_TRIGGER = 2;
	public static final int XBOX_AXIS_RIGHT_TRIGGER = 3;
	public static final int XBOX_AXIS_RIGHT_X = 4;
	public static final int XBOX_AXIS_RIGHT_Y = 5;
	
	
	//OI Buttons Position
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
	
	public static final int XBOX_BUTTON_A_TESTER   		  	  = 1;
	public static final int XBOX_BUTTON_B_TESTER		      = 2;
	public static final int XBOX_BUTTON_X_TESTER		      = 3;
	public static final int XBOX_BUTTON_Y_TESTER		   	  = 4;
	public static final int XBOX_BUTTON_LEFT_BUMPER_TESTER    = 5;
	public static final int XBOX_BUTTON_RIGHT_BUMPER_TESTER   = 6;
	public static final int XBOX_BUTTON_BACK_TESTER		      = 7;
	public static final int XBOX_BUTTON_START_TESTER	  	  = 8;
	public static final int XBOX_BUTTON_L3_TESTER		  	  = 9;
	public static final int XBOX_BUTTON_R3_TESTER		  	  = 10;
	 
	//Motors
		//Drive Motors
	public static final int DRIVE_RIGHT_MOTOR_1 = 0;
	public static final int DRIVE_RIGHT_MOTOR_2 = 1;
	public static final int DRIVE_LEFT_MOTOR_1 	= 2;
	public static final int DRIVE_LEFT_MOTOR_2 	= 3;	

		//Climber Motor
	public static final int CLIMBER_MOTOR_TOP = 6;
	public static final int CLIMBER_MOTOR_BOTTOM = 7;


		//Infeed Motors
	public static final int INFEED_ROLLER_LEFT    = 4;
	public static final int INFEED_ROLLER_RIGHT	= 5;
	
	//PCM ID
	public static final int PCM_ID 				= 0;
	public static final int PCM_ID2 			= 1;
	
	//Solenoids
	public static final int DRIVE_SHIFT 		= 0;
	public static final int WRIST_MOVE_LONG_FORWARD   = 7;
	public static final int WRIST_MOVE_LONG_BACKWARD  = 3;

	public static final int ARM_BRAKE 		= 1;
	public static final int INFEED_OPEN		= 4;
	public static final int INFEED_CLOSE	= 2;
	public static final int INFEED_UP 		= 5;
	public static final int INFEED_DOWN		= 6;

	public static final int CLIMBER_STABLIZER		=	0;//Currently 0 because physically not allocated and commented out in code
	public static final int CLIMBER_PLATFORM_IN		=	0;//Currently 0 because physically not allocated and commented out in code
	public static final int CLIMBER_PLATFORM_OUT	=	0;//Currently 0 because physically not allocated and commented out in code
	
	//DIO Sensors
	public static final int DRIVE_ENC_LEFT_A 	= 0;
	public static final int DRIVE_ENC_LEFT_B 	= 1;
	public static final int DRIVE_ENC_RIGHT_A 	= 2;
	public static final int DRIVE_ENC_RIGHT_B 	= 3;

	public static final int INFEED_LIMIT_SWITCH   = 0;//Currently 0 because physically not allocated and commented out in code

	public static final int CLIMBER_SWITCH_TOP		= 0;
	public static final int CLIMBER_SWITCH_BOTTOM 	= 0;

}
