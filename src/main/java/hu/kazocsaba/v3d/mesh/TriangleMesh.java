package hu.kazocsaba.v3d.mesh;

/**
 * A mesh composed of a set of triangles.
 * @author Kazó Csaba
 */
public interface TriangleMesh {
	/**
	 * Returns an iterator over the triangles of this mesh.
	 * @return an iterator over the triangles of this mesh
	 */
	public TriangleListIterator iterateTriangles();
}
