package Food;

public class GroupName {
	
	private final int id;
	private final String fullGroup;
	private final String group;
	
	public GroupName(int groupId, String groupName){
		id = groupId;
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
	
	public int getId() {
		return id;
	}
	public String getFullGroup() {
		return fullGroup;
	}
	public String getGroup() {
		return group;
	}

}
