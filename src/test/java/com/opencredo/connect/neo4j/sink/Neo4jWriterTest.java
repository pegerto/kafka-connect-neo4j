package com.opencredo.connect.neo4j.sink;

import com.opencredo.connect.neo4j.util.Neo4jBoltRule;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.sink.SinkRecord;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.neo4j.graphdb.Result;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;


public class Neo4jWriterTest extends EasyMockSupport {

    private static final Schema SCHEMA = SchemaBuilder.STRING_SCHEMA;

    private Neo4jWriter writer;

    @Rule
    public Neo4jBoltRule neo4j = new Neo4jBoltRule();


    @Before
    public void start(){
        writer = new Neo4jWriter(neo4j.getBoltUrl());
    }

    @Test
    public void writeOneRecords() throws SQLException {
        final Collection<SinkRecord> records = Collections.singleton(
                new SinkRecord("test_topic", 1, null, null, SCHEMA, "", 0));

        writer.write(records);
        Result result = neo4j.getGraphDatabase().execute(String.format("MATCH (a:%s) return a", "test_topic"));

        assertThat(result, notNullValue());
        assertThat(result.hasNext(), is(true));

        Map match  = result.next();
        assertThat(result.hasNext(), is(false));
    }


}