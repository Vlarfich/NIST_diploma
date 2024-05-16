package main;

import io.github.pr0methean.betterrandom.prng.AesCounterRandom;
import io.github.pr0methean.betterrandom.seed.DevRandomSeedGenerator;
import io.github.pr0methean.betterrandom.seed.AnuQuantumSeedClient;
import io.github.pr0methean.betterrandom.seed.SeedException;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s).replace(' ', '0');
    }

    public static void main(String[] args) {
        AnuQuantumSeedClient client = new AnuQuantumSeedClient();
        System.out.println(client.isWorthTrying());
        byte[] seed = {-41, 91, 59, 38, -2, -113, -109, 81, 10, 94, 62,
                7, -92, 12, -126, 58, 101, 80, 32, 53, 31, -120};
        try {
            AesCounterRandom random = new AesCounterRandom(seed);
            for (int i = 0; i < 100; i++) {
                System.out.println(random.nextInt(0, 2));
            }
        } catch (SeedException s) {
            System.err.println("Wait for site to respond");
        }


        try (InputStream inputStream = new FileInputStream("qrbg-1M.bin");) {
            int byteRead = -1;

            while ((byteRead = inputStream.read()) != -1) {
                //System.out.print(byteRead + " ");
                System.out.print(padLeft(Integer.toBinaryString(byteRead), 8) + " ");
            }
        } catch (IOException e) {
            System.err.println("File read error");
        }
    }
}
