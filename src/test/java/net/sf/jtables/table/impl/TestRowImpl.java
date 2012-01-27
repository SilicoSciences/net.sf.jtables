package net.sf.jtables.table.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import net.sf.jtables.table.Row;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TestRowImpl {
	
	private RowImpl r1, r2;

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
		r1 = r2 = null;
	}

	@Ignore
	@Test
	public final void testHashCode() {
		fail("Not yet implemented"); // TODO
	}
	
	@Ignore
	@Test
	public final void testEqualsObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetObject01() throws IOException {
		final StringTable table = new StringTableReader(new File("src/test/resources/anno-orphan-anno.txt"), true, false).readAll();
		Row<String> r = table.getRow(0);
//		System.out.println(r.getIdentifier());
		assertEquals("1", r.get("mz"));
		assertEquals("2", r.get("frac"));
		assertEquals("3", r.get("conf"));
	}

	

}