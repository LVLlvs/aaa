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
package com.alibaba.druid.sql.dialect.blink.parser;

import com.alibaba.druid.sql.parser.Lexer;
import com.alibaba.druid.sql.parser.SQLCreateTableParser;
import com.alibaba.druid.sql.parser.SQLParserFeature;
import com.alibaba.druid.sql.parser.SQLStatementParser;

public class BlinkStatementParser extends SQLStatementParser {
    public BlinkStatementParser(String sql) {
        super(new BlinkExprParser(sql));
    }

    public BlinkStatementParser(String sql, SQLParserFeature... features) {
        super(new BlinkExprParser(sql, features));
    }

    public BlinkStatementParser(Lexer lexer) {
        super(new BlinkExprParser(lexer));
    }
//
//    public H2SelectParser createSQLSelectParser() {
//        return new H2SelectParser(this.exprParser, selectListCache);
//    }

    public SQLCreateTableParser getSQLCreateTableParser() {
        return new BlinkCreateTableParser(this.exprParser);
    }
}
