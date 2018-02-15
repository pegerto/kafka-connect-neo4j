package com.opencredo.connect.neo4j.sink;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.Assert.assertThat;

@RunWith(PowerMockRunner.class)
public class Neo4jSinkTaskTest extends TestCase {

    private Neo4jSinkTask task;

    @Before
    public void setup(){
        task = new Neo4jSinkTask();
    }

    @Test
    public void testVersion() throws Exception {
        assertThat(task.version(), matchesPattern("[0-9]*\\.[0-9]*"));
    }

}