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

		
	}
	
	/**Method Naming: 'read' = Analog; 'get' = Digital **/
	
	/*
	 public boolean getExample_Button(){
		return xboxController_Button.getRawButton(#);
	}
		BUTTON EXAMPLE^^ 
	 */
	
	//Controller Axis Driver
	public double readXboxLeftX_Driver(){
		return xboxController_Driver.getRawAxis(RobotMap.XBOX_AXIS_LEFT_X);
	}
	
	public double readXboxLeftY_Driver(){
		return xboxController_Driver.getRawAxis(RobotMap.XBOX_AXIS_LEFT_Y);
	}
	
	public double readXboxRightX_Driver(){
		return xboxController_Driver.getRawAxis(RobotMap.XBOX_AXIS_RIGHT_X);
	}
	
	public double readXboxRightY_Driver(){
		return xboxController_Driver.getRawAxis(RobotMap.XBOX_AXIS_RIGHT_Y);
	}
	
	
	//Controller Triggers Driver
	public double readLeftTrigger_Driver(){
		return xboxController_Driver.getRawAxis(RobotMap.XBOX_AXIS_LEFT_TRIGGER);		
	}
	
	public double readRightTrigger_Driver(){
		return xboxController_Driver.getRawAxis(RobotMap.XBOX_AXIS_RIGHT_TRIGGER);
	}
	
	//Controller Buttons Driver
	
	public boolean getButtonA_Driver(){
		return xboxController_Driver.getRawButton(1);
	}
	
	public boolean getButtonB_Driver(){
		return xboxController_Driver.getRawButton(2);
	}
	
	public boolean getButtonX_Driver(){
		return xboxController_Driver.getRawButton(3);
	}
	
	public boolean getButtonY_Driver(){
		return xboxController_Driver.getRawButton(4);
	}
	
	public boolean getButtonLB_Driver(){
		return xboxController_Driver.getRawButton(5);
	}
	
	public boolean getButtonRB_Driver(){
		return xboxController_Driver.getRawButton(6);
	}
	
	public boolean getButtonBack_Driver(){
		return xboxController_Driver.getRawButton(7);
	}
	
	public boolean getButtonStart_Driver(){
		return xboxController_Driver.getRawButton(8);
	}
	
	public boolean getButtonL3_Driver(){
		return xboxController_Driver.getRawButton(9);
	}
	
	public boolean getButtonR3_Driver(){
		return xboxController_Driver.getRawButton(10);
	}
	
	//Controller D-Pad Driver
	
	public int getDPad_Driver(){
		int POV = xboxController_Operator.getPOV();
		return POV;
		
		//Center == -1
		//Everything else relates to corresponding angle!
	}
	
	//Controller Axis Operator
	public double getXboxLeftStickY_Operator(){
		double value = xboxController_Operator.getRawAxis(RobotMap.XBOX_AXIS_LEFT_Y);
		return value;
	}
	
	public double getXboxLeftStickX_Operator(){
		double value = xboxController_Operator.getRawAxis(RobotMap.XBOX_AXIS_LEFT_X);
		return value;
	}
	
	public double getXboxRightStickY_Operator(){
		double value = xboxController_Operator.getRawAxis(RobotMap.XBOX_AXIS_RIGHT_Y);
		return value;
	}
	
	public double getXboxRightStickX_Operator(){
		double value = xboxController_Operator.getRawAxis(RobotMap.XBOX_AXIS_RIGHT_X);
		return value;
	}
	
	//Controller Triggers Operator
	public double getXboxTriggerRight_Operator(){
		double right = xboxController_Operator.getRawAxis(RobotMap.XBOX_AXIS_RIGHT_TRIGGER);
		return right;
	}
	
	public double getXboxTriggerLeft_Operator(){
		double left  = xboxController_Operator.getRawAxis(RobotMap.XBOX_AXIS_LEFT_TRIGGER);
		return left;
	}
	
	//Controller Buttons Operator
	
	public boolean getButtonA_Operator(){
		return xboxController_Operator.getRawButton(1);
	}
		
	public boolean getButtonB_Operator(){
		return xboxController_Operator.getRawButton(2);
	}
		
	public boolean getButtonX_Operator(){
		return xboxController_Operator.getRawButton(3);
	}
		
	public boolean getButtonY_Operator(){
		return xboxController_Operator.getRawButton(4);
	}
		
	public boolean getButtonLB_Operator(){
		return xboxController_Operator.getRawButton(5);
	}
		
	public boolean getButtonRB_Operator(){
		return xboxController_Operator.getRawButton(6);
	}
		
	public boolean getButtonBack_Operator(){
		return xboxController_Operator.getRawButton(7);
	}
		
	public boolean getButtonStart_Operator(){
		return xboxController_Operator.getRawButton(8);
	}
		
	public boolean getButtonL3_Operator(){
		return xboxController_Operator.getRawButton(9);
	}
		
	public boolean getButtonR3_Operator(){
		return xboxController_Operator.getRawButton(10);
	}
	
	//Controller D-Pad Operator
	public int getDPad_Operator(){
		int POV = xboxController_Operator.getPOV();
		return POV;
		
		//Center == -1
		//Everything else relates to corresponding angle!
	}
}