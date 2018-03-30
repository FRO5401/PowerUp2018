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
    	//System.out.print("PID Move Exec.");
    	if(Robot.armwrist.getArmAngle() > 100)
    	{
    		Robot.armwrist.setMaxSpeed(0.4, -0.4);	
    	}
    	else if(Robot.armwrist.getArmAngle() > 65)
    	{
    		Robot.armwrist.setMaxSpeed(0.6, -0.3);
    	}
    	else if(Robot.armwrist.getArmAngle() > 30)
    	{
    		Robot.armwrist.setMaxSpeed(0.6, RobotMap.ARM_PEAK_OUTPUT_REVERSE);
    	}
    	else if(Robot.armwrist.getArmAngle() <= 30)
    	{    		
    		System.out.println("SLOW DOWN ARM");
    		Robot.armwrist.setMaxSpeed(RobotMap.ARM_PEAK_OUTPUT_FORWARD, RobotMap.ARM_PEAK_OUTPUT_REVERSE);
    	}
    	else
    	{
    		Robot.armwrist.setMaxSpeed(RobotMap.ARM_PEAK_OUTPUT_FORWARD, RobotMap.ARM_PEAK_OUTPUT_REVERSE);
    	}
    	if (Robot.armwrist.onTarget(setPoint)){
    		//desiredTurnAngleRelativeToInitAngle too small
    		System.out.println("ArmPID should stop1");
        	Robot.armwrist.pidStop();
    		done = true;
    	} 

    	Robot.armwrist.getArmAngle();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    @Override
	protected void end() {
    	//System.out.println("PIDMove ENDING");
    	Robot.armwrist.pidStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
	protected void interrupted() {
    	
    	Robot.armwrist.armInterrupted();
    	
    }
}
