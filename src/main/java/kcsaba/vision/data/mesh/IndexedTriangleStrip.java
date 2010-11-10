package kcsaba.vision.data.mesh;

/**
 * A triangle strip mesh where the strips use indices to reference the points.
 * @author Kazó Csaba
 */
public interface IndexedTriangleStrip extends TriangleStrip, PointList, IndexedTriangleMesh {
	/**
	 * Returns the index of a point in a strip.
	 * @param strip the index of the strip
	 * @param point the position within the strip
	 * @return the point index
	 * @throws IndexOutOfBoundsException if either argument is invalid
	 */
	public int getStripPointIndex(int strip, int point);
}
