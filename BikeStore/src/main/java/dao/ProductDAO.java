package dao;

import models.Customer;
import models.Product;

import java.rmi.RemoteException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductDAO extends GenericDAO<Product, Integer> implements IProductDAO {
    public ProductDAO() throws RemoteException{
        super(Product.class);
    }

    @Override
    public List<Product> getProductWithHighestPrice() throws RemoteException {
        String jpql = "SELECT MAX(p.listPrice) FROM Product p";
        Double maxPrice = (Double) em.createQuery(jpql).getSingleResult();

        jpql = "SELECT p FROM Product p WHERE p.listPrice = :maxPrice";
        return em.createQuery(jpql, aClass).setParameter("maxPrice", maxPrice).getResultList();
    }

    @Override
    public List<Product> getProductHaveNotSoldYet() throws RemoteException {
        String jpql = "SELECT distinct p FROM Product p WHERE p.id NOT IN (SELECT distinct oi.product.id FROM OrderItem oi)";
        return em.createQuery(jpql, aClass).getResultList();
    }

    @Override
    public Map<Product, Long> getTotalQuantityOfEachProductSold() throws RemoteException {
        String jpql = "SELECT p, SUM(oi.quantity) FROM Product p LEFT JOIN p.orderItems oi GROUP BY p";

        List<Object[]> result = em.createQuery(jpql).getResultList();

        Map<Product, Long> map = new LinkedHashMap<>();

        for (Object[] o : result) {
            Product product = (Product) o[0];
            Long count = (Long) o[1];
            map.put(product, (count == null ? 0l : count));
        }

        return map;
    }


}
