package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.subsystems.ShortArm;

/**
 *
 */
public class InfeedControl extends Command {
	
	private int in;
	private int out;
	private boolean armOverride;
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
    	Robot.shortarm.setTalonSRXNeutralMode(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	upDown   = Robot.oi.xboxAxis(RobotMap.XBOX_AXIS_LEFT_Y, Robot.oi.xboxController_Operator);
    	in  = Robot.oi.xboxAxisAsDigitalInput(RobotMap.XBOX_AXIS_RIGHT_TRIGGER, Robot.oi.xboxController_Operator);
    	out = Robot.oi.xboxAxisAsDigitalInput(RobotMap.XBOX_AXIS_LEFT_TRIGGER, Robot.oi.xboxController_Operator);  
    	openClaw = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_RIGHT_BUMPER_OPERATOR, Robot.oi.xboxController_Operator); 
    	armOverride = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_L3_OPERATOR, Robot.oi.xboxController_Operator); 
    	
    		//Overrides the arm (manual move)
    	if(armOverride){
    		Robot.shortarm.setArmBrake(true);
    		Robot.shortarm.manualOverride((-1 * upDown) * RobotMap.ARM_SPEED);
    	} else {
    		Robot.shortarm.manualOverride(0);
    		Robot.shortarm.setArmBrake(false);
    	}
    	
    		//Moving the Cube
    	if(in == 1){
    		Robot.shortarm.feedIn(in);
    	}
    	else if(out == 1){
    		Robot.shortarm.feedOut(out);
    	}
    	else if(in == 1 && out == 1) {
    		System.out.println("FeedStop");
    		Robot.shortarm.feedStop();
    	}
    	else {
    		System.out.println("FeedStop");
    		Robot.shortarm.feedStop();
    	}
    	
    		//Open/Closing the Claw
    	if(openClaw){
    		Robot.shortarm.closeClaw();
    	}
    	else if(!openClaw){
    		Robot.shortarm.openClaw();
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
