package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class InfeederPressure extends Command {

    public InfeederPressure() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.infeeder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.infeeder.infeederPressure();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
