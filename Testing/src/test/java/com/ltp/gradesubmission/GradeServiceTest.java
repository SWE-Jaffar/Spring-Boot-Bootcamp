package com.ltp.gradesubmission;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.ltp.gradesubmission.pojo.Grade;
import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.service.GradeService;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


    @RunWith(MockitoJUnitRunner.class)
    public class GradeServiceTest {

        @Mock
        private GradeRepository gradeRepository;

        @InjectMocks
        private GradeService gradeService;

        @Test
        public void getGradesFromRepository() {
            when(gradeRepository.getGrades()).thenReturn(Arrays.asList(new Grade("Ali", "B", "Math"), new Grade("Jafer", "A+", "Physics")));
        
        //The method under test
        List<Grade> result = gradeService.getGrades();

        //Verify the result
        assertEquals("Ali", result.get(0).getName());
        
    }

    @Test
    public void gradeIndexTest(){
        Grade grade = new Grade("Ali", "B", "Math");
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);
        int valid = gradeService.getGradeIndex(grade.getId());
        int notValid = gradeService.getGradeIndex("123");
        assertEquals(0,valid);
        assertEquals(Constants.NOT_FOUND, notValid);

    }
    
    }
