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

import net.sf.jtables.table.Table;
import net.sf.kerner.utils.io.lazy.LazyStringWriter;

/**
 * 
 * 
 * A {@code TableWriter} will write a {@link Table} to
 * <ul>
 * <li>
 * a {@link java.io.File}</li>
 * <li>
 * a {@link java.io.Writer}</li>
 * <li>
 * an {@link java.io.OutputStream}</li>
 * </ul>
 * 
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2010-10-26 *
 */
public class TableWriter extends LazyStringWriter {

	/**
	 * 
	 * Construct a @code TableWriter} that will write given {@link Table}.
	 * 
	 * @param table
	 *            {@link Table} to write
	 */
	public TableWriter(Table<?> table) {
		super(table.toString());
	}
}
