package org.skcorg.datastructure.custom;

import org.skcorg.datastructure.custom.exception.InvalidPositionException;

/*
 * Linkedlist is linear data structure with elements stores in non contiguous memory location
 * Advantages over arrays
 *	1) Dynamic size
 *	2) Ease of insertion/deletion
 * Drawbacks:
 * 	1) Random access is not allowed. We have to access elements sequentially starting from 
 * 		the first node. So we cannot do binary search with linked lists.
 */
public class LinkedList {
	private Node _head;
	public final Insert insert;
	public final Delete delete;
	public final Size size;
	public final Reverse reverse;
	public final LoopOperations loopOperations;

	public LinkedList() {
		super();
		insert = new Insert();
		delete = new Delete();
		size = new Size();
		reverse = new Reverse();
		loopOperations = new LoopOperations();

	}

	public class Insert {
		/*
		 * Insertion [Start]
		 * create a new node
		 * set newNode->next=head set
		 * head=newNode
		 */
		public void AtStart(Integer data) {
			Node _node = new Node(data);
			_node.setNext(_head);
			_head = _node;
		}

		/*
		 * Insertion [end]
		 * create a new node
		 * set newNode->next=null
		 * if empty list
		 * then head=newNode
		 * else
		 * traverse through the list and find last node
		 * set lastNode->next=newNode
		 */
		public void AtEnd(Integer data) {
			Node _node = new Node(data);
			_node.setNext(null);
			if (_head == null) {
				_head = _node;
			} else {
				Node _current = _head;
				while (_current.getNext() != null) {
					_current = _current.getNext();
				}
				_current.setNext(_node);
			}

		}

		/*
		 * Insertion [position]
		 * if position>1 and empty list
		 * throw InvalidPositionException
		 * else
		 * traverse through the list and find position node
		 * create a new node
		 * newNode->next=node->next
		 * node->next=newNode
		 */
		public void AtPosition(Integer data, int position)
				throws InvalidPositionException {
			if (position > 1 && _head == null) {
				throw new InvalidPositionException("Cannot insert at postion "
						+ position);
			} else {
				int _count = 1;
				Node _current = _head, _prev = _head;
				while (_current.getNext() != null && _count < position) {
					_prev = _current;
					_current = _current.getNext();
					_count++;
				}
				Node _node = new Node(data);
				_node.setNext(_current);
				_prev.setNext(_node);
			}
		}
	}

	public class Delete {
		/*
		 * Deletion [Start]
		 * if head!=null
		 * head=head->next
		 */
		public void AtStart() {
			if (_head != null) {
				_head = _head.getNext();
			}
		}

		/*
		 * Deletion [end]
		 * traverse through the list and second lastNode
		 * set secondlastNode->null
		 */
		public void AtEnd(Integer data) {
			Node _current = _head, _prev = _head;
			while (_current.getNext() != null) {
				_prev = _current;
				_current = _current.getNext();
			}
			_prev.setNext(null);
		}

		/*
		 * Deletion [position]
		 * if position>1 and empty list
		 * throw InvalidPositionException
		 * else
		 * traverse through the list and find position node
		 * create a new node
		 * newNode->next=node->next
		 * node->next=newNode
		 */
		public void AtPosition(Integer data, int position)
				throws InvalidPositionException {
			if (position > 1 && _head == null) {
				throw new InvalidPositionException("Cannot insert at postion "
						+ position);
			} else {
				int _count = 1;
				Node _current = _head, _prev = _head;
				while (_current.getNext() != null && _count < position) {
					_prev = _current;
					_current = _current.getNext();
					_count++;
				}
				_prev.setNext(_current.getNext());
			}
		}

		public void deleteNnodesAfterMnodes(final int m, final int n) {
			Node _current = _head;
			while (_current != null) {
				// Skip M nodes
				for (int count = 1; count < m && _current != null; count++)
					_current = _current.getNext();

				// If we reached end of list, then return
				if (_current == null)
					return;

				// Start from link node and remove N nodes
				Node _temp = _current.getNext();
				for (int count = 1; count <= n && _temp != null; count++) {
					_temp = _temp.getNext();
				}
				_current.setNext(_temp);

				// Set current pointer for link iteration
				_current = _temp;
			}
		}
	}

