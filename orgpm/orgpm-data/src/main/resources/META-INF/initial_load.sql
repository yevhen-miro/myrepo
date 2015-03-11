insert into MITARBEITER (id,arbeitszeit, bemerkung, gruppe, hydroid, kartenid, status, kennung, name, personalid,vorname) VALUES (1,8,'DWH-Entwickler','FAS',136862,2456,0, 'gap87','Miroshnychenko',7772, 'Yevhen');
insert into MITARBEITER (id,arbeitszeit, bemerkung, gruppe, hydroid, kartenid, status, kennung, name, personalid,vorname) VALUES (2,8,'SAS-Administrator','FCS',136989,1236,0, 'gap01','Sinaga',4656, 'David');
insert into MITARBEITER (id,arbeitszeit, bemerkung, gruppe, hydroid, kartenid, status, kennung, name, personalid,vorname) VALUES (3,8,'Java-Entwickler','FCS',132362,2254,0, 'gap07','Reibiger',7772, 'Jens');


insert into BUCHUNGEN (id,aktivitaet_id, anfang, datum, ende, minuten, pause_bis, pause_von, projekt_id, stunden,taetigkeiten,wartung_id,mitarbeiter) VALUES (1,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,'kta114',3,'TBS-Integration',1,1);
insert into BUCHUNGEN (id,aktivitaet_id, anfang, datum, ende, minuten, pause_bis, pause_von, projekt_id, stunden,taetigkeiten,wartung_id,mitarbeiter) VALUES (2,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,'kta114',3,'SAS-Support',1,1);
insert into BUCHUNGEN (id,aktivitaet_id, anfang, datum, ende, minuten, pause_bis, pause_von, projekt_id, stunden,taetigkeiten,wartung_id,mitarbeiter) VALUES (3,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,'kta114',3,'DWH Besprechung',1,1);
insert into BUCHUNGEN (id,aktivitaet_id, anfang, datum, ende, minuten, pause_bis, pause_von, projekt_id, stunden,taetigkeiten,wartung_id,mitarbeiter) VALUES (4,2,sysdate,sysdate,sysdate,20,sysdate, sysdate,'kta114',3,'Materialized View for DM_PRODUCTION',!,1);
insert into BUCHUNGEN (id,aktivitaet_id, anfang, datum, ende, minuten, pause_bis, pause_von, projekt_id, stunden,taetigkeiten,wartung_id,mitarbeiter) VALUES (5,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,'kta114',3,'Trigger-korrketur',1,1);
insert into BUCHUNGEN (id,aktivitaet_id, anfang, datum, ende, minuten, pause_bis, pause_von, projekt_id, stunden,taetigkeiten,wartung_id,mitarbeiter) VALUES (6,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,'fls000',3,'Rheinwerk',1,1);
insert into BUCHUNGEN (id,aktivitaet_id, anfang, datum, ende, minuten, pause_bis, pause_von, projekt_id, stunden,taetigkeiten,wartung_id,mitarbeiter) VALUES (7,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,'fls000',3,'LOGI-Schnittstelle',1,1);
insert into BUCHUNGEN (id,aktivitaet_id, anfang, datum, ende, minuten, pause_bis, pause_von, projekt_id, stunden,taetigkeiten,wartung_id,mitarbeiter) VALUES (8,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,'fls000',3,'F3LS-DAO',1,1);
insert into BUCHUNGEN (id,aktivitaet_id, anfang, datum, ende, minuten, pause_bis, pause_von, projekt_id, stunden,taetigkeiten,wartung_id,mitarbeiter) VALUES (9,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,'fls000',3,'Junit-test',1,1);


insert into PROJEKTE  (id,begriff ,bemerkung,hauptprojekt ,aufwand ,projektgruppe ,projekt_id ,projektleiter ,projekt_name ,status ,ende ,start ,storno ,wartung ) VALUES (1,'kta114','Sammelprojekt','kta114',780,'FLS','kta114','Schmitz', 'Projektbezeichnung Erneuerung Produktionsbuch / Datawarehouse',1,sysdate,sysdate,false,false);
insert into PROJEKTE (id,begriff ,bemerkung,hauptprojekt ,aufwand ,projektgruppe ,projekt_id ,projektleiter ,projekt_name ,status ,ende ,start ,storno ,wartung ) VALUES (2,'fls000','Sammelprojekt','fls000',1200,'FLS','fls000','Engemann', 'Hotline-Service / Maintenance FLS',1,sysdate,sysdate,false,false);
insert into PROJEKTE (id,begriff ,bemerkung,hauptprojekt ,aufwand ,projektgruppe ,projekt_id ,projektleiter ,projekt_name ,status ,ende ,start ,storno ,wartung ) VALUES (3,'SAP000','Sammelprojekt','SAP000',440,'FLS','sap000','Schmitz', 'Hotline-Service SAP Interface',1,sysdate,sysdate,false,false);

alter sequence seq_buchungen restart with 200;
alter sequence seq_mitarbeiter restart with 200;
alter sequence seq_projekt restart with 200;