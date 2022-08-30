package com.cta;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class CtaControllerTest {

	@Autowired
	private MockMvc mvc;

    @Test
    public void getWalletEmptyTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/checkWallet").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("")));
    }

    @Test
    public void getWalletTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/addMoney/3,2,1").accept(MediaType.APPLICATION_JSON));
        mvc.perform(MockMvcRequestBuilders.get("/checkWallet").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("3,2,1")));
        mvc.perform(MockMvcRequestBuilders.get("/emptyWallet").accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getNumFare() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/addMoney/3,2,2,1").accept(MediaType.APPLICATION_JSON));
        mvc.perform(MockMvcRequestBuilders.get("/checkFare/4").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(equalTo("2")));
    }

    @Test
    public void getNumFareWays() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/addMoney/3,2,2,1").accept(MediaType.APPLICATION_JSON));
        mvc.perform(MockMvcRequestBuilders.get("/checkFareWays/4").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(equalTo("(2,2),(1,3)")));
    }

}
