package com.operr.collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LinkedListTest {

	private static final Logger log = Logger.getLogger(LinkedListTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.debug("LinkedListTest.setUpBeforeClass()");
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.DEBUG);
		rootLogger.addAppender(new ConsoleAppender(new PatternLayout("%d{ABSOLUTE} %5p %t %c{1}:%M:%L - %m%n")));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		log.debug("LinkedListTest.tearDownAfterClass()");
	}

	@Before
	public void setUp() throws Exception {
		log.debug("\r\n\r\nLinkedListTest.setUp()");
	}

	@After
	public void tearDown() throws Exception {
		log.debug("LinkedListTest.tearDown()");
	}

	@Test
	public void test001Add() throws Exception {
		MyLinkedList<Integer> list = new MyLinkedList();
		list.append(1);
		assert(list.size() == 1);
		list.append(2);
		assert(list.size() == 2);
	}

	@Test
	public void test002Get() throws Exception {
		MyLinkedList<Integer> list = new MyLinkedList();
		list.append(1);
		assert(list.get(0) == 1);
		list.append(2);
		assert(list.get(1) == 2);
	}

	@Test
	public void test003Filter01() throws Exception {
		MyLinkedList<Integer> list = new MyLinkedList();
		list.append(1);
		list.append(2);
		list.filter(new MyListFilterAction<Integer>() {
			@Override
			public Boolean isRemained(Integer value) {
				return value <= 1;
			}
		});
		log.info("test003Filter() [size:" + list.size() + "]");
		assert(list.size() == 1);
		assert(list.get(0) == 1);

		list = new MyLinkedList();
		list.append(1);
		list.append(2);
		list.append(2);
		list.append(1);
		list.filter(new MyListFilterAction<Integer>() {
			@Override
			public Boolean isRemained(Integer value) {
				return value <= 1;
			}
		});
		assert(list.size() == 2);
		assert(list.get(0) == 1);
		assert(list.get(1) == 1);
	}

	@Test
	public void test004RemoveTail() throws Exception {
		MyLinkedList<Integer> list = new MyLinkedList();
		list.append(1);
		list.append(2);
		list.removeTail();
		assert(list.size() == 1);
		assert(list.get(0) == 1);
	}

	@Test
	public void test005RemoveGt() throws Exception {
		MyLinkedList<Integer> list = new MyLinkedList();
		list.append(1);
		list.append(2);
		list.append(3);
		list.removeGt(2);
		log.info("test005RemoveGt() [size:" + list.size() + "]");
		for (int i = 0; i < list.size(); i++) {
			log.info("test005RemoveGt() [" + i + ":" + list.get(i) + "]");
		}
		assert(list.size() == 2);
		assert(list.get(0) == 1);
		assert(list.get(1) == 2);
	}

}
