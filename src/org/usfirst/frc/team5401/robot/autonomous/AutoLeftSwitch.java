package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSwitch extends CommandGroup {

    public AutoLeftSwitch() {
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	if(gameData.charAt(0) == 'L')
    	{
    	//Start at Auto Position #2. Drive Forward 140 inches, place block on switch
    
    	}
    	else 
    	{    	
    	//Start at Auto Position #2. Drive Forward 55 inches, turn right 
    	//(Figure out specifics later)
    	}
    }
}
