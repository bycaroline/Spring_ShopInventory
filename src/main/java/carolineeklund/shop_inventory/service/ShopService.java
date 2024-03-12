package carolineeklund.shop_inventory.service;

import carolineeklund.shop_inventory.model.Item;
import carolineeklund.shop_inventory.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *  Service class for the Shop entity. This class is used to interact with the data source (such as a database) and perform operations on the data.
 */
@Service
public class ShopService {

    /**
     * Allows the ShopService instance to utilize the methods provided by the ShopRepository interface,to interact with the data source (such as a database).
     */
    private final ShopRepository shopRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository){
        this.shopRepository= shopRepository;
    }

    public Item createItem (Item item){
        return shopRepository.save(item);
    }

    public Iterable<Item> getAllItems(){
        return shopRepository.findAll();
    }

    public Optional<Item> getItemById(Long id){
        return shopRepository.findById(id);
    }

    public Item updateItem(Item item){
        return shopRepository.save(item);
    }

    public void deleteItem(Long id){
        shopRepository.deleteById(id);
    }

    public List<Item> getLowInStock(int quantity){
        return shopRepository.getLowQuantityItems(quantity);
    }

}
