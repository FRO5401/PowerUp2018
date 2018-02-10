package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmPIDMove extends Command {

	private boolean done; 
	private double setPoint;
	
    public ArmPIDMove(double setPointInput) {
    	
    	requires(Robot.armwrist);
    	setPoint = setPointInput / RobotMap.ANGLE_PER_PULSE;
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	done = (Robot.oi.getArmButtons() == -1);
    	Robot.armwrist.setPoint(setPoint);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.armwrist.getArmAngle();
    	done = (Robot.oi.getArmButtons() !=-1);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.armwrist.pidStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	Robot.armwrist.armInterrupted();
    	
    }
}
