package com.opencredo.connect.neo4j.sink;

import com.opencredo.connect.neo4j.util.Cypher;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.sink.SinkRecord;
import org.neo4j.driver.v1.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.neo4j.driver.v1.Values.value;

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
                        createNode(tx, record.topic(), record);
                    }
                    return 0;
                }
            });
        }
    }

    private int createNode(Transaction tx, String topic,  SinkRecord record)
    {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put(Cypher.NODE_ID, record.key());
        if (!record.valueSchema().type().isPrimitive()){
            Struct value = (Struct) record.value();
            for (Field field: record.valueSchema().fields()){
                parameterMap.put(field.name(), value.get(field));
            }
        }

        final String clause = new Cypher.CreateClauseBuilder(topic)
                .fields(record.valueSchema().type() == Schema.Type.STRUCT?record.valueSchema().fields():null)
                .build();

        tx.run( clause, value(parameterMap));
        return 1;
    }

}
