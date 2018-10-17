package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class InfeedControl2 extends Command {
	
	private int upDown;
	private int open;
	private int close;
	private boolean in;
	private boolean out;
	
    public InfeedControl2() {
    	requires(Robot.shortarm);
    	
    	upDown = 0;
    	open = 0;
    	close = 0;
        in = false;
        out = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	open = -1*Robot.oi.xboxAxisAsDigitalInput(RobotMap.XBOX_AXIS_LEFT_TRIGGER, Robot.oi.xboxController_Operator);
    	close = Robot.oi.xboxAxisAsDigitalInput(RobotMap.XBOX_AXIS_RIGHT_TRIGGER, Robot.oi.xboxController_Operator);
    	upDown = -1 * Robot.oi.xboxAxisAsDigitalInput(RobotMap.XBOX_AXIS_LEFT_Y, Robot.oi.xboxController_Operator);
    	out = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_LEFT_BUMPER_OPERATOR, Robot.oi.xboxController_Operator);
    	in = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_RIGHT_BUMPER_OPERATOR, Robot.oi.xboxController_Operator);
    	
    	//Robot.infeed.clawOpenClose(openClose);
    	Robot.infeed.clawUpDown(upDown);
    	
    	if (close == 1) {
    		Robot.infeed.clawOpenClose(-close);
    	} else if (open == -1) {
    		Robot.infeed.clawOpenClose(-open);
    	}
    	
    	if(out == true)
    	{
    		Robot.infeed.feedInOut(-1);
    	}
    	else if(in == true)
    	{
    		Robot.infeed.feedInOut(1);
    	}
    	else
    	{
    		Robot.infeed.feedInOut(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
