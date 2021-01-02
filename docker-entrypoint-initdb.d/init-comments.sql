CREATE TABLE IF NOT EXISTS spot (
  id uuid primary key,
  created_date TIMESTAMP,
  last_updated_date TIMESTAMP,
  title varchar(50),
  description varchar(250)
);

create table if not exists comment (
  id uuid primary key,
  created_date timestamp,
  last_updated_date timestamp,
  spot_id uuid references spot(id),
  text varchar(250)
);