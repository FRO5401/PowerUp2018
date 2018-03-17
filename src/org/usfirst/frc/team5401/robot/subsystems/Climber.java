package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;


import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class Climber extends Subsystem {
	
//	private Solenoid climberStablizer;
	private DigitalInput climberSwitchTop;
	private DigitalInput climberSwitchBottom;
	private VictorSP climberMotorTop;
	private VictorSP climberMotorBottom;
//	private DoubleSolenoid climberPlatforms;
	
	private boolean stablizerEnabled;
	private boolean platformEnabled;
	
	
	public Climber(){
//		climberStablizer    = new Solenoid(RobotMap.CLIMBER_STABLIZER);
//		climberPlatforms      = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.CLIMBER_PLATFORM_IN, RobotMap.CLIMBER_PLATFORM_OUT);
//		climberSwitchTop    = new DigitalInput (RobotMap.CLIMBER_SWITCH_TOP);
//		climberSwitchBottom = new DigitalInput (RobotMap.CLIMBER_SWITCH_BOTTOM);
		climberMotorTop = new VictorSP(RobotMap.CLIMBER_MOTOR_TOP);
		climberMotorBottom = new VictorSP(RobotMap.CLIMBER_MOTOR_BOTTOM);
		
		stablizerEnabled = false;
		platformEnabled = false;
		SmartDashboard.putBoolean("Stablizer State", stablizerEnabled);
		SmartDashboard.putBoolean("Climb Platforms State", platformEnabled);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	//Stablizer
	public void changeStablizer(boolean stablizerState){
//		climberStablizer.set(stablizerState);
		stablizerEnabled = stablizerState;
	}
	
	//1 is out, everything else is in
	public void changeClimberPlatforms(int platformState){
		if(platformState == 1)
		{
//			climberPlatforms.set(DoubleSolenoid.Value.kForward);
		}
		else
		{
//			climberPlatforms.set(DoubleSolenoid.Value.kReverse);
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
	
	public void climberStartMotors(double input){
		if (!(reportTopClimbSwitch() && input > 0 || reportBottomClimbSwitch() && input < 0)){
			input = 0;
		}
		climberMotorTop.set(RobotMap.CLIMB_PRECISION);		
		climberMotorBottom.set(RobotMap.CLIMB_PRECISION);		
	}
	
	public void stopClimber(){
//		climberMotor.set(ControlMode.PercentOutput, 0);
	}
}

