package br.cesjf.hotellucena.dao;

import br.cesjf.hotellucena.model.Apartamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.cesjf.hotellucena.util.PersistenceUtil;

public class ApartamentoDAO {

    public static ApartamentoDAO usuarioDAO;

    public static ApartamentoDAO getInstance() {
        if (usuarioDAO == null) {
            usuarioDAO = new ApartamentoDAO();
        }
        return usuarioDAO;
    }
    
    public Apartamento buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Apartamento a where a.name =:nome ");
        query.setParameter("nome", nome);

        List<Apartamento> apartamento = query.getResultList();
        if (apartamento != null && apartamento.size() > 0) {
            return apartamento.get(0);
        }

        return null;
    }

    public List<Apartamento> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Apartamento As a");
        return query.getResultList();
    }

    public List<Apartamento> buscarApartamentoeInstancia() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct a from Apartamento a group by a.name");
        return query.getResultList();
    }

        public List<Apartamento> buscarApartamentoeTypeInstancia() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct a from Apartamento a group by a.typeApartamento");
        return query.getResultList();
    }

        public void remover(Apartamento usuario) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        if (!em.contains(usuario)) {
            usuario = em.merge(usuario);
        }
        em.remove(usuario);
        em.getTransaction().commit();
    }

    public Apartamento persistir(Apartamento usuario) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            usuario = em.merge(usuario);
            em.getTransaction().commit();
            System.out.println("Registro Apartamento gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from Apartamento ");
       query.executeUpdate();
       em.getTransaction().commit();
    }

}
