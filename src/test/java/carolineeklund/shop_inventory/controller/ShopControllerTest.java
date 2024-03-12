package carolineeklund.shop_inventory.controller;

import carolineeklund.shop_inventory.model.Item;
import carolineeklund.shop_inventory.service.ShopService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShopService shopService;

    private Item item;

    /**
     * When testing a new separate h2 database is created.
     */

    @BeforeEach
    public void setup(){
        this.item = new Item("milk", 16, 1);
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Using ObjectMapper to convert the Item-object to JSON
     */

    @Test
    void createItem() throws Exception {
        mockMvc.perform(post("/api/shop")
                .contentType(MediaType.APPLICATION_JSON)
                . content(new ObjectMapper().writeValueAsString(item)))
                .andExpect(status().isOk());
    }

    @Test
    void getAllItems() throws Exception {
        mockMvc.perform(get("/api/shop"))
                .andExpect(status().isOk());
    }

    @Test
    void findItemById() {
        try {
            Mockito.when(shopService.getItemById(1L)).thenReturn(java.util.Optional.of(item));
            mockMvc.perform(get("/api/shop/1"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void updateItem() throws Exception {
        mockMvc.perform(put("/api/flowers/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(item)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteItem() throws Exception {
        mockMvc.perform(delete("/api/shop/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getLowQuantityItems() throws Exception {
        try {
            Mockito.when(shopService.getLowInStock(1)).thenReturn(java.util.List.of(item));
            mockMvc.perform(get("/api/shop/search/quantity"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail(e);
        }

    }
}