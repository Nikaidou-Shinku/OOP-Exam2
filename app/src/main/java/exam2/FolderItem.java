package exam2;

import java.util.Date;

public class FolderItem {
  private String name;
  private Date date;
  private int size;

  private Owner owner;

  public FolderItem(
    String initName,
    Date initDate,
    int initSize,
    Owner initOwner
  ) {
    this.name = initName;
    this.date = initDate;
    this.size = initSize;
    this.owner = initOwner;
  }

  public String getName() {
    return name;
  }

  public int getSize() {
    return size;
  }

  public Date getDate() {
    return date;
  }

  public Owner getOwner() {
    return owner;
  }

  public boolean equals(Object o) {
    if (o instanceof FolderItem) {
      FolderItem rhs = (FolderItem)o;
      return name.equals(rhs.name);
    }

    return false;
  }
}
