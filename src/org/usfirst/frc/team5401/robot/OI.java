package org.usfirst.frc.team5401.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team5401.robot.commands.*;
import org.usfirst.frc.team5401.robot.*;
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

	public OI(){
	
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
	
	public int xboxAxisAsDigitalInput(int axisInput, Joystick userMap)
	{
		//if a  joystick on the xbox controller goes up, the output is negative
		if(userMap.getRawAxis(axisInput) > RobotMap.AXIS_THRESHOLD)
		{
			return 1;
		}
		else if(userMap.getRawAxis(axisInput) < (-1 * RobotMap.AXIS_THRESHOLD))
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
	
	public int getXboxLeftStickY_Operator(){
		double value = xboxController_Operator.getRawAxis(RobotMap.XBOX_AXIS_LEFT_Y);
		if (value > .5){//this is controller down
			return -1;// so down as in negative
		} else if (value < -.5){//this is controller up
			return 1;// so up as in positive
		} else {
			return 0;
		}
	}
}
