/**********************************************************************
Copyright (c) 2009-2010 Alexander Kerner. All rights reserved.
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import net.sf.jtables.table.AnnotatedTable;
import net.sf.kerner.utils.io.IOUtils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2011-04-11
 * 
 */
public class TestStringTableReader {

	private StringTable table;

	private StringReader stringReader;

	private StringTableReader tableReader;

	private List<List<String>> rows;

	private List<List<String>> cols;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@SuppressWarnings("serial")
	@Before
	public void setUp() throws Exception {
		rows = new ArrayList<List<String>>();
		rows.add(new ArrayList<String>() {
			{
				add("eins00");
				add("eins01");
				add("eins02");
			}
		});
		rows.add(new ArrayList<String>() {
			{
				add("zwei00");
				add("zwei01");
				add("zwei02");
			}
		});
		cols = new ArrayList<List<String>>();
		cols.add(new ArrayList<String>() {
			{
				add("eins00");
				add("zwei00");
			}
		});
		cols.add(new ArrayList<String>() {
			{
				add("eins01");
				add("zwei01");
			}
		});
		cols.add(new ArrayList<String>() {
			{
				add("eins02");
				add("zwei02");
			}
		});
		table = new StringTable(rows);
		stringReader = new StringReader(table.toString());
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link net.sf.jtables.table.impl.StringTableReader#StringTableReader(boolean, boolean, java.lang.String)}
	 * .
	 */
	@Test
	@Ignore
	public final void testStringTableReaderBooleanBooleanString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link net.sf.jtables.table.impl.StringTableReader#StringTableReader(boolean, boolean)}
	 * .
	 */
	@Test
	@Ignore
	public final void testStringTableReaderBooleanBoolean() {
		fail("Not yet implemented"); // TODO
	}

	// START SNIPPET: example1
	
	/**
	 * Test method for
	 * {@link net.sf.jtables.table.AbstractTableReader#readAll()}.
	 * 
	 * @throws IOException
	 */
	@Test
	public final void testReadReader() throws IOException {
		String tableString =
			  "1 2 3"
			+ IOUtils.NEW_LINE_STRING
			+ "a b c"
			;
		StringReader stringReader = new StringReader(tableString);
		StringTableReader tableReader = new StringTableReader(stringReader, false, false, " ");
		AnnotatedTable<String> table = tableReader.readAll();
		assertArrayEquals(new String[]{"1","2","3"}, table.getRow(0).toArray());
	}

	// END SNIPPET: example1
	
	/**
	 * Test method for
	 * {@link net.sf.jtables.table.AbstractTableReader#readAll()}.
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("serial")
	@Test
	public final void testReadReader01() throws IOException {
		table.setColumnIdentifier(new LinkedHashSet<String>() {
			{
				add("cid00");
				add("cid01");
				add("cid02");
			}
		});
		table.setRowIdentifier(new LinkedHashSet<String>(){
			{
				add("rid00");
				add("rid01");
			}
		});
		stringReader = new StringReader(table.toString());
		tableReader = new StringTableReader(stringReader, true, true);
		AnnotatedTable<String> result = tableReader.readAll();
		assertEquals(table.toString(), result.toString());
	}
	
	/**
	 * Test method for
	 * {@link net.sf.jtables.table.AbstractTableReader#readAll()}.
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("serial")
	@Test
	public final void testReadReader02() throws IOException {
		table.setColumnIdentifier(new LinkedHashSet<String>() {
			{
				add("cid00");
				add("cid01");
				add("cid02");
			}
		});
		stringReader = new StringReader(table.toString());
		tableReader = new StringTableReader(stringReader, true, false);
		AnnotatedTable<String> result = tableReader.readAll();
		assertEquals(table.toString(), result.toString());
	}
	
	/**
	 * Test method for
	 * {@link net.sf.jtables.table.AbstractTableReader#readAll()}.
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("serial")
	@Test
	public final void testReadReader03() throws IOException {
		table.setRowIdentifier(new LinkedHashSet<String>(){
			{
				add("rid00");
				add("rid01");
			}
		});
		stringReader = new StringReader(table.toString());
		tableReader = new StringTableReader(stringReader, false, true);
		AnnotatedTable<String> result = tableReader.readAll();
		assertEquals(table.toString(), result.toString());
	}
	
	/**
	 * Test method for
	 * {@link net.sf.jtables.table.AbstractTableReader#readAll()}.
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("serial")
	@Test
	public final void testReadReader04() throws IOException {
		table.setRowIdentifier(new LinkedHashSet<String>(){
			{
				add("rid00");
			}
		});
		stringReader = new StringReader(table.toString());
		tableReader = new StringTableReader(stringReader, false, true);
		AnnotatedTable<String> result = tableReader.readAll();
		assertEquals(table.toString(), result.toString());
	}
	
	/**
	 * Test method for
	 * {@link net.sf.jtables.table.AbstractTableReader#readAll()}.
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("serial")
	@Test
	public final void testReadReader05() throws IOException {
		table.setColumnIdentifier(new LinkedHashSet<String>() {
			{
				add("cid00");
			}
		});
		stringReader = new StringReader(table.toString());
		tableReader = new StringTableReader(stringReader, true, false);
		AnnotatedTable<String> result = tableReader.readAll();
		assertEquals(table.toString(), result.toString());
	}

}
