package com.opencredo.connect.neo4j;

import com.opencredo.connect.neo4j.sink.Neo4jSinkTask;
import junit.framework.TestCase;
import org.apache.kafka.common.config.ConfigDef;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;


import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static  org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
public class Neo4jSinkConnectorTest extends TestCase {

    private Neo4jSinkConnector connector;

    @Before
    public void setup(){
        connector = new Neo4jSinkConnector();
    }

    @Test
    public void testTaskClass() {
        assertEquals(connector.taskClass(), Neo4jSinkTask.class);
    }

    @Test
    public void testVersion() {
        assertThat(connector.version(), matchesPattern("[0-9]*\\.[0-9]*"));
    }

    @Test
    public void configTaskMaxSize() {
        assertThat(connector.taskConfigs(13).size(), is(13));
    }

    @Test
    public void checkConfigDef(){
        ConfigDef conf = connector.config();
        assertThat(conf, notNullValue());
    }

    @Test
    public void validateConfig(){
        final Map<String, String> connectorConfigs = new HashMap<>();
        connector.validate(connectorConfigs).configValues();

    }

}