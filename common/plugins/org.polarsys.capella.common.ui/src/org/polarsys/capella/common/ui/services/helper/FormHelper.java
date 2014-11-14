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
package org.polarsys.capella.common.ui.services.helper;

import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import org.polarsys.capella.common.mdsofa.common.misc.Couple;

/**
 * UI Forms helper.<br>
 * Allows creation of Composites, Layouts and Forms widgets.
 */
public class FormHelper {
  /**
   * Layout usable types.
   */
  public static enum LayoutType {
    GRID_LAYOUT, TABLEWRAP_LAYOUT
  }

  /**
   * Create a new composite and set the layout using
   * {@link #updateCompositeLayoutWithLayoutType(Composite, org.polarsys.capella.common.mdsofa.common.ui.helper.FormHelper.LayoutType, int)} method.
   * @param toolkit_p
   * @param parent_p
   * @param numColumns_p
   * @return
   */
  public static Composite createCompositeWithLayoutType(FormToolkit toolkit_p, Composite parent_p, LayoutType layoutType_p, int numColumns_p,
      boolean equalWidth_p) {
    Composite result = toolkit_p.createComposite(parent_p);
    updateCompositeLayoutWithLayoutType(result, layoutType_p, numColumns_p, equalWidth_p);
    return result;
  }

  /**
   * Create a user text widget with preceding label.<br>
   * Requires at least a two columns layout so that both the label and the text are displayed on the same line.
   * @param toolkit_p
   * @param parent_p
   * @param labelMessage_p
   * @param initialText_p
   * @param editable_p
   * @return
   */
  public static Couple<Label, Text> createLabelAndText(FormToolkit toolkit_p, Composite parent_p, String labelMessage_p, String initialText_p,
      boolean editable_p) {
    // Create label.
    Label label = toolkit_p.createLabel(parent_p, labelMessage_p, SWT.WRAP);
    label.setForeground(toolkit_p.getColors().getColor(IFormColors.TITLE));
    // Create text.
    Text text = new Text(parent_p, SWT.BORDER);
    text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    // Set existing value.
    if (null != initialText_p) {
      text.setText(initialText_p);
    }
    // Set editable state.
    text.setEditable(editable_p);
    return new Couple<Label, Text>(label, text);
  }

  /**
   * Create a user text widget with preceding label and following button.<br>
   * Requires at least a three columns layout so that the label, the text and the button are displayed on the same line.
   * @param toolkit_p
   * @param parent_p
   * @param labelMessage_p
   * @param buttonLabel_p
   * @param listener_p
   * @return
   */
  public static Couple<Text, Button> createLabelTextAndButton(FormToolkit toolkit_p, Composite parent_p, String labelMessage_p, String buttonLabel_p,
      SelectionListener listener_p) {
    // Create label and text.
    Couple<Label, Text> labelAndText = createLabelAndText(toolkit_p, parent_p, labelMessage_p, null, true);
    // Create button.
    Button button = toolkit_p.createButton(parent_p, buttonLabel_p, SWT.PUSH);
    // Add button listener.
    if (null != listener_p) {
      button.addSelectionListener(listener_p);
    }
    return new Couple<Text, Button>(labelAndText.getValue(), button);
  }

  /**
   * Create a link with a label description.<br>
   * Requires a two columns layout so that both the link and the label are displayed on the same line.
   * @param toolkit_p
   * @param parent_p
   * @param icon_p
   * @param linkText_p
   * @param linkRef_p
   * @param linkDescription_p can be <code>null</code>, if not necessary. Description is rendered as a tooltip.
   * @param listener_p
   */
  public static void createLinkWithDescription(FormToolkit toolkit_p, Composite parent_p, Image icon_p, String linkText_p, Object linkRef_p,
      String linkDescription_p, IHyperlinkListener listener_p) {
    ImageHyperlink specificationLink = toolkit_p.createImageHyperlink(parent_p, SWT.WRAP);
    specificationLink.setText(linkText_p);
    if (null != linkDescription_p) {
      specificationLink.setToolTipText(linkDescription_p);
    }
    specificationLink.setImage(icon_p);
    specificationLink.setHref(linkRef_p);
    specificationLink.addHyperlinkListener(listener_p);
  }

