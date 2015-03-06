insert into MITARBEITER (id,arbeitszeit, bemerkung, gruppe, hydroid, kartenid, status, kennung, name, personalid,vorname) VALUES (1,8,'DWH-Entwickler','FAS',136862,2456,0, 'gap87','Miroshnychenko',7772, 'Yevhen');
insert into MITARBEITER (id,arbeitszeit, bemerkung, gruppe, hydroid, kartenid, status, kennung, name, personalid,vorname) VALUES (2,8,'SAS-Administrator','FCS',136989,1236,0, 'gap01','Sinaga',4656, 'David');
insert into MITARBEITER (id,arbeitszeit, bemerkung, gruppe, hydroid, kartenid, status, kennung, name, personalid,vorname) VALUES (3,8,'Java-Entwickler','FCS',132362,2254,0, 'gap07','Reibiger',7772, 'Jens');


insert into BUCHUNGEN (id,aktivität_id, anfang, datum, ende, minuten, pause_bis, pause_von, projekt_id, stunden,tätigkeiten,wartung_id,mitarbeiter) VALUES (1,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,'kta114',3,'TBS-Integration',1,1);
insert into BUCHUNGEN (id,aktivität_id, anfang, datum, ende, minuten, pause_bis, pause_von, projekt_id, stunden,tätigkeiten,wartung_id,mitarbeiter) VALUES (2,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,'kta114',3,'SAS-Support',1,1);
insert into BUCHUNGEN (id,aktivität_id, anfang, datum, ende, minuten, pause_bis, pause_von, projekt_id, stunden,tätigkeiten,wartung_id,mitarbeiter) VALUES (3,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,'kta114',3,'DWH Besprechung',1,1);
insert into BUCHUNGEN (id,aktivität_id, anfang, datum, ende, minuten, pause_bis, pause_von, projekt_id, stunden,tätigkeiten,wartung_id,mitarbeiter) VALUES (4,2,sysdate,sysdate,sysdate,20,sysdate, sysdate,'kta114',3,'Materialized View for DM_PRODUCTION',!,1);
insert into BUCHUNGEN (id,aktivität_id, anfang, datum, ende, minuten, pause_bis, pause_von, projekt_id, stunden,tätigkeiten,wartung_id,mitarbeiter) VALUES (5,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,'kta114',3,'Trigger-korrketur',1,1);
insert into BUCHUNGEN (id,aktivität_id, anfang, datum, ende, minuten, pause_bis, pause_von, projekt_id, stunden,tätigkeiten,wartung_id,mitarbeiter) VALUES (6,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,'fls000',3,'Rheinwerk',1,1);
insert into BUCHUNGEN (id,aktivität_id, anfang, datum, ende, minuten, pause_bis, pause_von, projekt_id, stunden,tätigkeiten,wartung_id,mitarbeiter) VALUES (7,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,'fls000',3,'LOGI-Schnittstelle',1,1);
insert into BUCHUNGEN (id,aktivität_id, anfang, datum, ende, minuten, pause_bis, pause_von, projekt_id, stunden,tätigkeiten,wartung_id,mitarbeiter) VALUES (8,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,'fls000',3,'F3LS-DAO',1,1);
insert into BUCHUNGEN (id,aktivität_id, anfang, datum, ende, minuten, pause_bis, pause_von, projekt_id, stunden,tätigkeiten,wartung_id,mitarbeiter) VALUES (9,1,sysdate,sysdate,sysdate,20,sysdate, sysdate,'fls000',3,'Junit-test',1,1);

alter sequence hibernate_sequence restart with 200;