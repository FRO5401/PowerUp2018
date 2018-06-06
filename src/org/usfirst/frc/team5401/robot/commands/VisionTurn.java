package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.autonomous.*;
/**
 *
 */
public class VisionTurn extends Command {

	private double desiredTurnAngleRelativeToInitAngle;
	
    public VisionTurn() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.drivebase);
        requires(Robot.dumbcamera);
        desiredTurnAngleRelativeToInitAngle = Robot.dumbcamera.visionLoopSynchronized();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Scheduler.getInstance().add(new AutoPIDTurnAngle(desiredTurnAngleRelativeToInitAngle));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}