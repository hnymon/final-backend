package com.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.web.domain.Member;
import com.web.domain.MemberDeliveryAddress;
import com.web.service.MemberAddressService;
import com.web.service.TokenService;
 
@RestController
public class MemberAddressController {
	
	@Autowired
	private MemberAddressService addressService;
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/addAddress")
	public Map<String, Object> addAddress(@RequestBody MemberDeliveryAddress memberDeliveryAddress, @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token) {
		Member currentMember = tokenService.getMemberByMemberNum(token);
		String res = addressService.saveAdr(memberDeliveryAddress, currentMember);
		Map<String, Object> map = new HashMap<>();
		map.put("result", res);
		return map;
	}
	@PostMapping("/editAdr")
	public Map<String, Object> editAdr(@RequestBody MemberDeliveryAddress memberDeliveryAddress, @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token) {
		Member currentMember = tokenService.getMemberByMemberNum(token);
		String res = addressService.editAdr(memberDeliveryAddress, currentMember);
		Map<String, Object> map = new HashMap<>();
		map.put("result", res);
		return map;
	}
	
	@PostMapping("/deleteAdr")
	public Map<String, Object> deleteAdr(@RequestBody MemberDeliveryAddress memberDeliveryAddress){
		Map<String, Object> map = new HashMap<>();
		String res = addressService.removeAdr(memberDeliveryAddress);
		map.put("result", res);
		return map;
	} 
	
	@PostMapping("/setDefaultAddress")
	public Map<String, Object> setDefaultAddress(@RequestBody MemberDeliveryAddress memberDeliveryAddress) {
		Map<String, Object> map = new HashMap<>();
		String res = addressService.setDefaultAddress(memberDeliveryAddress);
		map.put("result", res);
		return map;
	}
	
}