  /**
   * Create a rich {@link FormText} with specified parameters.
   * @param toolkit_p
   * @param parent_p
   * @param content_p
   * @param listener_p
   */
  public static FormText createRichText(FormToolkit toolkit_p, Composite parent_p, String content_p, IHyperlinkListener listener_p) {
    // Create the form text.
    FormText text = toolkit_p.createFormText(parent_p, false);
    if (null != content_p) {
      try {
        // Set its pseudo HTML content.
        text.setText(content_p, true, false);
      } catch (SWTException e) {
        text.setText(e.getMessage(), false, false);
      }
    }
    if (null != listener_p) {
      text.addHyperlinkListener(listener_p);
    }
    return text;
  }

  /**
   * Create a section with a composite child using given child layout type.<br>
   * Created section layouts in filling and grabbing in both directions.
   * @param toolkit_p
   * @param parent_p
   * @param sectionStyle_p
   * @param layoutType_p
   * @param childNumColumns_p
   * @param equalWidth_p
   * @return
   */
  public static Couple<Section, Composite> createSectionWithChildComposite(FormToolkit toolkit_p, Composite parent_p, int sectionStyle_p,
      LayoutType layoutType_p, int childNumColumns_p, boolean equalWidth_p) {
    Section resultingSection = toolkit_p.createSection(parent_p, sectionStyle_p);
    updateControlLayoutDataWithLayoutTypeData(resultingSection, layoutType_p);
    Composite childComposite = createCompositeWithLayoutType(toolkit_p, resultingSection, layoutType_p, childNumColumns_p, equalWidth_p);
    resultingSection.setClient(childComposite);
    return new Couple<Section, Composite>(resultingSection, childComposite);
  }

  /**
   * Create a section with specified title and description.<br>
   * The created section layouts its content according to the {@link TableWrapLayout}.
   * @param toolkit_p
   * @param parent_p
   * @param title_p
   * @param description_p can be <code>null</code>
   * @return a {@link Couple} containing the child composite and the created section.
   */
  public static Couple<Section, Composite> createSectionWithDescription(FormToolkit toolkit_p, Composite parent_p, String title_p, String description_p) {
    // Create the section style.
    int sectionStyle = ExpandableComposite.TITLE_BAR;
    if (null != description_p) {
      // Add description style.
      sectionStyle |= Section.DESCRIPTION;
    }
    // Create the section.
    Couple<Section, Composite> createdSectionWithChildComposite =
        createSectionWithChildComposite(toolkit_p, parent_p, sectionStyle, LayoutType.GRID_LAYOUT, 1, true);
    // Get created section.
    Section section = createdSectionWithChildComposite.getKey();
    // Set it its title.
    section.setText(title_p);
    // Set it its description if necessary.
    if (null != description_p) {
      section.setDescription(description_p);
    }
    return createdSectionWithChildComposite;
  }

  /**
   * Create a section with specified title and description.<br>
   * The created section layouts its content according to the {@link TableWrapLayout}.
   * @param managedForm_p
   * @param parent_p
   * @param title_p
   * @param description_p can be <code>null</code>
   * @return a {@link Couple} containing the child composite and the created section.
   */
  public static Couple<Section, Composite> createTwistieSectionWithDescription(final IManagedForm managedForm_p, Composite parent_p, String title_p,
      String description_p) {
    // Create the section style.
    int sectionStyle = ExpandableComposite.TWISTIE | ExpandableComposite.TITLE_BAR;
    if (null != description_p) {
      // Add description style.
      sectionStyle |= Section.DESCRIPTION;
    }
    // Create the section.
    Couple<Section, Composite> createdSectionWithChildComposite =
        createSectionWithChildComposite(managedForm_p.getToolkit(), parent_p, sectionStyle, LayoutType.TABLEWRAP_LAYOUT, 1, true);
    // Get created section.
    Section section = createdSectionWithChildComposite.getKey();
    section.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    // Set it its title.
    section.setText(title_p);
    // Set it its description if necessary.
    if (null != description_p) {
      section.setDescription(description_p);
    }
    // Handle expansion.
    section.addExpansionListener(new ExpansionAdapter() {
      @Override
      public void expansionStateChanged(ExpansionEvent e) {
        managedForm_p.reflow(false);
      }
    });
    return createdSectionWithChildComposite;
  }

