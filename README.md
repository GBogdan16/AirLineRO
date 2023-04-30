# AirLineRO
Proiectul este o aplicatie din consola cu o functie de login/register prin care un user si un admin au diferite optiuni.

Un user are urmatoarele optiuni :
1.Afisarea zborurilor disponibile
2.Cumpararea un bilet in functie de numarul zborului
3.Afisarea biletelor pe care le detine 
4.Stergerea unui bilet in functie de numarul zborului.

Un admin are urmatoarele optiuni:
1.Crearea unui admin
2.Afisare zboruri
3.Crearea unui zbor cu flightname/date/hour
4.Actualizarea unui zbor in functie de numele zborului actualizam data si ora
5.Stergerea unui zbor in functie de numele lui.

Pentru proiect am folosit ca limbaj de programare java 17 si pentru baza de date pgAdmin-PostgreSQL.
Proiectul are 3 tabele : users cu coloanele email,password,isadmin ; tickets cu coloanele idflight,iduser  ; flights cu coloanele flightname,date,hour.

CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL DEFAULT 'nextval('users_id_seq'::regclass)',
    email character(40) COLLATE pg_catalog."default" NOT NULL,
    password character(40) COLLATE pg_catalog."default" NOT NULL,
    isadmin boolean NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.tickets
(
    id bigint NOT NULL DEFAULT 'nextval('"Tickets_id_seq"'::regclass)',
    idflight integer NOT NULL,
    iduser integer NOT NULL,
    CONSTRAINT "Tickets_pkey" PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.flights
(
    id bigint NOT NULL DEFAULT 'nextval('"Flights_id_seq"'::regclass)',
    flightname character(40) COLLATE pg_catalog."default" NOT NULL,
    date character(40) COLLATE pg_catalog."default" NOT NULL,
    hour character(40) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Flights_pkey" PRIMARY KEY (id)
)
