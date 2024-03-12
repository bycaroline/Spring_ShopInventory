package carolineeklund.shop_inventory.controller;

import carolineeklund.shop_inventory.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

/**
 * Controller class responsible for handling web requests related to the shop.
 */
@Controller
public class WebController {
    // Base URL for the shop controller
    private final String SHOP_CONTROLLER_BASE_URL = "http://localhost:8087";
    // Instance of RestTemplate to make HTTP requests
    private final RestTemplate restTemplate;

    /**
     * Constructor for WebController.
     *
     * @param restTemplate Instance of RestTemplate to inject.
     */
    @Autowired
    public WebController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the shop inventory!");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
         // Fetches all items from the shop API and ads them to the model as an attribute
        Item[] items = restTemplate.getForObject(SHOP_CONTROLLER_BASE_URL + "/api/shop", Item[].class);
        model.addAttribute("items", items);

         //Sends a request to the shop API to retrieve items with low quantity
         //Extracts the response body containing items with low quantity
         //Adds the low quantity items to the model attribute "lowQuantityItems"

        ResponseEntity<Item[]> response = restTemplate.getForEntity(SHOP_CONTROLLER_BASE_URL + "/api/shop/search/quantity", Item[].class);
        Item[] lowQuantityItems = response.getBody();
        model.addAttribute("lowQuantityItems", lowQuantityItems);

         //Returns the name of the view template to render, in this case, "about"
        return "about";
    }
}
