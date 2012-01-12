package net.sf.jtables.table.impl;

import java.util.List;
import java.util.Set;

import net.sf.jtables.table.Column;
import net.sf.kerner.utils.collections.list.ListTransformer;
import net.sf.kerner.utils.collections.list.impl.AbstractTransformingListFactory;
import net.sf.kerner.utils.transformer.Transformer;

@Deprecated
class ListToColumnTransformer<T> extends
		AbstractTransformingListFactory<List<T>, Column<T>> implements
		Transformer<List<T>, Column<T>>, ListTransformer<List<T>, Column<T>> {

	private final Set<? extends Object> idents;

	ListToColumnTransformer(Set<? extends Object> idents) {
		super();
		this.idents = idents;
	}

	public Column<T> transform(List<T> element) {
		final ColumnImpl<T> result = new ColumnImpl<T>(element);
		result.setIdentifier(idents);
		System.err.println("idents="+idents);
		return result;
	}

}
