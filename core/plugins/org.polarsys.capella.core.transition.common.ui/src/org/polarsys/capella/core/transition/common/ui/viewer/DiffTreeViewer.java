/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.ui.viewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem.FilterAction;
import org.polarsys.capella.core.transition.common.handlers.filter.CompoundFilteringItems;
import org.polarsys.capella.core.transition.common.handlers.filter.FilteringDifferencesHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.filter.IFilterItem;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelType.DiffAction;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelType.DiffScope;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelViewer;
import org.polarsys.capella.core.transition.common.ui.Activator;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class DiffTreeViewer extends Composite {

  private TreeViewer viewerResult;

  List<IDiffModelViewer> listDiff = new ArrayList<IDiffModelViewer>();

  private HashMap<IDifference, IDiffModelViewer> listMerge;

  List<IDiffModelViewer> listDisplayDiff = new ArrayList<IDiffModelViewer>();

  public final static int COL_ELT = 0;

  public final static int COL_TYPE = 1;

  public final static int COL_SCOPE = 2;

  public final static int COL_DIFF = 3;

  public final static int COL_ACTION = 4;

  public final static int COL_DETAIL = 5;

  public final static int COL_TRACEABILITY = 6;

  private static final int COL_TYPE_WIDTH = 70;

  private static final int COL_ELT_WIDTH = 80;

  private static final int COL_TRACEABILITY_WIDTH = 30;

  private static final int COL_SCOPE_WIDTH = 60;

  private static final int COL_DIFF_WIDTH = 520;

  private static final int COL_ACTION_WIDTH = 140;

  private static final int COL_DETAIL_WIDTH = 0;

  private final String COL_ELT_NAME = "Kind";

  private final String COL_TYPE_NAME = "Type";

  private final String COL_SCOPE_NAME = "Scope";

  private final String COL_DIFF_NAME = "Differences";

  private final String COL_ACTION_NAME = "Action";

  private final String COL_DETAIL_NAME = "Detail";

  private final String COL_TRACEABILITY_NAME = "T";

  private String[] columnNames = new String[] { COL_ELT_NAME, COL_TYPE_NAME, COL_SCOPE_NAME, COL_DIFF_NAME, COL_ACTION_NAME, COL_DETAIL_NAME,
                                               COL_TRACEABILITY_NAME };

  IContext context;

  /**
   */
  public DiffTreeViewer(IContext context_p, Composite parent, HashMap<IDifference, IDiffModelViewer> listMerge_p, List<IDiffModelViewer> listDiff_p) {
    this(context_p, parent, listMerge_p, listDiff_p, false);
  }

  public DiffTreeViewer(IContext context_p, Composite parent, HashMap<IDifference, IDiffModelViewer> listMerge_p, List<IDiffModelViewer> listDiff_p,
      boolean activateListener_p) {
    super(parent, SWT.NONE);
    context = context_p;
    this.listDiff = listDiff_p;
    this.listMerge = listMerge_p;
    createCompositeTree(parent);
    createActions();
  }

  /**
   * @param parent
   */
  private void createCompositeTree(Composite parent) {
    setLayoutData(new GridData(GridData.FILL_BOTH));
    setLayout(new FillLayout());

    loadImageRegistry();

    // Create columns
    DiffTreeSorter ts = new DiffTreeSorter();
    Tree tree = new Tree(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
    viewerResult = new TreeViewer(tree);

    String[] columns = getColumns();

    TreeViewerColumn treecolumnviewer = new TreeViewerColumn(viewerResult, SWT.LEFT);
    TreeColumn treeColumn = treecolumnviewer.getColumn();
    treeColumn.setText(columns[COL_ELT]);
    treeColumn.setWidth(COL_ELT_WIDTH);
    treeColumn.setResizable(true);
    treeColumn.addSelectionListener(new DiffTreeSelectionAdapter(ts, treeColumn, COL_ELT, viewerResult));
    treecolumnviewer.setLabelProvider(createLabelProvider(COL_ELT));
    treecolumnviewer.setEditingSupport(createCellModifier(COL_ELT));

    treecolumnviewer = new TreeViewerColumn(viewerResult, SWT.LEFT);
    treeColumn = treecolumnviewer.getColumn();
    treeColumn.setText(columns[COL_TYPE]);
    treeColumn.setWidth(COL_TYPE_WIDTH);
    treeColumn.setResizable(true);
    treeColumn.addSelectionListener(new DiffTreeSelectionAdapter(ts, treeColumn, COL_TYPE, viewerResult));
    treecolumnviewer.setLabelProvider(createLabelProvider(COL_TYPE));
    treecolumnviewer.setEditingSupport(createCellModifier(COL_TYPE));

    treecolumnviewer = new TreeViewerColumn(viewerResult, SWT.LEFT);
    treeColumn = treecolumnviewer.getColumn();
    treeColumn.setText(columns[COL_TRACEABILITY]);
    treeColumn.setToolTipText("Traceability");
    treeColumn.setWidth(COL_TRACEABILITY_WIDTH);
    treeColumn.setResizable(true);
    treeColumn.addSelectionListener(new DiffTreeSelectionAdapter(ts, treeColumn, COL_TRACEABILITY, viewerResult));
    treecolumnviewer.setLabelProvider(createLabelProvider(COL_TRACEABILITY));
    treecolumnviewer.setEditingSupport(createCellModifier(COL_TRACEABILITY));

    treecolumnviewer = new TreeViewerColumn(viewerResult, SWT.LEFT);
    treeColumn = treecolumnviewer.getColumn();
    treeColumn.setText(columns[COL_SCOPE]);
    treeColumn.setWidth(COL_SCOPE_WIDTH);
    treeColumn.setResizable(true);
    treeColumn.addSelectionListener(new DiffTreeSelectionAdapter(ts, treeColumn, COL_SCOPE, viewerResult));
    treecolumnviewer.setLabelProvider(createLabelProvider(COL_SCOPE));
    treecolumnviewer.setEditingSupport(createCellModifier(COL_SCOPE));

    treecolumnviewer = new TreeViewerColumn(viewerResult, SWT.LEFT);
    treeColumn = treecolumnviewer.getColumn();
    treeColumn.setText(columns[COL_DIFF]);
    treeColumn.setWidth(COL_DIFF_WIDTH);
    treeColumn.setResizable(true);
    treeColumn.addSelectionListener(new DiffTreeSelectionAdapter(ts, treeColumn, COL_DIFF, viewerResult));
    treecolumnviewer.setLabelProvider(createLabelProvider(COL_DIFF));
    treecolumnviewer.setEditingSupport(createCellModifier(COL_DIFF));

    treecolumnviewer = new TreeViewerColumn(viewerResult, SWT.LEFT);
    treeColumn = treecolumnviewer.getColumn();
    treeColumn.setText(columns[COL_ACTION]);
    treeColumn.setWidth(COL_ACTION_WIDTH);
    treeColumn.setResizable(true);
    treeColumn.addSelectionListener(new DiffTreeSelectionAdapter(ts, treeColumn, COL_ACTION, viewerResult));
    treecolumnviewer.setLabelProvider(createLabelProvider(COL_ACTION));

    // Create the cell editors
    treecolumnviewer.setEditingSupport(createCellModifier(COL_ACTION));

    treecolumnviewer = new TreeViewerColumn(viewerResult, SWT.LEFT);
    treeColumn = treecolumnviewer.getColumn();
    treeColumn.setText(columns[COL_DETAIL]);
    treeColumn.setWidth(COL_DETAIL_WIDTH);
    treeColumn.setResizable(true);
    treeColumn.addSelectionListener(new DiffTreeSelectionAdapter(ts, treeColumn, COL_DETAIL, viewerResult));
    treecolumnviewer.setLabelProvider(createLabelProvider(COL_DETAIL));
    treecolumnviewer.setEditingSupport(createCellModifier(COL_DETAIL));

    ColumnViewerToolTipSupport.enableFor(viewerResult);
    viewerResult.setColumnProperties(columns);
    viewerResult.setSorter(ts);
    viewerResult.setContentProvider(new DiffTreeContentProvider());

    // Set default tree column
    tree.setHeaderVisible(true);
    tree.setLinesVisible(true);
    tree.setSortDirection(SWT.UP);
    tree.setSortColumn(tree.getColumn(COL_ELT));

    setListDiff(listDiff);
  }

  /**
   * @param colAction_p
   * @return
   */
  protected EditingSupport createCellModifier(int index_p) {
    switch (index_p) {
      case COL_ACTION:
        return new DiffTreeCellModifier(this, viewerResult);

      default:
      break;
    }
    return null;
  }

  /**
   * 
   */
  protected void loadImageRegistry() {

    // Load images
    ImageRegistry reg = Activator.getDefault().getImageRegistry();
    ImageDescriptor desc = reg.getDescriptor("unchecked.gif");
    if (desc == null) {
      desc = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.getDefault().PLUGIN_ID, "icons/ctool16/unchecked.gif");
      reg.put("unchecked.gif", desc.createImage());
    }
    desc = reg.getDescriptor("checked.gif");
    if (desc == null) {
      desc = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.getDefault().PLUGIN_ID, "icons/ctool16/checked.gif");
      reg.put("checked.gif", desc.createImage());
    }
    desc = reg.getDescriptor("process.png");
    if (desc == null) {
      desc = AbstractUIPlugin.imageDescriptorFromPlugin("org.polarsys.capella.core.ui.resources", "icons/full/obj16/capella_process.gif");
      reg.put("process.png", desc.createImage());
    }
  }

  /**
   * @param colElt_p
   * @return
   */
  protected CellLabelProvider createLabelProvider(int index_p) {

    switch (index_p) {
      case COL_ELT:
        return new DiffTreeColumnLabelProvider() {

          @Override
          public Color getBackground(Object element) {
            return super.getBackground(element);
          }

          @Override
          public String getText(Object element) {
            return ((IDiffModelViewer) element).getElementDiff().toString();
          }

          @Override
          public Image getImage(Object element) {
            IDiffModelViewer diff = (IDiffModelViewer) element;
            return diff.getImage();
          }

          @Override
          public String getToolTipText(Object element) {
            String tooltip = "Capella Element is not present in current Workspace";
            EObject me = ((IDiffModelViewer) element).getSemanticElementDiff();
            if (me != null) {
              tooltip = EObjectLabelProviderHelper.getText(me);
            }
            return tooltip;
          }
        };

      case COL_TYPE:
        return new DiffTreeColumnLabelProvider() {

          @Override
          public Image getImage(Object element_p) {
            return super.getImage(element_p);
          }

          @Override
          public String getText(Object element_p) {
            return ((IDiffModelViewer) element_p).getTypeDiff().toString();
          }
        };

      case COL_TRACEABILITY:
        return new DiffTreeColumnLabelProvider() {

          @Override
          public Image getImage(Object element_p) {
            return super.getImage(element_p);
          }

          @Override
          public String getText(Object element_p) {
            return ((IDiffModelViewer) element_p).getTraceability();
          }
        };

      case COL_SCOPE:
        return new DiffTreeColumnLabelProvider() {
          @Override
          public String getText(Object element) {
            return ((IDiffModelViewer) element).getScopeDiff().toString();
          }

        };

      case COL_DIFF:
        return new DiffTreeColumnLabelProvider() {
          @Override
          public String getText(Object element_p) {
            return ((IDiffModelViewer) element_p).getTextDiff();
          }

          @Override
          public String getToolTipText(Object element_p) {
            return wordwrap(((IDiffModelViewer) element_p).getTextDiff(), 80);
          }
        };

      case COL_ACTION:
        return new DiffTreeColumnLabelProvider() {
          @Override
          public String getText(Object element) {
            return DiffAction.getText(((IDiffModelViewer) element));
          }

          @Override
          public Image getImage(Object element) {
            if (((IDiffModelViewer) element).getActionDiff() == null) {
              return Activator.getDefault().getImageRegistry().get("unchecked.gif");

            } else if (((IDiffModelViewer) element).getActionDiff() != FilterAction.NO_ACTION) {
              return Activator.getDefault().getImageRegistry().get("checked.gif");
            }
            return Activator.getDefault().getImageRegistry().get("unchecked.gif");
          }

          /**
           * {@inheritDoc}
           */
          @Override
          public Image getToolTipImage(Object object_p) {
            return Activator.getDefault().getImageRegistry().get("process.png");
          }

          @Override
          public String getToolTipText(Object element) {
            IDiffModelViewer viewer = (IDiffModelViewer) element;
            String value = "Propagation rules :\n";
            boolean addedText = false;
            boolean added = false;

            String tiret = "- ";

            IHandler handler = FilteringDifferencesHandlerHelper.getInstance(context);
            if (handler instanceof CompoundFilteringItems) {
              for (IFilterItem item : ((CompoundFilteringItems) handler).getFilterItems(context)) {
                FilterAction action =
                    item.getDestinationRole(viewer.getRelatedDiff(), viewer.getScopeDiff() == DiffScope.Source ? Role.REFERENCE : Role.TARGET, context);
                if (action != null) {
                  if (addedText) {
                    value += "\n";
                  }
                  value += tiret + item.getDescription(viewer.getRelatedDiff());
                  added = true;
                  addedText = true;
                }
              }
            }

            if (!(viewer == viewer.getRoot())) {

              if (addedText) {
                value += "\n";
              }
              if (viewer.getActionDiff() == FilterAction.NO_ACTION) {
                value += tiret + "A disabled difference is required to merge this difference";
                addedText = true;

              } else {
                value += tiret + "An enabled difference requires merge of this difference";
                addedText = true;
              }

            } else if (viewer.getDefaultActionDiff() != viewer.getActionDiff()) {
              if (addedText) {
                value += "\n";
              }
              value += tiret + "User choice";
              addedText = true;
            }

            if (!added) {
              if (addedText) {
                value += "\n";
              }
              value += tiret + "Default behavior";
            }
            return value;
          }

        };

      case COL_DETAIL:
        return new DiffTreeColumnLabelProvider() {
          @Override
          public String getText(Object element) {
            return ((IDiffModelViewer) element).getDetailedDiff();
          }

          @Override
          public String getToolTipText(Object element) {
            return wordwrap(((IDiffModelViewer) element).getDetailedDiff(), 80);
          }
        };

      default:
      break;
    }

    return null;
  }

  /**
   * Create the actions.
   */
  private void createActions() {
    manageDoubleClick();
  }

  /**
   * @return the listDiff
   */
  public HashMap<IDifference, IDiffModelViewer> getListMerge() {
    return listMerge;
  }

  /**
   * @return the listDiff
   */
  public List<IDiffModelViewer> getListDiff() {
    return listDiff;
  }

  /**
   * @param listDiff the listDiff to set
   */
  public void setListDiff(List<IDiffModelViewer> listDiff) {
    this.listDiff = listDiff;
    if (listDiff != null) {
      viewerResult.setInput(listDiff.toArray());
    }
    viewerResult.refresh();
  }

  /**
   * @return
   */
  public List<String> getColumnNames() {
    return Arrays.asList(getColumns());
  }

  protected String[] getColumns() {
    return columnNames;
  }

  /**
   * @return the viewerResult
   */
  public TreeViewer getViewerResult() {
    return viewerResult;
  }

  /**
   * Manage Double Click in result tree.
   */
  void manageDoubleClick() {
    viewerResult.getTree().addMouseListener(new MouseAdapter() {
      @Override
      public void mouseDoubleClick(MouseEvent e) {
        super.mouseDoubleClick(e);
        final TreeItem var = viewerResult.getTree().getItem(new Point(e.x, e.y));
        if (var != null) {

          selectInExplorer(var.getData());

        }

      }
    });
  }

  /**
   * Select semantic object in Capella Explorer.
   * @param selectedNode
   */
  protected void selectInExplorer(Object selectedNode) {

    EObject elem = null;
    if ((selectedNode instanceof IDiffModelViewer) && (((IDiffModelViewer) selectedNode).getSemanticElementDiff() != null)) {
      elem = ((IDiffModelViewer) selectedNode).getSemanticElementDiff();
    }

    if (null != elem) {
      IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
      try {
        final String ID = "capella.project.explorer"; //$NON-NLS-1$
        IViewPart explorerView = activePage.showView(ID);
        ISelection newSelection = new StructuredSelection(elem);
        explorerView.getViewSite().getSelectionProvider().setSelection(newSelection);
      } catch (PartInitException exception_p) {
        // nothing
      }
    }
  }

  private static String wordwrap(String base, int regex) {
    // Prepare variables
    String rsm = base;
    boolean gotspace = false;
    boolean gotfeed = false;

    // Jump to characters to add line feeds
    int pos = regex;
    while (pos < rsm.length()) {
      // Progressively go backwards until next space
      int bf = pos - regex; // What is the stop point
      gotspace = false;
      gotfeed = false;

      // Find space just before to avoid cutting words
      for (int ap = pos; ap > bf; ap--) {
        // Is it a space?
        if ((String.valueOf(rsm.charAt(ap)).equals(" ") == true) && (gotspace == false)) {
          // Insert line feed and compute position variable
          gotspace = true;
          pos = ap; // Go to position
        }
        // If it is a line feed, go to it
        else if ((String.valueOf(rsm.charAt(ap)).equals("\n") == true) && (gotfeed == false)) {
          pos = ap; // Go to position
          gotfeed = true;
        }
      }
      // Got no feed? Append a line feed to the appropriate place
      if (gotfeed == false) {
        if (gotspace == false) {
          rsm = new StringBuffer(rsm).insert(pos, "\n").toString();
        } else {
          rsm = new StringBuffer(rsm).insert(pos + 1, "\n").toString();
        }
      }
      // Increment position by regex and restart loop
      pos += (regex + 1);
    }
    // Return the result
    return (rsm);
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.swt.widgets.Widget#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
  }

}
