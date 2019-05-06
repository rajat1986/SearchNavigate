package parameters;
import org.testng.annotations.DataProvider;

public class DataProvider_Search
{
	@DataProvider(name="ValidParameter")
    public static Object[][] getValidParameters(){
    return new Object[][] 
    	{
            { "aata"},
            { "sugar"},
            { "tomato"}
        };

    }
	
	@DataProvider(name="InvalidParameter")
    public static Object[][] getInvalidParameters(){
    return new Object[][] 
    	{
            { "xyz"},
            { "rwhlka"}
        };

    }
}