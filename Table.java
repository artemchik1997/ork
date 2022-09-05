package ork;

import java.util.ArrayList;
import java.util.List;

public class Table {


    public static void main(String[] args) {

        // создаю класс Args_Arr с неиницилизироваными полями
        class  Input_arg {
            String str;
            String expression;

            String number;
        }

        Input_arg Args_Arr[];


        Args_Arr = new Input_arg[args.length];


        int strcount = 0;
        int expresscount = 0;
        int numbercount = 0;
        String exp;



        // в этом цикле я присваиваю полям  объекта Args_Arr те значения требующие в задании
        for (int i = 0; i < Args_Arr.length; i++) {
            Args_Arr[i] = new Input_arg();

            if ((args[i].contains("+") || args[i].contains("-") || args[i].contains("*")) && args[i].contains("=")) {
                exp = args[i].replace("=", "");
                String expressionText = exp;
                List<Lexeme> lexemes = lexAnalyze(expressionText);
                LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
                Args_Arr[i].expression = String.valueOf(expr(lexemeBuffer));
                expresscount += 1;


            }
            if (args[i].contains("\'")) {

                Args_Arr[i].str = args[i].replace("\'", "");
                strcount += 1;



            }
            if(args[i].matches("[0-9]+")){
                try {
                    Integer.parseInt(args[i]);
                    Args_Arr[i].number = args[i];
                    numbercount +=1;
                } catch(Exception e) {
                    System.err.println("#Error "+e);
                }
            }

        }

//в цикле for идет проверка на наличие определенных аргументов из cmd
//---------------------------------------------------------------------------------------------------------


        int num = 1;
        for (Input_arg i:Args_Arr ){

            boolean all_args = false;
            if (strcount != 0 && numbercount != 0 && expresscount!= 0){
                if(strcount>numbercount && strcount>expresscount){
                    if (num ==1){
                        displayTable disp = new displayTable();
                        disp.all_arg(strcount);
                        num-=1;
                    }
                    all_args = true;
                    } else if (strcount<numbercount && numbercount>expresscount) {
                    if (num ==1){
                        displayTable disp = new displayTable();
                        disp.all_arg(numbercount);
                        num-=1;
                    }
                    all_args = true;
                }else{
                    if (num ==1){
                        displayTable disp = new displayTable();
                        disp.all_arg(expresscount);
                        num-=1;
                    }
                    all_args = true;

                }
            }


            boolean strbool = false;
            if (strcount != 0 && ( numbercount == 0 && expresscount == 0)) {
                if (num ==1){
                    displayTable disp = new displayTable();
                    disp.str_only(strcount);
                    num-=1;}
                strbool = true;


            }
            boolean expr_str = false;
            if (strcount != 0 && expresscount != 0 && numbercount == 0) {
                if (strcount > expresscount  ) {
                    if(num == 1){
                        displayTable disp = new displayTable();
                        disp.express_str(strcount);num -=1;}


                    expr_str = true;
                }else {
                    if(num == 1){
                        displayTable disp = new displayTable();
                        disp.express_str(expresscount);num -=1;}


                    expr_str = true;}
            }
            boolean str_numbool = false;
            if ((strcount != 0 && numbercount != 0)&& expresscount == 0 ) {
                if (strcount > numbercount  ) {
                    if(num == 1){
                        displayTable disp = new displayTable();
                        disp.str_number(strcount);num -=1;}
                    str_numbool = true;



                }else {
                    if(num == 1){
                        displayTable disp = new displayTable();
                        disp.str_number(numbercount);num -=1;}
                    str_numbool = true;
            }
              }
            boolean expr_num_bool = false;

            if((expresscount != 0 && numbercount != 0) && strcount == 0) {
                if (expresscount > numbercount  ) {
                    if(num == 1){
                        displayTable disp = new displayTable();
                        disp.express_num(expresscount);num -=1;}
                    expr_num_bool = true;



                }else {
                    if(num == 1){
                        displayTable disp = new displayTable();
                        disp.express_num(numbercount);num -=1;}
                    expr_num_bool = true;}}

            boolean numbool = false;
            if (numbercount != 0 &&(expresscount == 0 && strcount == 0)  ){
                if(num == 1){
                    displayTable disp = new displayTable();
                    disp.num_only(numbercount);num -=1;}
                numbool = true;
            }

            boolean expbool = false;
            if (expresscount!=0 && (numbercount == 0 && strcount == 0)){
                if(num == 1){
                    displayTable disp = new displayTable();
                    disp.exp_only(expresscount);num -=1;}
                expbool = true;
            }


//------------------------------------------------------------------------------------------------------------
            if(expbool == true){System.out.println(i.expression);}

            if(numbool == true){System.out.println(i.number);}

            if (strbool == true){System.out.println(i.str);

            }
            if (expr_str == true) {
                if (i.str !=  null ){System.out.println("                                 "+i.str.strip());}

                if(i.expression != null ){
                    System.out.println(i.expression.strip());

                }
            }
            if(all_args == true){
                if (i.str !=  null ){System.out.println("                                 "+i.str);}

                if(i.expression != null ){System.out.println(i.expression);}
                if(i.number != null){System.out.println("                                                                  "+i.number);}
            }



            if (expr_num_bool == true){
                if (i.expression !=  null ){System.out.println("      "+i.expression);}

                if(i.number != null ){
                    System.out.println("                                  "+i.number);

                }
            }



            if(str_numbool == true){
                if (i.number !=  null ){System.out.println("   "+i.number);}

                if(i.str != null ){
                    System.out.println("                              "+i.str);

                }
            }



        }

    }

