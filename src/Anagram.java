import java.io.*;

public class Anagram {

    public static void main(String[] args) throws IOException {
        String result1 = "";
        String result2 ="";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("./src/input1.txt")));
        BufferedReader bf2 = new BufferedReader(new FileReader(new File("./src/input2.txt")));

        while (bufferedReader.read() != -1) {
            result1 += bufferedReader.readLine();
        }

        bufferedReader.close();

        while(bf2.read() != -1) {
            result2 += bf2.readLine();
        }

        bf2.close();

        System.out.println(new Anagram().checkAnagram(result1,
                result2));
    }

    public int checkAnagram(String A, String B) {
        char[] arrayA = A.toCharArray();
        char[] arrayB = B.toCharArray();
        StringBuilder sb = new StringBuilder(B.length());
        sb.append(B);

        int match = firstSolution(arrayA, arrayB);
        int ans = (arrayA.length - match) + (sb.capacity() - match);
        return ans;
    }

    private int firstSolution(char [] arrayA, char[] arrayB) {
        int match = 0;

        for (int i = 0; i < arrayA.length; i++) {
            for (int j = 0; j < arrayB.length; j ++) {
                if(arrayA[i] == arrayB[j]) {
                    arrayB[j] = 0;
                    match++;
                    break;
                }
            }
        }
        return match;
    }

    private int secondSolution(int [] arrayA, StringBuffer sb) {
        int match = 0;

        for (int i = 0; i < arrayA.length; i++) {
            int index = sb.indexOf(String.valueOf(arrayA[i]));

            if(index != -1) {
                sb = sb.replace(index, index + 1,String.valueOf(0));
                match++;
            }
        }
        return match;
    }
}
