CREATE OR REPLACE FUNCTION update_geom()
  RETURNS trigger AS
$BODY$
BEGIN
 NEW.geom=ST_Transform(ST_SetSRID(ST_Point(NEW.lon,NEW.lat),4326),3857);

 RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql;