package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.heap.Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class HeapTest {

    private final int SEED = 1000;
    private final Random rnd = new Random(SEED);
    private Heap<Double> heap;

    @Before
    public void setUp() {
        heap = new Heap<>();
    }

    @Test(expected = IllegalStateException.class)
    public void should_throwAnException_when_tryToPopFromEmptyHeap() {
        //when
        heap.pop();

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_attemptingToPutNull() {
        //when
        heap.put(null);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_heapifyStartIndexAndEndIndexAreNegative() {
        //when
        heap.heapify(-1, -3);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_tryingToGetMinimumElementFromEmptyHeap() {
        //when
        List<Double> doubles = new ArrayList<>();
        heap = new Heap<>(doubles);
        heap.getMinimumElement();

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_startIdAndEndIdAreGreaterThanHeapSize() {
        //when
        heap.heapify(5, 6);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_EndIdIsGreaterThanHeapSize() {
        //when
        heap.heapify(0, 5);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_startIdIsGreaterThanEndId() {
        //when
        heap.heapify(1, 0);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_oneElementInTheListIsNull() {
        //given
        List<Integer> list = Arrays.asList(1, 2, 3, 4, null, 5);

        //when
        Heap<Integer> integerHeap = new Heap<>(list);
        integerHeap.build();

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_twoElementsAreNull() {
        //given
        List<Integer> list = Arrays.asList(1, 2, null, 4, null, 5);

        //when
        Heap<Integer> integerHeap = new Heap<>(list);
        integerHeap.build();

        //then
        assert false;
    }

    @Test
    public void should_buildCorrectHeap_when_initialListIsEmpty() {
        //when
        heap.build();

        //then
        int expectedSize = 0;
        assertEquals(expectedSize, heap.getHeapSize());
    }

    @Test
    public void should_correctlyPutOneElementToHeap_when_puttingOneDouble() {
        //when
        heap.put(1.0);

        //then
        int expectedSize = 1;
        assertEquals(expectedSize, heap.getHeapSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_listIsNull() {
        //when
        Heap<Double> doubleHeap = new Heap<>(null);
        doubleHeap.build();

        //then
        assert false;
    }

    @Test
    public void should_buildCorrectHeap_when_listHasNoElements() {
        //when
        Heap<Double> doubleHeap = new Heap<>();
        doubleHeap.build();

        //then
        assert true;
    }

    @Test
    public void should_buildCorrectHeap_when_listHasOneElement() {
        //given
        List<Double> list = Collections.singletonList(1.0);

        //when
        Heap<Double> doubleHeap = new Heap<>(list);

        //then
        Double expectedMaxValue = 1.0;
        assertEquals(expectedMaxValue, doubleHeap.getMinimumElement());
    }

    @Test
    public void should_buildCorrectHeap_when_listHasTwoElements() {
        //given
        List<Double> list = Arrays.asList(1.0, 2.0);

        //when
        Heap<Double> doubleHeap = new Heap<>(list);

        //then
        Double expectedMaxValue = 1.0;
        assertEquals(expectedMaxValue, doubleHeap.getMinimumElement());
    }

    @Test
    public void should_buildCorrectHeap_when_listHasTwoEqualElements() {
        //given
        List<Double> list = Arrays.asList(1.0, 2.0, 2.0, 1.0);

        //when
        Heap<Double> doubleHeap = new Heap<>(list);

        //then
        Double expectedMaxValue = 1.0;
        assertEquals(expectedMaxValue, doubleHeap.getMinimumElement());
    }

    @Test
    public void should_buildCorrectlyHeap_when_insertingTwoDoubleElements() {
        //when
        heap.put(2.0);
        heap.put(1.0);

        //then
        Double expectedMaxValue = 1.0;
        int expectedSize = 2;

        assertEquals(expectedMaxValue, heap.getMinimumElement());
        assertEquals(expectedSize, heap.getHeapSize());
    }

    @Test
    public void should_buildCorrectlyHeap_when_insertingThreeDoubleNegativeElements() {
        //when
        heap.put(-5.0);
        heap.put(-3.0);
        heap.put(-2.0);

        //then
        Double expectedMaxValue = -5.0;
        int expectedSize = 3;

        assertEquals(expectedMaxValue, heap.getMinimumElement());
        assertEquals(expectedSize, heap.getHeapSize());
    }

    @Test
    public void should_maintainHeapStructure_when_poppingThreeElements() {
        //when
        heap.put(5.0);
        heap.put(3.0);
        heap.put(2.0);
        heap.put(10.0);
        heap.put(15.0);

        heap.pop();
        heap.pop();
        heap.pop();

        //then
        int expectedSize = 2;
        Double expectedMaxValue = 10.0;

        assertEquals(expectedMaxValue, heap.getMinimumElement());
        assertEquals(expectedSize, heap.getHeapSize());
    }

    @Test
    public void should_maintainHeapStructure_when_puttingAndPoppingMixedElements() {
        //when
        heap.put(-2.0);
        heap.put(3.0);
        heap.put(-15.0);

        heap.pop();

        //then
        int expectedSize = 2;
        Double expectedMaxValue = -2.0;

        assertEquals(expectedMaxValue, heap.getMinimumElement());
        assertEquals(expectedSize, heap.getHeapSize());
    }

    @Test
    public void should_popMaxElement_when_insertingManyElements() {
        //given
        int numberOfElements = 100_000;

        //when
        Double[] nums = new Double[numberOfElements];

        for (int i = 0; i < nums.length; i++) {
            double generatedValue = rnd.nextDouble();
            nums[i] = generatedValue;
            heap.put(generatedValue);
        }

        Arrays.sort(nums);

        //then
        int expectedSize = 100_000;
        Double expectedMaxValue = nums[0];

        assertEquals(expectedMaxValue, heap.getMinimumElement());
        assertEquals(expectedSize, heap.getHeapSize());
    }

    @Test
    public void should_correctlyDeleteAndAddElements_whenInsertingManyElementsAndThenDeleting() {
        //given
        int numberOfElements = 100_000;

        //when
        List<Double> nums = new ArrayList<>();

        for (int i = 0; i < numberOfElements; i++) {
            double generatedValue = rnd.nextDouble();
            nums.add(generatedValue);
        }

        Heap<Double> doubleHeap = new Heap<>(nums);

        for (int i = 0; i < numberOfElements; i++) {
            doubleHeap.pop();
        }

        //then
        int expectedSize = 0;

        assertEquals(expectedSize, doubleHeap.getHeapSize());
    }

    @Test
    public void should_haveHeapStructure_when_insertingStringValues() {
        //given
        Heap<String> stringHeap = new Heap<>();

        //when
        stringHeap.put("Ala");
        stringHeap.put("Jan");
        stringHeap.put("Adrian");
        stringHeap.put("Pawel");

        //then
        int expectedSize = 4;
        String expectedMaxString = "Adrian";

        assertEquals(expectedMaxString, stringHeap.getMinimumElement());
        assertEquals(expectedSize, stringHeap.getHeapSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_startIdIsGreaterThan() {
        //when
        int startId = 2;
        heap.heapifyUp(startId);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwAnException_when_startIdIsNegative() {
        //when
        int startId = -2;
        heap.heapifyUp(startId);

        //then
        assert false;
    }

}