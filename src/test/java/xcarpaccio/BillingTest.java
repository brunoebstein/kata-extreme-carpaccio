package xcarpaccio;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;

public class BillingTest {

    @Test(expected = RuntimeException.class)
    public void execptionByDefault() {
        Billing.bill(new Order());
    }

    @Test
    public void germany() {
        Order order = new Order();
        order.names = Arrays.asList("","");
        order.country = "DE";
        order.prices = Arrays.asList(5.0, 8.88);
        order.quantities = Arrays.asList(1, 2);
        order.reduction = "STANDARD";
        Answer answer = Billing.bill(order);
        Assertions.assertThat(answer.total).isEqualTo(5.0 * 1.20 + 8.88 * 2 * 1.20);
    }

    @Test
    public void france() {
        Order order = new Order();
        order.names = Arrays.asList("","");
        order.country = "FR";
        order.prices = Arrays.asList(5.0, 8.88);
        order.quantities = Arrays.asList(1, 2);
        order.reduction = "STANDARD";
        Answer answer = Billing.bill(order);
        Assertions.assertThat(answer.total).isEqualTo(5.0 * 1.20 + 8.88 * 2 * 1.20);
    }

    @Test(expected = RuntimeException.class)
    public void croatiaNotSupported() {
        Order order = new Order();
        order.names = Arrays.asList("","");
        order.country = "qq";
        order.prices = Arrays.asList(5.0, 8.88);
        order.quantities = Arrays.asList(1, 2);
        order.reduction = "STANDARD";
        Answer answer = Billing.bill(order);
    }

    @Test
    public void between1000and5000() {
        Order order = new Order();
        order.names = Arrays.asList("");
        order.country = "DE";
        order.prices = Arrays.asList(950.0);
        order.quantities = Arrays.asList(1);
        order.reduction = "STANDARD";
        Answer answer = Billing.bill(order);
        Assertions.assertThat(answer.total).isEqualTo(950.0 * 1.20 * 0.97);
    }


    @Test
    public void maxiReduc() {
        Order order = new Order();
        order.names = Arrays.asList("");
        order.country = "DE";
        order.prices = Arrays.asList(41666.67);
        order.quantities = Arrays.asList(1);
        order.reduction = "STANDARD";
        Answer answer = Billing.bill(order);
        Assertions.assertThat(answer.total).isEqualTo(41666.67 * 1.20 * 0.85);
    }

    @Test(expected = RuntimeException.class)
    public void unknownReduction() {
        Order order = new Order();
        order.names = Arrays.asList("");
        order.country = "DE";
        order.prices = Arrays.asList(41666.67);
        order.quantities = Arrays.asList(1);
        order.reduction = "WTF";
        Answer answer = Billing.bill(order);
    }

    @Test(expected = RuntimeException.class)
    public void pricenotquality() {
        Order order = new Order();
        order.names = Arrays.asList("","");
        order.country = "DE";
        order.prices = Arrays.asList(41666.67, 3.0);
        order.quantities = Arrays.asList(1);
        order.reduction = "STANDARD";
        Answer answer = Billing.bill(order);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zetseg() {
        Order order = new Order();
        order.names = null;
        order.country = null;
        order.prices = null;
        order.quantities = null;
        order.reduction = "null";
        Answer answer = Billing.bill(order);
    }

}
