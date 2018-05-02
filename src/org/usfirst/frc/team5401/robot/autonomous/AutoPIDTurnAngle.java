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
	private boolean doneTurn;
	
    public AutoPIDTurnAngle(double angle) {
    	//Units are degrees
    	requires(Robot.drivebase);
    	desiredTurnAngleRelativeToInitAnglePID = angle;
    	currentAngleRelativeToInitAngle = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.gyroReset();
    	Robot.drivebase.enableTurnPID();
    	Robot.drivebase.setTurnSetpoint(desiredTurnAngleRelativeToInitAnglePID);
        doneTurn = false;							//Initialize finish flag
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        currentAngleRelativeToInitAngle = Robot.drivebase.getGyroAngle();
    	SmartDashboard.putNumber("Relative to Inital Angle", currentAngleRelativeToInitAngle);
     	if(Robot.drivebase.getTurnPIDOnTarget())
    		{
				Robot.drivebase.disableTurnPID();
				Robot.drivebase.stop();
				doneTurn = true;
    		}	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return doneTurn;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivebase.disableTurnPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivebase.disableTurnPID();
    }
}
