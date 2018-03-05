package com.opencredo.connect.neo4j.util;

import org.apache.kafka.connect.data.Field;

import java.util.List;

public class Cypher {

    public static final String NODE_ID = "id";

    public static class CreateClauseBuilder{
        private String node;
        private List<Field> fields;

        public CreateClauseBuilder(String node){
            this.node = node;
        }

        public CreateClauseBuilder fields(List<Field> field){
            this.fields = field;
            return this;
        }

        public String build(){
            return String.format("CREATE (n:%s { %s:$%s %s })", this.node, NODE_ID, NODE_ID, getFieldFragment());
        }

        private String getFieldFragment(){
            String result = "";
            if (fields == null) return result;
            for (Field field:fields){
                result += String.format(",%s:$%s", field.name(),field.name());
            }
            return result;
        }

    }


}
