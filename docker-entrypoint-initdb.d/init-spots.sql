CREATE TABLE IF NOT EXISTS spot (
   id uuid primary key,
  created_date TIMESTAMP,
  last_updated_date TIMESTAMP,
  title varchar(50),
  description varchar(250)
);