package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightScale extends CommandGroup {

    public AutoRightScale() {
    	//Start at Auto Position #6
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
    			addSequential(new AutoRightSideSwitch());
    		}
    		else
    		{
    			addSequential(new BaselineOnly());
    		}
    	}
    	
    }
}
