From openjdk:8
copy build/libs/DBManager-all.jar DBManager-all.jar
CMD ["java","-jar","DBManager-all.jar"]