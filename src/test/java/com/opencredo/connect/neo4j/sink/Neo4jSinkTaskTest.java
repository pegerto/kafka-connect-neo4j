package com.opencredo.connect.neo4j.sink;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.sink.SinkRecord;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.verifyUnexpectedCalls;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.Assert.assertThat;

@RunWith(PowerMockRunner.class)
public class Neo4jSinkTaskTest extends EasyMockSupport {

    private Neo4jSinkTask task;
    private static final Schema SCHEMA = SchemaBuilder.STRING_SCHEMA;

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

        final Neo4jSinkTask task = new Neo4jSinkTask(){
            @Override
            public void start(Map<String, String> map) {
                writer = mockWriter;
            }
        };
        replayAll();

        task.put(Collections.EMPTY_LIST);
        verifyUnexpectedCalls(mockWriter);

    }


    @Test
    public void testPutOneRecords(){
        final Neo4jWriter mockWriter = createMock(Neo4jWriter.class);

        final Neo4jSinkTask task = new Neo4jSinkTask(){
            @Override
            public void start(Map<String, String> map) {
                writer = mockWriter;
            }
        };
        final Collection<SinkRecord> records = Collections.singleton(
                new SinkRecord("test_topic", 1, null, null, SCHEMA, "", 0));

        mockWriter.write(records);
        expectLastCall().andVoid().once();

        replayAll();
        task.start(new HashMap<String, String>());
        task.put(records);
        verifyAll();

    }
}