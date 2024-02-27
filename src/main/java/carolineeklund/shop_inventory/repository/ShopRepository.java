package carolineeklund.shop_inventory.repository;

import carolineeklund.shop_inventory.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Item, Long> {
}
