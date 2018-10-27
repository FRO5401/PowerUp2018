package org.usfirst.frc.team5401.robot.autonomous;
import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

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
    		addSequential(new AutoDrive(21, .5));
    		addSequential(new AutoTurnAngle(-37));
    		addSequential(new AutoDrive(90, .5)); //78
    		addSequential(new AutoTurnAngle(37));
    		addSequential(new AutoDrive(6, .5));
    		addSequential(new InfeedUpDownForAuto(31));
    		addSequential(new WaitCommand(1));
    		addSequential(new InfeedInOutForAuto(-1));
    		//addSequential(new AutoPIDDrive(-15));
    		//addSequential(new AutoPIDTurnAngle(90));
    	}
    	else if(gameData.charAt(0) == 'R'){    	
    	//Start at Auto Position #3. 
    	//This will be putting block the right side (Closer to right)
    		addSequential(new AutoDrive(21, .5));
    		addSequential(new AutoTurnAngle(37));
    		addSequential(new AutoDrive(72, .5)); //90
    		addSequential(new AutoTurnAngle(-37));
    		addSequential(new AutoDrive(20, .5)); //7
    		addSequential(new InfeedUpDownForAuto(31));
    		addSequential(new WaitCommand(1));
    		addSequential(new InfeedInOutForAuto(-1));
    		//addSequential(new AutoPIDDrive(-15));
    		//addSequential(new AutoPIDTurnAngle(-90));
    	}
    	else if(gameData.charAt(0) == 'X'){
    		//XXX Fix
    		addSequential(new AutoDrive(50, .5));
    		addSequential(new AutoTurnAngle(90));
    		addSequential(new AutoTurnAngle(-90));
    	}
    }
}
