package net.sf.jtables.table.impl;

import java.util.List;
import java.util.Set;

import net.sf.jtables.table.Row;
import net.sf.kerner.utils.collections.list.AbstractTransformingListFactory;
import net.sf.kerner.utils.collections.list.ListTransformer;
import net.sf.kerner.utils.transformer.Transformer;

class ListToRowTransformer<T> extends
		AbstractTransformingListFactory<List<T>, Row<T>> implements
		Transformer<List<T>, Row<T>>, ListTransformer<List<T>, Row<T>> {

	private final Set<? extends Object> idents;
	
	ListToRowTransformer(Set<? extends Object> idents) {
		super();
		this.idents = idents;
	}

	public Row<T> transform(List<T> element) {
		final RowImpl<T> result = new RowImpl<T>(element);
		result.setIdentifier(idents);
		return result;
	}

}
