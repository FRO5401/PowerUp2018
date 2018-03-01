package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberPlatformChangePosition extends Command {
	
	public int climberPlatformState;
	
    public ClimberPlatformChangePosition(int climberPlatformStateInput) {
    	requires(Robot.climber);
    	climberPlatformState = climberPlatformStateInput;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climber.changeClimberPlatforms(climberPlatformState);
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
