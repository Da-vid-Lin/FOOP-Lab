package reflection.uml;

import reflection.uml.ReflectionData.ClassData;
import reflection.uml.ReflectionData.DiagramData;
import reflection.uml.ReflectionData.Link;
import reflection.uml.ReflectionData.LinkType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/** A deliberately simple, deterministic hierarchy layout for students to improve. */
public class UMLLayout {
    public record ClassLayout(double centerX, double centerY, double width, double height) { }

    public Map<String, ClassLayout> calculateLayout(DiagramData diagram) {
        List<String> order = hierarchyOrder(diagram);
        Map<String, Integer> depth = hierarchyDepths(diagram, order);
        Map<String, ClassData> data = new HashMap<>();
        diagram.classes().forEach(type -> data.put(type.qualifiedName(), type));

        Map<Integer, Integer> columns = new HashMap<>();
        Map<String, ClassLayout> result = new HashMap<>();
        for (String name : order) {
            ClassData type = data.get(name);
            int level = depth.getOrDefault(name, 0);
            int column = columns.merge(level, 1, Integer::sum) - 1;
            double width = 180;
            double height = 35 * (1 + type.fields().size()
                    + type.constructors().size() + type.methods().size());
            result.put(name, new ClassLayout(120 + column * 230, 60 + level * 180, width, height));
        }
        return Map.copyOf(result);
    }

    private List<String> hierarchyOrder(DiagramData diagram) {
        Map<String, Integer> inDegree = new HashMap<>();
        Map<String, List<String>> children = new HashMap<>();
        diagram.classes().forEach(type -> {
            inDegree.put(type.qualifiedName(), 0);
            children.put(type.qualifiedName(), new ArrayList<>());
        });
        for (Link link : diagram.links()) {
            if (isHierarchy(link.type()) && children.containsKey(link.to()) && inDegree.containsKey(link.from())) {
                children.get(link.to()).add(link.from());
                inDegree.merge(link.from(), 1, Integer::sum);
            }
        }
        Queue<String> ready = new PriorityQueue<>();
        inDegree.forEach((name, degree) -> { if (degree == 0) ready.add(name); });
        List<String> order = new ArrayList<>();
        while (!ready.isEmpty()) {
            String parent = ready.remove();
            order.add(parent);
            children.get(parent).stream().sorted().forEach(child -> {
                if (inDegree.merge(child, -1, Integer::sum) == 0) ready.add(child);
            });
        }
        // Defensive fallback for malformed cyclic input.
        diagram.classes().stream().map(ClassData::qualifiedName).sorted()
                .filter(name -> !order.contains(name)).forEach(order::add);
        return order;
    }

    private Map<String, Integer> hierarchyDepths(DiagramData diagram, List<String> order) {
        Map<String, Integer> depth = new HashMap<>();
        order.forEach(name -> depth.put(name, 0));
        for (String parent : order) {
            for (Link link : diagram.links()) {
                if (isHierarchy(link.type()) && link.to().equals(parent)) {
                    depth.merge(link.from(), depth.get(parent) + 1, Math::max);
                }
            }
        }
        return depth;
    }

    private static boolean isHierarchy(LinkType type) {
        return type == LinkType.EXTENDS || type == LinkType.IMPLEMENTS;
    }
}
