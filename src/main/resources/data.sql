DROP TABLE IF EXISTS item;

CREATE TABLE item (
id INT AUTO_INCREMENT  PRIMARY KEY,
name VARCHAR(150) NOT NULL,
description VARCHAR(1000),
cost INT NOT NULL,
type VARCHAR(50)
);

insert into item (name, description, cost, type)
values
('Private Mentoring', 'Book private session with one of the mentors', 50, 'BASIC'),
('Extend Assignment', 'Extend SI week assignment deadline by one day', 500, 'BASIC'),
('Rent a Mentor', 'Mentor joins your team for one hour to help you with your project', 1000, 'EXTRA'),
('Off-School Day', 'The whole course goes to an off-school program for a day', 30000, 'EXTRA'),
('Workshop', 'Purchase 60 min workshop with a mentor or mentors on the topic of your choosing', 1000, 'EXTRA'),
('Extra materials', 'Get some extra materials for the topic you are currently studying', 500, 'EXTRA'),
('Dressing Up', 'All mentors should dress up as pirates (or just funny) for the day', 5000, 'EXTRA');

DROP TABLE IF EXISTS students;

CREATE TABLE students (
id INT AUTO_INCREMENT  PRIMARY KEY,
name VARCHAR(150) NOT NULL,
surname VARCHAR(150) NOT NULL,
role VARCHAR(50) NOT NULL,
passwordhash VARCHAR(150) NOT NULL,
email VARCHAR(150) NOT NULL,
balance INT NOT NULL,
earnings INT NOT NULL
);

INSERT INTO students (name, surname, role, passwordhash, email, balance, earnings)
VALUES
('Lukasz', 'Lesiuk', 'student', '---', 'lukasz.lesiuk.2@gmail.com', 1000, 3000),
('Uesugi', 'Kenshin', 'student', '---', 'uesugi.genshin@niigata.jp', 1500, 4000),
('Miamoto', 'Musashi', 'student', '---', 'miamoto.mushashi@nitenichi-ryu.jp', 0, 9000),
('name1', 'surname1', 'student', '---', 'mail@mail.com', 3750, 13000);
