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
	private boolean doneTurn;
	
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
    	Robot.drivebase.enableDriveStraightPID();
    	Robot.drivebase.setDriveStraightSetpoint(distance);    	
    	doneTurn = false;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() {
    	//System.out.println("AutoPIDDrive Exe");
    	SmartDashboard.putNumber("navx Angle", Robot.drivebase.getGyroAngle());
    	SmartDashboard.putNumber("Right SetPOINT", Robot.drivebase.getDriveStraightSetpoint(2));
    	SmartDashboard.putNumber("LEFT SetPOINT", Robot.drivebase.getDriveStraightSetpoint(1));
    	
    	SmartDashboard.putNumber("Right Error", Math.abs(Robot.drivebase.getDriveStraightSetpoint(2) - Robot.drivebase.getEncoderDistance(2)));
    	SmartDashboard.putNumber("LEft Error", Math.abs(Robot.drivebase.getDriveStraightSetpoint(1) - Robot.drivebase.getEncoderDistance(1)));
    	Robot.drivebase.getError();
        if ((Math.abs(Robot.drivebase.getDriveStraightSetpoint(1) - Robot.drivebase.getEncoderDistance(1)) < RobotMap.DRIVE_PID_ABSOLUTE_TOLERANCE) 	&&	(Math.abs(Robot.drivebase.getDriveStraightSetpoint(2) - Robot.drivebase.getEncoderDistance(2)) < RobotMap.DRIVE_PID_ABSOLUTE_TOLERANCE))
		{
	    	Robot.drivebase.disableDriveStraightPID();
			Robot.drivebase.stop();
			doneTurn = true;
		}	
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
/*    	//Need verification that left and right side are at where it is needed																		vv Right set point is negative. Negative sign cancels it out
        if ((Math.abs(Robot.drivebase.getDriveStraightSetpoint(1) - Robot.drivebase.getEncoderDistance(1)) < RobotMap.DRIVE_PID_ABSOLUTE_TOLERANCE) 	&&	(Math.abs(Robot.drivebase.getDriveStraightSetpoint(2) - Robot.drivebase.getEncoderDistance(2)) < RobotMap.DRIVE_PID_ABSOLUTE_TOLERANCE)) {
        	return true;
        } else {
        	return false;
        }

  */
    	System.out.print("Left Encoder: " + Robot.drivebase.getEncoderDistance(1) + " ");
    	System.out.println("Right Encoder: " + Robot.drivebase.getEncoderDistance(2) + " ");
    	return doneTurn;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivebase.disableDriveStraightPID();
    	System.out.println("AUtoPIDDrive Finsihed");
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivebase.disableDriveStraightPID();
    }
}
