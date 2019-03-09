package ECOO;

public class FibSquare {

	public int fibPosition; // tracks position number, increments by one for each new object
	public long fibValue; // tracks the length of the square

	public Coordinate startingPoint;
	public Coordinate endingPoint;


	public FibSquare() { // first FibSquare

		startingPoint = new Coordinate(1, 0);
		fibPosition = 1;
		fibValue = 1;
		endingPoint = new Coordinate(0, -1);

	}

	public FibSquare(FibSquare first) { // second FibSquare

		startingPoint = first.endingPoint;
		fibPosition = 2;
		fibValue = 1;
		endingPoint = new Coordinate(-1, 0);

	}
	
	public FibSquare(FibSquare nMinusOne, FibSquare nMinusTwo) { // third and following FibSquare

		startingPoint = nMinusOne.endingPoint;
		fibPosition = nMinusOne.fibPosition + 1;
		fibValue = nMinusOne.fibValue + nMinusTwo.fibValue;
		
		endingPoint = new Coordinate();

		int direction = fibPosition % 4;
		switch (direction) {
		case 0: // down and right
			endingPoint.x = startingPoint.x + fibValue;
			endingPoint.y = startingPoint.y - fibValue;
			break;
		case 1: // down and left
			endingPoint.x = startingPoint.x - fibValue;
			endingPoint.y = startingPoint.y - fibValue;
			break;
		case 2: // up and left
			endingPoint.x = startingPoint.x - fibValue;
			endingPoint.y = startingPoint.y + fibValue;
			break;
		case 3: // up and right
			endingPoint.x = startingPoint.x + fibValue;
			endingPoint.y = startingPoint.y + fibValue;
			break;
		}
	}

	public boolean isPointContained(Coordinate point) {
		// must be larger than minimum, less than maximum
		long minX = Math.min(startingPoint.x, endingPoint.x);
		long maxX = Math.max(startingPoint.x, endingPoint.x);

		long minY = Math.min(startingPoint.y, endingPoint.y);
		long maxY = Math.max(startingPoint.y, endingPoint.y);

		boolean horizontal = point.x >= minX && point.x <= maxX;
		boolean vertical =  point.y >= minY && point.y <= maxY;

		return horizontal && vertical;
	}

}
