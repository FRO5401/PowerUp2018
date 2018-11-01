package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.InfeedControl;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 *
 */
public class ShortArm extends Subsystem {

	Solenoid brake    = new Solenoid(RobotMap.ARM_BRAKE); 
	DoubleSolenoid rollerClaw = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.INFEED_OPEN, RobotMap.INFEED_CLOSE);
	
	TalonSRX armTalon = new TalonSRX(RobotMap.ARM_TALON_CHANNEL);
	VictorSP leftRoller  = new VictorSP(RobotMap.INFEED_ROLLER_LEFT);
	VictorSP rightRoller = new VictorSP(RobotMap.INFEED_ROLLER_RIGHT);
	
	public void moveArm(double armDirection){
		if(armDirection > RobotMap.AXIS_THRESHOLD || armDirection < (-1 * RobotMap.AXIS_THRESHOLD)){
			setArmBrake(false);
			armTalon.set(ControlMode.PercentOutput, armDirection);
			
		}
		else if(armDirection == 0){
			armTalon.set(ControlMode.PercentOutput, 0);
			setArmBrake(true);
			
		}		
	}
	
	public void setArmBrake(boolean armBrake){
		brake.set(armBrake);
	}
	
	public void feedOut(double rollerDirection){
		//TODO: Add Feeder Rollers Method(s)
		System.out.println("Roller Direction" + rollerDirection);
		leftRoller.set((-1) * rollerDirection);
		rightRoller.set(rollerDirection);
	}
	public void feedIn(double rollerDirection){
		leftRoller.set((.75) * rollerDirection);
		rightRoller.set((-.75) * rollerDirection);
	}
	
	public void feedStop(){
		leftRoller.set(0);
		rightRoller.set(0);
	}
	
	public void openClaw(){
		rollerClaw.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void closeClaw(){
		rollerClaw.set(DoubleSolenoid.Value.kForward);
	}
	
	public void manualOverride(double armDirection){
		//TODO: Add Override Method\
		armTalon.set(ControlMode.PercentOutput, armDirection);
		getArmAngle();
	}
	
	public void setTalonSRXNeutralMode(int mode){
		if(mode == 1){
			armTalon.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
		}
		else if(mode == 2){
			armTalon.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
		}
	}
	
	public double getArmAngle(){
		//Shows degrees. Converts native units to degrees
		double armAngle  = (armTalon.getSensorCollection().getQuadraturePosition() * RobotMap.ANGLE_PER_PULSE);
		SmartDashboard.putNumber("Arm Angle", armAngle);
		SmartDashboard.putNumber("Native Units for Arm", armTalon.getSensorCollection().getQuadraturePosition());
		return armAngle;	
	}
	public void getTalonTemp(){
		double talonTemp = armTalon.getTemperature();
		SmartDashboard.putNumber("Talon Temperature", talonTemp);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new InfeedControl());
    }
}