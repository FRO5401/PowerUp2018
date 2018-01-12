package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.commands.XboxMove;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * 
 */
public class AutoTurnAngle extends Command {
	
	private double desiredTurnAngleRelativeToInitAngle;
	private double currentAngleRelativeToInitAngle;
	private double initAngle;
	private boolean finished;
	private double angleReport;
	
	//Constants
	private final double angleThreshold;
	private final double autoTurnSpeed;
	private final double autoTurnPrecision;
	private final boolean modeAuto;
	private final boolean modeAutoTarget;

    public AutoTurnAngle(double angle, boolean inAuto, boolean autoTarget) {
    	//Initialize Constants
    	angleThreshold	= 1; 		//Turn angle in degrees
    	autoTurnSpeed	= 0.95;
    	autoTurnPrecision = .5;
    	
    	modeAuto = inAuto;
    	modeAutoTarget = autoTarget;
    	
    	requires(Robot.drivebase);
    	
    	desiredTurnAngleRelativeToInitAngle = angle;
    	currentAngleRelativeToInitAngle = 0;
    	initAngle = 0;
    	finished = true;
    	System.out.println("AutoTurnAngle Constructed");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initAngle = Robot.drivebase.getGyroAngle();
    	currentAngleRelativeToInitAngle = 0;
    	
    	if (modeAutoTarget){
    		desiredTurnAngleRelativeToInitAngle = 90;
    	}
    	
//    	Robot.drivebase.recalibrateGyro(); 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println("InitAngle: " + initAngle);
    	//System.out.println("AutoTurning: " + desiredTurnAngleRelativeToInitAngle);
    	//System.out.println("Current Angle: " + currentAngleRelativeToInitAngle);
    	if (Math.abs(desiredTurnAngleRelativeToInitAngle) <= angleThreshold){
    		//desiredTurnAngleRelativeToInitAngle too small
    		System.out.println("AutoTurnAngle should stop1");
    		finished = true;
    	} else {
    		if (desiredTurnAngleRelativeToInitAngle > 0 && (currentAngleRelativeToInitAngle < Math.abs(desiredTurnAngleRelativeToInitAngle) - angleThreshold)){
    			Robot.drivebase.drive(-autoTurnSpeed * autoTurnPrecision, autoTurnSpeed * autoTurnPrecision);
    			finished = false;
    		} else if (desiredTurnAngleRelativeToInitAngle < 0 && (currentAngleRelativeToInitAngle > angleThreshold - Math.abs(desiredTurnAngleRelativeToInitAngle))) {
    			Robot.drivebase.drive(autoTurnSpeed * autoTurnPrecision, -autoTurnSpeed * autoTurnPrecision);
    			finished = false;
    		} else { //error or exactly 0
    			//Finished
    			finished = true;
    			System.out.println("AutoTurnAngle should stop2");
    		}
    	currentAngleRelativeToInitAngle = Robot.drivebase.getGyroAngle() - initAngle;
    	}
    	double angle = Robot.drivebase.getGyroAngle();
    	SmartDashboard.putNumber("Gyro Angle", currentAngleRelativeToInitAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (modeAuto) {	 //if in auto, stop motors
    		Robot.drivebase.stop();
    	} else { //if in teleop, start xboxmove
    		Scheduler.getInstance().add(new XboxMove());
    	}
    	System.out.println("AutoTurnAngle end()");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivebase.stop();
    	System.out.println("AutoTurnAngle Interrupted");
    }
}
