-- Script d'initialisation des données de formations
-- Intelligence artificielle & automatisations
INSERT INTO formations (id, titre, categorie, description, duree_heures, prix) VALUES 
(gen_random_uuid(), 'Intelligence artificielle', 'Intelligence artificielle & Automatisations', 'Découvrez les fondamentaux de l''intelligence artificielle, ses applications et ses enjeux pour les entreprises.', 40, 5000),
(gen_random_uuid(), 'Maximiser l''utilisation de l''IA dans votre usage quotidien', 'Intelligence artificielle & Automatisations', 'Apprenez à intégrer l''IA dans vos tâches quotidiennes pour gagner en productivité.', 16, 2500),
(gen_random_uuid(), 'Canva : maîtriser les outils de création visuelle', 'Intelligence artificielle & Automatisations', 'Maîtrisez Canva pour créer des visuels professionnels avec l''aide de l''IA.', 12, 1800),
(gen_random_uuid(), 'Zapier : automatiser ses actions sans coder', 'Intelligence artificielle & Automatisations', 'Automatisez vos workflows professionnels sans connaissances en programmation.', 8, 1500),
(gen_random_uuid(), 'Notion : organiser sa gestion de projet collaborative', 'Intelligence artificielle & Automatisations', 'Organisez vos projets et équipes avec Notion et ses fonctionnalités avancées.', 16, 2200);

-- Cybersécurité
INSERT INTO formations (id, titre, categorie, description, duree_heures, prix) VALUES 
(gen_random_uuid(), 'Cybersécurité', 'Cybersécurité', 'Introduction aux principes fondamentaux de la cybersécurité et aux bonnes pratiques.', 24, 3500),
(gen_random_uuid(), 'Cybersécurité réseaux/internet', 'Cybersécurité', 'Sécurisez vos réseaux et connexions internet contre les menaces courantes.', 32, 4500),
(gen_random_uuid(), 'Sécurité des applications', 'Cybersécurité', 'Développez des applications sécurisées en intégrant les principes de sécurité dès la conception.', 40, 5500),
(gen_random_uuid(), 'Sécurité des infrastructures', 'Cybersécurité', 'Protégez vos infrastructures IT et cloud contre les attaques et intrusions.', 48, 6500),
(gen_random_uuid(), 'Administration Secure Transport', 'Cybersécurité', 'Gérez les transports sécurisés et les protocoles de chiffrement avancés.', 32, 4800);

-- DevOps & Automatisation
INSERT INTO formations (id, titre, categorie, description, duree_heures, prix) VALUES 
(gen_random_uuid(), 'DevOps', 'DevOps & Automatisation', 'Maîtrisez les pratiques DevOps pour améliorer la collaboration et l''efficacité.', 40, 6000),
(gen_random_uuid(), 'Linux : les fondamentaux', 'DevOps & Automatisation', 'Apprenez les bases du système d''exploitation Linux et ses commandes essentielles.', 24, 3000),
(gen_random_uuid(), 'Linux administration', 'DevOps & Automatisation', 'Devenez administrateur Linux système et réseau.', 48, 5500),
(gen_random_uuid(), 'Git', 'DevOps & Automatisation', 'Maîtrisez le contrôle de version avec Git pour vos projets de développement.', 16, 2500),
(gen_random_uuid(), 'GitHub', 'DevOps & Automatisation', 'Exploitez GitHub pour la collaboration et l''intégration continue.', 12, 2000),
(gen_random_uuid(), 'Docker', 'DevOps & Automatisation', 'Conteneurisez vos applications avec Docker pour un déploiement simplifié.', 24, 3500),
(gen_random_uuid(), 'Kubernetes', 'DevOps & Automatisation', 'Orchestrez vos conteneurs avec Kubernetes pour des applications scalables.', 40, 6000),
(gen_random_uuid(), 'OpenShift', 'DevOps & Automatisation', 'Déployez et gérez des applications sur la plateforme OpenShift.', 32, 5000),
(gen_random_uuid(), 'Ansible', 'DevOps & Automatisation', 'Automatisez l''configuration et le déploiement avec Ansible.', 24, 3800),
(gen_random_uuid(), 'Jenkins', 'DevOps & Automatisation', 'Mettez en place des pipelines d''intégration continue avec Jenkins.', 20, 3200),
(gen_random_uuid(), 'XL Deploy', 'DevOps & Automatisation', 'Automatisez le déploiement d''applications avec XL Deploy.', 16, 2800),
(gen_random_uuid(), 'XL Release', 'DevOps & Automatisation', 'Gérez vos releases et déploiements avec XL Release.', 16, 2800),
(gen_random_uuid(), 'XL Deploy / XL Release', 'DevOps & Automatisation', 'Maîtrisez la suite XL Deploy et XL Release pour le déploiement automatisé.', 32, 4800),
(gen_random_uuid(), 'API Management', 'DevOps & Automatisation', 'Gérez et sécurisez vos APIs avec les solutions de gestion d''API.', 24, 4000),
(gen_random_uuid(), 'Jboss', 'DevOps & Automatisation', 'Déployez et gérez des applications Java sur JBoss EAP.', 32, 4500),
(gen_random_uuid(), 'Tomcat', 'DevOps & Automatisation', 'Administration et configuration du serveur d''applications Tomcat.', 24, 3500);

