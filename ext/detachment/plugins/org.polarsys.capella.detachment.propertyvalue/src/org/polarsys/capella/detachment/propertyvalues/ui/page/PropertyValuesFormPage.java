/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.detachment.propertyvalues.ui.page;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.PatternSyntaxException;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.polarsys.capella.detachment.propertyvalue.messages.Messages;
import org.polarsys.kitalpha.model.common.scrutiny.interfaces.IScrutinize;

public class PropertyValuesFormPage extends org.polarsys.kitalpha.model.detachment.ui.page.AbstractDetachmentFormPage {

	private TreeViewer treeViewer;
	private int maskTypeFilter = 0;

	private final PropertyValueTextFilterViewer textFilter = new PropertyValueTextFilterViewer();
	private final PropertyValueTypeFilterViewer typeFilter = new PropertyValueTypeFilterViewer();

	public PropertyValuesFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	@Override
	protected void createFormContent(IManagedForm managedForm) {

		final FormToolkit tk = managedForm.getToolkit();
		final ScrolledForm scrolledForm = managedForm.getForm();

		scrolledForm.setText(Messages.Title_DetachmentPropertyValues);
		scrolledForm.setExpandHorizontal(true);

		Composite pageComposite = scrolledForm.getBody();
		GridLayout gridLayout = new GridLayout(1, true);
		pageComposite.setLayout(gridLayout);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		pageComposite.setLayoutData(gridData);

		Composite subPageComposite = tk.createComposite(pageComposite);
		GridLayoutFactory.fillDefaults().margins(10, 5).numColumns(1).applyTo(subPageComposite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(subPageComposite);

		Group filterGroup = createAndInitGroupFilter(subPageComposite);

		final Label filterinTextgFieldLabel = tk.createLabel(filterGroup, Messages.Label_FilterTextPattern);
		filterinTextgFieldLabel.pack();
		createAndInitInputTextFilter(filterGroup);
		tk.createLabel(filterGroup, Messages.Label_FilterType);
		createAndInitToolbarFilter(typeFilter, filterGroup);
		createTreeTableViewer(tk, gridData, subPageComposite);
		setTreeTableViewerInput();
	}

	@SuppressWarnings("rawtypes")
	private void setTreeTableViewerInput() {
		Set<Object> input = new HashSet<Object>();
		Collection<IScrutinize> scrutinizers = PropertyValueHelper.getScrutinizers(getFinderID());
		for (IScrutinize iScrutinize : scrutinizers) {
			input.addAll((((Map<?, ?>) iScrutinize.getAnalysisResult()).keySet()));
		}
		treeViewer.setInput(input);
	}

	private void createTreeTableViewer(final FormToolkit tk, GridData gridData, Composite subPageComposite) {
		
		ToolBar treeManagementBar = new ToolBar(subPageComposite, SWT.FLAT);
		GridLayoutFactory.fillDefaults().margins(10, 5).numColumns(1).applyTo(treeManagementBar);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(treeManagementBar);
		treeManagementBar.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		
		final ToolItem expandCollapse = new ToolItem(treeManagementBar, SWT.PUSH);
		expandCollapse.setImage(Constants.getExpandAllIcon());
		expandCollapse.setToolTipText(Messages.Collapse_expandAll);
		expandCollapse.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (expandCollapse.getToolTipText().equalsIgnoreCase(Messages.Collapse_expandAll)){
					expandCollapse.setImage(Constants.getCollapsAllIcon());
					expandCollapse.setToolTipText(Messages.Collapse_CollapsedAll);
					treeViewer.expandAll();
				} else {
					expandCollapse.setImage(Constants.getExpandAllIcon());
					expandCollapse.setToolTipText(Messages.Collapse_expandAll);
					treeViewer.collapseAll();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		final ToolItem deselctAll = new ToolItem(treeManagementBar, SWT.PUSH);
		deselctAll.setImage(Constants.getCheckAllIcon());
		deselctAll.setToolTipText(Messages.Checkbox_CheckAll);
		deselctAll.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (deselctAll.getToolTipText().equalsIgnoreCase(Messages.Checkbox_CheckAll)){
					deselctAll.setImage(Constants.getUncheckAllIcon());
					deselctAll.setToolTipText(Messages.Checkbox_UncheckAll);
					updateResult(true);
					updateTreeFromResult();
				} else {
					deselctAll.setImage(Constants.getCheckAllIcon());
					deselctAll.setToolTipText(Messages.Checkbox_CheckAll);
					updateResult(false);
					updateTreeFromResult();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Composite treeTableComposite = tk.createComposite(subPageComposite);
		TreeColumnLayout treeColLayout = new TreeColumnLayout();
		treeTableComposite.setLayout(treeColLayout);
		treeTableComposite.setLayoutData(gridData);

		Tree tree = tk.createTree(treeTableComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CHECK);

		treeViewer = new TreeViewer(tree);
		treeViewer.getTree().setHeaderVisible(true);
		treeViewer.getTree().setLayoutData(gridData);

		// Columns
		TreeColumn propertiesColumn = new TreeColumn(tree, SWT.LEFT);
		propertiesColumn.setAlignment(SWT.LEFT);
		propertiesColumn.setText(Messages.Pv);
		treeColLayout.setColumnData(propertiesColumn, new ColumnWeightData(40, true));

		TreeColumn propertyPath = new TreeColumn(tree, SWT.RIGHT);
		propertyPath.setAlignment(SWT.LEFT);
		propertyPath.setText(Messages.PvPath);
		treeColLayout.setColumnData(propertyPath, new ColumnWeightData(90, true));

		tree.setLinesVisible(true);

		ViewerComparator comparator = new ViewerComparator(new PropertiesComparator()) {
			@SuppressWarnings("unchecked")
			@Override
			public int compare(Viewer viewer, Object e1, Object e2) {
				return getComparator().compare(e1, e2);
			}
		};

		treeViewer.setComparator(comparator);

		treeViewer.addFilter(textFilter);
		treeViewer.addFilter(typeFilter);

		treeViewer.setContentProvider(new PropertyValueContentProvider());

		treeViewer.setLabelProvider(new PropertyValueLabelProvider());

		treeViewer.getTree().addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				int detail = e.detail;
				if (detail == SWT.CHECK) {
					Widget item = e.item;
					if (item != null) {
						TreeItem treeItem = (TreeItem) item;
						boolean checked = treeItem.getChecked();
						Object data = item.getData();
						updateResult(data, checked);
						updateTreeFromResult();
					}
				}
			}

			

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		
		treeViewer.setUseHashlookup(true);
	}

	private Group createAndInitGroupFilter(Composite subPageComposite) {
		Group filterGroup = new Group(subPageComposite, SWT.SHADOW_ETCHED_IN);
		filterGroup.setText(Messages.Title_GroupFiltering);
		GridLayoutFactory.fillDefaults().margins(10, 5).numColumns(2).applyTo(filterGroup);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(filterGroup);
		filterGroup.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		return filterGroup;
	}

	private StyledText createAndInitInputTextFilter(Group filterGroup) {
		final StyledText inputFilter = new StyledText(filterGroup, SWT.SINGLE | SWT.LEFT | SWT.SEARCH);
		inputFilter.setToolTipText(Messages.Filter_PropertyValue);
		inputFilter.setMargins(5, 1, 5, 1);
		inputFilter.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		inputFilter.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				e.gc.setAntialias(SWT.ON);
				Color blue = Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_BORDER);
				e.gc.setAlpha(140);
				e.gc.setForeground(blue);
				Rectangle rec = new Rectangle(0, 0, inputFilter.getClientArea().width - 1,
						inputFilter.getClientArea().height - 1);
				e.gc.drawRoundRectangle(0, 0, rec.width, rec.height, 3, 3);
			}
		});

		inputFilter.addKeyListener(new KeyAdapter() {

			ErrorRegExpMessageToolTip errorToolTip;

			@Override
			public void keyReleased(KeyEvent e) {
				try {

					if (errorToolTip != null) {
						errorToolTip.hide();
						errorToolTip.deactivate();
					}
					inputFilter.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
					textFilter.setPattern(inputFilter.getText());
					updateTreeFromResult();
				} catch (PatternSyntaxException ex) {
					inputFilter.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
					if (errorToolTip == null) {
						errorToolTip = new ErrorRegExpMessageToolTip(inputFilter, "");
					}

					Point location = inputFilter.getLocation();
					errorToolTip.setRegExp(inputFilter.getText());
					errorToolTip.activate();
					errorToolTip.show(new Point(location.x * 2, location.y + 15));
				}
			}
		});
		return inputFilter;
	}

	private void createAndInitToolbarFilter(final PropertyValueTypeFilterViewer typeFilter, Group filterGroup) {
		ToolBar toolbar = new ToolBar(filterGroup, SWT.FLAT);
		GridLayoutFactory.fillDefaults().margins(10, 5).numColumns(1).applyTo(toolbar);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(toolbar);
		toolbar.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));

		ToolItem pvItem = new ToolItem(toolbar, SWT.CHECK);
		pvItem.setImage(Constants.getPropertyValueIcon());
		pvItem.setToolTipText("Property Values");
		pvItem.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				doViewerFilter(event, typeFilter, Constants.PROPERTY_VALUES);
			}
		});

		ToolItem pgItem = new ToolItem(toolbar, SWT.CHECK);
		pgItem.setImage(Constants.getPropertyGroupIcon());
		pgItem.setToolTipText("Property Group");
		pgItem.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				doViewerFilter(event, typeFilter, Constants.PROPERTY_VALUES_GROUP);
			}
		});
		
		ToolItem pkgItem = new ToolItem(toolbar, SWT.CHECK);
		pkgItem.setImage(Constants.getPropertyPackageIcon());
		pkgItem.setToolTipText("Property Package");
		pkgItem.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				doViewerFilter(event, typeFilter, Constants.PROPERTY_VALUES_PACKAGE);
			}
		});

		ToolItem enumTypeItem = new ToolItem(toolbar, SWT.CHECK);
		enumTypeItem.setImage(Constants.getEnumTypeIcon());
		enumTypeItem.setToolTipText("Property Enumeration Type");
		enumTypeItem.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				doViewerFilter(event, typeFilter, Constants.PROPERTY_ENUMERATION_TYPE);
			}
		});
	}

	private void doViewerFilter(Event e, PropertyValueTypeFilterViewer filter, int propertyValueType) {
		boolean isChecked = ((ToolItem) e.widget).getSelection();

		if (isChecked) {
			maskTypeFilter = maskTypeFilter | propertyValueType;
		} else {
			maskTypeFilter = maskTypeFilter & ~propertyValueType;
		}
		filter.setFilterType(maskTypeFilter);
		updateTreeFromResult();
	}
	
	@SuppressWarnings("rawtypes")
	private void updateResult(Object data, boolean checked) {
		Collection<IScrutinize> scrutinizers = PropertyValueHelper.getScrutinizers(getFinderID());
		for (IScrutinize iScrutinize : scrutinizers) {
			@SuppressWarnings("unchecked")
			Map<Object, Boolean> analysisResult = (Map<Object, Boolean>) iScrutinize.getAnalysisResult();
			if (analysisResult.containsKey(data)) {
				analysisResult.put(data, checked);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void updateResult(boolean checked) {
		Collection<IScrutinize> scrutinizers = PropertyValueHelper.getScrutinizers(getFinderID());
		for (IScrutinize iScrutinize : scrutinizers) {
			@SuppressWarnings("unchecked")
			Map<Object, Boolean> analysisResult = (Map<Object, Boolean>) iScrutinize.getAnalysisResult();
			for(Entry<Object, Boolean> entry: analysisResult.entrySet()){
				entry.setValue(checked);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void updateTreeFromResult() {
		Collection<IScrutinize> scrutinizers = PropertyValueHelper.getScrutinizers(getFinderID());
		for (IScrutinize iScrutinize : scrutinizers) {
			@SuppressWarnings("unchecked")
			Map<Object, Boolean> analysisResult = (Map<Object, Boolean>) iScrutinize.getAnalysisResult();
			for (Entry<Object, Boolean> entry : analysisResult.entrySet()) {
				updateTree(entry.getKey(), entry.getValue());
			}
		}
	}
	
	private void updateTree(Object data, boolean checked) {
		TreeItem[] items = treeViewer.getTree().getItems();

		if (items != null && items.length > 0) {
			for (TreeItem item : items) {
				updateTree(item, data, checked);
			}
		}
		treeViewer.getTree().redraw();
		treeViewer.refresh();
	}

	private void updateTree(TreeItem item, Object data, boolean checked) {
		if (data != null && item.getData() != null && item.getData().equals(data)) {
			item.setChecked(checked);
			updateTreeItemChildren(item, data, checked);
			updateTreeItemParents(item, data, checked);
		}
		
		TreeItem[] items = item.getItems();
		for (TreeItem i : items) {
			updateTree(i, data, checked);
		}
	}

	private void updateTreeItemParents(TreeItem treeItem, Object data, boolean checked) {
		TreeItem parentItem = treeItem.getParentItem();
		if (parentItem != null) {
			parentItem.setChecked(checked);
			parentItem.setGrayed(checked);
			updateTreeItemParents(parentItem, data, checked);
		}
	}

	private void updateTreeItemChildren(TreeItem treeItem, Object data, boolean checked) {
		TreeItem[] items = treeItem.getItems();

		if (items != null && items.length > 0) {
			for (TreeItem t : items) {
					t.setChecked(checked);
					updateTreeItemChildren(t, data, checked);
				}
			}
	}

}