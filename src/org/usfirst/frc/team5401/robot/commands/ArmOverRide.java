package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.OI;
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
    protected void initialize() {
    	Robot.armwrist.setTalonSRXNeutralMode(2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Joystick up gives a negative value. The negative sign swtiches that.
    	double overRideMoving = -Robot.oi.getXboxRightStickY_Operator() * RobotMap.ARM_OVERRIDE_PRECISION;
    	System.out.println(overRideMoving);
    	boolean overRideButton = Robot.oi.getXboxOperator_R3();
    	//calling to the button as well as the joystick
    	Robot.armwrist.getArmAngle();
    	
    	if(overRideButton == true)
    	{	
    		if(Math.abs(overRideMoving) > RobotMap.ARM_OVERRIDE_JOYSTICK_THRESHOLD)
    		{
    			Robot.armwrist.setBrake(false);
    			Robot.armwrist.overrideMove(overRideMoving);
    		}
    		else
    		{	
    			Robot.armwrist.setBrake(false);
    			Robot.armwrist.overrideMove(0);
    		}
    	}
    	//Calling for the overridemove button from the subsystem and the value of the left joystick form OI. This is executed.
    	overrideEnabled = (!overRideButton);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return overrideEnabled;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Arm Override End");
    	Robot.armwrist.overrideStopped();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("Arm Override Interrupted");
    	Robot.armwrist.armInterrupted();
    }
}