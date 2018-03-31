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
    		addSequential(new AutoPIDDrive(21));
    		addSequential(new AutoPIDTurnAngle(-37));
    		addSequential(new AutoPIDDrive(78));
    		addSequential(new AutoPIDTurnAngle(37));
    		addSequential(new AutoPIDDrive(7));
    		addSequential(new InfeedUpDownForAuto(-1));
    		addSequential(new WaitCommand(1));
    		addSequential(new InfeedInOutForAuto(-1));
    		//addSequential(new AutoPIDDrive(-15));
    		//addSequential(new AutoPIDTurnAngle(90));
    	}else if(gameData.charAt(0) == 'R')
    	{    	
    	//Start at Auto Position #3. 
    	//This will be putting block the right side (Closer to right)
    		addSequential(new AutoPIDDrive(21));
    		addSequential(new AutoPIDTurnAngle(37));
    		addSequential(new AutoPIDDrive(90));
    		addSequential(new AutoPIDTurnAngle(-37));
    		addSequential(new AutoPIDDrive(7));
    		addSequential(new InfeedUpDownForAuto(-1));
    		addSequential(new WaitCommand(1));
    		addSequential(new InfeedInOutForAuto(-1));
    		//addSequential(new AutoPIDDrive(-15));
    		//addSequential(new AutoPIDTurnAngle(-90));
    	}else if(gameData.charAt(0) == 'X')
    	{
    		//XXX Fix
    		addSequential(new AutoPIDDrive(50));
    		addSequential(new AutoPIDTurnAngle(90));
    		addSequential(new AutoPIDTurnAngle(-90));
    	}
    }
}
