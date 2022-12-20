package pl.edu.pw.ee;

import org.junit.Test;
import static org.junit.Assert.*;


public class LongestCommonSubsequenceTest {
    
    @Test
    public void test() {
        String text1 = "PREZENTY";
        String text2 = "RESZTA";
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(text1, text2);
        System.out.println(longestCommonSubsequence.findLCS());
    }
    
}
