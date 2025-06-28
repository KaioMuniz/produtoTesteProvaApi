# Etapa 1: Build
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copia os arquivos do projeto
COPY pom.xml .
COPY src ./src
COPY mvnw .
COPY .mvn .mvn
COPY mvnw.cmd .

# Compila o projeto e cria o .jar
# Para Maven:
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Renomeia o JAR gerado para um nome padrão
RUN cp target/*.jar app.jar

# Etapa 2: Imagem final
FROM eclipse-temurin:21.0.1_12-jre-alpine

WORKDIR /app

# Copia apenas o .jar da etapa anterior
COPY --from=build /app/app.jar .

# Porta padrão do Spring Boot
EXPOSE 8086

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]