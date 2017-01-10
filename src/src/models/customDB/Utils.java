package models.customDB;


public class Utils {
    public static String bytesToStringUTFCustom(byte[] bytes) {

        char[] buffer = new char[bytes.length >> 1];

        for(int i = 0; i < buffer.length; i++) {

            int bpos = i << 1;

            char c = (char)(((bytes[bpos]&0x00FF)<<8) + (bytes[bpos+1]&0x00FF));

            buffer[i] = c;

        }

        return new String(buffer);

    }

    public static byte[] stringToBytesUTFCustom(String str) {
        char[] buffer = str.toCharArray();
        byte[] b = new byte[buffer.length << 1];
        for (int i = 0; i < buffer.length; i++) {
            int bpos = i << 1;
            b[bpos] = (byte) ((buffer[i] & 0xFF00) >> 8);
            b[bpos + 1] = (byte) (buffer[i] & 0x00FF);
        }
        return b;
    }

    public static String formatDate(long time){
        long minutes;
        long hours;
        long seconds;
        seconds = (time%60);
        minutes = (time)/60;
        hours = (minutes)/60;
        return hours+"h:"+minutes+"m:"+seconds+"s";
    }
}
