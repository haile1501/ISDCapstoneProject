--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

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

DROP DATABASE IF EXISTS ecobike;
--
-- Name: ecobike; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE ecobike WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Vietnamese_Vietnam.1258';


ALTER DATABASE ecobike OWNER TO postgres;

\connect ecobike

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
-- Name: Bike; Type: TABLE; Schema: public; Owner: postgres
--

SELECT d.*, COUNT(b.barcode) AS bike_count
FROM public."Dock" d
LEFT JOIN public."Bike" b ON d.dock_id = b.dock_id
GROUP BY d.dock_id;

-- Lấy các row về bike hiện có trong dock
SELECT d.*, b.*
FROM public."Dock" d
LEFT JOIN public."Bike" b ON d.dock_id = b.dock_id;

CREATE TABLE public."Bike" (
    barcode character varying NOT NULL,
    bike_type_id integer,
    license_plate character varying,
    dock_id integer,
    battery_percentage integer
);


ALTER TABLE public."Bike" OWNER TO postgres;

--
-- Name: BikeType; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."BikeType" (
    type_id integer NOT NULL,
    type_name character varying,
    saddle_count integer,
    pedal_count integer,
    rear_seat_count integer,
    bike_value integer,
    rental_price_multiplier numeric(4,2) DEFAULT 1.0,
    has_electric_motor boolean
);


ALTER TABLE public."BikeType" OWNER TO postgres;

--
-- Name: COLUMN "BikeType".bike_value; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public."BikeType".bike_value IS 'value of bike in this type to calculate deposit fee';


--
-- Name: BikeType_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."BikeType_type_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."BikeType_type_id_seq" OWNER TO postgres;

--
-- Name: BikeType_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."BikeType_type_id_seq" OWNED BY public."BikeType".type_id;


--
-- Name: Dock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Dock" (
    dock_id integer NOT NULL,
    dock_name character varying,
    capacity integer,
    area integer,
    address character varying
);


ALTER TABLE public."Dock" OWNER TO postgres;

--
-- Name: Dock_dock_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Dock_dock_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Dock_dock_id_seq" OWNER TO postgres;

--
-- Name: Dock_dock_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Dock_dock_id_seq" OWNED BY public."Dock".dock_id;


--
-- Name: Rental; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Rental" (
    rental_id integer NOT NULL,
    bike_barcode character varying,
    customer_name integer,
    card_number integer,
    start_time integer,
    end_time integer,
    rent_dock integer,
    return_dock integer,
    battery_percentage integer,
    status integer
);


ALTER TABLE public."Rental" OWNER TO postgres;

--
-- Name: Rental_rental_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Rental_rental_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Rental_rental_id_seq" OWNER TO postgres;

--
-- Name: Rental_rental_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Rental_rental_id_seq" OWNED BY public."Rental".rental_id;


--
-- Name: Transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Transaction" (
    transaction_id integer NOT NULL,
    rental_id integer,
    amount integer,
    status integer
);


ALTER TABLE public."Transaction" OWNER TO postgres;

--
-- Name: COLUMN "Transaction".amount; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public."Transaction".amount IS 'amount to pay';


--
-- Name: Transaction_transaction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Transaction_transaction_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Transaction_transaction_id_seq" OWNER TO postgres;

--
-- Name: Transaction_transaction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Transaction_transaction_id_seq" OWNED BY public."Transaction".transaction_id;


--
-- Name: BikeType type_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."BikeType" ALTER COLUMN type_id SET DEFAULT nextval('public."BikeType_type_id_seq"'::regclass);


--
-- Name: Dock dock_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Dock" ALTER COLUMN dock_id SET DEFAULT nextval('public."Dock_dock_id_seq"'::regclass);


--
-- Name: Rental rental_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Rental" ALTER COLUMN rental_id SET DEFAULT nextval('public."Rental_rental_id_seq"'::regclass);


