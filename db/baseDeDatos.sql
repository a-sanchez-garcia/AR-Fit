--
-- PostgreSQL database cluster dump
--

-- Started on 2025-09-16 11:23:00

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE admin;
ALTER ROLE admin WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS;

--
-- User Configurations
--








--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.10 (Debian 16.10-1.pgdg13+1)
-- Dumped by pg_dump version 17.0

-- Started on 2025-09-16 11:23:00

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2025-09-16 11:23:00

--
-- PostgreSQL database dump complete
--

--
-- Database "arfitdb" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.10 (Debian 16.10-1.pgdg13+1)
-- Dumped by pg_dump version 17.0

-- Started on 2025-09-16 11:23:00

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3605 (class 1262 OID 16384)
-- Name: arfitdb; Type: DATABASE; Schema: -; Owner: admin
--

CREATE DATABASE arfitdb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE arfitdb OWNER TO admin;

\connect arfitdb

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 240 (class 1259 OID 17450)
-- Name: chat; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.chat (
    id bigint NOT NULL,
    user1_id bigint NOT NULL,
    user2_id bigint NOT NULL
);


ALTER TABLE public.chat OWNER TO admin;

--
-- TOC entry 239 (class 1259 OID 17449)
-- Name: chat_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.chat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.chat_id_seq OWNER TO admin;

--
-- TOC entry 3606 (class 0 OID 0)
-- Dependencies: 239
-- Name: chat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.chat_id_seq OWNED BY public.chat.id;


