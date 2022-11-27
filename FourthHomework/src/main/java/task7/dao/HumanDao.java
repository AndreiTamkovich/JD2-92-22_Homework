package task7.dao;

import task7.model.Human;

public interface HumanDao {
    void create(Human human);

    Human findById(Integer id);

    void update(Human human);

    void delete(Human human);
}
