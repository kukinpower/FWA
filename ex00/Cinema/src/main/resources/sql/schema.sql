CREATE TABLE if not exists cinema_users (
    "user_id" bigserial PRIMARY KEY,
    "email" text,
    "password" text,
    "first_name" text,
    "last_name" text,
    "phone" text
);
