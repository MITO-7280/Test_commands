/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;


public class Chassis extends SubsystemBase {
  
  public TalonFX leftMaster = new TalonFX(Constants.leftMastermotor);
  public TalonFX leftSlave = new TalonFX(Constants.leftSlavemotor);
  public TalonFX rightMaster = new TalonFX(Constants.rightMastermotor);
  public TalonFX rightSlave = new TalonFX(Constants.rightSlavemotor);

  public Solenoid modeSwitcher = new Solenoid(Constants.driveSwitcher);
  
  
  public double leftSpeed, rightSpeed, leftPosition, rightPosition;
  
  public Chassis() {
    Constants.Talon_init(leftMaster ,40,false);
    Constants.Talon_init(leftSlave  ,40,false);
    Constants.Talon_init(rightMaster,40,false);
    Constants.Talon_init(rightSlave ,40,false);

    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);

    leftMaster.setNeutralMode(NeutralMode.Brake);
    leftSlave.setNeutralMode(NeutralMode.Brake);
    rightMaster.setNeutralMode(NeutralMode.Brake);
    rightSlave.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
      super.setDefaultCommand(defaultCommand);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //show Velocity data
    SmartDashboard.putNumber("left Velocity RPM", leftMaster.getSelectedSensorVelocity()*Constants.cum_rpm);
    SmartDashboard.putNumber("right Velocity RPM", rightMaster.getSelectedSensorVelocity()*Constants.cum_rpm);

    //show position data
    SmartDashboard.putNumber("left Position rotation", leftMaster.getSelectedSensorPosition()*Constants.cum_rpm);
    SmartDashboard.putNumber("right Positon rotation", rightMaster.getSelectedSensorPosition()*Constants.cum_rpm);

    //show drive direction data
    SmartDashboard.putBoolean("isForward", RobotContainer.judge.isForward);

    //show drive speed data 
    SmartDashboard.putBoolean("isHighSpeed", RobotContainer.judge.isHighSpeed);

    //active solenoid to fast of slow
    modeSwitcher.set(RobotContainer.judge.isHighSpeed);
  }

  public void configVelocityPID(){
    Constants.setFalconPID(leftMaster, 0, 0.1, 0, 0);
    Constants.setFalconPID(rightMaster, 0, 0, 0, 0);
  }

  public void configPositionPID(){
    Constants.setFalconPID(leftMaster, 0, 0, 0, 0);
    Constants.setFalconPID(rightMaster, 0, 0, 0, 0);
  }
  
  public void drive(double xValue, double yValue){
    //set drive speed and drive with velocity PID
    configVelocityPID();
    if (RobotContainer.judge.isForward){
        if (RobotContainer.judge.isHighSpeed){
            leftSpeed = (yValue + xValue)* Constants.highSpeedConstant;
            rightSpeed = (yValue - xValue)* Constants.highSpeedConstant;
        } else {
            leftSpeed = (yValue + xValue)* Constants.lowSpeedConstant;
            rightSpeed = (yValue - xValue)* Constants.lowSpeedConstant;
        }
    } 
    else {
        if (RobotContainer.judge.isHighSpeed){
            leftSpeed = -(yValue + xValue)* Constants.highSpeedConstant;
            rightSpeed = -(yValue - xValue)* Constants.highSpeedConstant;
        } else {
            leftSpeed = -(yValue + xValue)* Constants.lowSpeedConstant;
            rightSpeed = -(yValue - xValue)* Constants.lowSpeedConstant;
        }
    }
    speedDrive();
  }
  public void speedDrive(){
    //drive with velocity PID
    leftMaster.set(ControlMode.Velocity, leftSpeed);
    rightMaster.set(ControlMode.Velocity, rightSpeed);
  }
}