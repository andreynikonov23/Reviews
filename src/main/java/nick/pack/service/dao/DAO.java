package nick.pack.service.dao;

import java.util.List;

public interface DAO<Entity, Key> {
    List<Entity> findByAll();
    Entity findById(Key key);
    void saveAndFlush(Entity entity);
    void delete(Entity entity);
}
