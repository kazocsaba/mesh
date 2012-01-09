package hu.kazocsaba.v3d.mesh;

import java.util.NoSuchElementException;
import hu.kazocsaba.math.matrix.Vector3;

/**
 * Abstract superclass for {@code IndexedTriangleStrip} objects implementing the {@code IndexedTriangleMesh}
 * and the {@code PointList} aspects.
 * @author Kazó Csaba
 */
public abstract class AbstractIndexedTriangleStrip implements IndexedTriangleStrip {

	@Override
	public int getTriangleCount() {
		int count=0;
		for (int strip=0; strip<getStripCount(); strip++) {
			count+=getStripLength(strip)-2;
		}
		return count;
	}

	@Override
	public int getTrianglePointIndex(int triangle, int point) {
		if (triangle<0) throw new IndexOutOfBoundsException();
		if (point<0 || point>=3) throw new IndexOutOfBoundsException();
		int trianglesInPreviousStrips=0;
		for (int currentStrip=0; currentStrip<getStripCount(); currentStrip++) {
			int trianglesInCurrentStrip=getStripLength(currentStrip)-2;
			if (triangle<trianglesInPreviousStrips+trianglesInCurrentStrip) {
				triangle=triangle-trianglesInPreviousStrips;
				boolean flip=(triangle % 2) == 0;
				if (flip) {
					if (point==2) point=1; else if (point==1) point=2;
				}
				return getStripPointIndex(currentStrip, triangle+point);
			}
			trianglesInPreviousStrips+=trianglesInCurrentStrip;
		}
		throw new IndexOutOfBoundsException();
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
		private int stripCursor=-1;
		private int pointCursor;
		private boolean flip;
		@Override
		public boolean hasNext() {
			return stripCursor<getStripCount()-1 || pointCursor+3<getStripLength(stripCursor);
		}

		@Override
		public void next() {
			if (!hasNext()) throw new NoSuchElementException();
			if (stripCursor==-1 || pointCursor+3==getStripLength(stripCursor)) {
				stripCursor++;
				pointCursor=0;
				flip=false;
			} else {
				pointCursor++;
				flip=!flip;
			}
			
		}

		@Override
		public Vector3 getV1() {
			return getPoint(getStripPointIndex(stripCursor,pointCursor));
		}

		@Override
		public Vector3 getV2() {
			return getPoint(getStripPointIndex(stripCursor,pointCursor+(flip ? 2 : 1)));
		}

		@Override
		public Vector3 getV3() {
			return getPoint(getStripPointIndex(stripCursor,pointCursor+(flip ? 1 : 2)));
		}
		
	}
}
