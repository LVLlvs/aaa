package com.alibaba.druid.bvt.support.odps.udf;

import org.junit.Assert;

import com.alibaba.druid.support.opds.udf.ExportConditions;

import junit.framework.TestCase;

public class ExportConditionsTest5 extends TestCase {
    ExportConditions udf = new ExportConditions();

    public void test_export_conditions() throws Exception {
        String result = udf.evaluate("select * from t where name = 'a1' or name = 'a2'");
        Assert.assertEquals("[[\"t\",\"name\",\"=\",[\"a1\",\"a2\"]]]", result);
    }
}
