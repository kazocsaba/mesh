package hu.kazocsaba.v3d.mesh;

import java.awt.Color;
import java.util.List;
import hu.kazocsaba.math.matrix.Vector3;

/**
 * A straightforward implementation of the {@code ColoredPointList} interface using lists to store the data.
 * @author Kazó Csaba
 */
public class ColoredPointListImpl extends PointListImpl implements ColoredPointList {
	private final List<Color> colors;
	
	/**
	 * Creates a new point list backed by the specified lists. The lists are stored by reference and should not
	 * be modified.
	 * @param points the points
	 * @param colors the colors
	 * @throws NullPointerException if either argument is {@code null}
	 * @throws IllegalArgumentException if the sizes of the lists are different
	 */
	public ColoredPointListImpl(List<Vector3> points, List<Color> colors) {
		super(points);
		if (points.size()!=colors.size()) throw new IllegalArgumentException("Different list sizes");
		this.colors=colors;
	}
	@Override
	public Color getPointColor(int index) {
		return colors.get(index);
	}

}
