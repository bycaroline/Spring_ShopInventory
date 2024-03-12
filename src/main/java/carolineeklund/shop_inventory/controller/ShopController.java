package carolineeklund.shop_inventory.controller;

import carolineeklund.shop_inventory.model.Item;
import carolineeklund.shop_inventory.repository.ShopRepository;
import carolineeklund.shop_inventory.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller class responsible for handling api requests related to the shop.
 * Handles the Http requests and returns the response.
 */
@RestController
@RequestMapping("api/shop")
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Iterable<Item> items = shopService.getAllItems();

        for (Item i : items) {
            String itemName = i.getName();
            String itemNameNew = item.getName();
            if (!itemNameNew.equals(itemName) || itemNameNew == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        Item newItem = shopService.createItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        Iterable<Item> items = shopService.getAllItems();
        List<Item> allItems = new ArrayList<>();

        for (Item i : items) {
            allItems.add(i);
        }

        return new ResponseEntity<>(allItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> findItemById(@PathVariable Long id) {
        Optional<Item> item = shopService.getItemById(id);
        if (item.isPresent()) {
            return ResponseEntity.ok(item.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        Optional<Item> optionalItem = shopService.getItemById(id);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            item.setName(itemDetails.getName());
            item.setPrice(itemDetails.getPrice());
            item.setQuantity(itemDetails.getQuantity());
            return ResponseEntity.ok(shopService.updateItem(item));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        shopService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * Method for finding out which items needs to be restocked.
     */
    @GetMapping("/search/quantity")
    public List<Item> getLowQuantityItems() {
        int threshold = 2;
        return shopService.getLowInStock(threshold);
    }

}
