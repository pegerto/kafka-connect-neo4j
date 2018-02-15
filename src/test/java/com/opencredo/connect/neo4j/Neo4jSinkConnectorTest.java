package com.opencredo.connect.neo4j;

import com.opencredo.connect.neo4j.sink.Neo4jSinkTask;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.text.MatchesPattern.matchesPattern;
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

}