 // класс таблицы вывода взависимости какие аргументы командной строки были переданы ,такие данные и будут
    // отображаться в при выводе
//------------------------------------------------------------------------------------------------------

    public static class displayTable{


        void all_arg(int height){
            System.out.println("height = "+height+"             width= "+3);
            System.out.println("expression                      "+"strings"+"                         number");
        }
        void express_str(int height){
            System.out.println("height = "+height+"             width= "+2);
            System.out.println("expression                      "+"strings");

        }

        void express_num(int height){
            System.out.println("height = "+height+"             width= "+2);
            System.out.println("expression                      "+"number");

        }
        void str_number(int height){
            System.out.println("height = "+height+"             width= "+2);
            System.out.println("numbers                      "+"strings");

        }
        void str_only(int height){
            System.out.println("height = "+height+"                width="+1);
            System.out.println("strings");

        }
        void exp_only(int height){
            System.out.println("height = "+height+"                width="+1);
            System.out.println("expression");

        }
        void num_only(int height){
            System.out.println("height = "+height+"                width="+1);
            System.out.println("number");
        }
    }



//внизу находится класс для работы с математическими выражениями
//----------------------------------------------------------------------------------------------------------

    public enum LexemeType {
        LEFT_BRACKET, RIGHT_BRACKET,
        OP_PLUS, OP_MINUS, OP_MUL, OP_DIV,
        NUMBER,
        EOF;
    }

    public static class Lexeme {
        LexemeType type;
        String value;

        public Lexeme(LexemeType type, String value) {
            this.type = type;
            this.value = value;
        }

        public Lexeme(LexemeType type, Character value) {
            this.type = type;
            this.value = value.toString();
        }

        @Override
        public String toString() {
            return "Lexeme{" +
                    "type=" + type +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static class LexemeBuffer {
        private int pos;

        public List<Lexeme> lexemes;

        public LexemeBuffer(List<Lexeme> lexemes) {
            this.lexemes = lexemes;
        }

        public Lexeme next() {
            return lexemes.get(pos++);
        }

        public void back() {
            pos--;
        }

        public int getPos() {
            return pos;
        }
    }

    public static List<Lexeme> lexAnalyze(String expText) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        int pos = 0;
        while (pos< expText.length()) {
            char c = expText.charAt(pos);
            switch (c) {
                case '(':
                    lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, c));
                    pos++;
                    continue;
                case ')':
                    lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, c));
                    pos++;
                    continue;
                case '+':
                    lexemes.add(new Lexeme(LexemeType.OP_PLUS, c));
                    pos++;
                    continue;
                case '-':
                    lexemes.add(new Lexeme(LexemeType.OP_MINUS, c));
                    pos++;
                    continue;
                case '*':
                    lexemes.add(new Lexeme(LexemeType.OP_MUL, c));
                    pos++;
                    continue;
                case '/':
                    lexemes.add(new Lexeme(LexemeType.OP_DIV, c));
                    pos++;
                    continue;
                default:
                    if (c <= '9' && c >= '0') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(c);
                            pos++;
                            if (pos >= expText.length()) {
                                break;
                            }
                            c = expText.charAt(pos);
                        } while (c <= '9' && c >= '0');
                        lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
                    } else {
                        if (c != ' ') {
                            throw new RuntimeException("#Unexpected character: " + c);
                        }
                        pos++;
                    }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }

    public static int expr(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        if (lexeme.type == LexemeType.EOF) {
            return 0;
        } else {
            lexemes.back();
            return plusminus(lexemes);
        }
    }

    public static int plusminus(LexemeBuffer lexemes) {
        int value = multdiv(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_PLUS:
                    value += multdiv(lexemes);
                    break;
                case OP_MINUS:
                    value -= multdiv(lexemes);
                    break;
                case EOF:
                case RIGHT_BRACKET:
                    lexemes.back();
                    return value;
                default:
                    throw new RuntimeException("#Unexpected token: " + lexeme.value
                            + " at position: " + lexemes.getPos());
            }
        }
    }

    public static int multdiv(LexemeBuffer lexemes) {
        int value = factor(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_MUL:
                    value *= factor(lexemes);
                    break;
                case OP_DIV:
                    value /= factor(lexemes);
                    break;
                case EOF:
                case RIGHT_BRACKET:
                case OP_PLUS:
                case OP_MINUS:
                    lexemes.back();
                    return value;
                default:
                    throw new RuntimeException("#Unexpected token: " + lexeme.value
                            + " at position: " + lexemes.getPos());
            }
        }
    }

    public static int factor(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        switch (lexeme.type) {
            case NUMBER:
                return Integer.parseInt(lexeme.value);
            case LEFT_BRACKET:
                int value = plusminus(lexemes);
                lexeme = lexemes.next();
                if (lexeme.type != LexemeType.RIGHT_BRACKET) {
                    throw new RuntimeException("#Unexpected token: " + lexeme.value
                            + " at position: " + lexemes.getPos());
                }
                return value;
            default:
                throw new RuntimeException("#Unexpected token: " + lexeme.value
                        + " at position: " + lexemes.getPos());
        }
    }

}
