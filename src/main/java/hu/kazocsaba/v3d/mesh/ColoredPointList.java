package hu.kazocsaba.v3d.mesh;

import java.awt.Color;

/**
 * A point list where each point has a color assigned to it.
 * @author Kazó Csaba
 */
public interface ColoredPointList extends PointList {
	/**
	 * Returns the color of a point.
	 * @param index the index of the point
	 * @return the color of the point at the specified index
	 * @throws IndexOutOfBoundsException if the index is out of range
	 * ({@code index<0 || index>=getPointCount()})
	 */
	public Color getPointColor(int index);
}
