package com.juliobeani.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.juliobeani.cursomc.domain.Address;
import com.juliobeani.cursomc.domain.Category;
import com.juliobeani.cursomc.domain.City;
import com.juliobeani.cursomc.domain.Client;
import com.juliobeani.cursomc.domain.Order;
import com.juliobeani.cursomc.domain.OrderItem;
import com.juliobeani.cursomc.domain.PagamentoComBoleto;
import com.juliobeani.cursomc.domain.PagamentoComCartao;
import com.juliobeani.cursomc.domain.Payment;
import com.juliobeani.cursomc.domain.Product;
import com.juliobeani.cursomc.domain.State;
import com.juliobeani.cursomc.domain.enums.ClientType;
import com.juliobeani.cursomc.domain.enums.PaymentStatus;
import com.juliobeani.cursomc.repositories.AddressRepository;
import com.juliobeani.cursomc.repositories.CategoryRepository;
import com.juliobeani.cursomc.repositories.CityRepository;
import com.juliobeani.cursomc.repositories.ClientRepository;
import com.juliobeani.cursomc.repositories.OrderItemRepository;
import com.juliobeani.cursomc.repositories.OrderRepository;
import com.juliobeani.cursomc.repositories.PaymentRepository;
import com.juliobeani.cursomc.repositories.ProductRepository;
import com.juliobeani.cursomc.repositories.StateRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired 
	private CityRepository cityRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		
		// Faz a cidade reconhecer o estado
		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);
		
		// Faz o estado reconhecer as cidades adicionando-as na lista
		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.PESSOAFISICA);
		// Adiciona os numeros de telefone no cliente (tabela fraca)
		cli1.getPhoneNumbers().addAll(Arrays.asList("27363323", "93838393"));
		
		// Cria os enderecos e faz o mesmo conhecer o cliente e a cidade
		Address addr1 = new Address(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Address addr2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		// Faz o cliente conhecer os enderecos
		cli1.getAddresses().addAll(Arrays.asList(addr1, addr2));
		
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(addr1, addr2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Order order1 = new Order(null,sdf.parse("30/09/2021 10:32"), cli1, addr1);
		Order order2 = new Order(null,sdf.parse("10/10/2021 19:35"), cli1, addr2);
		
		Payment pay1 = new PagamentoComCartao(null, PaymentStatus.QUITADO, order1, 6);
		order1.setPayment(pay1);
		
		Payment pay2 = new PagamentoComBoleto(null, PaymentStatus.PENDENTE, order2, sdf.parse("20/07/2017 00:00"), null);
		order2.setPayment(pay2);
		
		cli1.getOrders().addAll(Arrays.asList(order1, order2));
		
		orderRepository.saveAll(Arrays.asList(order1, order2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
		OrderItem orderItem1 = new OrderItem(order1, p1, 0.00, 1, 2000.00);
		OrderItem orderItem2 = new OrderItem(order1, p3, 0.00, 2, 80.00);
		OrderItem orderItem3 = new OrderItem(order2, p2, 100.00, 1, 800.00);
		
		order1.getItens().addAll(Arrays.asList(orderItem1, orderItem2));
		order2.getItens().addAll(Arrays.asList(orderItem3));
		
		p1.getItens().addAll(Arrays.asList(orderItem1));
		p2.getItens().addAll(Arrays.asList(orderItem3));
		p3.getItens().addAll(Arrays.asList(orderItem2));
		
		orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3));
	}
}