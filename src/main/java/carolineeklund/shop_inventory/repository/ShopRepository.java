package carolineeklund.shop_inventory.repository;

import carolineeklund.shop_inventory.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing and managing items in the shop.
 * Extends JpaRepository to inherit basic CRUD operations.
 */
@Repository
public interface ShopRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE i.quantity < 2")
    List<Item> getLowQuantityItems(@Param("quantity") int quantity);
}
