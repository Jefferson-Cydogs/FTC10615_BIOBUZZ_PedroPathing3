package org.firstinspires.ftc.teamcode.cydogs.vision;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.cydogs.components.Turret;

/**
 * Aimer — decides how the turret looks each loop.
 * Modes:
 *   HOLD   — do not move the turret
 *   PRESET — look at a saved angle (degrees)
 *   TRACK  — if we see a good tag, center on it; if not, hold still
 *   SEARCH — sweep back and forth until a tag is found (use sparingly in auto)
 * Call update() once every loop.
 * Use isLocked() before shooting.
 * Hardware names live inside TagSight and Turret (Webcam 1, turret).
 */
public class Aimer {

    public enum Mode {
        HOLD,
        PRESET,
        TRACK,
        SEARCH
    }

    // How close to center counts as locked (degrees of bearing)
    private static final double LOCK_TOL_DEG = 3.0;

    // How hard to nudge toward the tag while tracking
    private static final double TRACK_GAIN = 0.15;

    // How far to move each loop while searching (turret degrees)
    private static final double SEARCH_STEP_DEG = 1.0;

    private final TagSight eyes;
    private final Turret turret;

    private Mode mode = Mode.HOLD;
    private double presetDegrees = 0.0;
    private double searchDirection = 1.0;

    /**
     * @param opMode    the running OpMode (needed for Turret)
     * @param alliance  RED, BLUE, or BOTH — passed through to TagSight
     */
    public Aimer(OpMode opMode, TagSight.Alliance alliance) {
        eyes = new TagSight(opMode.hardwareMap, alliance);
        turret = new Turret(opMode);
    }

    /** Do not move the turret. */
    public void hold() {
        mode = Mode.HOLD;
    }

    /**
     * Look toward a known angle (turret degrees).
     * Good for auto: preset before driving to a shoot pose.
     */
    public void preset(double turretDegrees) {
        presetDegrees = turretDegrees;
        mode = Mode.PRESET;
        turret.setTurretDegrees(presetDegrees);
    }

    /**
     * Follow a visible tag. If no tag, stay put (does not start a sweep).
     * Best default for match autos.
     */
    public void track() {
        mode = Mode.TRACK;
    }

    /**
     * Sweep until a tag appears, then you can switch to track().
     * Prefer preset + track in auto so you do not waste time scanning.
     */
    public void search() {
        mode = Mode.SEARCH;
    }

    public Mode getMode() {
        return mode;
    }

    /**
     * Run one step of the current mode.
     * Call this every loop of the OpMode (alongside follower.update() if driving).
     */
    public void update() {
        eyes.update();

        switch (mode) {
            case HOLD:
                // intentionally do nothing to the turret
                break;

            case PRESET:
                // keep (or re-apply) the saved angle
                turret.setTurretDegrees(presetDegrees);
                break;

            case TRACK:
                if (eyes.hasTarget) {
                    if (Math.abs(eyes.bearingDegrees) > LOCK_TOL_DEG) {
                        // If it tracks the wrong way, negate bearing:
                        // turret.nudgeByDegrees(-eyes.bearingDegrees * TRACK_GAIN);
                        turret.nudgeByDegrees(eyes.bearingDegrees * TRACK_GAIN);
                    }
                }
                // no target → hold current angle (no search)
                break;

            case SEARCH:
                if (eyes.hasTarget) {
                    // found something; fine-center like track
                    if (Math.abs(eyes.bearingDegrees) > LOCK_TOL_DEG) {
                        turret.nudgeByDegrees(eyes.bearingDegrees * TRACK_GAIN);
                    }
                } else {
                    if (turret.isAtMax()) {
                        searchDirection = -1.0;
                    } else if (turret.isAtMin()) {
                        searchDirection = 1.0;
                    }
                    turret.nudgeByDegrees(searchDirection * SEARCH_STEP_DEG);
                }
                break;
        }
    }

    /**
     * True when we see a valid target and it is centered enough to shoot.
     */
    public boolean isLocked() {
        return eyes.hasTarget && Math.abs(eyes.bearingDegrees) <= LOCK_TOL_DEG;
    }

    /** For telemetry or advanced use. */
    public TagSight getEyes() {
        return eyes;
    }

    /** For telemetry or advanced use. */
    public Turret getTurret() {
        return turret;
    }

    /** Call when the OpMode ends if you want to shut the camera down. */
    public void close() {
        eyes.close();
    }
}