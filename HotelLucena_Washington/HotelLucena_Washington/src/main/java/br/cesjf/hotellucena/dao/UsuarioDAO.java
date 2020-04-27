package br.cesjf.hotellucena.dao;

import br.cesjf.hotellucena.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.cesjf.hotellucena.util.PersistenceUtil;

public class UsuarioDAO {

    public static UsuarioDAO usuarioDAO;

    public static UsuarioDAO getInstance() {
        if (usuarioDAO == null) {
            usuarioDAO = new UsuarioDAO();
        }
        return usuarioDAO;
    }

    public Usuario buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Usuarios a where a.name =:nome ");
        query.setParameter("nome", nome);

        List<Usuario> usuarios = query.getResultList();
        if (usuarios != null && usuarios.size() > 0) {
            return usuarios.get(0);
        }

        return null;
    }

    public List<Usuario> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Usuarios As a");
        return query.getResultList();
    }

    public void remover(Usuario usuario) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        if (!em.contains(usuario)) {
            usuario = em.merge(usuario);
        }
        em.remove(usuario);
        em.getTransaction().commit();
    }

    public Usuario persistir(Usuario usuario) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            usuario = em.merge(usuario);
            em.getTransaction().commit();
            System.out.println("Registro Usuarios gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public void removeAll() {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(" delete from Usuarios ");
        query.executeUpdate();
        em.getTransaction().commit();
    }

    public Usuario login(Usuario usuario) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Usuarios a where a.emailUsuario =:emailUsuario");
        query.setParameter("emailUsuario", usuario.getEmailUsuario());

        List<Usuario> usuarios = query.getResultList();
        if (usuarios != null && usuarios.size() > 0) {
            return usuarios.get(0);
        }

        return null;
    }

}
