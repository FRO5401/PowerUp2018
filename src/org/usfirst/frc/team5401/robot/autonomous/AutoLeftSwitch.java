package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;


public class AutoLeftSwitch extends CommandGroup {

    public AutoLeftSwitch() {
    	//The following is a ternary operator, which is similar to a condensed if else statement
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	if(gameData.isEmpty()){
    		gameData = "XXX";
    	}
    		if(gameData.charAt(0) == 'L'){
    	//Start at Auto Position #2. Drive to front of switch, place block on switch
    		addSequential(new AutoPIDDrive(21));
    		addSequential(new AutoPIDTurnAngle(7));
    		addSequential(new AutoPIDDrive(40));
    		addSequential(new AutoPIDTurnAngle(-6));
    	    addSequential(new AutoPIDDrive(37));
    	    //addSequential(new xxxxARM());
    	    addSequential(new AutoPIDDrive(-15));
    		addSequential(new AuoPIDTurnAngle(90));
    		//addSequential(new xxxxARM());
    	}
    	else if(gameData.charAt(0) == 'R')
    	{    	
    	//Start at Auto Position #2. Drive Forward 85 inches to base line 
    	//(Figure out specifics later)
    		addSequential(new AutoPIDDrive(85));
    		//addSequential(new xxxxARM());
    		addSequential(new AutoPIDDrive(-15));
    		addSequential(new AuoPIDTurnAngle(-90));
 
    	}else if(gameData.charAt(0) == 'X')
    	{
    		addSequential(new AutoPIDDrive(85));
    		addSequential(new AutoPIDTurnAngle(90));
    		//addSequential(new xxxxARM());
    	}
    }
  }