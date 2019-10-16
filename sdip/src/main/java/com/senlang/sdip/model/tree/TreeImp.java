/**
 * 
 */
package com.senlang.sdip.model.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.senlang.sdip.util.StringHelper;

import lombok.Data;

/**
 * @author Mc.D
 *
 */
@Data
public class TreeImp implements Tree {
	public TreeImp(List<TreeNode> list) {
		this.initMap(list);
		this.initList();
	}

	private Map<Long, TreeNodeImp> treeNodesMap;
	private List<TreeNodeImp> treeNodesList;

	@Override
	public List<TreeNodeImp> getTree() {
		return this.getTreeNodesList() == null ? new ArrayList<>() : this.getTreeNodesList();
	}

	@Override
	public List<TreeNodeImp> getRoot() {
		return this.getTreeNodesList() == null || this.getTreeNodesList().isEmpty() ? new ArrayList<>()
				: this.getTreeNodesList().parallelStream().filter(c -> c.getParent() == null)
						.collect(Collectors.toList());
	}

	@Override
	public TreeNode getTreeNode(String nodeId) {
		return this.getTreeNodesMap().get(nodeId);
	}

	private void initMap(List<TreeNode> list) {
		this.setTreeNodesMap(list.stream().map(c -> new TreeNodeImp(c))
				.collect(Collectors.toMap(TreeNodeImp::getId, Function.identity())));
		this.getTreeNodesMap().values().parallelStream().filter(c -> !StringHelper.isNullOrWhiteSpace(c.getParentId()))
				.forEach(c -> {
					TreeNodeImp parent = this.getTreeNodesMap().get(c.getParentId());
					if (parent != null) {
						c.setParent(parent);
						parent.addChild(c);
					}
				});
	}

	private void initList() {
		if (this.getTreeNodesList() != null) {
			return;
		}
		if (this.getTreeNodesMap() == null || this.getTreeNodesMap().isEmpty()) {
			return;
		}
		this.getTreeNodesMap().values().parallelStream().filter(c -> c.getParent() == null).forEach(c -> {
			this.getTreeNodesList().add(c);
			this.getTreeNodesList().addAll(c.getAllChildren());
		});
	}
}
