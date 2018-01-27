package org.usfirst.frc.team5401.robot.autonomous;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoPIDDrive extends Command {
	
	double distance;
	
    public AutoPIDDrive(double desiredDistance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivebase);
    	
    	distance = desiredDistance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.shiftGearHighToLow();
    	Robot.drivebase.encoderReset();
    	
    	//double distance = SmartDashboard.getNumber("DriveStraight Distance", 0);
    	Robot.drivebase.setSetpoint(distance);
    	Robot.drivebase.enablePID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Math.abs(Robot.drivebase.getSetpoint() - Robot.drivebase.getPosition()) < RobotMap.PID_ABSOLUTE_TOLERANCE) {
        	Robot.drivebase.disablePID();
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
