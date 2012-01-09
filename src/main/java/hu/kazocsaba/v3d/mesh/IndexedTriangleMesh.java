package hu.kazocsaba.v3d.mesh;

import hu.kazocsaba.math.matrix.Vector3;

/**
 * A triangle mesh which provides a list of points and triples of point indices.
 * @author Kazó Csaba
 */
public interface IndexedTriangleMesh extends TriangleMesh, PointList {

	/**
	 * Returns the number of triangles in the mesh.
	 * @return the number of triangles
	 */
	public int getTriangleCount();

	/**
	 * Returns the index of the specified vertex.
	 * @param triangle the index of the triangle to query
	 * @param point the index of the vertex within the triangle
	 * @return the index of the specified vertex
	 * @throws IndexOutOfBoundsException if the triangle index is out of
	 * range or if {@code point} is not 0, 1, or 2
	 */
	public int getTrianglePointIndex(int triangle, int point);
	/**
	 * Returns the the specified vertex.<p>
	 * This method is equivalent to {@code getPoint(getTrianglePointIndex(triangle, point))}.
	 * @param triangle the index of the triangle to query
	 * @param point the index of the vertex within the triangle
	 * @return the specified vertex
	 * @throws IndexOutOfBoundsException if the triangle index is out of
	 * range or if {@code point} is not 0, 1, or 2
	 */
	public Vector3 getTrianglePoint(int triangle, int point);
}
