package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climb extends Command {
	

    public Climb() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double POVinput = Robot.oi.xboxDPad(Robot.oi.xboxController_Operator);
    	System.out.println("POV " + POVinput);
    	
    	if (POVinput == 315 || POVinput == 45 || POVinput == 0) {
    		Robot.climber.climberStartMotors(1);//up
    	} else if (POVinput == 135 || POVinput == 225 || POVinput == 180) {
    		Robot.climber.climberStartMotors(-1);//down
    	} else {
    		Robot.climber.stopClimber();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climber.stopClimber();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.climber.stopClimber();
    }
}
