package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RollerClawControl extends Command {
	
	private int openClose;
	private int upDown;
	private boolean in;
	private boolean out;

    public RollerClawControl() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.rollerClaw);
        
        openClose = 0;
        upDown = 0;
        in = false;
        out = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	openClose = Robot.oi.xboxAxisAsDigitalInput(RobotMap.XBOX_AXIS_LEFT_Y, Robot.oi.xboxController_Operator);
    	upDown = Robot.oi.xboxAxisAsDigitalInput(RobotMap.XBOX_AXIS_LEFT_X, Robot.oi.xboxController_Operator);
    	in = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_LEFT_BUMPER_OPERATOR, Robot.oi.xboxController_Operator);
    	out = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_RIGHT_BUMPER_OPERATOR, Robot.oi.xboxController_Operator);
    	
    	Robot.rollerClaw.clawOpenClose(openClose);
    	Robot.rollerClaw.clawUpDown(upDown);
    	Robot.rollerClaw.feedIn(in);
    	Robot.rollerClaw.feedOut(out);
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
