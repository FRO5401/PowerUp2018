package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.OI;

/**
 *
 */
public class InfeedCloseOpen extends Command {

		private int CO;
		private boolean coStopped;
		
    public InfeedCloseOpen(int CloseorOpen) {
    	requires(Robot.Infeeder);
    	CO = CloseorOpen;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	CO = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.oi.xboxAxisAsDigitalInput(RobotMap.XBOX_AXIS_LEFT_Y ,RobotMap.XBOX_CONTROLLER_OPERATOR) = CO;
    	if(CO = 1){
    		Robot.infeed.infeedOC(1);
    		coStopped = true;
    	} else if (CO = -1){
    		Robot.infeed.infeedOC(-1);
    		coStopped = true;
    	} else {
    		coStopped = false
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return COStopped;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
