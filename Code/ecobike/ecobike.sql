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

CREATE TABLE public."Bike" (
    barcode character varying NOT NULL,
    bike_type_id integer,
    license_plate character varying,
    dock_id integer,
    battery_percentage integer,
    is_rented boolean
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
    rental_price_multiplier numeric(4,2) DEFAULT 1.0
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
    customer_name character varying,
    card_number character varying,
    rent_dock integer,
    return_dock integer,
    start_time timestamp without time zone,
    end_time timestamp without time zone
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
    amount numeric,
    status integer,
    type character varying,
    transaction_time timestamp without time zone
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

INSERT INTO public."Bike" VALUES ('738944081559', 2, 'HCK 728', 4, 0, false);
INSERT INTO public."Bike" VALUES ('716339438957', 3, 'JZA 390', 1, NULL, false);
INSERT INTO public."Bike" VALUES ('123113223152', 1, 'ACB 126', 1, NULL, false);
INSERT INTO public."Bike" VALUES ('474531339517', 3, 'LOF 847', 6, NULL, false);
INSERT INTO public."Bike" VALUES ('038329643436', 1, 'KGX 562', 6, NULL, false);
INSERT INTO public."Bike" VALUES ('194380922350', 2, 'CDI 956', 6, 0, false);
INSERT INTO public."Bike" VALUES ('964262007259', 2, 'QCH 326', 3, 0, false);
INSERT INTO public."Bike" VALUES ('828829238561', 3, 'UQU 674', 2, NULL, false);
INSERT INTO public."Bike" VALUES ('471772068437', 2, 'URX 820', 6, 0, false);
INSERT INTO public."Bike" VALUES ('584178364795', 1, 'EQZ 701', 3, NULL, false);
INSERT INTO public."Bike" VALUES ('269776252771', 2, 'KEW 631', 4, 0, false);
INSERT INTO public."Bike" VALUES ('080535491881', 1, 'NJC 539', 6, NULL, false);
INSERT INTO public."Bike" VALUES ('783281825172', 3, 'YMQ 705', 3, NULL, false);
INSERT INTO public."Bike" VALUES ('860511867594', 3, 'OCR 974', 3, NULL, false);
INSERT INTO public."Bike" VALUES ('409797859982', 1, 'VJG 478', 2, NULL, false);
INSERT INTO public."Bike" VALUES ('158897874036', 2, 'MUV 956', 1, 0, false);
INSERT INTO public."Bike" VALUES ('843954972862', 2, 'HKK 090', 3, 0, false);
INSERT INTO public."Bike" VALUES ('920325880572', 2, 'KAS 001', 5, 0, false);
INSERT INTO public."Bike" VALUES ('290042346162', 2, 'MLQ 539', 6, 0, false);
INSERT INTO public."Bike" VALUES ('896211698376', 2, 'AEO 796', 3, 0, false);
INSERT INTO public."Bike" VALUES ('223774954482', 2, 'BSW 128', 4, 0, false);
INSERT INTO public."Bike" VALUES ('192992769044', 2, 'VZR 979', 3, 0, false);
INSERT INTO public."Bike" VALUES ('227000971838', 2, 'BKO 364', 4, 0, false);
INSERT INTO public."Bike" VALUES ('958982495296', 3, 'XGV 168', 2, NULL, false);
INSERT INTO public."Bike" VALUES ('607339699328', 2, 'IEW 380', 6, 0, false);
INSERT INTO public."Bike" VALUES ('333162573958', 3, 'DNY 604', 4, NULL, false);
INSERT INTO public."Bike" VALUES ('268174040848', 1, 'EPY 723', 4, NULL, false);
INSERT INTO public."Bike" VALUES ('276207848705', 2, 'HYJ 086', 5, 0, false);
INSERT INTO public."Bike" VALUES ('551828682789', 2, 'KQL 874', 6, 0, false);
INSERT INTO public."Bike" VALUES ('560004828061', 1, 'EGR 433', 6, NULL, false);
INSERT INTO public."Bike" VALUES ('360518273759', 1, 'ZMT 673', 6, NULL, false);
INSERT INTO public."Bike" VALUES ('984975164094', 1, 'TRB 210', 1, NULL, false);
INSERT INTO public."Bike" VALUES ('517191645235', 2, 'PIV 373', 2, 0, false);
INSERT INTO public."Bike" VALUES ('950626365369', 1, 'XXB 915', 2, NULL, false);
INSERT INTO public."Bike" VALUES ('575815769376', 1, 'IPY 486', 1, NULL, false);
INSERT INTO public."Bike" VALUES ('072635413262', 2, 'CFQ 398', 5, 0, false);
INSERT INTO public."Bike" VALUES ('789800019173', 1, 'FYI 419', 6, NULL, false);
INSERT INTO public."Bike" VALUES ('723629191168', 1, 'FMY 522', 3, NULL, false);
INSERT INTO public."Bike" VALUES ('015675632889', 1, 'FNB 294', 4, NULL, false);
INSERT INTO public."Bike" VALUES ('122353366007', 3, 'FZR 803', 6, NULL, false);
INSERT INTO public."Bike" VALUES ('874522751385', 3, 'VGY 496', 4, NULL, false);
INSERT INTO public."Bike" VALUES ('121649303675', 1, 'LPH 717', 5, NULL, false);
INSERT INTO public."Bike" VALUES ('470812313229', 1, 'BWO 342', 4, NULL, false);
INSERT INTO public."Bike" VALUES ('273423717625', 2, 'TCS 238', 2, 0, false);
INSERT INTO public."Bike" VALUES ('028039733906', 1, 'BGG 553', 3, NULL, false);
INSERT INTO public."Bike" VALUES ('890126962191', 1, 'PTC 394', 2, NULL, false);
INSERT INTO public."Bike" VALUES ('763142573541', 3, 'BAE 327', 6, NULL, false);
INSERT INTO public."Bike" VALUES ('075585094625', 1, 'UHZ 973', 6, NULL, false);
INSERT INTO public."Bike" VALUES ('746971212311', 2, 'YEB 142', 6, 0, false);
INSERT INTO public."Bike" VALUES ('124144111539', 1, 'SXF 102', 2, NULL, false);
INSERT INTO public."Bike" VALUES ('395762288629', 1, 'QGD 849', 3, NULL, false);
INSERT INTO public."Bike" VALUES ('916852705292', 2, 'KUM 125', 1, 0, false);
INSERT INTO public."Bike" VALUES ('734553814905', 1, 'LAH 301', 4, NULL, false);
INSERT INTO public."Bike" VALUES ('645744564428', 3, 'WDC 349', 2, NULL, false);
INSERT INTO public."Bike" VALUES ('922799958837', 2, 'CHH 484', 2, 0, false);
INSERT INTO public."Bike" VALUES ('415241099090', 3, 'VUJ 184', 6, NULL, false);
INSERT INTO public."Bike" VALUES ('089975629556', 1, 'BGE 889', 1, NULL, false);
INSERT INTO public."Bike" VALUES ('428639598769', 1, 'NOG 699', 6, NULL, false);
INSERT INTO public."Bike" VALUES ('467309449882', 2, 'JJY 867', 5, 0, false);
INSERT INTO public."Bike" VALUES ('807369304345', 3, 'UQW 304', 4, NULL, false);
INSERT INTO public."Bike" VALUES ('649582436340', 2, 'AEU 888', 4, 0, false);
INSERT INTO public."Bike" VALUES ('065036579101', 1, 'ZOX 688', 1, NULL, false);
INSERT INTO public."Bike" VALUES ('916871570894', 1, 'UTN 983', 1, NULL, false);
INSERT INTO public."Bike" VALUES ('512301075293', 3, 'PAN 823', 6, NULL, false);
INSERT INTO public."Bike" VALUES ('843450951695', 1, 'LIA 588', 2, NULL, false);
INSERT INTO public."Bike" VALUES ('605580097939', 2, 'FKC 726', 1, 0, false);
INSERT INTO public."Bike" VALUES ('023786540953', 2, 'LSO 996', 1, 0, false);
INSERT INTO public."Bike" VALUES ('536612340416', 1, 'OVU 221', 1, NULL, false);
INSERT INTO public."Bike" VALUES ('518223553331', 3, 'STH 527', 5, NULL, false);
INSERT INTO public."Bike" VALUES ('205605813288', 3, 'GPL 384', 4, NULL, false);
INSERT INTO public."Bike" VALUES ('696839780140', 3, 'LGP 782', 2, NULL, false);
INSERT INTO public."Bike" VALUES ('616195353799', 1, 'BBC 465', 5, NULL, false);
INSERT INTO public."Bike" VALUES ('503302575532', 1, 'PEH 379', 3, NULL, false);
INSERT INTO public."Bike" VALUES ('444591166117', 3, 'KNW 041', 2, NULL, false);
INSERT INTO public."Bike" VALUES ('609449848374', 2, 'XYU 058', 3, 0, false);
INSERT INTO public."Bike" VALUES ('096892597707', 3, 'GKQ 778', 1, NULL, false);
INSERT INTO public."Bike" VALUES ('739194801553', 2, 'FMI 450', 5, 0, false);
INSERT INTO public."Bike" VALUES ('380237846705', 1, 'MUH 694', 6, NULL, false);
INSERT INTO public."Bike" VALUES ('198970193300', 1, 'HMF 561', 3, NULL, false);
INSERT INTO public."Bike" VALUES ('573816332112', 1, 'XHS 949', 2, NULL, false);
INSERT INTO public."Bike" VALUES ('700935228155', 1, 'PVX 598', 6, NULL, false);
INSERT INTO public."Bike" VALUES ('692464621282', 1, 'NRS 859', 4, NULL, false);
INSERT INTO public."Bike" VALUES ('070591962285', 1, 'NBB 773', 6, NULL, false);
INSERT INTO public."Bike" VALUES ('216603202208', 3, 'DZJ 848', 3, NULL, false);
INSERT INTO public."Bike" VALUES ('168022310827', 1, 'EKJ 341', 5, NULL, false);
INSERT INTO public."Bike" VALUES ('513799675830', 2, 'NLO 975', 6, 0, false);
INSERT INTO public."Bike" VALUES ('215518648795', 2, 'NBE 901', 4, 0, false);
INSERT INTO public."Bike" VALUES ('373203353805', 1, 'LWV 308', 2, NULL, false);
INSERT INTO public."Bike" VALUES ('062777212493', 2, 'SRT 364', 1, 0, false);
INSERT INTO public."Bike" VALUES ('718756317163', 3, 'WOS 980', 5, NULL, false);
INSERT INTO public."Bike" VALUES ('164309108013', 3, 'QKJ 879', 2, NULL, false);
INSERT INTO public."Bike" VALUES ('493162099399', 3, 'PFR 859', 5, NULL, false);
INSERT INTO public."Bike" VALUES ('045334947877', 1, 'AAE 944', 4, NULL, false);
INSERT INTO public."Bike" VALUES ('062552867163', 1, 'TXZ 440', 6, NULL, false);
INSERT INTO public."Bike" VALUES ('435671485253', 2, 'OHY 900', 6, 0, false);
INSERT INTO public."Bike" VALUES ('500226150519', 2, 'GWQ 619', 3, 0, false);
INSERT INTO public."Bike" VALUES ('776286783735', 3, 'PXY 052', 2, NULL, false);
INSERT INTO public."Bike" VALUES ('472853931871', 1, 'AHY 454', 2, NULL, false);
INSERT INTO public."Bike" VALUES ('533284766860', 3, 'ZFJ 150', 4, NULL, false);
INSERT INTO public."Bike" VALUES ('664880172850', 2, 'NHO 721', 5, 0, false);


--
-- Data for Name: BikeType; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."BikeType" VALUES (1, 'Standard', 1, 1, 1, 400000, 1.00);
INSERT INTO public."BikeType" VALUES (2, 'Standard E-bike', 1, 1, 1, 700000, 1.50);
INSERT INTO public."BikeType" VALUES (3, 'Twin', 2, 2, 1, 550000, 1.50);


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

SELECT pg_catalog.setval('public."Rental_rental_id_seq"', 19, true);


--
-- Name: Transaction_transaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Transaction_transaction_id_seq"', 29, true);


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

