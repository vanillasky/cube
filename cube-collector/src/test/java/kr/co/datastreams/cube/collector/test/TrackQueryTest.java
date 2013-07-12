package kr.co.datastreams.cube.collector.test;

import kr.co.datastreams.cube.collector.TrackQuery;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 5
 * Time: 오후 6:18
 * To change this template use File | Settings | File Templates.
 */
public class TrackQueryTest {

    TrackQuery baseQuery;

    @Before
    public void setUp() throws Exception {
        baseQuery = new TrackQuery(new String[]{"바탕화면", "윈도우"});
    }


    @Test
    public void testCreateWithNull() throws Exception {
        TrackQuery f2 = new TrackQuery();
        assertTrue(f2.isEmpty());
    }

    @Test
    public void testEquals() throws Exception {
        TrackQuery f2 = new TrackQuery(new String[]{"바탕화면", "윈도우"});
        assertEquals(baseQuery, f2);
        assertFalse(baseQuery == f2);
    }


    @Test
    public void testAddQuery() throws Exception {
        TrackQuery f2 = baseQuery.add(new TrackQuery(new String[]{"애플"}));
        String[] expected = {"바탕화면", "윈도우", "애플"};
        assertArrayEquals(expected, f2.getKeywords());
        assertFalse(baseQuery == f2);
    }

    @Test
    public void testAddKeywords() throws Exception {
        TrackQuery f2 = baseQuery.addKeywords(new String[]{"애플"});
        String[] expected = {"바탕화면", "윈도우", "애플"};
        assertArrayEquals(expected, f2.getKeywords());
        assertFalse(baseQuery == f2);
    }


    @Test
    public void testAdd_duplicatedKeyword() throws Exception {
        TrackQuery f2 = baseQuery.add(new TrackQuery(new String[]{"윈도우"}));
        String[] expected = {"바탕화면", "윈도우"};
        assertArrayEquals(expected, f2.getKeywords());
        assertFalse(baseQuery == f2);
        assertEquals(baseQuery, f2);
    }


}
