package carolineeklund.shop_inventory.controller;

import carolineeklund.shop_inventory.model.Item;
import carolineeklund.shop_inventory.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/shop")
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return shopRepository.save(item);
    }

    @GetMapping
    public List<Item> getAllItems() {
        return shopRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> findItemById(@PathVariable Long id){
        Optional<Item> item = shopRepository.findById(id);
        if (item.isPresent()) {
            return ResponseEntity.ok(item.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemDetails){
        Optional<Item> i = shopRepository.findById(id);
        if(i.isPresent()){
            Item item = i.get();
            item.setName(itemDetails.getName());
            item.setPrice(itemDetails.getPrice());
            item.setQuantity(itemDetails.getQuantity());
            return ResponseEntity.ok(shopRepository.save(item));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id){
        if (shopRepository.existsById(id)) {
            shopRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
