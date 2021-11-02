/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rafael
 */
public class ComparatorUtilsTest {

    public ComparatorUtilsTest() {
    }

    /**
     * Test of sortByValueAsc method, of class ComparatorUtils.
     */
    @Test
    public void testSortByValueAsc() {
        System.out.println("sortByValueAsc");
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "teste2");
        map.put(1, "teste");
        Map<Integer, String> expResult = new HashMap<>();
        expResult.put(1, "teste");
        expResult.put(2, "teste2");
        Map<Integer, String> result = ComparatorUtils.sortByValueAsc(map);
        assertEquals(expResult, result);
    }

    /**
     * Test of sortByValueDesc method, of class ComparatorUtils.
     */
    @Test
    public void testSortByValueDesc() {
        System.out.println("sortByValueDesc");
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "teste");
        map.put(2, "teste2");
        Map<Integer, String> expResult = new HashMap<>();
        expResult.put(2, "teste2");
        expResult.put(1, "teste");
        Map<Integer, String> result = ComparatorUtils.sortByValueDesc(map);
        assertEquals(expResult, result);
    }

}
