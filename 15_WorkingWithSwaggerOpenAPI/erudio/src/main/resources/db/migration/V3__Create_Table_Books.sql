-- Table: public.book

-- DROP TABLE IF EXISTS public.book;
CREATE SEQUENCE book_seq;

CREATE TABLE IF NOT EXISTS public.book
(
    id bigint NOT NULL DEFAULT nextval('book_seq'::regclass),
    author varchar(120) NOT NULL,
    launch_date timestamp  without time zone NOT NULL,
    price decimal(65,2) NOT NULL,
    title varchar(120) NOT NULL,
    CONSTRAINT book_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.book
    OWNER to postgres;