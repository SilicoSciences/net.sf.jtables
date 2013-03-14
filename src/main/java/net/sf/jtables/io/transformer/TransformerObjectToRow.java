package net.sf.jtables.io.transformer;

import net.sf.jtables.io.WriterTableBuffered;
import net.sf.jtables.table.Row;
import net.sf.kerner.utils.Transformer;

public interface TransformerObjectToRow<V, T> extends Transformer<V, Row<T>> {

}
