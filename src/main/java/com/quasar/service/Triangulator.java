package com.quasar.service;

import java.awt.Point;

import com.quasar.model.SatelliteEntity;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

import com.lemmingapex.trilateration.TrilaterationFunction;
import com.quasar.exception.PositionUndeterminableException;

public class Triangulator {

    private final SatelliteEntity firstSatellite;
    private final SatelliteEntity secondSatellite;
    private final SatelliteEntity thirdSatellite;

    public Triangulator(SatelliteEntity firstSatellite, SatelliteEntity secondSatellite, SatelliteEntity thirdSatellite) {
        this.firstSatellite = firstSatellite;
        this.secondSatellite = secondSatellite;
        this.thirdSatellite = thirdSatellite;
    }

    public Point getLocation(double[] distances) {
        double[][] positions = {
            {firstSatellite.position.x, firstSatellite.position.y},
            {secondSatellite.position.x, secondSatellite.position.y},
            {thirdSatellite.position.x, thirdSatellite.position.y},};

        TrilaterationFunction trilaterationFunction = new TrilaterationFunction(positions, distances);
        LeastSquaresProblem problem = new LeastSquaresBuilder()
                .start(new double[]{0.0, 0.0})
                .model(trilaterationFunction)
                .target(new double[]{0.0, 0.0, 0.0})
                .lazyEvaluation(false)
                .maxEvaluations(1000)
                .maxIterations(1000)
                .build();

        LeastSquaresOptimizer optimizer = new LevenbergMarquardtOptimizer();
        LeastSquaresOptimizer.Optimum optimum = optimizer.optimize(problem);

        double[] estimated = optimum.getPoint().toArray();

        double maxError = 0;
        for (int i = 0; i < positions.length; i++) {
            double dx = estimated[0] - positions[i][0];
            double dy = estimated[1] - positions[i][1];
            double realDistance = Math.sqrt(dx * dx + dy * dy);
            double error = Math.abs(realDistance - distances[i]);
            maxError = Math.max(maxError, error);
        }

        if (maxError > 1.0) {
            throw new PositionUndeterminableException("Cant determinate point position. Please, verify the entries.");
        }

        return new Point(
                (int) Math.round(estimated[0]),
                (int) Math.round(estimated[1])
        );
    }

}
