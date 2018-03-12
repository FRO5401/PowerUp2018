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
    	if(gameData.charAt(0) == 'L')
    	{
    	//Start at Auto Position #1.
    	//This will be putting block on left side 
    		addSequential(new AutoPIDDrive(100));
    		/*addSequential(new AutoTurnAngle(-37));
    		addSequential(new AutoPIDDrive(85));
    		addSequential(new AutoTurnAngle(37));
    		addSequential(new AutoPIDDrive(20));
    		addSequential(new xxxxARM());*/
   
    	}
    	else if(gameData.charAt(0) == 'R')
    	{    	
    	//Start at Auto Position #1. 
    	//This will be putting block the right side
    		addSequential(new AutoPIDDrive(85));
    		/*addSequential(new AutoTurnAngle(37));
    		addSequential(new AutoPIDDrive(85));
    		addSequential(new AutoTurnAngle(-37));
    		addSequential(new AutoPIDDrive(24));
    		addSequential(new xxxxARM());*/
    		
    	}
    	else if(gameData.charAt(0) == 'X')
    	{
    		addSequential(new AutoPIDDrive(85));
    		//addSequential(new xxxxARM());
    	}
    }
  }