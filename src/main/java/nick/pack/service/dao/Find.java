package nick.pack.service.dao;

import java.util.List;

public interface Find<Entity, Key> {
    List<Entity> findByAll();
//    Entity findById(Key key);
}
