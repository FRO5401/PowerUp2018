package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.WaitCommand;
import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightScale extends CommandGroup {

    public AutoRightScale() {
    	//Robot must be backward
    	//Starts at the right end of driver station wall. where the flat part hits the slant
    	//The following is a ternary operator, which is similar to a condensed if else statement
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	if(gameData.isEmpty()){
    		gameData = "XXX";
    	} 
    	
    	if(gameData.charAt(1) == 'R')
    	{
    		addSequential(new AutoPIDDrive(-270));
    		addSequential(new ArmPIDMove(RobotMap.AUTO_SCALE_SETPOINT));
    		addSequential(new AutoPIDTurnAngle(90));
    	}
    	else
    	{
    		if(gameData.charAt(0) == 'R')
    		{
            	addSequential(new AutoPIDDrive(-144));
            	addSequential(new AutoPIDTurnAngle(90));
            	addSequential(new AutoPIDDrive(20));
            	addSequential(new InfeedUpDownForAuto(-1));
            	addSequential(new WaitCommand(1));
            	addSequential(new InfeedInOutForAuto(-1));
    		}
    		else
    		{
    			addSequential(new AutoPIDDrive(-97));
    		}
    	}
    	
    }
}
