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
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.parser.Token;
import junit.framework.TestCase;

public class MySqlAlterDbTest0 extends TestCase {
    public void test_alter_first() throws Exception {
        String sql = "ALTER DATABASE `#mysql50#a-b-c` UPGRADE DATA DIRECTORY NAME;";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement stmt = parser.parseStatementList().get(0);
        parser.match(Token.EOF);

        assertEquals("ALTER DATABASE `#mysql50#a-b-c` UPGRADE DATA DIRECTORY NAME;", SQLUtils.toMySqlString(stmt));
        assertEquals("alter database `#mysql50#a-b-c` upgrade data directory name;", SQLUtils.toMySqlString(stmt, SQLUtils.DEFAULT_LCASE_FORMAT_OPTION));
    }

    public void test_alter_2() throws Exception {
        String sql = "ALTER DATABASE db1_test SET k1='v1' k2='v2'";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement stmt = parser.parseStatementList().get(0);
        parser.match(Token.EOF);

        assertEquals("ALTER DATABASE db1_test SET k1 = 'v1', k2 = 'v2'", SQLUtils.toMySqlString(stmt));
    }

    public void test_alter_3() throws Exception {
        String sql = "ALTER DATABASE db1_test KILL LOADDATA 'lddt_xxxxx_123'";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement stmt = parser.parseStatementList().get(0);
        parser.match(Token.EOF);

        assertEquals("ALTER DATABASE db1_test KILL LOADDATA 'lddt_xxxxx_123'", SQLUtils.toMySqlString(stmt));
    }

}
