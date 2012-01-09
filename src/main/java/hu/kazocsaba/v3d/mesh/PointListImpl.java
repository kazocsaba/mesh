package hu.kazocsaba.v3d.mesh;

import java.util.List;
import hu.kazocsaba.math.matrix.Vector3;

/**
 * A straightforward implementation of the {@code PointList} interface backed by a list of {@code Vector3} objects.
 * @author Kazó Csaba
 */
public class PointListImpl implements PointList {
	private final List<Vector3> points;

	/**
	 * Creates a new point list backed by the specified list of vectors. The list is stored by reference and should not
	 * be modified.
	 * @param points the list of points
	 * @throws NullPointerException if either the argument or an element of the list is {@code null}
	 */
	public PointListImpl(List<Vector3> points) {
		this.points = points;
		for (int i = 0; i < points.size(); i++) {
			if (points.get(i)==null) throw new NullPointerException("Null element in point list");
		}
	}
	
	@Override
	public Vector3 getPoint(int index) {
		return points.get(index);
	}

	@Override
	public int getPointCount() {
		return points.size();
	}

}
