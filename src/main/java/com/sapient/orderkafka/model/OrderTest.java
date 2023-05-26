package com.sapient.orderkafka.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ReflectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class OrderTest {

    static Map<String,Double> products = Map.of("Apple", 100.50,"Mango",60.70,"Grapes",75.25,"Orange",30.50);

    static ObjectMapper mapper = new ObjectMapper();
    static Random random = new Random();

    public static void main(String[] args) throws JsonProcessingException {
        Integer[] arr = {1,2,5,6,3,6,1,4,6};
        ArrayList<Integer> al = new ArrayList<>();
        al.addAll(Arrays.asList(arr));
        Iterator<Integer> iterator = al.iterator();
//        while (iterator.hasNext())
//            System.out.println(iterator.next());
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        System.out.println(al);
        //Order order = getNewOrder();
      //System.out.println(mapper.writeValueAsString(order));
    }

    private static Order getNewOrder() throws JsonProcessingException {
        Order order = new Order();
        order.setRemarks("testing order");
        order.setOrderDetailsList(getRandomOrderDetails(random.nextInt(1,4)));
        order.setAmount(order.getOrderDetailsList().stream().mapToDouble(m->m.getPrice()).sum());
        return order;
    }

    private static List<OrderDetails> getRandomOrderDetails(int nextInt) {
        List<String> lables = List.copyOf(products.keySet());
        List<OrderDetails> ls = new ArrayList<>();
        while (nextInt>0){
            OrderDetails orderDetails = new OrderDetails();
            String product = lables.get(random.nextInt(1, products.size()));
            Double price = products.get(product);
            orderDetails.setProductName(product);
            int qty = random.nextInt(5, 30);
            orderDetails.setQuantity(qty);
            orderDetails.setPrice(qty*price);
            ls.add(orderDetails);
            nextInt--;
        }
        return ls;
    }

}
