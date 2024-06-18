import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {

            System.out.println("Hashing word 'buterbrod' using MessageDigest:");
            String stringToHash = "buterbrod";

            String md5Hash = HashGenerator.generateHashMD(stringToHash, "MD5");
            String sha1Hash = HashGenerator.generateHashMD(stringToHash, "SHA-1");
            String sha256Hash = HashGenerator.generateHashMD(stringToHash, "SHA-256");

            System.out.println("MD5 Hash: " + md5Hash);
            System.out.println("SHA-1 Hash: " + sha1Hash);
            System.out.println("SHA-256 Hash: " + sha256Hash);

            System.out.println("\nGenerating SecureRandom hashes:");
            String sha1prngHash = HashGenerator.generateHashSecureRandom("SHA1PRNG");
            String drbgHash = HashGenerator.generateHashSecureRandom("DRBG");
            String windowsPrngHash = HashGenerator.generateHashSecureRandom("Windows-PRNG");

            System.out.println("SHA1PRNG Hash: "+ sha1prngHash);
            System.out.println("DRBG HasH: "+drbgHash);
            System.out.println("Windows-PRNG: "+windowsPrngHash);

            System.out.println("\nTesting valid and invalid classes (Car):");
            System.out.println("\nValid:");

            Car car1 = new Car("Mazda Miata", 2000);
            Car car2 = new Car("Bentley Arnage", 2005);
            Car car3 = new Car("Bentley Arnage", 2005);

            System.out.println("Car 1: "+car1);
            System.out.println("Hash: "+car1.hashCode());
            System.out.println("Car 2: "+car2);
            System.out.println("Hash: "+car2.hashCode());
            System.out.println("Car 3: "+car3);
            System.out.println("Hash: "+car3.hashCode());

            System.out.println("Car 1 = car 2? "+ ((car1.equals(car2)) ? "Yes" : "No"));
            System.out.println("Car 2 = car 3? "+ ((car2.equals(car3)) ? "Yes" : "No"));

            System.out.println("\nMapping:");

            Map<Car, Integer> carMap = new HashMap<>();
            carMap.put(car1, 100000);
            carMap.put(car2, 200000);
            carMap.put(car3, 300000);
            for(Map.Entry<Car, Integer> carEntry : carMap.entrySet()){
                System.out.println(carEntry.getKey()+ " "+carEntry.getValue());
            }

            System.out.println("\nInvalid:");

            CarInvalid carInvalid1 = new CarInvalid("Mazda Miata", 2000);
            CarInvalid carInvalid2 = new CarInvalid("Bentley Arnage", 2005);
            CarInvalid carInvalid3 = new CarInvalid("Bentley Arnage", 2005);

            System.out.println("Car 1: "+carInvalid1);
            System.out.println("Hash: "+carInvalid1.hashCode());
            System.out.println("Car 2: "+carInvalid2);
            System.out.println("Hash: "+carInvalid2.hashCode());
            System.out.println("Car 3: "+carInvalid3);
            System.out.println("Hash: "+carInvalid3.hashCode());

            System.out.println("Car 1 = car 2? "+ ((carInvalid1.equals(carInvalid2)) ? "Yes" : "No"));
            System.out.println("Car 2 = car 3? "+ ((carInvalid2.equals(carInvalid3)) ? "Yes" : "No"));

            System.out.println("\nMapping:");

            // В результаті всі 3 автомобіля записуються в різні слоти хеш-мапи, адже JDK автоматично
            // коригує хеш-код, що не відповідає стандарту, допомагаючи уникнути колізій.
            Map<CarInvalid, Integer> carInvalidMap = new HashMap<>();
            carInvalidMap.put(carInvalid1, 100000);
            carInvalidMap.put(carInvalid2, 200000);
            carInvalidMap.put(carInvalid3, 300000);
            for(Map.Entry<CarInvalid, Integer> carInvalidEntry : carInvalidMap.entrySet()){
                System.out.println(carInvalidEntry.getKey()+ " "+carInvalidEntry.getValue());
            }

            try (FileWriter writer = new FileWriter("hash.txt")) {
                writer.write("MD5: " + md5Hash+"\n");
                writer.write("SHA-1: " +sha1Hash+"\n");
                writer.write("SHA-256: " +sha256Hash+"\n");
                writer.write("SHA1PRNG: " +sha1prngHash+"\n");
                writer.write("DRBG: " +drbgHash+"\n");
                writer.write("Windows-PRNG: " +windowsPrngHash+"\n");
                writer.write("Car1 hash: " +car1.hashCode()+"\n");
                writer.write("Car2 hash: " +car2.hashCode()+"\n");
                writer.write("Car3 hash: " +car3.hashCode()+"\n");
                writer.write("CarInvalid1 hash: " +carInvalid1.hashCode()+"\n");
                writer.write("CarInvalid2 hash: " +carInvalid2.hashCode()+"\n");
                writer.write("CarInvalid3 hash: " +carInvalid3.hashCode()+"\n");
            } catch (IOException e) {
                e.getMessage();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}