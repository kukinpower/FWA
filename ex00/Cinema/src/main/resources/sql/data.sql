insert into cinema_users (user_id, email, password, first_name, last_name, phone_number, image_filename)
values (1, 'some@mail.com', 'pass', 'Anton', 'Chekhov', '777', 'shades.png')
     , (2, 'any@mail.com', 'pass', 'Mikhail', 'Bulgakov', '899999', 'shades.png')
     , (3, 'fyodor@mail.com', 'pass', 'Fyodor', 'Dostoevsky', '+7899999', 'shades.png')
     on conflict do nothing
;
