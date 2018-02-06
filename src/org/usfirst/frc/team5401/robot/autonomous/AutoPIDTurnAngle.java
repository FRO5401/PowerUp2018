package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.XboxMove;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * 
 */
public class AutoPIDTurnAngle extends Command {
	
	private double desiredTurnAngleRelativeToInitAnglePID;
	private double currentAngleRelativeToInitAngle;
	
    public AutoPIDTurnAngle(double angle) {
    	//Units are degrees
    	requires(Robot.drivebase);
    	desiredTurnAngleRelativeToInitAnglePID = angle;
    	currentAngleRelativeToInitAngle = 0;
    	System.out.println("AutoTurnAngle Constructed");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.gyroReset();
    	Robot.drivebase.enableTurnPID();
    	Robot.drivebase.setTurnSetpoint(desiredTurnAngleRelativeToInitAnglePID);
    	System.out.println("Initializing Auto PID Angle Turn");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngleRelativeToInitAngle = Robot.drivebase.getGyroAngle();
    	SmartDashboard.putNumber("Relative to Inital Angle", currentAngleRelativeToInitAngle);
    	
    	System.out.println("Executing Auto PID Angle Turn");
    	System.out.println("Left Error: " + Robot.drivebase.getLeftTurnPIDError() + " Right Error: " + Robot.drivebase.getRightTurnPIDError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.drivebase.getTurnPIDOnTarget("left") && Robot.drivebase.getTurnPIDOnTarget("right"))
    	{
    		return true;
    	}	else	{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivebase.disableTurnPID();
    	System.out.println("AutoPIDTurnAngle end()");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivebase.disableTurnPID();
    	System.out.println("AutoPIDTurnAnglePID Interrupted");
    }
}
