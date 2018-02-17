package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WristMove extends Command {
	
	int wristChangePoint;

    public WristMove() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.armwrist);
    	wristChangePoint = 0;
    }

    // Called just before this Command runs the first time
    @Override
	protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() {
       	wristChangePoint = Robot.oi.readXboxLeftY_Axis();
    	Robot.armwrist.longWristUpDown(wristChangePoint);
    	Robot.armwrist.shortWristUpDown(wristChangePoint);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
	protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
	protected void interrupted() {
    }
}
