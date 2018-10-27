package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 *
 */
public class XboxMove extends Command {

	public XboxMove() {
		requires(Robot.drivebase);
        //System.out.println("XBoxMove Constructed");
    }

    // Called just before this Command runs the first time
    @Override
	protected void initialize() {
    	Robot.drivebase.shiftGearHighToLow();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Angle XboxDrive", Robot.drivebase.getGyroAngle());
    	
    	double  slew      = Robot.oi.xboxAxis(RobotMap.XBOX_AXIS_LEFT_X, Robot.oi.xboxController_Driver);
    	double 	throttle  = Robot.oi.xboxAxis(RobotMap.XBOX_AXIS_RIGHT_TRIGGER, Robot.oi.xboxController_Driver);
    	double 	reverse   = Robot.oi.xboxAxis(RobotMap.XBOX_AXIS_LEFT_TRIGGER, Robot.oi.xboxController_Driver);
    	boolean precision = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_LEFT_BUMPER_DRIVER, Robot.oi.xboxController_Driver);
    	boolean brake	  = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_RIGHT_BUMPER_DRIVER, Robot.oi.xboxController_Driver);
    	boolean turn	  = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_L3_DRIVER, Robot.oi.xboxController_Driver);
    	
    	boolean gearShiftLow  = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_BACK_DRIVER, Robot.oi.xboxController_Driver);
    	boolean gearShiftHigh = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_START_DRIVER, Robot.oi.xboxController_Driver);
    	
    	//Manual Gear Shift
    	if (gearShiftHigh){
    		Robot.drivebase.shiftGearLowToHigh();
    	} 
    	else if (gearShiftLow){
    		Robot.drivebase.shiftGearHighToLow();
    	}

    	//Driving Code
    	double right = 0, left = 0, sensitivity = 0;
    	
    	if (precision) { //Sets drive precision based on RobotMap and Precision Mode
    		sensitivity	=	RobotMap.DRIVE_SENSITIVITY_PRECISE;
    	}
    	else {
    		sensitivity	=	RobotMap.DRIVE_SENSITIVITY_DEFAULT;
    	}
    	
    	if (brake){
    		left  = 0;
    		right = 0;
    	}
    	else if(!turn){
    		if (slew > RobotMap.DRIVE_THRESHHOLD){ //If Slew is positive (Thumbstick pushed right), go Right
    			left  = (throttle - reverse) * sensitivity;					//Send Left full power
    			right = (throttle - reverse) * sensitivity * (1 - slew);	//Send Right partial power, tempered by how hard the thumbstick is being pushed
    		} 
    		else if(slew < (-1 * RobotMap.DRIVE_THRESHHOLD)){ //If Slew is negative (Thumbstick pushed left), go Left
    			left  = (throttle - reverse) * sensitivity * (1 + slew); //Send Left partial power, tempered by how hard thumbstick is being pushed left
    			right = (throttle - reverse) * sensitivity; 			//Send right full power
    		}
    		else { //Drive forward/back normally
    			left  = (throttle - reverse) * sensitivity;
    			right = (throttle - reverse) * sensitivity;
    		}
    	} 
    	else {
    		if (Math.abs(slew) > RobotMap.DRIVE_THRESHHOLD){
    			left  = RobotMap.DRIVE_SPIN_SENSITIVITY * slew;
    			right = RobotMap.DRIVE_SPIN_SENSITIVITY * slew * -1;
    		}
    	}
    	
    	Robot.drivebase.drive(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
	protected void end() { //SHOULD never run
    	Robot.drivebase.stopMotors();
    	//System.out.println("XboxMove end()");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
	protected void interrupted() {
    	Robot.drivebase.stopMotors();
    	//System.out.println("XboxMove Interrupted");
    }
}
