package em.eua.distrcomp.lesson3.ex1;


public class CoordinatesTwoThreads {

    public static void main(String[] args) {

        for (int i = 0; i < 1; i++) {
            Walker walker = new Walker("person" + i, new int[]{0, 0}, 100);
            new Thread(walker).start();
        }
    }
}
