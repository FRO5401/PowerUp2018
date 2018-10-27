package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class InfeedControl extends Command {
	
	private int in;
	private int out;
	private double upDown;
	private boolean openClaw;
	
    public InfeedControl() {
    	requires(Robot.shortarm);
    	
    	upDown   = Robot.oi.xboxAxis(RobotMap.XBOX_AXIS_LEFT_Y, Robot.oi.xboxController_Operator);
    	in  = Robot.oi.xboxAxisAsDigitalInput(RobotMap.XBOX_AXIS_RIGHT_TRIGGER, Robot.oi.xboxController_Operator);
    	out = Robot.oi.xboxAxisAsDigitalInput(RobotMap.XBOX_AXIS_LEFT_TRIGGER, Robot.oi.xboxController_Operator);  
    	openClaw = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_RIGHT_BUMPER_OPERATOR, Robot.oi.xboxController_Operator); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//TODO: Get logic from Pat's claw, integrate here. 
    		//Moving the Arm
    	Robot.shortarm.moveArm(upDown);
    	
    		//Moving the Cube
    	if(in == 1){
    		Robot.shortarm.feedInOut(in);
    	}
    	else if(out == 1){
    		Robot.shortarm.feedInOut(out);
    	}
    	else {
    		Robot.shortarm.feedStop();
    	}
    	
    		//Open/Closing the Claw
    	if(openClaw){
    		Robot.shortarm.openClaw();
    	}
    	else if(!openClaw){
    		Robot.shortarm.closeClaw();
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
    	Robot.shortarm.feedStop();
    	Robot.shortarm.moveArm(0);
    }
}
