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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2010-10-26
 * 
 */
@SuppressWarnings("serial")
public class TestTableReader {

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
	}

	/**
	 * Test method for
	 * {@link net.sf.kerner.commons.collection.table.impl.TableReader#TableReader(boolean, boolean, java.lang.String)}
	 * .
	 */
	@Test
	@Ignore
	public final void testTableReaderBooleanBooleanString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link net.sf.kerner.commons.collection.table.impl.TableReader#TableReader(boolean, java.lang.String)}
	 * .
	 */
	@Test
	@Ignore
	public final void testTableReaderBooleanString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link net.sf.kerner.commons.collection.table.impl.TableReader#TableReader(java.lang.String)}
	 * .
	 */
	@Test
	@Ignore
	public final void testTableReaderString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link net.sf.kerner.commons.collection.table.impl.TableReader#TableReader()}
	 * .
	 */
	@Test
	@Ignore
	public final void testTableReader() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link net.sf.kerner.commons.collection.table.impl.TableReader#handleLine(java.lang.String)}
	 * .
	 */
	@Test
	@Ignore
	public final void testHandleLine() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link net.sf.kerner.commons.collection.table.impl.TableReader#getIds(java.lang.String)}
	 * .
	 */
	@Test
	@Ignore
	public final void testGetIds() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link net.sf.kerner.commons.collection.table.impl.TableReader#read(java.io.Reader)}
	 * .
	 * @throws IOException 
	 */
	@Test
	public final void testReadReader() throws IOException {
		final StringTable table = new StringTable();
		table.setColumnIdentifier(new LinkedHashSet<String>() {
			{
				add("c1");
				add("c2");
				add("c3");
			}
		});
		table.setRowIdentifier(new LinkedHashSet<String>() {
			{
				add("r1");
				add("r2");
				add("r3");
			}
		});
		table.addRow(new ArrayList<String>(){
			{
				add("eins");
				add("zwei");
			}
		});
		final String result = new TableReader(true, true, "\t").read(new StringReader(table.toString())).toString();
//		System.out.println(result);
		assertEquals(table.toString(), result);
	}
	
	/**
	 * Test method for
	 * {@link net.sf.kerner.commons.collection.table.impl.TableReader#read(java.io.Reader)}
	 * .
	 * @throws IOException 
	 */
	@Test
	public final void testReadReader01() throws IOException {
		final StringTable table = new StringTable();
		table.setRowIdentifier(new LinkedHashSet<String>() {
			{
				add("r1");
				add("r2");
				add("r3");
			}
		});
		table.addRow(new ArrayList<String>(){
			{
				add("eins");
				add("zwei");
			}
		});
		final String result = new TableReader(false, true, "\t").read(new StringReader(table.toString())).toString();
//		System.out.println(result);
		assertEquals(table.toString(), result);
	}
	
	/**
	 * Test method for
	 * {@link net.sf.kerner.commons.collection.table.impl.TableReader#read(java.io.Reader)}
	 * .
	 * @throws IOException 
	 */
	@Test
	public final void testReadReader02() throws IOException {
		final StringTable table = new StringTable();
		table.addRow(new ArrayList<String>(){
			{
				add("eins");
				add("zwei");
			}
		});
		final String result = new TableReader().read(new StringReader(table.toString())).toString();
//		System.out.println(result);
		assertEquals(table.toString(), result);
	}
	
	/**
	 * Test method for
	 * {@link net.sf.kerner.commons.collection.table.impl.TableReader#read(java.io.Reader)}
	 * .
	 * @throws IOException 
	 */
	@Test
	public final void testReadReader03() throws IOException {
		final StringTable table = new StringTable();
		table.addColumn(new ArrayList<String>(){
			{
				add("eins");
				add("zwei");
			}
		});
		final String result = new TableReader().read(new StringReader(table.toString())).toString();
		assertEquals(table.toString(), result);
	}
	
	/**
	 * Test method for
	 * {@link net.sf.kerner.commons.collection.table.impl.TableReader#read(java.io.Reader)}
	 * .
	 * @throws IOException 
	 */
	@Test
	public final void testReadReader04() throws IOException {
		final StringTable table = new StringTable();
		table.addColumn(new ArrayList<String>());
		final String result = new TableReader().read(new StringReader(table.toString())).toString();
		assertEquals(table.toString(), result);
	}
}
