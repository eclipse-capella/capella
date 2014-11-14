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
package org.polarsys.capella.common.flexibility.wizards.renderer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.bindings.keys.IKeyLookup;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.Activator;
import org.polarsys.capella.common.flexibility.wizards.constants.ICommonConstants;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.ui.services.swt.events.AbstractKeyAdapter;

/**
 */
public class TextRenderer extends AbstractRenderer {

  protected Composite rootTextControl;
  protected Composite rootControl;
  protected Text textControl;
  protected Label imageControl;
  protected Label validateControl;
  Label label;

  Color errorColor;
  Color warningColor;
  Color defaultColor;
  Color disabledColor;

  public Label createPartLabel(Composite parent, String text, boolean required) {
    label = new Label(parent, 0);
    label.setText(text);

    if (required) {
      label.setFont(JFaceResources.getFontRegistry().getBold("org.eclipse.jface.defaultfont")); //$NON-NLS-1$
    }
    return label;
  }

  /**
   * @return
   */
  protected Color getDefaultColor(Display display_p) {
    return display_p.getSystemColor(SWT.COLOR_WHITE);
  }

  /**
   * @see org.polarsys.capella.common.flexibility.wizards.schema.IRenderer#render(org.eclipse.swt.widgets.Composite)
   */
  @Override
  public void performRender(Composite parent_p, IRendererContext context_p) {
    Display display = parent_p.getShell().getDisplay();

    if (isColoredOnValidation()) {
      defaultColor = getDefaultColor(display);
      errorColor = defaultColor;
      warningColor = defaultColor;
      disabledColor = defaultColor;
    }

    if (isDescription() && !Boolean.FALSE.equals(context_p.getParameter("PARAMETER_RENDER_LABEL"))) {
      createPartLabel(parent_p, context_p.getProperty(this).getName() + ICommonConstants.COLON_CHARACTER, false);
    }

    rootControl = new Composite(parent_p, SWT.NONE);

    rootTextControl = new Composite(rootControl, getRootStyle()) {

      /**
       * {@inheritDoc}
       */
      @Override
      public void setBackground(Color color_p) {
        //Avoid default background color to be set
        if ((color_p != null) && !color_p.equals(getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND))) {
          super.setBackground(color_p);
        }
      }

    };

    if (parent_p.getLayout() instanceof GridLayout) {
      GridData data = new GridData(GridData.FILL_HORIZONTAL);
      GridLayout layout = new GridLayout();
      layout.numColumns = getNbColumn();
      layout.makeColumnsEqualWidth = false;
      layout.marginWidth = 0;
      layout.marginHeight = 0;
      rootControl.setLayoutData(data);
      rootControl.setLayout(layout);
    } else {
      rootControl.setLayout(new FillLayout(SWT.HORIZONTAL));
    }

    if (parent_p.getLayout() instanceof GridLayout) {
      GridData data = new GridData(GridData.FILL_HORIZONTAL);
      GridLayout layout = new GridLayout();
      layout.numColumns = getNbTextColumn();
      layout.makeColumnsEqualWidth = false;
      layout.marginWidth = 4;
      layout.marginHeight = 2;
      rootTextControl.setLayoutData(data);
      rootTextControl.setLayout(layout);
    } else {
      rootTextControl.setLayout(new FillLayout(SWT.HORIZONTAL));
    }

