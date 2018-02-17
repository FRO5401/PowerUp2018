//NOT NEEDED (REMOVE FROM BUILD)
package org.usfirst.frc.team5401.robot.commands;



import org.usfirst.frc.team5401.robot.Robot;



import edu.wpi.first.wpilibj.command.Command;



/**

 *

 */

public class ArmMove extends Command {
	
	private boolean done; 

    public ArmMove() {
    	requires(Robot.armwrist);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    // Called just before this Command runs the first time

    @Override
	protected void initialize() {
    	done = (Robot.oi.getArmButtons() !=-1);
    	Robot.armwrist.setPoint(Robot.oi.getArmButtons());
    }
    // Called repeatedly when this Command is scheduled to run

    @Override
	protected void execute() {
    	done = (Robot.oi.getArmButtons() !=-1);
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
    	Robot.armwrist.pidStop();
    }
}