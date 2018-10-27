package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.subsystems.CompressorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CompressorToggle extends Command {

	private boolean compressorToggle;
	
    public CompressorToggle() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.compressorsubsystem);
        
        compressorToggle = Robot.oi.xboxButton(RobotMap.XBOX_BUTTON_START_OPERATOR, Robot.oi.xboxController_Operator);
    }

    // Called just before this Command runs the first time
    @Override
	protected void initialize() {
    	if(compressorToggle && Robot.compressorsubsystem.isEnabled()){
    		Robot.compressorsubsystem.stopCompressor();
    	}
    	else if(compressorToggle && !Robot.compressorsubsystem.isEnabled()){
    		Robot.compressorsubsystem.startCompressor();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    @Override
	protected void end() {
    	//end() does nothing because this command toggles the state
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
	protected void interrupted() {
    	Robot.compressorsubsystem.stopCompressor();
    	//System.out.println("CompressorToggle Interrupted");
    }
}
