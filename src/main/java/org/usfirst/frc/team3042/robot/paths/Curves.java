package org.usfirst.frc.team3042.robot.paths;

import java.util.ArrayList;

import org.usfirst.frc.team3042.robot.paths.PathBuilder.Waypoint;
import org.usfirst.frc.team3042.lib.Path;
import org.usfirst.frc.team3042.lib.math.RigidTransform2d;
import org.usfirst.frc.team3042.lib.math.Rotation2d;
import org.usfirst.frc.team3042.lib.math.Translation2d;

public class Curves implements PathContainer {
    
    @Override
    public Path buildPath() {
        ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
        waypoints.add(new Waypoint(0, 0, 0, 60));
        waypoints.add(new Waypoint(5, 0, 1, 60));
        waypoints.add(new Waypoint(0, 0, 1, 60));
        waypoints.add(new Waypoint(5, 0, 3, 60));
        waypoints.add(new Waypoint(0, 0, 3, 60));
        waypoints.add(new Waypoint(5, 0, 5, 60));
        waypoints.add(new Waypoint(0, 0, 5, 60));

        return PathBuilder.buildPathFromWaypoints(waypoints);
    }
    
    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(230, 242), Rotation2d.fromDegrees(180.0)); 
    }

    @Override
    public boolean isReversed() {
        return false; 
    }
}