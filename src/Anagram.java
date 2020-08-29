import java.io.*;

/**
 * @author stevemak
 * This class shows how to solve an anagram questions which you might come across in interviews
 * The full details on the question being asked and other interesting things can be found
 * @ https://www.developerssauna.stevekmak.com/2020/08/istio-servicemesh.html
 */
public class Anagram {

    /**
     * I used to filed input data just to see how the algorithms perform with a large input
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String result1 = "";
        String result2 ="";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("./src/input1.txt")));
        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(new File("./src/input2.txt")));

        while (bufferedReader.read() != -1) {
            result1 += bufferedReader.readLine();
        }

        bufferedReader.close();

        while(bufferedReader2.read() != -1) {
            result2 += bufferedReader2.readLine();
        }

        bufferedReader2.close();
        System.out.println(new Anagram().checkAnagramFirstSolution(result1, result2));
    }

    public int checkAnagramFirstSolution(String A, String B) {
        char[] arrayA = A.toCharArray();
        char[] arrayB = B.toCharArray();
        int match = firstSolution(arrayA, arrayB);
        int ans = (arrayA.length - match) + (arrayB.length - match);
        return ans;
    }

    public int checkAnagramSecondSolution(String A, String B) {
        char[] arrayA = A.toCharArray();
        StringBuilder sb = new StringBuilder(B.length());
        sb.append(B);
        int match = secondSolution(arrayA, sb);
        int ans = (arrayA.length - match) + (sb.capacity() - match);
        return ans;
    }

    /**
     * First solution which uses two char arrays
     * Used one array as the base reference where I compare the second array elements with
     * When a match is found on the second array I assign zero to that index so that when checking the second time
     * with a similar character it will not match. It's like a quick way of deleting
     * I then increment the match counter to record this match
     * NB: It's important to break the second loop when match has been found because not doing so will let the second array
     * iterate till the last value and it there are many similar characters it will match them all
     * @param firstCharArray
     * @param secondCharArray
     * @return {int}
     */
    private int firstSolution(char [] firstCharArray, char[] secondCharArray) {
        int match = 0;

        for (int i = 0; i < firstCharArray.length; i++) {
            for (int j = 0; j < secondCharArray.length; j ++) {
                if(firstCharArray[i] == secondCharArray[j]) {
                    secondCharArray[j] = 0;
                    match++;
                    break;
                }
            }
        }
        return match;
    }

    /**
     * Second solution takes advantage of the Java String methods in particular index of method
     * The other String that I use for comparing input should be assigned to a StringBuilder because String is immutable
     * so when I do the replacement it will not change the value of object. So with StringBuilder since it's mutable I can
     * do the quick deletion on a certain index by assigning zero to that index
     * @param charArray
     * @param stringBuilder
     * @return {int}
     */
    private int secondSolution(char [] charArray, StringBuilder stringBuilder) {
        int match = 0;

        for (int i = 0; i < charArray.length; i++) {
            int index = stringBuilder.indexOf(String.valueOf(charArray[i]));

            if(index != -1) {
                stringBuilder = stringBuilder.replace(index, index + 1,String.valueOf(0));
                match++;
            }
        }
        return match;
    }
}
