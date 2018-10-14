package com.kozyrenko.demo.gisjavasql.util;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransformHelper {
    private final static int DEFAULT_GEOM = 4326;
    private final static int TARGET_GEOM = 3857;
    private final MathTransform transform;
    public static final double AUSTIN_DISTORTION = 0.86091;

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformHelper.class);

    private TransformHelper() {
        MathTransform temp = null;
        try {
            CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:" + DEFAULT_GEOM);
            CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:" + TARGET_GEOM);
            temp = CRS.findMathTransform(sourceCRS, targetCRS, false);
        } catch (FactoryException e) {
            LOGGER.error("Could not get CRS for EPSG " + TARGET_GEOM, e);
        }
        transform = temp;
    }

    public Point transform(double lat, double lon) throws TransformException {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), DEFAULT_GEOM);
        Point point = geometryFactory.createPoint(new Coordinate(lat, lon));
        Point result = (Point) JTS.transform(point, transform);
        result.setSRID(TARGET_GEOM);

        return result;
    }

    private static class LazyHolder {
        private static final TransformHelper INSTANCE = new TransformHelper();
    }

    public static TransformHelper getInstance() {
        return LazyHolder.INSTANCE;
    }
}

