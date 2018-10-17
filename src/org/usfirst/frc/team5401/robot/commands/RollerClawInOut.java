package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	While held, run roller. Use this command from OI
 *
 */
public class RollerClawInOut extends Command {
	
	int rollerDirection;
	boolean limitSwitch;

    public RollerClawInOut(int direction) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.rollerclaw);
        rollerDirection = direction;
        limitSwitch = false;

    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.rollerclaw.rollerInOut(rollerDirection);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        limitSwitch = Robot.rollerclaw.getLimitSwitch();
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
