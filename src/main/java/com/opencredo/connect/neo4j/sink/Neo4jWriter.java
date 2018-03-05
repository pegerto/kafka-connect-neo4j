package com.opencredo.connect.neo4j.sink;

import org.apache.kafka.connect.sink.SinkRecord;
import org.neo4j.driver.v1.*;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.neo4j.driver.v1.Values.parameters;

public class Neo4jWriter {

    private Driver driver;

    public Neo4jWriter(String boltUrl) {
        driver = GraphDatabase.driver(boltUrl, Config.build()
                .withMaxConnectionPoolSize(50)
                .withoutEncryption()
                .withConnectionAcquisitionTimeout(2, TimeUnit.MINUTES)
                .toConfig());

    }

    void write(final Collection<SinkRecord> records){
        try ( Session session = driver.session() )
        {
            session.writeTransaction(new TransactionWork<Integer>() {
                @Override
                public Integer execute(Transaction tx) {
                    for (SinkRecord record: records) {
                        createNode(tx, record.topic(), 0);
                    }
                    return 0;
                }
            });
        }
    }

    private int createNode(Transaction tx, String nodeType, Object id)
    {
        tx.run( "CREATE (n:"+nodeType +" { name:$name })",
                parameters("name", id));
        return 1;
    }

}
