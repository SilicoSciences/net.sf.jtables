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

import java.util.List;

/**
 * 
 * An {@link net.sf.jtables.table.AnnotatedMutableTable AnnotatedMutableTable} with {@link java.lang.String String} elements.
 *
 *
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2010-12-05
 *
 */
public class StringTable extends AnnotatedMutableTableImpl<String> {

	public StringTable() {
		super();
	}
	
	public StringTable(List<List<? extends String>> rows) {
		super(rows);
	}
}
