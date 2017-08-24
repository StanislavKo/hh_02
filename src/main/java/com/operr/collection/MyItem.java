package com.operr.collection;

public class MyItem<T> {

	private T value;
	private MyItem<T> next;
	private MyItem<T> prev;

	public MyItem(T value, MyItem<T> prev, MyItem<T> next) {
		super();
		this.value = value;
		this.prev = prev;
		this.next = next;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public MyItem<T> getNext() {
		return next;
	}

	public void setNext(MyItem<T> next) {
		this.next = next;
	}

	public MyItem<T> getPrev() {
		return prev;
	}

	public void setPrev(MyItem<T> prev) {
		this.prev = prev;
	}

}
