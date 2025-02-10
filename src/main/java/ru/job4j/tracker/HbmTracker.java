package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sessionFactory = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();

        } catch (Exception ex) {
            session.getTransaction().rollback();

        } finally {
            session.close();
        }

        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Session session = sessionFactory.openSession();
        boolean result = true;
        try {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();

        } catch (Exception ex) {
            session.getTransaction().rollback();
            result = false;

        } finally {
            session.close();
        }

        return result;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();

        } catch (Exception ex) {
            session.getTransaction().rollback();

        } finally {
            session.close();
        }
    }

    @Override
    public List<Item> findAll() {
        Session session = sessionFactory.openSession();
        List<Item> result = null;
        try {
            session.beginTransaction();
            result = session.createQuery("FROM Item")
                    .getResultList();
            session.getTransaction().commit();

        } catch (Exception ex) {
            session.getTransaction().rollback();

        } finally {
            session.close();
        }

        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sessionFactory.openSession();
        List<Item> result = null;
        try {
            session.beginTransaction();
            result = session.createQuery(
                            "FROM Item I WHERE I.name = :name")
                    .setParameter("name", key)
                    .getResultList();
            session.getTransaction().commit();

        } catch (Exception ex) {
            session.getTransaction().rollback();

        } finally {
            session.close();
        }

        return result;
    }

    @Override
    public Item findById(int id) {
        Session session = sessionFactory.openSession();
        Item result = null;
        try {
            session.beginTransaction();
            result = session.get(Item.class, id);
            session.getTransaction().commit();

        } catch (Exception ex) {
            session.getTransaction().rollback();

        } finally {
            session.close();
        }

        return result;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
