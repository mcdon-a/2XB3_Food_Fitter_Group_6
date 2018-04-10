package Food;

/**
 * ADT for a GroupName object
 *
 * @author Illya Pilipenko
 *         <p>
 *         Date: 2018 - 03 - 28
 */
public class GroupName {

	/**
	* The original name of the food group, out of 23 options
	*/
	private final String fullGroup;
	/**
	* The name of the food group, simplified to one of 6 categories
	*/
	private final String group;

	/**
	* Constructor for the GroupName ADT
	* @param groupId The id of the food group, as given in the file
	* @param groupName The original name of the food group
	*/
	public GroupName(int groupId, String groupName){
		//id = groupId;
		fullGroup = groupName;
		switch (groupId){
		case 2: case 9: case 11: 	group = "Vegetables and Fruit";
									break;
		case 8: case 18: case 20: 	group = "Grain products";
									break;
		case 1: case 3: 			group = "Milk and alternatives";
									break;
		case 5: case 7: case 10:
		case 12: case 13: case 15:
		case 16: case 17: 			group = "Meat and alternatives";
									break;
		case 4: 					group = "Fats and oils";
									break;
		case 6: case 22: 			group = "Mixed groups";
									break;
		case 14: case 19: case 21:
		case 25: 					group = "Junk foods";
									break;
		default:					group = "Invalid group";
									break;
		}
	}

	/**
	* Accessor method for the full group name
	* @return Full group name
	*/
	public String getFullName() {
		return fullGroup;
	}

	/**
	* Accessor method for the group name
	* @return Group name
	*/
	public String getName() {
		return group;
	}

}
