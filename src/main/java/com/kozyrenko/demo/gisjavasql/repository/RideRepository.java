package com.kozyrenko.demo.gisjavasql.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.kozyrenko.demo.gisjavasql.model.Ride;
import com.vividsolutions.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    @Transactional(readOnly = true)
    @Query("SELECT r FROM Ride r where dwithin(:point, r.startGeom, :radius)=true")
    List<Ride> findByStartLocation(@Param("point") Point center, @Param("radius") float radius);

    @Transactional(readOnly = true)
    @Query(value = "SELECT json_build_object(" +
            "         'type', 'FeatureCollection'," +
            "         'features', json_agg(" +
            "                       json_build_object(" +
            "                         'type',       'Feature'," +
            "                         'id',         id," +
            "                         'geometry',   ST_AsGeoJSON(ST_Point(end_location_long,end_location_lat))\\:\\:json," +
            "                           'properties',   row_to_json(ride)\\:\\:jsonb - 'end_geom' - 'end_location_long' - 'end_location_lat'" +
            "                        )" +
            "             )" +
            "          )" +
            " FROM (select id, driver_rating, rider_rating, end_geom, end_location_long, end_location_lat from ride) ride " +
            "WHERE st_dwithin(ride.end_geom,ST_Transform(ST_SetSRID(ST_Point(:lon,:lat),4326),3857),:radius) limit 10000",
    nativeQuery = true)
    JsonNode findByEndLocation(@Param("lat") double lat, @Param("lon") double lon, @Param("radius") int radius);
}
