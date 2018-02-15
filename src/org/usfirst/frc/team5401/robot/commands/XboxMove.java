package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class XboxMove extends Command {
	double velocitySample1;
	double velocitySample2;

	
	public XboxMove() {
		velocitySample1 = 0;
		velocitySample2 = 0;
		requires(Robot.drivebase);
        System.out.println("XBoxMove Constructed");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.shiftGearHighToLow();
    	//Robot.drivebase.shiftGearLowToHigh();
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
    	boolean invert	  = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_B_DRIVER, Robot.oi.xboxController_Driver);
    	
    	boolean gearShiftLow  = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_BACK_DRIVER, Robot.oi.xboxController_Driver);
    	boolean gearShiftHigh = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_START_DRIVER, Robot.oi.xboxController_Driver);
    	
    	//Manual Gear Shift
/*    	if (gearShiftHigh){
    		Robot.drivebase.shiftGearLowToHigh();;
    	} else if (gearShiftLow){
    		Robot.drivebase.shiftGearHighToLow();
		}
*/
    	
    	//Inverts Drive
    	if (invert){
    		slew *= -1;
    		double temp = throttle;
    		throttle = reverse;
    		reverse = temp;
    	}
	
 /*   		//Alternative Upshift using velocity
    		if(velocitySample2 >= maximumVelocityForLowGear){
    			Robot.drivebase.shiftGearLowToHigh();
    		}


    		//Uses Current Velocity to Shift High to Low
    		if(velocitySample2 <= minimumVelocityForHighGear){
    			Robot.drivebase.shiftGearHighToLow();
    		}

*/    		
    	//Gear Shift Done
    	

    	//Driving Code
    	double right = 0, left = 0, sensitivity;
    	
    	if (precision) { //Sets drive precision based on RobotMap and Precision Mode
    		sensitivity	=	RobotMap.DRIVE_SENSITIVITY_PRECISE;
    	} else {
    		sensitivity	=	RobotMap.DRIVE_SENSITIVITY_DEFAULT;
    	}
    	
    	if (brake){
    		left  = 0;
    		right = 0;
    	} else if(!turn){
    		if (slew > RobotMap.DRIVE_THRESHHOLD){ //If Slew is positive (Thumbstick pushed right), go Right
    			left  = (throttle - reverse) * sensitivity;					//Send Left full power
    			right = (throttle - reverse) * sensitivity * (1 - slew);	//Send Right partial power, tempered by how hard the thumbstick is being pushed
    		} else if (slew < (-1 * RobotMap.DRIVE_THRESHHOLD)){ //If Slew is negative (Thumbstick pushed left), go Left
    			left  = (throttle - reverse) * sensitivity * (1 + slew); //Send Left partial power, tempered by how hard thumbstick is being pushed left
    			right = (throttle - reverse) * sensitivity; 			//Send right full power
    		} else { //Drive forward/back normally
    			left  = (throttle - reverse) * sensitivity;
    			right = (throttle - reverse) * sensitivity;
    		}
    	} else {
    		if (invert){
    			slew *= -1;
    		}
    		if (Math.abs(slew) > RobotMap.DRIVE_THRESHHOLD){
    			left  = RobotMap.DRIVE_SPIN_SENSITIVITY * slew;
    			right = RobotMap.DRIVE_SPIN_SENSITIVITY * slew * -1;
    		}
    	}
    	
    	Robot.drivebase.drive(left, right);
    
    	
    	
    	
/*****Shifting Gear Code*********/
    	Robot.drivebase.getEncoderDistance(3);
/*    	//Backlogs the old final velocity (velocity 2) into the new initial velocity (velocity 1)
    	velocitySample1 = velocitySample2;
*/   	
    	//Gets new final velocity
    	velocitySample2 = Robot.drivebase.getVelocityOfRobot();
    	    	
    	
    	//												vvvvv this is for no shifting at acceleration = 0 when robot is totally still, might be unnecessary
/*    	if(slew <= 0 + RobotMap.DRIVE_THRESHHOLD && velocitySample2 != 0){
    	//Uses average acceleration for gear shifting up to higher speeds
    	//0 is just there to understand original logic
		//Commented out because of problems of unwanted shifting up if running at a low constant velocity
    		//if(Math.abs(avgAccelerationFromSamples) <= 0 + accelerationThreshhold){
    			//Robot.drivebase.shiftGearLowToHigh();
    		//}
    		
    	//Alternative Upshift using velocity
    		if(Math.abs(velocitySample2) >= MAXIMUM_VELOCITY_FOR_LOW_GEAR){
    			Robot.drivebase.shiftGearLowToHigh();
    			Robot.drivebase.setDPPHighGear();
    		}


    	//Uses Current Velocity to Shift High to Low
    		if(Math.abs(velocitySample2) <= MINIMUM_VELOCITY_FOR_HIGH_GEAR){
    			Robot.drivebase.shiftGearHighToLow();
    			Robot.drivebase.setDPPLowGear();
    		}

    	//Alternative Downshift Due to release in Thottle
    		//if(Math.abs(thottle) <= 0 + RobotMap.DRIVE_THRESHHOLD) {
    		//	Robot.drivebase.shiftGearHighToLow();
    		//}
    		
    	}
    	//Gear Shift Done
*/    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() { //SHOULD never run
    	Robot.drivebase.stop();
    	System.out.println("XboxMove end()");
//    	this.cancel(); //not needed
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivebase.stop();
    	System.out.println("XboxMove Interrupted");
//    	this.cancel(); //not needed
    }
}
