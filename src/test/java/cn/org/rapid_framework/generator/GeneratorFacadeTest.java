package cn.org.rapid_framework.generator;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: chenyuan
 * Date: 7/11/14
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 */
public class GeneratorFacadeTest {
    private Logger logger = LoggerFactory.getLogger(GeneratorFacadeTest.class);

    private GeneratorFacade generatorFacade;

    @Before
    public void create(){
        generatorFacade = new GeneratorFacade();
    }

    @Test
    public void testPrintAllTableNames() throws Exception {
        logger.info("###print all table names start");
        generatorFacade.printAllTableNames();
        logger.info("###print all table names end");
    }

    @Test
    public void testDeleteOutRootDir() throws Exception {

    }

    @Test
    public void testGenerateByMap() throws Exception {

    }

    @Test
    public void testDeleteByMap() throws Exception {

    }

    @Test
    public void testGenerateByAllTable() throws Exception {

    }

    @Test
    public void testDeleteByAllTable() throws Exception {

    }

    @Test
    public void testGenerateByTable() throws Exception {

    }

    @Test
    public void testDeleteByTable() throws Exception {

    }

    @Test
    public void testGenerateByClass() throws Exception {

    }

    @Test
    public void testDeleteByClass() throws Exception {

    }

    @Test
    public void testGenerateBySql() throws Exception {

    }

    @Test
    public void testDeleteBySql() throws Exception {

    }
}
