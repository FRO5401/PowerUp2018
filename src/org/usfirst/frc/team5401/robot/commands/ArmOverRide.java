package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmOverRide extends Command {

    public ArmOverRide() {
    	requires(Robot.arm);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double overRideMoving = Robot.oi.getXboxRightStickY_Operator();
    	boolean overRideButton = Robot.oi.getOperatorOverride_Driver();
    	//calling to the button as well as the joystick
    	
    	
    	if(overRideButton == true)
    	{
    		Robot.arm.setBrake(false);
    		Robot.arm.overrideMove(overRideMoving);	
    	}
    	//Calling for the overridemove button from the subsystem and the value of the left joystick form OI. This is executed.
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.overrideStopped();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.arm.armInterrupted();
    }
}
