package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenterSwitch extends CommandGroup {

    public AutoCenterSwitch() {
    	//The following is a ternary operator, which is similar to a condensed if else statement
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	if(gameData.isEmpty()){
    		gameData = "XXX";
    	}
    	
    	if(gameData.charAt(0) == 'L')
    	{
    	//Start at Auto Position #3.
    	//This will be putting block on left side (Closer to Left)
    		addSequential(new AutoPIDDrive(21));
    		addSequential(new AutoPIDTurnAngle(-37));
    		addSequential(new AutoPIDDrive(85));
    		addSequential(new AutoPIDTurnAngle(37));
    		addSequential(new AutoPIDDrive(3));
    		//addSequential(new xxxxARM());
    		//addSequential(new AutoPIDDrive(-15));
    		//addSequential(new AutoPIDTurnAngle(90));
    		//addSequential(new xxxxARM()); 
    	}
    	else if(gameData.charAt(0) == 'R')
    	{    	
    	//Start at Auto Position #3. 
    	//This will be putting block the right side (Closer to right)
    		addSequential(new AutoPIDDrive(21));
    		addSequential(new AutoPIDTurnAngle(37));
    		addSequential(new AutoPIDDrive(85));
    		addSequential(new AutoPIDTurnAngle(-37));
    		addSequential(new AutoPIDDrive(4));
    		//addSequential(new xxxxARM());
    		//addSequential(new AutoPIDDrive(-15));
    		//addSequential(new AutoPIDTurnAngle(-90));
    		//addSequential(new xxxxARM());
    	}else if(gameData.charAt(0) == 'X')
    	{
    		addSequential(new AutoPIDDrive(50));
    		addSequential(new AutoPIDTurnAngle(90));
    		addSequential(new AutoPIDTurnAngle(-90));
    	}
    }
}
