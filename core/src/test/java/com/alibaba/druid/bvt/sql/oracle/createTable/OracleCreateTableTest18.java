/*
 * Copyright 1999-2017 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.bvt.sql.oracle.createTable;

import com.alibaba.druid.sql.OracleTest;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.oracle.parser.OracleStatementParser;
import com.alibaba.druid.sql.dialect.oracle.visitor.OracleSchemaStatVisitor;
import com.alibaba.druid.util.JdbcConstants;
import org.junit.Assert;

import java.util.List;

public class OracleCreateTableTest18 extends OracleTest {
    public void test_types() throws Exception {
        String sql = //
                "  CREATE TABLE \"SONAR\".\"AUTHORS\" " //
                        + "   (    \"ID\" NUMBER(38,0) NOT NULL ENABLE, " //
                        + "    \"PERSON_ID\" NUMBER(38,0) NOT NULL ENABLE, " //
                        + "    \"LOGIN\" VARCHAR2(100 BYTE), " //
                        + "    \"CREATED_AT\" TIMESTAMP (6), " //
                        + "    \"UPDATED_AT\" TIMESTAMP (6), " //
                        + "     PRIMARY KEY (\"ID\")" //
                        + "  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS " //
                        + "  TABLESPACE \"USERS\"  ENABLE" //
                        + "   ) SEGMENT CREATION DEFERRED " //
                        + "  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 " //
                        + " NOCOMPRESS LOGGING" //
                        + "  TABLESPACE \"USERS\" ;";

        OracleStatementParser parser = new OracleStatementParser(sql);
        List<SQLStatement> statementList = parser.parseStatementList();
        SQLStatement statement = statementList.get(0);
        print(statementList);

        Assert.assertEquals(1, statementList.size());

        Assert.assertEquals("CREATE TABLE \"SONAR\".\"AUTHORS\" (\n" +
                        "\t\"ID\" NUMBER(38, 0) NOT NULL ENABLE,\n" +
                        "\t\"PERSON_ID\" NUMBER(38, 0) NOT NULL ENABLE,\n" +
                        "\t\"LOGIN\" VARCHAR2(100 BYTE),\n" +
                        "\t\"CREATED_AT\" TIMESTAMP(6),\n" +
                        "\t\"UPDATED_AT\" TIMESTAMP(6),\n" +
                        "\tPRIMARY KEY (\"ID\")\n" +
                        "\t\tUSING INDEX\n" +
                        "\t\tPCTFREE 10\n" +
                        "\t\tINITRANS 2\n" +
                        "\t\tMAXTRANS 255\n" +
                        "\t\tTABLESPACE \"USERS\"\n" +
                        "\t\tCOMPUTE STATISTICS\n" +
                        "\t\tENABLE\n" +
                        ")\n" +
                        "PCTFREE 10\n" +
                        "PCTUSED 40\n" +
                        "INITRANS 1\n" +
                        "MAXTRANS 255\n" +
                        "NOCOMPRESS\n" +
                        "LOGGING\n" +
                        "TABLESPACE \"USERS\";", //
                SQLUtils.toSQLString(statement, JdbcConstants.ORACLE));

        OracleSchemaStatVisitor visitor = new OracleSchemaStatVisitor();
        statement.accept(visitor);

        System.out.println("Tables : " + visitor.getTables());
        System.out.println("fields : " + visitor.getColumns());
        System.out.println("coditions : " + visitor.getConditions());
        System.out.println("relationships : " + visitor.getRelationships());
        System.out.println("orderBy : " + visitor.getOrderByColumns());

        Assert.assertEquals(1, visitor.getTables().size());

        Assert.assertEquals(5, visitor.getColumns().size());

        Assert.assertTrue(visitor.containsColumn("SONAR.AUTHORS", "ID"));
    }
}
