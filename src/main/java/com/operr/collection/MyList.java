package com.operr.collection;

public interface MyList<T extends Comparable> {

	void append(T value);
	T get(Integer index) throws IndexOutOfBoundsException;
	Integer size();
	void filter(MyListFilterAction action);
	
}
