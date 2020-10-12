insert into item (name, description, cost, type)
values
('Private Mentoring', 'Book private session with one of the mentors', 50, 'BASIC'),
('Extend Assignment', 'Extend SI week assignment deadline by one day', 500, 'BASIC'),
('Rent a Mentor', 'Mentor joins your team for one hour to help you with your project', 1000, 'EXTRA'),
('Off-School Day', 'The whole course goes to an off-school program for a day', 30000, 'EXTRA'),
('Workshop', 'Purchase 60 min workshop with a mentor or mentors on the topic of your choosing', 1000, 'EXTRA'),
('Extra materials', 'Get some extra materials for the topic you are currently studying', 500, 'EXTRA'),
('Dressing Up', 'All mentors should dress up as pirates (or just funny) for the day', 5000, 'EXTRA');


insert into quest (name, reward, short_description, details, instruction, is_extra)
values
('Spot mistake in assignment', 50, 'At Codecool we pay a lot of attention to the quality of our assignment instructions, but mistakes still happen. You can however let us know about them and not only make the life easier for your colleagues, but also earn some CCs!', 'The default value of quest is shown on the top, but a mentor can award more depending on your replies, especially your fix suggestion.', 'Please let us know: on which page the mistake is, what exactly is it and what is your idea to fix it?', 0),
('Spot mistake in assignment2', 500, 'At Codecool we pay a lot of attention to the quality of our assignment instructions, but mistakes still happen. You can however let us know about them and not only make the life easier for your colleagues, but also earn some CCs!', 'The default value of quest is " + reward + ", but a mentor can award more depending on your replies, especially your fix suggestion.', 'Please let us know: on which page the mistake is, what exactly is it and what is your idea to fix it?', 0),
('Spot mistake in assignment3', 250, 'At Codecool we pay a lot of attention to the quality of our assignment instructions, but mistakes still happen. You can however let us know about them and not only make the life easier for your colleagues, but also earn some CCs!', 'The default value of quest is " + reward + ", but a mentor can award more depending on your replies, especially your fix suggestion.', 'Please let us know: on which page the mistake is, what exactly is it and what is your idea to fix it?', 1);

INSERT INTO users (id, username, role, password)
VALUES
(1, 'nodi', 'user', '1'),
(2, 'user1', 'user', '1'),
(3, 'user2','user', '1');

--INSERT INTO users (username, name, surname, role, password)
--VALUES
--('nodi','Lukasz', 'Lesiuk', 'user', '1'),
--('user1','Uesugi', 'Kenshin', 'user', '1'),
--('user2','Miamoto', 'Musashi', 'user', '1'),
--('user3','name1', 'surname1', 'user', '1'),
--('mbart','Michal', 'Bartosik', 'user', '1');

insert into students (first_name, last_name, current_balance, rank, module, total_earnings, user_id)
values
('Lukasz', 'Lesiuk', 1000, 'Samurai', 'Web', 1000, 1),
('Michal', 'Bartosik', 500, 'Samurai', 'Web', 1000, 3)


--INSERT INTO authorities (username, authority)
--VALUES
--('nodi', 'USER'),
--('user1', 'USER'),
--('user2', 'USER'),
--('user3', 'USER');