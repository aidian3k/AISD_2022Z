package pl.edu.pw.ee;

import org.junit.Test;

public class LongestCommonSubsequenceTest {
    @Test
    public void test() {
        String text1 = "często_z_odkrywaniem";
        String text2 = "rzeczy_nie_trzeba\n_się_spieszyć";
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(text1, text2);
        longestCommonSubsequence.prepareDisplayMatrix();
        longestCommonSubsequence.displayArr();
    }
    
}
