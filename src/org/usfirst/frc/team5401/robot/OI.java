package org.usfirst.frc.team5401.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team5401.robot.commands.*;
//import org.usfirst.frc.team5401.robot.autonomous.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
	Joystick xboxController_Driver = new Joystick(RobotMap.XBOX_CONTROLLER_DRIVER);
	Joystick xboxController_Operator = new Joystick(RobotMap.XBOX_CONTROLLER_OPERATOR);
	
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
	
	
	
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	//Buttons
	public OI(){
		
		xboxA_Operator.whenPressed(new ArmPIDMove(RobotMap.FLOOR_SETPOINT));
		xboxB_Operator.whenPressed(new ArmPIDMove(RobotMap.PORTAL_SETPOINT));
		xboxX_Operator.whenPressed(new ArmPIDMove(RobotMap.SET_SWITCH_SETPOINT));
		xboxLeftBumper_Operator.whenPressed(new ArmPIDMove(RobotMap.SCALE_SETPOINT));
		xboxRightBumper_Operator.whenPressed(new ArmPIDMove(RobotMap.HIGHEST_RUNG_SETPOINT));
		
		xboxL3_Operator.whenPressed(new WristMove());
		
	}
	
	/**Method Naming: 'read' = Analog; 'get' = Digital **/
	
	public double readXboxLeftX_Driver(){
		return xboxController_Driver.getRawAxis(RobotMap.XBOX_AXIS_LEFT_X);
	}
	
	public double readLeftTrigger_Driver(){
		return xboxController_Driver.getRawAxis(RobotMap.XBOX_AXIS_LEFT_TRIGGER);		
	}
	
	public double readRightTrigger_Driver(){
		return xboxController_Driver.getRawAxis(RobotMap.XBOX_AXIS_RIGHT_TRIGGER);
	}
	
	public int readXboxLeftY_Operator(){
		if(xboxController_Operator.getRawAxis(RobotMap.XBOX_AXIS_LEFT_Y) > 0)
		{
			return 1;
		}
		if(xboxController_Operator.getRawAxis(RobotMap.XBOX_AXIS_LEFT_Y) < 0)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
	
	public boolean getPrecision_Driver(){
		return xboxController_Driver.getRawButton(RobotMap.XBOX_BUTTON_LEFT_BUMPER_DRIVER);
	}
	
	public boolean getBrake_Driver(){
		return xboxController_Driver.getRawButton(RobotMap.XBOX_BUTTON_RIGHT_BUMPER_DRIVER);
	}
	
	public boolean getTurnButton_Driver(){
		return xboxController_Driver.getRawButton(RobotMap.XBOX_BUTTON_L3_DRIVER);
	}
	
	public boolean getDriveInvertButton_Driver() {
		return xboxController_Driver.getRawButton(RobotMap.XBOX_BUTTON_B_DRIVER);
	}
	
	public int getDPad_Operator(){
		int POV = xboxController_Operator.getPOV();//gets the POV of the D-pad on the Operator Controller, an unpressed D-pad is -1, otherwise its the angle at which it is pressed
		//Use specific values, not inequalities
		if (POV == 0 || POV == 45 || POV == 315){
			//Possible SmartDashboard values?
			return 1;	//Up
		}
		else if (POV == 180 || POV == 225 || POV == 135){
			//Possible SmartDashboard values?
			return -1; 	//Down
		}
		else
		{
			return 0; 	//not pressed/error or incorrect section is pressed
						//Does nothing
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
	
	//For GearMechanism
	public int getXboxRightStickY_Driver(){ //TODO remove
		double value = xboxController_Driver.getRawAxis(RobotMap.XBOX_AXIS_RIGHT_Y);
		if (value > .5){
			return 1;
		} else if (value < -.5){
			return -1;
		} else {
			return 0;
		}
	}
	

	//For Feeder Up/Down
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
	
	//For Feeder In/Out
	public int getXboxTriggers_Operator(){
		double left  = xboxController_Operator.getRawAxis(RobotMap.XBOX_AXIS_LEFT_TRIGGER);
		double right = xboxController_Operator.getRawAxis(RobotMap.XBOX_AXIS_RIGHT_TRIGGER);
		if (right > .1){ 
			return 1;
		} else if (left > .1){//<--left is in
			return -1;
		} else {
			return 0;
		}
	}
	
	//Gear Shift to Low
	public boolean getXboxBack_Driver(){
		return xboxController_Driver.getRawButton(RobotMap.XBOX_BUTTON_BACK_DRIVER);
	}
	
	//Gear Shift to High
	public boolean getXboxStart_Driver(){
		return xboxController_Driver.getRawButton(RobotMap.XBOX_BUTTON_START_DRIVER);
	}
	
	public boolean getXboxOperator_R3(){
		return xboxController_Operator.getRawButton(RobotMap.XBOX_BUTTON_R3_OPERATOR);
	}
	
	public boolean getXboxOperatorL3(){
		return xboxController_Operator.getRawButton(RobotMap.XBOX_BUTTON_L3_OPERATOR);
	}
}