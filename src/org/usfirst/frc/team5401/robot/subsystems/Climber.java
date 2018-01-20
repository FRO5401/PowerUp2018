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
	
	private Solenoid rollerStabilizer;
	private DoubleSolenoid climberExtender;
	private DigitalInput climberSwitchTop;
	private DigitalInput climberSwitchBottom;
	private TalonSRX climberMotor;
	private DoubleSolenoid climbPlatforms;
	
	private boolean stabilizerEnabled;
	private boolean extenderEnabled;
	private boolean platformEnabled;
	
	
	public Climber(){
		rollerStabilizer    = new Solenoid(RobotMap.STABILIZER_OUT);
		climberExtender     = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.CLIMBER_EXTENDER_IN, RobotMap.CLIMBER_EXTENDER_OUT);
		climbPlatforms      = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.CLIMBER_PLATFORM_UP, RobotMap.CLIMBER_PLATFORM_DOWN);
		climberSwitchTop    = new DigitalInput (RobotMap.CLIMBER_SWITCH_TOP);
		climberSwitchBottom = new DigitalInput (RobotMap.CLIMBER_SWITCH_BOTTOM);
		climberMotor        = new TalonSRX (RobotMap.CLIMBER_MOTOR);
		
		stabilizerEnabled = false;
		extenderEnabled = false;
		platformEnabled = false;
		SmartDashboard.putBoolean("Stabilizer State", stabilizerEnabled);
		SmartDashboard.putBoolean("Climb Extender State", extenderEnabled);
		SmartDashboard.putBoolean("Climb Platforms State", platformEnabled);
	}
	
	//Stabilizer
	public void deployStabilizer(){
		rollerStabilizer.set(true);
		stabilizerEnabled = true;
	}
	
	public void retractStabilizer(){
		rollerStabilizer.set(false);
		stabilizerEnabled = false;
	}
	
	public void switchStabilizerState(){
		if (stabilizerEnabled){
			deployStabilizer();
		}
		else {
			retractStabilizer();
		}
	}
	
	//Climb Extender
	public void climbExtenderUp(){
		climberExtender.set(DoubleSolenoid.Value.kForward);
		extenderEnabled = true;
	}
	
	public void climbExtenderDown(){
		climberExtender.set(DoubleSolenoid.Value.kReverse);
		extenderEnabled = false;
	}
	
	public void switchExtenderState(){
		if (extenderEnabled){
			climbExtenderUp();
		}
		else {
			climbExtenderDown();
		}
	}
	
	//Climb Platforms
	public void platformUp(){
		climbPlatforms.set(DoubleSolenoid.Value.kForward);
		platformEnabled = true;
	}
	
	public void platformDown(){
		climbPlatforms.set(DoubleSolenoid.Value.kReverse);
		platformEnabled = false;		
	}
	
	public void switchPlatformState(){
		if (platformEnabled){
			platformUp();
		}
		else{
			platformDown();
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
	//Climb Motors
	public void climberStartMotors(double input){
		if(reportTopClimbSwitch() && input > 0){
			input = 0;
		}
		if(reportBottomClimbSwitch() && input < 0){
			input = 0;
		}
				
		climberMotor.set(ControlMode.PercentOutput, (input * RobotMap.CLIMB_PRECISION));
	}
	public void stopClimber(){
		climberMotor.set(ControlMode.PercentOutput, 0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

