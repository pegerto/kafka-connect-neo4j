package com.opencredo.connect.neo4j.sink;


import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.Config;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

public class Neo4jSinkConfig extends AbstractConfig{

    public static final String BOLT_URL = "bolt.url";
    private static final String BOLT_URL_DOC = "Bolt connection URL.";
    private static final String BOLT_URL_DISPLAY = "Bolt URL";

    public Neo4jSinkConfig(Map<?, ?> props){
        super(CONFIG_DEF, props);
    }

    private static final String CONNECTION_GROUP = "Connection";


    public static final ConfigDef CONFIG_DEF = new ConfigDef()
            .define(BOLT_URL, ConfigDef.Type.STRING, ConfigDef.NO_DEFAULT_VALUE,
                    ConfigDef.Importance.HIGH, BOLT_URL_DOC, CONNECTION_GROUP, 1, ConfigDef.Width.LONG,BOLT_URL_DISPLAY);


}
