package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSwitch extends CommandGroup {

    public AutoLeftSwitch() {
    	//The following is a ternary operator, which is similar to a condensed if else statement
    	String gameData = (DriverStation.getInstance().getGameSpecificMessage() == null) ? "X" : DriverStation.getInstance().getGameSpecificMessage();
    	if(gameData.charAt(0) == 'L')
    	{
    	//Start at Auto Position #2. Drive Forward 140 inches, place block on switch
    		addSequential(new AutoDrive(140.595, 1));
    	  //addSequential(new xxxx());
    
    	}
    	else 
    	{    	
    	//Start at Auto Position #2. Drive Forward 55 inches, turn right 
    	//(Figure out specifics later)
    		addSequential(new AutoDrive(55, 1));
    		addSequential(new AutoTurnAngle(90));
    		//addSequential(new xxxx());
    	}
    }
}
