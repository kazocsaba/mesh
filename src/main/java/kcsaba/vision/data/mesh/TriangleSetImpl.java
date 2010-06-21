package kcsaba.vision.data.mesh;

import java.util.List;
import java.util.NoSuchElementException;
import kcsaba.math.matrix.Vector3;

/**
 * A triangle mesh which just stores a list of points. Triangle {@code i} is formed by points
 * {@code 3*i}, {@code 3*i+1}, and {@code 3*i+2}.
 * @author Kazó Csaba
 */
public class TriangleSetImpl implements IndexedTriangleMesh {
	private PointList vertices;
	/**
	 * Creates a new instance with the specified data. The list will be stored
	 * by reference. No further operations on the argument should be performed.
	 * @param vertices the points of the mesh
	 * @throws NullPointerException if either the argument or an element of it is {@code null}
	 * @throws IllegalArgumentException if the number of points is not divisible by 3
	 */
	public TriangleSetImpl(List<Vector3> vertices) {
		if ((vertices.size()%3)!=0) throw new IllegalArgumentException("Invalid number of points");
		for (int i=0; i<vertices.size(); i++) if (vertices.get(i)==null) throw new NullPointerException();
		this.vertices=Meshes.toPointList(vertices);
	}
	/**
	 * Creates a new instance with the specified data. The list will be stored
	 * by reference.
	 * @param vertices the points of the mesh
	 * @throws NullPointerException if the argument is {@code null}
	 * @throws IllegalArgumentException if the number of points is not divisible by 3
	 */
	public TriangleSetImpl(PointList vertices) {
		if ((vertices.getPointCount()%3)!=0) throw new IllegalArgumentException("Invalid number of points");
		this.vertices=vertices;
	}

	@Override
	public int getPointCount() {
		return vertices.getPointCount();
	}

	@Override
	public Vector3 getPoint(int index) {
		return vertices.getPoint(index);
	}

	@Override
	public int getTriangleCount() {
		return vertices.getPointCount()/3;
	}

	@Override
	public int getTrianglePointIndex(int triangle, int point) {
		if (triangle<0 || 3*triangle>=vertices.getPointCount()) throw new IndexOutOfBoundsException("Invalid triangle index");
		if (point<0 || point>=3) throw new IndexOutOfBoundsException("Invalid point index");
		return 3*triangle+point;
	}

	@Override
	public Vector3 getTrianglePoint(int triangle, int point) {
		return vertices.getPoint(getTrianglePointIndex(triangle, point));
	}

	@Override
	public TriangleListIterator iterateTriangles() {
		return new Iterator();
	}

	private class Iterator implements TriangleListIterator {
		private int cursor=-3;
		@Override
		public boolean hasNext() {
			return cursor+3 < vertices.getPointCount();
		}

		@Override
		public void next() {
			if (!hasNext()) throw new NoSuchElementException();
			cursor+=3;
		}

		@Override
		public Vector3 getV1() {
			if (cursor<0) throw new IllegalStateException("You must call next() first.");
			return vertices.getPoint(cursor);
		}

		@Override
		public Vector3 getV2() {
			if (cursor<0) throw new IllegalStateException("You must call next() first.");
			return vertices.getPoint(cursor+1);
		}

		@Override
		public Vector3 getV3() {
			if (cursor<0) throw new IllegalStateException("You must call next() first.");
			return vertices.getPoint(cursor+2);
		}

	}
}
