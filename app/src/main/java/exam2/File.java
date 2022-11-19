package exam2;

import java.util.Date;

public class File extends FolderItem {
  private String extension;

  public File(
    String initName,
    Date initDate,
    int initSize,
    Owner initOwner,
    String initExtension
  ) {
    super(initName, initDate, initSize, initOwner);
    this.extension = initExtension;
  }

  public String getExtension() {
    return extension;
  }

  public String toString() {
    return String.format(
      "%s %s %s %s",
      this.getDate(),
      this.getSize(),
      this.getOwner(),
      this.getName() + "." + extension
    );
  }
}
