package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoScaleLeft extends CommandGroup {

    public AutoScaleLeft() {
    	//Start at Auto Position #1
    	//The following is a ternary operator, which is similar to a condensed if else statement
String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	if(gameData.isEmpty()){
    		gameData = "XXX";
    	}
    		if(gameData.charAt(0) == 'R'){
    	//Start at Auto Position #2. Drive to front of switch, place block on switch
    		addSequential(new AutoPIDDrive(21));
    		addSequential(new AutoTurnAngle(7));
    		addSequential(new AutoPIDDrive(40));
    		addSequential(new AutoTurnAngle(-6));
    	    addSequential(new AutoPIDDrive(37));
    	}
    	else if(gameData.charAt(0) == 'R')
    	{    	
    	//Start at Auto Position #2. Drive Forward 85 inches to base line 
    	//(Figure out specifics later)
    		addSequential(new AutoPIDDrive(85));
 
    	}else if(gameData.charAt(0) == 'X')
    	{
    		addSequential(new AutoPIDDrive(10));
    	}
    }
  }