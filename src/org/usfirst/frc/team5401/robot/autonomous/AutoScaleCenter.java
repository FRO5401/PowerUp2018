package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoScaleCenter extends CommandGroup {

    public AutoScaleCenter() {
    	//Start at Auto Position #3
    	//The following is a ternary operator, which is similar to a condensed if else statement
String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	if(gameData.isEmpty()){
    		gameData = "XXX";
    	}
    }
}
