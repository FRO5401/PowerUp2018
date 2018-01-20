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
	
	private boolean stabilizerEnabled;
	
	
	public Climber(){
		rollerStabilizer    = new Solenoid(RobotMap.STABILIZER_OUT);
		climberExtender     = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.CLIMBER_EXTENDER_IN, RobotMap.CLIMBER_EXTENDER_OUT);
		climberSwitchTop    = new DigitalInput (RobotMap.CLIMBER_SWITCH_TOP);
		climberSwitchBottom = new DigitalInput (RobotMap.CLIMBER_SWITCH_BOTTOM);
		climberMotor        = new TalonSRX (RobotMap.CLIMBER_MOTOR);
		
		stabilizerEnabled = false;
	}
		
	public void controlStabilizer(boolean enabled){
		if(enabled){
			rollerStabilizer.set(true);
		}
		else if(!enabled){
			rollerStabilizer.set(false);
		}

	}
	  
	
	public void climbExtenderUpDown(int climbExtenderDirection){
		if (climbExtenderDirection == 1){
			climberExtender.set(DoubleSolenoid.Value.kForward);
			SmartDashboard.putNumber("Climber is Extended", 1);
		}
		else if (climbExtenderDirection == -1){
			climberExtender.set(DoubleSolenoid.Value.kReverse);
			SmartDashboard.putNumber("Climber is Extended", -1);
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

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

