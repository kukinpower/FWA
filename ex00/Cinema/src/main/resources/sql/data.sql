insert into cinema_users (user_id, email, password, first_name, last_name, phone)
values (1, 'some@mail.com', 'pass', 'Anton', 'Chekhov', '777')
     , (2, 'any@mail.com', 'pass', 'Mikhail', 'Bulgakov', '899999')
     , (3, 'fyodor@mail.com', 'pass', 'Fyodor', 'Dostoevsky', '+7899999')
     on conflict do nothing
;
