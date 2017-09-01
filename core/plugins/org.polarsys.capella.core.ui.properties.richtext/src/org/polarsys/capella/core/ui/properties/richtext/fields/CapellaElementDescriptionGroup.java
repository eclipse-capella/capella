/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.richtext.fields;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.ui.properties.helpers.NotificationHelper;

/**
 * A customized description group.
 * 
 * @author Joao Barata
 */
public class CapellaElementDescriptionGroup extends ElementDescriptionGroup {

    /**
     * @param parent
     * @param widgetFactory
     */
    public CapellaElementDescriptionGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
        super(parent, widgetFactory);
    }

    /**
     * Called by the section refresh coming from wherever.
     */
    public void loadData(EObject element) {
        loadData(element, CapellacorePackage.eINSTANCE.getCapellaElement_Description());
    }

    /**
     * Set data value i.e change given object for given feature with specified value.
     * 
     * @param object
     * @param feature
     * @param value
     */
    @Override
    protected void setDataValue(final EObject object, final EStructuralFeature feature, final Object value) {
        if (NotificationHelper.isNotificationRequired(object, feature, value)) {
            AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
                public void run() {
                    descriptionTextField.saveContent();
                }
            };
            executeCommand(command);
        }
    }
}
