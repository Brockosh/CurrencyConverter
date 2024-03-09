import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConverterController {

    private final String apiKey;

    @Autowired
    public CurrencyConverterController(@Value("${freeCurrencyApiKey}") String apiKey) {
        this.apiKey = apiKey;
    }

    @GetMapping("/convert")
    public String convertCurrency(@RequestParam String from, @RequestParam String to, @RequestParam Double amount) {
        String apiUrl = "https://freecurrencyapi.com/api/v3/latest?apikey=" + this.apiKey + "&base_currency=" + from;

        RestTemplate restTemplate = new RestTemplate();
        CurrencyResponse response = restTemplate.getForObject(apiUrl, CurrencyResponse.class);

        if (response != null && response.getData() != null && response.getData().get(to) != null) {
            Double toRate = response.getData().get(to);
            Double convertedAmount = amount * toRate;
            return "Converted amount: " + convertedAmount;
        } else {
            return "Could not fetch exchange rates";
        }
    }
}


