package net.sf.jtables.table.impl;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestRowImpl {
	
	private RowImpl<String> row1, row2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		row1 = null;
		row2 = null;
	}

	@Test
	public final void testHashCode01() {
		row1 = new RowImpl<String>();
		row2 = new RowImpl<String>();
		assertEquals(row1.hashCode(), row2.hashCode());
	}
	
	@Test
	public final void testEqualsObject01() {
		row1 = new RowImpl<String>();
		row2 = new RowImpl<String>();
		assertEquals(row1, row2);
	}
	
	@Test
	public final void testHashCode02() {
		row1 = new RowImpl<String>(Arrays.asList("blau", "rot"));
		row2 = new RowImpl<String>(Arrays.asList("blau", "rot"));
		assertEquals(row1.hashCode(), row2.hashCode());
	}
	
	@Test
	public final void testEqualsObject02() {
		row1 = new RowImpl<String>(Arrays.asList("blau", "rot"));
		row2 = new RowImpl<String>(Arrays.asList("blau", "rot"));
		assertEquals(row1, row2);
	}

	@Test
	public final void testGetObject01() {
		row1 = new RowImpl<String>(Arrays.asList("blau", "rot"));
		row1.setIdentifier(new LinkedHashSet<Object>(Arrays.asList("eins", "zwei")));
		assertEquals("blau",row1.get("eins"));
		assertEquals("rot",row1.get("zwei"));
	}
	
	@Test(expected=NoSuchElementException.class)
	public final void testGetObject02() {
		row1 = new RowImpl<String>(Arrays.asList("blau", "rot"));
//		row1.setIdentifier(new LinkedHashSet<Object>(Arrays.asList("eins", "zwei")));
		assertEquals("blau",row1.get("eins"));
//		assertEquals("rot",row1.get("zwei"));
	}
	
	@Test
	public final void testGetObject03() {
		row1 = new RowImpl<String>(Arrays.asList("blau", "rot"));
		row1.setIdentifier(new LinkedHashSet<Object>(Arrays.asList("eins")));
		assertEquals("blau",row1.get("eins"));
//		assertEquals("rot",row1.get("zwei"));
	}
	
	@Test(expected=NoSuchElementException.class)
	public final void testGetObject04() {
		row1 = new RowImpl<String>(Arrays.asList("blau", "rot"));
		row1.setIdentifier(new LinkedHashSet<Object>());
		assertEquals("blau",row1.get("eins"));
//		assertEquals("rot",row1.get("zwei"));
	}

}
