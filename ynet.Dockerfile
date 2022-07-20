# build stage

FROM openjdk:17.0.2-oraclelinux8  as build

COPY /Ynet-News /Ynet-News

RUN cd /Ynet-News \
    && chmod +x gradlew \
    && ./gradlew build


# run stage

FROM openjdk:17.0.2-oraclelinux8 

COPY --from=build Ynet-News/build/libs/Ynet-News-0.0.1-SNAPSHOT.jar .

EXPOSE 5000

CMD ["java","-jar", "Ynet-News-0.0.1-SNAPSHOT.jar"]