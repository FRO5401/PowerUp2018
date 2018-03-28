package org.usfirst.frc.team5401.robot.autonomous;

import org.usfirst.frc.team5401.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class InfeedInOutForAuto extends Command {

	private int directionControl;
	
	//1 for out, 0 for stop, -1 for in
    public InfeedInOutForAuto(int directionInput) {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.infeed);
        directionControl = directionInput;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Infeed for Auto Init");
    	Robot.infeed.feedInOut(directionControl);
    	
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
