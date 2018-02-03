package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenterSwitch extends CommandGroup {

    public AutoCenterSwitch() {
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	if(gameData.charAt(0) == 'L')
    	{
    	//Start at Auto Position #3. 
    	//This will be putting block on left side (Closer to Left)
    		addSequential(new AutoDrive(55, 1));
    		addSequential(new AutoTurnAngle(-90));
    		addSequential(new AutoDrive(30, 1));
    		addSequential(new AutoTurnAngle(90));
    		addSequential(new AutoDrive(45, 1));
    		//addSequential(new xxxx());
    	}
    	else 
    	{    	
    	//Start at Auto Position #3. 
    	/*
    		//This will be putting block the right side
    		addSequential(new AutoDrive(55, 1));
    		addSequential(new AutoTurnAngle(90));
    		addSequential(new AutoDrive(45, 1));
    		addSequential(new AutoTurnAngle(-90));
    		addSequential(new AutoDrive(45, 1));
    		 * 
    		 */
    	}
    	
    }
}