-- Cloud Computing
INSERT INTO formations (id, titre, categorie, description, duree_heures, prix) VALUES 
(gen_random_uuid(), 'Introduction au Cloud computing', 'Cloud Computing', 'Comprenez les concepts fondamentaux du cloud computing et ses modèles de service.', 16, 2500),
(gen_random_uuid(), 'Microsoft Azure', 'Cloud Computing', 'Déployez et gérez des applications sur la plateforme Microsoft Azure.', 40, 6000),
(gen_random_uuid(), 'AWS (Amazon Web Services)', 'Cloud Computing', 'Maîtrisez les services AWS pour construire des applications cloud.', 48, 7000),
(gen_random_uuid(), 'Google Cloud', 'Cloud Computing', 'Développez et déployez des applications sur Google Cloud Platform.', 40, 6000);

-- Data, reporting et BI
INSERT INTO formations (id, titre, categorie, description, duree_heures, prix) VALUES 
(gen_random_uuid(), 'Data analyst', 'Data, reporting et BI', 'Devenez analyste de données et maîtrisez les techniques d''analyse.', 48, 6500),
(gen_random_uuid(), 'Data scientist', 'Data, reporting et BI', 'Maîtrisez le machine learning et l''analyse de données avancée.', 64, 8500),
(gen_random_uuid(), 'Power BI : créer vos premiers tableaux interactifs', 'Data, reporting et BI', 'Créez des tableaux de bord interactifs avec Power BI.', 16, 2500),
(gen_random_uuid(), 'Power BI : exploiter et visualiser les données', 'Data, reporting et BI', 'Exploitez les données et créez des visualisations percutantes.', 20, 3000),
(gen_random_uuid(), 'Power BI : maîtriser l''analyse des données', 'Data, reporting et BI', 'Approfondissez vos connaissances en analyse de données avec Power BI.', 24, 3500),
(gen_random_uuid(), 'Oracle : initiation', 'Data, reporting et BI', 'Initiez-vous à la base de données Oracle et au langage SQL.', 24, 3500),
(gen_random_uuid(), 'SQL Server : initiation', 'Data, reporting et BI', 'Découvrez SQL Server et ses fonctionnalités de base de données.', 24, 3500);

