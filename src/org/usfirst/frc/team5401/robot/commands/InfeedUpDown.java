package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.OI;

/**
 *
 */
public class InfeedUpDown extends Command {

		private int UD;
		private boolean udStopped;
		
    public InfeedUpDown(int UPorDOWN) {
    	requires(Robot.Infeeder);
    	UD = UPorDOWN;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	UD = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.oi.xboxAxisAsDigitalInput(RobotMap.XBOX_AXIS_LEFT_Y ,RobotMap.XBOX_CONTROLLER_OPERATOR) = UD;
    	if(UD = 1){
    		Robot.infeed.infeedUD(1);
    		udStopped = true;
    	} else if (UD = -1){
    		Robot.infeed.infeedUD(-1);
    		udStopped = true;
    	} else {
    		udStopped = false
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return udStopped;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
