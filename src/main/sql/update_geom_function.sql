CREATE OR REPLACE FUNCTION update_geom()
  RETURNS trigger AS
$BODY$
BEGIN
  NEW.start_geom=ST_Transform(ST_SetSRID(ST_Point(NEW.start_location_long,NEW.start_location_lat),4326),3857);
  NEW.end_geom=ST_Transform(ST_SetSRID(ST_Point(NEW.end_location_long,NEW.end_location_lat),4326),3857);
 RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql;