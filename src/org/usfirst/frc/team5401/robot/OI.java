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

  //User Maps
  Joystick xboxController_Driver = new Joystick(RobotMap.XBOX_CONTROLLER_DRIVER);
  Joystick xboxController_Operator = new Joystick(RobotMap.XBOX_CONTROLLER_OPERATOR);

  //Assign values from RobotMap here
  int leftStickX = RobotMap.XBOX_AXIS_LEFT_X
  int leftStickY = RobotMap.XBOX_AXIS_LEFT_Y

  int rightStickX = RobotMap.XBOX_AXIS_RIGHT_X
  int rightStickY = RobotMap.XBOX_AXIS_RIGHT_Y

  int leftTrigger = RobotMap.XBOX_AXIS_LEFT_TRIGGER
  int rightTrigger = RobotMap.XBOX_AXIS_RIGHT_TRIGGER

  int buttonA = 1
  int buttonB = 2
  int buttonX = 3
  int buttonY = 4
  int buttonLB = 5
  int buttonRB = 6
  int buttonBack = 7
  int buttonStart = 8
  int buttonL3 = 9
  int buttonR3 = 10

  //Controller Axis
  public double xboxAxis(input, userMap) {
    return userMap.getRawAxis(input)
  }

  //Controller Button
  public double xboxButton(button, userMap) {
    return userMap.getRawButton(button)
  }

  //DPad
  public double xboxDPad(userMap){
    return userMap.getPOV()
  }
}
