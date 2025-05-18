package dao;

import models.Customer;

import java.rmi.RemoteException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomerDAO extends GenericDAO<Customer, Integer> implements ICustomerDAO {
    public CustomerDAO() throws RemoteException {
        super(Customer.class);
    }

    @Override
    public Map<String, Integer> getNumberCustomerByState() throws RemoteException {
        String jpql = "SELECT c.address.state, COUNT(c) FROM Customer c GROUP BY c.address.state";

        List<Object[]> result = em.createQuery(jpql).getResultList();
        Map<String, Integer> map = new LinkedHashMap<>();
        for (Object[] o : result) {
            String state = (String) o[0];
            Long count = (Long) o[1];
            map.put(state, count.intValue());
        }

        return map;
    }
}
