/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
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

		createTreeTableViewer(tk, gridData, subPageComposite);
		setTreeTableViewerInput();
	}


	@SuppressWarnings("rawtypes")
	private void setTreeTableViewerInput() {
		Set<Object> input = new HashSet<Object>();
		Collection<IScrutinize> scrutinizers = PropertyValueHelper.getScrutinizers(getScrutinyAnalysis(), getFinderID());
		for (IScrutinize iScrutinize : scrutinizers) {
			Map<EObject, Boolean> result = (Map<EObject, Boolean>)iScrutinize.getAnalysisResult();
			for (Entry<EObject, Boolean> e : result.entrySet()) {
				EObject key = e.getKey();
				if (!PropertyValueHelper.isChildOfPropertyValue(key)){
					input.add(key);
				}
			}
		}
		treeViewer.setInput(input);
	}

	private void createTreeTableViewer(final FormToolkit tk, GridData gridData, Composite subPageComposite) {
		Composite toolbarsComposite = prepareComposite(tk, subPageComposite);
		ToolBar treeManagementBar = initializeToolBar(toolbarsComposite);
		initializeCollapseButton(treeManagementBar);
		initializeSelectAllButton(treeManagementBar);
		initializeDeselectAllButton(treeManagementBar);
		initializePkgFilteringButton(treeManagementBar);
		initializePgFilteringButton(treeManagementBar);
		initializePEnumFilteringButton(treeManagementBar);
		initializePvFilteringButton(treeManagementBar);
		initializeTextFilteringField(tk, toolbarsComposite, treeManagementBar);
		Composite treeTableComposite = tk.createComposite(subPageComposite);
		TreeColumnLayout treeColLayout = new TreeColumnLayout();
		treeTableComposite.setLayout(treeColLayout);
		treeTableComposite.setLayoutData(gridData);
		Tree tree = createTreeViewer(tk, gridData, treeTableComposite);
		createViewerColumns(treeColLayout, tree);
		setComparator();
		setFilters();
		setTreeProviders();
		addListeners();
		treeViewer.setUseHashlookup(true);
	}

	private void addListeners() {
		treeViewer.getTree().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int detail = e.detail;
				if (detail == SWT.CHECK){
					Widget item = e.item;
					if (item != null){
						TreeItem treeItem = (TreeItem)item;
						updateChildren(treeItem, treeItem.getChecked());
						updateParent(treeItem, treeItem.getChecked());
					}
				}
			}
		});
		treeViewer.getTree().addListener(SWT.Expand, new Listener() {

			@Override
			public void handleEvent(Event event) {
				Widget item = event.item;
				if (item != null){
					TreeItem i = (TreeItem)item;
					if (i.getChecked()){
						updateChildren(i, i.getChecked());
						updateParent(i, i.getChecked());
					}
				}
				treeViewer.refresh();
			}
		});
		
		((CheckboxTreeViewer)treeViewer).setCheckStateProvider(new ICheckStateProvider() {
			
			@Override
			public boolean isGrayed(Object element) {
				return false;
			}
			
			@Override
			public boolean isChecked(Object element) {
				return getStateOf(element);
			}
		});
	}

	private void setTreeProviders() {
		treeViewer.setContentProvider(new PropertyValueContentProvider());
		treeViewer.setLabelProvider(new PropertyValueLabelProvider());
	}

	private void setFilters() {
		treeViewer.addFilter(textFilter);
		treeViewer.addFilter(typeFilter);
	}

	private void setComparator() {
// FIXME API Change in Eclipse Neon
//		ViewerComparator comparator = new ViewerComparator(new PropertiesComparator()) {
//			@SuppressWarnings("unchecked")
//			@Override
//			public int compare(Viewer viewer, Object e1, Object e2) {
//				return getComparator().compare(e1, e2);
//			}
//		};
//
//		treeViewer.setComparator(comparator);
	}

	private void createViewerColumns(TreeColumnLayout treeColLayout, Tree tree) {
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
	}

	private Tree createTreeViewer(final FormToolkit tk, GridData gridData, Composite treeTableComposite) {
		Tree tree = tk.createTree(treeTableComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CHECK | SWT.MULTI);
		treeViewer = new CheckboxTreeViewer(tree);
		treeViewer.getTree().setHeaderVisible(true);
		treeViewer.getTree().setLayoutData(gridData);
		return tree;
	}

	private ToolBar initializeToolBar(Composite toolbarsComposite) {
		ToolBar treeManagementBar = new ToolBar(toolbarsComposite, SWT.FLAT);
		treeManagementBar.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		return treeManagementBar;
	}

	private Composite prepareComposite(final FormToolkit tk, Composite subPageComposite) {
		Composite toolbarsComposite = tk.createComposite(subPageComposite);
		GridLayoutFactory.fillDefaults().margins(0, 0).numColumns(2).equalWidth(false).applyTo(toolbarsComposite);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(toolbarsComposite);
		return toolbarsComposite;
	}

	private void initializeTextFilteringField(final FormToolkit tk, Composite toolbarsComposite,
			ToolBar treeManagementBar) {
		new ToolItem(treeManagementBar, SWT.SEPARATOR | SWT.VERTICAL);
		
		//Text filter
		final Text inputFilter = tk.createText(toolbarsComposite, "", SWT.SINGLE | SWT.LEFT | SWT.SEARCH);
		inputFilter.setToolTipText(Messages.Filter_PropertyValue);
		inputFilter.setMessage(". = any character, .* = any string"); //$NON-NLS-1$
		inputFilter.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		
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
					treeViewer.refresh();
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
	}

	private void initializePvFilteringButton(ToolBar treeManagementBar) {
		ToolItem pvItem = new ToolItem(treeManagementBar, SWT.CHECK);
		pvItem.setImage(Constants.getPropertyValueIcon());
		pvItem.setToolTipText("Property Values");
		pvItem.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				doViewerFilter(event, typeFilter, Constants.PROPERTY_VALUES);
			}
		});
	}

	private void initializePEnumFilteringButton(ToolBar treeManagementBar) {
		ToolItem enumTypeItem = new ToolItem(treeManagementBar, SWT.CHECK);
		enumTypeItem.setImage(Constants.getEnumTypeIcon());
		enumTypeItem.setToolTipText("Property Enumeration Type");
		enumTypeItem.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				doViewerFilter(event, typeFilter, Constants.PROPERTY_ENUMERATION_TYPE);
			}
		});
	}

	private void initializePgFilteringButton(ToolBar treeManagementBar) {
		ToolItem pgItem = new ToolItem(treeManagementBar, SWT.CHECK);
		pgItem.setImage(Constants.getPropertyGroupIcon());
		pgItem.setToolTipText("Property Group");
		pgItem.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				doViewerFilter(event, typeFilter, Constants.PROPERTY_VALUES_GROUP);
			}
		});
	}

	private void initializePkgFilteringButton(ToolBar treeManagementBar) {
		new ToolItem(treeManagementBar, SWT.SEPARATOR | SWT.VERTICAL);
		
		//Filtering tool item
		ToolItem pkgItem = new ToolItem(treeManagementBar, SWT.CHECK);
		pkgItem.setImage(Constants.getPropertyPackageIcon());
		pkgItem.setToolTipText("Property Package");
		pkgItem.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				doViewerFilter(event, typeFilter, Constants.PROPERTY_VALUES_PACKAGE);
			}
		});
	}

	private void initializeDeselectAllButton(ToolBar treeManagementBar) {
		final ToolItem deselctAll = new ToolItem(treeManagementBar, SWT.PUSH);
		deselctAll.setImage(Constants.getUncheckAllIcon());
		deselctAll.setToolTipText(Messages.Checkbox_UncheckAll);
		deselctAll.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] items = treeViewer.getTree().getItems();
				updateResult(false);
				for (TreeItem treeItem : items) {
					treeItem.setChecked(false);
					updateChildren(treeItem, false);
				}
			}
		});
	}

	private void initializeSelectAllButton(ToolBar treeManagementBar) {
		new ToolItem(treeManagementBar, SWT.SEPARATOR | SWT.VERTICAL);
		
		final ToolItem selctAll = new ToolItem(treeManagementBar, SWT.PUSH);
		selctAll.setImage(Constants.getCheckAllIcon());
		selctAll.setToolTipText(Messages.Checkbox_CheckAll);
		selctAll.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] items = treeViewer.getTree().getItems();
				updateResult(true);
				for (TreeItem treeItem : items) {
					treeItem.setChecked(true);
					updateChildren(treeItem, true);
				}
			}
		});
	}

	private void initializeCollapseButton(ToolBar treeManagementBar) {
		final ToolItem expandCollapse = new ToolItem(treeManagementBar, SWT.PUSH);
		expandCollapse.setImage(Constants.getExpandAllIcon());
		expandCollapse.setToolTipText(Messages.Collapse_expandAll);
		expandCollapse.addSelectionListener(new SelectionAdapter() {
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
				//PropertyValueHelper.refreshViewerFromResult(treeViewer, getFinderID());
				treeViewer.refresh();
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
		treeViewer.refresh();
	}
	
	@SuppressWarnings("rawtypes")
	private void updateResult(Object data, boolean checked) {
		if (data != null){
			Collection<IScrutinize> scrutinizers = PropertyValueHelper.getScrutinizers(getScrutinyAnalysis(), getFinderID());
			ITreeContentProvider contentProvider = (ITreeContentProvider) treeViewer.getContentProvider();
			for (IScrutinize iScrutinize : scrutinizers) {
				@SuppressWarnings("unchecked")
				Map<Object, Boolean> analysisResult = (Map<Object, Boolean>) iScrutinize.getAnalysisResult();
				if (analysisResult.containsKey(data)) {
					analysisResult.put(data, checked);
					Object parent = contentProvider.getParent(data);
					if (parent != null && analysisResult.containsKey(parent) && !checked){
						analysisResult.put(parent, checked);
					}
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void updateResult(boolean checked) {
		Collection<IScrutinize> scrutinizers = PropertyValueHelper.getScrutinizers(getScrutinyAnalysis(), getFinderID());
		for (IScrutinize iScrutinize : scrutinizers) {
			@SuppressWarnings("unchecked")
			Map<Object, Boolean> analysisResult = (Map<Object, Boolean>) iScrutinize.getAnalysisResult();
			for(Entry<Object, Boolean> entry: analysisResult.entrySet()){
				entry.setValue(checked);
			}
		}
	}

	private void updateParent(TreeItem treeItem, boolean state) {
		if (treeItem != null){ 
			TreeItem parent = treeItem.getParentItem();
			if (parent != null){
				boolean parentState = parent.getChecked();
				Object data = parent.getData();
				if (data != null && parentState && parentState != state){
					updateResult(data, state);
					parent.setChecked(state);
					updateParent(parent, state);
				}
			}
		}
	}

	private void updateChildren(TreeItem treeItem, boolean state) {
		if (treeItem != null){ 
			TreeItem[] items = treeItem.getItems();
			Object data = treeItem.getData();
			if (data != null){
				updateResult(data, treeItem.getChecked());
			}
			if (items != null && items.length != 0){
				for (TreeItem item : items) {
					item.setChecked(state);
					updateChildren(item, state);
				}
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private boolean getStateOf(Object elt) {
		Collection<IScrutinize> scrutinizers = PropertyValueHelper.getScrutinizers(getScrutinyAnalysis(), getFinderID());
		for (IScrutinize iScrutinize : scrutinizers) {
			Map<Object, Boolean> analysisResult = (Map<Object, Boolean>) iScrutinize.getAnalysisResult();
			if (analysisResult.containsKey(elt)){
				return analysisResult.get(elt);
			}
		}
		return false;
	}
}