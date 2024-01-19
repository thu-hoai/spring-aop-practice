-- Create table t_firebase_device_token
CREATE TABLE public.t_firebase_device_token (
    device_token_id bigint NOT NULL,
    device_token_value character varying(255) NOT NULL,
    ref_mku_user_mku_id bigint NOT NULL
);

CREATE SEQUENCE public.t_firebase_device_token_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE ONLY public.t_firebase_device_token ALTER COLUMN device_token_id SET DEFAULT nextval('public.t_firebase_device_token_seq'::regclass);

SELECT pg_catalog.setval('public.t_firebase_device_token_seq', 1, false);

-- ALTER TABLE ONLY public.t_firebase_device_token
--     ADD CONSTRAINT t_firebase_device_token_pkey PRIMARY KEY (device_token_id);

ALTER TABLE ONLY public.t_firebase_device_token
    ADD CONSTRAINT fk_firebase_device_token__mku_user FOREIGN KEY (ref_mku_user_mku_id) REFERENCES public.t_mk_user(mku_id);
