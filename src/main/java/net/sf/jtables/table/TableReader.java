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

package net.sf.jtables.table;

import java.io.IOException;

import net.sf.kerner.utils.io.buffered.IOIterable;
import net.sf.kerner.utils.io.buffered.IOIterator;

/**
 * 
 * A {@code TableReader} reads an {@link AnnotatedTable} from an input source.
 * </p> It does so by extending
 * {@link IOIterable} in oder to
 * provide possibility to iterate over a table's rows.
 * </p>
 * Via {@link TableReader#readAll()} it is also possible to read in a whole table at once.
 * 
 * @see IOIterable
 * @see AnnotatedTable
 * @see StringTable
 * @see IntegerTable
 * 
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2012-01-12
 * 
 * @param <T> type of elements in {@code Table}
 */
public interface TableReader<T> extends IOIterable<Row<T>> {

	/**
	 * Close this reader.
	 * 
	 * @see java.io.Reader#close()
	 * 
	 */
	void close();

	/**
	 * 
	 * Read a {@link Table} at once.
	 * 
	 * @return new instance of {@code AnnotatedTable} that was read
	 * @throws IOException if reading failed
	 */
	AnnotatedTable<T> readAll() throws IOException;

	/**
	 * 
	 */
	IOIterator<Row<T>> getIterator() throws IOException;

}
