package oldfashionpound;
/**
 * 
 */


/**
 * @author edoardo
 *
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static oldfashionpound.MyLog.print;
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		print("Welcome to Old Fashion Pound!");
		print("*****************************");

		Price price = Price.builder()
				.pounds(5)
				.shillings(17)
				.pennies(8)
				.build();

		Price price2 = Price.builder()
				.pounds(3)
				.shillings(4)
				.pennies(10)
				.build();

		PriceResult priceResult = PriceResult.builder()
				.pounds(700)
				.shillings(800)
				.pennies(900)
				.priceRestOfMoney(price2)
				.build();

		// print(price.toString());
		// print(priceResult.toString());

		List<Price> listSum = new ArrayList<>();
		listSum.add(price);
		listSum.add(price2);

		// 5p 17s 8d + 3p 4s 10d= 9p 2s 6d
		//
		print(" = "  + OldFashionPound.sum.apply(listSum, null).toString());

		List<Price> listSubtraction = new ArrayList<>();
		listSubtraction.add(price);
		listSubtraction.add(price2);

		// 5p 17s 8d - 3p 4s 10d= 2p 12s 10d
		//
		print(" = "  + OldFashionPound.subtraction.apply(listSubtraction, null).toString());

		List<Price> listSubtraction2 = new ArrayList<>();
		listSubtraction2.add(price2);
		listSubtraction2.add(price);

		// 3p 4s 10d - 5p 17s 8d =  -3p 7s 2d
		//
		print(" = "  + OldFashionPound.subtraction.apply(listSubtraction2, null).toString());

		List<Price> listMultiplication = new ArrayList<>();
		listMultiplication.add(price);
		// 5p 17s 8d * 2 = 11p 15s 4d
		//
		print(" = "  + OldFashionPound.multiplication.apply(listMultiplication, 2).toString());

		// 5p 17s 8d / 3 = 1p 19s 2d (2d)
		//
		List<Price> listDivision = new ArrayList<>();
		listDivision.add(price);
		print(" = "  + OldFashionPound.division.apply(listDivision, 3).toString());

		// 18p 16s 1d / 15 = 1p 5s 0d (1s 1d)
		//
		Price price3 = Price.builder()
				.pounds(18)
				.shillings(16)
				.pennies(1)
				.build();
		print(price3.toString());

		List<Price> listDivision2 = new ArrayList<>();
		listDivision2.add(price3);
		print(" = "  + OldFashionPound.division.apply(listDivision2, 15).toString());

		print("***************************");
		print("Thank you and see you soon!");
	}
}
