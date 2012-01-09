package hu.kazocsaba.v3d.mesh;

import hu.kazocsaba.math.matrix.Vector3;

/**
 * An object which holds a list of points. Used for providing random (indexed) access to the
 * individual vertices.
 * @author Kazó Csaba
 */
public interface PointList {

	/**
	 * Returns the point at the specified index.
	 * @param index the index of the point to return
	 * @return the point at the specified index
	 * @throws IndexOutOfBoundsException if the index is out of range
	 * ({@code index<0 || index>=getPointCount()})
	 */
	public Vector3 getPoint(int index);

	/**
	 * Returns the number of points in the mesh.
	 * @return the number of points
	 */
	public int getPointCount();
}
