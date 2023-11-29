package com.ltp.gradesubmission;

import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.ltp.gradesubmission.controller.GradeController;

@SpringBootTest
@AutoConfigureMockMvc
class GradeSubmissionApplicationTests {

	@Autowired
	private GradeController gradeController;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(gradeController);
		assertNotNull(mockMvc);
	}

	@Test
	public void testShowGradeForm(){
		RequestBuilder req = MockMvcRequestBuilders.get("/?id=123");
		try {
			mockMvc.perform(req).andExpect(status().is2xxSuccessful())
			.andExpect(view().name("form"))
			.andExpect(model().attributeExists("grade"))
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSuccessfulSubmission(){
		RequestBuilder req = MockMvcRequestBuilders.post("/handleSubmit")
		.param("id", "123")
		.param("name", "Ali")
		.param("subject", "Math")
		.param("score", "C")
		;
		try {
			mockMvc.perform(req).andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/grades"))
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
