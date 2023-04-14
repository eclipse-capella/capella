/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.preferences.EMFModelValidationPreferences;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.diagram.ui.business.api.image.WorkspaceImageHelper;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.Style;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.model.handler.markers.ICapellaValidationConstants;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.model.handler.validation.CapellaDiagnostician;
import org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram.ImagePathRemoveResolver;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase;

/**
 * Test for I_46 quick fix : remove not found image on element style in diagram
 * @author Séraphin Costa
 *
 */
public class Rule_I_46_IncorrectImagePath extends ValidationRulePartialTestCase {

    private static final String INVALID_DNODE_1 = "_t384wPZTEe2yhKJMWgAjpw";

    private static final String INVALID_DNODE_2 = "_u2KNcPZTEe2yhKJMWgAjpw";

    // parent node
    private static final String SEMANTIC_NODE = "4b5920a3-ced9-4457-a669-db3d7a342f62";

    private int nodeCounter = 0;

    @Override
    protected List<String> getScopeDefinition() {
        return Arrays.asList(SEMANTIC_NODE);
    }

    @Override
    protected EClass getTargetedEClass() {
        return ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT;
    }

    @Override
    protected String getRuleID() {
        return "org.polarsys.capella.core.platform.sirius.sirius.validation.I_46";
    }

    @Override
    protected List<OracleDefinition> getOracleDefinitions() {
        return Arrays.asList(new OracleDefinition(SEMANTIC_NODE, 1));
    }

    private List<IMarker> checkDiagnostic(CapellaDiagnostician capellaDiagnostician, EObject modelToCheck,
            int expectedSeverity, Predicate<String> messagePredicate) {

        // validate
        Diagnostic diagnostic = capellaDiagnostician.validate(modelToCheck);

        // check diagnostic of validation
        int severity = diagnostic.getSeverity();
        assertEquals("Bad diagnostic severity", expectedSeverity, severity);
        List<Diagnostic> children = diagnostic.getChildren();
        assertEquals("Bad diagnostic children number", 2, children.size());

        // return marker
        IFile resourceFile = EcoreUtil2.getFile(modelToCheck.eResource());
        List<IMarker> result = children.stream().map(error -> {
            assertEquals("Bad diagnostic severity", expectedSeverity, error.getSeverity());
            assertTrue("Bad diagnotic message", messagePredicate.test(error.getMessage()));
            return LightMarkerRegistry.getInstance().createMarker(resourceFile, error, ICapellaValidationConstants.CAPELLA_MARKER_ID);
        }).filter(marker -> marker != null).collect(Collectors.toList());
        return result;
    }

    @Override
    protected IStatus testCheckQuickFix(List<IMarker> markers) {
        for (IMarker marker : markers) {
            if (getCheckQuickFix() && marker != null) {
                // execute quick fix
                ImagePathRemoveResolver imagePathRemoveResolver = new ImagePathRemoveResolver();
                imagePathRemoveResolver.run(marker);

                // check quick fix behavior
                EObject genericElement = MarkerViewHelper.getModelElementsFromMarker(marker).get(0);
                assertTrue(genericElement instanceof AbstractDNode);
                AbstractDNode node = (AbstractDNode) genericElement;

                boolean mustBeWSI = (node.getStyle().getDescription() instanceof WorkspaceImageDescription);

                if (mustBeWSI) {
                    if (!(node.getStyle() instanceof WorkspaceImage)) {
                        return Status.error("The I_46 Resolver has failed (must be workspace image)");
                    }
                } else {
                    if (node.getStyle() instanceof WorkspaceImage) {
                        return Status.error("The I_46 Resolver has failed (must not be workspace image)");
                    }
                }
            }
        }
        return Status.OK_STATUS;
    }

    @Override
    public void test() throws Exception {
        CapellaDiagnostician capellaDiagnostician = new CapellaDiagnostician(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory(), new NullProgressMonitor());

        CapellaModel model = getTestModel(getRequiredTestModel());
        CapellaElement diagramToCheck = prepareWorkspaceImage(model);

        EMFModelValidationPreferences.setConstraintDisabled(getRuleID(), false);

        List<IMarker> marker = checkDiagnostic(capellaDiagnostician, diagramToCheck,
                Diagnostic.ERROR, message -> message.contains("can not be found for the diagram element"));

        if (getCheckQuickFix() && marker != null) {
            IStatus status = testCheckQuickFix(marker);
            assertTrue(status.getMessage(), status.isOK());
        }

        EMFModelValidationPreferences.setConstraintDisabled(getRuleID(), EMFModelValidationPreferences.isConstraintDisabledByDefault(getRuleID()));
    }

    private Stream<EObject> getInvalidElement(DView view) {
        DViewQuery query = new DViewQuery(view);
        Iterator<EObject> found = query.getAllContentInRepresentations(eobject -> {
            String id = IdManager.getInstance().getId(eobject);
            return id.equals(INVALID_DNODE_1) || id.equals(INVALID_DNODE_2);
        });
        List<EObject> result = new ArrayList<>();
        found.forEachRemaining(result::add);
        return result.stream();
    }

    private void updateStyleToInvalidImage(AbstractDNode dNode) {
        Style style = dNode.getStyle();
        if (style instanceof BasicLabelStyle) {
            BasicLabelStyle labelStyle = (BasicLabelStyle) style;
            WorkspaceImageHelper.INSTANCE.updateStyle(labelStyle, "file:/C:/INVALID/PATH/11_Images/circle_ws2.png");
        } else {
            fail("No label style for node of test");
        }
    }

    private AbstractDNode countElements(AbstractDNode dNode) {
        this.nodeCounter++;
        return dNode;
    }

    private CapellaElement prepareWorkspaceImage(CapellaModel model) {
        Session session = getSessionForTestModel(getRequiredTestModel());
        this.nodeCounter = 0;
        session.getOwnedViews().stream() //
                .flatMap(this::getInvalidElement) //
                .filter(AbstractDNode.class::isInstance) //
                .map(AbstractDNode.class::cast) //
                .map(this::countElements) //
                .forEach(this::updateStyleToInvalidImage);

        assertEquals("Expected node " + INVALID_DNODE_1 + " and " + INVALID_DNODE_2 + " in aird", 2, this.nodeCounter);

        IScope scope = new ScopeModelWrapper(model);
        CapellaElement element = (CapellaElement) IdManager.getInstance().getEObject(SEMANTIC_NODE, scope);
        return element;
    }

    @Override
    protected String getRequiredTestModel() {
        return "exchange-item-instance-and-part-model";
    }

    @Override
    protected boolean getCheckQuickFix() {
        return true;
    }
}