	public class Size {
		public int iterative() {
			Node _current = _head;
			int count = 0;
			while (_current != null) {
				count++;
				_current = _current.getNext();
			}
			return count;
		}

		public int recursive() {
			return recursive(_head);
		}

		private int recursive(Node _node) {
			if (_node == null) {
				return 1;
			} else {
				return 1 + recursive(_node.getNext());
			}
		}
	}

	public class Search {
		public boolean iterative(Integer data) {
			Node _current = _head;
			while (_current != null && _current.getData() != data) {
				_current = _current.getNext();
			}
			return _current == null ? false : true;
		}

		public boolean recursive(Integer data) {
			return recursive(_head, data);
		}

		private boolean recursive(Node _node, Integer data) {
			if (_node == null) {
				return false;
			} else {
				return _node.getData().equals(data) ? true : recursive(
						_node.getNext(), data);
			}
		}
	}

	public class Reverse {
		public void iterative() {
			Node _current = _head, _prev = null, _next = _head;
			while (_current != null) {
				_next = _current.getNext();
				_current.setNext(_prev);
				_prev = _current;
				_current = _next;
			}
			_head = _prev;
		}

		public void recursive() {
			_head = recursive(_head);
		}

		private Node recursive(Node _node) {
			if (_node.getNext() == null)
				return _node;
			Node _newNode = recursive(_node.getNext());
			_node.getNext().setNext(_node);
			_node.setNext(null);
			return _newNode;
		}

		public void reverseAltKnodes(final int k) {
			_head = reverseAltKnodes(_head, k, true);
		}

		private Node reverseAltKnodes(final Node _node, final int k,
				final boolean reverse) {
			if (_node == null)
				return null;
			Node _next = _node, _prev = null, _current = _node;
			int count = 0;
			while (_current != null && count < k) {
				_next = _current.getNext();
				if (reverse) {
					_current.setNext(_prev);
				}
				_prev = _current;
				_current = _next;
				count++;
			}
			if (reverse) {
				_node.setNext(reverseAltKnodes(_current, k, !reverse));
				return _prev;
			} else {
				_prev.setNext(reverseAltKnodes(_current, k, !reverse));
				return _node;
			}

		}

		public void reverseEveryKNodes(final int k) {
			_head = reverseEveryKNodes(_head, k);
		}

		private Node reverseEveryKNodes(final Node _node, final int k) {
			Node _next = _node, _prev = null, _current = _node;
			int count = 0;
			while (_node != null && count < k) {
				_next = _current.getNext();
				_current.setNext(_prev);
				_prev = _current;
				_current = _next;
				count++;
			}
			if (_next != null) {
				_node.setNext(reverseEveryKNodes(_next, k));
			}
			return _prev;
		}
	}

	public class LoopOperations {
		public boolean hasLoop() {
			Node _slow = _head, _fast = _head;
			boolean isLooped = false;
			while (_fast != null && _fast.getNext() != null) {
				_slow = _slow.getNext();
				_fast = _fast.getNext().getNext();
				if (_slow == _fast) {
					isLooped = true;
					break;
				}
			}
			return isLooped;
		}

		public void removeLoop() {
			Node _slow = _head, _fast = _head;
			boolean isLooped = false;
			while (_fast != null && _fast.getNext() != null) {
				_slow = _slow.getNext();
				_fast = _fast.getNext().getNext();
				if (_slow == _fast) {
					isLooped = true;
					break;
				}
			}
			if (isLooped) {
				Node _prev = null, _tempHead = _head;
				while (_tempHead != _slow) {
					_prev = _slow;
					_slow = _slow.getNext();
					_tempHead = _tempHead.getNext();
				}
				if (_prev != null) {
					_prev.setNext(null);
				}
			}
		}
	}

	public class PairWiseSwap {
		public void iterative() {
			Node _current = _head;
			while (_current != null && _current.getNext() != null) {
				// swap
				Integer data = _current.getData();
				_current.setData(_current.getNext().getData());
				_current.getNext().setData(data);
				_current = _current.getNext().getNext();
			}
		}

