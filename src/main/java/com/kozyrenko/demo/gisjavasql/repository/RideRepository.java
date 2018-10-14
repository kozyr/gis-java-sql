package com.kozyrenko.demo.gisjavasql.repository;

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
    List<Ride> findByStartLocation(@Param("point") Point point, @Param("radius") float radius);

    @Transactional(readOnly = true)
    @Query("SELECT r FROM Ride r where dwithin(:point, r.endGeom, :radius)=true")
    List<Ride> findByEndLocation(@Param("point") Point point, @Param("radius") float radius);
}
