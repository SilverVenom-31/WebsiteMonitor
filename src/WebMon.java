//Created by @AkhilD on 1/8/21.


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class WebMon {

    public static void main(String args[]) {
        System.out.println("\nHello Friend!!! Lets work...\n");

        ArrayList<String> aList = new ArrayList<>();
        String line = "";
        int index = 0;
        int naming = 1;
        try {
            // File file = new File("/Users/akhil/Documents/ACTIVITY/WebsiteMonitor/Docs/Urls.txt");
            File file = new File(args[0]+"/Docs/Urls.txt");
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File does not exists...\nCreating .txt file in path:\n" + file.getCanonicalPath() + "\nNew File created....\nPlease insert Urls for monitoring.");
                System.exit(10);
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                //System.out.println(line);
                if (line.contains("*")) {
                    index = line.indexOf('*');
                    aList.add(line.substring(index + 2));
                }
            }
            System.out.println("\nWebsites to Monitor:");
            for (String s : aList) {
                System.out.println("\n" + naming + ". URL: " + s);
                URL url = new URL(s);
                //Retrieving the contents
                Scanner sct = new Scanner(url.openStream());
                // StringBuffer to store result
                StringBuffer sb = new StringBuffer();
                while (sct.hasNext()) {
                    sb.append(sct.next());
                }
                //String buffer to String
                String result = sb.toString();

                //Reducing HTML tags
                result = result.replaceAll("<[^>]*>", "");
                System.out.println("Extracted website content");

                File fd = new File(args[0]+"/Docs/" + naming + ".txt");
                if (!fd.exists()) {
                    System.out.println("Writing content to file.");

                    FileWriter myWriter = new FileWriter(args[0]+"/Docs/" + naming + ".txt");
                    naming++;
                    myWriter.write(result);
                    myWriter.close();
                    System.out.println("Written....");
                } else {
                    Path fileName = Path.of(args[0]+"/Docs/" + naming + ".txt");
                    String stored = Files.readString(fileName);
                    if (!result.equals(stored)) {
                        System.out.println("Website Contents are changed!!! You may take a look");
                        //  System.out.println("New Content: " + result + " Previous Content: " + stored);
                        naming++;
                    } else {
                        System.out.println("No changes so far....");
                        naming++;
                    }
                }
            }
            System.out.println("\n....Good Bye!!!");
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
