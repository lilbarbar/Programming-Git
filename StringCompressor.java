import java.util.zip.Deflater;

public class StringCompressor { // FOUND ONLINE AND COMPLETELY COPIED FROM BITO.AI FYIII

    public static byte[] compressString(String data) {
        byte[] input = data.getBytes();
        byte[] output = new byte[input.length];

        Deflater deflater = new Deflater();
        deflater.setInput(input);
        deflater.finish();
        int compressedDataLength = deflater.deflate(output);

        return output;
    }
}