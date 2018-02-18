package com.opencredo.connect.neo4j.sink;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.sink.SinkRecord;
import org.easymock.EasyMock;
import org.easymock.EasyMockListener;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.TransactionWork;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collection;
import java.util.Collections;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expectLastCall;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(PowerMockRunner.class)
public class Neo4jWriterTest extends EasyMockSupport {

    private static final Schema SCHEMA = SchemaBuilder.STRING_SCHEMA;
    private Neo4jWriter writer;
    private Driver mockDriver;


    @Before
    public void start(){
        this.mockDriver = mock(Driver.class);
        this.writer = new Neo4jWriter(mockDriver);
    }

    @Test
    public void writeOneRecords(){
        final Collection<SinkRecord> records = Collections.singleton(
                new SinkRecord("test_topic", 1, null, null, SCHEMA, "", 0));

        // Expectation to create a session
        final Session mockSession = mock(Session.class);
        mockDriver.session();
        expectLastCall().andReturn(mockSession).once();

        // Expectation to close the session
        mockSession.close();
        expectLastCall().andVoid().once();

        // Expect to call writeTransaction
        mockSession.writeTransaction((TransactionWork<Object>) anyObject());
        expectLastCall().andReturn(1).once();
        replayAll();


        writer.write(records);
        verifyAllUnexpectedCalls();

    }

}