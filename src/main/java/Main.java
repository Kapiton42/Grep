import java.io.IOException;

/**
 * Created by kapiton on 02.02.16.
 */
public class Main {
    private static int COUNT = 3;
    public static void main(String[] args) throws IOException {
        String searchString = args[0];
        //TestFileCreater.createFile("/home/kapiton/testFile");
        System.out.println("Stream");
        for(int j = 0; j < 3; j++) {
            long timeout = System.currentTimeMillis();
            for (int i = 1; i < args.length; i++)
                Grep.streamSearch(args[i], searchString);
            timeout = System.currentTimeMillis() - timeout;
            System.out.print(timeout + "\n");
        }
        System.out.println("Parallel buffered");
        for(int j = 0; j < COUNT; j++) {
            long timeout = System.currentTimeMillis();
            ParallelGrep.grep(args);
            timeout = System.currentTimeMillis() - timeout;
            System.out.print(timeout + "\n");
        }
        System.out.println("Buffered");
        for(int j = 0; j < COUNT; j++) {
            long timeout = System.currentTimeMillis();
            for (int i = 1; i < args.length; i++)
                Grep.bufferedSearch(args[i], searchString);
            timeout = System.currentTimeMillis() - timeout;
            System.out.print(timeout + "\n");
        }
    }
}
