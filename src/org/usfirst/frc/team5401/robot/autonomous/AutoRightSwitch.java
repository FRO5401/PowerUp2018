package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSwitch extends CommandGroup {

    public AutoRightSwitch() {
    	//The following is a ternary operator, which is similar to a condensed if else statement
    	String gameData = (DriverStation.getInstance().getGameSpecificMessage() == null) ? "XXX" : DriverStation.getInstance().getGameSpecificMessage();
    	if(gameData.charAt(0) == 'R')
    	{
    	//Start at Auto Position #5. Drive Forward 140 inches, place block on switch
    		addSequential(new AutoPIDDrive(140.595));
    		//addSequential(new xxxx());
    
    	}
    	else 
    	{    	
    	//Start at Auto Position #5. Drive Forward 55 inches, turn left 
    	//(Figure out specifics later)
    		addSequential(new AutoPIDDrive(55));
    		addSequential(new AutoTurnAngle(-90));
    		//addSequential(new xxxx());
    	}
    }
}
