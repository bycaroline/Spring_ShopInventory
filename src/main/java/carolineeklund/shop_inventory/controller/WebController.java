package carolineeklund.shop_inventory.controller;

import carolineeklund.shop_inventory.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class WebController {

    private final String SHOP_CONTROLLER_BASE_URL = "http://localhost:8087"; // Adjust this URL accordingly
    private final RestTemplate restTemplate;

    @Autowired
    public WebController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("message", "Välkommen till Shop");
        return "home";
    }



    @GetMapping("/about")
    public String about(Model model) {
        // Call the findAll endpoint of ShopController to fetch items
        Item[] items = restTemplate.getForObject(SHOP_CONTROLLER_BASE_URL + "/api/shop", Item[].class);

        // Add the items to the model
        model.addAttribute("items", items);

        return "about";
    }


}
