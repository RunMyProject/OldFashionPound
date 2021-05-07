package oldfashionpound;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

/**
 * @author edoardo
 *
 */
@Getter
@SuperBuilder
public class PriceResult extends Price {

    private Price priceRestOfMoney;

    public String toString() {
        return super.toString() + priceRestOfMoneyToString();
    }

    private String priceRestOfMoneyToString() {
        return Optional.ofNullable(priceRestOfMoney).isPresent() && !priceRestOfMoney.isEmpty()
                ? ( "( " + priceRestOfMoney.toRestString() + ")" )
                : "";
    }
}
