-- Create table t_user_contact
CREATE TABLE public.t_user_contact (
    id bigint NOT NULL,
    ref_mku_user_mku_id_owner bigint NOT NULL,
    ref_mku_user_mku_id_contact bigint NOT NULL
);

CREATE SEQUENCE public.t_user_contact_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE ONLY public.t_user_contact ALTER COLUMN id SET DEFAULT nextval('public.t_user_contact_id_seq'::regclass);

SELECT pg_catalog.setval('public.t_user_contact_id_seq', 1, false);

--ALTER TABLE ONLY public.t_user_contact
--    ADD CONSTRAINT t_user_contact_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.t_user_contact
    ADD CONSTRAINT fk_1_user_contact__mku_user FOREIGN KEY (ref_mku_user_mku_id_owner) REFERENCES public.t_mk_user(mku_id);
    
ALTER TABLE ONLY public.t_user_contact
	ADD CONSTRAINT fk_2_user_contact__mku_user FOREIGN KEY (ref_mku_user_mku_id_contact) REFERENCES public.t_mk_user(mku_id);