-- Métiers du numérique & développement
INSERT INTO formations (id, titre, categorie, description, duree_heures, prix) VALUES 
(gen_random_uuid(), 'Développeur d''application full stack', 'Métiers du numérique & développement', 'Devenez développeur full stack avec les technologies modernes.', 120, 15000),
(gen_random_uuid(), 'Python : initiation', 'Métiers du numérique & développement', 'Apprenez les bases de la programmation Python.', 24, 3000),
(gen_random_uuid(), 'Python : avancé', 'Métiers du numérique & développement', 'Maîtrisez les concepts avancés de Python et ses frameworks.', 40, 5000),
(gen_random_uuid(), 'Méthode agile : les fondamentaux', 'Métiers du numérique & développement', 'Comprenez et appliquez les méthodes agiles dans vos projets.', 16, 2500),
(gen_random_uuid(), 'Community manager', 'Métiers du numérique & développement', 'Devenez expert en gestion de communauté et réseaux sociaux.', 32, 4500),
(gen_random_uuid(), 'UX design et ergonomie des sites Web', 'Métiers du numérique & développement', 'Concevez des interfaces utilisateur optimales et ergonomiques.', 40, 5500),
(gen_random_uuid(), 'Lancer son activité en freelance', 'Métiers du numérique & développement', 'Apprenez à lancer et gérer votre activité de freelance.', 16, 2500);

-- Création de sites web
INSERT INTO formations (id, titre, categorie, description, duree_heures, prix) VALUES 
(gen_random_uuid(), 'Créer un site avec WordPress', 'Création de sites web', 'Créez et gérez votre site web avec WordPress.', 24, 3500),
(gen_random_uuid(), 'Webflow : construire un site internet en no code', 'Création de sites web', 'Construisez des sites web professionnels sans coder avec Webflow.', 20, 3200),
(gen_random_uuid(), 'Bubble : prise en main', 'Création de sites web', 'Développez des applications web sans code avec Bubble.', 24, 3800),
(gen_random_uuid(), 'SEO : optimiser le référencement de votre site', 'Création de sites web', 'Optimisez votre site pour les moteurs de recherche et améliorez votre visibilité.', 16, 2800);

-- Bureautique & outils numériques
INSERT INTO formations (id, titre, categorie, description, duree_heures, prix) VALUES 
(gen_random_uuid(), 'Word 2021 : initiation', 'Bureautique & outils numériques', 'Apprenez les bases du traitement de texte avec Word 2021.', 12, 1500),
(gen_random_uuid(), 'Word 2021 : intermédiaire', 'Bureautique & outils numériques', 'Approfondissez vos connaissances de Word 2021.', 12, 1500),
(gen_random_uuid(), 'Word 2021 : expertise', 'Bureautique & outils numériques', 'Maîtrisez les fonctionnalités avancées de Word 2021.', 16, 2000),
(gen_random_uuid(), 'Excel 2021 : initiation', 'Bureautique & outils numériques', 'Découvrez les fondamentaux d''Excel 2021.', 16, 2000),
(gen_random_uuid(), 'Excel 2021 : intermédiaire', 'Bureautique & outils numériques', 'Approfondissez vos compétences en Excel 2021.', 20, 2500),
(gen_random_uuid(), 'Excel 2021 : perfectionnement', 'Bureautique & outils numériques', 'Devenez expert en Excel 2021 avec les fonctions avancées.', 24, 3000),
(gen_random_uuid(), 'PowerPoint : initiation', 'Bureautique & outils numériques', 'Créez des présentations professionnelles avec PowerPoint.', 12, 1500),
(gen_random_uuid(), 'Outlook', 'Bureautique & outils numériques', 'Gérez votre messagerie et calendrier avec Outlook.', 8, 1200);

-- Logiciels métiers & logistique
INSERT INTO formations (id, titre, categorie, description, duree_heures, prix) VALUES 
(gen_random_uuid(), 'SAP : débutant', 'Logiciels métiers & logistique', 'Initiez-vous à l''ERP SAP et ses modules principaux.', 40, 6000),
(gen_random_uuid(), 'Silae', 'Logiciels métiers & logistique', 'Maîtrisez le logiciel Silae pour la gestion de la paie et des RH.', 32, 4500);
