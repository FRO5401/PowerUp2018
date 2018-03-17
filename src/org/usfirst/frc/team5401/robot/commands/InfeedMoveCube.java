package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.OI;

/**
 *
 */
public class InfeedMoveCube extends Command {

		private int MC;
		private boolean ioStopped;
		
    public InfeedMoveCube(int moveCubeDirection) {
    	requires(Robot.Infeeder);
    	MC = moveCubeDirection;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	MC = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.oi.getXboxTriggers_Operator() = MC;
    	if(MC = 1){
    		Robot.infeed.infeedIO(1);
    		ioStopped = true;
    	} else if (MC = -1){
    		Robot.infeed.infeedIO(-1);
    		ioStopped = true;
    	} else {
    		ioStopped = false
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ioStopped;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.Infeeder.infeedStopped();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.Infeeder.infeedStopped();
    }
}
