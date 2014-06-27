package cn.org.rapid_framework.generator.provider.db.table;

import cn.org.rapid_framework.generator.provider.db.table.model.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenyuan
 * Date: 6/27/14
 * Time: 9:15
 * To change this template use File | Settings | File Templates.
 */
public class TableFactoryTest {

    private Logger logger = LoggerFactory.getLogger(TableFactoryTest.class);

    @org.junit.Test
    public void testGetInstance() throws Exception {

    }

    @org.junit.Test
    public void testGetCatalog() throws Exception {

    }

    @org.junit.Test
    public void testGetSchema() throws Exception {

    }

    @org.junit.Test
    public void testGetAllTables() throws Exception {
        List<Table> tables = TableFactory.getInstance().getAllTables();
        for (Table table : tables) {
            logger.info("table");
        }
    }

    @org.junit.Test
    public void testGetTable() throws Exception {

    }
}
