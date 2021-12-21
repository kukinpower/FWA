insert into cinema_users (email, password, first_name, last_name, phone_number, image_filename)
values ('some@mail.com', 'pass', 'Anton', 'Chekhov', '777', 'shades.png')
     , ('any@mail.com', 'pass', 'Mikhail', 'Bulgakov', '899999', 'shades.png')
     , ('fyodor@mail.com', 'pass', 'Fyodor', 'Dostoevsky', '+7899999', 'shades.png')
     on conflict do nothing
;
