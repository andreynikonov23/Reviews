package nick.pack.service.dao;

import nick.pack.model.Rating;
import nick.pack.model.Review;
import nick.pack.model.User;

public interface DAO<Entity, Key> extends Find<Entity, Key> {
    void saveAndFlush(Entity entity);
    void delete(Entity entity);
}
