package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.VictorSP;
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
	TalonSRX armTalon = new TalonSRX(RobotMap.ARM_TALON_CHANNEL);
	
	public void moveArm(double direction){
		if(direction > RobotMap.AXIS_THRESHOLD || direction < (-1 * RobotMap.AXIS_THRESHOLD)){
			setArmBrake(false);
			armTalon.set(ControlMode.PercentOutput, direction);
			getArmAngle();
		}
		else if(direction == 0){
			armTalon.set(ControlMode.PercentOutput, 0);
			setArmBrake(true);
			getTalonTemp();
		}		
	}
	
	public void setArmBrake(boolean armBrake){
		brake.set(armBrake);
	}
	
	public void feedInOut(){
		//TODO: Add Feeder Rollers Method(s)
	}
	
	public void manualOverride(){
		//TODO: Add Override Method
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