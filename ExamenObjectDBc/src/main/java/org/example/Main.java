package org.example;

import controlador.ClienteDao;
import model.Cliente;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Cayetano", 500L, "activo");
        Cliente cliente2 = new Cliente("Jhon", 1200L, "activo");
        Cliente cliente3 = new Cliente("Francisco", 800L, "inactivo");
        Cliente cliente4 = new Cliente("Maite", 1500L, "activo");
        Cliente cliente5 = new Cliente("Jose Antonio", 200L, "activo");


        ClienteDao.insertarCliente(cliente1);
        ClienteDao.insertarCliente(cliente2);
        ClienteDao.insertarCliente(cliente3);
        ClienteDao.insertarCliente(cliente4);
        ClienteDao.insertarCliente(cliente5);

        /**
         * me muestra la informacion del cliente del id 1
         */
        System.out.println("Información del cliente con ID 1:");
        ClienteDao.getCleinte(1L);

        /**
         * muestra clientes activos con una venta mayor a 1000
         */
        System.out.println("\nListado de clientes activos con un total de ventas mayor a 1000:");
        ClienteDao.listarMejoresClientes(1000L);

        /**
         * mostrar extadisticas generales
         */
        System.out.println("\nEstadísticas generales:");
        ClienteDao.estadisticas();

    }
}