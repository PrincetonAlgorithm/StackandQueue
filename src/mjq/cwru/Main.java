package mjq.cwru;

import java.lang.reflect.Array;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
    }

    public class LinkedStackOfStrings {

        public class Node {
            String item;
            Node next;
        }

        private Node first = null;

        public void push(String item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
        }

        public String pop() {
            String item = first.item;
            first = first.next;
            return item;
        }

        public boolean isEmpty() {
            return first == null;
        }

    }

    //basic one
    public class FixedCapacityStacksOfStrings {

        private String[] s;
        private int N = 0;

        public FixedCapacityStacksOfStrings(int capacity) {
            s = new String[capacity];
        }

        public void push(String item) {
            s[N++] = item;
        }

        // making s[n] to be null can let gc reclaim memory
        //if overflow throw exception
        public String pop() {
            String item = s[--N];
            s[N] = null;
            return item;
        }

        public boolean isEmpty() {
            return N == 0;
        }
    }

    //can increase and decrease capacity automatically
    public class ResizingArrayStacksOfString {
        private String[] s;
        private int N = 0;

        public ResizingArrayStacksOfString(int capacity) {
            s = new String[capacity];
        }

        public void push(String item) {
            if (N == s.length)
                resize(2 * s.length);
            s[N++] = item;
        }

        //if overflow throw exception
        public String pop() {
            String item = s[--N];
            s[N] = null;
            if (N > 0 && N == s.length / 4)
                resize(s.length / 2);
            return item;
        }

        public boolean isEmpty() {
            return N == 0;
        }

        private void resize(int capacity) {
            String[] copy = new String[capacity];
            for (int i = 0; i < N; i++)
                copy[i] = s[i];
            s = copy;
        }
    }

    //Queues
    public class LinkedQueueOfStrings {

        private Node first, last;

        public class Node {
            String item;
            Node next;
        }

        public void enqueue(String item) {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty())
                first = last;
            else
                oldLast.next = last;
        }

        //if overflow throw exception
        public String dequeue() {
            String item = first.item;
            first = first.next;
            if (isEmpty())
                last = null;
            return item;
        }

        public boolean isEmpty() {
            return first == null;
        }

    }

    //Linked Stack with generics implements Iterable
    public class LinkedStack<Item> implements Iterable {

        @Override
        public Iterator iterator() {
            return new ListIterator();
        }

        private class ListIterator implements Iterator {

            private Node current = first;

            @Override
            public boolean hasNext() {
                return current == null;
            }

            @Override
            public Object next() {
                Item item = first.item;
                first = first.next;
                return item;
            }
        }

        public class Node {
            Item item;
            Node next;
        }

        private Node first = null;

        public void push(Item item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
        }

        public Item pop() {
            Item item = first.item;
            first = first.next;
            return item;
        }

        public boolean isEmpty() {
            return first == null;
        }

    }

    //Array Stack implements Iterable
    public class ArrayStack implements Iterable {

        @Override
        public Iterator iterator() {
            return new ReverseArray();
        }

        public class ReverseArray implements Iterator {

            private int i = N;

            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public Object next() {
                return s[--i];
            }
        }

        private String[] s;
        private int N = 0;

        public ArrayStack(int capacity) {
            s = new String[capacity];
        }

        public void push(String item) {
            if (N == s.length)
                resize(2 * s.length);
            s[N++] = item;
        }

        //if overflow throw exception
        public String pop() {
            String item = s[--N];
            s[N] = null;
            if (N > 0 && N == s.length / 4)
                resize(s.length / 2);
            return item;
        }

        public boolean isEmpty() {
            return N == 0;
        }

        private void resize(int capacity) {
            String[] copy = new String[capacity];
            for (int i = 0; i < N; i++)
                copy[i] = s[i];
            s = copy;
        }

    }

    public class Bag<Item> implements Iterable<Item> {
        int N;

        public Bag() {
        }

        public void add(Item x) {
        }

        public int size() {
            return N;
        }

        @Override
        public Iterator<Item> iterator() {
            return null;
        }
    }

}
