package mil.nga.proj;

import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;
import org.locationtech.proj4j.ProjCoordinate;

/**
 * Projection transform wrapper
 * 
 * @author osbornb
 */
public class ProjectionTransform {

	/**
	 * Coordinate transform factory
	 */
	protected static CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();

	/**
	 * From Projection
	 */
	protected final Projection fromProjection;

	/**
	 * To Projection
	 */
	protected final Projection toProjection;

	/**
	 * Coordinate transform
	 */
	protected final CoordinateTransform transform;

	/**
	 * Create a projection transform
	 * 
	 * @param fromProjection
	 *            from projection
	 * @param toProjection
	 *            to projection
	 * @return projection transform
	 */
	public static ProjectionTransform create(Projection fromProjection,
			Projection toProjection) {
		return new ProjectionTransform(fromProjection, toProjection);
	}

	/**
	 * Create a projection transform
	 * 
	 * @param fromProjection
	 *            from projection
	 * @param toEpsg
	 *            to epsg
	 * @return projection transform
	 */
	public static ProjectionTransform create(Projection fromProjection,
			long toEpsg) {
		return new ProjectionTransform(fromProjection,
				ProjectionFactory.getProjection(toEpsg));
	}

	/**
	 * Create a projection transform
	 * 
	 * @param fromProjection
	 *            from projection
	 * @param toAuthority
	 *            to coordinate authority
	 * @param toCode
	 *            to coordinate code
	 * @return projection transform
	 */
	public static ProjectionTransform create(Projection fromProjection,
			String toAuthority, long toCode) {
		return new ProjectionTransform(fromProjection,
				ProjectionFactory.getProjection(toAuthority, toCode));
	}

	/**
	 * Create a projection transform
	 * 
	 * @param fromProjection
	 *            from projection
	 * @param toAuthority
	 *            to coordinate authority
	 * @param toCode
	 *            to coordinate code
	 * @return projection transform
	 */
	public static ProjectionTransform create(Projection fromProjection,
			String toAuthority, String toCode) {
		return new ProjectionTransform(fromProjection,
				ProjectionFactory.getProjection(toAuthority, toCode));
	}

	/**
	 * Create a projection transform
	 * 
	 * @param fromEpsg
	 *            from epsg
	 * @param toProjection
	 *            to projection
	 * @return projection transform
	 */
	public static ProjectionTransform create(long fromEpsg,
			Projection toProjection) {
		return new ProjectionTransform(
				ProjectionFactory.getProjection(fromEpsg), toProjection);
	}

	/**
	 * Create a projection transform
	 * 
	 * @param fromAuthority
	 *            from coordinate authority
	 * @param fromCode
	 *            from coordinate code
	 * @param toProjection
	 *            to projection
	 * @return projection transform
	 */
	public static ProjectionTransform create(String fromAuthority,
			long fromCode, Projection toProjection) {
		return new ProjectionTransform(
				ProjectionFactory.getProjection(fromAuthority, fromCode),
				toProjection);
	}

	/**
	 * Create a projection transform
	 * 
	 * @param fromAuthority
	 *            from coordinate authority
	 * @param fromCode
	 *            from coordinate code
	 * @param toProjection
	 *            to projection
	 * @return projection transform
	 */
	public static ProjectionTransform create(String fromAuthority,
			String fromCode, Projection toProjection) {
		return new ProjectionTransform(
				ProjectionFactory.getProjection(fromAuthority, fromCode),
				toProjection);
	}

	/**
	 * Create a projection transform
	 * 
	 * @param fromEpsg
	 *            from epsg
	 * @param toEpsg
	 *            to epsg
	 * @return projection transform
	 */
	public static ProjectionTransform create(long fromEpsg, long toEpsg) {
		return new ProjectionTransform(
				ProjectionFactory.getProjection(fromEpsg),
				ProjectionFactory.getProjection(toEpsg));
	}

	/**
	 * Create a projection transform
	 * 
	 * @param fromAuthority
	 *            from coordinate authority
	 * @param fromCode
	 *            from coordinate code
	 * @param toAuthority
	 *            to coordinate authority
	 * @param toCode
	 *            to coordinate code
	 * @return projection transform
	 */
	public static ProjectionTransform create(String fromAuthority,
			long fromCode, String toAuthority, long toCode) {
		return new ProjectionTransform(
				ProjectionFactory.getProjection(fromAuthority, fromCode),
				ProjectionFactory.getProjection(toAuthority, toCode));
	}

	/**
	 * Create a projection transform
	 * 
	 * @param fromAuthority
	 *            from coordinate authority
	 * @param fromCode
	 *            from coordinate code
	 * @param toAuthority
	 *            to coordinate authority
	 * @param toCode
	 *            to coordinate code
	 * @return projection transform
	 */
	public static ProjectionTransform create(String fromAuthority,
			String fromCode, String toAuthority, String toCode) {
		return new ProjectionTransform(
				ProjectionFactory.getProjection(fromAuthority, fromCode),
				ProjectionFactory.getProjection(toAuthority, toCode));
	}

	/**
	 * Constructor
	 * 
	 * @param fromProjection
	 *            from projection
	 * @param toProjection
	 *            to projection
	 */
	public ProjectionTransform(Projection fromProjection,
			Projection toProjection) {
		this.fromProjection = fromProjection;
		this.toProjection = toProjection;
		this.transform = ctFactory.createTransform(fromProjection.getCrs(),
				toProjection.getCrs());
	}

	/**
	 * Copy Constructor
	 * 
	 * @param transform
	 *            projection transform
	 */
	public ProjectionTransform(ProjectionTransform transform) {
		this(transform.getFromProjection(), transform.getToProjection());
	}

	/**
	 * Transform the projected coordinate
	 * 
	 * @param from
	 *            from coordinate
	 * @return to coordinate
	 */
	public ProjCoordinate transform(ProjCoordinate from) {
		ProjCoordinate to = new ProjCoordinate();
		transform.transform(from, to);
		return to;
	}

	/**
	 * Transform a x and y location
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @return transformed coordinates as [x, y]
	 */
	public double[] transform(double x, double y) {
		ProjCoordinate fromCoord = new ProjCoordinate(x, y);
		ProjCoordinate toCoord = transform(fromCoord);
		return new double[] { toCoord.x, toCoord.y };
	}

	/**
	 * Get the from projection in the transform
	 * 
	 * @return from projection
	 */
	public Projection getFromProjection() {
		return fromProjection;
	}

	/**
	 * Get the to projection in the transform
	 * 
	 * @return to projection
	 */
	public Projection getToProjection() {
		return toProjection;
	}

	/**
	 * Get the transform
	 * 
	 * @return transform
	 */
	public CoordinateTransform getTransform() {
		return transform;
	}

	/**
	 * Is the from and to projection the same?
	 * 
	 * @return true if the same projection
	 */
	public boolean isSameProjection() {
		return fromProjection.equals(toProjection);
	}

	/**
	 * Get the inverse transformation
	 * 
	 * @return inverse transformation
	 */
	public ProjectionTransform getInverseTransformation() {
		return toProjection.getTransformation(fromProjection);
	}

}
