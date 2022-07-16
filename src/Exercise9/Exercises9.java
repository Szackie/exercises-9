package Exercise9;

import java.io.*;
import java.util.*;


class Exercises9 {

    public static void main(String[] a) {

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("C:\\Users\\Szymon\\IdeaProjects\\Exercises 9\\some text.txt"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder wholeText = new StringBuilder();
        while (scanner.hasNextLine())
            wholeText.append(scanner.nextLine());

        scanner.close();
        ArrayList<WordAmount> words = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(wholeText.toString(), " (){}[]/\\.,;:");
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
        words.sort((o1, o2) -> Integer.compare(o2.getWord().length(), o1.getWord().length()));

        for (int i = 0; i < 20; i++)
            System.out.println(words.get(i));

        System.out.println("\nAll palindromes\n");
        ArrayList<WordAmount> palindromes = new ArrayList<>();
        for (WordAmount wordAmount : words) {
            boolean isPalindrome = true;
            String word = wordAmount.getWord();
            for (int j = 0; j < word.length() / 2; j++) {
                if (!((word.charAt(j)) == (word.charAt(word.length() - 1 - j)))) {
                    isPalindrome = false;
                    break;
                }

            }
            if (isPalindrome && word.length() > 1)
                palindromes.add(wordAmount);

        }
        System.out.println(palindromes);

        StringBuilder cloudForHTML= new StringBuilder("""
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="utf-8">
                </head>
                <body>""");
        words.sort(Comparator.reverseOrder());
        int maks=words.get(0).getAmount();

        for(int i=0;i<400;i++){
            int fontSize=20+((words.get(i).getAmount()*50)/maks);
            double angle=Math.random()*360;
            double radius=Math.random()*400;
            int x= (int) (Math.cos(Math.toRadians(angle))*radius)+400;
            int y= (int) (Math.sin(Math.toRadians(angle))*radius)+400;
     //       int y= (int) (Math.random()*400);
        cloudForHTML.append("\n<div style=\"position:absolute;\n" + "left: ").append(x).append("px; top: ").append(y).append("px;\n").append("font-size: ").append(fontSize).append("px\">").append(words.get(i).getWord()).append("</div>\n");
        }
        cloudForHTML.append("</body>\n" + "</html>");
        try {

           PrintWriter writer=new PrintWriter("C:\\Users\\Szymon\\IdeaProjects\\Exercises 9\\src\\Exercise9\\Cloud of words.html");
        {
            writer.write(cloudForHTML.toString());
            writer.close();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}