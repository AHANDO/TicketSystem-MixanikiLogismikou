package com.myticketsystem.hys;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.plaf.TreeUI;
import javax.swing.tree.TreeCellRenderer;

import com.alee.laf.tree.WebTreeElement;
import com.alee.laf.tree.WebTreeStyle;
import com.alee.laf.tree.WebTreeUI;
import com.alee.utils.ImageUtils;
import com.alee.utils.TextUtils;

/*
 * Xreiastike custom tree cell renderer, gia na min exoun icons ta nodes tou WebTree 
 * (i naxoun custom icon, alla emeis dialexame na min exoun)
 */

public class CustomTreeCellRenderer extends WebTreeElement implements TreeCellRenderer {

	private static final long serialVersionUID = 1L;

	public static final String ID_PREFIX = "WTCR";

	protected ImageIcon closedIcon = WebTreeUI.CLOSED_ICON;

	protected String id;
	
	protected ImageIcon leafIcon = WebTreeUI.LEAF_ICON;

	protected ImageIcon openIcon = WebTreeUI.OPEN_ICON;

	protected ImageIcon rootIcon = WebTreeUI.ROOT_ICON;

	public CustomTreeCellRenderer() {
		super();
		setId();
		setName("Tree.cellRenderer");
		setForeground(UIManager.getColor("Tree.textForeground"));
	}

	public Icon getClosedIcon() {
		return closedIcon;
	}

	private String getIconTypeKey(final String type) {
		return "WebTreeCellRenderer." + id + "." + type;
	}

	public Icon getLeafIcon() {
		return leafIcon;
	}

	public Icon getOpenIcon() {
		return openIcon;
	}

	public Icon getRootIcon() {
		return rootIcon;
	}


	@Override
	public WebTreeElement getTreeCellRendererComponent(final JTree tree, final Object value, final boolean isSelected,
			final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {
		final boolean enabled = tree.isEnabled();

		// Visual settings
		setFont(tree.getFont());
		setEnabled(enabled);

		// Icon
		final ImageIcon icon = leaf ? leafIcon
				: tree.getModel().getRoot() == value ? rootIcon : expanded ? openIcon : closedIcon;
		if (enabled) {
			// setIcon ( Helper.getIcon("images/tick.png") );
			// setIcon ( icon );
		} else {
			final String type = leaf ? "leaf"
					: tree.getModel().getRoot() == value ? "root" : expanded ? "open" : "closed";
			setIcon(ImageUtils.getDisabledCopy(getIconTypeKey(type), icon));
		}

		// Text
		setText(tree.convertValueToText(value, isSelected, expanded, leaf, row, hasFocus));

		// Border
		final TreeUI tui = tree.getUI();
		final int sw = tui instanceof WebTreeUI ? ((WebTreeUI) tui).getSelectionShadeWidth()
				: WebTreeStyle.selectionShadeWidth;
		setMargin(sw + 2, sw + 2, sw + 2, sw + 4);

		// Orientation
		setComponentOrientation(tree.getComponentOrientation());

		return this;
	}

	public void setClosedIcon(final Icon closedIcon) {
		this.closedIcon = closedIcon != null ? ImageUtils.getImageIcon(closedIcon) : null;
		ImageUtils.clearDisabledCopyCache(getIconTypeKey("closed"));
	}

	private void setId() {
		this.id = TextUtils.generateId(ID_PREFIX);
	}

	public void setLeafIcon(final Icon leafIcon) {
		this.leafIcon = leafIcon != null ? ImageUtils.getImageIcon(leafIcon) : null;
		ImageUtils.clearDisabledCopyCache(getIconTypeKey("leaf"));
	}

	public void setOpenIcon(final Icon openIcon) {
		this.openIcon = openIcon != null ? ImageUtils.getImageIcon(openIcon) : null;
		ImageUtils.clearDisabledCopyCache(getIconTypeKey("open"));
	}

	public void setRootIcon(final Icon rootIcon) {
		this.rootIcon = rootIcon != null ? ImageUtils.getImageIcon(rootIcon) : null;
		ImageUtils.clearDisabledCopyCache(getIconTypeKey("root"));
	}
}