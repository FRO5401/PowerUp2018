package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.*;

/**
 *
 */
public class AutoPIDDriveEncoderCorrection extends Command {
	
	double distance;
	boolean isDone = false;
	double leftEncoder, rightEncoder;

    public AutoPIDDriveEncoderCorrection(double desiredDistance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivebase);
    	distance = desiredDistance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.shiftGearHighToLow();
    	Robot.drivebase.encoderReset();
    	
    	isDone = false;
    	leftEncoder = Robot.drivebase.getEncoderDistance(1);
    	rightEncoder = Robot.drivebase.getEncoderDistance(2);
    	
    	Robot.drivebase.enableDriveStraightPID();
    	Robot.drivebase.setDriveStraightSetpoint(distance);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Math.abs(leftEncoder - rightEncoder) >= RobotMap.DRIVE_PID_CORRECTION_THRESHOLD) {
    		
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
