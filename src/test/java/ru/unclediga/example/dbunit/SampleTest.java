package ru.unclediga.example.dbunit;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;

/***************************************************************
   https://dbunit.sourceforge.net/dbunit/howto.html
***************************************************************/

@RunWith(JUnit4.class)
public class SampleTest extends DBTestCase
{
    public SampleTest()
    {
        super( "My DBUnit Test" );
        System.out.println(">>>>>>>>>" + new File(".").getAbsolutePath());
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:mem:default;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:dbunit/schema.sql'" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "" );
	// System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "" );
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("dbunit/data.xml")) {
            return new FlatXmlDataSetBuilder().build(resourceAsStream);
        }
    }

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception
    {
        return DatabaseOperation.REFRESH;
    }

    @Override	
    protected DatabaseOperation getTearDownOperation() throws Exception
    {
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
    public void Test1() throws Exception {
   
    IDataSet databaseDataSet = getConnection().createDataSet();
        String[] dt  =  databaseDataSet.getTableNames(); 
        for(String t : dt){
           System.out.println("DS:TABLE>>>>>>>>>" + t);
           ITable dclients = databaseDataSet.getTable(t);
           System.out.println("DS>>>>>>>>> cnt=" + dclients.getRowCount());
           System.out.println("DS>>>>>>>>> " + dclients.getTableMetaData());
        };



       IDataSet expectedDataSet; 
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("dbunit/data.xml")) {
            expectedDataSet = new FlatXmlDataSetBuilder().build(resourceAsStream);
        }
        
        String[] et  =  expectedDataSet.getTableNames(); 
        for(String t : et){
           System.out.println("ES:TABLE>>>>>>>>>" + t);
           ITable eclients = expectedDataSet.getTable(t);
           System.out.println("ES>>>>>>>>> cnt=" + eclients.getRowCount());
           System.out.println("ES>>>>>>>>> " + eclients.getTableMetaData());
        };

       Assertion.assertEquals(databaseDataSet.getTable("CLIENTS"),expectedDataSet.getTable("CLIENTS"));
       Assertion.assertEquals(databaseDataSet.getTable("ITEMS"),expectedDataSet.getTable("ITEMS"));
       // ERROR Failed tests:   Test1(ru.unclediga.example.dbunit.SampleTest): table count expected:<[5]> but was:<[2]>
       // there are 5 tables in scheme.sql, but data.xml have 2 tables only 
       // Assertion.assertEquals(databaseDataSet,expectedDataSet);
    }

}