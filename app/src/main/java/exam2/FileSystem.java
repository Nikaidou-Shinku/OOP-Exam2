package exam2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public class FileSystem {
  private List<FolderItem> folderItemList;

  public FileSystem() {
    this.folderItemList = new ArrayList<>();
  }

  public void loadFolderItemFromFile(String fileName) {
    List<SimpleEntry<FolderItem, String>> folderItems = new ArrayList<>();
    HashMap<String, Folder> folderMap = new HashMap<>();

    Path path = Paths.get(fileName);

    try {
      Files.readAllLines(path).stream()
        .forEach(line -> {
          String[] parts = line.split("_");

          if (parts.length == 5 && parts[0].equals("Folder")) { // handle folder
            Folder item = new Folder(
              parts[2],
              new Date(),
              4096,
              new Owner(parts[3], parts[4]),
              new ArrayList<>()
            );

            folderItems.add(new SimpleEntry<>(item, parts[1]));
            if (parts[1].equals("/")) {
              folderMap.put("/" + parts[2], item);
            } else {
              folderMap.put(parts[1] + "/" + parts[2], item);
            }
          }

          if (parts.length == 6 && parts[0].equals("File")) { // handle file
            folderItems.add(
              new SimpleEntry<>(
                new File(
                  parts[2],
                  new Date(),
                  0,
                  new Owner(parts[3], parts[4]),
                  parts[5]
                ),
                parts[1]
              )
            );
          }

          // the two statements above may be clearer if we take them out of the control flow
          // maybe use lambda expressions :)
        });
    } catch (Exception e) {
      e.printStackTrace();
    }

    folderItems.forEach(pair -> {
      String parentPath = pair.getValue();
      FolderItem item = pair.getKey();

      if (!parentPath.equals("/"))
        folderMap.get(parentPath).addFolderItem(item);

      this.folderItemList.add(item);
    });
  }

  public void displayFolderItemInfo() {
    folderItemList.forEach(item -> {
      System.out.println(item);
    });
  }
}
