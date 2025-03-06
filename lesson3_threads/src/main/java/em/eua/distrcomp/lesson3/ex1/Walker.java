package em.eua.distrcomp.lesson3.ex1;

import java.util.concurrent.locks.ReentrantLock;

public class Walker implements Runnable {
    private String name;
    private int[] coords;
    private int sleepTime;
    private static ReentrantLock lock = new ReentrantLock();

    public Walker(String name, int[] startingCoords, int sleepTime) {
        this.name = name;
        this.coords = startingCoords;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        while (true) {
            coords = calculateStep(coords);

            if (Walker.lock.isLocked()) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(getMessage(name, coords));

            if (coords[0] == 2 && coords[1] == 2) {
                lock.tryLock();
            }

            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int[] calculateStep(int[] current) {
        int step = (int) Math.floor(Math.random() * 5);
        switch (step) {
            case 0:
                return new int[]{current[0] - 1, current[1]};
            case 2:
                return new int[]{current[0] + 1, current[1]};
            case 3:
                return new int[]{current[0], current[1] - 1};
            case 4:
                return new int[]{current[0], current[1] + 1};
            default:
                return current;
        }
    }

    private String getMessage(String person, int[] coord) {
        return person + " is standing on [" + coord[0] + "," + coord[1] + "]";
    }
}