--
-- Name: Transaction transaction_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Transaction" ALTER COLUMN transaction_id SET DEFAULT nextval('public."Transaction_transaction_id_seq"'::regclass);


--
-- Data for Name: Bike; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: BikeType; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."BikeType" VALUES (1, 'Standard', 1, 1, 1, 400000, 1.00, false);
INSERT INTO public."BikeType" VALUES (2, 'Standard E-bike', 1, 1, 1, 700000, 1.50, true);
INSERT INTO public."BikeType" VALUES (3, 'Twin', 2, 2, 1, 550000, 1.50, false);


--
-- Data for Name: Dock; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Dock" VALUES (1, 'Bach Khoa', 100, 200, '1 Dai Co Viet, Bach Khoa, Hai Ba Trung');
INSERT INTO public."Dock" VALUES (2, 'Thuy Loi', 60, 120, '175 Tay Son, Trung Liet, Dong Da');
INSERT INTO public."Dock" VALUES (3, 'Kinh Te Quoc Dan', 60, 120, '207 Giai Phong, Dong Tam, Hai Ba Trung');
INSERT INTO public."Dock" VALUES (4, 'Xay Dung', 100, 200, '55 Giai Phong, Dong Tam, Hai Ba Trung');
INSERT INTO public."Dock" VALUES (5, 'Khoa Hoc Tu Nhien', 70, 140, '334 Nguyen Trai, Thanh Xuan Trung, Thanh Xuan');
INSERT INTO public."Dock" VALUES (6, 'Ngoai Thuong', 70, 140, '91 Chua Lang, Lang Thuong, Dong Da');


--
-- Data for Name: Rental; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: Transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: BikeType_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."BikeType_type_id_seq"', 3, true);


--
-- Name: Dock_dock_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Dock_dock_id_seq"', 6, true);


--
-- Name: Rental_rental_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Rental_rental_id_seq"', 1, false);


--
-- Name: Transaction_transaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Transaction_transaction_id_seq"', 1, false);


--
-- Name: BikeType BikeType_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."BikeType"
    ADD CONSTRAINT "BikeType_pk" PRIMARY KEY (type_id);


--
-- Name: Bike Bike_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Bike"
    ADD CONSTRAINT "Bike_pk" PRIMARY KEY (barcode);


--
-- Name: Dock Dock_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Dock"
    ADD CONSTRAINT "Dock_pk" PRIMARY KEY (dock_id);


--
-- Name: Rental Rental_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Rental"
    ADD CONSTRAINT "Rental_pk" PRIMARY KEY (rental_id);


--
-- Name: Transaction Transaction_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Transaction"
    ADD CONSTRAINT "Transaction_pk" PRIMARY KEY (transaction_id);


--
-- Name: Bike Bike_BikeType_type_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Bike"
    ADD CONSTRAINT "Bike_BikeType_type_id_fk" FOREIGN KEY (bike_type_id) REFERENCES public."BikeType"(type_id);


--
-- Name: Rental Rental_Bike_barcode_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Rental"
    ADD CONSTRAINT "Rental_Bike_barcode_fk" FOREIGN KEY (bike_barcode) REFERENCES public."Bike"(barcode);


--
-- Name: Rental Rental_Dock_dock_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Rental"
    ADD CONSTRAINT "Rental_Dock_dock_id_fk" FOREIGN KEY (rent_dock) REFERENCES public."Dock"(dock_id);


--
-- Name: Rental Rental_Dock_dock_id_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Rental"
    ADD CONSTRAINT "Rental_Dock_dock_id_fk2" FOREIGN KEY (return_dock) REFERENCES public."Dock"(dock_id);


--
-- Name: Transaction Transaction_Rental_rental_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Transaction"
    ADD CONSTRAINT "Transaction_Rental_rental_id_fk" FOREIGN KEY (rental_id) REFERENCES public."Rental"(rental_id);


--
-- PostgreSQL database dump complete
--

