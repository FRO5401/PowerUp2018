package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import edu.wpi.first.wpilibj.command.Scheduler;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StabilizerDeploy extends Command {
	
	public boolean desiredState;
	
    public StabilizerDeploy(boolean stateInput) {
    	requires(Robot.climber);
    	desiredState = stateInput;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climber.changeStablizer(desiredState);
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
    	Scheduler.getInstance().add(new InfeedControl());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Scheduler.getInstance().add(new InfeedControl());
    }
}
