INSERT INTO User (url, password, name, email, address, admin, lastUpdated)
VALUES ('demo-admin', 'admin_pass', 'Demo App Admin', 'admin@sharecare.com', 'Atlanta, GA', TRUE, CURRENT_TIMESTAMP);

INSERT INTO User (url, password, name, email, address, lastUpdated)
VALUES ('demo-user', 'user_pass', 'Demo App User', 'user@sharecare.com', 'Atlanta, GA', CURRENT_TIMESTAMP);

INSERT INTO Article (url, title, category, published)
VALUES ('aging-eye', 'The Aging Eye: See into Your Future', 'Health', CURRENT_TIMESTAMP);

INSERT INTO Article (url, title, category, published)
VALUES ('chill-out', 'Chill Out', 'Health', CURRENT_TIMESTAMP);

INSERT INTO Article (url, title, category, published)
VALUES ('eye-facts', '6 Surprising Eye Health Myths', 'Health', CURRENT_TIMESTAMP);

INSERT INTO Article (url, title, category, published)
VALUES ('syndrome-x', 'Metabolic Syndrome: Could You Have It?', 'Health', CURRENT_TIMESTAMP);
