package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class Climber extends Subsystem {
	
	private Solenoid rollerStablizer;
	private DigitalInput climberSwitchTop;
	private DigitalInput climberSwitchBottom;
	private TalonSRX climberMotor;
	private DoubleSolenoid climbPlatforms;
	
	private boolean stablizerEnabled;
	private boolean platformEnabled;
	
	
	public Climber(){
		rollerStablizer    = new Solenoid(RobotMap.CLIMBER_STABILIZER);
		climbPlatforms      = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.CLIMBER_PLATFORM_IN, RobotMap.CLIMBER_PLATFORM_OUT);
		climberSwitchTop    = new DigitalInput (RobotMap.CLIMBER_SWITCH_TOP);
		climberSwitchBottom = new DigitalInput (RobotMap.CLIMBER_SWITCH_BOTTOM);
		climberMotor        = new TalonSRX (RobotMap.CLIMBER_MOTOR);
		
		stablizerEnabled = false;
		platformEnabled = false;
		SmartDashboard.putBoolean("Stablizer State", stabilizerEnabled);
		SmartDashboard.putBoolean("Climb Platforms State", platformEnabled);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	//Stabilizer
	public void changeStablizer(boolean stablizerState){
		rollerStablizer.set(stablizerState);
		stablizerEnabled = stablizerState;
	}
	
	public void changeClimbPlatforms(boolean platformState){
		if(platformState == true)
		{
			climbPlatform.set(DoubleSolenoid.kForward);
		}
		else
		{
			climbPlatform.set(DoubleSolenoid.kBackward);
		}
		
	}
	
	//XXX Switches are all reversed because they default to true and go false when tripped
	public boolean reportTopClimbSwitch(){
		SmartDashboard.putBoolean("Climber Top Switch", !(climberSwitchTop.get()));
		return  !climberSwitchTop.get();
		
	}
	
	public boolean reportBottomClimbSwitch(){
		SmartDashboard.putBoolean("Climber Bottom Switch", !(climberSwitchBottom.get()));
		return  !climberSwitchBottom.get();
		
	}
	
}

