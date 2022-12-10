import java.util.*;
public class Data
{
    static String WebName;
    static String unencrypted;
    static String encrypted;
    static ArrayList<Integer> key = new ArrayList<Integer>();
    public static Integer Rand(int min, int max){
        return new Integer((int)(Math.random() * (max - min)) + min);
    }
    public static void dataobj(String webname, String password) {
        WebName = webname;
        unencrypted = password;
        char buffer = ' ';
        StringBuilder Encrypted = new StringBuilder(password);
        //creating a key for the encryption
        for (int i = 0; i < 36; i++) {
            key.add(Rand(0, (password.length())));
        }
        //scrambling using key
        for (int i = 0; i < 20; i=i+2) {
            buffer = Encrypted.charAt(key.get(i));
            Encrypted.setCharAt(key.get(i), Encrypted.charAt(key.get(i+1)));
            Encrypted.setCharAt(key.get(i+1), buffer);
        }
        for (int i = 0; i < 16; i=i+2) {
            int AsciiBuffer = (int)Encrypted.charAt(key.get(i+20));
            AsciiBuffer = AsciiBuffer + (key.get(i+21));
            buffer = (char)AsciiBuffer;
            Encrypted.setCharAt(key.get(i+20), buffer);          
        }
        encrypted = Encrypted.toString();
    }
    public static boolean Login(String website, String encrypted, ArrayList<Data> a) {
        boolean loginstate = false;
        boolean foundweb = false;
        StringBuilder Encrypted = new StringBuilder(encrypted);
        char buffer = ' ';
        for (Data i : a) {
            if (website.toUpperCase().equals(i.WebName.toUpperCase())) {
                //unencryption process (using key)
                for (int k = 0; k < 20; k=k+2) {
                    buffer = Encrypted.charAt(i.key.get(19-(k)));
                    Encrypted.setCharAt(i.key.get(19-(k)), Encrypted.charAt(i.key.get(19-(k+1))));
                    Encrypted.setCharAt(i.key.get(19-(k+1)), buffer);
                }
                for (int l = 0; l < 16; l=l+2) {
                    int AsciiBuffer = (int)Encrypted.charAt(i.key.get(34-l));
                    AsciiBuffer = AsciiBuffer - (i.key.get(35-l));
                    buffer = (char)AsciiBuffer;
                    Encrypted.setCharAt(i.key.get(34-l), buffer); 
                }
                //check password validity
                if (i.unencrypted.equals(Encrypted.toString())) {
                    loginstate = true;
                }
            }   
        }
        return loginstate;
    }
}
