package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class InfeedControl extends Command {
	
	private int inOut;
	private double upDown;
	private boolean openClaw;
	
    public InfeedControl() {
    	requires(Robot.shortarm);
    	
    	upDown   = Robot.oi.xboxAxis(RobotMap.XBOX_AXIS_LEFT_Y, Robot.oi.xboxController_Operator);
    	inOut    = Robot.oi.xboxAxisAsDigitalInput(RobotMap.XBOX_AXIS_RIGHT_TRIGGER, Robot.oi.xboxController_Operator);
    	openClaw = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_RIGHT_BUMPER_OPERATOR, Robot.oi.xboxController_Operator); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//TODO: Get logic from Pat's claw, integrate here. 
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
    	inOut = 0;
    }
}
