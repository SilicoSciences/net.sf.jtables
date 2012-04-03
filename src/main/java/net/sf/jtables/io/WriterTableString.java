/**********************************************************************
Copyright (c) 2011-2012 Alexander Kerner. All rights reserved.
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

package net.sf.jtables.io;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * 
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2012-03-13
 * 
 */
public class WriterTableString extends WriterTableBufferedImpl {

	public WriterTableString(File file) throws IOException {
		super(file);
	}

	public WriterTableString(OutputStream stream) {
		super(stream);
	}

	public WriterTableString(Writer writer) {
		super(writer);
	}
}
