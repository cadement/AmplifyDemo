DROP TABLE IF EXISTS User;

CREATE TABLE User (
  url       VARCHAR(50),
  password  VARCHAR(15),
  name      VARCHAR(50),
  email     VARCHAR(100),
  address   VARCHAR(100),
  lastLogin TIMESTAMP
);

DROP TABLE IF EXISTS Article;

CREATE TABLE Article (
  url       VARCHAR(50),
  title     VARCHAR(50),
  category  VARCHAR(20),
  published TIMESTAMP
);

DROP TABLE IF EXISTS ReadArticle;

CREATE TABLE ReadArticle (
  userUrl    VARCHAR(50),
  articleUrl VARCHAR(50),
  readOn     TIMESTAMP
)