package org.usfirst.frc.team2506.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class SlaveCANTalon {
	private CANTalon talon;
	
	public SlaveCANTalon(int port, MasterCANTalon masterTalon) {
		talon = new CANTalon(port);
		talon.changeControlMode(TalonControlMode.Follower);
		talon.set(masterTalon.getTalon().getDeviceID());
	}
}
