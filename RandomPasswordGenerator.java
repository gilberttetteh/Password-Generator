import java.util.Random;
import java.util.Scanner;

public class RandomPasswordGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the minimum password length: ");
        int minLength = scanner.nextInt();

        System.out.print("Enter the minimum number of uppercase characters: ");
        int minUppercase = scanner.nextInt();

        System.out.print("Enter the minimum number of digits: ");
        int minDigits = scanner.nextInt();

        System.out.print("Enter the minimum number of special characters: ");
        int minSpecial = scanner.nextInt();

        System.out.print("Enter the allowed special characters (e.g. &!#@): ");
        String specialChars = scanner.next();

        System.out.println("Enter 1 to generate a new password or 2 to check a password:");
        int choice = scanner.nextInt();

        if (choice == 1) {
            String password = generatePassword(minLength, minUppercase, minDigits, minSpecial, specialChars);
            System.out.println("Generated password: " + password);
        } else if (choice == 2) {
            System.out.print("Enter the password to check: ");
            String password = scanner.next();
            boolean isValid = checkPassword(password, minLength, minUppercase, minDigits, minSpecial, specialChars);
            if (isValid) {
                System.out.println("The password is valid.");
            } else {
                System.out.println("The password is not valid.");
            }
        }
    }

    public static String generatePassword(int minLength, int minUppercase, int minDigits, int minSpecial, String specialChars) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        while (password.length() < minLength) {
            int randomIndex = random.nextInt(3);
            char randomChar;

            switch (randomIndex) {
                case 0:
                    randomChar = (char) ('A' + random.nextInt(26));
                    break;
                case 1:
                    randomChar = (char) ('0' + random.nextInt(10));
                    break;
                default:
                    randomChar = specialChars.charAt(random.nextInt(specialChars.length()));
                    break;
            }

            password.append(randomChar);
        }

        while (countUppercase(password.toString()) < minUppercase) {
            int randomIndex = random.nextInt(password.length());
            password.setCharAt(randomIndex, (char) ('A' + random.nextInt(26)));
        }

        while (countDigits(password.toString()) < minDigits) {
            int randomIndex = random.nextInt(password.length());
            password.setCharAt(randomIndex, (char) ('0' + random.nextInt(10)));
        }

        while (countSpecial(password.toString(), specialChars) < minSpecial) {
            int randomIndex = random.nextInt(password.length());
            password.setCharAt(randomIndex, specialChars.charAt(random.nextInt(specialChars.length())));
        }

        return password.toString();
    }

    public static boolean checkPassword(String password, int minLength, int minUppercase, int minDigits, int minSpecial, String specialChars) {
        if (password.length() < minLength) {
            return false;
        }

        int countUppercase = countUppercase(password);
        int countDigits = countDigits(password);
        int countSpecial = countSpecial(password, specialChars);

        if (countUppercase < minUppercase || countDigits < minDigits || countSpecial < minSpecial) {
            return false;
        }

        for (char c : specialChars.toCharArray()) {
            if (password.indexOf(c) == -1) {
                return false;
            }
        }

        return true;
    }

    public static int countUppercase(String password) {
        int count = 0;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                count++;
            }
        }
        return count;
    }

    public static int countDigits(String password) {
        int count = 0;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return count;
    }

    public static int countSpecial(String password, String specialChars) {
        int count = 0;
        for (char c : password.toCharArray()) {
            if (specialChars.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
}