package pl.edu.pw.ee;

import org.junit.Test;
import pl.edu.pw.ee.huffmanCoding.Node;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeTest {

    private Node node;

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_signIsNull() {
        //when
        Character sign = null;
        int frequency = 0;

        node = new Node(sign, frequency);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_frequencyIsLessThanOne() {
        //when
        Character sign = 'a';
        int frequency = 0;

        node = new Node(sign, frequency);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_frequencyIsLessThanZero() {
        //when
        Character sign = 'a';
        int frequency = -3;

        node = new Node(sign, frequency);

        //then
        assert false;
    }

    @Test
    public void should_correctlyCreateNode_when_signAndFrequencyAreProper() {
        //when
        Character sign = 'a';
        int frequency = 2;

        node = new Node(sign, frequency);

        //then
        assert true;
    }

    @Test
    public void should_correctlyCreateNode_when_callingEmptyConstructor() {
        //when
        node = new Node();

        //then
        assert true;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_rightNodeIsNull() {
        //when
        char sign = 'a';
        Node left = new Node();
        Node right = null;

        node = new Node(sign, left, right);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_leftNodeIsNull() {
        //when
        char sign = 'a';
        Node left = null;
        Node right = new Node();

        node = new Node(sign, left, right);

        //then
        assert false;
    }

    @Test
    public void should_correctlyCreateInternalNode_when_constructorDataIsProper() {
        //when
        char sign = 'a';
        Node left = new Node();
        Node right = new Node();

        node = new Node(sign, left, right);

        //then
        assert true;
    }

    @Test
    public void should_correctlyRecognizeLeafNode_when_nodeIsLeaf() {
        //when
        char sign = 'a';
        int frequency = 5;

        node = new Node(sign, frequency);

        //then
        boolean expectedIsLeaf = true;
        assertEquals(expectedIsLeaf, node.isLeaf());
    }

    @Test
    public void should_correctlySortNodesByFrequency_when_allNodesAreCorrectlyCreated() {
        //given
        int numberOfNodes = 3;

        //when
        Node[] nodes = new Node[numberOfNodes];

        for(int i = numberOfNodes - 1; i >= 0; --i) {
            int currentNumber = i + 97;
            char currentChar = (char) currentNumber;

            nodes[i] = new Node(currentChar, currentNumber);
        }

        Arrays.sort(nodes);

        //then
        char expectedMinimumFrequency = 97;
        assertEquals(expectedMinimumFrequency, nodes[0].getFrequency());
    }
}