  /**
   * Create a twistie section with a title, a description and a toolbar.
   * @param sectionContainer_p
   * @param managedForm_p
   * @param title_p
   * @param description_p can be <code>null</code>
   * @param isExpanded_p Is the section expanded at startup.
   * @param toolbarActions_p can be <code>null</code>
   * @return
   */
  public static Couple<Section, Composite> createTwistieSectionWithToolbar(Composite sectionContainer_p, IManagedForm managedForm_p, String title_p,
      String description_p, boolean isExpanded_p, List<? extends IAction> toolbarActions_p) {
    // Create the section.
    Couple<Section, Composite> createdSection = FormHelper.createTwistieSectionWithDescription(managedForm_p, sectionContainer_p, title_p, description_p);
    // Make this section expanded at construction time.
    Section section = createdSection.getKey();
    section.setExpanded(isExpanded_p);
    // Create the toolbar for the section.
    if (null != toolbarActions_p) {
      createSectionToolbar(section, toolbarActions_p);
    }
    return createdSection;
  }

  /**
   * Force control size.
   * @param control_p
   * @param widthInChars_p The expected width, in number of chars to display.
   * @param heightInChars_p The expected height, in number of chars to display.
   */
  public static void forceControlSize(Control control_p, int widthInChars_p, int heightInChars_p) {
    // Preconditions.
    if ((null == control_p) || (0 >= widthInChars_p) || (0 >= heightInChars_p)) {
      return;
    }
    // Get font metrics.
    GC gc = new GC(control_p);
    FontMetrics fontMetrics = gc.getFontMetrics();
    gc.dispose();
    // Get layout data.
    Object layoutData = control_p.getLayoutData();
    if (layoutData instanceof GridData) {
      GridData data = (GridData) layoutData;
      data.widthHint = Dialog.convertWidthInCharsToPixels(fontMetrics, widthInChars_p);
      data.heightHint = Dialog.convertHeightInCharsToPixels(fontMetrics, heightInChars_p);
    } else if (layoutData instanceof TableWrapData) {
      TableWrapData data = (TableWrapData) layoutData;
      data.heightHint = Dialog.convertHeightInCharsToPixels(fontMetrics, heightInChars_p);
    }
  }

  /**
   * Update given composite with given layout type and given number of columns (if it makes any sense).<br>
   * Also set the layout data to
   * {@link #updateControlLayoutDataWithLayoutTypeData(Composite, org.polarsys.capella.common.mdsofa.common.ui.helper.FormHelper.LayoutType)}.
   * @param composite_p
   * @param layoutType_p
   * @param numColumns_p
   */
  public static Object updateCompositeLayoutWithLayoutType(Composite composite_p, LayoutType layoutType_p, int numColumns_p, boolean equalWidth_p) {
    Layout selectedLayout = null;
    if (LayoutType.GRID_LAYOUT.equals(layoutType_p)) {
      GridLayout layout = new GridLayout();
      layout.numColumns = numColumns_p;
      layout.makeColumnsEqualWidth = equalWidth_p;
      selectedLayout = layout;
    } else if (LayoutType.TABLEWRAP_LAYOUT.equals(layoutType_p)) {
      TableWrapLayout layout = new TableWrapLayout();
      layout.numColumns = numColumns_p;
      layout.makeColumnsEqualWidth = equalWidth_p;
      selectedLayout = layout;
    }
    // Do not set neither layout if layout could not be created.
    if (null != selectedLayout) {
      composite_p.setLayout(selectedLayout);
    }
    return selectedLayout;
  }

