package com.opencredo.connect.neo4j;


import com.opencredo.connect.neo4j.sink.Neo4jSinkConfig;
import com.opencredo.connect.neo4j.sink.Neo4jSinkTask;
import com.opencredo.connect.neo4j.util.Version;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Neo4jSinkConnector extends SinkConnector {

    private Map<String, String> config;

    public String version() {
        return Version.getVersion();
    }

    public void start(Map<String, String> map) {

    }

    public Class<? extends Task> taskClass() {
        return Neo4jSinkTask.class;
    }

    public List<Map<String, String>> taskConfigs(int maxTasks) {
        final List<Map<String,String>> configs = new ArrayList<>();
        for (int i = 0; i < maxTasks; i++)
            configs.add(config);

        return configs;
    }

    public void stop() {

    }

    public ConfigDef config() {
        return Neo4jSinkConfig.CONFIG_DEF;
    }
}
