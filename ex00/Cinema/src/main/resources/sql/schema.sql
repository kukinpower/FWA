create table if not exists "cinema_users"
(
    "user_id"    bigserial PRIMARY KEY,
    "email"      text,
    "password"   text,
    "first_name" text,
    "last_name"  text,
    "phone"      text
);

create table if not exists "auth_event_history"
(
    "event_id"       bigserial PRIMARY KEY,
    "cinema_user_id" bigserial NOT NULL,
    "event_type"     text,
    "event_time"     timestamp,
    "ip_address"     text
);

DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'auth_event_history_cinema_user_id_fkey') THEN
            ALTER TABLE auth_event_history
                ADD CONSTRAINT client_contact_contact_id_fkey
                    FOREIGN KEY (cinema_user_id) REFERENCES cinema_users(user_id);
        END IF;
    END;
$$;
