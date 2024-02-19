package com.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.web.dto.DeliveryInfo;
import com.web.dto.MyOrderDTO;
import com.web.dto.MyOrderPageDTO;
import com.web.dto.OrderAdminDTO;
import com.web.dto.OrderDto;
import com.web.service.OrderService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {
	
	@Value("${iamport.key}")
    private String restApiKey;
    @Value("${iamport.secret}")
    private String restApiSecret;

    private IamportClient iamportClient;
    
    @Autowired
    private OrderService orderService;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(restApiKey, restApiSecret);
    }

//    @PostMapping("/order/payment")
//    public ResponseEntity<String> paymentComplete(@Login SessionUser sessionUser, @RequestBody List<OrderSaveDto> orderSaveDtos) throws IOException {
//        String orderNumber = String.valueOf(orderSaveDtos.get(0).getOrderNumber());
//        try {
//            Long userId = sessionUser.getUserIdNo();
//            paymentService.saveOrder(userId, orderSaveDtos);
//            log.info("결제 성공 : 주문 번호 {}", orderNumber);
//            return ResponseEntity.ok().build();
//        } catch (RuntimeException e) {
//            log.info("주문 상품 환불 진행 : 주문 번호 {}", orderNumber);
//            String token = refundService.getToken(apiKey, secretKey);
//            refundService.refundWithToken(token, orderNumber, e.getMessage());
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
    
    @PostMapping("/order/add")
    public ResponseEntity<String> paymentComplete(@RequestBody OrderDto orderdto, @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token){
    	try {
    		orderService.addOrder(orderdto, token);
    		return ResponseEntity.ok().build();
			
		} catch (RuntimeException e) {
          log.info("주문 상품 환불 진행 : 주문 번호 {}");
//          String pToken = refundService.getToken(apiKey, secretKey);
//          refundService.refundWithToken(token, orderNumber, e.getMessage());
          return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
      }
    }
 
 
    @PostMapping("/payment/validation/{imp_uid}")
    public IamportResponse<Payment> validateIamport(@PathVariable String imp_uid) throws IamportResponseException, IOException {
        IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
        log.info("결제 요청 응답. 결제 내역 - 주문 번호: {}", payment.getResponse().getMerchantUid());
        return payment;
    }
    
    @GetMapping("/order/loadDefaultDelivery")
    public DeliveryInfo loadDefaultDelivery(@RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token){
    	return orderService.loadDefaultDelivery(token);
    }
    
    @GetMapping("/order/loadDeliveryList")
    public List<DeliveryInfo> loadDeliveryList(@RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token){
    	return orderService.loadDeliveryList(token);
    }
    
    @GetMapping("/order/loadMyOrder")
    public MyOrderPageDTO loadMyOrder(@PageableDefault(size=10, sort="id", direction=Sort.Direction.DESC)Pageable pageable, 
    		@RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token){
    	return orderService.loadMyOrder(pageable, token);
    }
    
    // 추가
    @PostMapping("/adminOrder/getList")
    public Map<String, Object> getOrderList() {
    	Map<String, Object> map = new HashMap<>();
    	try {
				List<OrderAdminDTO> list = orderService.getOrderList();
				map.put("orderList", list);
				map.put("result", "Success");
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	map.put("result", "Failure");
    	return map;
    }
    @PostMapping("/adminOrder/getOrderDetail")
    public Map<String, Object> getOrderDetail(@RequestBody OrderAdminDTO id) {
    	System.out.println(id);
    	System.out.println("////////////////////////////////////////////////");
    	Map<String, Object> map = new HashMap<>();
    	try {
				List<OrderAdminDTO> list = orderService.getOrderDetailList(id.getId());
				map.put("odt", list);
				System.out.println(list);
				map.put("result", "Success");
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	map.put("result", "Failure");
    	return map;
    	
    	
    }
    
    @PostMapping("/adminOrder/approval")
    public String oderDetailApproval(@RequestBody OrderAdminDTO dto) {
    	System.out.println(dto);
    	String res = orderService.approval(dto);
    	return res;
    }
}
