package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;

/**
 *
 */
public class AutoPIDDrive extends Command {
	
	
    public AutoPIDDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivebase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.encoderReset();
    	
    	double distance = SmartDashboard.getNumber("DriveStraight Distance", 0);
    	//double distance = 100;
    	Robot.drivebase.setSetpoint(distance);
    	Robot.drivebase.enablePID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Math.abs(Robot.drivebase.getSetpoint() - Robot.drivebase.getPosition()) < .5) {
        	Robot.drivebase.disable();
        	return true;
        } else {
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}