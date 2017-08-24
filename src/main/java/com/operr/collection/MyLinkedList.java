package com.operr.collection;

import java.util.List;

public class MyLinkedList<T extends Comparable> implements MyList<T> {

	private MyItem<T> head = null;
	private MyItem<T> tail = null;
	
	@Override
	public void append(T value) {
		if (head == null) { 
			head = new MyItem<T>(value, null, null);
			tail = head;
		} else {
			MyItem<T> newItem = new MyItem<T>(value, tail, null);
			tail.setNext(newItem);
			tail = newItem;
		}
	}

	@Override
	public T get(Integer index) throws IndexOutOfBoundsException {
		if (head == null) {
			throw new IndexOutOfBoundsException();
		}
		MyItem<T> item = head;
		for (int i = 0; i < index; i++) {
			if (item == tail) {
				throw new IndexOutOfBoundsException();
			}
			item = item.getNext();
		}
		return item.getValue();
	}

	@Override
	public Integer size() {
		Integer size = 0;
		MyItem<T> item = head;
		while (item != null) {
			size++;
			item = item.getNext();
		}
		return size;
	}

	@Override
	public void filter(MyListFilterAction action) {
		if (head == null) {
			return;
		}
		MyItem<T> item = head;
		do {
			if (action.isRemained(item.getValue())) {
				item = item.getNext();
			} else {
				MyItem<T> curItem = item;
				if (curItem.getPrev() == null && curItem.getNext() == null) {
					// single item in the list
					head = null;
					tail = null;
					return;
				} else if (curItem.getPrev() == null) {
					// 1st item of the list
					head = item.getNext();
				} else if (curItem.getNext() == null) {
					// last item of the list
					tail = item.getPrev();
					if (tail != null) {
						tail.setNext(null);
					}
					return;
				}
				item = item.getNext();
				item.setPrev(curItem.getPrev());
				if (curItem.getPrev() != null) {
					curItem.getPrev().setNext(item);
				}
			}
		} while (item != null);
	}

	public void removeTail() {
		if (tail == null) {
			return;
		}
		if (tail.getPrev() == null) {
			head = null;
			tail = null;
			return;
		}
		tail.getPrev().setNext(null);
		tail = tail.getPrev();
	}
	
	public void removeGt(T target) {
		filter(new MyListFilterAction<T>() {
			@Override
			public Boolean isRemained(T value) {
				return value.compareTo(target) <= 0;
			}
		});
	}
	
}
