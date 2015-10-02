insert into LOGIN(id,password) VALUES(1,'rdsPXngmyFfXN20b2bwzwMVEeQourJYUSoryKxKYyUA=');
insert into LOGIN(id,password) VALUES(2,'rdsPXngmyFfXN20b2bwzwMVEeQourJYUSoryKxKYyUA=');
insert into LOGIN(id,password) VALUES(3,'rdsPXngmyFfXN20b2bwzwMVEeQourJYUSoryKxKYyUA=');

insert into ROLLEN (id, name) VALUES (80,  'ADMIN');
insert into ROLLEN (id, name) VALUES (81,  'USER');
 

insert into MITARBEITER (id,arbeitszeit, bemerkung, gruppe, hydroid, kartenid, status, kennung, nachname, personalid,vorname, rolle ) VALUES (1,8,'DWH-Entwickler','FAS','A136862',2456,0, 'gap87','Miroshnychenko',7772, 'Yevhen','ADMIN');
insert into MITARBEITER (id,arbeitszeit, bemerkung, gruppe, hydroid, kartenid, status, kennung, nachname, personalid,vorname,  rolle) VALUES (2,8,'SAS-Administrator','FCS','A136989',1236,0, 'gap01','Sinaga',4656, 'David','ADMIN');
insert into MITARBEITER (id,arbeitszeit, bemerkung, gruppe, hydroid, kartenid, status, kennung, nachname, personalid,vorname,  rolle) VALUES (3,8,'Java-Entwickler','FCS','A132362',2254,0, 'gap07','Reibiger',7772, 'Jens','USER');

insert into PROJEKTE  (id,bemerkung,hauptprojekt ,aufwand ,projektgruppe ,projekt_id ,projektleiter ,projekt_name ,ende ,start ,storno ,wartung ) VALUES (2,'Sammelprojekt','fls000',1200,'FLS','fls000','Engemann', 'Hotline-Service / Maintenance FLS',sysdate,sysdate,false,false);
insert into PROJEKTE  (id,bemerkung,hauptprojekt ,aufwand ,projektgruppe ,projekt_id ,projektleiter ,projekt_name ,ende ,start ,storno ,wartung ) VALUES (1,'Sammelprojekt','kta114',780,'FLS','kta114','Schmitz', 'Erneuerung Produktionsbuch / Datawarehouse',sysdate,sysdate,false,false);
insert into PROJEKTE  (id,bemerkung,hauptprojekt ,aufwand ,projektgruppe ,projekt_id ,projektleiter ,projekt_name ,ende ,start ,storno ,wartung ) VALUES (3,'Sammelprojekt','SAP000',440,'FLS','sap000','Schmitz', 'Hotline-Service SAP Interface',sysdate,sysdate,false,false);




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
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (20,7,true,'Rufbereitschaft',null,null,3);

insert into BUCHUNGEN (id,aktivitaet, anfang, datum, ende, minuten, pause_bis, pause_von, projekt, stunden,taetigkeiten,wartung_id,hydroid) VALUES (1,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,1,3,'TBS-Integration',1,'A136862');
insert into BUCHUNGEN (id,aktivitaet, anfang, datum, ende, minuten, pause_bis, pause_von, projekt, stunden,taetigkeiten,wartung_id,hydroid) VALUES (2,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,1,3,'SAS-Support',1,'A136862');
insert into BUCHUNGEN (id,aktivitaet, anfang, datum, ende, minuten, pause_bis, pause_von, projekt, stunden,taetigkeiten,wartung_id,hydroid) VALUES (3,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,1,3,'DWH Besprechung',1,'A136862');
insert into BUCHUNGEN (id,aktivitaet, anfang, datum, ende, minuten, pause_bis, pause_von, projekt, stunden,taetigkeiten,wartung_id,hydroid) VALUES (4,2,sysdate,sysdate,sysdate,20,sysdate, sysdate,1,3,'Materialized View for DM_PRODUCTION',1,'A566561');
insert into BUCHUNGEN (id,aktivitaet, anfang, datum, ende, minuten, pause_bis, pause_von, projekt, stunden,taetigkeiten,wartung_id,hydroid) VALUES (5,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,1,3,'Trigger-korrketur',1,'A566561');
insert into BUCHUNGEN (id,aktivitaet, anfang, datum, ende, minuten, pause_bis, pause_von, projekt, stunden,taetigkeiten,wartung_id,hydroid) VALUES (6,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,2,3,'Rheinwerk',1,'A566561');
insert into BUCHUNGEN (id,aktivitaet, anfang, datum, ende, minuten, pause_bis, pause_von, projekt, stunden,taetigkeiten,wartung_id,hydroid) VALUES (7,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,2,3,'LOGI-Schnittstelle',1,'A136862');
insert into BUCHUNGEN (id,aktivitaet, anfang, datum, ende, minuten, pause_bis, pause_von, projekt, stunden,taetigkeiten,wartung_id,hydroid) VALUES (8,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,2,3,'F3LS-DAO',1,'A136862');
insert into BUCHUNGEN (id,aktivitaet, anfang, datum, ende, minuten, pause_bis, pause_von, projekt, stunden,taetigkeiten,wartung_id,hydroid) VALUES (9,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,2,3,'Junit-test',1,'A136862');


alter sequence seq_buchungen restart with 200;
alter sequence seq_mitarbeiter restart with 200;
alter sequence seq_projekt restart with 200;
alter sequence seq_aktivitaet restart with 200;
alter sequence seq_mitarbeiter_rollen restart with 200;
alter sequence seq_login restart with 200;


insert into MITARBEITER_ROLLEN ( id,  mitarbeiter, rolle) VALUES( 90,  1, 80);
insert into MITARBEITER_ROLLEN ( id,  mitarbeiter, rolle) VALUES( 91,  2, 81);
insert into MITARBEITER_ROLLEN ( id,  mitarbeiter, rolle) VALUES( 92,  3, 80);

insert into MITARBEITER_PROJEKTE (id, mitarbeiter, projekt) VALUES (30, 1, 1);
insert into MITARBEITER_PROJEKTE (id, mitarbeiter, projekt) VALUES (31, 1, 2);
insert into MITARBEITER_PROJEKTE (id, mitarbeiter, projekt) VALUES (32, 1, 3);