package exam2;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Folder extends FolderItem implements Iterable<FolderItem> {
  private List<FolderItem> folderItemList;

  public Folder(
    String initName,
    Date initDate,
    int initSize,
    Owner initOwner,
    List<FolderItem> initFolderItemList
  ) {
    super(initName, initDate, initSize, initOwner);
    this.folderItemList = initFolderItemList;
  }

  @Override
  public Iterator<FolderItem> iterator() {
    return folderItemList.iterator();
  }

  public void addFolderItem(FolderItem folderItem) {
    folderItemList.add(folderItem);
  }

  public FolderItem getFolderItem(String name) {
    return folderItemList.stream()
      .filter(f -> f.getName().equals(name))
      .findFirst()
      .orElse(null);
  }

  public int getNumberOfFolderItems() {
    return folderItemList.stream()
      .map(f -> {
        if (f instanceof Folder) {
          // TODO: check this with nested folders
          return ((Folder)f).getNumberOfFolderItems() + 1; // +1 for the folder itself
        } else {
          return 1;
        }
      })
      .reduce(0, (a, b) -> a + b);
  }

  public String toString() {
    return String.format(
      "%s %s %s %s (%s)",
      this.getDate(),
      this.getSize(),
      this.getOwner(),
      this.getName() + "/",
      this.getNumberOfFolderItems()
    );
  }
}
