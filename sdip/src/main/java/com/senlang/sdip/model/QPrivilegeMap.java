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
public class QPrivilegeMap extends EntityPathBase<PrivilegeMap> {

	/**
	* 
	*/
	private static final long serialVersionUID = -1258682410762570218L;

	public static final QPrivilegeMap dbo = new QPrivilegeMap("privilegeMap");

	public final com.senlang.sdip.model.QDbo _super = new com.senlang.sdip.model.QDbo(this);
	
	public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

	public final StringPath createdBy = _super.createdBy;

	public final NumberPath<Long> id = _super.id;

	public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

	public final StringPath updatedBy = _super.updatedBy;
	
	public final StringPath url = createString("url");
	
	public final StringPath name = createString("name");
	
	public final StringPath method = createString("method");
	
	public final StringPath category = createString("category");

	public QPrivilegeMap(String variable) {
		super(PrivilegeMap.class, forVariable(variable));
	}

	public QPrivilegeMap(Path<? extends PrivilegeMap> path) {
		super(path.getType(), path.getMetadata());
	}

	public QPrivilegeMap(PathMetadata metadata) {
		super(PrivilegeMap.class,metadata);
	}

}
