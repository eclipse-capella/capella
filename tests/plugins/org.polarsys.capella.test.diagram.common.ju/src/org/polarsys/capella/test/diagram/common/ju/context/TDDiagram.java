package org.polarsys.capella.test.diagram.common.ju.context;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.IActivityManager;
import org.eclipse.ui.activities.IWorkbenchActivitySupport;
import org.hamcrest.core.IsInstanceOf;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.TraceabilityServices;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DragAndDropFromProjectExplorerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Wrapper of Traceability diagram.
 * <p>
 * Provide API for editing diagram during tests.
 * </p>
 */
public class TDDiagram extends CommonDiagram {

  private static final String TRACE_TOOL = "trace.creation"; //$NON-NLS-1$
  private static final String DND_OWDER_TOOL = "TD_DnD_TraceableOwner"; //$NON-NLS-1$
  private static final String DND_TRACEABLE_TOOL = "TD_DnD_TraceableOwner"; //$NON-NLS-1$

  public static final String SHOW_RELATED_TOOL = "showRelatedElements"; //$NON-NLS-1$
  public static final String SHOW_ALL_RELATED_TOOL = "showAllRelatedElements"; //$NON-NLS-1$

  protected boolean hierarchical;
  
  /**
   * Default constructor.
   * 
   * @param executionContext execution context
   * @param diagram Sirius Diagram to wrap
   * @param hierarchical diagram variation
   */
  public TDDiagram(SessionContext executionContext, DDiagram diagram, boolean hierarchical) {
    super(executionContext, diagram);
    this.hierarchical = hierarchical;
  }


  /**
   * Enables the Capella Traceability Capability in Activity support.
   * <p>
   * This Capacity is required to create Traceability diagrams.
   * </p>
   * 
   * @param active state of Capacity
   */
  public static void enableTraceabilityCapacity(boolean active) {
    IWorkbenchActivitySupport activitySupport = PlatformUI.getWorkbench().getActivitySupport();

    IActivityManager asm = activitySupport.getActivityManager();
    Set<String> enabledActivityIds = new HashSet<>(asm.getEnabledActivityIds());

    boolean updated;
    if (active) {
      updated = enabledActivityIds.add(TraceabilityServices.GLOBAL_ACTIVITITY_ID);
    } else {
      updated = enabledActivityIds.remove(TraceabilityServices.GLOBAL_ACTIVITITY_ID);      
    }

    if (updated) {
      activitySupport.setEnabledActivityIds(enabledActivityIds);
    }    
  }


  /**
   * Creates and opens a diagram at provided target.
   * 
   * @param context execution context
   * @param target target of the diagram
   * @return a {@link CommonDiagram} instance
   */
  public static TDDiagram openNewDiagram(SessionContext context, EObject target, boolean hierarchical) {
    var uri = TestHelper.getAirdResource(context.getSession()).getURI();
    
    String diagramDescription = hierarchical ? IDiagramNameConstants.HIERARCHICAL_TRACEABILTY_DIAGRAM
            : IDiagramNameConstants.TRACEABILTY_DIAGRAM;
    return (TDDiagram) new CreateDiagramStep(context, target, diagramDescription, uri) {
      @Override
      public TDDiagram getResult() {
        TDDiagram diagramContext = new TDDiagram(getExecutionContext(), diagram, hierarchical);
        getExecutionContext().putSemanticElement(diagramContext.getDiagramId(), diagram);
        return diagramContext;
      }
    }.run().open();
  }

  /**
   * Call the tool to Drag-and-drop an element from Explorer onto the diagram.
   * <p>
   * </p>
   * 
   * @param draggedElement semantic element
   * @return new view if any 
   */
  public EObject dragAndDropFromExplorer(EObject draggedElement) {
    
    boolean isSourceTraceable = draggedElement instanceof Constraint
        || draggedElement instanceof PropertyValueGroup
        || draggedElement instanceof AbstractPropertyValue;
    String toolname = isSourceTraceable
        ? DND_TRACEABLE_TOOL : DND_OWDER_TOOL;

    // By default,  Dnd step expects 1 new View.
    DDiagramElement view = new DragAndDropFromProjectExplorerTool(this, toolname, draggedElement, getDiagramId()) {
      
      @Override
      protected void preRunTest() {
        super.preRunTest();
        if (hierarchical) {
          _elements = getDiagram().getDiagramElements(); // result is not permanent.
        }
      }
      
      private Collection<DDiagramElement> findAllNewElements() {
        List<DDiagramElement> result = new ArrayList<>(getDiagram().getDiagramElements());
        // Remove existing elements.
        result.removeAll(_elements);
        
        var children = new ArrayList<DDiagramElement>();
        for (var newElement : result) {
          if (result.contains(newElement.eContainer())) {
            children.add(newElement);
          }
        }
        // Keep only 
        result.removeAll(children);
        return result;
      }
      
      @Override
      protected void postRunTest() {
        if (hierarchical || !isSourceTraceable) {
          // Non-Traceable elements may provide several nodes.
          // In hierarchical diagram, elements may not appear in the same region as where it is moved.
          _newElements = findAllNewElements();
          
        } else {
          super.postRunTest();
        }
      }
      
      @Override
      public DDiagramElement getResult() {
        return !_newElements.isEmpty() ? super.getResult() : null;
      }
    }.run();
    return view != null ? view.getTarget() : null;
  }

  /**
   * Shows related elements in diagram.
   * 
   * @param source an end of {@link Trace} relationship.
   * @param indirectTraces include indirect relations
   * @param expectedViewedElements elements expected to be viewed
   */
  public void showRelatedElements(NamedElement source, boolean indirectTraces, ModelElement... expectedElements) {
    String toolname = indirectTraces
        ? SHOW_ALL_RELATED_TOOL : SHOW_RELATED_TOOL;
    String[] expectedIds = Stream.of(expectedElements).map(ModelElement::getId).toArray(String[]::new);
    new InsertRemoveTool(this, toolname, source.getId()).insert(expectedIds);
  }
  
  /**
   * Creates a {@link Trace} between to Element.
   * 
   * @param source origin of Trace
   * @param target destination of Trace
   * @return created {@link Trace}
   */
  public EObject trace(ModelElement source, ModelElement target) {
    EObject result = new CreateDEdgeTool(this, TRACE_TOOL, source.getId(), target.getId()).run().getTarget();
    
    assertThat(result, IsInstanceOf.instanceOf(Trace.class));
    return (Trace) result;
  }

}
