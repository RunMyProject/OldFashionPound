/**
 * 
 */
package oldfashionpound;

/**
 * @author edoardo
 *
 */
@FunctionalInterface
public interface FIOldFashionPound<List, Integer> {
	public PriceResult apply(List priceList, Integer factor);
}
