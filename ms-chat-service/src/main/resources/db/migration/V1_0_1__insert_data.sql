-- chats
INSERT INTO chats (id, creation_date) VALUES (8, '2021-10-03 20:29:55.000000');
INSERT INTO chats (id, creation_date) VALUES (10, '2021-10-03 20:29:55.000000');

-- chat_participants
INSERT INTO chats_participants (id, left_chat, chat_id, user_id) VALUES (3, false, 8, 2);
INSERT INTO chats_participants (id, left_chat, chat_id, user_id) VALUES (4, false, 8, 1);
INSERT INTO chats_participants (id, left_chat, chat_id, user_id) VALUES (5, false, 10, 2);
INSERT INTO chats_participants (id, left_chat, chat_id, user_id) VALUES (6, true, 10, 5);

-- chat_messages
INSERT INTO chat_messages (id, date, text, author_id, chat_id, tweet_id) VALUES (5, '2021-10-03 20:39:55.000000', 'hello from MrCat', 2, 8, 6);
INSERT INTO chat_messages (id, date, text, author_id, chat_id, tweet_id) VALUES (6, '2021-10-03 20:40:19.000000', 'hello from John Doe', 1, 8, null);
INSERT INTO chat_messages (id, date, text, author_id, chat_id, tweet_id) VALUES (7, '2021-10-03 20:41:03.000000', 'test message 2 from John Doe', 1, 8, null);