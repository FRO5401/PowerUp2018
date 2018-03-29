package org.usfirst.frc.team5401.robot.autonomous;

import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.ArmPIDMove;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftScale extends CommandGroup {

    public AutoLeftScale() {
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	//Robot must be backward
    	//Starts at the left end of driver station wall. where the flat part hits the slant
    	if(gameData.isEmpty()){
    		gameData = "XXX";
    	}
    	
    	if(gameData.charAt(1) == 'L')
    	{
    		addSequential(new AutoPIDDrive(-270));
    		addSequential(new ArmPIDMove(RobotMap.AUTO_SCALE_SETPOINT));
    		addSequential(new AutoPIDTurnAngle(-90));
    	}
    	else
    	{
    		if(gameData.charAt(0) == 'L')
    		{
        		addSequential(new AutoPIDDrive(-144));
            	addSequential(new AutoPIDTurnAngle(-90));
            	addSequential(new AutoPIDDrive(20));
            	addSequential(new InfeedUpDownForAuto(-1));
            	addSequential(new InfeedInOutForAuto(-1));
    		}
    		else
    		{
    			addSequential(new AutoPIDDrive(-97));
    		}
    	}
    	
    }
  }