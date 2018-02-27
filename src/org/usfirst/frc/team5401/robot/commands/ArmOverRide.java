package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class ArmOverRide extends Command {

	boolean overrideEnabled;
	
    public ArmOverRide() {
    	requires(Robot.armwrist);
    	overrideEnabled = false;
    	//*When this is false, the command will keep running
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    
    // Called just before this Command runs the first time
    @Override
	protected void initialize() {
    	Robot.armwrist.setTalonSRXNeutralMode(1);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() {
    	//Joystick up gives a negative value. The negative sign swtiches that.
    	double overRideMoving = -Robot.oi.xboxAxis(RobotMap.XBOX_AXIS_RIGHT_Y, Robot.oi.xboxController_Operator) * RobotMap.ARM_OVERRIDE_PRECISION;
    	System.out.println(overRideMoving);
    	boolean overRideButton = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_R3_OPERATOR, Robot.oi.xboxController_Operator);
    	//calling to the button as well as the joystick
    	Robot.armwrist.getArmAngle();
    	Robot.armwrist.getWristAngle();
    	
    	//True means brake is released
    	Robot.armwrist.setBrake(overRideButton);
    	
    	if(overRideButton == true)
    	{	
    		System.out.println("overRideButton is true");
    		if(overRideMoving > RobotMap.ARM_OVERRIDE_JOYSTICK_THRESHOLD && Robot.armwrist.getArmAngle() < 175)
    		{
    			Robot.armwrist.overrideMove(overRideMoving);
    			System.out.println("Arm moving up");
    		}
    		else if(overRideMoving < RobotMap.ARM_OVERRIDE_JOYSTICK_THRESHOLD && Robot.armwrist.getArmAngle() > 24)
    		{
    			Robot.armwrist.overrideMove(overRideMoving);
    			System.out.println("Arm moving down");
    		}
    		
    		if(Math.abs(overRideMoving) < RobotMap.ARM_OVERRIDE_JOYSTICK_THRESHOLD)
    		{	
//    			Robot.armwrist.setBrake(true);
    			System.out.println("Override Button + Brake on");
    			Robot.armwrist.overrideMove(0);
    		}
    	}  
    	System.out.print("Test1");
    	//Calling for the overridemove button from the subsystem and the value of the left joystick form OI. This is executed.
    	overrideEnabled = (!overRideButton);
    	Robot.armwrist.getArmAngle();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
        return overrideEnabled;
    }

    // Called once after isFinished returns true
    @Override
	protected void end() {
    	System.out.println("Arm Override End");
    	Robot.armwrist.overrideStopped();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
	protected void interrupted() {
    	System.out.println("Arm Override Interrupted");
    	Robot.armwrist.armInterrupted();
    }
}