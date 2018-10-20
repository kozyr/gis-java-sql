CREATE INDEX idx_ride_start_geom ON ride USING gist(start_geom);
CREATE INDEX idx_ride_end_geom ON ride USING gist(end_geom);