		public void iterativeLinks() {
			if (_head == null || _head.getNext() == null)
				return;
			Node _current = _head.getNext(), _prev = _head;
			_head = _prev;
			while (true) {
				Node _next = _current.getNext();
				_current.setNext(_prev);
				if (_next == null || _next.getNext() == null) {
					break;
				}
				_prev.setNext(_next.getNext());
				_prev = _next;
				_current = _next.getNext();
			}
		}

		public void recursive() {
			recursive(_head);
		}

		private void recursive(Node _node) {
			if (_node != null && _node.getNext() != null) {
				// swap
				Integer data = _node.getData();
				_node.setData(_node.getNext().getData());
				_node.getNext().setData(data);
				recursive(_node.getNext().getNext());
			}

		}
	}

	public LinkedList clone() {
		final LinkedList clonedLinkedList = new LinkedList();
		Node _current = _head;
		while (_current != null) {
			clonedLinkedList.insert.AtEnd(_current.getData());
		}
		return clonedLinkedList;
	}

	public boolean isPalindrome() {
		final LinkedList clonedLinkedList = clone();
		clonedLinkedList.reverse.recursive();
		Node _listNode = _head, _cloneListNode = clonedLinkedList._head;
		while (_listNode != null && _cloneListNode != null) {
			if (_listNode.getData() != _cloneListNode.getData()) {
				return false;
			}
			_listNode = _listNode.getNext();
			_cloneListNode = _cloneListNode.getNext();
		}
		return true;
	}

	public Integer findMiddle() {
		Node _slow = _head, _fast = _head;
		while (_fast != null && _fast.getNext() != null) {
			_slow = _slow.getNext();
			_fast = _fast.getNext().getNext();
		}
		return _slow.getData();
	}

	public Integer findNthNodeFromEnd(int n) throws InvalidPositionException {
		Node _ref = _head, _main = _head;
		int count = 0;

		while (count < n) {
			if (_ref == null) {
				throw new InvalidPositionException(n
						+ "is greater than the no. of nodes in list");
			}
			_ref = _ref.getNext();
			count++;
		} /* End of while */

		while (_ref != null) {
			_main = _main.getNext();
			_ref = _ref.getNext();
		}
		return _main.getData();
	}

	public int countOccurance(Integer data) {
		Node _node = _head;
		int count = 0;
		while (_node != null) {
			if (_node.getData().equals(data)) {
				count++;
			}
			_node = _node.getNext();
		}
		return count;
	}

	public void mergeSort() {
		_head = mergeSort(_head);
	}

	private Node mergeSort(Node _node) {
		Node _current = _node;
		if (_current == null || _current.getNext() == null) {
			return _current;
		}
		Node _firstHalf = _current;
		Node _slow = _current;
		Node _fast = _current.getNext();
		while (_fast != null && _fast.getNext() != null) {
			_slow = _slow.getNext();
			_fast = _fast.getNext().getNext();
		}
		Node _secondHalf = _slow;
		return merge(mergeSort(_firstHalf), mergeSort(_secondHalf));
	}

	private Node merge(Node firstHalf, Node secondHalf) {
		final Node _head = new Node();
		Node _firstHalf = firstHalf, _secondHalf = secondHalf;
		Node _final = _head;
		while (_firstHalf != null && _secondHalf != null) {
			if (_firstHalf.getData() < _secondHalf.getData()) {
				_final.setNext(_firstHalf);
				_final = _firstHalf;
				_firstHalf = _firstHalf.getNext();
			} else {
				_final.setNext(_secondHalf);
				_final = _secondHalf;
				_secondHalf = _secondHalf.getNext();
			}
		}
		_final.setNext(_firstHalf == null ? _secondHalf : _firstHalf);
		return _head.getNext();
	}

	class Node {
		private Integer data;
		private Node next;

		public Node() {
			super();
		}

		public Node(Integer data) {
			super();
			this.data = data;
		}

		public Integer getData() {
			return data;
		}

		public void setData(Integer data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}
}
