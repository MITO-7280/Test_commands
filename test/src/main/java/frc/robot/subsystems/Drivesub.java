/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Drivesub extends SubsystemBase {
  //Motorport setting
  public TalonFX lhMotor = new TalonFX(Constants.lhMotorport);
  public TalonFX lbMotor = new TalonFX(Constants.lbMotorport);
  public TalonFX rhMotor = new TalonFX(Constants.rhMotorport);
  public TalonFX rbMotor = new TalonFX(Constants.rbMotorport);

}

