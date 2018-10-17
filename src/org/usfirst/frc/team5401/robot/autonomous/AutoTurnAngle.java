package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

/**
 * @deprecated use AutoPIDTurnAngle
 */
@Deprecated
public class AutoTurnAngle extends Command {
	
	private double desiredTurnAngleRelativeToInitAngle;
	private double currentAngleRelativeToInitAngle;
	private double initAngle;
	private boolean finished;
	private double angleReport;
	
	
    public AutoTurnAngle(double angle) {
    	//Units are degrees
    	requires(Robot.drivebase);
    	
    	desiredTurnAngleRelativeToInitAngle = angle;
    	currentAngleRelativeToInitAngle = 0;
    	initAngle = 0;
    	finished = true;
    	System.out.println("AutoTurnAngle Constructed");
    }

    // Called just before this Command runs the first time
    @Override
	protected void initialize() {
    	initAngle = Robot.drivebase.getGyroAngle();
    	currentAngleRelativeToInitAngle = 0;
    	
    	
//    	Robot.drivebase.recalibrateGyro(); 
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() {
    	//System.out.println("InitAngle: " + initAngle);
    	//System.out.println("AutoTurning: " + desiredTurnAngleRelativeToInitAngle);
    	//System.out.println("Current Angle: " + currentAngleRelativeToInitAngle);
    	if (Math.abs(desiredTurnAngleRelativeToInitAngle) <= RobotMap.ANGLE_THRESHOLD){
    		//desiredTurnAngleRelativeToInitAngle too small
    		System.out.println("AutoTurnAngle should stop1");
    		finished = true;
    	} else {
    		if (desiredTurnAngleRelativeToInitAngle > 0 && (currentAngleRelativeToInitAngle < Math.abs(desiredTurnAngleRelativeToInitAngle) - RobotMap.ANGLE_THRESHOLD)){
    			Robot.drivebase.drive(RobotMap.AUTO_TURN_SPEED * RobotMap.AUTO_TURN_PRECISION, -RobotMap.AUTO_TURN_SPEED * RobotMap.AUTO_TURN_PRECISION);
    			finished = false;
    		} else if (desiredTurnAngleRelativeToInitAngle < 0 && (currentAngleRelativeToInitAngle > RobotMap.ANGLE_THRESHOLD - Math.abs(desiredTurnAngleRelativeToInitAngle))) {
    			Robot.drivebase.drive(-RobotMap.AUTO_TURN_SPEED * RobotMap.AUTO_TURN_PRECISION, RobotMap.AUTO_TURN_SPEED * RobotMap.AUTO_TURN_PRECISION);
    			finished = false;
    		} else { //error or exactly 0
    			//Finished 
    			finished = true;
    			System.out.println("AutoTurnAngle should stop2");
    		}
    	currentAngleRelativeToInitAngle = Robot.drivebase.getGyroAngle() - initAngle;
    	}
    	double angle = Robot.drivebase.getGyroAngle();
    	SmartDashboard.putNumber("Relative to Inital Angle", currentAngleRelativeToInitAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    @Override
	protected void end() {
    	Robot.drivebase.stop();
    	System.out.println("AutoTurnAngle end()");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
	protected void interrupted() {
    	Robot.drivebase.stop();
    	System.out.println("AutoTurnAngle Interrupted");
    }
}
