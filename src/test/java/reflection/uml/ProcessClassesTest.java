package reflection.uml;

import org.junit.jupiter.api.Test;
import reflection.uml.ReflectionData.ClassType;
import reflection.uml.ReflectionData.Visibility;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessClassesTest {
    interface ExampleInterface { }
    static class ExampleClass { private List<String> names; }

    @Test void recognisesAnInterface() {
        assertEquals(ClassType.INTERFACE, new ProcessClasses().getClassType(ExampleInterface.class));
    }

    @Test void extractsADeclaredField() {
        var fields = new ProcessClasses().getFields(ExampleClass.class);
        assertEquals(1, fields.size());
        assertEquals("names", fields.get(0).name());
        assertEquals(Visibility.PRIVATE, fields.get(0).visibility());
        assertTrue(fields.get(0).type().contains("List"));
    }

    @Test void diagramRetainsQualifiedIdentity() {
        var diagram = new ProcessClasses().process(List.of(ExampleClass.class));
        assertEquals(ExampleClass.class.getName(), diagram.classes().get(0).qualifiedName());
    }
}
