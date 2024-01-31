import java.io.*;

public class Day2_part2 {

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

    static String need_lose = "X";
    static String need_draw = "Y";
    static String need_win = "Z";

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
        if (me.equals(need_win)) {
            if (elve.equals(rock_elve)) {
                result += paper_bonus;
            }
            if (elve.equals(paper_elve)) {
                result += scissor_bonus;
            }
            if (elve.equals(scissor_elve)) {
                result += rock_bonus;
            }
            result += victory_p;
        }
        if (me.equals(need_draw)) {
            if (elve.equals(rock_elve)) {
                result += rock_bonus;
            }
            if (elve.equals(paper_elve)) {
                result += paper_bonus;
            }
            if (elve.equals(scissor_elve)) {
                result += scissor_bonus;
            }
            result += draw_p;
        }
        if (me.equals(need_lose)) {
            if (elve.equals(rock_elve)) {
                result += scissor_bonus;
            }
            if (elve.equals(paper_elve)) {
                result += rock_bonus;
            }
            if (elve.equals(scissor_elve)) {
                result += paper_bonus;
            }
            result += defeat_p;
        }
        return result;
    }
}