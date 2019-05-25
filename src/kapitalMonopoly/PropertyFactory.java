package kapitalMonopoly;

public class PropertyFactory {
	public static PropertyInterface getProperty(String property)
	{
		if (property.equals("House"))
			return new House();
		else if (property.equals("Hotel"))
			return new Hotel();
		else if (property.equals("SkyScraper"))
			return new Skyscraper();
		return null;
	}
}
