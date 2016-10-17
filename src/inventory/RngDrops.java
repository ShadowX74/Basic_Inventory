/**
 * *********************************************************************
 * Program Filename: RngDrops.java
 * Author: Shen, Xiangyu
 * Date: 10/16/16
 * Description: Template for each drop
 * Input: Called from main class/Keyboard
 * Output: creates object where called
 **********************************************************************
 */

package inventory;

public class RngDrops {
	private String name, action; 
	private int weight;
	private boolean kills;
	
	public RngDrops(String nam, int weig, boolean kill, String act) {
		this.name = nam;
		this.weight = weig;
		this.kills = kill;
		this.action = act;
	}

        /**
        * *********************************************************************
        * Method: getName
        * Description: returns the object's name variable
        * Parameters: N/A
        * Pre-conditions: called from an object
        * Post-conditions: variable passed
        * *********************************************************************
        */
	public String getName() {
		return name;
	}

        /**
        * *********************************************************************
        * Method: getWeight
        * Description: returns the object's weight variable
        * Parameters: N/A
        * Pre-conditions: called from an object
        * Post-conditions: variable passed
        * *********************************************************************
        */
	public int getWeight() {
		return weight;
	}

        /**
        * *********************************************************************
        * Method: getKills
        * Description: returns the object's kills variable
        * Parameters: N/A
        * Pre-conditions: called from an object
        * Post-conditions: variable passed
        * *********************************************************************
        */
	public boolean getKills() {
		return kills;
	}

        /**
        * *********************************************************************
        * Method: getAction
        * Description: returns the object's action variable
        * Parameters: N/A
        * Pre-conditions: called from an object
        * Post-conditions: variable passed
        * *********************************************************************
        */
	public String getAction() {
		return action;
	}
}