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

CREATE TABLE public.abilities (
                                  id integer NOT NULL,
                                  name text NOT NULL
);


ALTER TABLE public.abilities OWNER TO postgres;

CREATE SEQUENCE public.abilities_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.abilities_id_seq OWNER TO postgres;

ALTER SEQUENCE public.abilities_id_seq OWNED BY public.abilities.id;


CREATE TABLE public.castles (
                                id integer NOT NULL,
                                name character varying NOT NULL,
                                side_id integer
);


ALTER TABLE public.castles OWNER TO postgres;

CREATE SEQUENCE public.castles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.castles_id_seq OWNER TO postgres;

ALTER SEQUENCE public.castles_id_seq OWNED BY public.castles.id;


CREATE TABLE public.creatures (
                                  id integer NOT NULL,
                                  name character varying NOT NULL,
                                  level integer,
                                  castle_id integer,
                                  damage_min integer,
                                  damage_max integer,
                                  attack integer,
                                  defense integer,
                                  health integer,
                                  speed integer,
                                  growth integer,
                                  gold integer,
                                  resource_id integer,
                                  resource_amount integer,
                                  ai_value integer
);


ALTER TABLE public.creatures OWNER TO postgres;

CREATE TABLE public.creatures_abilities (
                                            creature_id integer NOT NULL,
                                            ability_id integer NOT NULL
);


ALTER TABLE public.creatures_abilities OWNER TO postgres;

CREATE SEQUENCE public.creatures_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.creatures_id_seq OWNER TO postgres;

ALTER SEQUENCE public.creatures_id_seq OWNED BY public.creatures.id;


CREATE TABLE public.resources (
                                  id integer NOT NULL,
                                  name character varying NOT NULL
);


ALTER TABLE public.resources OWNER TO postgres;

CREATE SEQUENCE public.resources_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.resources_id_seq OWNER TO postgres;

ALTER SEQUENCE public.resources_id_seq OWNED BY public.resources.id;


CREATE TABLE public.sides (
                              id integer NOT NULL,
                              name character varying NOT NULL
);


ALTER TABLE public.sides OWNER TO postgres;

CREATE SEQUENCE public.sides_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sides_id_seq OWNER TO postgres;

ALTER SEQUENCE public.sides_id_seq OWNED BY public.sides.id;


ALTER TABLE ONLY public.abilities ALTER COLUMN id SET DEFAULT nextval('public.abilities_id_seq'::regclass);


ALTER TABLE ONLY public.castles ALTER COLUMN id SET DEFAULT nextval('public.castles_id_seq'::regclass);


ALTER TABLE ONLY public.creatures ALTER COLUMN id SET DEFAULT nextval('public.creatures_id_seq'::regclass);


ALTER TABLE ONLY public.resources ALTER COLUMN id SET DEFAULT nextval('public.resources_id_seq'::regclass);


ALTER TABLE ONLY public.sides ALTER COLUMN id SET DEFAULT nextval('public.sides_id_seq'::regclass);


ALTER TABLE ONLY public.abilities
    ADD CONSTRAINT abilities_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.castles
    ADD CONSTRAINT castles_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.creatures
    ADD CONSTRAINT creature_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.abilities
    ADD CONSTRAINT name_unique UNIQUE (name);


ALTER TABLE ONLY public.creatures_abilities
    ADD CONSTRAINT pk_creature_ability PRIMARY KEY (creature_id, ability_id);


ALTER TABLE ONLY public.resources
    ADD CONSTRAINT resources_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.sides
    ADD CONSTRAINT sides_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.creatures_abilities
    ADD CONSTRAINT fk_ability_id FOREIGN KEY (ability_id) REFERENCES public.abilities(id);


ALTER TABLE ONLY public.creatures
    ADD CONSTRAINT fk_castle_id FOREIGN KEY (castle_id) REFERENCES public.castles(id);


ALTER TABLE ONLY public.creatures_abilities
    ADD CONSTRAINT fk_creature_id FOREIGN KEY (creature_id) REFERENCES public.creatures(id);


ALTER TABLE ONLY public.creatures
    ADD CONSTRAINT fk_resource_id FOREIGN KEY (resource_id) REFERENCES public.resources(id);


ALTER TABLE ONLY public.castles
    ADD CONSTRAINT fk_side_id FOREIGN KEY (side_id) REFERENCES public.sides(id);


