package org.sid.bilingservice;

import org.sid.bilingservice.entities.Bill;
import org.sid.bilingservice.entities.ProductItems;
import org.sid.bilingservice.model.Customer;
import org.sid.bilingservice.model.Product;
import org.sid.bilingservice.repository.BillRepository;
import org.sid.bilingservice.repository.ProductItemRepository;
import org.sid.bilingservice.service.CustomerRestClient;
import org.sid.bilingservice.service.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BilingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductRestClient productRestClient){
        return args -> {
            Collection<Product> products=productRestClient.allProducts().getContent();
            Long customerId=1L;
            Customer customer=customerRestClient.findCustomerById(customerId);
            if (customer==null) throw new RuntimeException("customer not found");
            Bill bill = new Bill();
            bill.setBillDate(new Date());
            bill.setCustomerId(customerId);
            Bill savedBill =billRepository.save(bill);
            products.forEach(product -> {
                ProductItems productItems=new ProductItems();
                productItems.setBill(savedBill);
                productItems.setProductId(product.getId());
                productItems.setQuantity(1+new Random().nextInt(10));
                productItems.setPrice(product.getPrice());
                productItems.setDiscount(Math.random());
                productItemRepository.save(productItems);
            });
        };
    }

}
