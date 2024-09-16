#######How to run######
mvn clean install
docker compose up
C:\Users\akash\dockertest\scala-flink>docker exec -it scala-flink-jobmanager-1 ./bin/flink run /flink-target/flink-wordcount-1.0-SNAPSHOT.jar
WARNING: Unknown module: jdk.compiler specified to --add-exports
WARNING: Unknown module: jdk.compiler specified to --add-exports
WARNING: Unknown module: jdk.compiler specified to --add-exports
WARNING: Unknown module: jdk.compiler specified to --add-exports
WARNING: Unknown module: jdk.compiler specified to --add-exports
Job has been submitted with JobID a72f30f8102f2b99014d3e8e201f9b08
Program execution finished
Job with JobID a72f30f8102f2b99014d3e8e201f9b08 has finished.
Job Runtime: 5505 ms
Accumulator Results:
- 77b972a851cffc076e58b1aeb0f8093f (java.util.ArrayList) [17 elements]


(a,1)
(and,2)
(apache,1)
(batch,1)
(count,1)
(data,1)
(distributed,1)
(engine,1)
(example,1)
(flink,3)
(framework,1)
(is,1)
(processing,3)
(provides,1)
(stream,1)
(using,1)
(word,1)

What's next:
    Try Docker Debug for seamless, persistent debugging tools in any container or image → docker debug scala-flink-jobmanager-1
    Learn more at https://docs.docker.com/go/debug-cli/

C:\Users\akash\dockertest\scala-flink>
#######################