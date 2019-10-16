/**
 * 
 */
package com.senlang.sdip.model.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author Mc.D
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TreeNodeImp implements TreeNode {

	public TreeNodeImp() {
		this.setChildren(new ArrayList<>());
		this.setAllChildren(new ArrayList<>());
	}

	public TreeNodeImp(TreeNode o) {
		this.setId(o.getId());
		this.setName(o.getName());
		this.setParentId(o.getParentId());
		this.setOrderId(o.getOrderId());
		this.setChildren(new ArrayList<>());
		this.setAllChildren(new ArrayList<>());
	}

	private long id;
	private String name;
	private String parentId;
	private int orderId;
	private int level;
	private TreeNode parent;
	private List<TreeNodeImp> children;
	@JsonIgnore
	private List<TreeNodeImp> allChildren;

	public void addChild(TreeNodeImp node) {
		this.children.add(node);
	}

	public void removeChild(TreeNodeImp node) {
		this.children.remove(node);
	}

	public List<TreeNodeImp> getAllChildren() {
		if (this.allChildren.isEmpty()) {
			this.allChildren.addAll(this.children);
			this.allChildren.addAll(this.children.parallelStream().flatMap(c -> c.getAllChildren().parallelStream()).collect(Collectors.toList()));
		}
		return this.allChildren;
	}
}
