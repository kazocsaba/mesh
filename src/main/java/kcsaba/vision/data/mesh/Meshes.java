package kcsaba.vision.data.mesh;

import java.util.ArrayList;
import java.util.List;
import kcsaba.math.matrix.Vector3;

/**
 * Utility methods operating on meshes.
 * @author Kazó Csaba
 */
public class Meshes {
	private Meshes() {}

	/**
	 * Returns the specified mesh as an {@code IndexedTriangleMesh}. If the argument already
	 * implements this interface, this method simply returns it.
	 * @param mesh a mesh
	 * @return an {@code IndexedTriangleMesh} representing the same mesh as the argument
	 */
	public static IndexedTriangleMesh toIndexed(TriangleMesh mesh) {
		if (mesh instanceof IndexedTriangleMesh) return (IndexedTriangleMesh)mesh;
		if (mesh instanceof PointList) return new TriangleSetImpl((PointList)mesh);
		TriangleListIterator iterator = mesh.iterateTriangles();
		List<Vector3> vertices=new ArrayList<Vector3>();
		while (iterator.hasNext()) {
			iterator.next();
			vertices.add(iterator.getV1());
			vertices.add(iterator.getV2());
			vertices.add(iterator.getV3());
		}
		return new TriangleSetImpl(vertices);
	}
	/**
	 * Returns a new {@code PointList} object backed by the specified list. The argument
	 * is stored by reference, no further operations should be performed on it.
	 * @param vertices the list to create the {@code PointList} from
	 * @return a {@code PointList} view of the specified list
	 * @throws NullPointerException if either the argument or an element of it is {@code null}
	 */
	public static PointList toPointList(final List<Vector3> vertices) {
		for (int i=0; i<vertices.size(); i++)
			if (vertices.get(i)==null) throw new NullPointerException();
		return new PointList() {

			@Override
			public Vector3 getPoint(int index) {
				return vertices.get(index);
			}

			@Override
			public int getPointCount() {
				return vertices.size();
			}
		};
	}
}
