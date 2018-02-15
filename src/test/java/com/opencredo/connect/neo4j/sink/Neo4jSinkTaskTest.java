package com.opencredo.connect.neo4j.sink;

import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collections;
import java.util.Map;

import static org.easymock.EasyMock.verifyUnexpectedCalls;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.Assert.assertThat;

@RunWith(PowerMockRunner.class)
public class Neo4jSinkTaskTest extends EasyMockSupport {

    private Neo4jSinkTask task;

    @Before
    public void setup(){
        task = new Neo4jSinkTask();
    }

    @Test
    public void testVersion() throws Exception {
        assertThat(task.version(), matchesPattern("[0-9]*\\.[0-9]*"));
    }

    @Test
    public void testPutRecordsEmpty(){
        final Neo4jWriter mockWriter = createMock(Neo4jWriter.class);

        Neo4jSinkTask task = new Neo4jSinkTask(){
            @Override
            public void start(Map<String, String> map) {
                writer = mockWriter;
            }
        };
        replayAll();

        task.put(Collections.EMPTY_LIST);
        verifyUnexpectedCalls(mockWriter);

    }

}