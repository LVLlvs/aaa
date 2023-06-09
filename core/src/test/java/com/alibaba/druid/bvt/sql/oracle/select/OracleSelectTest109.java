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
package com.alibaba.druid.bvt.sql.oracle.select;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.OracleTest;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.oracle.parser.OracleStatementParser;
import com.alibaba.druid.sql.parser.SQLParserFeature;
import com.alibaba.druid.sql.repository.SchemaRepository;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;

import java.util.List;

public class OracleSelectTest109 extends OracleTest {
    public void test_0() throws Exception {
        String sql = "/* No SQL_REDO for temporary tables */";

        OracleStatementParser parser = new OracleStatementParser(sql, SQLParserFeature.KeepComments);
        List<SQLStatement> statementList = parser.parseStatementList();
        System.out.println(statementList.toString());

        assertEquals(0, statementList.size());

//        SchemaStatVisitor visitor = SQLUtils.createSchemaStatVisitor(DbType.oracle);
//        stmt.accept(visitor);
//
//        {
//            String text = SQLUtils.toOracleString(stmt);
//
//            assertEquals("SELECT OBJID, NAME, OWNER, OWNERID, TABLESPACE\n"
//                         + "\t, TSNO, FILENO, BLOCKNO, AUDIT$, COMMENT$\n"
//                         + "\t, CLUSTERFLAG, PCTFREE$, PCTUSED$, INITRANS, MAXTRANS\n"
//                         + "\t, DEGREE, INSTANCES, CACHE, PROPERTY, DEFLOG\n"
//                         + "\t, TSDEFLOG, ROID, ROWCNT, BLKCNT, AVGRLEN\n" + "\t, TFLAGS, TRIGFLAG, OBJSTATUS, XDBOOL\n"
//                         + "FROM SYS.EXU10TABU T$\n" + "WHERE NOT EXISTS (\n" + "\tSELECT NAME\n"
//                         + "\tFROM SYS.EXU8NXPU N$\n" + "\tWHERE N$.NAME = T$.NAME\n" + "\t\tAND N$.TYPE = 2\n" + ")\n"
//                         + "ORDER BY T$.XDBOOL DESC, T$.NAME", text);
//        }
//
//        System.out.println("Tables : " + visitor.getTables());
//        System.out.println("fields : " + visitor.getColumns());
//        System.out.println("coditions : " + visitor.getConditions());
//        System.out.println("relationships : " + visitor.getRelationships());
//        System.out.println("orderBy : " + visitor.getOrderByColumns());
//
//        assertEquals(2, visitor.getTables().size());
//        assertEquals(31, visitor.getColumns().size());
//        assertEquals(3, visitor.getConditions().size());
//        assertEquals(1, visitor.getRelationships().size());
//        assertEquals(2, visitor.getOrderByColumns().size());

    }
}
