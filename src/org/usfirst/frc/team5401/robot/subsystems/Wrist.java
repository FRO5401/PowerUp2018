package org.usfirst.frc.team5401.robot.subsystems;
import org.usfirst.frc.team5401.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 */
public class Wrist extends Subsystem {

	private DoubleSolenoid wristMoveLeft1;
	private DoubleSolenoid wristMoveRight1;
	private DoubleSolenoid wristMoveLeft2;
	private DoubleSolenoid wristMoveRight2;

	public Wrist(){
		wristMoveLeft1 = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.WRIST_MOVE_LEFT_FORWARD_1, RobotMap.WRIST_MOVE_LEFT_BACKWARD_1);
		wristMoveRight1 = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.WRIST_MOVE_RIGHT_FORWARD_1, RobotMap.WRIST_MOVE_RIGHT_BACKWARD_1);
		wristMoveLeft2 = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.WRIST_MOVE_LEFT_FOWARD_2, RobotMap.WRIST_MOVE_LEFT_BACKWARD_2);
		wristMoveRight2 = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.WRIST_MOVE_RIGHT_FORWARD_2, RobotMap.WRIST_MOVE_RIGHT_BACKWARD_2);

	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	// Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    	//Controlled by a button
    public void wristDownCenter(int downCenterdirection){
    	if(downCenterdirection == 1) {
    		wristMoveLeft1.set(DoubleSolenoid.Value.kForward);
    		wristMoveRight1.set(DoubleSolenoid.Value.kForward);
    		//Wrist will be extending outwards
    	} else if(downCenterdirection == -1) {
    		wristMoveLeft1.set(DoubleSolenoid.Value.kReverse);
    		wristMoveRight1.set(DoubleSolenoid.Value.kReverse);
    		//Wrist will be coming back inward
    	}
    }
    
    public void wristCenterUp(int centerUpDirection){
    	if(centerUpDirection == 1 /*&& Encoder value > x(Degree) */){
    		wristMoveLeft2.set(DoubleSolenoid.Value.kForward);
    		wristMoveRight2.set(DoubleSolenoid.Value.kForward);
    	}
    	else if(centerUpDirection == -1 /* && Encoder Value < x(Dxegree) */){
    		wristMoveLeft2.set(DoubleSolenoid.Value.kReverse);
    		wristMoveRight2.set(DoubleSolenoid.Value.kReverse);
    	}
    }
}

