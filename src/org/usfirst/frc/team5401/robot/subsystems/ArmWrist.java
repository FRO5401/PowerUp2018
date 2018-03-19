//TalonSRX integrated encoder does not have DPP
//Don't know the why loopIndex is used vs slotIndex
package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/*
 * TRUE FOR BRAKE MEANS BRAKE IS released
 */

public class ArmWrist extends Subsystem {

	private DoubleSolenoid wristMoveLong;
	private Solenoid brake;
	//Encoder not needed for TalonSRX due to Versa Planetary Encoders
	private TalonSRX armTalon;

	private boolean armPidEnabled;
	
	private double armAngle;
	private int loopIndex;
	private int slotIndex;
	
	public ArmWrist(){
		armPidEnabled = false;
		
		loopIndex = 0;
		slotIndex = 0;
		//This is for the ConfigSelectedFeedbackSensor whose second parameter is PID index, this loop index is the ACTUAL parameter of PID index and is zero for a primary closed loop, or one per cascade coasting. 
		//Zero is used as it is used within the example code.

		//Object instantiation
		armTalon = new TalonSRX(RobotMap.ARM_TALON_CHANNEL);//TODO need to check on RoboRIO  //TODO Make this a constant ref to robot map, move other todo to robotmap
		//armPot = new AnalogPotentiometer(RobotMap.ARM_POT_CHANNEL, RobotMap.ARM_RANGE, RobotMap.ARM_OFFSET);
		
		brake = new Solenoid(RobotMap.PCM_ID, RobotMap.ARM_BRAKE);
		
		wristMoveLong = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.WRIST_MOVE_LONG_FORWARD, RobotMap.WRIST_MOVE_LONG_BACKWARD);

		/******REPEAT THE FOLLOWING LINE TO MAKE SET POINTS*********/

		//0 is the output value, possibly position wanted? in encoder tick or analog value, not sure native value

		//Sets up the Control mode to PID position
		//armTalon.set(ControlMode.Position, 0);	this runs in the constructor which means on start-up the robot will start moving (BAD)
		
