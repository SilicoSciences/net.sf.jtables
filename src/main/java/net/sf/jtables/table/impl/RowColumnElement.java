package net.sf.jtables.table.impl;

public class RowColumnElement<T> {
	
	private final String identifier;
	
	private volatile T value;
	
	public RowColumnElement(String identifier) {
		this.identifier = identifier;
	}
	
	public RowColumnElement(String identifier, T value) {
		this.identifier = identifier;
		this.value = value;
	}
	
	public RowColumnElement() {
		this(null);
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RowColumnElement))
			return false;
		RowColumnElement other = (RowColumnElement) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return getValue().toString();
	}

}
