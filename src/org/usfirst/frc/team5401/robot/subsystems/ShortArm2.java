package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Solenoid;
import org.usfirst.frc.team5401.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 *
 */
public class ShortArm2 extends Subsystem {
	
	Solenoid brake    = new Solenoid(RobotMap,); 
	VictorSP gearTurn = new VictorSP(RobotMap,);
	
	public void turnGear(int ge){
		if(ge == 1 || ge == -1){
			setArmBrake(false);
			gearTurn.set(ge * RobotMap.ARM_SPEED);
			setArmBrake(true);
		} else if (ge == 0) {
			setArmBrake(true);
			gearTurn.set(0);
			setArmBrake(false);
		}
		
	}
	
	public void setArmBrake(boolean armBrake){
		brake.set(armBrake);
		
	}
	
	public void allowMove( int ge) {
		double angle = getArmAngle();
		if (angle > 70) {
			gearTurn.set(0);
			setArmBrake(true);
		} else if (angle > 0 && angle < 70) {
			setArmBrake(false);
			gearTurn.set(ge * RobotMap.ARM_SPEED);
			setArmBrake(true);
		}
	}
	
	public void overrideMove(double operatorJoystick){
		armTalon.set(ControlMode.PercentOutput, operatorJoystick);
		//System.out.println("overrideMove");
		//Like DriveBase, sends out a direction to move to speed controller
	}
	
	public double getArmAngle(){
		//Shows degrees. Converts native units to degrees
		double armAngle = (armTalon.getSensorCollection().getQuadraturePosition() * RobotMap.ANGLE_PER_PULSE);
		SmartDashboard.putNumber("Arm Angle", armAngle);
		SmartDashboard.putNumber("Native Units for Arm", armTalon.getSensorCollection().getQuadraturePosition());
		//System.out.println("Native Units Position" + armTalon.getSensorCollection().getQuadraturePosition());
		//System.out.println("Arm Motor Speed " + armTalon.getSensorCollection().getQuadratureVelocity());
		return armAngle;	
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

