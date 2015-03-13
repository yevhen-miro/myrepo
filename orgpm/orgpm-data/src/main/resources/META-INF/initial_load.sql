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

insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (1,1,true,'Konzept',null,null,1);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (2,2,true,'Realisierung',null,null,1);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (3,1,true,'Weiterentwicklung',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (4,2,true,'Störungsbehebung',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (5,3,true,'Hotline',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (6,4,true,'Besprechungen / Meetings',null,null,2);
insert into AKTIVITAETEN (id,aktivitaet_nr,status,text,bemerkung,aufwand,projekt) VALUES (7,5,true,'SPS-Unterstützung',null,null,2);
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


alter sequence seq_buchungen restart with 200;
alter sequence seq_mitarbeiter restart with 200;
alter sequence seq_projekt restart with 200;
alter sequence seq_aktivitaet restart with 200;