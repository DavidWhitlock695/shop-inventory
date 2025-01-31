import com.shop.shop_inventory.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Item,Integer> {}
