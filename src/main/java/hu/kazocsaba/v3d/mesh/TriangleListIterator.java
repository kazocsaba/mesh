package hu.kazocsaba.v3d.mesh;

import hu.kazocsaba.math.matrix.Vector3;

/**
 * An iterator over a list of triangles. Works similarly as {@link java.util.Iterator}. The vertices of the trianges are available after
 * calls to the {@link #next()} method.
 * @author Kazó Csaba
 */
public interface TriangleListIterator {
	/**
	 * Returns {@code true} if the iteration has more triangles.
	 * @return {@code true} if the iteration has more elements
	 */
	public boolean hasNext();
	/**
	 * Moves to the next element in the iteration.
	 * @throws java.util.NoSuchElementException if the iteration has no more elements
	 */
	public void next();
	/**
	 * Return the first vertex of the current triangle.
	 * @return the first vertex of the current triangle
	 * @throws IllegalStateException if the {@code next} method has not yet been called
	 */
	public Vector3 getV1();
	/**
	 * Return the second vertex of the current triangle.
	 * @return the second vertex of the current triangle
	 * @throws IllegalStateException if the {@code next} method has not yet been called
	 */
	public Vector3 getV2();
	/**
	 * Return the third vertex of the current triangle.
	 * @return the third vertex of the current triangle
	 * @throws IllegalStateException if the {@code next} method has not yet been called
	 */
	public Vector3 getV3();
}
