<!-- / This basicall show case the use case Stateful operators in flink and Timers

 -->
#######How to run######

mvn clean install

docker compose up

python socket_transaction.py

C:\Users\akash\dockertest\scala-flink>docker exec -it scala-flink_fraud_detection-jobmanager-1 ./bin/flink run /flink-target/flink-FraudDetection-1.0-SNAPSHOT.jar

###############################