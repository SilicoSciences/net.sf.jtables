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

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import net.sf.jtables.table.AnnotatedMutableTable;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2010-12-05
 * 
 */
public class TestStringTableReader {

	private StringTable table;

	private StringReader stringReader;

	private StringTableReader tableReader;

	private List<List<? extends String>> rows;

	private List<List<? extends String>> cols;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@SuppressWarnings("serial")
	@Before
	public void setUp() throws Exception {
		rows = new ArrayList<List<? extends String>>();
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
		cols = new ArrayList<List<? extends String>>();
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

	/**
	 * Test method for
	 * {@link net.sf.jtables.table.AbstractTableReader#read(java.io.Reader)}.
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("serial")
	@Test
	public final void testReadReader() throws IOException {
		tableReader = new StringTableReader(false, false);
		AnnotatedMutableTable<String> result = tableReader.read(stringReader);
		assertEquals(table.toString(), result.toString());
	}

	/**
	 * Test method for
	 * {@link net.sf.jtables.table.AbstractTableReader#read(java.io.Reader)}.
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("serial")
	@Test
	public final void testReadReader01() throws IOException {
		table.setColumnIdentifier(new LinkedHashSet<String>() {
			{
				add("cid01");
			}
		});
//		System.out.println(table);
		tableReader = new StringTableReader(true, false);
		AnnotatedMutableTable<String> result = tableReader.read(stringReader);
		System.out.println(result);
	}

}
