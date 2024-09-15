################
Start Containers
################
C:\Users\akash\dockertest\Kafka>docker-compose -f kafka.yaml up -d
time="2024-09-15T15:10:27+05:30" level=warning msg="C:\\Users\\akash\\dockertest\\Kafka\\kafka.yaml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion"
[+] Running 3/3
 ✔ Network kafka_default        Created                                                                            0.1s
 ✔ Container kafka-zookeeper-1  Started                                                                            1.2s
 ✔ Container kafka-kafka-1      Started                                                                            1.5s


################
Stop and remove containers
################
C:\Users\akash\dockertest\Kafka>docker ps -a
CONTAINER ID   IMAGE                             COMMAND                  CREATED       STATUS                     PORTS                                         NAMES
f5589b2a3547   confluentinc/cp-kafka:7.4.4       "/etc/confluent/dock…"   2 hours ago   Up 2 hours                 9092/tcp, 0.0.0.0:29092->29092/tcp            kafka-kafka-1
0c61d2bc02ee   confluentinc/cp-zookeeper:7.4.4   "/etc/confluent/dock…"   2 hours ago   Up 2 hours                 2888/tcp, 3888/tcp, 0.0.0.0:22181->2181/tcp   kafka-zookeeper-1
3e107bf30a7f   confluentinc/cp-kafka:7.4.4       "/etc/confluent/dock…"   3 hours ago   Exited (143) 2 hours ago                                                 dockertest-kafka-1
cec225b49abe   confluentinc/cp-zookeeper:7.4.4   "/etc/confluent/dock…"   3 hours ago   Exited (143) 2 hours ago                                                 dockertest-zookeeper-1

C:\Users\akash\dockertest\Kafka>docker-compose -f kafka.yaml down
time="2024-09-15T16:43:28+05:30" level=warning msg="C:\\Users\\akash\\dockertest\\Kafka\\kafka.yaml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion"
[+] Running 3/3
 ✔ Container kafka-kafka-1      Removed                                                                            1.7s
 ✔ Container kafka-zookeeper-1  Removed                                                                            0.9s
 ✔ Network kafka_default        Removed    

###############
 Stop Containers
 ###############

 docker-compose -f kafka.yaml stop

###############
Kafka Topic Create
###############

C:\Users\akash\dockertest>docker exec -it 3e107bf30a7f /bin/kafka-topics --create --replication-factor 1 --partitions 1 --topic my-topic --bootstrap-server localhost:9092
Created topic my-topic.



###############
Kafka Console Consumer
###############
PS C:\Users\akash> docker exec -it 3e107bf30a7f /bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic my-topic --from-beginning
a
akash
hi there



###############
Kafka Console Producer
###############
PS C:\Users\akash> docker exec -it 3e107bf30a7f /bin/kafka-console-producer --bootstrap-server localhost:9092 --topic my-topic
>a
>akash
>hi there