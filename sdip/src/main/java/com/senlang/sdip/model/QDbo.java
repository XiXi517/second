/**
 * 
 */
package com.senlang.sdip.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.Path;

/**
 * @author Mc.D
 *
 */
public class QDbo extends EntityPathBase<Dbo> {

	/**
	* 
	*/
	private static final long serialVersionUID = -1258682410762570218L;

	public static final QDbo dbo = new QDbo("dbo");

	public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

	public final StringPath createdBy = createString("createdBy");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

	public final StringPath updatedBy = createString("updatedBy");

	public QDbo(String variable) {
		super(Dbo.class, forVariable(variable));
	}

	public QDbo(Path<? extends Dbo> path) {
		super(path.getType(), path.getMetadata());
	}

	public QDbo(PathMetadata metadata) {
		super(Dbo.class,metadata);
	}

}
