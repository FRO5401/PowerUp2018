package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climb extends Command {
	
	private double input;
	
	private boolean climbTopSwitch;
	private boolean climbBottomSwitch;

    public Climb(double direction) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    	input = direction;
    	
    	climbTopSwitch = false;
    	climbBottomSwitch = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	climbTopSwitch = Robot.climber.reportTopClimbSwitch();
    	climbBottomSwitch = Robot.climber.reportBottomClimbSwitch();
    	
    	if(input > 0 || (climbTopSwitch || climbBottomSwitch)){
    		Robot.climber.startClimber();
    		Robot.climber.climberSwitchControl(input);
    	}
    	else if(input < 0 || !(climbTopSwitch || climbBottomSwitch)){
    		Robot.climber.stopClimber();
    		Robot.climber.climberSwitchControl(input);
    	}
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
    	Robot.climber.stopClimber();
    	System.out.print("Climber Interrupted");
    }
}
