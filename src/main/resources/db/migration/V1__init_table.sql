CREATE table t_entry (
   id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   group_name varchar(50) DEFAULT NULL,
   entry_name varchar(50) DEFAULT NULL,
   entry_content jsonb DEFAULT NULL
);
CREATE table t_group (
   id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   group_name varchar(50) DEFAULT NULL,
   group_content jsonb DEFAULT NULL
);