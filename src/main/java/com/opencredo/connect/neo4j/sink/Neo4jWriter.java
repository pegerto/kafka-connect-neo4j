package com.opencredo.connect.neo4j.sink;

import org.apache.kafka.connect.sink.SinkRecord;

import java.util.Collection;

public class Neo4jWriter {

    public Neo4jWriter() {

    }

    void write(final Collection<SinkRecord> records){
        for (SinkRecord record: records){
        }
    }

}
