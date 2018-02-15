package com.opencredo.connect.neo4j.sink;

import com.opencredo.connect.neo4j.util.Version;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Collection;
import java.util.Map;


public class Neo4jSinkTask extends SinkTask {
    private static final Logger log = LoggerFactory.getLogger(Neo4jSinkTask.class);

    protected Neo4jWriter writer;

    public String version() {
        return Version.getVersion();
    }

    public void start(Map<String, String> map) {
        log.info("Starting task");
        writer = new Neo4jWriter();
    }

    public void put(Collection<SinkRecord> records) {
        if (records.isEmpty()) {
            return;
        }
    }

    public void stop() {

    }
}
