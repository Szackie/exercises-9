package Excercise9;

import java.io.*;
import java.util.*;
//TODO: check warnings

class Excercises9 {

    public static void main(String[] a) {

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("C:\\Users\\Szymon\\IdeaProjects\\Exercises 9\\some text.txt"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String wholeText = "";
        while (scanner.hasNextLine())
            wholeText += scanner.nextLine();

        ArrayList<WordAmount> words = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(wholeText, " (){}[]/\\.,;:");
        while (tokenizer.hasMoreTokens()) {
            WordAmount token = new WordAmount(tokenizer.nextToken(), 1);

            if (words.contains(token)) {
                WordAmount repeatedWord = words.get(words.indexOf(token));
                words.set(words.indexOf(token), new WordAmount(token.getWord(), repeatedWord.getAmount() + 1));
            } else
                words.add(token);
        }

        System.out.println("\n20 most common words\n");
        words.sort(Comparator.reverseOrder());
        for (int i = 0; i < 20; i++)
            System.out.println(words.get(i));

        System.out.println("\n20 rarest words\n");
        words.sort(Comparator.naturalOrder());
        for (int i = 0; i < 20; i++)
            System.out.println(words.get(i));

        System.out.println("\n20 longest words\n");
        words.sort(new Comparator<WordAmount>() {
            @Override
            public int compare(WordAmount o1, WordAmount o2) {
                if (o1.getWord().length() < o2.getWord().length())
                    return 1;
                else if (o1.getWord().length() == o2.getWord().length())
                    return 0;
                else
                    return -1;
            }
        });

        for (int i = 0; i < 20; i++)
            System.out.println(words.get(i));

        System.out.println("\nAll palindromes\n");
        ArrayList<WordAmount> palindromes = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            boolean isPalindrome = true;
            String word = words.get(i).getWord();
            for (int j = 0; j < word.length() / 2; j++) {
                if (!((word.charAt(j)) == (word.charAt(word.length() - 1 - j)))) {
                    isPalindrome = false;
                    break;
                }

            }
            if (isPalindrome && word.length() > 1)
                palindromes.add(words.get(i));

        }
        System.out.println(palindromes);

        String cloudForHTML="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>";
        words.sort(Comparator.reverseOrder());
        int maks=words.get(0).getAmount();

        for(int i=0;i<400;i++){
            int fontSize=20+((words.get(i).getAmount()*50)/maks);
            int x= (int) (Math.random()*400);
            int y= (int) (Math.random()*400);
        cloudForHTML+="\n<div style=\"position:absolute;\n" +
                "left: "+x+"px; top: "+y+"px;\n" +
                "font-size: "+fontSize+"px\">"+words.get(i).getWord()+"</div>\n";
        }
        cloudForHTML+="</body>\n" +
                "</html>";
        try {
            //FIXME: change the way of writing to file
            RandomAccessFile raf=new RandomAccessFile("C:\\Users\\Szymon\\IdeaProjects\\Exercises 9\\src\\Excercise9\\Cloud of words.html","rw");
        {
            raf.writeUTF(cloudForHTML);
            raf.close();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}