package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSwitch extends CommandGroup {

    public AutoRightSwitch() {
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	if(gameData.charAt(0) == 'R')
    	{
    	//Start at Auto Position #5. Drive Forward 140 inches, place block on switch
    		addSequential(new AutoDrive(140.595, 1));
    		//addSequential(new xxxx());
    
    	}
    	else 
    	{    	
    	//Start at Auto Position #5. Drive Forward 55 inches, turn left 
    	//(Figure out specifics later)
    		addSequential(new AutoDrive(55, 1));
    		addSequential(new AutoTurnAngle(-90));
    		//addSequential(new xxxx());
    	}
    }
}
