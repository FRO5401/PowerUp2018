package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmPIDMove extends Command {

	private boolean done; 
	private double setPoint;
	//Setpoint input in degrees
    public ArmPIDMove(double setPointInput) {
    	
    	requires(Robot.armwrist);
    	setPoint = setPointInput;
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    @Override
	protected void initialize() {
    	
    	done = false;
    	Robot.armwrist.setPoint(setPoint);

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() {
    	System.out.print("PID Move Exec.");
    	if (Robot.armwrist.onTarget(setPoint)){
    		//desiredTurnAngleRelativeToInitAngle too small
    		System.out.println("ArmPID should stop1");
        	Robot.armwrist.pidStop();
    		done = true;
    	} 

    	Robot.armwrist.getArmAngle();
    	Robot.armwrist.getWristAngle();
//    	done = (Robot.armwrist.onTarget(setPoint));
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    @Override
	protected void end() {
    	
    	Robot.armwrist.pidStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
	protected void interrupted() {
    	
    	Robot.armwrist.armInterrupted();
    	
    }
}
