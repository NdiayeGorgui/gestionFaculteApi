# Services de formation de la faculté informatique
Le but de cette application web est de permettre au responsable administratif de la faculté de gérer les services
des enseignants. Il devra être possible de les enregistrer, les modifier, d’en tirer des récapitulatifs, des
synthèses. Chaque enseignant donne un ou plusieurs cours et est caractérisé par (matricule nom, prénom, statut,
adresse, téléphone, courriel).
Votre projet ne doit pas tenter de gérer les emplois du temps, ni la répartition des cours par semaine.
La faculté contient plusieurs formations, et chaque groupe suit une et une seule formation.
Les heures de cours ne sont pas toutes comptées de la même manière (elles ne sont pas payées au même prix,
suivant que ce sont des Travaux dirigés, des Travaux Pratique ou des Cours magistraux qui est caractérisé par
(son code et nom de cours) ).
Les affectations des enseignants à un cours sont définies au niveau des groupes. Et, il est nécessaire de gérer
l’affectation individuelle des groupes (besoin de savoir que tel enseignant est en charge du groupe n1, tel autre
du groupe n2 et le nombre de groupes ).
Les enseignants ne doivent pas avoir tous le même service : leur statut donne une base, mais elle peut être
modifiée : temps partiel, temps plein, contractuelle ou contrat particulier (vacataire).
# Fonctionnement
Les utilisateurs n’ont pas les mêmes droits suivant leur identité
Les pages ne seront pas en libre accès : suivant les droits qui lui auront été conférés, chaque utilisateur ne
pourra avoir accès qu’à certaines informations et certains traitements. Il faudra donc gérer un système de mots
de passe pour contrôler l’accès au site.
1. L’ utilisateur enseignant n’a que le droit de consulter le contenu de la base (et seulement par des requêtes
prédéfinies).
2. Le responsable administratif a le droit de modifier le contenu des tables (mais certaines données sensibles
peuvent lui être interdites : il n’est qu’un utilisateur privilégié, avec des droits plus importants que l’utilisateur
enseignant, mais limités quand même). Les modifications qui lui sont autorisées devront passer, comme pour
l’utilisateur de base, par des requêtes prédéfinies.
3. L’administrateur de la base a le droit de définir les niveaux de droits des utilisateurs, modifier la structure de
la base (il a tous les droits).
# Fonctionnalités requises pour la consultation
Consultations accessibles à tous les utilisateurs identifiés.
1. Liste des cours et des enseignants d’une formation.
2. Liste des cours et des enseignants d’une année.
3. Liste des cours et des enseignants de la faculté.
4. Liste des cours et des enseignants d’une matière.
5. Liste des cours d’un enseignant.
6. Pour chaque enseignant, calculer son service, et afficher le nombre d’heures supplémentaires, ou le nombre
d’heures régulier et/ou par formation et/ou par statut.
7. Il faut pouvoir distinguer entre les enseignants permanents et les autres dans les différentes statistiques : il
faudra avoir trois versions de chaque calcul : tous les enseignants, les permanents, et les vacataires.
# Fonctionnalités requises pour la mise à jour
Pour les utilisateurs autorisés seulement : responsable administratif et administrateur de la base.
1. Créer/modifier/supprimer tout objet intervenant dans la gestion des affectations (faculté, formations, filière,
cours, enseignant).
2. Affecter un enseignement à un enseignant. Supprimer cette affectation