INSERT INTO CATEGORIES (libelle) VALUES ('Sports & Loisirs');
INSERT INTO CATEGORIES (libelle) VALUES ('Informatique');
INSERT INTO CATEGORIES (libelle) VALUES ('Ameublement');
INSERT INTO CATEGORIES (libelle) VALUES ('Vêtements');

INSERT INTO [UTILISATEURS] (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)
VALUES
  ('accumsan','Yael','Ramirez','egestas.ligula@protonmail.org','0102030405','523-892 Non Street','21235','Lanester','GXX33OUT7OY','0','0'),
  ('a,','Jeremy','Long','a.sollicitudin@icloud.net','0102030405','591-2455 Mauris Rd.','78242','Illkirch-Graffenstaden','OLM62VQL0AS','0','0'),
  ('Vivamus','Charde','Arnold','tellus.id@protonmail.ca','0102030405','Ap #882-8086 Mollis Rd.','38057','Liévin','IEO54HVS4QY','0','0'),
  ('penatibus','Ethan','Ferguson','quis.pede@hotmail.couk','0102030405','P.O. Box 761, 8267 Pellentesque Street','95553','Auxerre','ZAN06YLW7PJ','0','0'),
  ('sem,','Germaine','Elliott','faucibus.orci@yahoo.net','0102030405','P.O. Box 235, 528 Tempor St.','15636','Saint-Louis','KJB18VPQ2PS','0','0'),
  ('sapien','Madaline','Horton','ut.tincidunt@aol.edu','0102030405','P.O. Box 377, 9008 Augue Rd.','73782','Saintes','VXH31DUN5DZ','0','0'),
  ('quam','Mohammad','Clay','scelerisque.scelerisque@protonmail.net','0102030405','274-4175 In Ave','37579','Narbonne','LLG83PJJ7OF','0','0'),
  ('at','Penelope','Mcguire','proin.ultrices@hotmail.net','0102030405','516-272 Felis Av.','51919','Le Mans','MYC70TOT7VK','0','0'),
  ('tincidunt','Hanae','Michael','mus.proin.vel@icloud.edu','0102030405','Ap #468-1595 Posuere, Avenue','72704','Saint-Louis','XYW23EWM1YU','0','0')


  INSERT INTO [ARTICLES_VENDUS] (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_vendeur,no_acheteur,no_categorie,etat_vente)
VALUES
  ('risus.','luctus et ultrices posuere cubilia Curae Donec tincidunt. Donec vitae erat vel pede blandit congue. In scelerisque scelerisque dui. Suspendisse','2022-04-11','2022-08-12','83','26','2','3','1','1'),
  ('Nulla','libero. Proin sed turpis nec mauris blandit mattis. Cras eget nisi dictum augue malesuada malesuada. Integer id magna et ipsum','2022-04-11','2022-08-12','58','96','2','6','2','1'),
  ('Sed','lorem, auctor quis, tristique ac, eleifend vitae, erat. Vivamus nisi. Mauris nulla. Integer urna. Vivamus molestie dapibus ligula. Aliquam erat','2022-04-11','2022-08-12','36','49','3','4','3','2'),
  ('massa.','elit, pretium et, rutrum non, hendrerit id, ante. Nunc mauris sapien, cursus in, hendrerit consectetuer, cursus et, magna. Praesent interdum','2022-04-11','2022-08-12','84','71','4','5','4','0'),
  ('nec,','amet, faucibus ut, nulla. Cras eu tellus eu augue porttitor interdum. Sed auctor odio a purus. Duis elementum, dui quis','2022-04-11','2022-08-12','75','36','5','3','2','1')


INSERT INTO ENCHERES VALUES ('4','5','2022-04-06','25');
INSERT INTO ENCHERES VALUES ('2','4','2022-04-12','18');
INSERT INTO ENCHERES VALUES ('6','3','2022-04-18','12');
INSERT INTO ENCHERES VALUES ('3','2','2022-04-20','30');

INSERT INTO RETRAITS VALUES ('3','rue du pet', '62150', 'Walaby');
INSERT INTO RETRAITS VALUES ('5','rue balsamique', '78410', 'Nulpart');
INSERT INTO RETRAITS VALUES ('2','rue de merde encore', '89740', 'Farage');
INSERT INTO RETRAITS VALUES ('4','rue paix à ton ame', '92000', 'Muerte');

--SELECT * FROM ARTICLES_VENDUS

--SELECT * FROM ENCHERES

--SELECT * FROM UTILISATEURS

--SELECT * FROM RETRAITS