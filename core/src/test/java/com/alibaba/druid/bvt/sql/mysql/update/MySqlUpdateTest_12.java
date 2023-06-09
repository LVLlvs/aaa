/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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
package com.alibaba.druid.bvt.sql.mysql.update;

import com.alibaba.druid.sql.MysqlTest;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat.Column;
import com.alibaba.druid.wall.WallUtils;
import org.junit.Assert;

import java.util.List;

public class MySqlUpdateTest_12 extends MysqlTest {
    public void test_0() throws Exception {
        String sql = "UPDATE t1\n" +
                "INNER JOIN t2 ON t1.id = t2.id\n" +
                "INNER JOIN t3 cm ON t3.id2= t1.id2  set gps_url=null  WHERE  t1.id = ?";

        MySqlStatementParser parser = new MySqlStatementParser(sql);
        List<SQLStatement> statementList = parser.parseStatementList();
        SQLStatement stmt = statementList.get(0);
        print(statementList);

        Assert.assertEquals(1, statementList.size());

        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        stmt.accept(visitor);

//        System.out.println("Tables : " + visitor.getTables());
//        System.out.println("fields : " + visitor.getColumns());
//        System.out.println("coditions : " + visitor.getConditions());
//        System.out.println("orderBy : " + visitor.getOrderByColumns());

        Assert.assertEquals(3, visitor.getTables().size());
        Assert.assertEquals(5, visitor.getColumns().size());
        // Assert.assertEquals(2, visitor.getConditions().size());

        Assert.assertTrue(visitor.containsTable("t1"));
        Assert.assertTrue(visitor.containsTable("t2"));
        Assert.assertTrue(visitor.containsTable("t3"));

        Assert.assertTrue(visitor.getColumns().contains(new Column("t1", "id")));
        Assert.assertTrue(visitor.getColumns().contains(new Column("t3", "id2")));

        {
            String output = SQLUtils.toMySqlString(stmt);
            Assert.assertEquals("UPDATE t1\n" +
                            "\tINNER JOIN t2 ON t1.id = t2.id\n" +
                            "\tINNER JOIN t3 cm ON t3.id2 = t1.id2\n" +
                            "SET gps_url = NULL\n" +
                            "WHERE t1.id = ?", //
                    output);
        }
        {
            String output = SQLUtils.toMySqlString(stmt, SQLUtils.DEFAULT_LCASE_FORMAT_OPTION);
            Assert.assertEquals("update t1\n" +
                            "\tinner join t2 on t1.id = t2.id\n" +
                            "\tinner join t3 cm on t3.id2 = t1.id2\n" +
                            "set gps_url = null\n" +
                            "where t1.id = ?", //
                    output);
        }

        assertTrue(WallUtils.isValidateMySql(sql));
    }
}
