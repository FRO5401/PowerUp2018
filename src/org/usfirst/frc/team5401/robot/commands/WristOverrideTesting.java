package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class WristOverrideTesting extends Command {
	
	boolean inOrOut;
	String  pneumaticName;
	
    public WristOverrideTesting(String name, boolean inOrOutInput) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.armwrist);
        pneumaticName = name;
        inOrOut = inOrOutInput;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(inOrOut == true)
    	{	
   			Robot.armwrist.longWristUpDown(1);
   		}
   		else if(inOrOut == false)
   		{
   		
    		Robot.armwrist.longWristUpDown(-1);	
    	}
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
