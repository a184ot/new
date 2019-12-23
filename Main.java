package com.company;

import com.sun.source.tree.ContinueTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String primer = "";
        for (int g = 0; g < 10; g += 0) {
            try {
                primer = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            primer = Space(primer);      // убираем пробелы и меняем регистр
            Correct(primer);
            boolean rim = Rim(primer);
            String otvet = "";
            if (rim == false) otvet = "" + ArabResh(primer);
            else otvet = RimResh(primer);
            System.out.println(otvet);
            Thread.sleep(3000);
        }




    }

    public static String Space(String primer) {   //убираем пробелы и меняем регистр
        primer = primer.toUpperCase();
        String primer2 = "";
        for (int i = 0; i < (primer.length()); i++) {
            char c = primer.charAt(i);
            char s = ' ';
            if (c != s) primer2 += c;
        }
        return primer2;
    }

    public static void Correct(String pr) {    // проверка на ошибки
        if ((pr.length() < 3) || (pr.length() > 9))
        {
            System.out.println("недопустимое количество символов");
            System.exit(0); //проверка на длинну (не более 9)
        }
        for (int i = 0; i < pr.length(); i++) {         //проверка все символы из допустимого диапазона
            char aa = pr.charAt(i);
            int stop = 0;
            if ((aa == 'X') || (aa == 'V') || (aa == 'I')) stop = 1;
            if (((aa == '+') || (aa == '-') || (aa == '/') || (aa == '*')) & ((i != 0) || (i != pr.length()))) stop = 1;
            if ((aa == '0') || (aa == '1') || (aa == '2') || (aa == '3') || (aa == '4') || (aa == '5')) stop = 1;
            if ((aa == '6') || (aa == '7') || (aa == '8') || (aa == '9')) stop = 1;

            if (stop == 0){
                System.out.println("присутствуют недопустимые символы или сочетание символов");
                System.exit(0);
            }
        }


        boolean rim = Rim(pr);

        if (rim == true) {          //проверка все цифры рим
            for (int i = 0; i < pr.length(); i++) {
                char aa = pr.charAt(i);
                int stop = 0;
                if ((aa == 'X') || (aa == 'V') || (aa == 'I')) stop = 1;
                if ((aa == '+') || (aa == '-') || (aa == '/') || (aa == '*')) stop = 1;
                if (stop == 0)
                {
                    System.out.println("недопустимое сочетание цифр (римские и арабские)");
                    System.exit(0);
                }
            }
        }

        if (rim == false) {         //проверка все цифры араб
            for (int i = 0; i < pr.length(); i++) {
                char aa = pr.charAt(i);
                int stop = 1;
                if ((aa == 'X') || (aa == 'V') || (aa == 'I')) stop = 0;
                if ((aa == '+') || (aa == '-') || (aa == '/') || (aa == '*')) stop = 1;
                if (stop == 0)
                {
                    System.out.println("недопустимое сочетание цифр (арабские и римские)");
                    System.exit(0);
                }
            }

        }
        int stop = 0;
        for (int i = 0; i < pr.length(); i++) {        //проверка на одно действие (не а+с+с)
            char aa = pr.charAt(i);
            if ((aa == '+') || (aa == '-') || (aa == '/') || (aa == '*')) stop++;

        }
        if (stop != 1)
        {
            System.out.println("Знаков действия больне чем допустимо");
            System.exit(0);
        }
        if ((rim = false) & (pr.length() > 5))
        {
            System.out.println("недопустимая длина строки ввода");
            System.exit(0); //для араб цифр макс длина 5
        }

        return;
    }

    public static boolean Rim(String pr) { //проверка рим или араб
        char aa = pr.charAt(0);
        boolean rim;   //проверка 1я цифра рим или араб
        if ((aa == 'X') || (aa == 'V') || (aa == 'I'))
            rim = true;
        else rim = false;
        return rim;
    }

    public static int ArabResh(String pr)   //решение если араб цифры
    {
        int num1 = 0;
        int num2 = 0;
        int otvet = 0;
        char znak = ' ';
        if (pr.length() == 3) {
            num1 = Character.getNumericValue(pr.charAt(0));
            num2 = Character.getNumericValue(pr.charAt(2));
            //System.out.println(num1);
            //System.out.println(num2);

            if ((num1 == 0) || (num2 == 0))
            {
                System.out.println("диапазон допустимых чисел 1 - 10");
                System.exit(0);
            }
            if (pr.charAt(1) == '+') znak = '+';
            if (pr.charAt(1) == '-') znak = '-';
            if (pr.charAt(1) == '*') znak = '*';
            if (pr.charAt(1) == '/') znak = '/';
        }
        if (pr.length() == 5) {
            if ((pr.charAt(0) != '1') || (pr.charAt(1) != '0') || (pr.charAt(3) != '1') || (pr.charAt(4) != '0'))
            {
                System.out.println("допустимый диапазон чисел 1-10");
                System.exit(0);
            }
            if (pr.charAt(2) == '+') znak = '+';
            if (pr.charAt(2) == '-') znak = '-';
            if (pr.charAt(2) == '*') znak = '*';
            if (pr.charAt(2) == '/') znak = '/';
            num1 = 10;
            num2 = 10;
        }
        if (pr.length() == 4) {
            if ((pr.charAt(0) == '1') & (pr.charAt(1) == '0')) {
                num1 = 10;
                num2 = Character.getNumericValue(pr.charAt(3));
                znak = pr.charAt(2);
            }
            if ((pr.charAt(2) == '1') & (pr.charAt(3) == '0')) {
                num2 = 10;
                num1 = Character.getNumericValue(pr.charAt(0));
                znak = pr.charAt(1);
            }
            if (num1 == 0)
            {
                System.out.println("недопустимый диапазон чисел (1-10)");
                System.exit(0);
            }

        }
        if (znak == '+') otvet = (num1 + num2);
        if (znak == '-') otvet = (num1 - num2);
        if (znak == '*') otvet = (num1 * num2);
        if (znak == '/') otvet = (num1 / num2);

        return otvet;
    }

    public static String RimResh(String pr)         //решение рим цифр
    {
        int num1 = 0;
        int num2 = 0;
        int zn = 0;
        for (int i = 1; i < pr.length(); i++) {     // ищем знак действия
            if (pr.charAt(i) == '-' || pr.charAt(i) == '+' || pr.charAt(i) == '*' || pr.charAt(i) == '/') zn = i;
        }
            if (zn == 1)            // переводим рим в араб цифры
            {
                if (pr.charAt(0) == 'I') num1 = 1;
                if (pr.charAt(0) == 'V') num1 = 5;
                if (pr.charAt(0) == 'X') num1 = 10;
            }

        if (zn == 2)
        {
            if (pr.charAt(0) == 'I' & pr.charAt(1) == 'I') num1 = 2;
            if (pr.charAt(0) == 'I' & pr.charAt(1) == 'V') num1 = 4;
            if (pr.charAt(0) == 'I' & pr.charAt(1) == 'X') num1 = 9;
            if (pr.charAt(0) == 'V' & pr.charAt(1) == 'I') num1 = 6;
        }
        if (zn == 3)
        {
            if (pr.charAt(0) == 'I' & pr.charAt(1) == 'I' & pr.charAt(2) == 'I') num1 = 3;
            if (pr.charAt(0) == 'V' & pr.charAt(1) == 'I' & pr.charAt(2) == 'I') num1 = 7;
        }
        if (zn == 4)
        {
            if (pr.charAt(0) == 'V' & pr.charAt(1) == 'I' & pr.charAt(2) == 'I' & pr.charAt(3) == 'I') num1 = 8;
        }
        int zn2 = pr.length() - 1 - zn;     //перевод в араб второй цифры

        if (zn2 == 1)
        {
            if (pr.charAt(zn+1) == 'I') num2 = 1;
            if (pr.charAt(zn+1) == 'V') num2 = 5;
            if (pr.charAt(zn+1) == 'X') num2 = 10;
        }

        if (zn2 == 2)
        {
            if (pr.charAt(zn+1) == 'I' & pr.charAt(zn+2) == 'I') num2 = 2;
            if (pr.charAt(zn+1) == 'I' & pr.charAt(zn+2) == 'V') num2 = 4;
            if (pr.charAt(zn+1) == 'I' & pr.charAt(zn+2) == 'X') num2 = 9;
            if (pr.charAt(zn+1) == 'V' & pr.charAt(zn+2) == 'I') num2 = 6;
        }
        if (zn2 == 3)
        {
            if (pr.charAt(zn+1) == 'I' & pr.charAt(zn+2) == 'I' & pr.charAt(zn+3) == 'I') num2 = 3;
            if (pr.charAt(zn+1) == 'V' & pr.charAt(zn+2) == 'I' & pr.charAt(zn+3) == 'I') num2 = 7;
        }
        if (zn == 4)
        {
            if (pr.charAt(zn+1) == 'V' & pr.charAt(zn+2) == 'I' & pr.charAt(zn+3) == 'I' & pr.charAt(zn+4) == 'I') num2 = 8;
        }
        if (num1 == 0 || num2 == 0)
        {
            System.out.println("недопустимые символы");
            System.exit(0);
        }
        String Rm = Integer.toString(num1) + (pr.charAt(zn)) + (Integer.toString(num2));    //перевод в строку
        int rimOtv = ArabResh(Rm);      // решение примера араб цифрами
        //System.out.println(Rm);
        //System.out.println(rimOtv);
        String rOtv = "";        // перевод ответа в рим
        if (rimOtv == 100) rOtv = "C";
        if (rimOtv > 89 & rimOtv <100) rOtv = "XC";
        if (rimOtv > 79 & rimOtv <90) rOtv = "LXXX";
        if (rimOtv > 69 & rimOtv <80) rOtv = "LXX";
        if (rimOtv > 59 & rimOtv <70) rOtv = "LX";
        if (rimOtv > 49 & rimOtv <60) rOtv = "L";
        if (rimOtv > 39 & rimOtv <50) rOtv = "XL";
        if (rimOtv > 29 & rimOtv <40) rOtv = "XXX";
        if (rimOtv > 19 & rimOtv <30) rOtv = "XX";
        if (rimOtv > 9 & rimOtv < 20) rOtv = "X";

        for (int i = 0; i < 10; i++)
        {
            if (rimOtv > 9) rimOtv -=10;
        }
        if (rimOtv == 1) rOtv += "I";
        if (rimOtv == 2) rOtv += "II";
        if (rimOtv == 3) rOtv += "III";
        if (rimOtv == 4) rOtv += "IV";
        if (rimOtv == 5) rOtv += "V";
        if (rimOtv == 6) rOtv += "VI";
        if (rimOtv == 7) rOtv += "VII";
        if (rimOtv == 8) rOtv += "VIII";
        if (rimOtv == 9) rOtv += "IX";
        if (rimOtv < 0)
        {
            System.out.println("некорректный пример. ответ меньше 1 для римских цифр");
            System.exit(0);
        }
        return rOtv;
    }
}