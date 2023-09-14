package nick.pack.service.dao;

public interface DAO<Entity, Key> extends Find<Entity, Key> {
    void saveAndFlush(Entity entity);
    void delete(Entity entity);
}
