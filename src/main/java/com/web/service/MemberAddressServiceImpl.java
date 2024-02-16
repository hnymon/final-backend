package com.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.domain.Member;
import com.web.domain.MemberDeliveryAddress;
import com.web.repository.MemberAddressRepository;

@Service
public class MemberAddressServiceImpl implements MemberAddressService{
	
	@Autowired
	private MemberAddressRepository memberAddressRepository;
	
	@Override
	public String saveAdr(MemberDeliveryAddress memberDeliveryAddress, Member currentMember) {
	    try {
	        if (memberDeliveryAddress == null || currentMember == null) {
	            throw new IllegalArgumentException("MemberDeliveryAddress 또는 currentMember가 null입니다.");
	        }
	        memberDeliveryAddress.setMember(currentMember);
	        System.out.println(memberDeliveryAddress);
	        memberAddressRepository.save(memberDeliveryAddress);
	        return "Save";
	    } catch (IllegalArgumentException e) {
	        // 예외 처리: 인자로 전달된 값이 유효하지 않을 때
	        e.printStackTrace();
	        return "Error: " + e.getMessage();
	    } catch (Exception e) {
	        // 예외 처리: 그 외 예외 발생 시
	        e.printStackTrace();
	        return "Error: 저장 중 오류가 발생했습니다.";
	    }
	}
	
	
	
	@Override
	public String editAdr(MemberDeliveryAddress memberDeliveryAddress, Member currentMember) {
		// TODO Auto-generated method stub
		System.out.println(memberDeliveryAddress);
		try {
			if (memberDeliveryAddress == null || currentMember == null) {
	            throw new IllegalArgumentException("MemberDeliveryAddress 또는 currentMember가 null입니다.");
	        }
			memberDeliveryAddress.setMember(currentMember);
			System.out.println(memberDeliveryAddress);
	        memberAddressRepository.save(memberDeliveryAddress);
	        return "Save";
	    } catch (IllegalArgumentException e) {
	        // 예외 처리: 인자로 전달된 값이 유효하지 않을 때
	        e.printStackTrace();
	        return "Error: " + e.getMessage();
	    } catch (Exception e) {
	        // 예외 처리: 그 외 예외 발생 시
	        e.printStackTrace();
	        return "Error: 저장 중 오류가 발생했습니다.";
	    }
	}



	@Override
	public String removeAdr(MemberDeliveryAddress memberDeliveryAddress) {
		try {
	        memberAddressRepository.delete(memberDeliveryAddress);
	        return "Remove";
	    } catch (IllegalArgumentException e) {
	        // 예외 처리: 인자로 전달된 값이 유효하지 않을 때
	        e.printStackTrace();
	        return "Error: " + e.getMessage();
	    } catch (Exception e) {
	        // 예외 처리: 그 외 예외 발생 시
	        e.printStackTrace();
	        return "Error: 삭제 중 오류가 발생했습니다.";
	    }
	}

	@Override
	public String setDefaultAddress(MemberDeliveryAddress memberDeliveryAddress) {
		// TODO Auto-generated method stub
		try {
			MemberDeliveryAddress defAdr = memberAddressRepository.findById(memberDeliveryAddress.getAddrNum()).get();
			System.out.println(defAdr);
			if(defAdr.getIsDefault()) {
				System.out.println(defAdr.getIsDefault());
				return "Already";
			}
			Long memberNum = defAdr.getMember().getMemberNum();
			List<MemberDeliveryAddress> list = memberAddressRepository.findAllByMemberMemberNum(memberNum);
			for(MemberDeliveryAddress adr : list) {
				adr.setDefault(false);
			}
			memberAddressRepository.saveAll(list);
			defAdr.setDefault(true);
			memberAddressRepository.save(defAdr);
			return "Success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failure";
	}



	@Override
	public List<MemberDeliveryAddress> findByMemberMemberNum(Long memberNum) {
		// TODO Auto-generated method stub
		List<MemberDeliveryAddress> addrs = memberAddressRepository.findAllByMemberMemberNum(memberNum);
		return addrs;
	}
	
	
}
