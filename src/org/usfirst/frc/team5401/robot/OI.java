package org.usfirst.frc.team5401.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team5401.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	//User Maps
	//These objects must be public for other classes to access when calling methods from OI
	public Joystick xboxController_Driver = new Joystick(RobotMap.XBOX_CONTROLLER_DRIVER);
	public Joystick xboxController_Operator = new Joystick(RobotMap.XBOX_CONTROLLER_OPERATOR);
	
	//Buttons
	Button xboxA_Driver			  = new JoystickButton(xboxController_Driver, 1);
	Button xboxB_Driver			  = new JoystickButton(xboxController_Driver, 2);
	Button xboxX_Driver			  = new JoystickButton(xboxController_Driver, 3);
	Button xboxY_Driver			  = new JoystickButton(xboxController_Driver, 4);
	Button xboxLeftBumper_Driver  = new JoystickButton(xboxController_Driver, 5);
	Button xboxRightBumper_Driver = new JoystickButton(xboxController_Driver, 6);
	Button xboxBack_Driver		  = new JoystickButton(xboxController_Driver, 7);
	Button xboxStart_Driver		  = new JoystickButton(xboxController_Driver, 8);
	Button xboxL3_Driver		  = new JoystickButton(xboxController_Driver, 9);
	Button xboxR3_Driver		  = new JoystickButton(xboxController_Driver, 10);
	
	Button xboxA_Operator			= new JoystickButton(xboxController_Operator, 1);
	Button xboxB_Operator			= new JoystickButton(xboxController_Operator, 2);
	Button xboxX_Operator			= new JoystickButton(xboxController_Operator, 3);
	Button xboxY_Operator			= new JoystickButton(xboxController_Operator, 4);
	Button xboxLeftBumper_Operator  = new JoystickButton(xboxController_Operator, 5);
	Button xboxRightBumper_Operator = new JoystickButton(xboxController_Operator, 6);
	Button xboxBack_Operator		= new JoystickButton(xboxController_Operator, 7);
	Button xboxStart_Operator		= new JoystickButton(xboxController_Operator, 8);
	Button xboxL3_Operator		  	= new JoystickButton(xboxController_Operator, 9);
	Button xboxR3_Operator		  	= new JoystickButton(xboxController_Operator, 10);

	public OI()
	{
		xboxY_Driver.whenPressed(new CompressorToggle());
		xboxL3_Operator.whenPressed(new WristMove());
		xboxR3_Operator.whenPressed(new ArmOverRide());
		
	}
	
	//Controller Axis
	public double xboxAxis(int input, Joystick userMap) {
		return userMap.getRawAxis(input);
	}	

	//Controller Button
	public boolean xboxButton(int button, Joystick userMap) {
		return userMap.getRawButton(button);
	}

	//DPad
	public double xboxDPad(Joystick userMap){
		return userMap.getPOV();
	}
	public int readXboxLeftY_Axis(){
		if(xboxController_Operator.getRawAxis(RobotMap.XBOX_AXIS_LEFT_Y) < -0.2){
			return -1;
		} else if (xboxController_Operator.getRawAxis(RobotMap.XBOX_AXIS_LEFT_Y) > 0.2){
			return 1;
		} else {
			return 0;
		}
	}
	
	public int getArmButtons(){
			int armPosDesired;
			if (xboxController_Operator.getRawButton(RobotMap.XBOX_BUTTON_A_OPERATOR)){
				 armPosDesired = RobotMap.FLOOR_SETPOINT;
			}
			else if (xboxController_Operator.getRawButton(RobotMap.XBOX_BUTTON_B_OPERATOR)){
				armPosDesired = RobotMap.PORTAL_SETPOINT;
			}
			else if (xboxController_Operator.getRawButton(RobotMap.XBOX_BUTTON_X_OPERATOR)){
				armPosDesired = RobotMap.SET_SWITCH_SETPOINT;
			}
			//else if (xboxController_Operator.getRawButton(RobotMap.XBOX_BUTTON_Y_OPERATOR)){
			//	armPosDesired = Robotmap.;
			//}
			else if (xboxController_Operator.getRawButton(RobotMap.XBOX_BUTTON_LEFT_BUMPER_OPERATOR)){
				armPosDesired = RobotMap.HIGHEST_RUNG_SETPOINT;
			}
			else {
				armPosDesired = -1;
			}
				return armPosDesired;
			}
	
	public int getXboxRightStickY_Operator(){
		double value = xboxController_Operator.getRawAxis(RobotMap.XBOX_AXIS_RIGHT_Y);
		if (value > .5){//this is controller down
			return -1;// so down as in negative
		} else if (value < -.5){//this is controller up
			return 1;// so up as in positive
		} else {
			return 0;
		}
	}
	
	public boolean getXboxOperator_R3(){
		return xboxController_Operator.getRawButton(RobotMap.XBOX_BUTTON_R3_OPERATOR);
	}
		
}
