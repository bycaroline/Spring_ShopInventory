package carolineeklund.shop_inventory.model;

import carolineeklund.shop_inventory.service.ShopService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private ShopService shopService;

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testItemCreation(){
        Item item = new Item("milk", 20, 2);
        assertEquals("milk", item.getName());
        assertEquals(20, item.getPrice());
        assertEquals(2, item.getQuantity());
    }

@Test
    public void testItemSetters(){
        Item item = new Item("milk", 20, 2);
        item.setName("cheese");
        item.setPrice(16);
        item.setQuantity(5);
        assertEquals("cheese", item.getName());
        assertEquals(16, item.getPrice());
        assertEquals(5, item.getQuantity());
}
}