  /**
   * Update given control layout data depending on given layout type.<br>
   * Replace layout data is set to fill/grab in both directions, if it makes any sense.
   * @param control_p
   */
  public static Object updateControlLayoutDataWithLayoutTypeData(Control control_p, LayoutType layoutType_p) {
    Object layoutData = null;
    if (LayoutType.GRID_LAYOUT.equals(layoutType_p)) {
      layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
    } else if (LayoutType.TABLEWRAP_LAYOUT.equals(layoutType_p)) {
      layoutData = new TableWrapData(TableWrapData.FILL_GRAB);
    }
    // Do not set layout data if it could not be created.
    if (null != layoutData) {
      control_p.setLayoutData(layoutData);
    }
    return layoutData;
  }

  /**
   * Create a section toolbar in given section filled in with provided actions.
   * @param section_p
   * @param actions_p
   */
  public static void createSectionToolbar(Section section_p, List<? extends IAction> actions_p) {
    // Preconditions.
    if ((null == actions_p) || actions_p.isEmpty() || (null == section_p)) {
      return;
    }
    // Create a toolbar manager.
    ToolBarManager toolBarManager = new ToolBarManager(SWT.FLAT);
    // Create the widget against the section widget.
    ToolBar toolbar = toolBarManager.createControl(section_p);
    // Create cursor to provide the end-user with an UI feedback.
    final Cursor handCursor = new Cursor(Display.getCurrent(), SWT.CURSOR_HAND);
    toolbar.setCursor(handCursor);
    // Cursor needs to be explicitly disposed
    toolbar.addDisposeListener(new DisposeListener() {
      public void widgetDisposed(DisposeEvent e) {
        if ((null != handCursor) && !handCursor.isDisposed()) {
          handCursor.dispose();
        }
      }
    });
    // Loop over provided actions.
    for (IAction action : actions_p) {
      toolBarManager.add(action);
    }
    toolBarManager.update(true);
    section_p.setTextClient(toolbar);
  }

  /**
   * Adapt specified composite to {@link FormToolkit} recursively.
   * @param toolkit_p
   * @param composite_p
   */
  public static void adaptRecursively(FormToolkit toolkit_p, Composite composite_p) {
    // Preconditions.
    if ((null == composite_p) || (null == toolkit_p)) {
      return;
    }
    toolkit_p.adapt(composite_p, true, true);
    Control[] children = composite_p.getChildren();
    for (Control control : children) {
      if (null != control) {
        toolkit_p.adapt(control, true, true);
        if (control instanceof Composite) {
          adaptRecursively(toolkit_p, (Composite) control);
        }
      }
    }
  }

  /**
   * Adapt background color of specified composite and its children.
   * @param composite_p
   * @param color_p
   * @param ignoreEditableTextField_p <code>true</code> means editable text field won't be modified.
   */
  public static void adaptBackgroundColor(Composite composite_p, Color color_p, boolean ignoreEditableTextField_p) {
    // Preconditions.
    if ((null == composite_p) || (null == color_p)) {
      return;
    }
    composite_p.setBackground(color_p);
    Control[] children = composite_p.getChildren();
    for (Control control : children) {
      if (null != control) {
        boolean applyColor = true;
        if ((control instanceof Text) && !ignoreEditableTextField_p && ((Text) control).getEditable()) {
          applyColor = false;
        }
        if (control instanceof Label) {
          if (((Label) control).getImage() != null) {
            applyColor = false;
          }
        }
        if (applyColor) {
          control.setBackground(color_p);
        }
        if (control instanceof Composite) {
          adaptBackgroundColor((Composite) control, color_p, ignoreEditableTextField_p);
        }
      }
    }
  }
}
