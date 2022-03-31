CREATE TABLE CATEGORIES
    (
        no_categorie INTEGER     IDENTITY(1, 1) NOT NULL,
        libelle      NVARCHAR(30) NOT NULL
    )

ALTER TABLE CATEGORIES
ADD
    CONSTRAINT categorie_pk
    PRIMARY KEY (no_categorie)

CREATE TABLE ENCHERES
    (
        no_utilisateur  INTEGER  NOT NULL,
        no_article      INTEGER  NOT NULL,
        date_enchere    DATETIME NOT NULL,
        montant_enchere INTEGER  NOT NULL
    )

ALTER TABLE ENCHERES
ADD
    CONSTRAINT enchere_pk
    PRIMARY KEY (no_utilisateur, no_article)

CREATE TABLE RETRAITS
    (
        no_article  INTEGER     NOT NULL,
        rue         NVARCHAR(30) NOT NULL,
        code_postal NVARCHAR(15) NOT NULL,
        ville       NVARCHAR(30) NOT NULL
    )

ALTER TABLE RETRAITS
ADD
    CONSTRAINT retrait_pk
    PRIMARY KEY (no_article)

CREATE TABLE UTILISATEURS
    (
        no_utilisateur INTEGER     IDENTITY(1, 1) NOT NULL,
        pseudo         NVARCHAR(30) NOT NULL,
        nom            NVARCHAR(30) NOT NULL,
        prenom         NVARCHAR(30) NOT NULL,
        email          NVARCHAR(50) NOT NULL,
        telephone      NVARCHAR(15),
        rue            NVARCHAR(100) NOT NULL,
        code_postal    NVARCHAR(10) NOT NULL,
        ville          NVARCHAR(30) NOT NULL,
        mot_de_passe   NVARCHAR(30) NOT NULL,
        credit         INTEGER     NOT NULL,
        administrateur BIT         NOT NULL
    )

ALTER TABLE UTILISATEURS
ADD
    CONSTRAINT utilisateur_pk
    PRIMARY KEY (no_utilisateur)


CREATE TABLE ARTICLES_VENDUS
    (
        no_article          INTEGER      IDENTITY(1, 1) NOT NULL,
        nom_article         NVARCHAR(30)  NOT NULL,
        description         NVARCHAR(300) NOT NULL,
        date_debut_encheres DATE         NOT NULL,
        date_fin_encheres   DATE         NOT NULL,
        prix_initial        INTEGER      NOT NULL,
        prix_vente          INTEGER,
        no_vendeur          INTEGER      NOT NULL,
        no_acheteur         INTEGER,
        no_categorie        INTEGER      NOT NULL,
        etat_vente          TINYINT      NOT NULL
    )

ALTER TABLE ARTICLES_VENDUS
ADD
    CONSTRAINT articles_vendus_pk
    PRIMARY KEY (no_article)

ALTER TABLE ARTICLES_VENDUS
ADD
    CONSTRAINT encheres_vendeur_fk
    FOREIGN KEY (no_vendeur)
    REFERENCES UTILISATEURS (no_utilisateur) ON DELETE no action ON UPDATE no action

ALTER TABLE ARTICLES_VENDUS
ADD
    CONSTRAINT encheres_acheteur_fk
    FOREIGN KEY (no_acheteur)
    REFERENCES UTILISATEURS (no_utilisateur) ON DELETE no action ON UPDATE no action

ALTER TABLE ENCHERES
ADD
    CONSTRAINT encheres_articles_vendus_fk
    FOREIGN KEY (no_article)
    REFERENCES ARTICLES_VENDUS (no_article) ON DELETE no action ON UPDATE no action
    

ALTER TABLE ENCHERES
ADD
    CONSTRAINT encheres_utilisateur_fk
    FOREIGN KEY (no_utilisateur)
    REFERENCES UTILISATEURS (no_utilisateur) ON DELETE no action ON UPDATE no action

ALTER TABLE RETRAITS
ADD
    CONSTRAINT retraits_articles_vendus_fk
    FOREIGN KEY (no_article)
    REFERENCES ARTICLES_VENDUS (no_article) ON DELETE no action ON UPDATE no action

ALTER TABLE ARTICLES_VENDUS
ADD
    CONSTRAINT articles_vendus_categories_fk
    FOREIGN KEY (no_categorie)
    REFERENCES categories (no_categorie) ON DELETE no action ON UPDATE no action
