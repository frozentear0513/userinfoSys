package Day2020_12_01;

public class Test01 {


        private final String username01 = "123";
        final String username02 = "456";
        String username03 = "789";
        public String username04 = "012";  //ok
        public static String username05 = "345";  //ok
        public static final String username06 = "678";   //ok
        static final String username07 = "901";
        protected static final String username08 = "234";
        protected final String username09 = "567";
        public transient String username10 = "890";   //ok
        public static final transient String username11 = "1234";  //ok
        final transient String username12 = "5678";
        transient String username13 = "9012";
        private String username14 = "3456";

        public static void main(String[] args) throws Exception {
            Class cla = Class.forName("Day2020_12_01.Test01");
            jiexi(cla.getDeclaredField("username01").getModifiers());
            jiexi(cla.getDeclaredField("username02").getModifiers());
            jiexi(cla.getDeclaredField("username03").getModifiers());
            jiexi(cla.getDeclaredField("username04").getModifiers());
            jiexi(cla.getDeclaredField("username05").getModifiers());
            jiexi(cla.getDeclaredField("username06").getModifiers());
            jiexi(cla.getDeclaredField("username07").getModifiers());
            jiexi(cla.getDeclaredField("username08").getModifiers());
            jiexi(cla.getDeclaredField("username09").getModifiers());
            jiexi(cla.getDeclaredField("username10").getModifiers());
            jiexi(cla.getDeclaredField("username11").getModifiers());
            jiexi(cla.getDeclaredField("username12").getModifiers());
            jiexi(cla.getDeclaredField("username13").getModifiers());
            jiexi(cla.getDeclaredField("username14").getModifiers());
        }
        static void jiexi(int a){
            String answer = " ";
            String xsf = " ";
            if(a >= 2048){
                xsf = "STRICT ";
                answer = xsf.concat(answer);
                a -= 2048;
            }
            if(a >= 1024){
                xsf = "ABSTRACT ";
                answer = xsf.concat(answer);
                a -= 1024;
            }
            if(a >= 512){
                xsf = "INTERFACE ";
                answer = xsf.concat(answer);
                a -= 512;
            }
            if(a >= 256){
                xsf = "NATIVE ";
                answer = xsf.concat(answer);
                a -= 256;
            }
            if(a >= 128){
                xsf = "TRANSIENT ";
                answer = xsf.concat(answer);
                a -= 128;
            }
            if(a >= 64){
                xsf = "VOLATILE ";
                answer = xsf.concat(answer);
                a -= 64;
            }
            if(a >= 32){
                xsf = "SYNCHRONIZED ";
                answer = xsf.concat(answer);
                a -= 32;
            }
            if(a >= 16){
                xsf = "FINAL ";
                answer = xsf.concat(answer);
                a -= 16;
            }
            if(a >= 8){
                a -= 8;
                xsf = "STATIC ";
                answer = xsf.concat(answer);
            }
            if(a >= 4){
                xsf = "PROTECTED ";
                answer = xsf.concat(answer);
                a -= 4;
            }
            if(a >= 2){
                xsf = "PRIVATE ";
                answer = xsf.concat(answer);
                a -= 2;
            }
            if(a == 1){
                xsf = "PUBLIC ";
                answer = xsf.concat(answer);
            }
            System.out.println(answer);
        }
    }







