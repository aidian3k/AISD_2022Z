package pl.edu.pw.ee.graph;

import static java.lang.String.format;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void shouldCorrectlyReadSmallBasicFile() {
        // given
        String filename = "correct_small_data.txt";
        String pathToFile = getFilePath(filename);

        // when
        Graph graph = new Graph(pathToFile, true);
        String graphAsStr = graph.toString();
        
        //then
        String expectedText = "Edge[src: Node: B, dest: Node: C, weight: 1.0]\n" +
"Edge[src: Node: C, dest: Node: D, weight: 1.0]\n" +
"Edge[src: Node: A, dest: Node: B, weight: 3.0]\n" +
"Edge[src: Node: A, dest: Node: C, weight: 5.0]\n" +
"Edge[src: Node: A, dest: Node: D, weight: 7.0]\n" +
"Edge[src: Node: D, dest: Node: E, weight: 7.0]\n";
        
        assertEquals(expectedText, graphAsStr);
    }
    
    @Test
    public void should_correctlyReadSingleEdge() {
        // given
        String filename = "single_edge.txt";
        String pathToFile = getFilePath(filename);

        // when
        Graph graph = new Graph(pathToFile, true);
        String graphAsStr = graph.toString();
        
        //then
        
        String expectedText = "Edge[src: Node: A, dest: Node: B, weight: 4.0]\n";
        assertEquals(expectedText, graphAsStr);
    }
    
    @Test
    public void Correct_repeated_small_data() {
        // given
        String filename = "correct_repeated_small_data.txt";
        String pathToFile = getFilePath(filename);

        // when
        Graph graph = new Graph(pathToFile, true);
        String graphAsStr = graph.toString();
        
        //then
        
        String expectedText = "Edge[src: Node: B, dest: Node: C, weight: 1.0]\n" +
"Edge[src: Node: C, dest: Node: D, weight: 1.0]\n" +
"Edge[src: Node: E, dest: Node: D, weight: 1.0]\n" +
"Edge[src: Node: A, dest: Node: B, weight: 3.0]\n" +
"Edge[src: Node: A, dest: Node: C, weight: 5.0]\n" +
"Edge[src: Node: A, dest: Node: D, weight: 7.0]\n";
        assertEquals(expectedText, graphAsStr);
    }
    
    @Test
    public void should_correctlyReadExtended_small_data() {
        // given
        String filename = "extended_small_data.txt";
        String pathToFile = getFilePath(filename);

        // when
        Graph graph = new Graph(pathToFile, false);
        String graphAsStr = graph.toString();
        
        //then
        
        String expectedText = "Edge[src: Node: A, dest: Node: C, weight: 3.1622776601683795]\n" +
"Edge[src: Node: C, dest: Node: D, weight: 6.082762530298219]\n" +
"Edge[src: Node: B, dest: Node: C, weight: 6.324555320336759]\n" +
"Edge[src: Node: A, dest: Node: D, weight: 6.4031242374328485]\n" +
"Edge[src: Node: A, dest: Node: B, weight: 7.0710678118654755]\n" +
"Edge[src: Node: D, dest: Node: E, weight: 8.246211251235321]\n";
        assertEquals(expectedText, graphAsStr);
    }
    
    @Test
    public void should_correctlyReadExtendedRepeatedSmallData() {
        // given
        String filename = "extended_repeated_small_data.txt";
        String pathToFile = getFilePath(filename);

        // when
        Graph graph = new Graph(pathToFile, false);
        String graphAsStr = graph.toString();
        
        //when
        String expectedText = "Edge[src: Node: C, dest: Node: D, weight: 2.23606797749979]\n" +
"Edge[src: Node: A, dest: Node: C, weight: 3.1622776601683795]\n" +
"Edge[src: Node: D, dest: Node: E, weight: 3.1622776601683795]\n" +
"Edge[src: Node: A, dest: Node: D, weight: 5.0]\n" +
"Edge[src: Node: B, dest: Node: C, weight: 6.324555320336759]\n" +
"Edge[src: Node: A, dest: Node: B, weight: 7.0710678118654755]\n";
        
        assertEquals(expectedText, graphAsStr);
    }
    
    @Test
    public void mix_extended_small_data() {
        // given
        String filename = "mix_extended_small_data.txt";
        String pathToFile = getFilePath(filename);

        // when
        Graph graph = new Graph(pathToFile, false);
        String graphAsStr = graph.toString();
        
        
    }
    
    

    private String getFilePath(String filename) {
        ClassLoader clsLoader = getClass().getClassLoader();

        try {
            Path path = Paths.get(clsLoader.getResource(filename).toURI());

            return path.toFile().getAbsolutePath();

        } catch (URISyntaxException e) {
            throw new RuntimeException(format("Cannot read file from filename: %s.", filename), e);
        }
    }

}
