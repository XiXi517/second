/**
 * 
 */
package com.senlang.sdip.model.tree;

/**
 * @author Mc.D
 *
 */
public interface TreeNode {
	public long getId();
	public String getName();
	public String getParentId();
	public int getOrderId();
}
