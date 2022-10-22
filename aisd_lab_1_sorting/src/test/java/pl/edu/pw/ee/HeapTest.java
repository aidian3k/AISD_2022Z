package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class HeapTest {

    private static final Random rnd = new Random();
    private Heap<Double> heap;

    @Before
    public void setUp() {
        heap = new Heap<>();
    }

    @Test(expected = IllegalStateException.class)
    public void should_throwAnException_when_tryToPopFromEmptyHeap() {
        //given

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

    @Test
    public void should_buildCorrectlyHeap_when_insertingTwoDoubleElements() {
        //when
        heap.put(2.0);
        heap.put(1.0);

        //then
        Double expectedMaxValue = 2.0;
        int expectedSize = 2;

        assertEquals(expectedMaxValue, heap.getMaximumElement());
        assertEquals(expectedSize, heap.getHeapSize());
    }

    @Test
    public void should_buildCorrectlyHeap_when_insertingThreeDoubleNegativeElements() {
        //when
        heap.put(-5.0);
        heap.put(-3.0);
        heap.put(-2.0);

        //then
        Double expectedMaxValue = -2.0;
        int expectedSize = 3;

        assertEquals(expectedMaxValue, heap.getMaximumElement());
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
        Double expectedMaxValue = 3.0;

        assertEquals(expectedMaxValue, heap.getMaximumElement());
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

        assertEquals(expectedMaxValue, heap.getMaximumElement());
        assertEquals(expectedSize, heap.getHeapSize());
    }

    @Test
    public void should_popMaxElement_when_insertingManyElements() {
        //given
        final int SEED = 1000;
        int numberOfElements = 100_000;

        Double[] nums = new Double[numberOfElements];

        for (int i = 0; i < nums.length; i++) {
            double generatedValue = rnd.nextDouble();
            nums[i] = generatedValue;
            heap.put(generatedValue);
        }

        Arrays.sort(nums);

        //then
        int expectedSize = 100_000;
        Double expectedMaxValue = nums[nums.length - 1];

        assertEquals(expectedMaxValue, heap.getMaximumElement());
        assertEquals(expectedSize, heap.getHeapSize());
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
        String expectedMaxString = "Pawel";

        assertEquals(expectedMaxString, stringHeap.getMaximumElement());
        assertEquals(expectedSize, stringHeap.getHeapSize());
    }

}