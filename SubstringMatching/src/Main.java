import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import parcs.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        //String text = "Romeo and Romeo";
        Path filePath = Path.of("input");
        Path numPointsPath = Path.of("numPoints"); 
        String text = Files.readString(filePath);
        String pattern = "Romeo";
        int numPoints = Integer.parseInt(Files.readAllLines(numPointsPath).get(0));

        task curtask = new task();
        curtask.addJarFile("SubstringMatcher.jar");

        AMInfo info = new AMInfo(curtask, null);

        point[] points = new point[numPoints];
        channel[] channels = new channel[numPoints];

        int textLen = text.length();
        int patternLen = pattern.length();
        int chunkSize = (textLen - patternLen + 1) / numPoints;

        long start = System.nanoTime();

        for (int i = 0; i < numPoints; i++) {
            points[i] = info.createPoint();
            channels[i] = points[i].createChannel();

            points[i].execute("SubstringMatcher");
            
            if(i != numPoints - 1){
                channels[i].write(text.substring(i*chunkSize, (i + 1)*chunkSize + patternLen - 1));
            }
            else{
                channels[i].write(text.substring(i*chunkSize, textLen));
            }
            channels[i].write(pattern);
        }

        System.out.println("Waiting for result...");

        List<Integer> res = new ArrayList<Integer>();

        for (int i = 0; i < numPoints; i++) {
            List<Integer> partialResult = (List<Integer>)channels[i].readObject();
            
            for(int index : partialResult){
                res.add(i*chunkSize + index);
            }

            points[i].delete();
        }

        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("Execution time: " + timeElapsed + " ns");

        for(int index : res){
            System.out.print(index);
            System.out.print(" ");
        }

        curtask.end();
    }
}
