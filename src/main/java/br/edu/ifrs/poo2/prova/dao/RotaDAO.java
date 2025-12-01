package br.edu.ifrs.poo2.prova.dao;

import br.edu.ifrs.poo2.prova.entity.Rota;
import br.edu.ifrs.poo2.prova.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class RotaDAO {

    public void salvar(Rota rota) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(rota); // Salva ou Atualiza
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Rota> listarTodas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Traz todas as rotas e seus pontos
            // 'distinct r' evita duplicar a rota na lista por causa do join
            return session.createQuery("select distinct r from Rota r left join fetch r.pontos", Rota.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}