    initializeControls(rootControl, context_p);
  }

  /**
   * @return
   */
  protected int getRootStyle() {
    return SWT.BORDER;
  }

  protected boolean isValidationEnd() {
    return true;
  }

  /**
   * @param rootControl_p
   */
  protected void initializeControls(Composite parent_p, final IRendererContext context_p) {

    if (isImage()) {
      imageControl = createImageControl(rootTextControl);
    }

    if (!isValidationEnd()) {
      validateControl = createImageControl(rootTextControl);
    }
    textControl = createTextControl(rootTextControl);

    if (parent_p.getLayout() instanceof GridLayout) {
      textControl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    }
    textControl.addKeyListener(new AbstractKeyAdapter() {
      /**
       * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
       */
      @Override
      public void keyPressed(KeyEvent event_p) {
        if ((event_p.character == SWT.CR)) {
          if (handle(event_p, IKeyLookup.CR_NAME)) {
            handleCRKeyStoke(context_p);
          }
        }
      }
    });

    textControl.addFocusListener(new FocusListener() {
      public void focusGained(FocusEvent event) {
        textControl.forceFocus();
        if (isDisposed()) {
          return;
        }
        if (isEditable(context_p.getProperty(TextRenderer.this), context_p)) {
          textControl.setText(getEditableText(context_p));
        }
      }

      public void focusLost(FocusEvent event) {
        if (isDisposed()) {
          return;
        }
        textChanged(context_p);
      }

    });

    if (isValidationEnd()) {
      validateControl = createImageControl(rootTextControl);
    }
    validateControl.setImage(Activator.getDefault().getImage("full/etool16/empty.gif"));

  }

  protected void handleCRKeyStoke(IRendererContext context_p) {
    textChanged(context_p);
  }

  /**
   * 
   */
  protected void textChanged(IRendererContext context_p) {
    IProperty property = context_p.getProperty(TextRenderer.this);
    if (isEditable(property, context_p)) {
      String newValue = textControl.getText();
      if (!newValue.equals(context_p.getPropertyContext().getCurrentValue(property))) {
        changeValue(property, context_p, newValue);
        updatedValue(property, context_p, newValue);
      }
    }
  }

  /**
   * @return
   */
  protected boolean isImage() {
    return false;
  }

  /**
   * @return
   */
  protected boolean isDescription() {
    return true;
  }

  /**
   * @return
   */
  protected int getNbColumn() {
    return 1;
  }

  /**
   * @return
   */
  protected int getNbTextColumn() {
    return 1 + (isImage() ? 1 : 0) + 1;
  }

  /**
   * @return
   */
  protected String getEditableText(IRendererContext context_p) {
    return textControl.getText();
  }

  /**
   * @param root_p
   * @return
   */
  private Label createImageControl(Composite parent_p) {
    return new Label(parent_p, SWT.NONE);
  }

  /**
   * @param parent_p
   */
  protected Text createTextControl(Composite parent_p) {
    return new Text(parent_p, SWT.NONE);
  }

  public void initialize(IProperty property_p, IRendererContext context_p) {
    setBackgroundTextControl(defaultColor);
    Object value = context_p.getPropertyContext().getDefaultValue(property_p);
    updatedValue(property_p, context_p, value);

  }

  /**
   * @param property_p
   * @return
   */
  protected boolean isEditable(IProperty property_p, IRendererContext context_p) {
    return (property_p instanceof IEditableProperty) && property_p.isEnabled(context_p.getPropertyContext());
  }

  @Override
  public void updatedValue(IProperty property_p, IRendererContext rendererContext_p, Object newValue_p) {

    if (isDisposed()) {
      return;
    }

    if (property_p.equals(rendererContext_p.getProperty(this))) {
      Object value = property_p.toType(newValue_p, rendererContext_p.getPropertyContext());
      IStatus diag_p = property_p.validate(value, rendererContext_p.getPropertyContext());

      if (!validateControl.isDisposed()) {
        validateControl.setToolTipText(diag_p.getMessage());
      }
      String text = getLabelProvider(rendererContext_p).getText(value);

      if (isImage() && !imageControl.isDisposed()) {
        Image image = getLabelProvider(rendererContext_p).getImage(value);
        if ((image != null) && !image.equals(imageControl.getImage())) {
          imageControl.setImage(image);
        }
      }

      if (text == null) {
        text = "";
      }
      if ((label != null) && !label.isDisposed()) {
        label.setEnabled(property_p.isEnabled(rendererContext_p.getPropertyContext()));
      }

      if (!textControl.isDisposed()) {
        textControl.setEditable(isEditable(property_p, rendererContext_p));
        textControl.setText(text);

        if (isColoredOnValidation() && (diag_p != null)) {
          if (diag_p.isOK()) {
            validateControl.setImage(Activator.getDefault().getImage("full/etool16/empty.gif"));
            if (!defaultColor.equals(rootTextControl.getBackground())) {
              setBackgroundTextControl(defaultColor);
            }

            if (!isEditable(property_p, rendererContext_p)) {
              if (!disabledColor.equals(rootTextControl.getBackground())) {
                setBackgroundTextControl(disabledColor);
              }
            }

          } else if (diag_p.matches(IStatus.INFO)) {
            if (!defaultColor.equals(rootTextControl.getBackground())) {
              setBackgroundTextControl(defaultColor);
            }
            validateControl.setImage(Activator.getDefault().getImage("full/etool16/info_tsk.gif"));

          } else if (diag_p.matches(IStatus.WARNING)) {
            if (!warningColor.equals(rootTextControl.getBackground())) {
              setBackgroundTextControl(warningColor);
            }
            validateControl.setImage(Activator.getDefault().getImage("full/etool16/warn_tsk.gif"));
          } else if (diag_p.matches(IStatus.ERROR)) {
            if (!errorColor.equals(rootTextControl.getBackground())) {
              setBackgroundTextControl(errorColor);
            }
            validateControl.setImage(Activator.getDefault().getImage("full/etool16/error_tsk.gif"));
          }
        }
      }
    }
  }

  /**
   * @param defaultColor_p
   */
  protected void setBackgroundTextControl(Color color_p) {
    textControl.setBackground(color_p);
    rootTextControl.setBackground(color_p);
    validateControl.setBackground(color_p);

    if (isImage()) {
      imageControl.setBackground(color_p);
    }

  }

  protected boolean isColoredOnValidation() {
    return true;
  }

  @Override
  public void dispose(IRendererContext context_p) {
    super.dispose(context_p);
    if (textControl != null) {
      textControl.dispose();
    }
    if (imageControl != null) {
      imageControl.dispose();
    }
    if (defaultColor != null) {
      defaultColor.dispose();
    }
    if (errorColor != null) {
      errorColor.dispose();
    }
    if (warningColor != null) {
      warningColor.dispose();
    }
    if (disabledColor != null) {
      disabledColor.dispose();
    }
  }

}
