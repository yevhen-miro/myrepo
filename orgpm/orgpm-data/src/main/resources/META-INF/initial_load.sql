insert into LOGIN(id,password) VALUES(1,'rdsPXngmyFfXN20b2bwzwMVEeQourJYUSoryKxKYyUA=');
insert into LOGIN(id,password) VALUES(2,'rdsPXngmyFfXN20b2bwzwMVEeQourJYUSoryKxKYyUA=');
insert into LOGIN(id,password) VALUES(3,'rdsPXngmyFfXN20b2bwzwMVEeQourJYUSoryKxKYyUA=');


insert into MITARBEITER (id,arbeitszeit, bemerkung, gruppe, hydroid, kartenid, status, kennung, nachname, personalid,vorname, rolle ) VALUES (1,8,'DWH-Entwickler','FAS','A136862',2456,0, 'gap87','Miroshnychenko',7772, 'Yevhen','ADMIN');
insert into MITARBEITER (id,arbeitszeit, bemerkung, gruppe, hydroid, kartenid, status, kennung, nachname, personalid,vorname,  rolle) VALUES (2,8,'SAS-Administrator','FCS','A136989',1236,0, 'gap01','Sinaga',4656, 'David','ADMIN');
insert into MITARBEITER (id,arbeitszeit, bemerkung, gruppe, hydroid, kartenid, status, kennung, nachname, personalid,vorname,  rolle) VALUES (3,8,'Java-Entwickler','FCS','A132362',2254,0, 'gap07','Reibiger',7772, 'Jens','USER');

insert into PROJEKTE  (id,bemerkung,hauptprojekt ,aufwand ,projektgruppe ,projekt_id ,projektleiter ,projekt_name ,ende ,start ,storno ,wartung, ganztägig ) VALUES (2,'Sammelprojekt','fls000',1200,'FLS','fls000','Engemann', 'Hotline-Service / Maintenance FLS',sysdate,sysdate,false,false,false);
insert into PROJEKTE  (id,bemerkung,hauptprojekt ,aufwand ,projektgruppe ,projekt_id ,projektleiter ,projekt_name ,ende ,start ,storno ,wartung, ganztägig ) VALUES (1,'Sammelprojekt','kta114',780,'FLS','kta114','Schmitz', 'Erneuerung Produktionsbuch / Datawarehouse',sysdate,sysdate,false,false,false);
insert into PROJEKTE  (id,bemerkung,hauptprojekt ,aufwand ,projektgruppe ,projekt_id ,projektleiter ,projekt_name ,ende ,start ,storno ,wartung, ganztägig ) VALUES (3,'Sammelprojekt','SAP000',440,'FLS','sap000','Schmitz', 'Hotline-Service SAP Interface',sysdate,sysdate,false,false,false);
insert into PROJEKTE  (id,bemerkung,hauptprojekt ,aufwand ,projektgruppe ,projekt_id ,projektleiter ,projekt_name ,ende ,start ,storno ,wartung, ganztägig ) VALUES (4,'Sammelprojekt','z-fehl',0,null,'z-fehl',null, 'Fehlzeit',null,null,false,false,true);
insert into PROJEKTE  (id,bemerkung,hauptprojekt ,aufwand ,projektgruppe ,projekt_id ,projektleiter ,projekt_name ,ende ,start ,storno ,wartung, ganztägig ) VALUES (5,'Sammelprojekt','z-gleit',0,null,'z-gleit',null, 'Gleittag',null,null,false,false,true);
insert into PROJEKTE  (id,bemerkung,hauptprojekt ,aufwand ,projektgruppe ,projekt_id ,projektleiter ,projekt_name ,ende ,start ,storno ,wartung, ganztägig ) VALUES (6,'Sammelprojekt','z-schul',0,null,'z-schul',null, 'Schulung',null,null,false,false,true);
insert into PROJEKTE  (id,bemerkung,hauptprojekt ,aufwand ,projektgruppe ,projekt_id ,projektleiter ,projekt_name ,ende ,start ,storno ,wartung, ganztägig ) VALUES (7,'Sammelprojekt','z-url',0,null,'z-url',null, 'Urlaub',null,null,false,false,true);

insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (1,1,true,'Konzept',null,null,1);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (2,2,true,'Realisierung',null,null,1);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (3,1,true,'Weiterentwicklung',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (4,2,true,'St�rungsbehebung',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (5,3,true,'Hotline',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (6,4,true,'Besprechungen / Meetings',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (7,5,true,'SPS-Unterst�tzung',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (8,6,true,'UNIX- Server u. Datenbanken',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (9,7,true,'Rufbereitschaft',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (10,8,true,'Netzwerk',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (11,9,true,'FLS Planung',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (12,10,true,'Datenschutz',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (13,11,true,'ABVE Standardisierung',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (14,12,true,'mopc: mobile PC',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (15,13,true,'Rheinwerk',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (16,14,true,'F3LS',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (17,99,true,'Sonderaufwendungen',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (18,1,true,'Hotline / Monitoring',null,null,3);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (19,2,true,'Anpassungen Schnittstellen',null,null,3);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (20,1,true,'Rufbereitschaft',null,null,3);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (21,1,true,'Fehlzeit',null,null,4);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (22,1,true,'Fehlzeit',null,null,5);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (23,1,true,'Fehlzeit',null,null,6);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (24,1,true,'Fehlzeit',null,null,7);

insert into BUCHUNGEN (id,aktivitaet, anfang, datum, ende, minuten, pause_bis, pause_von, projekt, stunden,taetigkeiten,wartung_id,mitarbeiter) VALUES (1,1,sysdate,'2015-10-22',sysdate,120,sysdate, sysdate,1,3,'TBS-Integration',1,1);
insert into BUCHUNGEN (id,aktivitaet, anfang, datum, ende, minuten, pause_bis, pause_von, projekt, stunden,taetigkeiten,wartung_id,mitarbeiter) VALUES (2,1,sysdate,'2015-10-23',sysdate,320,sysdate, sysdate,2,3,'SAS-Support',1,1);
insert into BUCHUNGEN (id,aktivitaet, anfang, datum, ende, minuten, pause_bis, pause_von, projekt, stunden,taetigkeiten,wartung_id,mitarbeiter) VALUES (3,1,sysdate,'2015-10-24',sysdate,480,sysdate, sysdate,3,3,'DWH Besprechung',1,1);
insert into BUCHUNGEN (id,aktivitaet, anfang, datum, ende, minuten, pause_bis, pause_von, projekt, stunden,taetigkeiten,wartung_id,mitarbeiter) VALUES (4,2,sysdate,sysdate,sysdate,20,sysdate, sysdate,1,3,'Materialized View for DM_PRODUCTION',1,2);
insert into BUCHUNGEN (id,aktivitaet, anfang, datum, ende, minuten, pause_bis, pause_von, projekt, stunden,taetigkeiten,wartung_id,mitarbeiter) VALUES (5,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,1,3,'Trigger-korrketur',1,2);
insert into BUCHUNGEN (id,aktivitaet, anfang, datum, ende, minuten, pause_bis, pause_von, projekt, stunden,taetigkeiten,wartung_id,mitarbeiter) VALUES (6,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,2,3,'Rheinwerk',1,3);
insert into BUCHUNGEN (id,aktivitaet, anfang, datum, ende, minuten, pause_bis, pause_von, projekt, stunden,taetigkeiten,wartung_id,mitarbeiter) VALUES (7,18,sysdate,sysdate,sysdate,20,sysdate, sysdate,2,3,'LOGI-Schnittstelle',1,3);
insert into BUCHUNGEN (id,aktivitaet, anfang, datum, ende, minuten, pause_bis, pause_von, projekt, stunden,taetigkeiten,wartung_id,mitarbeiter) VALUES (8,19,sysdate,'2015-10-25',sysdate,500,sysdate, sysdate,2,3,'F3LS-DAO',1,1);
insert into BUCHUNGEN (id,aktivitaet, anfang, datum, ende, minuten, pause_bis, pause_von, projekt, stunden,taetigkeiten,wartung_id,mitarbeiter) VALUES (9,20,sysdate,'2015-10-26',sysdate,45,sysdate, sysdate,2,3,'Junit-test',1,1);

insert into mitarbeiter_projekte ( id , mitarbeiter , projekt ) values(1,1,1);
insert into mitarbeiter_projekte ( id , mitarbeiter , projekt ) values(2,1,2);
insert into mitarbeiter_projekte ( id , mitarbeiter , projekt ) values(3,1,3);
insert into mitarbeiter_projekte ( id , mitarbeiter , projekt ) values(4,1,4);
insert into mitarbeiter_projekte ( id , mitarbeiter , projekt ) values(5,1,5);
insert into mitarbeiter_projekte ( id , mitarbeiter , projekt ) values(6,1,6);
insert into mitarbeiter_projekte ( id , mitarbeiter , projekt ) values(7,1,7);
insert into mitarbeiter_projekte ( id , mitarbeiter , projekt ) values(8,3,2);

alter sequence seq_buchungen restart with 200;
alter sequence seq_mitarbeiter restart with 200;
alter sequence seq_projekt restart with 200;
alter sequence seq_aktivitaet restart with 200;
alter sequence seq_login restart with 200;
alter sequence seq_mitarbeiter_projekte restart with 200;

