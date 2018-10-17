package org.usfirst.frc.team5401.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5401.robot.RobotMap;

public class RollerArm2 extends Subsystem {

    private VictorSP grabberLeft = new VictorSP();
    private VictorSP grabberRight = new VictorSP();
    private DoubleSolenoid doubleSolenoidLeft = new DoubleSolenoid();
    private DoubleSolenoid doubleSolenoidRight = new DoubleSolenoid();


    // Put methods for controlling this subsystem
    // here. Call these from Commands.



    public void initDefaultCommand() {
        // TODO: Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }

    private void turnGrabbers(int joystick) {
        if (joystick == 1 || joystick == -1) {
            grabberLeft.set(joystick * RobotMap.ROLLER_SPEED);
            grabberRight.set(joystick * RobotMap.ROLLER_SPEED);
        } else {
            grabberLeft.set(0);
            grabberRight.set(0);
        }
    }

    private void inOut(int direction) {
        if (direction == 1) {
            doubleSolenoidLeft.set(DoubleSolenoid.Value.kForward);
            doubleSolenoidRight.set(DoubleSolenoid.Value.kForward);
        } else if (direction == -1){
            doubleSolenoidLeft.set(DoubleSolenoid.Value.kReverse);
            doubleSolenoidRight.set(DoubleSolenoid.Value.kReverse);
        }
    }
}