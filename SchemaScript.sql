CREATE TABLE IF NOT EXISTS public."CATEGORY"
(
    "category_ID" integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 4 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    category_name character(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "CATEGORY_pkey" PRIMARY KEY ("category_ID")
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."CATEGORY"
    OWNER to postgres;
Alter table public."CATEGORY" alter Column "category_ID" restart with 1;

CREATE TABLE IF NOT EXISTS public."DOCUMENTS"
(
    "doc_ID" integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    doc_name character varying(50) COLLATE pg_catalog."default",
    doc_author character(50) COLLATE pg_catalog."default",
    pub_date date,
    "no_of-copies" integer,
    article_name character varying(50) COLLATE pg_catalog."default",
    editor character(50) COLLATE pg_catalog."default",
    contributor character(50) COLLATE pg_catalog."default",
    "cat_ID" integer NOT NULL,
    "Issues" character varying COLLATE pg_catalog."default",
    "Publiser" character varying COLLATE pg_catalog."default",
    editions character varying COLLATE pg_catalog."default",
    CONSTRAINT "DOCUMENTS_pkey" PRIMARY KEY ("doc_ID"),
    CONSTRAINT "cat_ID" FOREIGN KEY ("cat_ID")
        REFERENCES public."CATEGORY" ("category_ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."DOCUMENTS"
    OWNER to postgres;
Alter table public."DOCUMENTS" alter Column "doc_ID" restart with 1;

CREATE TABLE IF NOT EXISTS public."USERS"
(
    "user_ID" integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_name character varying(50) COLLATE pg_catalog."default",
    usermob_no double precision,
    password character varying COLLATE pg_catalog."default",
    is_admin boolean NOT NULL DEFAULT false,
    CONSTRAINT "USERS_pkey" PRIMARY KEY ("user_ID")
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."USERS"
    OWNER to postgres;
Alter table public."USERS" alter Column "user_ID" restart with 1;

CREATE TABLE IF NOT EXISTS public."RECORDS"
(
    "docu_ID" integer,
    "user_ID" integer,
    copies_borrow integer,
    issue_date date,
    due_date date,
    status character varying COLLATE pg_catalog."default",
    CONSTRAINT "docu_ID" FOREIGN KEY ("docu_ID")
        REFERENCES public."DOCUMENTS" ("doc_ID") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT "user_ID" FOREIGN KEY ("user_ID")
        REFERENCES public."USERS" ("user_ID") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."RECORDS"
    OWNER to postgres;

