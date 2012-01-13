/**********************************************************************
Copyright (c) 2009-2012 Alexander Kerner. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 ***********************************************************************/

package net.sf.jtables.table.impl;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

import net.sf.kerner.utils.io.IOUtils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TestStringTable {
	
	private StringTable table1;
	
	private StringTable table2;

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
		table1 = table2 = null;
	}

	@Ignore
	@Test
	public final void testClone01() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGetColumnsString() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGetRowsString() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGetRowObject() {
		fail("Not yet implemented"); // TODO
	}
	
	@Ignore
	@Test
	public final void testGetColumnObject() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testSetRow() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testSetColumn() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testAddColumnIntColumnOfT() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testAddColumnColumnOfT() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testSet() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAddRow01() throws IOException {
		
		// read a table from a string
		// table has row and column headers
		
		table1 = new StringTableReader(new StringReader("\tcolA\tcolB"
				+IOUtils.NEW_LINE_STRING
				+ "rowA\ta.a\ta.b"
				), true, true).readAll();
		
		// add another row to table
		
		table1.addRow(new RowImpl<String>(Arrays.asList("rowB","b.a","b.b"
				)));
		
		assertEquals(2,table1.getRows().size());
		assertArrayEquals(new String[]{"b.a","b.b"}, table1.getRow("rowB").toArray());
	}
	
	@Test
	public final void testAddRow02() throws IOException {
		
		// read a table from a string
		// table has only column headers
		
		table1 = new StringTableReader(new StringReader("\tcolA\tcolB"
				+IOUtils.NEW_LINE_STRING
				+ "a.a\ta.b"
				), true, false).readAll();
		
		// add another row to table
		
		table1.addRow(new RowImpl<String>(Arrays.asList("b.a","b.b"
				)));
		
		assertEquals(2,table1.getRows().size());
		assertArrayEquals(new String[]{"a.a","b.a"}, table1.getColumn("colA").toArray());
		assertArrayEquals(new String[]{"a.b","b.b"}, table1.getColumn("colB").toArray());
	}

	@Ignore
	@Test
	public final void testAddRowInt01() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testFillRows() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testFillColumns() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testFill() {
		fail("Not yet implemented"); // TODO
	}
	
	@Ignore
	@Test
	public final void testFillAndSet() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testRemoveRow() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testRemoveColumn() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testRemove() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testHashCode01() throws IOException {
		table1 = new StringTableReader(new StringReader(""), true, true).readAll();
		table2 = new StringTableReader(new StringReader(""), true, true).readAll();
		assertEquals(table1.hashCode(), table2.hashCode());
	}

	@Test
	public final void testEqualsObject01() throws IOException {
		table1 = new StringTableReader(new StringReader(""), true, true).readAll();
		table2 = new StringTableReader(new StringReader(""), true, true).readAll();
		assertEquals(table1, table2);
	}
	
	@Test
	public final void testHashCode02() throws IOException {
		table1 = new StringTableReader(new StringReader("\tcolA\tcolB"
		+IOUtils.NEW_LINE_STRING
		+ "rowA\ta.a\ta.b"
		+IOUtils.NEW_LINE_STRING
		+ "rowB\tb.a\tb.b"
		), true, true).readAll();
		table2 = new StringTableReader(new StringReader("\tcolA\tcolB"
				+IOUtils.NEW_LINE_STRING
				+ "rowA\ta.a\ta.b"
				+IOUtils.NEW_LINE_STRING
				+ "rowB\tb.a\tb.b"
				), true, true).readAll();
		assertEquals(table1.hashCode(), table2.hashCode());
	}

	@Test
	public final void testEqualsObject02() throws IOException {
		table1 = new StringTableReader(new StringReader("\tcolA\tcolB"
				+IOUtils.NEW_LINE_STRING
				+ "rowA\ta.a\ta.b"
				+IOUtils.NEW_LINE_STRING
				+ "rowB\tb.a\tb.b"
				), true, true).readAll();
		table2 = new StringTableReader(new StringReader("\tcolA\tcolB"
				+IOUtils.NEW_LINE_STRING
				+ "rowA\ta.a\ta.b"
				+IOUtils.NEW_LINE_STRING
				+ "rowB\tb.a\tb.b"
				), true, true).readAll();
		assertEquals(table1, table2);
	}

	@Test
	public final void testIterator01() throws IOException {
		table1 = new StringTableReader(new StringReader(""
				), true, true).readAll();
		table1.iterator();
	}

	@Ignore
	@Test
	public final void testGetRowInt() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGetRows() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGetColumnInt() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGetColumns() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGet() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGetRowSize() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGetColumnSize() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGetMaxRowSize() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGetMaxColumnSize() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGetNumberOfRows() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGetNumberOfColumns() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGetRowIterator() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGetColumnIterator() {
		fail("Not yet implemented"); // TODO
	}

}
