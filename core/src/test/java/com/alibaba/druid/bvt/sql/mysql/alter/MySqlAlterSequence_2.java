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
package com.alibaba.druid.bvt.sql.mysql.alter;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.util.JdbcConstants;
import junit.framework.TestCase;
import org.junit.Assert;

public class MySqlAlterSequence_2 extends TestCase {
    public void test_alter_seq_0() throws Exception {
        String sql = "ALTER SEQUENCE SEQ_XXX INCREMENT BY 2";
        SQLStatement stmt = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL).get(0);
        String output = SQLUtils.toOracleString(stmt);
        Assert.assertEquals("ALTER SEQUENCE SEQ_XXX INCREMENT BY 2", output);
    }

    public void test_alter_seq_1() throws Exception {
        String sql = "ALTER SEQUENCE customers_seq \n" +
                "   MAXVALUE 1500;";
        SQLStatement stmt = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL).get(0);
        String output = SQLUtils.toOracleString(stmt);
        Assert.assertEquals("ALTER SEQUENCE customers_seq MAXVALUE 1500;", output);
    }

    public void test_alter_seq_2() throws Exception {
        String sql = "ALTER SEQUENCE customers_seq \n" +
                "   CYCLE\n" +
                "   CACHE 5;";
        SQLStatement stmt = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL).get(0);
        String output = SQLUtils.toOracleString(stmt);
        Assert.assertEquals("ALTER SEQUENCE customers_seq CYCLE CACHE 5;", output);
    }
}
