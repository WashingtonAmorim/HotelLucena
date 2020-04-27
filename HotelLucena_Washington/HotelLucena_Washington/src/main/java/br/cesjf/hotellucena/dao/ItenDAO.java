package br.cesjf.hotellucena.dao;

import br.cesjf.hotellucena.model.Iten;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.cesjf.hotellucena.util.PersistenceUtil;

public class ItenDAO {

    public static ItenDAO itensDAO;

    public static ItenDAO getInstance() {
        if (itensDAO == null) {
            itensDAO = new ItenDAO();
        }
        return itensDAO;
    }

    public Iten buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Itens a where a.item =:item ");
        query.setParameter("nome", nome);

        List<Iten> itens = query.getResultList();
        if (itens != null && itens.size() > 0) {
            return itens.get(0);
        }

        return null;
    }

    public List<Iten> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Itens As a");
        return query.getResultList();
    }

    public void remover(Iten item) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        if (!em.contains(item)) {
            item = em.merge(item);
        }
        em.remove(item);
        em.getTransaction().commit();
    }

    public Iten persistir(Iten item) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            item = em.merge(item);
            em.getTransaction().commit();
            System.out.println("Registro Itens gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    public void removeAll() {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("delete from Itens ");
        query.executeUpdate();
        em.getTransaction().commit();
    }

}
