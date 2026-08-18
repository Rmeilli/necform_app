# Étape 1 : Build de l'application Angular
FROM node:20-alpine AS build
WORKDIR /app

# Copie des fichiers de dépendances
COPY package*.json .
RUN npm ci

# Copie du code source et build
COPY . .
RUN npm run build

# Étape 2 : Serveur web léger pour servir l'application
FROM nginx:alpine

# Copie de la configuration Nginx personnalisée
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copie des fichiers buildés
COPY --from=build /app/dist/necform-front/browser /usr/share/nginx/html

# Exposition du port
EXPOSE 80

# Lancement de Nginx
CMD ["nginx", "-g", "daemon off;"]