import java.util.*;

// Yunho Cho
// TA: Ben Wang
// CSE 122 AF
// June 29 2023
// DNA
// This class is a dna. With given particular four nucleotides dna (A, C, G, T)
// , it calculates the most common nucleotide.
public class DNA {
    public static void main(String[] args) {
        String dna = "ATGCGCACTATGGTAG";
        String mostCommon = mostCommonNucleotide(dna);
        System.out.println(dna + " => " + mostCommon);
    }

    // Finds and returns the most commonly used nucleotide in DNA 
    // if more than one dna tie then it will return one dna by 
    // in alphabet order of [A, C, G, T].
    // no exception
    // Parameters:
    //      - dna: the squence of dna
    //  Returns:
    // - String: the most commonly used nucleotide in dna
    public static String mostCommonNucleotide(String dna) {    
        int[] count = countNucleotide(dna);
        int idx = findMax(count);
        if (idx == 0) {
            return "A";
        } else if (idx == 1) {
            return "C";
        } else if (idx == 2) {
            return "G";
        } else {
            return "T";
        }
    }

    // Counts number of each nucleotide used in dna and 
    // returns list of counts of each nucleoties.
    // no exception
    // Parameters:
    //      - dna: the sequence of dna
    //  Returns:
    //  - int[]: array of count of each nucleotides in dna, 
    //           for example - [2,3,2,5]
    public static int[] countNucleotide(String dna) {
        int[] count = new int[4];
        for (int i = 0; i < dna.length(); i++) {
            if(dna.charAt(i) == 'A') {
                count[0]++;
            } else if(dna.charAt(i) == 'C') {
                count[1]++;
            } else if(dna.charAt(i) == 'G') {
                count[2]++;
            } else {
                count[3]++;
            }
        }
        return count;
    }

    // Finds which nucleotide is most commonly used based on the counts of 
    // each nucleotides used and returns the index that indicates any nucleotide
    // that is most commonly used. 
    // no exception
    // Parameters:
    //    - count: array of total number of each nucleotides used in dna and 
    //             each index indicates the type of nuclotide in order of 
    //             A, C, G, T.
    // Returns:
    //   - int: index of an array count that indicates the nucleotide that is most commonly used
    public static int findMax(int[] count) {
        int ret = 0;
        int max = -1;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > max) {
                max = count[i];
                ret = i;
            }
        }
        return ret;
    }
}

