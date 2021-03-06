package com.mazvile.menuappweb;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MenuAppWebApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Ignore
    @Test
    public void exampleTest() throws Exception {
        this.mvc.perform(post("/test")).andExpect(status().isOk())
                .andExpect(content().string("1"));
        this.mvc.perform(get("/test")).andExpect(status().isOk())
                .andExpect(content().string("Potato"));
    }
}
