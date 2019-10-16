/**
 * 
 */
package com.senlang.sdip.model.tree;

import java.util.List;

/**
 * @author Mc.D
 *
 */
public interface Tree {
	public List<TreeNodeImp> getTree();
	public List<TreeNodeImp> getRoot();
	public TreeNode getTreeNode(String nodeId);
}
