package ru.unclediga.example.dbunit;

import org.dbunit.Assertion;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.sql.DataSource;
import java.io.InputStream;

@RunWith(JUnit4.class)
public class DataSourceDBUnitTest extends DataSourceBasedDBTestCase {
    
    @Override
    protected DataSource getDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(
                "jdbc:h2:mem:default;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:dbunit/schema.sql'");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("dbunit/data.xml")) {
            return new FlatXmlDataSetBuilder().build(resourceAsStream);
        }
    }

    private IDataSet getDataSet2() throws Exception {
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("dbunit/data2.xml")) {
            return new FlatXmlDataSetBuilder().build(resourceAsStream);
        }
    }

    private IDataSet getDataSetAgro() throws Exception {
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("dbunit/data3.xml")) {
            return new FlatXmlDataSetBuilder().build(resourceAsStream);
        }
    }

    @Override
    protected DatabaseOperation getSetUpOperation() {
        return DatabaseOperation.REFRESH;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void givenDataSetEmptySchema_whenDataSetCreated_thenTablesAreEqual() throws Exception {
        IDataSet expectedDataSet = getDataSet();
        ITable expectedTable = expectedDataSet.getTable("CLIENTS");
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("CLIENTS");
        Assertion.assertEquals(expectedTable, actualTable);
    }
    @Test(expected = org.dbunit.dataset.NoSuchTableException.class)
    public void givenDataSetEmptySchema_whenDataSetCreated_thenTablesNotFound() throws Exception {
        IDataSet expectedDataSet = getDataSet();
        ITable expectedTable = expectedDataSet.getTable("NEW_CLIENTS");
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("NEW_CLIENTS");
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void givenDataSetEmptySchema_whenDataSetCreated_thenTablesAreEqual2() throws Exception {
        IDataSet expectedDataSet = getDataSet2();
        ITable expectedTable = expectedDataSet.getTable("CLIENTS");
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("CLIENTS");
        Assertion.assertEqualsIgnoreCols(expectedTable,actualTable,new String[]{"id","last_name"});
    }

    @Test(expected = junit.framework.ComparisonFailure.class)
    public void givenDataSetEmptySchema_whenDataSetCreated_thenTablesNotEqual2() throws Exception {
        IDataSet expectedDataSet = getDataSet2();
        ITable expectedTable = expectedDataSet.getTable("CLIENTS");
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("CLIENTS");
        Assertion.assertEqualsIgnoreCols(expectedTable,actualTable,new String[]{"id"});
    }
    @Test
    public void givenDataSetEmptySchema_whenDataSetCreated_thenTablesAreEqual3() throws Exception {
        IDataSet expectedDataSet = getDataSet2();
        ITable expectedTable = expectedDataSet.getTable("ITEMS");
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("ITEMS");
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void AGRO_MOB_USER() throws Exception {
        IDataSet expectedDataSet = getDataSetAgro();
        ITable expectedTable = expectedDataSet.getTable("AGRO_MOB_USER");
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("AGRO_MOB_USER");
        try {
            Assertion.assertEquals(expectedTable, actualTable);
        }catch (junit.framework.ComparisonFailure e){
            Assert.assertEquals(e.getExpected(),"4");
            Assert.assertEquals(e.getActual(),"0");
            Assert.assertEquals(e.getMessage(),"row count (table=AGRO_MOB_USER) expected:<[4]> but was:<[0]>");
        }
    }
}