		//Sets up feedback device for PID
		//May have to change the QuadEncoder to something else.
		armTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, loopIndex, RobotMap.TIMEOUT_LIMIT_IN_Ms);//10 is a timeout that waits for successful conection to sensor
		armTalon.setSensorPhase(false);//true if sensor value is positive if the motor controller output is negative. False if both are positive or negative
		
		//Sets allowable error, which is how far the actual value is off from intended value
		//0 is slot index, which is parameter slot for the constant. Not sure what this actually does
		//This was in the example not sure why it was zero
		armTalon.configAllowableClosedloopError(slotIndex, (int)(RobotMap.ARM_THRESHOLD_FOR_PID_IN_DEGREES / RobotMap.ANGLE_PER_PULSE), RobotMap.TIMEOUT_LIMIT_IN_Ms);
		
		//Sets max and min reverse and forward. First number is max/min output in percent 1 = 100%
        armTalon.configNominalOutputForward(RobotMap.ARM_NOM_OUTPUT, 	RobotMap.TIMEOUT_LIMIT_IN_Ms);
        armTalon.configNominalOutputReverse(RobotMap.ARM_NOM_OUTPUT, 	RobotMap.TIMEOUT_LIMIT_IN_Ms);
        armTalon.configPeakOutputForward(RobotMap.ARM_PEAK_OUTPUT, 	RobotMap.TIMEOUT_LIMIT_IN_Ms);
        armTalon.configPeakOutputReverse(-1* RobotMap.ARM_PEAK_OUTPUT, 	RobotMap.TIMEOUT_LIMIT_IN_Ms);
       
        //Sets F, P, I, and D
        //Middle parameter is the corresponding value for F, P, I, or D
        //In the example the loopIndex variable is in the slotIndex parameter(not quite sure why, perhaps loopIndex is slotIndex) -Jason L.
        armTalon.config_kF(slotIndex, RobotMap.ARM_kF, RobotMap.TIMEOUT_LIMIT_IN_Ms);
        armTalon.config_kP(slotIndex, RobotMap.ARM_kP, RobotMap.TIMEOUT_LIMIT_IN_Ms);
        armTalon.config_kI(slotIndex, RobotMap.ARM_kI, RobotMap.TIMEOUT_LIMIT_IN_Ms);
        armTalon.config_kD(slotIndex, RobotMap.ARM_kD, RobotMap.TIMEOUT_LIMIT_IN_Ms);        
	}
	
	public void initDefaultCommand() {
        //Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void longWristUpDown(int longWristDirection){
    	if(longWristDirection > 0) {
    		wristMoveLong.set(DoubleSolenoid.Value.kForward);
    		//Long wrist will be going back in
    	} 
    	else if(longWristDirection < 0) {
    		wristMoveLong.set(DoubleSolenoid.Value.kReverse);
    		//Wrist will be extending forward
    	}
    }

	public void setBrake(boolean brakeSet){
		//Controlled by either override or reaching end of PID setpoint
		//Disabled once override is engaged
		brake.set(brakeSet);
	}

	

	public void setPoint(double setPointIndexInDegrees){
		double setPointNativeUnits = setPointIndexInDegrees / RobotMap.ANGLE_PER_PULSE;

		//System.out.println(setPointNativeUnits);
		armTalon.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
		armTalon.set(ControlMode.Position, setPointNativeUnits);
		brake.set(true);  //TODO brake is reversed, we should refactor this to only reverse it once
		armPidEnabled = true;
		//Finds set point
		//Calls to command for which set point

	}

	public void pidStop(){
		brake.set(false);
		armPidEnabled = false;
		armTalon.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
		armTalon.set(ControlMode.PercentOutput, 0);
		
	}

	public void overrideMove(double operatorJoystick){
		armTalon.set(ControlMode.PercentOutput, operatorJoystick);
		//System.out.println("overrideMove");
		//Like DriveBase, sends out a direction to move to speed controller
	}
	
	public boolean onTarget(double setPointDegrees){
		SmartDashboard.putNumber("Set point", setPointDegrees);
		double armNativeUnitError = (armTalon.getSensorCollection().getQuadraturePosition()) - (setPointDegrees / RobotMap.ANGLE_PER_PULSE);
		SmartDashboard.putNumber("Arm Native Unit Error", armNativeUnitError);
		//System.out.println("\nArm Native Unit Error: " + armNativeUnitError);
		
		double armAngleError = (armTalon.getSensorCollection().getQuadraturePosition() * RobotMap.ANGLE_PER_PULSE) - (setPointDegrees);
		SmartDashboard.putNumber("Arm Angle Error", armAngleError);
		
		boolean onTarget = Math.abs(armNativeUnitError) < (RobotMap.ARM_THRESHOLD_FOR_PID_IN_DEGREES / RobotMap.ANGLE_PER_PULSE);//TODO may want to make this bombproof because i think right now negative angles will confuse it
		return onTarget;
		//getClosedLoopT gets the SetPoint already set (or moving to)
	}
	
	public void overrideStopped(){
		
		armTalon.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
		armTalon.set(ControlMode.PercentOutput, 0);
		brake.set(false);
		armPidEnabled = false;
	}
	
	public void armInterrupted(){
		armPidEnabled = false;
		brake.set(false);
		armTalon.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
		armTalon.set(ControlMode.PercentOutput, 0);
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
	
	public void adjustWristToAngle(){
		//Forward (in code) is Currently (physically) In and Reverse is Out (Don't know why)
		if(getArmAngle() <= 34)/*Ground and or Reset*/{
			wristMoveLong.set(DoubleSolenoid.Value.kForward);
		} else if(getArmAngle() >= 34  && getArmAngle() <= 60)/*Portal/Switch*/{
			wristMoveLong.set(DoubleSolenoid.Value.kForward);
		}else if(getArmAngle() >= 60 && getArmAngle() <= 105)/*ScaleMidAndLow*/{
			wristMoveLong.set(DoubleSolenoid.Value.kForward);	
		}else if(getArmAngle() >= 105 && getArmAngle() <= 122)/*ScaleHigh*/{
			wristMoveLong.set(DoubleSolenoid.Value.kReverse);
		}else{
			wristMoveLong.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
	//1 for mode is coast. 2 for mode is brake
	public void setTalonSRXNeutralMode(int mode)
	{
		if(mode == 1)
		{
			armTalon.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
		}
		if(mode == 2)
		{
			armTalon.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
		}
	}
}

