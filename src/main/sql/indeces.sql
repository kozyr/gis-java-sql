CREATE INDEX idx_ride_geom ON ride USING gist(geom);
CREATE INDEX idx_ride_driver_rating on ride(driver_rating);                                                                  ;