--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4
-- Dumped by pg_dump version 12.4

-- Started on 2020-09-24 14:30:08

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

--
-- TOC entry 203 (class 1259 OID 455686)
-- Name: t_mk_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_mk_user (
    mku_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    version bigint,
    mku_email character varying(255),
    mku_is_enabled boolean,
    mku_first_name character varying(255),
    mku_argos_id character varying(255),
    mku_last_name character varying(255),
    mku_pwd_reset_date timestamp without time zone,
    mku_password_hash character varying(255),
    mku_username character varying(255),
    mku_avatar bytea,
    mku_date_of_birth timestamp without time zone,
    mku_auth_provider character varying(255)
);


CREATE SEQUENCE public.t_mk_user_mku_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.t_mk_user_mku_id_seq OWNED BY public.t_mk_user.mku_id;

ALTER TABLE ONLY public.t_mk_user ALTER COLUMN mku_id SET DEFAULT nextval('public.t_mk_user_mku_id_seq'::regclass);

SELECT pg_catalog.setval('public.t_mk_user_mku_id_seq', 1, false);

ALTER TABLE ONLY public.t_mk_user
    ADD CONSTRAINT t_mk_user_pkey PRIMARY KEY (mku_id);

INSERT INTO public.T_MK_USER(MKU_ARGOS_ID, MKU_USERNAME, MKU_FIRST_NAME, MKU_LAST_NAME, MKU_EMAIL, MKU_PASSWORD_HASH, MKU_IS_ENABLED, MKU_PWD_RESET_DATE, CREATED_BY, VERSION, MKU_AUTH_PROVIDER, mku_avatar, mku_date_of_birth)
	VALUES('0000099246', 'bycnit', 'Supper', 'Admin', 'admin@bougues-construction.com', '$2a$10$qJvkQYc2PjVwEXsRS0VEq.bedlRlPwssYSi.J/U6tAn77plRGSt.a', true, '2010-01-01', 'bycnit', 0, 'LOCAL', '', '2010-01-01');

--
-- Name: role; Type: TABLE; role
--
CREATE TABLE public.role (
    role_id integer NOT NULL,
    name character varying(255),
    description character varying(512)
);

CREATE SEQUENCE public.role_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.role_role_id_seq OWNED BY public.role.role_id;
ALTER TABLE ONLY public.role ALTER COLUMN role_id SET DEFAULT nextval('public.role_role_id_seq'::regclass);
SELECT pg_catalog.setval('public.role_role_id_seq', 1, false);
ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_id_pkey PRIMARY KEY (role_id);

INSERT INTO public.role(name, description)
	VALUES('Administrator', 'Administrator');
INSERT INTO public.role(name, description)
	VALUES('User', 'User');
