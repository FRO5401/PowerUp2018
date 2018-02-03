package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenterSwitch extends CommandGroup {

    public AutoCenterSwitch() {
    	//The following is a ternary operator, which is similar to a condensed if else statement
    	String gameData = (DriverStation.getInstance().getGameSpecificMessage() == null) ? "XXX" : DriverStation.getInstance().getGameSpecificMessage();
    	
    	
    	if(gameData.charAt(0) == 'L')
    	{
    	//Start at Auto Position #3. Drive Forward 55 inches
    	//This will be putting block on left side (Closer to Left)
    		addSequential(new AutoPIDDrive(50));
    		addSequential(new AutoTurnAngle(-90));
    		addSequential(new AutoPIDDrive(40));
    		addSequential(new AutoTurnAngle(90));
    		addSequential(new AutoPIDDrive(45));
    		//addSequential(new xxxx());
    	}
    	else if(gameData.charAt(0) == 'R')
    	{    	
    	//Start at Auto Position #3. Drive Forward 55 inches
    	//This will be putting block the right side
    		addSequential(new AutoPIDDrive(70));
    		addSequential(new AutoTurnAngle(90));
    		addSequential(new AutoPIDDrive(56));
    		addSequential(new AutoTurnAngle(-90));
    		addSequential(new AutoPIDDrive(70));
    		//addSequential(new xxxx());
    	}
    	else if(gameData.charAt(0) == 'X')
    	{
    		
    	}
    }
}
