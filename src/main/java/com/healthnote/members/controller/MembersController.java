package com.healthnote.members.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import com.healthnote.members.dao.MembersDAO;
import com.healthnote.members.service.MembersService;
import com.healthnote.vo.ChangeFixedScheduleDTO;
import com.healthnote.vo.FixedScheduleDTO;
import com.healthnote.vo.MemberAndFixedScheduleDTO;
import com.healthnote.vo.MemberDTO;

@Controller
public class MembersController {
	
	@Autowired
	private View jsonview;
	
	@Autowired 
	private MembersService MembersService;
	
	/*
	날 짜 : 2019. 8. 8.
	작성자 : 김 정 권
	기 능 : 좌측 Member클릭시 해당 접속 아이디(트레이너)의 모든 회원들에 대한 정보와 정적 시간표를 같이 DTO에 담아서 가져옴  
	*/
	@RequestMapping(value = "/getMemberAndFixedSchedule", method = RequestMethod.POST)
	public View getMemberAndFixedSchedule(HttpSession session, Model model) {
		
		String trainerId = (String) session.getAttribute("trainerId");
		ArrayList<MemberAndFixedScheduleDTO> list = MembersService.getMemberAndFixedSchedule(trainerId);
		
		model.addAttribute("memberlist", list);

		return jsonview;
	
	}
	
	
	/*
	날 짜 : 2019. 8. 8.
	작성자 : 김 정 권
	기 능 : Member(수강생) 삭제   
	*/
	@RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
	public View deleteMember(HttpSession session, Model model, @RequestBody Map<String, Object> data) {

		String phonenum = (String) data.get("memberId");
		int result = MembersService.deleteMember(phonenum);
		model.addAttribute("result", result);

		return jsonview;
	
	}
	
	/*
	날 짜 : 2019. 8. 8.
	작성자 : 김 정 권
	기 능 : Member(수강생) 기본정보 변경   
	*/
	@RequestMapping(value = "/changeMemberInfo", method = RequestMethod.POST)
	public View changeMemberInfo(HttpSession session, Model model, @RequestBody Map<String, Object> data) {

		MemberDTO paramdto = new MemberDTO();
		
		paramdto.setPhonenum((String) data.get("memberId"));
		paramdto.setName((String) data.get("name"));
		paramdto.setGender(Integer.parseInt((String) data.get("gender")));
		paramdto.setStart_date((String) data.get("start_date"));
		paramdto.setEnd_date((String) data.get("end_date"));
		paramdto.setUnusedpt(Integer.parseInt((String) data.get("unusedpt")));
		paramdto.setUsedpt(Integer.parseInt((String) data.get("usedpt")));
		paramdto.setEmail((String) data.get("email"));
		paramdto.setHeight(Integer.parseInt((String) data.get("height")));
		
		int result = MembersService.changeMemberInfo(paramdto);
		model.addAttribute("result", result);

		return jsonview;
		
	}
	
	
	/*
	날 짜 : 2019. 8. 9.
	작성자 : 김 정 권
	기 능 : Member(수강생) 기본정보 변경   
	*/
	@RequestMapping(value = "/insertMember", method = RequestMethod.POST)
	public View insertMember(HttpSession session, Model model, @RequestBody Map<String, Object> data) {
	
		MemberDTO paramdto = new MemberDTO();
		
		paramdto.setPhonenum((String) data.get("memberId"));
		paramdto.setName((String) data.get("name"));
		paramdto.setGender(Integer.parseInt((String) data.get("gender")));
		paramdto.setStart_date((String) data.get("start_date"));
		paramdto.setEnd_date((String) data.get("end_date"));
		paramdto.setUnusedpt(Integer.parseInt((String) data.get("unusedpt")));
		paramdto.setUsedpt(Integer.parseInt((String) data.get("usedpt")));
		paramdto.setEmail((String) data.get("email"));
		paramdto.setHeight(Integer.parseInt((String) data.get("height")));
		
		int result = MembersService.insertMember(paramdto);
		model.addAttribute("result", result);

		return jsonview;
	}
	
	
	/*
	날 짜 : 2019. 8. 9.
	작성자 : 김 정 권
	기 능 : Member(수강생) 신규 등록 후 고정 스케줄 삽입    
	*/
	@RequestMapping(value = "/insertFixedSchedule", method = RequestMethod.POST)
	public View insertFixedSchedule(HttpSession session, Model model, @RequestBody Map<String, Object> data) {
		
		FixedScheduleDTO paramdto = new FixedScheduleDTO();
		
		paramdto.setDay(Integer.parseInt((String) data.get("day")));
		paramdto.setPhonenum((String) data.get("phonenum"));
		paramdto.setStart_time((String) data.get("start_time"));
		paramdto.setEnd_time((String) data.get("end_time"));
		
		int result = MembersService.insertFixedSchedule(paramdto);
		model.addAttribute("result", result);
		
		return jsonview;
	}
	
	
	/*
	날 짜 : 2019. 8. 9.
	작성자 : 김 정 권
	기 능 : Member(수강생) 고정 스케줄 삭제     
	 */
	@RequestMapping(value = "/deleteFixedSchedule", method = RequestMethod.POST)
	public View deleteFixedSchedule(HttpSession session, Model model, @RequestBody Map<String, Object> data) {
		
		FixedScheduleDTO paramdto = new FixedScheduleDTO();
		
		paramdto.setDay(Integer.parseInt((String) data.get("day")));
		paramdto.setPhonenum((String) data.get("phonenum"));
		paramdto.setStart_time((String) data.get("start_time"));
		paramdto.setEnd_time((String) data.get("end_time"));
		
		int result = MembersService.deleteFixedSchedule(paramdto);
		model.addAttribute("result", result);
		
		return jsonview;
		
	}
	
	
	/*
	날 짜 : 2019. 8. 9.
	작성자 : 김 정 권
	기 능 : Member(수강생) 고정 스케줄 변경    
	 */
	@RequestMapping(value = "/changeFixedSchedule", method = RequestMethod.POST)
	public View changeFixedSchedule(HttpSession session, Model model, @RequestBody Map<String, Object> data) {
		
		ChangeFixedScheduleDTO paramdto = new ChangeFixedScheduleDTO();
		
		paramdto.setAfter_day(Integer.parseInt((String) data.get("after_day")));
		paramdto.setBefore_day(Integer.parseInt((String) data.get("before_day")));
		paramdto.setPhonenum((String) data.get("phonenum"));
		paramdto.setStart_time((String) data.get("start_time"));
		paramdto.setEnd_time((String) data.get("end_time"));
		
		int result = MembersService.changeFixedSchedule(paramdto);
		model.addAttribute("result", result);
		
		return jsonview;
	}
	
}