--
-- TOC entry 238 (class 1259 OID 17430)
-- Name: comment; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.comment (
    id bigint NOT NULL,
    post_id bigint NOT NULL,
    user_id bigint NOT NULL,
    content text NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.comment OWNER TO admin;

--
-- TOC entry 237 (class 1259 OID 17429)
-- Name: comment_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.comment_id_seq OWNER TO admin;

--
-- TOC entry 3607 (class 0 OID 0)
-- Dependencies: 237
-- Name: comment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.comment_id_seq OWNED BY public.comment.id;


--
-- TOC entry 218 (class 1259 OID 17248)
-- Name: exercise; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.exercise (
    id bigint NOT NULL,
    name character varying(100) NOT NULL,
    description text,
    muscle_group character varying(50),
    equipment character varying(100)
);


ALTER TABLE public.exercise OWNER TO admin;

--
-- TOC entry 217 (class 1259 OID 17247)
-- Name: exercise_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.exercise_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.exercise_id_seq OWNER TO admin;

--
-- TOC entry 3608 (class 0 OID 0)
-- Dependencies: 217
-- Name: exercise_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.exercise_id_seq OWNED BY public.exercise.id;


--
-- TOC entry 220 (class 1259 OID 17262)
-- Name: follow; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.follow (
    id bigint NOT NULL,
    follower_id bigint NOT NULL,
    followed_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.follow OWNER TO admin;

--
-- TOC entry 219 (class 1259 OID 17261)
-- Name: follow_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.follow_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.follow_id_seq OWNER TO admin;

--
-- TOC entry 3609 (class 0 OID 0)
-- Dependencies: 219
-- Name: follow_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.follow_id_seq OWNED BY public.follow.id;


--
-- TOC entry 242 (class 1259 OID 17467)
-- Name: message; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.message (
    id bigint NOT NULL,
    chat_id bigint NOT NULL,
    sender_id bigint NOT NULL,
    content text NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    seen boolean DEFAULT false
);


ALTER TABLE public.message OWNER TO admin;

--
-- TOC entry 241 (class 1259 OID 17466)
-- Name: message_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.message_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.message_id_seq OWNER TO admin;

--
-- TOC entry 3610 (class 0 OID 0)
-- Dependencies: 241
-- Name: message_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.message_id_seq OWNED BY public.message.id;


--
-- TOC entry 244 (class 1259 OID 17488)
-- Name: notification; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.notification (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    type character varying(50),
    message text,
    is_read boolean DEFAULT false,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.notification OWNER TO admin;

--
-- TOC entry 243 (class 1259 OID 17487)
-- Name: notification_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.notification_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.notification_id_seq OWNER TO admin;

--
-- TOC entry 3611 (class 0 OID 0)
-- Dependencies: 243
-- Name: notification_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.notification_id_seq OWNED BY public.notification.id;


--
-- TOC entry 236 (class 1259 OID 17413)
-- Name: post; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.post (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    content text,
    image character varying(255),
    video character varying(255),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    likes_count integer DEFAULT 0,
    comments_count integer DEFAULT 0
);


ALTER TABLE public.post OWNER TO admin;

--
-- TOC entry 235 (class 1259 OID 17412)
-- Name: post_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.post_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.post_id_seq OWNER TO admin;

--
-- TOC entry 3612 (class 0 OID 0)
-- Dependencies: 235
-- Name: post_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.post_id_seq OWNED BY public.post.id;


--
-- TOC entry 230 (class 1259 OID 17350)
-- Name: progress_log; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.progress_log (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    exercise_id bigint NOT NULL,
    date date NOT NULL,
    weight numeric(5,2),
    reps integer,
    sets integer
);


ALTER TABLE public.progress_log OWNER TO admin;

--
-- TOC entry 229 (class 1259 OID 17349)
-- Name: progress_log_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.progress_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.progress_log_id_seq OWNER TO admin;

--
-- TOC entry 3613 (class 0 OID 0)
-- Dependencies: 229
-- Name: progress_log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.progress_log_id_seq OWNED BY public.progress_log.id;


--
-- TOC entry 232 (class 1259 OID 17367)
-- Name: record; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.record (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    exercise_id bigint NOT NULL,
    weight numeric(5,2) NOT NULL,
    reps integer NOT NULL,
    date date NOT NULL
);


ALTER TABLE public.record OWNER TO admin;

--
-- TOC entry 231 (class 1259 OID 17366)
-- Name: record_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.record_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.record_id_seq OWNER TO admin;

--
-- TOC entry 3614 (class 0 OID 0)
-- Dependencies: 231
-- Name: record_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.record_id_seq OWNED BY public.record.id;


--
-- TOC entry 226 (class 1259 OID 17315)
-- Name: training_session; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.training_session (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    workout_id bigint NOT NULL,
    started_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    finished_at timestamp without time zone
);


ALTER TABLE public.training_session OWNER TO admin;

--
-- TOC entry 225 (class 1259 OID 17314)
-- Name: training_session_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.training_session_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.training_session_id_seq OWNER TO admin;

--
-- TOC entry 3615 (class 0 OID 0)
-- Dependencies: 225
-- Name: training_session_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.training_session_id_seq OWNED BY public.training_session.id;


--
-- TOC entry 228 (class 1259 OID 17333)
-- Name: training_set; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.training_set (
    id bigint NOT NULL,
    session_id bigint NOT NULL,
    workout_exercise_id bigint NOT NULL,
    set_number integer NOT NULL,
    weight numeric(5,2) NOT NULL,
    reps integer NOT NULL
);


ALTER TABLE public.training_set OWNER TO admin;

--
-- TOC entry 227 (class 1259 OID 17332)
-- Name: training_set_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.training_set_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.training_set_id_seq OWNER TO admin;

--
-- TOC entry 3616 (class 0 OID 0)
-- Dependencies: 227
-- Name: training_set_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.training_set_id_seq OWNED BY public.training_set.id;


--
-- TOC entry 216 (class 1259 OID 17236)
-- Name: users; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    username character varying(100) NOT NULL,
    email character varying(150) NOT NULL,
    password character varying(255) NOT NULL,
    profile_picture character varying(255),
    fav_exercise bigint,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.users OWNER TO admin;

--
-- TOC entry 215 (class 1259 OID 17235)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO admin;

--
-- TOC entry 3617 (class 0 OID 0)
-- Dependencies: 215
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 234 (class 1259 OID 17386)
-- Name: versus; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.versus (
    id bigint NOT NULL,
    user1_id bigint NOT NULL,
    user2_id bigint NOT NULL,
    exercise_id bigint NOT NULL,
    winner_id bigint NOT NULL,
    date date NOT NULL
);


ALTER TABLE public.versus OWNER TO admin;

--
-- TOC entry 233 (class 1259 OID 17385)
-- Name: versus_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.versus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.versus_id_seq OWNER TO admin;

--
-- TOC entry 3618 (class 0 OID 0)
-- Dependencies: 233
-- Name: versus_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.versus_id_seq OWNED BY public.versus.id;


--
-- TOC entry 222 (class 1259 OID 17282)
-- Name: workout; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.workout (
    id bigint NOT NULL,
    user_id bigint,
    title character varying(150) NOT NULL,
    description text,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.workout OWNER TO admin;

--
-- TOC entry 224 (class 1259 OID 17297)
-- Name: workout_exercise; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.workout_exercise (
    id bigint NOT NULL,
    workout_id bigint NOT NULL,
    exercise_id bigint NOT NULL,
    order_number integer NOT NULL,
    planned_sets integer DEFAULT 3 NOT NULL
);


ALTER TABLE public.workout_exercise OWNER TO admin;

--
-- TOC entry 223 (class 1259 OID 17296)
-- Name: workout_exercise_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.workout_exercise_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.workout_exercise_id_seq OWNER TO admin;

--
-- TOC entry 3619 (class 0 OID 0)
-- Dependencies: 223
-- Name: workout_exercise_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.workout_exercise_id_seq OWNED BY public.workout_exercise.id;


--
-- TOC entry 221 (class 1259 OID 17281)
-- Name: workout_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.workout_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.workout_id_seq OWNER TO admin;

--
-- TOC entry 3620 (class 0 OID 0)
-- Dependencies: 221
-- Name: workout_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.workout_id_seq OWNED BY public.workout.id;


--
-- TOC entry 3358 (class 2604 OID 17453)
-- Name: chat id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.chat ALTER COLUMN id SET DEFAULT nextval('public.chat_id_seq'::regclass);


--
-- TOC entry 3356 (class 2604 OID 17433)
-- Name: comment id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.comment ALTER COLUMN id SET DEFAULT nextval('public.comment_id_seq'::regclass);


--
-- TOC entry 3339 (class 2604 OID 17251)
-- Name: exercise id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.exercise ALTER COLUMN id SET DEFAULT nextval('public.exercise_id_seq'::regclass);


--
-- TOC entry 3340 (class 2604 OID 17265)
-- Name: follow id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.follow ALTER COLUMN id SET DEFAULT nextval('public.follow_id_seq'::regclass);


--
-- TOC entry 3359 (class 2604 OID 17470)
-- Name: message id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.message ALTER COLUMN id SET DEFAULT nextval('public.message_id_seq'::regclass);


--
-- TOC entry 3362 (class 2604 OID 17491)
-- Name: notification id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.notification ALTER COLUMN id SET DEFAULT nextval('public.notification_id_seq'::regclass);


--
-- TOC entry 3352 (class 2604 OID 17416)
-- Name: post id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.post ALTER COLUMN id SET DEFAULT nextval('public.post_id_seq'::regclass);


--
-- TOC entry 3349 (class 2604 OID 17353)
-- Name: progress_log id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.progress_log ALTER COLUMN id SET DEFAULT nextval('public.progress_log_id_seq'::regclass);


--
-- TOC entry 3350 (class 2604 OID 17370)
-- Name: record id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.record ALTER COLUMN id SET DEFAULT nextval('public.record_id_seq'::regclass);


--
-- TOC entry 3346 (class 2604 OID 17318)
-- Name: training_session id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.training_session ALTER COLUMN id SET DEFAULT nextval('public.training_session_id_seq'::regclass);


--
-- TOC entry 3348 (class 2604 OID 17336)
-- Name: training_set id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.training_set ALTER COLUMN id SET DEFAULT nextval('public.training_set_id_seq'::regclass);


--
-- TOC entry 3337 (class 2604 OID 17239)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3351 (class 2604 OID 17389)
-- Name: versus id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.versus ALTER COLUMN id SET DEFAULT nextval('public.versus_id_seq'::regclass);


--
-- TOC entry 3342 (class 2604 OID 17285)
-- Name: workout id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.workout ALTER COLUMN id SET DEFAULT nextval('public.workout_id_seq'::regclass);


--
-- TOC entry 3344 (class 2604 OID 17300)
-- Name: workout_exercise id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.workout_exercise ALTER COLUMN id SET DEFAULT nextval('public.workout_exercise_id_seq'::regclass);


--
-- TOC entry 3595 (class 0 OID 17450)
-- Dependencies: 240
-- Data for Name: chat; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.chat (id, user1_id, user2_id) FROM stdin;
1	1	2
2	2	3
\.


--
-- TOC entry 3593 (class 0 OID 17430)
-- Dependencies: 238
-- Data for Name: comment; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.comment (id, post_id, user_id, content, created_at) FROM stdin;
1	1	2	Nice work!	2025-09-12 19:02:38.095416
2	2	1	Looking strong!	2025-09-12 19:02:38.095416
\.


--
-- TOC entry 3573 (class 0 OID 17248)
-- Dependencies: 218
-- Data for Name: exercise; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.exercise (id, name, description, muscle_group, equipment) FROM stdin;
1	Bench Press	Chest press exercise	Chest	Barbell
2	Squat	Leg strength exercise	Legs	Barbell
3	Deadlift	Back and leg exercise	Back	Barbell
4	Bicep Curl	Arm isolation exercise	Arms	Dumbbell
\.


--
-- TOC entry 3575 (class 0 OID 17262)
-- Dependencies: 220
-- Data for Name: follow; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.follow (id, follower_id, followed_id, created_at) FROM stdin;
1	1	2	2025-09-12 19:02:38.095416
2	1	3	2025-09-12 19:02:38.095416
3	2	3	2025-09-12 19:02:38.095416
\.


--
-- TOC entry 3597 (class 0 OID 17467)
-- Dependencies: 242
-- Data for Name: message; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.message (id, chat_id, sender_id, content, created_at, seen) FROM stdin;
1	1	1	Hey, ready for workout today?	2025-09-12 19:02:38.095416	f
2	1	2	Yes! Let’s crush it!	2025-09-12 19:02:38.095416	f
\.


--
-- TOC entry 3599 (class 0 OID 17488)
-- Dependencies: 244
-- Data for Name: notification; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.notification (id, user_id, type, message, is_read, created_at) FROM stdin;
1	1	like	User 2 liked your post	f	2025-09-12 19:02:38.095416
2	2	comment	User 1 commented on your post	f	2025-09-12 19:02:38.095416
\.


--
-- TOC entry 3591 (class 0 OID 17413)
-- Dependencies: 236
-- Data for Name: post; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.post (id, user_id, content, image, video, created_at, likes_count, comments_count) FROM stdin;
1	1	Great chest day today!	chest.jpg	\N	2025-09-12 19:02:38.095416	0	0
2	2	Leg day gains!	\N	legday.mp4	2025-09-12 19:02:38.095416	0	0
\.


--
-- TOC entry 3585 (class 0 OID 17350)
-- Dependencies: 230
-- Data for Name: progress_log; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.progress_log (id, user_id, exercise_id, date, weight, reps, sets) FROM stdin;
1	1	1	2025-09-11	70.00	10	4
2	2	2	2025-09-11	100.00	10	5
\.


--
-- TOC entry 3587 (class 0 OID 17367)
-- Dependencies: 232
-- Data for Name: record; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.record (id, user_id, exercise_id, weight, reps, date) FROM stdin;
1	1	1	75.00	8	2025-09-12
2	2	2	110.00	8	2025-09-12
\.


--
-- TOC entry 3581 (class 0 OID 17315)
-- Dependencies: 226
-- Data for Name: training_session; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.training_session (id, user_id, workout_id, started_at, finished_at) FROM stdin;
1	1	1	2025-09-12 08:00:00	2025-09-12 09:00:00
2	2	2	2025-09-12 10:00:00	2025-09-12 11:30:00
\.


--
-- TOC entry 3583 (class 0 OID 17333)
-- Dependencies: 228
-- Data for Name: training_set; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.training_set (id, session_id, workout_exercise_id, set_number, weight, reps) FROM stdin;
1	1	1	1	70.00	10
2	1	1	2	75.00	8
3	2	2	1	100.00	10
4	2	3	1	120.00	6
\.


--
-- TOC entry 3571 (class 0 OID 17236)
-- Dependencies: 216
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.users (id, username, email, password, profile_picture, fav_exercise, created_at) FROM stdin;
1	johndoe	johndoe@example.com	hashedpassword1	profile1.jpg	1	2025-09-12 19:02:38.095416
2	janedoe	janedoe@example.com	hashedpassword2	profile2.jpg	2	2025-09-12 19:02:38.095416
3	fitfan	fitfan@example.com	hashedpassword3	profile3.jpg	3	2025-09-12 19:02:38.095416
\.


--
-- TOC entry 3589 (class 0 OID 17386)
-- Dependencies: 234
-- Data for Name: versus; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.versus (id, user1_id, user2_id, exercise_id, winner_id, date) FROM stdin;
1	1	2	1	1	2025-09-10
2	2	3	2	2	2025-09-11
\.


--
-- TOC entry 3577 (class 0 OID 17282)
-- Dependencies: 222
-- Data for Name: workout; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.workout (id, user_id, title, description, created_at) FROM stdin;
1	1	Chest Day	Focus on chest muscles	2025-09-12 19:02:38.095416
2	2	Leg Day	Strength and endurance for legs	2025-09-12 19:02:38.095416
\.


--
-- TOC entry 3579 (class 0 OID 17297)
-- Dependencies: 224
-- Data for Name: workout_exercise; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.workout_exercise (id, workout_id, exercise_id, order_number, planned_sets) FROM stdin;
1	1	1	1	4
2	2	2	1	5
3	2	3	2	3
\.


--
-- TOC entry 3621 (class 0 OID 0)
-- Dependencies: 239
-- Name: chat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.chat_id_seq', 1, false);


--
-- TOC entry 3622 (class 0 OID 0)
-- Dependencies: 237
-- Name: comment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.comment_id_seq', 1, false);


--
-- TOC entry 3623 (class 0 OID 0)
-- Dependencies: 217
-- Name: exercise_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.exercise_id_seq', 1, false);


--
-- TOC entry 3624 (class 0 OID 0)
-- Dependencies: 219
-- Name: follow_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.follow_id_seq', 3, true);


--
-- TOC entry 3625 (class 0 OID 0)
-- Dependencies: 241
-- Name: message_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.message_id_seq', 1, false);


--
-- TOC entry 3626 (class 0 OID 0)
-- Dependencies: 243
-- Name: notification_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.notification_id_seq', 1, false);


--
-- TOC entry 3627 (class 0 OID 0)
-- Dependencies: 235
-- Name: post_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.post_id_seq', 1, false);


--
-- TOC entry 3628 (class 0 OID 0)
-- Dependencies: 229
-- Name: progress_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.progress_log_id_seq', 1, false);


--
-- TOC entry 3629 (class 0 OID 0)
-- Dependencies: 231
-- Name: record_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.record_id_seq', 1, false);


--
-- TOC entry 3630 (class 0 OID 0)
-- Dependencies: 225
-- Name: training_session_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.training_session_id_seq', 1, false);


--
-- TOC entry 3631 (class 0 OID 0)
-- Dependencies: 227
-- Name: training_set_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.training_set_id_seq', 1, false);


--
-- TOC entry 3632 (class 0 OID 0)
-- Dependencies: 215
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.users_id_seq', 6, true);


--
-- TOC entry 3633 (class 0 OID 0)
-- Dependencies: 233
-- Name: versus_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.versus_id_seq', 1, false);


--
-- TOC entry 3634 (class 0 OID 0)
-- Dependencies: 223
-- Name: workout_exercise_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.workout_exercise_id_seq', 1, false);


--
-- TOC entry 3635 (class 0 OID 0)
-- Dependencies: 221
-- Name: workout_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.workout_id_seq', 1, false);


--
-- TOC entry 3396 (class 2606 OID 17455)
-- Name: chat chat_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.chat
    ADD CONSTRAINT chat_pkey PRIMARY KEY (id);


--
-- TOC entry 3394 (class 2606 OID 17438)
-- Name: comment comment_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);


--
-- TOC entry 3370 (class 2606 OID 17255)
-- Name: exercise exercise_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.exercise
    ADD CONSTRAINT exercise_pkey PRIMARY KEY (id);


--
-- TOC entry 3372 (class 2606 OID 17270)
-- Name: follow follow_follower_id_followed_id_key; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.follow
    ADD CONSTRAINT follow_follower_id_followed_id_key UNIQUE (follower_id, followed_id);


--
-- TOC entry 3374 (class 2606 OID 17268)
-- Name: follow follow_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.follow
    ADD CONSTRAINT follow_pkey PRIMARY KEY (id);


--
-- TOC entry 3398 (class 2606 OID 17476)
-- Name: message message_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_pkey PRIMARY KEY (id);


--
-- TOC entry 3400 (class 2606 OID 17497)
-- Name: notification notification_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT notification_pkey PRIMARY KEY (id);


--
-- TOC entry 3392 (class 2606 OID 17423)
-- Name: post post_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (id);


--
-- TOC entry 3384 (class 2606 OID 17355)
-- Name: progress_log progress_log_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.progress_log
    ADD CONSTRAINT progress_log_pkey PRIMARY KEY (id);


--
-- TOC entry 3386 (class 2606 OID 17372)
-- Name: record record_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.record
    ADD CONSTRAINT record_pkey PRIMARY KEY (id);


--
-- TOC entry 3388 (class 2606 OID 17374)
-- Name: record record_user_id_exercise_id_key; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.record
    ADD CONSTRAINT record_user_id_exercise_id_key UNIQUE (user_id, exercise_id);


--
-- TOC entry 3380 (class 2606 OID 17321)
-- Name: training_session training_session_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.training_session
    ADD CONSTRAINT training_session_pkey PRIMARY KEY (id);


--
-- TOC entry 3382 (class 2606 OID 17338)
-- Name: training_set training_set_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.training_set
    ADD CONSTRAINT training_set_pkey PRIMARY KEY (id);


--
-- TOC entry 3366 (class 2606 OID 17246)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- TOC entry 3368 (class 2606 OID 17244)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3390 (class 2606 OID 17391)
-- Name: versus versus_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.versus
    ADD CONSTRAINT versus_pkey PRIMARY KEY (id);


--
-- TOC entry 3378 (class 2606 OID 17303)
-- Name: workout_exercise workout_exercise_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.workout_exercise
    ADD CONSTRAINT workout_exercise_pkey PRIMARY KEY (id);


--
-- TOC entry 3376 (class 2606 OID 17290)
-- Name: workout workout_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.workout
    ADD CONSTRAINT workout_pkey PRIMARY KEY (id);


--
-- TOC entry 3422 (class 2606 OID 17456)
-- Name: chat fk_chat_user1; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.chat
    ADD CONSTRAINT fk_chat_user1 FOREIGN KEY (user1_id) REFERENCES public.users(id);


--
-- TOC entry 3423 (class 2606 OID 17461)
-- Name: chat fk_chat_user2; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.chat
    ADD CONSTRAINT fk_chat_user2 FOREIGN KEY (user2_id) REFERENCES public.users(id);


--
-- TOC entry 3420 (class 2606 OID 17439)
-- Name: comment fk_comment_post; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fk_comment_post FOREIGN KEY (post_id) REFERENCES public.post(id) ON DELETE CASCADE;


--
-- TOC entry 3421 (class 2606 OID 17444)
-- Name: comment fk_comment_user; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3402 (class 2606 OID 17276)
-- Name: follow fk_follow_followed; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.follow
    ADD CONSTRAINT fk_follow_followed FOREIGN KEY (followed_id) REFERENCES public.users(id);


--
-- TOC entry 3403 (class 2606 OID 17271)
-- Name: follow fk_follow_follower; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.follow
    ADD CONSTRAINT fk_follow_follower FOREIGN KEY (follower_id) REFERENCES public.users(id);


--
-- TOC entry 3424 (class 2606 OID 17477)
-- Name: message fk_message_chat; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT fk_message_chat FOREIGN KEY (chat_id) REFERENCES public.chat(id) ON DELETE CASCADE;


--
-- TOC entry 3425 (class 2606 OID 17482)
-- Name: message fk_message_sender; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT fk_message_sender FOREIGN KEY (sender_id) REFERENCES public.users(id);


--
-- TOC entry 3426 (class 2606 OID 17498)
-- Name: notification fk_notification_user; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT fk_notification_user FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3419 (class 2606 OID 17424)
-- Name: post fk_post_user; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT fk_post_user FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3411 (class 2606 OID 17361)
-- Name: progress_log fk_progress_exercise; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.progress_log
    ADD CONSTRAINT fk_progress_exercise FOREIGN KEY (exercise_id) REFERENCES public.exercise(id);


--
-- TOC entry 3412 (class 2606 OID 17356)
-- Name: progress_log fk_progress_user; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.progress_log
    ADD CONSTRAINT fk_progress_user FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3413 (class 2606 OID 17380)
-- Name: record fk_record_exercise; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.record
    ADD CONSTRAINT fk_record_exercise FOREIGN KEY (exercise_id) REFERENCES public.exercise(id);


--
-- TOC entry 3414 (class 2606 OID 17375)
-- Name: record fk_record_user; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.record
    ADD CONSTRAINT fk_record_user FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3407 (class 2606 OID 17322)
-- Name: training_session fk_session_user; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.training_session
    ADD CONSTRAINT fk_session_user FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3408 (class 2606 OID 17327)
-- Name: training_session fk_session_workout; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.training_session
    ADD CONSTRAINT fk_session_workout FOREIGN KEY (workout_id) REFERENCES public.workout(id);


--
-- TOC entry 3409 (class 2606 OID 17339)
-- Name: training_set fk_set_session; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.training_set
    ADD CONSTRAINT fk_set_session FOREIGN KEY (session_id) REFERENCES public.training_session(id) ON DELETE CASCADE;


--
-- TOC entry 3410 (class 2606 OID 17344)
-- Name: training_set fk_set_wkex; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.training_set
    ADD CONSTRAINT fk_set_wkex FOREIGN KEY (workout_exercise_id) REFERENCES public.workout_exercise(id);


--
-- TOC entry 3401 (class 2606 OID 17256)
-- Name: users fk_user_fav_exercise; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_user_fav_exercise FOREIGN KEY (fav_exercise) REFERENCES public.exercise(id);


--
-- TOC entry 3415 (class 2606 OID 17407)
-- Name: versus fk_versus_exercise; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.versus
    ADD CONSTRAINT fk_versus_exercise FOREIGN KEY (exercise_id) REFERENCES public.exercise(id);


--
-- TOC entry 3416 (class 2606 OID 17392)
-- Name: versus fk_versus_user1; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.versus
    ADD CONSTRAINT fk_versus_user1 FOREIGN KEY (user1_id) REFERENCES public.users(id);


--
-- TOC entry 3417 (class 2606 OID 17397)
-- Name: versus fk_versus_user2; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.versus
    ADD CONSTRAINT fk_versus_user2 FOREIGN KEY (user2_id) REFERENCES public.users(id);


--
-- TOC entry 3418 (class 2606 OID 17402)
-- Name: versus fk_versus_winner; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.versus
    ADD CONSTRAINT fk_versus_winner FOREIGN KEY (winner_id) REFERENCES public.users(id);


--
-- TOC entry 3405 (class 2606 OID 17309)
-- Name: workout_exercise fk_wkex_exercise; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.workout_exercise
    ADD CONSTRAINT fk_wkex_exercise FOREIGN KEY (exercise_id) REFERENCES public.exercise(id);


--
-- TOC entry 3406 (class 2606 OID 17304)
-- Name: workout_exercise fk_wkex_workout; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.workout_exercise
    ADD CONSTRAINT fk_wkex_workout FOREIGN KEY (workout_id) REFERENCES public.workout(id) ON DELETE CASCADE;


--
-- TOC entry 3404 (class 2606 OID 17291)
-- Name: workout fk_workout_user; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.workout
    ADD CONSTRAINT fk_workout_user FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2025-09-16 11:23:01

--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.10 (Debian 16.10-1.pgdg13+1)
-- Dumped by pg_dump version 17.0

-- Started on 2025-09-16 11:23:01

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2025-09-16 11:23:01

--
-- PostgreSQL database dump complete
--

-- Completed on 2025-09-16 11:23:01

--
-- PostgreSQL database cluster dump complete
--

