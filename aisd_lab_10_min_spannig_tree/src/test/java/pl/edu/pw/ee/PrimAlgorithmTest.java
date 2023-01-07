package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

public class PrimAlgorithmTest {

    private PrimAlgorithm primAlgorithm;
    private KruskalAlgorithm kruskalAlgorithm;

    @Before
    public void setUp() {
        this.primAlgorithm = new PrimAlgorithm();
        this.kruskalAlgorithm = new KruskalAlgorithm();
    }

    @Test
    public void test() {
        System.out.println(primAlgorithm.findMST("src/test/resources/lesssonSampleGraph.txt"));
        System.out.println(kruskalAlgorithm.findMST("src/test/resources/lesssonSampleGraph.txt"));
    }

    @Test
    public void test2() {
        System.out.println(primAlgorithm.findMST("src/test/resources/sampleGraph.txt"));
        System.out.println(kruskalAlgorithm.findMST("src/test/resources/sampleGraph.txt"));
    }
}
