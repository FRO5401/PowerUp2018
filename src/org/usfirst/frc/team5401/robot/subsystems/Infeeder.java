package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
/**
 *
 */
public class Infeeder extends Subsystem {
	
	private VictorSP leftInfeed;
	private VictorSP rightInfeed;
	
	private Solenoid infeederMoverLeft;
	private Solenoid infeederMoverRight;
	
	private DoubleSolenoid infeederInOut1;
	private DoubleSolenoid infeederInOut2;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void infeederUp(){
		infeederInOut1.set(DoubleSolenoid.Value.kReverse);
		infeederInOut2.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void infeederDown(){
		infeederInOut1.set(DoubleSolenoid.Value.kForward);
		infeederInOut2.set(DoubleSolenoid.Value.kForward);
	}
	
	public void infeederOpen(){
		infeederMoverRight.set(false);
		infeederMoverLeft.set(false);
	}
	
	public void infeederPressure(){
		infeederMoverRight.set(true);
		infeederMoverLeft.set(true);
	}
	
	public void infeederCubeIn(){
		leftInfeed.set(-1);
		rightInfeed.set(-1);
	}
	
	public void infeederCubeOut(){
		leftInfeed.set(1);
		rightInfeed.set(1);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

