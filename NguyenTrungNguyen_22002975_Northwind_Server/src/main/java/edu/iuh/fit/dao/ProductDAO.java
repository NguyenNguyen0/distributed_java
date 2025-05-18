package edu.iuh.fit.dao;

import edu.iuh.fit.model.Product;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProductDAO extends GenericDAO<Product, Integer> {
    public ProductDAO(Class<Product> clazz) {
        super(clazz);
    }

    public ProductDAO(EntityManager em, Class<Product> clazz) {
        super(em, clazz);
    }

//    Find the list of products with the highest price
    public List<Product> listProductsWithTheHighestPrice(){
        String jpql = "select p from Product p " +
                "where p.listPrice = (select max(p.listPrice) " +
                                        "from Product p)";

        return em.createQuery(jpql, Product.class)
                .getResultList();
    }



//  Find the list of products with the highest price
    public List<Product> listProductsWithTheHighestPrice2(){
        String sql = "select * from products p " +
                "where p.list_price = (select max(list_price) " +
                                        "from products)";

        return em.createNativeQuery(sql, Product.class)
                .getResultList();
    }

    //    Find a list of products that have not been sold yet.
    public List<Product> listProductsNotBeenSold(){
        String jpql = "select p from Product p " +
                "where SIZE(p.items) = 0";
        return em.createQuery(jpql, Product.class)
                .getResultList();
    }

    public List<Product> listProductsNotBeenSold2(){
        String jpql = "select p from Product p " +
                "left join OrderItem  oi on p.id = oi.product.id " +
                "where oi.product.id is null";

        return em.createQuery(jpql, Product.class)
                .getResultList();
    }

    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO(Product.class);
        productDAO.listProductsNotBeenSold2()
                .forEach(p -> System.out.println(p));
//        productDAO.listProductsWithTheHighestPrice()
//                .forEach(p -> System.out.println(p));
//        Product product = productDAO.findById(4);
//        System.out.println(product);

//        productDAO.getAll()
//                .forEach(p -> System.out.println(p));
    }
}
