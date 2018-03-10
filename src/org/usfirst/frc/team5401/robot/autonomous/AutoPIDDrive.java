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
    @Override
	protected void initialize() {
    	Robot.drivebase.shiftGearHighToLow();
    	Robot.drivebase.encoderReset();
    	
    	//double distance = SmartDashboard.getNumber("DriveStraight Distance", 0);
    	Robot.drivebase.enablePID();
    	Robot.drivebase.setSetpoint(distance);    	
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() {
    	SmartDashboard.putNumber("navx Angle", Robot.drivebase.getGyroAngle());
    	SmartDashboard.putNumber("Right SetPOINT", Robot.drivebase.getSetpoint(2));
    	SmartDashboard.putNumber("LEFT SetPOINT", Robot.drivebase.getSetpoint(1));
    	
    	SmartDashboard.putNumber("Right Error", Math.abs(Robot.drivebase.getSetpoint(2) - Robot.drivebase.getEncoderDistance(2)));
    	SmartDashboard.putNumber("LEft Error", Math.abs(Robot.drivebase.getSetpoint(1) - Robot.drivebase.getEncoderDistance(1)));
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
    	//Need verification that left and right side are at where it is needed																		vv Right set point is negative. Negative sign cancels it out
        if ((Math.abs(Robot.drivebase.getSetpoint(1) - Robot.drivebase.getEncoderDistance(1)) < RobotMap.DRIVE_PID_ABSOLUTE_TOLERANCE) 	&&	(Math.abs(Robot.drivebase.getSetpoint(2) - Robot.drivebase.getEncoderDistance(2)) < RobotMap.DRIVE_PID_ABSOLUTE_TOLERANCE)) {
        	return true;
        } else {
        	return false;
        }
    }

    // Called once after isFinished returns true
    @Override
	protected void end() {
    	Robot.drivebase.disablePID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
	protected void interrupted() {
    	Robot.drivebase.disablePID();
    }
}
