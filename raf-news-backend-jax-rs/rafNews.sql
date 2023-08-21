--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

-- Started on 2023-08-21 16:51:12

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
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
-- TOC entry 215 (class 1259 OID 16408)
-- Name: article; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.article (
    id integer NOT NULL,
    service_user_id integer NOT NULL,
    category_id integer NOT NULL,
    title text NOT NULL,
    body text,
    time_published timestamp with time zone NOT NULL,
    number_of_views integer
);


ALTER TABLE public.article OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16450)
-- Name: article_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.article ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.article_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 216 (class 1259 OID 16415)
-- Name: article_with_tag; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.article_with_tag (
    id integer NOT NULL,
    article_id integer NOT NULL,
    tag_id integer NOT NULL
);


ALTER TABLE public.article_with_tag OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16449)
-- Name: article_with_tag_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.article_with_tag ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.article_with_tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 217 (class 1259 OID 16420)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id integer NOT NULL,
    category_name text NOT NULL,
    description text
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16448)
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.category ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 218 (class 1259 OID 16429)
-- Name: comment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comment (
    id integer NOT NULL,
    article_id integer NOT NULL,
    author text NOT NULL,
    body text,
    time_published timestamp with time zone NOT NULL
);


ALTER TABLE public.comment OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16447)
-- Name: comment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.comment ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 214 (class 1259 OID 16399)
-- Name: service_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.service_user (
    id integer NOT NULL,
    username text NOT NULL,
    email text NOT NULL,
    pass text NOT NULL,
    user_role text NOT NULL,
    is_enabled text NOT NULL,
    first_name text NOT NULL,
    last_name text NOT NULL
);


ALTER TABLE public.service_user OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16445)
-- Name: service_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.service_user ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.service_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 219 (class 1259 OID 16436)
-- Name: tag; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tag (
    id integer NOT NULL,
    tag_name text NOT NULL
);


ALTER TABLE public.tag OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16446)
-- Name: tag_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.tag ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3359 (class 0 OID 16408)
-- Dependencies: 215
-- Data for Name: article; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.article (id, service_user_id, category_id, title, body, time_published, number_of_views) FROM stdin;
1	1	1	wsyhshg	assahgsh	2023-08-21 16:45:37.548266+02	3
\.


--
-- TOC entry 3360 (class 0 OID 16415)
-- Dependencies: 216
-- Data for Name: article_with_tag; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.article_with_tag (id, article_id, tag_id) FROM stdin;
1	1	1
2	1	2
\.


--
-- TOC entry 3361 (class 0 OID 16420)
-- Dependencies: 217
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (id, category_name, description) FROM stdin;
1	film	film category
\.


--
-- TOC entry 3362 (class 0 OID 16429)
-- Dependencies: 218
-- Data for Name: comment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comment (id, article_id, author, body, time_published) FROM stdin;
1	1	test	teaghastsays	2023-08-21 16:46:22.18081+02
\.


--
-- TOC entry 3358 (class 0 OID 16399)
-- Dependencies: 214
-- Data for Name: service_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.service_user (id, username, email, pass, user_role, is_enabled, first_name, last_name) FROM stdin;
1	admin	admin	$2a$10$Ugf8sZ48NErx1HINqPGavuv1Olyt.zYU9LRRO29C08a61zMnk8Tg.	admin	true	admin	admin
\.


--
-- TOC entry 3363 (class 0 OID 16436)
-- Dependencies: 219
-- Data for Name: tag; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tag (id, tag_name) FROM stdin;
1	indie
2	art
\.


--
-- TOC entry 3375 (class 0 OID 0)
-- Dependencies: 225
-- Name: article_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.article_id_seq', 1, true);


--
-- TOC entry 3376 (class 0 OID 0)
-- Dependencies: 224
-- Name: article_with_tag_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.article_with_tag_id_seq', 2, true);


--
-- TOC entry 3377 (class 0 OID 0)
-- Dependencies: 223
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_id_seq', 1, true);


--
-- TOC entry 3378 (class 0 OID 0)
-- Dependencies: 222
-- Name: comment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comment_id_seq', 1, true);


--
-- TOC entry 3379 (class 0 OID 0)
-- Dependencies: 220
-- Name: service_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.service_user_id_seq', 1, true);


--
-- TOC entry 3380 (class 0 OID 0)
-- Dependencies: 221
-- Name: tag_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tag_id_seq', 2, true);


--
-- TOC entry 3203 (class 2606 OID 16414)
-- Name: article article_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article
    ADD CONSTRAINT article_pkey PRIMARY KEY (id);


--
-- TOC entry 3205 (class 2606 OID 16419)
-- Name: article_with_tag article_with_tag_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article_with_tag
    ADD CONSTRAINT article_with_tag_pkey PRIMARY KEY (id);


--
-- TOC entry 3207 (class 2606 OID 16426)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 3211 (class 2606 OID 16435)
-- Name: comment comment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);


--
-- TOC entry 3199 (class 2606 OID 16405)
-- Name: service_user service_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.service_user
    ADD CONSTRAINT service_user_pkey PRIMARY KEY (id);


--
-- TOC entry 3213 (class 2606 OID 16442)
-- Name: tag tag_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (id);


--
-- TOC entry 3209 (class 2606 OID 16428)
-- Name: category unique_category_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT unique_category_name UNIQUE (category_name) INCLUDE (category_name);


--
-- TOC entry 3201 (class 2606 OID 16407)
-- Name: service_user unique_email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.service_user
    ADD CONSTRAINT unique_email UNIQUE (email) INCLUDE (email);


--
-- TOC entry 3215 (class 2606 OID 16444)
-- Name: tag unique_tag_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tag
    ADD CONSTRAINT unique_tag_name UNIQUE (tag_name) INCLUDE (tag_name);


-- Completed on 2023-08-21 16:51:13

--
-- PostgreSQL database dump complete
--

