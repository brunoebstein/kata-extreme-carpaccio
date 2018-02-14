package xcarpaccio;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Billing {

    private static final Map<String, Double> taxes = new HashMap<String, Double>() {{
        put("DE", 1.20);
        put("UK", 1.21);
        put("FR", 1.20);
        put("IT", 1.25);
        put("ES", 1.19);
        put("PL", 1.21);
        put("RO", 1.20);
        put("NL", 1.20);
        put("BE", 1.24);
        put("EL", 1.20);
        put("CZ", 1.19);
        put("PT", 1.23);
        put("HU", 1.27);
        put("SE", 1.23);
        put("AT", 1.22);
        put("BG", 1.21);
        put("DK", 1.21);
        put("FI", 1.17);
        put("SK", 1.18);
        put("IE", 1.21);
        put("HR", 1.23);
        put("LT", 1.23);
        put("SI", 1.24);
        put("LV", 1.20);
        put("EE", 1.22);
        put("CY", 1.21);
        put("LU", 1.25);
        put("MT", 1.20);
    }};

    public static Answer bill(Order order) {
        if (order.prices == null || order.quantities == null || !taxes.containsKey(order.country) || order.prices.size() != order.quantities.size()) {
            throw new IllegalArgumentException();
        }
        if ("STANDARD".equals(order.reduction)) {
            double total = 0;
            for (int i = 0; i < order.prices.size(); i++) {
                Double price = order.prices.get(i);
                Integer q = order.quantities.get(i);
                total += price * q;
            }
            total *= taxes.get(order.country);
            if (total < 1000) {
                return new Answer(total);
            }
            if (total >= 50000) {
                total *= 0.85;
                return new Answer(total);
            }
            if (total >= 10000) {
                total *= 0.90;
                return new Answer(total);
            }
            if (total >= 7000) {
                total *= 0.93;
                return new Answer(total);
            }
            if (total >= 5000) {
                total *= 0.95;
                return new Answer(total);
            }
            if (total >= 1000) {
                total *= 0.97;
                return new Answer(total);
            }
        }
        throw new RuntimeException();
    }
}
