package exam2;

public class Owner {
  private String id;
  private String name;

  public Owner(String initId, String initName) {
    this.id = initId;
    this.name = initName;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
