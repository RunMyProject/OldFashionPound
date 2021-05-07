/**
 * 
 */
package oldfashionpound;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

/**
 * @author edoardo
 *
 */
@AllArgsConstructor
@Getter
@SuperBuilder
public class Price {

	private Integer pounds;
	private Integer shillings;
	private Integer pennies;

	public String toString() {
		return  (Optional.ofNullable(pounds).isPresent() ? pounds + "p " : "") +
				(Optional.ofNullable(shillings).isPresent() ? shillings + "s " : "") +
				(Optional.ofNullable(pennies).isPresent() ? pennies + "d " : "");
	}

	public String toRestString() {
		return  (Optional.ofNullable(shillings).isPresent() && shillings!=0 ? shillings + "s " : "") +
				(Optional.ofNullable(pennies).isPresent() && pennies!=0 ? pennies + "d " : "");
	}

	public boolean isEmpty() {
		return  !Optional.ofNullable(pounds).isPresent() &&
				!Optional.ofNullable(shillings).isPresent() &&
				!Optional.ofNullable(pennies).isPresent();
	}
}