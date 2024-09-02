package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class TreeSet<T> implements Set<T> {
    private static class Node<T> {
        T obj;
        Node<T> parent;
        Node<T> left;
        Node<T> right;

        Node(T obj) {
            this.obj = obj;
        }
    }

    private class TreeSetIterator implements Iterator<T> {
        Node<T> current = findMin(root);

        private Node<T> findMin(Node <T> node) {
            while(node.left != null) {
                node = node.left;
            }
            return node;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the tree");
        }
        Node <T> nextNode = current;
        if(current.right != null) {
            current = findMin(current.right);
        } else {
            Node <T> parent = current.parent;
            while (parent != null && current == parent.right) {
                current = parent;
                parent = parent.parent;
            }
            current = parent;
        }
        return nextNode.obj;
        }

        public boolean remove(T obj) {
            boolean res = false;
            Node <T> node = getNode(obj); 
            if(node.left == null || node.right == null) {
                replaceNodeInParent(node, null);
                size--;
                res = true;
            } else if (node.left == null) {
                replaceNodeInParent(node, node.right);
                size--;
                res = true;
            } else if (node.right == null) {
                replaceNodeInParent(node, node.left);
                size--;
                res = true;
            } else {
                Node <T> nodeToReplace = findMin(node.right);
                replaceNodeInParent(node, nodeToReplace);
                size--;
                res = true;
            }
            return res;
        }

        private void replaceNodeInParent(Node<T> nodeToRemove, Node<T> nodeToReplace) {
            Node <T> parent = nodeToRemove.parent;
            String res = comparator.compare(nodeToRemove.obj, nodeToRemove.parent.obj) > 0 ? "right" : "left";
            if (parent == null) {
                root = nodeToReplace;
            } else if (res == "left") {
                parent.left = nodeToReplace;
            } else {
                parent.right = nodeToReplace;
            }
            if (nodeToReplace != null) {
                nodeToReplace.parent = nodeToRemove.parent;
            }
            nodeToRemove = null;
        }
    }

    private Node<T> root;
    private Comparator<T> comparator;
    int size;
    public TreeSet(Comparator<T> comparator) {
        this.comparator = comparator;
    } 
    
    public TreeSet() {
        this((Comparator<T>)Comparator.naturalOrder());
    }
    @Override
    public boolean add(T obj) {
        boolean res = false;
        if (!contains(obj)) {
            res = true;
            Node<T> node = new Node<>(obj);
            if(root == null) {
                addRoot(node);
            } else {
                addAfterParent(node);
            }
            size++;

        }
        return res;
    }

    private void addAfterParent(Node<T> node) {
        Node<T> parent = getParent(node.obj);
        if(comparator.compare(node.obj, parent.obj) > 0) {
            parent.right = node;
        } else {
            parent.left = node;
        }
        node.parent = parent;
    }

    private void addRoot(Node<T> node) {
        root = node;
    }

    @Override
    public boolean remove(T pattern) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    @Override
    public boolean contains(T pattern) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeSetIterator();
    }

    @Override
    public T get(Object pattern) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
    private Node<T> getParentOrNode(T pattern) {
        Node<T> current = root;
        Node<T> parent = null;
        int compRes = 0;
        while(current != null && (compRes = comparator.compare(pattern, current.obj)) != 0) {
            parent = current;
            current = compRes > 0 ? current.right : current.left;
        }
        return current == null ? parent : current;
    }
    private Node<T> getNode(T pattern) {
        Node<T> res = getParentOrNode(pattern);
        if(res != null) {
            int compRes = comparator.compare(pattern, res.obj);
            res = compRes == 0 ? res : null;
        }
        
        return res;

    }
    private Node<T> getParent(T pattern) {
        Node<T> res = getParentOrNode(pattern);
        int compRes = comparator.compare(pattern, res.obj);
        return compRes == 0 ? null : res;

    }

}