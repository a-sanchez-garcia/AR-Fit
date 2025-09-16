-- ===============================
-- 1️⃣ Exercise (primero, porque es FK en users)
-- ===============================
INSERT INTO exercise (id, name, description, muscle_group, equipment) VALUES
(1, 'Bench Press', 'Chest press exercise', 'Chest', 'Barbell'),
(2, 'Squat', 'Leg strength exercise', 'Legs', 'Barbell'),
(3, 'Deadlift', 'Back and leg exercise', 'Back', 'Barbell'),
(4, 'Bicep Curl', 'Arm isolation exercise', 'Arms', 'Dumbbell');

-- ===============================
-- 2️⃣ Users
-- ===============================
INSERT INTO users (id, username, email, password, profile_picture, fav_exercise) VALUES
(1, 'johndoe', 'johndoe@example.com', 'hashedpassword1', 'profile1.jpg', 1),
(2, 'janedoe', 'janedoe@example.com', 'hashedpassword2', 'profile2.jpg', 2),
(3, 'fitfan', 'fitfan@example.com', 'hashedpassword3', 'profile3.jpg', 3);

-- ===============================
-- 3️⃣ Follow
-- ===============================
INSERT INTO follow (follower_id, followed_id) VALUES
(1, 2),
(1, 3),
(2, 3);

-- ===============================
-- 4️⃣ Workout
-- ===============================
INSERT INTO workout (id, user_id, title, description) VALUES
(1, 1, 'Chest Day', 'Focus on chest muscles'),
(2, 2, 'Leg Day', 'Strength and endurance for legs');

-- ===============================
-- 5️⃣ Workout_Exercise
-- ===============================
INSERT INTO workout_exercise (id, workout_id, exercise_id, order_number, planned_sets) VALUES
(1, 1, 1, 1, 4),
(2, 2, 2, 1, 5),
(3, 2, 3, 2, 3);

-- ===============================
-- 6️⃣ Training_Session
-- ===============================
INSERT INTO training_session (id, user_id, workout_id, started_at, finished_at) VALUES
(1, 1, 1, '2025-09-12 08:00:00', '2025-09-12 09:00:00'),
(2, 2, 2, '2025-09-12 10:00:00', '2025-09-12 11:30:00');

-- ===============================
-- 7️⃣ Training_Set
-- ===============================
INSERT INTO training_set (id, session_id, workout_exercise_id, set_number, weight, reps) VALUES
(1, 1, 1, 1, 70.0, 10),
(2, 1, 1, 2, 75.0, 8),
(3, 2, 2, 1, 100.0, 10),
(4, 2, 3, 1, 120.0, 6);

-- ===============================
-- 8️⃣ Progress_Log
-- ===============================
INSERT INTO progress_log (id, user_id, exercise_id, date, weight, reps, sets) VALUES
(1, 1, 1, '2025-09-11', 70.0, 10, 4),
(2, 2, 2, '2025-09-11', 100.0, 10, 5);

-- ===============================
-- 9️⃣ Record
-- ===============================
INSERT INTO record (id, user_id, exercise_id, weight, reps, date) VALUES
(1, 1, 1, 75.0, 8, '2025-09-12'),
(2, 2, 2, 110.0, 8, '2025-09-12');

-- ===============================
-- 🔟 Versus
-- ===============================
INSERT INTO versus (id, user1_id, user2_id, exercise_id, winner_id, date) VALUES
(1, 1, 2, 1, 1, '2025-09-10'),
(2, 2, 3, 2, 2, '2025-09-11');

-- ===============================
-- 1️⃣1️⃣ Post
-- ===============================
INSERT INTO post (id, user_id, content, image, video) VALUES
(1, 1, 'Great chest day today!', 'chest.jpg', NULL),
(2, 2, 'Leg day gains!', NULL, 'legday.mp4');

-- ===============================
-- 1️⃣2️⃣ Comment
-- ===============================
INSERT INTO comment (id, post_id, user_id, content) VALUES
(1, 1, 2, 'Nice work!'),
(2, 2, 1, 'Looking strong!');

-- ===============================
-- 1️⃣3️⃣ Chat
-- ===============================
INSERT INTO chat (id, user1_id, user2_id) VALUES
(1, 1, 2),
(2, 2, 3);

-- ===============================
-- 1️⃣4️⃣ Message
-- ===============================
INSERT INTO message (id, chat_id, sender_id, content) VALUES
(1, 1, 1, 'Hey, ready for workout today?'),
(2, 1, 2, 'Yes! Let’s crush it!');

-- ===============================
-- 1️⃣5️⃣ Notification
-- ===============================
INSERT INTO notification (id, user_id, type, message) VALUES
(1, 1, 'like', 'User 2 liked your post'),
(2, 2, 'comment', 'User 1 commented on your post');
