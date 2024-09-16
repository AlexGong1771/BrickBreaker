import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Brick brick = new Brick(10, 10); // Create a 10x10 brick grid
        Scanner scanner = new Scanner(System.in);

        // Loop to allow adding multiple stones
        while (true) {
            brick.write();
            System.out.println("///////////////////////////////////////////////////////////////////////////////////////////");
            System.out.println("Enter stone position (x y) and type (LARGE, SMALL, VERTICAL, ANGLE) or type 'exit' to quit:");
            System.out.println("///////////////////////////////////////////////////////////////////////////////////////////");
            String input = scanner.nextLine();

            // Exit condition
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                // Parse user input
                String[] parts = input.split(" ");

                // Debugging output
                System.out.println("Parsed input: " + java.util.Arrays.toString(parts));

                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                String typeStr = parts[2].toUpperCase();

                // Debugging output
                System.out.println("x: " + x + ", y: " + y + ", type: " + typeStr);

                // Convert string to StoneType
                StoneType stoneType;
                switch (typeStr) {
                    case "LARGE":
                        stoneType = StoneType.LARGE;
                        break;
                    case "SMALL":
                        stoneType = StoneType.SMALL;
                        break;
                    case "VERTICAL":
                        stoneType = StoneType.VERTICAL;
                        break;
                    case "ANGLE":
                        stoneType = StoneType.ANGLE;
                        break;
                    default:
                        System.out.println("Invalid stone type. Try again.");
                        continue;
                }

                // Add stone to the brick grid
                Stone stone = new Stone(x, y, stoneType);
                brick.addStone(stone);

                // Display the grid

                brick.rewrite();
                System.out.println();

            } catch (Exception e) {
                // Print out more info on the exception
                e.printStackTrace(); // Helps identify the exact problem
                System.out.println("Invalid input. Please enter the data in the format: x y TYPE");
            }
        }

        scanner.close(); // Close the scanner when finished
    }
}
