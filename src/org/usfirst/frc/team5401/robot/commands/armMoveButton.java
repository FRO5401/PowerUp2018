package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class armMoveButton extends Command {

	private boolean done; 
	
    public armMoveButton() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	done = (Robot.oi.getArmButtons() !=-1);
    	Robot.arm.SetPoint(Robot.oi.getArmButtons());

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	done = (Robot.oi.getArmButtons() !=-1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.pidStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.arm.pidStop();
    }
}
