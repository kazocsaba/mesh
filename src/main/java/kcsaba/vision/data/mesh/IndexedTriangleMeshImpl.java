package kcsaba.vision.data.mesh;

import java.util.List;
import java.util.NoSuchElementException;
import kcsaba.math.matrix.Vector3;

/**
 * An implementation of {@code IndexTriangleMesh} which stores the points as a
 * list of {@code Vector3} and the triangles as a list of point index triples.
 * @author Kazó Csaba
 */
public class IndexedTriangleMeshImpl extends PointListImpl implements IndexedTriangleMesh {

	private final List<int[]> triangles;

	/**
	 * Creates a new instance with the specified data. Both lists will be stored
	 * by reference. No further operations on these objects should be performed.
	 * @param points the points of the mesh
	 * @param triangles the triangles specified by point index triples
	 * @throws NullPointerException if either argument or an element of either list is {@code null}
	 * @throws IllegalArgumentException if an array in the triangle list has a length different from 3
	 * @throws IndexOutOfBoundsException if a point index is out of range
	 */
	public IndexedTriangleMeshImpl(List<Vector3> points, List<int[]> triangles) {
		super(points);
		this.triangles = triangles;
		for (int i=0; i<triangles.size(); i++) {
			int[] tri=triangles.get(i);
			if (tri==null) throw new NullPointerException("Null element in triangle list");
			if (tri.length!=3) throw new IllegalArgumentException("Triangle list contains array of length "+tri.length);
			if (tri[0]<0 || tri[0]>=points.size()) throw new IndexOutOfBoundsException("Invalid point index: "+tri[0]);
			if (tri[1]<0 || tri[1]>=points.size()) throw new IndexOutOfBoundsException("Invalid point index: "+tri[1]);
			if (tri[2]<0 || tri[2]>=points.size()) throw new IndexOutOfBoundsException("Invalid point index: "+tri[2]);
		}
	}


	@Override
	public int getTriangleCount() {
		return triangles.size();
	}

	@Override
	public int getTrianglePointIndex(int triangle, int point) {
		return triangles.get(triangle)[point];
	}

	@Override
	public Vector3 getTrianglePoint(int triangle, int point) {
		return getPoint(getTrianglePointIndex(triangle, point));
	}

	@Override
	public TriangleListIterator iterateTriangles() {
		return new Iterator();
	}

	private class Iterator implements TriangleListIterator {
		private int cursor=-1;
		@Override
		public boolean hasNext() {
			return cursor+1 < getTriangleCount();
		}

		@Override
		public void next() {
			if (!hasNext()) throw new NoSuchElementException();
			cursor++;
		}

		@Override
		public Vector3 getV1() {
			if (cursor==-1) throw new IllegalStateException("You must call next() first.");
			return getTrianglePoint(cursor, 0);
		}

		@Override
		public Vector3 getV2() {
			if (cursor==-1) throw new IllegalStateException("You must call next() first.");
			return getTrianglePoint(cursor, 1);
		}

		@Override
		public Vector3 getV3() {
			if (cursor==-1) throw new IllegalStateException("You must call next() first.");
			return getTrianglePoint(cursor, 2);
		}

	}
}
