

CREATE TABLE IF NOT EXISTS public.usuario
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    nome character varying(100) COLLATE pg_catalog."default",
    email character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT usuario_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.telefone
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    ddd character varying(2) COLLATE pg_catalog."default",
    numero character varying(9) COLLATE pg_catalog."default",
    tipo character varying(11) COLLATE pg_catalog."default",
    usuario_id bigint,
    CONSTRAINT telefone_pkey PRIMARY KEY (id),
    CONSTRAINT fk_telefone_usuario FOREIGN KEY (usuario_id)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)



TABLESPACE pg_default;