--
-- Name: User Role; Type: TABLE; user_role
--
CREATE TABLE public.user_role (
    role_id integer,
    user_id integer
);

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fk_user__reference_user FOREIGN KEY (user_id) REFERENCES public.t_mk_user(mku_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fk_user__reference_role FOREIGN KEY (role_id) REFERENCES public.role(role_id) ON UPDATE RESTRICT ON DELETE RESTRICT;

INSERT INTO public.user_role(role_id, user_id)
	VALUES(1, 1);

-- Table ref_transaction_status
CREATE TABLE public.ref_transaction_status (
    ref_transaction_status_code character varying(10),
    ref_transaction_status_desc character varying(255)
);

ALTER TABLE ONLY public.ref_transaction_status
    ADD CONSTRAINT ref_transaction_status_code_pkey PRIMARY KEY (ref_transaction_status_code);

-- Table ref_account_types
CREATE TABLE public.ref_account_types (
    ref_account_type_code character varying(10),
    ref_account_type_description character varying(255)
);

ALTER TABLE ONLY public.ref_account_types
    ADD CONSTRAINT ref_account_type_code_pkey PRIMARY KEY (ref_account_type_code);

CREATE TABLE public.ref_account_status (
    ref_account_status_code character varying(10),
    ref_account_status_description character varying(255)
);

ALTER TABLE ONLY public.ref_account_status
    ADD CONSTRAINT ref_account_status_code_pkey PRIMARY KEY (ref_account_status_code);

-- Table account
 CREATE TABLE public.account (
    account_number bigint NOT NULL,
    ref_account_types_account_type_code character varying(10),
	ref_account_status_account_status_code character varying(10),
	t_mk_user_mku_id bigint NOT NULL,
	account_current_balance decimal(12,0)
);

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_number_pkey PRIMARY KEY (account_number);
ALTER TABLE ONLY public.account
    ADD CONSTRAINT fk_account__ref_account_types FOREIGN KEY (ref_account_types_account_type_code) REFERENCES public.ref_account_types(ref_account_type_code) ON UPDATE RESTRICT ON DELETE RESTRICT;
ALTER TABLE ONLY public.account
    ADD CONSTRAINT fk_account__ref_account_status FOREIGN KEY (ref_account_status_account_status_code) REFERENCES public.ref_account_status(ref_account_status_code) ON UPDATE RESTRICT ON DELETE RESTRICT;

CREATE SEQUENCE public.account_account_number_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.account_account_number_seq OWNED BY public.account.account_number;

ALTER TABLE ONLY public.account ALTER COLUMN account_number SET DEFAULT nextval('public.account_account_number_seq'::regclass);
SELECT pg_catalog.setval('public.account_account_number_seq', 1, false);


-- Token t_verification_token
CREATE TABLE public.t_verification_token (
    token_id bigint NOT NULL,
    token_value character varying(255) NOT NULL,
    token_expiration_date timestamp without time zone,
    user_id bigint NOT NULL
);

CREATE SEQUENCE public.t_verification_token_token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE ONLY public.t_verification_token ALTER COLUMN token_id SET DEFAULT nextval('public.t_verification_token_token_id_seq'::regclass);

SELECT pg_catalog.setval('public.t_verification_token_token_id_seq', 1, false);

ALTER TABLE ONLY public.t_verification_token
    ADD CONSTRAINT t_verification_token_pkey PRIMARY KEY (token_id);

ALTER TABLE ONLY public.t_verification_token
    ADD CONSTRAINT fk_verification_token__mku_user FOREIGN KEY (user_id) REFERENCES public.t_mk_user(mku_id);

INSERT INTO public.t_verification_token (token_value, token_expiration_date, user_id) VALUES ('6a809910-0949-4404-80be-8a9fca730358', '2020-12-12 00:00:00', 1);

-- Table transaction
CREATE TABLE public.transaction (
	transaction_id bigint NOT NULL,
	ref_account_account_number_from bigint NOT NULL,
	ref_account_account_number_to bigint NOT NULL,
	transaction_amount decimal(12, 0),
	transaction_date_time timestamp without time zone,
	ref_transaction_status_transaction_status_code character varying(10),
	create_by character varying(255) NOT NULL,
	message_text character varying(255)
);

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_id_pkey PRIMARY KEY (transaction_id);
CREATE SEQUENCE public.transaction_transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.transaction_transaction_id_seq OWNED BY public.transaction.transaction_id;
ALTER TABLE ONLY public.transaction ALTER COLUMN transaction_id SET DEFAULT nextval('public.transaction_transaction_id_seq'::regclass);
SELECT pg_catalog.setval('public.transaction_transaction_id_seq', 1, false);

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT fk_1_transaction__account FOREIGN KEY (ref_account_account_number_from) REFERENCES public.account(account_number) ON UPDATE RESTRICT ON DELETE RESTRICT;
ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT fk_2_transaction__account FOREIGN KEY (ref_account_account_number_to) REFERENCES public.account(account_number) ON UPDATE RESTRICT ON DELETE RESTRICT;
ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT fk_transaction__ref_transaction_status FOREIGN KEY (ref_transaction_status_transaction_status_code) REFERENCES public.ref_transaction_status(ref_transaction_status_code) ON UPDATE RESTRICT ON DELETE RESTRICT;


    -- Table message_templates
    CREATE TABLE public.message_templates (
	message_id bigint NOT NULL,
	message_tex character varying(255) NOT NULL
);

ALTER TABLE ONLY public.message_templates
    ADD CONSTRAINT message_id_pkey PRIMARY KEY (message_id);

INSERT INTO public.ref_account_types(ref_account_type_code, ref_account_type_description)
VALUES('UNIT', 'Unit');
INSERT INTO public.ref_account_types(ref_account_type_code, ref_account_type_description)
VALUES('SCORE', 'Score');

INSERT INTO public.ref_account_status(ref_account_status_code, ref_account_status_description)
VALUES('ACTIVATE', 'Activate');
INSERT INTO public.ref_account_status(ref_account_status_code, ref_account_status_description)
VALUES('DEACTIVATE', 'Deactivate');
