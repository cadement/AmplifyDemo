INSERT INTO User (url, password, name, email, address, admin, lastUpdated, lastLogin) VALUES ('user-1', 'pw1', 'User 1', 'user1@sharecare.com', 'Atlanta, GA', false, NULL, NULL);
INSERT INTO User (url, password, name, email, address, admin, lastUpdated, lastLogin) VALUES ('user-2', 'pw2', 'User 2', 'user2@sharecare.com', 'Atlanta, GA', false, NULL, NULL);

INSERT INTO Article (url, title, category, published) VALUES ('article-1', 'Title 1', 'Category 1', '2001-01-01 00:00:00');
INSERT INTO Article (url, title, category, published) VALUES ('article-2', 'Title 2', 'Category 1', '2002-01-02 00:00:00');

INSERT INTO ReadArticle (userUrl, articleUrl, readOn) VALUES ('user-1', 'article-1', '2014-01-02 00:00:00');
INSERT INTO ReadArticle (userUrl, articleUrl, readOn) VALUES ('user-1', 'article-2', '2014-01-01 00:00:00');
