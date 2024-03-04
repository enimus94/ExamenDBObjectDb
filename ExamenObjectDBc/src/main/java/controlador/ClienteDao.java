package controlador;

import model.Cliente;
import util.ObjectDBUtil;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClienteDao {
    public ClienteDao() {

    }

    public static void insertarCliente(Cliente cliente) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void getCleinte(Long id) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.id = :id");
            query.setParameter("id", id);
            List<Cliente> clientes = query.getResultList();

            if (clientes.isEmpty()) {
                System.out.println("No se encontró ningún cliente con el ID proporcionado.");
            } else {
                Cliente cliente = clientes.get(0);
                System.out.println("Información del cliente:");
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("Toatal de ventas: " + cliente.getTotalVentas());
                System.out.println("Estado: " + cliente.getEstado());

            }
        }finally {
            em.close();
        }
    }
    public static void listarMejoresClientes (Long cantidad){
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.estado = 'activo' AND c.totalVentas > :cantidad");
            query.setParameter("cantidad", cantidad);
            List<Cliente> clientes = query.getResultList();

            if (clientes.isEmpty()) {
                System.out.println("No se encontraron clientes activos con un total de ventas mayor a " + cantidad);
            } else {
                System.out.println("Clientes activos con un total de ventas mayor a " + cantidad + ":");
                for (Cliente cliente : clientes) {
                    System.out.println("Nombre: " + cliente.getNombre());
                    System.out.println("Total de Ventas: " + cliente.getTotalVentas());
                    System.out.println("Estado" + cliente.getEstado());
                }
            }

            em.getTransaction().commit();
        }finally {
            em.close();
        }

    }
    public static void estadisticas(){
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();

            /**
             * Esta funcion se encarga del total de ventas de todos los clientes
             */
            Query totalQuery = em.createQuery("SELECT SUM(c.totalVentas) FROM Cliente c");
            Long totalVentas = (Long) totalQuery.getSingleResult();
            System.out.println("Total de ventas entre todos los clientes: " + totalVentas);

            /**
             * Se encarga del pormedio de ventas
             */
            Query countQuery = em.createQuery("SELECT COUNT(c) FROM Cliente c");
            Long numClientes = (Long) countQuery.getSingleResult();
            double promedioVentas = totalVentas / (double) numClientes;
            System.out.println("Promedio de ventas de los clientes: " + promedioVentas);

            /**
             * te dice clientes inactivos con ventas mayores a 0
             */
            Query inactivoQuery = em.createQuery("SELECT COUNT(c) FROM Cliente c WHERE c.estado = 'inactivo' AND c.totalVentas > 0");
            Long numClientesInactivos = (Long) inactivoQuery.getSingleResult();
            System.out.println("Cantidad de clientes inactivos con ventas mayores a 0: " + numClientesInactivos);

            em.getTransaction().commit();
        }finally {
            em.close();
        }

    }
}