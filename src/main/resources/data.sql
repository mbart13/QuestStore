insert into item (name, description, cost, type)
values
('Private Mentoring', 'Book private session with a mentor', 50, 'BASIC'),
('Extend Assignment', 'Extend SI week assignment deadline by one day', 500, 'BASIC'),
('Rent a Mentor', 'Mentor joins your team for one hour to help you with your project', 1000, 'EXTRA'),
('Off-School Day', 'The whole course goes to an off-school program for a day', 30000, 'EXTRA'),
('Workshop', 'Purchase 60 min workshop with a mentor or mentors on any topic you want', 1000, 'EXTRA'),
('Extra materials', 'Get some extra materials for the topic you are currently studying', 500, 'EXTRA'),
('Dressing Up', 'All mentors should dress up as pirates (or just funny) for the day', 5000, 'EXTRA');


insert into quest (name, reward, short_description, details, instruction, is_extra)
values
('Spot mistake in assignment', 50, 'At Codecool we pay a lot of attention to the quality of our assignment instructions, but mistakes still happen. You can however let us know about them and not only make the life easier for your colleagues, but also earn some CCs!', 'The default value of quest is shown on the top, but a mentor can award more depending on your replies, especially your fix suggestion.', 'Please let us know: on which page the mistake is, what exactly is it and what is your idea to fix it?', 0),
('Spot mistake in assignment2', 500, 'At Codecool we pay a lot of attention to the quality of our assignment instructions, but mistakes still happen. You can however let us know about them and not only make the life easier for your colleagues, but also earn some CCs!', 'The default value of quest is " + reward + ", but a mentor can award more depending on your replies, especially your fix suggestion.', 'Please let us know: on which page the mistake is, what exactly is it and what is your idea to fix it?', 0),
('Spot mistake in assignment3', 250, 'At Codecool we pay a lot of attention to the quality of our assignment instructions, but mistakes still happen. You can however let us know about them and not only make the life easier for your colleagues, but also earn some CCs!', 'The default value of quest is " + reward + ", but a mentor can award more depending on your replies, especially your fix suggestion.', 'Please let us know: on which page the mistake is, what exactly is it and what is your idea to fix it?', 1);

INSERT INTO users (id, first_name, last_name, username, email, role, password)
VALUES
(1, 'Noriaki', 'Kasai', 'nori', null, 'ROLE_ADMIN', '$2a$10$q85./aUgQSyvTI.1dypU/OUrociI5k82l0t4evmUgIYsRrB8hICdy'),
(2, 'Dominik', 'Starzyk', 'domi', null,'ROLE_MENTOR', '$2a$10$q85./aUgQSyvTI.1dypU/OUrociI5k82l0t4evmUgIYsRrB8hICdy'),
(3, 'Lukasz', 'Lesiuk', 'nodi', null,'ROLE_STUDENT', '$2a$10$q85./aUgQSyvTI.1dypU/OUrociI5k82l0t4evmUgIYsRrB8hICdy'),
(4, 'Michal', 'Bartosik', 'mbart', null,'ROLE_STUDENT', '$2a$10$q85./aUgQSyvTI.1dypU/OUrociI5k82l0t4evmUgIYsRrB8hICdy');

insert into students (current_balance, rank, module, total_earnings, user_id)
values
(1000, 'Samurai', 'Web', 1000, 3),
(500, 'Samurai', 'Web', 1000, 4);

insert into mentors (user_id)
values(2);

insert into courses (id, name)
values
(1, 'WAW.2019.10'),
(2, 'KRK.2019.10'),
(3, 'WAW.2020.02'),
(4, 'KRK.2020.02'),
(5, 'KRK.2020.05');

insert into mentor_courses (mentor_id, course_id)
values
(2, 2),
(2, 3);


--INSERT INTO authorities (username, authority)
--VALUES
--('nodi', 'USER'),
--('user1', 'USER'),
--('user2', 'USER'),
--('user3', 'USER');