package com.opencredo.connect.neo4j;


import com.opencredo.connect.neo4j.sink.Neo4jSinkTask;
import com.opencredo.connect.neo4j.util.Version;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;

import java.util.List;
import java.util.Map;

public class Neo4jSinkConnector extends SinkConnector {


    public String version() {
        return Version.getVersion();
    }

    public void start(Map<String, String> map) {

    }

    public Class<? extends Task> taskClass() {
        return Neo4jSinkTask.class;
    }

    public List<Map<String, String>> taskConfigs(int i) {
        return null;
    }

    public void stop() {

    }

    public ConfigDef config() {
        return null;
    }
}
