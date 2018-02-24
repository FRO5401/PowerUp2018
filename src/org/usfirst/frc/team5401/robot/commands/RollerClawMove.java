package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RollerClawMove extends Command {

    public RollerClawMove() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rollerclaw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean clawHeightsChange = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_L3_OPERATOR, Robot.oi.xboxController_Operator);
    	boolean rollerClawMotorIn = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_RIGHT_BUMPER_OPERATOR, Robot.oi.xboxController_Operator);
    	boolean rollerClawMotorOut = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_LEFT_BUMPER_OPERATOR, Robot.oi.xboxController_Operator);
    	
    	int expandOrContractClaw = Robot.oi.xboxAxisAsDigitalInput(RobotMap.XBOX_AXIS_LEFT_Y, Robot.oi.xboxController_Operator);
    	
    	Robot.rollerclaw.checkVictor();
    	
    	if(rollerClawMotorIn)
    	{
    		Robot.rollerclaw.rollerInOut(1);
    	}
    	else if(rollerClawMotorOut)
		{
			Robot.rollerclaw.rollerInOut(-1);
		}
    	else
    	{
    		Robot.rollerclaw.rollerInOut(0);
    	}
    	
    	if(clawHeightsChange)
    	{
    		if(expandOrContractClaw == 1)
    		{
    			Robot.rollerclaw.rollerClawShortChange(expandOrContractClaw);
    			Robot.rollerclaw.rollerClawLongChange(expandOrContractClaw);
    		}
    		else if(expandOrContractClaw == -1)
    		{
    			Robot.rollerclaw.rollerClawShortChange(expandOrContractClaw);
    			Robot.rollerclaw.rollerClawLongChange(expandOrContractClaw);
    		}
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
