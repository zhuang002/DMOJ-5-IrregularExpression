/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irregularexpression;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zhuan
 */
public class IrregularExpressionTest {
    
    public IrregularExpressionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testPattern2() {
        Pattern2 pt=new Pattern2("abc");
        
        assertEquals(pt.length,2);
        assertEquals(pt.match("abc"),false);
        assertEquals(pt.match("aa"),false);
        assertEquals(pt.match("cc"),false);
        assertEquals(pt.match("a"),false);
        assertEquals(pt.match("ab"),true);
        assertEquals(pt.match("b"),false);
        assertEquals(pt.match("c"),false);
        assertEquals(pt.match("ac"),true);
        assertEquals(pt.match("bc"),true);
        assertEquals(pt.match("ca"),true);
    }
    
    @Test
    public void testExpression() {
        Expression exp=new Expression("abc[deffd]ghi");
        assertEquals(exp.match("abdefghi"),false);
        assertEquals(exp.match("abcdefghi"),true);
        assertEquals(exp.match("abcdefgh"),false);
        assertEquals(exp.match("abcdedghi"),true);
        assertEquals(exp.match("abcddeghi"),true);
        assertEquals(exp.match("abcdeghi"),false);
        assertEquals(exp.match("abcfefghi"),true);
        
        exp=new Expression("abc[deffd]");
        assertEquals(exp.match("abdef"),false);
        assertEquals(exp.match("abcdef"),true);
        assertEquals(exp.match("abcdefg"),false);
        assertEquals(exp.match("abcded"),true);
        assertEquals(exp.match("abcdde"),true);
        assertEquals(exp.match("abcde"),false);
        assertEquals(exp.match("abcfef"),true);
        
        exp=new Expression("[deffd]ghi");
        assertEquals(exp.match("abdefghi"),false);
        assertEquals(exp.match("defghi"),true);
        assertEquals(exp.match("defgh"),false);
        assertEquals(exp.match("dedghi"),true);
        assertEquals(exp.match("ddeghi"),true);
        assertEquals(exp.match("deghi"),false);
        assertEquals(exp.match("fefghi"),true);
    }
}
