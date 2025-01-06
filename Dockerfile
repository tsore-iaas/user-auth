# Étape 1 : Utiliser une image avec Maven et OpenJDK 17 pour construire le projet
FROM maven:3.8.6-openjdk-17 AS build

# Étape 2 : Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Étape 3 : Copier le fichier pom.xml et télécharger les dépendances Maven
COPY pom.xml .

# Télécharger les dépendances Maven sans copier le code source pour tirer parti du cache Docker
RUN mvn dependency:go-offline

# Étape 4 : Copier tout le code source dans le conteneur
COPY src ./src

# Étape 5 : Construire le projet Spring Boot
RUN mvn clean package -DskipTests

# Étape 6 : Créer l'image de production à partir d'une image OpenJDK 17 plus légère
FROM openjdk:17-jre-slim AS runtime

# Étape 7 : Définir le répertoire de travail pour l'application
WORKDIR /app

# Étape 8 : Copier le fichier JAR généré depuis l'étape de construction
COPY --from=build /app/target/*.jar app.jar

# Étape 9 : Exposer le port sur lequel l'application écoute (par défaut 8080 pour Spring Boot)
EXPOSE 8080

# Étape 10 : Lancer l'application Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
