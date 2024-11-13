package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.DeviceNames;
import org.firstinspires.ftc.teamcode.auton.drive.SampleMecanumDrive;

@Config
@Autonomous(group = "ff")
public class RRAutonMultipleTrajectories extends LinearOpMode {
    public static double FORWARD_1 = 6;
    public static double STRAFE_LEFT = 18;
    public static double TURN_90 = 90;
    public static double TURN_135 = 135;
    public static double TURN_M45 = -45;
    public static double TURN_45 = 45;

    public static final double FORWARD_2 = 6;

    DcMotor armMotor = null;
    Servo clawServo;
    Servo wristServo;
    DcMotor armMotor2;


    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        armMotor = hardwareMap.dcMotor.get(DeviceNames.MOTOR_ARM);
        armMotor2 = hardwareMap.dcMotor.get(DeviceNames.MOTOR_ARM2);
        clawServo = hardwareMap.servo.get(DeviceNames.SERVO_CLAW);

        wristServo = hardwareMap.servo.get(DeviceNames.SERVO_WRIST);

        Trajectory trajectory1 = drive.trajectoryBuilder(new Pose2d())
                .forward(FORWARD_1)
                .build();

        Trajectory trajectory2 = drive.trajectoryBuilder(trajectory1.end())
                .strafeLeft(STRAFE_LEFT)
                .build();

        Trajectory trajectory3 = drive.trajectoryBuilder(trajectory2.end())
                .forward(FORWARD_2)
                .build();

        Trajectory trajectory4 = drive.trajectoryBuilder(trajectory3.end())
                .back(FORWARD_2)
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectory(trajectory1);

        drive.followTrajectory(trajectory2);

        drive.turn(Math.toRadians(TURN_M45));


//        drive.turn(Math.toRadians(TURN_45));
//
//        drive.followTrajectory(trajectory3);
//
//        drive.followTrajectory(trajectory4);
//
//
//        drive.turn(Math.toRadians(TURN_M45));
//
//        drive.turn(Math.toRadians(TURN_45));
//
//        drive.followTrajectory(trajectory3);
//
//        drive.followTrajectory(trajectory4);
    }

}
