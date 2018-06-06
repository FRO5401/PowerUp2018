package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionTurn extends Command {

	private double desiredTurnAngleRelativeToInitAnglePID;
	private double currentAngleRelativeToInitAngle;
	private boolean doneTurn;
	
    public VisionTurn() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.dumbcamera);
        requires(Robot.drivebase);
        
    	desiredTurnAngleRelativeToInitAnglePID = Robot.dumbcamera.visionLoopSynchronized();
    	currentAngleRelativeToInitAngle = 0;
    	doneTurn = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.gyroReset();
    	Robot.drivebase.enableTurnPID();
    	Robot.drivebase.setTurnSetpoint(desiredTurnAngleRelativeToInitAnglePID);
        doneTurn = false;
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
    	//Something to note: if the vision processing does not find anything, the coordinates 0,0 will be returned.
    	//As a result the robot should turn one direction to the end of the camera vision. 
    	//NEED to add a conditional if that movement is done, repeat the action.
    	Robot.drivebase.disableTurnPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivebase.disableTurnPID();
    }
}
