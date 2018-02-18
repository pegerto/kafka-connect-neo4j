package com.opencredo.connect.neo4j.sink;

import org.apache.kafka.connect.sink.SinkRecord;
import org.neo4j.driver.v1.*;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class Neo4jWriter {

    private Driver driver;

    public Neo4jWriter() {
        driver = GraphDatabase.driver("bolt://localhost:7687" , Config.build()
                .withMaxConnectionPoolSize(50)
                .withoutEncryption()
                .withConnectionAcquisitionTimeout(2, TimeUnit.MINUTES)
                .toConfig());

    }


    public Neo4jWriter(Driver driver){
        this.driver = driver;
    }

    void write(final Collection<SinkRecord> records){
        try ( Session session = driver.session() )
        {
            for (SinkRecord record: records) {
                session.writeTransaction(new TransactionWork<Integer>() {
                    @Override
                    public Integer execute(Transaction tx) {
                        return createNode(tx);
                    }
                });
            }
        }
    }

    private int createNode( Transaction tx )
    {
        tx.run( "CREATE (a:Person {name: $name})");
        return 1;
    }

}
