package org.saurav.simpletests.problems;

public class DelimiterToCamel {

	public static void main(String a[]) {
		String toCamelCase = "You_have_chosen_to_translate_this_kata_For_your_convenience_we_have_provided_the_existing_test_cases_used_for_the_language_that_you_have_already_completed_as_well_as_all_of_the_other_related_fields";

		String result = toCamelCase(toCamelCase);
		System.out.println(result);

	}

	public static String toCamelCase(String toCamelCase) {
		if(toCamelCase == null && toCamelCase.isEmpty()){
			return "";
		}
		StringBuilder result = new StringBuilder();
		String[] splitChars = toCamelCase.split("[-_ ]+");
		for (int i = 0; i < splitChars.length; i++) {
			String string = splitChars[i];
			if (i == 0) {
				if (Character.isLowerCase(string.charAt(0))) {
					result.append(string);
					continue;
				}
			}

			char upperCase = Character.toUpperCase(string.charAt(0));
			
			String replacedString = string.replaceFirst(String.valueOf(string.charAt(0)), String.valueOf(upperCase));
			System.out.println(replacedString);
			result.append(replacedString);

		}
		return result.toString();
	}

}
