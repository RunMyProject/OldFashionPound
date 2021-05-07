/**
 * 
 */
package oldfashionpound;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author edoardo
 *
 */
public class OldFashionPound {

    public static FIOldFashionPound<List<Price>, Integer> sum = (priceList, factor) -> {

        // calculate pennies
        //
        AtomicReference<Integer> pennies = new AtomicReference<>(new Integer(0));
        priceList.forEach(price -> {
            pennies.updateAndGet(penny -> penny + price.getPennies());
        });
        Integer penniesDivider = pennies.get() / Setup.PENNIES_DIVIDER;
        pennies.updateAndGet(penny -> penny % Setup.PENNIES_DIVIDER);

        // calculate shillings
        //
        AtomicReference<Integer> shillings = new AtomicReference<>(new Integer(penniesDivider));
        priceList.forEach(price -> {
            shillings.updateAndGet(shilling -> shilling + price.getShillings());
        });
        Integer shillingsDivider = shillings.get() / Setup.SHILLINGS_DIVIDER;
        shillings.updateAndGet(shilling -> shilling % Setup.SHILLINGS_DIVIDER);

        // calculate pounds
        //
        AtomicReference<Integer> pounds = new AtomicReference<>(new Integer(shillingsDivider));
        priceList.forEach(price -> {
            pounds.updateAndGet(pound -> pound + price.getPounds());
        });

        PriceResult priceResult = PriceResult
            .builder()
            .pounds(pounds.get())
            .shillings(shillings.get())
            .pennies(pennies.get())
            .build();

        return priceResult;
    };

    public static FIOldFashionPound<List<Price>, Integer> subtraction = (priceList, factor) -> {

        // calculate initial blocks
        //
        AtomicReference<Integer> pounds    = new AtomicReference<>(new Integer(priceList.stream().findFirst().get().getPounds()));
        AtomicReference<Integer> shillings = new AtomicReference<>(new Integer(priceList.stream().findFirst().get().getShillings()));
        AtomicReference<Integer> pennies   = new AtomicReference<>(new Integer(priceList.stream().findFirst().get().getPennies()));

        priceList.stream().skip(1).forEach(price -> {
            pounds.updateAndGet(pound -> pound - price.getPounds());
            shillings.updateAndGet(shilling -> shilling - price.getShillings());
            pennies.updateAndGet(penny -> penny - price.getPennies());
        });

        // calculate loan shillings
        //
        if(pennies.get()<0) {
            pennies.updateAndGet(penny -> penny + Setup.PENNIES_DIVIDER);
            shillings.updateAndGet(shilling -> shilling - 1);
        }

        // calculate loan pounds
        //
        if(shillings.get()<0) {
            shillings.updateAndGet(shilling -> shilling + Setup.SHILLINGS_DIVIDER);
            pounds.updateAndGet(pound -> pound - 1);
        }

        PriceResult priceResult = PriceResult
                .builder()
                .pounds(pounds.get())
                .shillings(shillings.get())
                .pennies(pennies.get())
                .build();

        return priceResult;
    };

    public static FIOldFashionPound<List<Price>, Integer> multiplication = (priceList, factor) -> {

        // calculate pennies
        //
        AtomicReference<Integer> pennies   = new AtomicReference<>(factor * new Integer(priceList.stream().findFirst().get().getPennies()));
        Integer penniesDivider = pennies.get() / Setup.PENNIES_DIVIDER;
        pennies.updateAndGet(penny -> penny % Setup.PENNIES_DIVIDER);

        // calculate shillings
        //
        AtomicReference<Integer> shillings = new AtomicReference<>(penniesDivider + factor * new Integer(priceList.stream().findFirst().get().getShillings()));
        Integer shillingsDivider = shillings.get() / Setup.SHILLINGS_DIVIDER;
        shillings.updateAndGet(shilling -> shilling % Setup.SHILLINGS_DIVIDER);

        // calculate pounds
        //
        AtomicReference<Integer> pounds    = new AtomicReference<>(shillingsDivider+ factor * new Integer(priceList.stream().findFirst().get().getPounds()));

        PriceResult priceResult = PriceResult
                .builder()
                .pounds(pounds.get())
                .shillings(shillings.get())
                .pennies(pennies.get())
                .build();

        return priceResult;
    };

    public static FIOldFashionPound<List<Price>, Integer> division = (priceList, factor) -> {

        // calculate pounds
        //
        AtomicReference<Integer> pounds   = new AtomicReference<>(new Integer(priceList.stream().findFirst().get().getPounds() / factor));

        // calculate shillings
        //
        Integer shillingsRest = (priceList.stream().findFirst().get().getPounds() % factor) * Setup.SHILLINGS_DIVIDER;
        AtomicReference<Integer> shillings  = new AtomicReference<>((shillingsRest + new Integer(priceList.stream().findFirst().get().getShillings()) ) / factor);

        // calculate pennies
        //
        Integer penniesRest = (priceList.stream().findFirst().get().getShillings() + shillingsRest - (shillings.get() * factor));
        AtomicReference<Integer> pennies  = new AtomicReference<>( ( (penniesRest * Setup.PENNIES_DIVIDER) + priceList.stream().findFirst().get().getPennies() ) / factor);

        // calculate final rest pennies
        //
        Integer penniesFinalRest = (priceList.stream().findFirst().get().getPennies() + penniesRest - (pennies.get() * factor));

        Price priceRestOfMoney = Price
                .builder()
                .shillings(penniesRest)
                .pennies(penniesFinalRest)
                .build();

        PriceResult priceResult = PriceResult
                .builder()
                .pounds(pounds.get())
                .shillings(shillings.get())
                .pennies(pennies.get())
                .priceRestOfMoney(priceRestOfMoney)
                .build();

        return priceResult;
    };

}