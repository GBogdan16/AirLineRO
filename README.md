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

