package org.usfirst.frc.team2506.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class MasterCANTalon {
	private CANTalon talon;
	private final int MAX_RPM = 870;
	
	public MasterCANTalon(int port) {
		talon = new CANTalon(port);
        talon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		talon.reverseSensor(false);
		talon.setProfile(0);
		talon.setF(0.1722);
        talon.setP(0.1);
        talon.setI(0); 
        talon.setD(0);
        talon.configNominalOutputVoltage(+0.0f, -0.0f);
        talon.configPeakOutputVoltage(+12.0f, -12.0f);
        talon.changeControlMode(TalonControlMode.Speed);
	}
	
	public void drive(double percent) {
		talon.set(percent * MAX_RPM);
	}
	
	public CANTalon getTalon() {
		return talon;
	}
}