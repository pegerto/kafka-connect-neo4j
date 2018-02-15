package com.opencredo.connect.neo4j.sink;

import com.opencredo.connect.neo4j.util.Version;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;

import java.util.Collection;
import java.util.Map;


public class Neo4jSinkTask extends SinkTask {
    public String version() {
        return Version.getVersion();
    }

    public void start(Map<String, String> map) {

    }

    public void put(Collection<SinkRecord> collection) {

    }

    public void stop() {

    }
}
