package club.codedemo.springbootstarters.controller;

import club.codedemo.springbootstarters.entity.Student;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {
    @SpyBean
    StudentController studentController;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        this.studentController.students =
                new ArrayList<>();
        this.studentController.students.add(new Student(1L, "zhangsan"));
        this.studentController.students.add(new Student(2L, "lisi"));
    }

    @Test
    void all() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/student/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("zhangsan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("lisi"))
        ;
    }

    @Test
    void add() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "3");
        jsonObject.put("name", "wangwu");

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/student/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Assertions.assertEquals(3, this.studentController.students.size());
    }

    @Test
    void findById() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/student/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("zhangsan"))
        ;
    }
}