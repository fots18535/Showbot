package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@Disabled
@TeleOp(name = "Showbot v2", group = "Ethan")
public class Showbot2 extends LinearOpMode {
    private DcMotor left;
    private DcMotor right;
    private Servo tail;

    @Override
    public void runOpMode() {
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        tail = hardwareMap.get(Servo.class, "tail");

        left.setDirection(DcMotorSimple.Direction.FORWARD);
        right.setDirection(DcMotorSimple.Direction.REVERSE);

        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData(">","Press Play to start OpMode");
        telemetry.update();

        waitForStart();

        double drive;
        double turn;
        if (opModeIsActive()) {
            while(opModeIsActive()) {
                drive = -gamepad1.left_stick_y;
                turn = gamepad1.right_stick_x;

                double leftPower = Range.clip(drive + turn, -1.0, 1.0);
                double rightPower = Range.clip(drive - turn, -1.0, 1.0);

                left.setPower(leftPower);
                right.setPower(rightPower);

                if (gamepad1.y) {
                    if (tail.getPosition() == 0) {
                        tail.setPosition(1);
                    } else {
                        tail.setPosition(0);
                    }
                }
            }
        }
    }
}
