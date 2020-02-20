/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
public class Judge extends SubsystemBase {
  
  public boolean isTableOn;
  public boolean isForward;
  public boolean isHighSpeed;

  
  public Judge() {

  }

  @Override
  public void setDefaultCommand(Command defaultCommand){
        super.setDefaultCommand(defaultCommand);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("isTableOn", isTableOn);
    SmartDashboard.putBoolean("isForward", isForward);
    SmartDashboard.putBoolean("isHighSpeed", isHighSpeed);
  }
  //speed
  public void toHighSpeed(){
    isHighSpeed = true;
  }

  public void toLowSpeed(){
    isHighSpeed = false;
  }

  public double accuracy(double value){
    if (value >= 0.05){
      return value;
    }
    if (value <= -0.05){
      return value;
    }
    return 0;
  }
  public void isForwardDetecting(){
    //drive forward or backward
    if (RobotContainer.oi.movestick.getPOV() == 0){
        isForward = true;
    } 
    else if (RobotContainer.oi.movestick.getPOV() == 180){
        isForward = false;
    }
  }
}
