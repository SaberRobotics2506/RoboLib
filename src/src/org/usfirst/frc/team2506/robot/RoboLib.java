package org.usfirst.frc.team2506.robot;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import edu.wpi.first.wpilibj.*;

public class RoboLib {
	private Joystick joystick;
	private RobotDrive driveTrain;
	private Map<Integer, Solenoid[]> solenoids = new HashMap<Integer, Solenoid[]>();
	private boolean solenoidClear = true;
	
	public RoboLib(Joystick joystick) {
		this.joystick = joystick;
	}
	public RoboLib(int joystickPort) {
		this.joystick = new Joystick(joystickPort);
	}
	
	public void setupDriveTrain(int left, int right) {
		driveTrain = new RobotDrive(left, right);
	}
	public void setupDriveTrain(int frontLeft, int rearLeft, int frontRight, int rearRight) {
		driveTrain = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
	}
	public void setupDriveTrain(SpeedController left, SpeedController right) {
		driveTrain = new RobotDrive(left, right);
	}
	public void setupDriveTrain(SpeedController frontLeft, SpeedController rearLeft, SpeedController frontRight, SpeedController rearRight) {
		driveTrain = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
	}
	
	public void mapPiston(int button, int solenoidOn, int solenoidOff) {
		Solenoid[] sols = new Solenoid[2];
		sols[0] = new Solenoid(solenoidOn);
		sols[1] = new Solenoid(solenoidOff);
		
		solenoids.put(button, sols);
	}
	public void mapPiston(int button, Solenoid solenoidOn, Solenoid solenoidOff) {
		Solenoid[] sols = new Solenoid[2];
		sols[0] = solenoidOn;
		sols[1] = solenoidOff;
		
		solenoids.put(button, sols);
	}
	
	public void swapPiston (Solenoid solenoidOn, Solenoid solenoidOff) {
		if (!solenoidOn.get() && solenoidClear) {
			solenoidOn.set(true);
			solenoidOff.set(false);
			solenoidClear = false;
		}
		if (solenoidOn.get() && solenoidClear) {
			solenoidOn.set(false);
			solenoidOff.set(true);
			solenoidClear = false;
		}
	}
	
	public void main () {
		driveTrain.tankDrive(joystick.getRawAxis(0), joystick.getRawAxis(5));
		for (Entry<Integer, Solenoid[]> entry : solenoids.entrySet())
			if (joystick.getRawButton(entry.getKey()))
				swapPiston(entry.getValue()[0], entry.getValue()[1]);
	}
}
