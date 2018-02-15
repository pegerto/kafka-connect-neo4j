package com.opencredo.connect.neo4j.sink;


import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

public class Neo4jSinkConfig extends AbstractConfig{


    public Neo4jSinkConfig(ConfigDef definition, Map<?, ?> originals, boolean doLog) {
        super(definition, originals, doLog);
    }

    public Neo4jSinkConfig(ConfigDef definition, Map<?, ?> originals) {
        super(definition, originals);
    }

    public static final ConfigDef CONFIG_DEF = new ConfigDef();


}
