package Services;

import models.User;

public interface APIService {
    public User create();

    public int read();

    public User update();

    public User delete();

    public String getMethod();
}
