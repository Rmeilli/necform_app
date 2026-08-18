# Étape 1 : Build de l'application avec Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copie du pom et téléchargement des dépendances (cache Docker optimisé)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copie du code source et compilation
COPY src ./src
RUN mvn clean package -DskipTests -B

# Étape 2 : Image légère pour exécuter l'application
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Création d'un utilisateur non-root pour la sécurité
RUN addgroup -S necform && adduser -S necform -G necform
USER necform

# Copie du JAR généré
COPY --from=build /app/target/*.jar app.jar

# Exposition du port
EXPOSE 8080

# Point d'entrée
ENTRYPOINT ["java", "-jar", "app.jar"]