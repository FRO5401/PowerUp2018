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
    	//Start at Auto Position #2 
    	//Drive to left side, and place block on switch
    		addSequential(new AutoDrive(140.595, 1));
    	  //addSequential(new xxxx());
    
    	}
    	else 
    	{    	
    	//Start at Auto Position #2
    	//Drive forward 55 inches, and turn right. Do not want to interfere
    		addSequential(new AutoDrive(55, 1));
    		addSequential(new AutoTurnAngle(90));
    		//addSequential(new xxxx());
    	}
    }
}
