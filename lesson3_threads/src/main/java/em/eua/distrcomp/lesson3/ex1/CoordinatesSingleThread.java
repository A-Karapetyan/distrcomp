package em.eua.distrcomp.lesson3.ex1;

public class CoordinatesSingleThread {
    static boolean isLocked = false;
    public static void main(String[] args) {
        int[] personCoord = new int[]{0, 0};

        while (!CoordinatesSingleThread.isLocked) {

            for (int i = 0; i < 10; i++) {
                System.out.println(getMessage("person1", personCoord));
                personCoord = calculateStep(personCoord);

                CoordinatesSingleThread.isLocked = personCoord[0] == -2 && personCoord[1] == -2;

                if (CoordinatesSingleThread.isLocked) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }

            try {
                Thread.sleep(200l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static int[] calculateStep(int[] current) {
        int step = (int) Math.floor(Math.random() * 4);
        switch (step) {
            case 0:
                return new int[]{current[0] - 1, current[1]};
            case 1:
                return new int[]{current[0] + 1, current[1]};
            case 2:
                return new int[]{current[0], current[1] - 1};
            case 3:
                return new int[]{current[0], current[1] + 1};
            default:
                return current;
        }
    }

    public static String getMessage(String person, int[] coord) {
        return person + " is standing on [" + coord[0] + "," + coord[1] + "]";
    }
}


