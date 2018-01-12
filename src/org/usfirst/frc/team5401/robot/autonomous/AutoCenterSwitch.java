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
    	//Start at Auto Position #3. Drive Forward 55 inches
    	//This will be putting block on left side (Closer to Left)
    		addSequential(new AutoDrive(70, 1));
    		addSequential(new AutoTurnAngle(-90, true, false));
    		addSequential(new AutoDrive(44, 1));
    		addSequential(new AutoTurnAngle(90, true, false));
    		addSequential(new AutoDrive(70, 1));
    		//addSequential(new xxxx());
    	}
    	else 
    	{    	
    	//Start at Auto Position #3. Drive Forward 55 inches
    	//This will be putting block the right side
    		addSequential(new AutoDrive(70, 1));
    		addSequential(new AutoTurnAngle(90, true, false));
    		addSequential(new AutoDrive(56, 1));
    		addSequential(new AutoTurnAngle(-90, true, false));
    		addSequential(new AutoDrive(70, 1));
    		//addSequential(new xxxx());
    	}
    }
}
