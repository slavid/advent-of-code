import java.io.*;

public class Day2_part1 {

    static String linea = "";

    static int victory_p = 6;
    static int draw_p = 3;
    static int defeat_p = 0;

    static int rock_bonus = 1;
    static int paper_bonus = 2;
    static int scissor_bonus = 3;

    static String elve_uses = "";
    static String me_uses = "";

    static String rock_elve = "A";
    static String paper_elve = "B";
    static String scissor_elve = "C";

    static String rock_me = "X";
    static String paper_me = "Y";
    static String scissor_me = "Z";

    static int score = 0;

    public static void main(String[] args) throws IOException {
        try {
            Reader inputText = new FileReader("input.txt");
            BufferedReader myReader = new BufferedReader(inputText);
            linea = myReader.readLine();
            while (linea != null) {
                if (!linea.isEmpty()) {
                    // System.out.println(linea);

                    linea = linea.trim();
                    elve_uses = linea.substring(0, 1);
                    me_uses = linea.substring(2, 3);

                    // System.out.println("Elfo usa: " + elve_uses + ", yo uso: " + me_uses);

                    score += getResult(elve_uses, me_uses);
                    // System.out.println(score);

                    linea = myReader.readLine();
                }
            }

            myReader.close();

            System.out.println(score);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static int getResult(String elve, String me) {
        int result = 0;
        // victory
        if ((elve.equals(rock_elve) && me.equals(paper_me)) || (elve.equals(paper_elve) && me.equals(scissor_me))
                || (elve.equals(scissor_elve) && me.equals(rock_me))) {
            if (me.equals(rock_me)) {
                result += rock_bonus;
            }
            if (me.equals(paper_me)) {
                result += paper_bonus;
            }
            if (me.equals(scissor_me)) {
                result += scissor_bonus;
            }

            return victory_p + result;
        }

        // draw
        if ((elve.equals(rock_elve) && me.equals(rock_me)) || (elve.equals(paper_elve) && me.equals(paper_me))
                || (elve.equals(scissor_elve) && me.equals(scissor_me))) {
            if (me.equals(rock_me)) {
                result += rock_bonus;
            }
            if (me.equals(paper_me)) {
                result += paper_bonus;
            }
            if (me.equals(scissor_me)) {
                result += scissor_bonus;
            }
            return draw_p + result;
        }
        // defeat
        if (me.equals(rock_me)) {
            result += rock_bonus;
        }
        if (me.equals(paper_me)) {
            result += paper_bonus;
        }
        if (me.equals(scissor_me)) {
            result += scissor_bonus;
        }

        return defeat_p + result;

        // return result;
    }
}