import java.awt.Dimension;

//utility class used to store handful related utility functions
public final class Utility {
	
	//arbitrarily making the class static by hiding the constructor, preventing instantiation
	private Utility() {
		
	}
	
	public static Dimension scaleDimension(Dimension originalDimension, double scale) {
		double originalWidth = originalDimension.getWidth();
		double originalHeight = originalDimension.getHeight();
		
		double scaledWidth = originalWidth * scale;
		double scaledHeight = originalHeight * scale;
		
		Dimension scaledDimension = new Dimension((int)scaledWidth, (int)scaledHeight);
		return scaledDimension;	
	}
	
}
